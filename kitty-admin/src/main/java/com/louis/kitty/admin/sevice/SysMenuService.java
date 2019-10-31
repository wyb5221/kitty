package com.louis.kitty.admin.sevice;

import java.util.List;

import com.louis.kitty.admin.dto.resp.SysInfoMenuDTO;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.core.service.CurdService;

/**
 * 菜单管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysMenuService extends CurdService<SysMenu> {

	/**
	 * 根据用户查询菜单树,用户ID和用户名为空则查询全部
	 * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
	 * @param userName
	 * @return
	 */
	List<SysMenu> findTree(String userName, int menuType);

	/**
	 * 根据系统名称查询菜单
	 * @param infoName
	 * @param menuType
	 * @return
	 */
	List<SysMenu> findByInfoNameTree(String infoName, int menuType);

	/**
	 * 查询系统菜单树
	 * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
	 * @return
	 */
	List<SysInfoMenuDTO> findInfoTree(int menuType);

	/**
	 * 根据用户名查找菜单列表
	 * @param userName
	 * @return
	 */
	List<SysMenu> findByUser(String userName);

	/**
	 * 根据系统名称查询系统菜单
	 * @param InfoName
	 * @return
	 */
	List<SysMenu> findByInfoName(String InfoName);
}
