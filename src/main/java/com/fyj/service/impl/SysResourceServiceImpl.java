package com.fyj.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyj.dao.SysResourceDao;
import com.fyj.dao.SysRoleDao;
import com.fyj.dto.SysResourceVo;
import com.fyj.dto.SysRoleVo;
import com.fyj.dto.SysUserVo;
import com.fyj.service.SysResourceService;
import com.fyj.service.SysUserRolesService;
import com.fyj.service.SysUserService;

@Service("sysResourceServiceImpl")
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRolesService sysUserRolesService;
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysResourceDao  sysResourceDao;
	
	public Set<String> findPerimissions(String userName) {
		SysUserVo sysUserVo = new SysUserVo();
		sysUserVo.setUserName(userName);
		List<SysUserVo> sysUserVoList = sysUserService.selectList(sysUserVo);
		if(sysUserVoList == null || sysUserVoList.size() < 0 ) {
			return Collections.EMPTY_SET;
		}
		Long userId  = sysUserVoList.get(0).getId();
		String roleIds = sysUserRolesService.findRoleIdsByUserId(userId);
		
		String[] roleIdArr = roleIds.split(",");
		
		Set<String> permissions = new HashSet<String>();
		for(String roleId : roleIdArr) {
			SysRoleVo sysRoleVo = sysRoleDao.selectById(Long.valueOf(roleId));
			String resourceIds = sysRoleVo.getResourceIds();
			String[] resourceIdsArr = resourceIds.split(",");
			for(String resourceId : resourceIdsArr) {
				SysResourceVo sysResourceVo = sysResourceDao.selectById(Long.valueOf(resourceId));
				permissions.add(sysResourceVo.getPermission());
			}
		}
		return permissions;
	}

	public SysResourceVo selectById(Long id) {
		return sysResourceDao.selectById(id);
	}

	public List<SysResourceVo> findAll() {
		List<SysResourceVo> sysResourceVoList = null;
		try {
			sysResourceVoList = sysResourceDao.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sysResourceVoList;
	}

}
