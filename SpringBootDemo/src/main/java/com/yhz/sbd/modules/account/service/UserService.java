package com.yhz.sbd.modules.account.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.account.dao.UserDao;
import com.yhz.sbd.modules.account.entity.User;
import com.yhz.sbd.modules.test.entity.City;

public interface UserService {
	
	Result<User> insertUser(User user);
	
	
	

}
