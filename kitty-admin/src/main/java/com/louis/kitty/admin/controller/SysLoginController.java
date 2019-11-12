package com.louis.kitty.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.louis.kitty.admin.enums.UserStatusEnum;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.security.JwtAuthenticatioToken;
import com.louis.kitty.admin.sevice.SysUserService;
import com.louis.kitty.admin.util.PasswordUtils;
import com.louis.kitty.admin.util.SecurityUtils;
import com.louis.kitty.admin.vo.LoginBean;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
public class SysLoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到验证码到 session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);	
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		String captcha = loginBean.getCaptcha();
		
		// 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha == null){
			return HttpResult.error("验证码已失效");
		}
		if(!captcha.equals(kaptcha)){
			return HttpResult.error("验证码不正确");
		}
		
		// 用户信息
		SysUser user = sysUserService.findByName(username);

		// 账号异常
		if (user == null || !UserStatusEnum.NORMAL.getCode().equals(user.getStatus())) {
			return HttpResult.error("账号状态异常,请联系管理员");
		}

		Long id = user.getId();
		if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			sysUserService.loginErr(id);
			return HttpResult.error("密码不正确");
		}

		// 系统登录认证
		JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);

		//登录成功重置登录错误次数
		sysUserService.loginTimeReset(id);
				
		return HttpResult.ok(token);
	}

}
