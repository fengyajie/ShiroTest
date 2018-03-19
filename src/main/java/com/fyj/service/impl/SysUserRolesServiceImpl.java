package com.fyj.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fyj.dao.SysUserRolesDao;
import com.fyj.dto.SysUserRolesVo;
import com.fyj.service.SysUserRolesService;

public class SysUserRolesServiceImpl implements SysUserRolesService {

	@Autowired
	private SysUserRolesDao sysUserRolesDao;
	
	public String findRoleIdsByUserId(Long userId) {
		List<SysUserRolesVo> list = sysUserRolesDao.findRoleIdsByUserId(userId);
		if(list == null || list.size() < 0) {
			return null;
		}
		SysUserRolesVo sysUserRolesVo = list.get(0);
		String roleId = sysUserRolesVo.getRoleId();
		return roleId;
	}
   
}
