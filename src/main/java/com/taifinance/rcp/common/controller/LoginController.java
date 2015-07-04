/*******************************************************************************
 * Copyright (c) 2015, TaiFinance and/or its affiliates.
 *
 * All rights reserved.
 *******************************************************************************/
package com.taifinance.rcp.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController包含了登录的Restful API接口
 * 
 * @author fisher
 */
@Controller
@RequestMapping(value = "/api/v1/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST)
	public String login() {
		return "account/login";
	}
}