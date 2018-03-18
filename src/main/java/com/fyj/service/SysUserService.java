package com.fyj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fyj.dto.SysUserVo;
import com.fyj.entity.SysUser;

@Service("sysUserService")
public interface SysUserService {

	public List<SysUserVo> selectList(SysUserVo sysUserVo);
	
	public void  insert(SysUser sysUser);
}
