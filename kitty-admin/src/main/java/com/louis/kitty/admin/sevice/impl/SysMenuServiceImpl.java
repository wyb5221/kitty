package com.louis.kitty.admin.sevice.impl;

import java.util.ArrayList;
import java.util.List;

import com.louis.kitty.admin.dao.DataOperateMyMapper;
import com.louis.kitty.admin.dao.SysInfoMapper;
import com.louis.kitty.admin.dto.resp.SysInfoMenuDTO;
import com.louis.kitty.admin.enums.DelFlagEnum;
import com.louis.kitty.admin.enums.InterfaceTypeEnum;
import com.louis.kitty.admin.enums.MenuTypeEnum;
import com.louis.kitty.admin.model.SysInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.constants.SysConstants;
import com.louis.kitty.admin.dao.SysMenuMapper;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.sevice.SysMenuService;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysInfoMapper sysInfoMapper;

	@Autowired
	private DataOperateMyMapper dataOperateMyMapper;

	@Override
	public int save(SysMenu record) {
		if(record.getId() == null || record.getId() == 0) {
			//新增菜单
			//先添加菜单栏目
			sysMenuMapper.insertSelective(record);
			Long id = record.getId();
			/**
			 * 如果是新增菜单，则自动添加一条类型为3的api接口管理默认菜单
			 */
			if(MenuTypeEnum.MENU.getCode().equals(record.getType())){
				SysMenu sysMenu = new SysMenu();
				sysMenu.setSysKey(record.getSysKey());
				sysMenu.setName("写接口");
				sysMenu.setParentId(id);
				sysMenu.setPerms("write");
				sysMenu.setType(MenuTypeEnum.INTERFACE.getCode());
				sysMenu.setOrderNum(0);
				sysMenu.setDelFlag(DelFlagEnum.UNDELETED.getCode());
				sysMenuMapper.insertSelective(sysMenu);
			}

			return id.intValue();
		}
		if(record.getParentId() == null) {
			record.setParentId(0L);
		}
		return sysMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysMenu record) {
		return sysMenuMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysMenu> records) {
		for(SysMenu record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysMenu findById(Long id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(SysMenu record) {
		return 0;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysMenuMapper);
	}
	
	@Override
	public List<SysMenu> findTree(String userName, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByUser(userName);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType, null);
		return sysMenus;
	}

	@Override
	public List<SysMenu> findByInfoNameTree(String infoName, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByInfoName(infoName);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType, null);
		return sysMenus;
	}

	@Override
	public List<SysInfoMenuDTO> findInfoTree(int menuType, String interfaceType) {
		List<SysInfoMenuDTO> infoMenuList = new ArrayList<>();

		List<SysInfo> infoList = sysInfoMapper.findAll();
		infoList.stream().forEach(info -> {
			SysInfoMenuDTO sysInfoMenuDTO = new SysInfoMenuDTO();
			sysInfoMenuDTO.setSysKey(info.getId());
			sysInfoMenuDTO.setName(info.getName());
			sysInfoMenuDTO.setCode(info.getCode());

			List<SysMenu> menuList = findBySysKeyTree(info.getId(), 0, interfaceType);
			sysInfoMenuDTO.setChildren(menuList);

			infoMenuList.add(sysInfoMenuDTO);
		});

		return infoMenuList;
	}

	public List<SysMenu> findBySysKeyTree(Long sysKey, int menuType, String interfaceType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findBySysKey(sysKey);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType, interfaceType);
		return sysMenus;
	}

	/**
	 * 查询系统菜单
	 * @param sysKey
	 * @return
	 */
	public List<SysMenu> findBySysKey(Long sysKey) {
		log.info("--查询系统菜单findBySysKey--sysKey:{}", sysKey);
		return sysMenuMapper.findBySysKey(sysKey);
	}

	@Override
	public List<SysMenu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findByUserName(userName);
	}

	@Override
	public List<SysMenu> findByInfoName(String InfoName) {
		log.info("--查询系统菜单findByInfoName入参--InfoName:{}", InfoName);
		return sysMenuMapper.findByInfoName(InfoName);
	}

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType, String interfaceType) {
		for (com.louis.kitty.admin.model.SysMenu SysMenu : SysMenus) {
			List<com.louis.kitty.admin.model.SysMenu> children = new ArrayList<>();
			for (com.louis.kitty.admin.model.SysMenu menu : menus) {

				// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉

				if(menuType == 1 ){
					if(MenuTypeEnum.BUTTON.getCode().equals(menu.getType()) ||
							MenuTypeEnum.INTERFACE.equals(menu.getType())){
						continue ;
					}
					//只展示默认菜单
					if(StringUtils.isEmpty(menu.getSysDefault())){
						continue;
					}
				}
				//当接口调用不是role角色菜单擦查询时都要过滤掉接口类型的菜单栏目
				if(!InterfaceTypeEnum.ROLE.getCode().equals(interfaceType)){
					//判断是否是接口类型
					if(MenuTypeEnum.INTERFACE.getCode().equals(menu.getType())){
						continue;
					}
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType, interfaceType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for(SysMenu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
}
