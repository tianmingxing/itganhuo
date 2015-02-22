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
package cn.itganhuo.app.common.pool;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.itganhuo.app.exception.PeripheralException;

/**
 * <h2>从config-pool.properties配置文件读取值</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ConfigPool {

	private static final Logger log = LogManager.getLogger(ConfigPool.class.getName());

	private static PropertiesConfiguration config = null;

	private ConfigPool() {
	}
	
	static {
		try {
			config = new PropertiesConfiguration(ConstantPool.CONFIG_POOL_FILE_NAME);
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			throw new PeripheralException(log, "Config-pool.properties file failed to initialize.", e);
		}
	}
	
	/**
	 * 往配置文件中保存键值对
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, String value) {
		try {
			config.setProperty(key, value);
			config.save();
		} catch (ConfigurationException e) {
			throw new PeripheralException(log, "Save the file fails.", e);
		}
	}

	/**
	 * 按键取值
	 * @param key
	 * @return 返回值
	 */
	public static String getString(String key) {
		return config.getString(key);
	}
}
