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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <h2>将系统内部抛出到本类的异常记录到日志</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>直接抛到本类就可以被写入日志</dd>
 * </dl>
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 7267973861644368332L;
	private static final Logger log = LogManager.getLogger(InternalException.class.getName());

	public InternalException(String message) {
		super(message);
		if (log.isErrorEnabled()) {
			log.error(message);
		}
	}

	public InternalException(Logger logger, Exception e) {
		if (logger.isErrorEnabled()) {
			logger.error("Internal system thrown.", e);
		}
	}

	public InternalException(Logger logger, String message) {
		if (logger.isErrorEnabled()) {
			logger.error(message);
		}
	}

	public InternalException(Logger logger, String message, Exception e) {
		if (logger.isErrorEnabled()) {
			logger.error("Internal system thrown.".concat(message), e);
		}
	}

}
