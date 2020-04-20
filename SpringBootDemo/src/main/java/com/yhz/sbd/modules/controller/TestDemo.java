package com.yhz.sbd.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestDemo {
	@RequestMapping("/test/Hello")
	@ResponseBody
	public String getName() {
		return "Hello world,this is spring boot demo";
	}

}
