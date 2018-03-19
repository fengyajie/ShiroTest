package com.fyj.service;

import java.util.Set;

import com.fyj.dto.SysRoleVo;

public interface SysRoleService {

	Set<String> findRoles(String userName);
	
	SysRoleVo selectById(Long id);
}
