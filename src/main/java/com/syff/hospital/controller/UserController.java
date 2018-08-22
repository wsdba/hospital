package com.syff.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.syff.hospital.domain.User;
import com.syff.hospital.service.UserService;


/**
 * @RestController:spring mvc的注解，
 * 相当于@Controller与@ResponseBody的合体，可以直接返回json
 * 
 * 
    这个类其实就是开发中，开发一个spring-boot程序的最基本最常用的方式。（在微服务应用中，用到类似于"Java企业应用开发实践"系列中的父子模块开发，之后再说）
    相对于ssm而言，spring-boot的读取属性文件的方式也相当容易，读取属性文件常用的三种方式
        使用FileUtil去读：见第一章 属性文件操作工具类
        使用如上的注解实现（最推荐的方式）
        使用Environment这个类来获取就行（这个可能写错类名了）

 */
@RestController
public class UserController {

    @Autowired//自动注入，可以直接省略掉set和get，它会帮我们set和get
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser() {
        return userService.getUser();
    }
    @RequestMapping(value="/addUser",method=RequestMethod.POST)
    public int addUser(@RequestBody List<User> users) {
//    	List<User> users = new ArrayList<User>();
//    	users.add(user);
        return userService.addUserWithBackId(users);
    }
}