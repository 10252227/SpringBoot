package com.yhz.sbd.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.common.vo.Result.ResultEnum;
import com.yhz.sbd.modules.account.dao.UserDao;
import com.yhz.sbd.modules.account.entity.User;
import com.yhz.sbd.modules.account.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserDao userDao;

	@Override
	public Result<User> insertUser(User user) {
		Result<User> result = new Result<User>(ResultEnum.SUCCESS.status,"Insert success!");
		try {
			userDao.insertUser(user);
			result.setObject(user);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
			
		}
		return result;
		
	}

}
