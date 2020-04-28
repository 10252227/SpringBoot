package com.yhz.sbd.modules.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.account.entity.User;
import com.yhz.sbd.modules.account.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/adduser",consumes="application/json")
	public Result<User> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
		
	}
	
	

}
