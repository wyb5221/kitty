package com.louis.kitty.admin.sevice.impl;

import java.util.ArrayList;
import java.util.List;

import com.louis.kitty.admin.dao.DataOperateMyMapper;
import com.louis.kitty.admin.dao.SysInfoMapper;
import com.louis.kitty.admin.dto.resp.SysInfoMenuDTO;
import com.louis.kitty.admin.model.SysInfo;
import lombok.extern.slf4j.Slf4j;
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
			return sysMenuMapper.insertSelective(record);
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
		findChildren(sysMenus, menus, menuType);
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
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	@Override
	public List<SysInfoMenuDTO> findInfoTree(int menuType) {
		List<SysInfoMenuDTO> infoMenuList = new ArrayList<>();

		List<SysInfo> infoList = sysInfoMapper.findAll();
		infoList.stream().forEach(info -> {
			SysInfoMenuDTO sysInfoMenuDTO = new SysInfoMenuDTO();
			sysInfoMenuDTO.setInfokey(info.getId());
			sysInfoMenuDTO.setName(info.getName());
			sysInfoMenuDTO.setCode(info.getCode());

			List<SysMenu> menuList = findBySysKeyTree(info.getId(), 0);
			sysInfoMenuDTO.setMenuList(menuList);

			infoMenuList.add(sysInfoMenuDTO);
		});

		return infoMenuList;
	}

	public List<SysMenu> findBySysKeyTree(Long sysKey, int menuType) {
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
		findChildren(sysMenus, menus, menuType);
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

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (com.louis.kitty.admin.model.SysMenu SysMenu : SysMenus) {
			List<com.louis.kitty.admin.model.SysMenu> children = new ArrayList<>();
			for (com.louis.kitty.admin.model.SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
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
			findChildren(children, menus, menuType);
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
