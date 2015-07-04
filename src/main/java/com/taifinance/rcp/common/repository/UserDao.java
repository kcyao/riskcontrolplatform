/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taifinance.rcp.common.entity.User;


public interface UserDao extends JpaRepository<User, Long> {
	
	/**
	 * 根据用户名获取用户
	 * 
	 * @param	username
	 * 			用户名
	 * 
	 * @return	指定用户名对应的用户对象
	 */
	User findByUsername(String username);
}
