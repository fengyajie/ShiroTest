package com.fyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {
      
	 @RequestMapping("/registerJsp")
	 public ModelAndView registerJsp(){
		 return new ModelAndView("/register");
	 }
}
