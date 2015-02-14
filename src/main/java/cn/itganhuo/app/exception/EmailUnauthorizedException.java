/*
 * Copyright 2014-2024 the https://github.com/xiaoxing598/itganhuo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * This project consists of JAVA private school online learning community group Friends co-creator [QQ group 329232140].
 * 本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140];
 * See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams];
 * The author does not guarantee the quality of the project and its stability, reliability, and security does not bear any responsibility.
 * 作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任.
 */
package cn.itganhuo.app.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 自定义异常：用户登录时发现未认证邮箱。
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class EmailUnauthorizedException extends AccountException {

	private static final long serialVersionUID = -8101093788178259739L;

	public EmailUnauthorizedException() {
		super();
	}

	public EmailUnauthorizedException(String message) {
		super(message);
	}

	public EmailUnauthorizedException(Throwable cause) {
		super(cause);
	}

	public EmailUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
