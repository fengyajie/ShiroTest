package com.fyj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class SuccessController {

	@RequestMapping("/success")
	public ModelAndView success(){
		return new ModelAndView("/success");
	}
}
