/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.MediaTypes;

import com.taifinance.rcp.common.entity.User;
import com.taifinance.rcp.common.service.UserService;

/**
 * RegisterController包含了用户注册的Restful API接口
 * 
 * @author fisher
 */
@Controller
@RequestMapping(value = "/api/v1/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	/**
	 * 注册用户服务
	 * 
	 * @param	user
	 * 			客户端提交的包含用户注册信息的JSON对象
	 * 
	 * @return	相应对象
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<User> register(@Valid User user) {
		
		userService.registerUser(user);
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}


	/**
	 * 用户名唯一性校验服务
	 * 
	 * @param	username
	 * 			客户端提交的需要校验的用户名
	 * 
	 * @return	若用户名不存在返回true，否则返回false
	 */
	@RequestMapping(value = "checkUsername")
	public ResponseEntity<Boolean> checkUsername(@RequestParam("username") String username) {
		
		Boolean isUsernameNotExist = Boolean.FALSE;
		
		if (userService.findUserByUsername(username) == null) {
			isUsernameNotExist = Boolean.TRUE;
		}
		
		return new ResponseEntity<Boolean>(isUsernameNotExist, HttpStatus.OK);
	}
}
