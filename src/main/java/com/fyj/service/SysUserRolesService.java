package com.fyj.service;

import org.springframework.stereotype.Service;

@Service("sysUserRolesService")
public interface SysUserRolesService {
   public String findRoleIdsByUserId(Long userId);
}
