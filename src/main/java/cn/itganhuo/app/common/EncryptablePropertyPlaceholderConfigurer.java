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
package cn.itganhuo.app.common;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import cn.itganhuo.app.exception.InternalException;

/**
 * 容器启动时通过PropertyPlaceholderConfigurer类读取jdbc.properties文件里的数据库加密配置信息，
 * 使用自定义一个继承PropertyPlaceholderConfigurer的类实现解密。
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 *
 */
public class EncryptablePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private final static Logger log = LogManager
			.getLogger(EncryptablePropertyPlaceholderConfigurer.class.getName());

	private String key = "http://www.itganhuo.cn";

	public EncryptablePropertyPlaceholderConfigurer(String key) {
		log.debug("key={}", key);
		this.key = key;
	}

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			String DriverClassName = props.getProperty("jdbc.driverClassName");
			if (DriverClassName != null) {
				props.setProperty(
						"jdbc.driverClassName",
						new String(Aes.decrypt(
								Aes.parseHexStr2Byte(DriverClassName), key)));
			}
			String Url = props.getProperty("jdbc.url");
			if (Url != null) {
				props.setProperty("jdbc.url",
						new String(Aes.decrypt(Aes.parseHexStr2Byte(Url), key)));
			}
			String Username = props.getProperty("jdbc.username");
			if (Username != null) {
				props.setProperty(
						"jdbc.username",
						new String(Aes.decrypt(Aes.parseHexStr2Byte(Username),
								key)));
			}
			String Password = props.getProperty("jdbc.password");
			if (Password != null) {
				props.setProperty(
						"jdbc.password",
						new String(Aes.decrypt(Aes.parseHexStr2Byte(Password),
								key)));
			}

			super.processProperties(beanFactory, props);

		} catch (Exception e) {
			throw new InternalException(log, e);
		}
	}

}
