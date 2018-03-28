package com.fyj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fyj.dto.SysUserVo;
import com.fyj.service.SysUserService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/indexJsp")
	 public ModelAndView indexJsp(){
		 return new ModelAndView("/index");
	 }
	
	@RequestMapping("/selectUser")
	@ResponseBody
	public SysUserVo selectUser(){
		return sysUserService.selectList(new SysUserVo()).get(0);
	}
}
