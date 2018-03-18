package com.fyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/indexJsp")
	 public ModelAndView indexJsp(){
		 return new ModelAndView("/index");
	 }
}
