/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有实体对象的基类
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略
 * 
 * @author fisher
 */
@MappedSuperclass	// JPA 基类的标识
public abstract class IdEntity {

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
