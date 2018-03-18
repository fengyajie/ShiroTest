package com.fyj.dao;

import java.util.List;

import com.fyj.dto.SysUserVo;
import com.fyj.entity.SysUser;

public interface SysUserDao {

	public List<SysUserVo> selectList(SysUserVo sysUserVo);
	
	public void  insert(SysUser sysUser);
}
