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
package cn.itganhuo.app.web.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.HttpUtil;
import cn.itganhuo.app.common.utils.StringUtil;

/**
 * <h2>表单重复提交请求拦截器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>防止表单重复提交</dd>
 * <dt>使用规范</dt>
 * <dd>
 * <ol>
 * <li>防止重复提交（根据页面标识选择性拦截） 如果你需要在表单提交时做重复提交拦截，那么请在表单里面隐藏 一个字段放token的值。 
 * <pre><input type="hidden" name="request_token" value="<%=HttpUtil.getString(session, "session_token")%>"></pre></li>
 * </ol>
 * </dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class FormRepeatSubmitHandlerInterceptor implements HandlerInterceptor {

	private static final Logger log = LogManager.getLogger(FormRepeatSubmitHandlerInterceptor.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("request_path => " + request.getRequestURI() + " | parameter => " + request.getQueryString());

		// 防止表单重复提交
		String request_token = request.getParameter(ConstantPool.REQUEST_TOKEN);
		// 1、判断页面上是否请求拦截表单重复提交
		if (StringUtil.hasText(request_token)) {
			// 2、如果会话中存在令牌并且与页面上一致，那就表示这是表单第一次提交，此时重新给会话中令牌赋值。
			String session_token = HttpUtil.getString(request.getSession(), ConstantPool.SESSION_TOKEN);
			if (StringUtil.hasText(session_token) && request_token.equals(session_token)) {
				HttpUtil.setValue(request.getSession(), ConstantPool.SESSION_TOKEN, UUID.randomUUID().toString());
			} else { // 3、两值不一致则进行拦截，造成此种情况可能是表单重复提交或是页面上没有正确取出会话中令牌的值。
				log.warn("Form Repeat Submit.");
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
