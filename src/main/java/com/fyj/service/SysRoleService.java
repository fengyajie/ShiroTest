package com.fyj.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.fyj.dto.SysRoleVo;

@Service("sysRoleService")
public interface SysRoleService {

	Set<String> findRoles(String userName);
	
	SysRoleVo selectById(Long id);
}
