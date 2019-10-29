package com.louis.kitty.admin.util;

import com.louis.kitty.admin.constants.SysConstants;
import com.louis.kitty.admin.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class WebUtils {

    @Autowired
    private HttpSession session;

    public void clearSession(){
        session.invalidate();
    }

    public String getSessionId(){
        return session.getId();
    }

    public <T> void setSession(String key, T value){
        session.setAttribute(key, value);
    }

    public Object getSession(String key){
        return session.getAttribute(key);
    }

    /**
     * 保存用户信息到缓存
     * @param user
     */
    public void setUserSession(SysUser user){
        setSession(SysConstants.USER_INFO, user);
    }

    /**
     *
     * @return
     */
    public SysUser getUserSession(){
        Object user = getSession(SysConstants.USER_INFO);
        if(user == null){
            user = new SysUser();
            ((SysUser) user).setUserName("wyb");
            ((SysUser) user).setName("wyb");
            ((SysUser) user).setId(new Long(33));
        }
        return (SysUser)user;
    }
}
