/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;

@Entity
@Table(name = "rcp_user")
public class User extends IdEntity {
	
	/** 登录名  */
	private String username;
	
	/** 公司姓名  */
	private String companyName;
	
	/** 明文密码  */
	private String plainPassword;
	
	/** 密文密码  */
	private String password;
	
	/** 被用以加密密码的salt值 */
	private String salt;
	
	/** 角色列表，多个角色以逗号分隔 */
	private String roles;
	
	/** 注册日期 */
	private Date registerDate;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	@NotBlank
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotBlank
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonIgnore
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Transient
	@JsonIgnore
	public List<String> getRoleList() {
		return ImmutableList.copyOf(StringUtils.split(roles, ","));
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}