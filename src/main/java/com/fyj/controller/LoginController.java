package com.fyj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fyj.dto.Result;
import com.fyj.util.Md5Util;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/loginJsp")
	public ModelAndView loginJsp(){
		return new ModelAndView("/login");
	}
	
	@RequestMapping("/unauthorizedJsp")
	public ModelAndView unauthorizedJsp(){
		return new ModelAndView("/refuse.jsp");
	}
	
	@RequestMapping("/loginLogin")
	@ResponseBody
	public Result login(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try{
		  subject.login(token);
		}catch(Exception e){
			e.printStackTrace();
			return new Result("ERROR","µÇÂ½Ê§°Ü");
		}
		return new Result("Ok","µÇÂ½³É¹¦");
	}
	
}
