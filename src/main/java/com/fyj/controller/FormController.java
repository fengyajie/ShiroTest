package com.fyj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyj.dto.Result;
import com.fyj.dto.SysUserVo;
import com.fyj.entity.SysUser;
import com.fyj.service.SysUserService;
import com.fyj.util.Md5Util;
import com.fyj.util.RandomSaltUtil;

@Controller
@RequestMapping("/form")
public class FormController {
   
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/userAdd")
	@ResponseBody
	public Result userAdd(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		SysUserVo sysUserVo = new SysUserVo();
		sysUserVo.setUserName(userName);
		List<SysUserVo> sysUserVoList = sysUserService.selectList(sysUserVo);
		if(sysUserVoList == null || sysUserVoList.size() < 0){
			String salt = RandomSaltUtil.randomSalt();
			SysUser sysUser = new SysUser();
			sysUser.setUserName(userName);
			sysUser.setRealName(request.getParameter("realName"));
			sysUser.setSalt(salt);
			sysUser.setPassword(Md5Util.md5Degest(password+salt));
			sysUser.setLocked(1);
			sysUserService.insert(sysUser);
		}
		return new Result("OK","");
	}
}
