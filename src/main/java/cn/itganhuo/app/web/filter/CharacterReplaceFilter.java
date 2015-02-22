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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <h2>特殊字符请求过滤器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>1、过滤替换特殊敏感字符；2、防御SQL注入攻击。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class CharacterReplaceFilter implements Filter {
	
	private final static Logger log = LogManager.getLogger(CharacterReplaceFilter.class.getName());
  
	@Override
	public void destroy() {
		log.debug("session destroy.");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		// 过滤特殊字符
        final String method = request.getMethod();
        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
        	log.debug("next CharacterReplaceFilter=>");
            KeyWordRequestWrapper wrapper = new KeyWordRequestWrapper(request);
            filterChain.doFilter(wrapper, response);
        } else {
        	filterChain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("SecurityHandlerFilter init.");
	}

}
