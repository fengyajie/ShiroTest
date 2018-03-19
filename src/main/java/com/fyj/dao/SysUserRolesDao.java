package com.fyj.dao;

import java.util.List;

import com.fyj.dto.SysUserRolesVo;

public interface SysUserRolesDao {
	public List<SysUserRolesVo> findRoleIdsByUserId(Long userId);
}
