/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.taifinance.rcp.common.entity.User;
import com.taifinance.rcp.common.repository.UserDao;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

/**
 * 用户管理业务
 * 
 * @author fisher
 */
@Component	// Spring Service Bean的标识
@Transactional
public class UserService {

	/** 密码加密使用的哈希算法 */
	public static final String HASH_ALGORITHM = "SHA-1";
	
	/** 哈希算法被迭代的次数 */
	public static final int HASH_INTERATIONS = 1024;
	
	/** 随机生成salt值的位数 */
	private static final int SALT_SIZE = 8;

	/** 用户数据访问 */
	private UserDao userDao;

	/** springside封装的日期类 */
	private Clock clock = Clock.DEFAULT;

	/**
	 * 获取所有用户的列表
	 *
	 * @return	包含所有用户的列表
	 */
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	/**
	 * 根据用户id获取用户
	 * 
	 * @param	id
	 * 			用户id
	 * 
	 * @return	指定id对应的用户对象
	 */
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param	username
	 * 			用户名
	 * 
	 * @return	指定用户名对应的用户对象
	 */
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * 用户信息注册
	 * 
	 * @param	user
	 * 			用户信息
	 */
	public void registerUser(User user) {
		
		// 首先加密密码
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(clock.getCurrentDate());

		userDao.save(user);
	}

	/**
	 * 用户信息更新
	 * 
	 * @param	user
	 * 			用户信息
	 */
	public void updateUser(User user) {
		
		// 如果更新了明文密码，需要重新加密生成密文
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		
		userDao.save(user);
	}

	/**
	 * 用户信息删除
	 * 
	 * @param	id
	 * 			用户id
	 */
	public void deleteUser(Long id) {
		
		userDao.delete(id);
	}

	/**
	 * 加密明文密码，生成随机的salt并经过1024次 sha-1 哈希迭代
	 */
	private void entryptPassword(User user) {
		
		// 随机生成并设置salt值
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		// 通过salt值与明文密码生成密文密码
		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
