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
package cn.itganhuo.app.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;

/**
 * <h2>自定义监听器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>在服务启动时会执行本类中的方法，用来完成一些参数初始化的工作。</dd>
 * <dt>使用规范</dt>
 * <dd></dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ContextListener implements ServletContextListener {

	private static final Logger log = LogManager.getLogger(ContextListener.class.getName());

	private ServletContext servletContext;

	public ContextListener() {
		servletContext = null;
	}

	private void log(String s) {
		if (servletContext != null) {
			servletContext.log(new StringBuffer().append("ContextListener: ").append(s).toString());
		} else {
			System.out.println(new StringBuffer().append("ContextListener: ").append(s).toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet. ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		log("ItGanHuo Context Destroyed.");
		servletContext = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.debug("Custom listener execution start.");

		servletContext = servletContextEvent.getServletContext();
		this.getRealPath();

		log.debug("End Custom listener execution.");
	}

	/**
	 * 获取当前项目部署的真实路径（绝对地址）
	 */
	private void getRealPath() {
		String real_path = servletContext.getRealPath("/").replace('\\', '/');
		if (!real_path.endsWith("/")) {
			real_path = real_path.concat("/");
		}
		ConfigPool.setProperty(ConstantPool.REAL_PATH, real_path);
	}

}
