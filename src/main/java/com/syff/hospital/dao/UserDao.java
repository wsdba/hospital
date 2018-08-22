package com.syff.hospital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syff.hospital.domain.User;
import com.syff.hospital.mapper.UserMapper;


@Repository
public class UserDao {
    
    @Autowired
    private UserMapper userMapper;
    
    public int insertUser(String username, String password){
        return userMapper.insertUser(username, password);
    }
    
    public int insertUserWithBackId(List<User> users){    
//        return userMapper.insertUser(user.get);
    	for(User user:users){
    		System.out.println(user.getPassword());
        	System.out.println(user.getUsername());	
    	}
    	//通过mybatis往数据库里面插入一条数据
    	userMapper.insertUser(users.get(0).getUsername(), users.get(0).getPassword());
    	return users.size();
    }
    
}
