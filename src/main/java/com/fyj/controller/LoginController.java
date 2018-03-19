package com.fyj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	/*@RequestMapping("/loginJsp")
	public ModelAndView loginJsp(){
		return new ModelAndView("/login");
	}*/
	
	@RequestMapping("/unauthorizedJsp")
	public ModelAndView unauthorizedJsp(){
		return new ModelAndView("/refuse.jsp");
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView login(HttpServletRequest request, ModelMap model){
		/*String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try{
		  subject.login(token);
		}catch(Exception e){
			e.printStackTrace();
			return new Result("ERROR","µÇÂ½Ê§°Ü");
		}
		return new Result("Ok","µÇÂ½³É¹¦");*/
		Exception shiroLoginFailureEx = (Exception)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if(shiroLoginFailureEx != null) {
			model.addAttribute("error", shiroLoginFailureEx.getMessage());
		}
		Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()) {
        	subject.logout();
        }
        if(model.containsAttribute("error")) {
        	model.remove("message");
        }
        return new ModelAndView("/login");
	}
	
}
