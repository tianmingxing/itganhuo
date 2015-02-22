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
package cn.itganhuo.app.web.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

import cn.itganhuo.app.common.pool.KeywordPool;

/**
 * <h2>自定义请求封装类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>修改请求中的参数并将请求传给下一个过滤器，直至后台接受处理。</dd>
 * <dt>使用规范</dt>
 * <dd>
 * <ol>
 * <li>现在默认找到特殊字符了就替换成*号</li>
 * </ol>
 * </dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class KeyWordRequestWrapper extends HttpServletRequestWrapper {

	public KeyWordRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		super.getContextPath();
		Map<String, String[]> map = super.getParameterMap();
		if (!map.isEmpty()) {
			Set<String> keySet = map.keySet();
			Iterator<String> keyIt = keySet.iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				String[] values = map.get(key);
				for (int i = 0; i < values.length; i++) {
					map.get(key)[i] = this.replaceParam(values[i]);
				}
			}
		}
		return map;
	}

	@Override
	public String getParameter(String str) {
		String s = super.getParameter(str);
		return replaceParam(s);
	}

	@Override
	public String[] getParameterValues(String str) {
		String[] ss = super.getParameterValues(str);
		if (ss == null || ss.length == 0) {
			return ss;
		}
		String[] ss2 = new String[ss.length];
		for (int i = 0; i < ss2.length; i++) {
			ss2[i] = replaceParam(ss[i]);
		}
		return ss2;
	}

	/**
	 * 字符比对和替换
	 * 
	 * @param str
	 *            要检查的参数
	 * @return 返回修改后的参数
	 */
	private String replaceParam(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		String retStr = String.valueOf(str);
		for (String key : KeywordPool.getKeys()) {
			if (str.contains(key)) {
				retStr = retStr.replaceAll(key, "*");
			}
		}
		return retStr;
	}

}
