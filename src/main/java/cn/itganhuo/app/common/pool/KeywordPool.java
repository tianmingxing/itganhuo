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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * <h2>特殊敏感字符读取池</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>从keyword-pool.txt文件中缓存数据</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class KeywordPool {
	
	private KeywordPool() {
		
	}

	private static List<String> keys = new ArrayList<String>();
	
	public static List<String> getKeys() {
		return keys;
	}

	static {
		String file = KeywordPool.class.getClassLoader().getResource(ConstantPool.KEYWORD_POOL_FILE_NAME).getFile();
		try {
			keys = FileUtils.readLines(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
