package com.fyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyj.dao.SysUserDao;
import com.fyj.dto.SysUserVo;
import com.fyj.entity.SysUser;
import com.fyj.service.SysUserService;

@Service("sysUserServiceImpl")
public class SysuserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	public List<SysUserVo> selectList(SysUserVo sysUserVo) {
		List<SysUserVo> sysUserList = null;
		try{
			sysUserList = sysUserDao.selectList(sysUserVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysUserList;
	}

	public void insert(SysUser sysUser) {
		try{
		  sysUserDao.insert(sysUser);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
