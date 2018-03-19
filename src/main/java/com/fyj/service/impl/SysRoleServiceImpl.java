package com.fyj.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fyj.dao.SysRoleDao;
import com.fyj.dto.SysRoleVo;
import com.fyj.dto.SysUserVo;
import com.fyj.service.SysRoleService;
import com.fyj.service.SysUserRolesService;
import com.fyj.service.SysUserService;

public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRolesService sysUserRolesService;
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	public Set<String> findRoles(String userName) {
		SysUserVo sysUserVo = new SysUserVo();
		sysUserVo.setUserName(userName);
		List<SysUserVo> sysUserVoList = sysUserService.selectList(sysUserVo);
		if(sysUserVoList == null || sysUserVoList.size() < 0 ) {
			return Collections.EMPTY_SET;
		}
		Long userId  = sysUserVoList.get(0).getId();
		String roleIds = sysUserRolesService.findRoleIdsByUserId(userId);
		Set<String> roleSet = new HashSet<String>();
		
		String[] roleIdArr = roleIds.split(",");
		
		for(String roleId : roleIdArr) {
			SysRoleVo sysRoleVo = sysRoleDao.selectById(Long.valueOf(roleId));
			String role = sysRoleVo.getRole();
			roleSet.add(role);
		}
		
		return roleSet;
	}

	public SysRoleVo selectById(Long id) {
		return sysRoleDao.selectById(id);
	}

}
