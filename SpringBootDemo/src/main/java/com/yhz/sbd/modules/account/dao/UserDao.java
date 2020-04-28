package com.yhz.sbd.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.account.entity.User;
@Mapper
public interface UserDao {
	
	@Insert("insert into user(user_name,password)"+ "values(#{userName},#{password})")
	void insertUser(User user);

}
