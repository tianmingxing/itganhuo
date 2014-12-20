/*
 * 1. This project consists of JAVA private school online learning community group Friends co-creator
 *  [QQ group 329232140].
 * 2. See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 3. The author does not guarantee the quality of the project and its stability, reliability, and security
 *  does not bear any responsibility.
 * 1、本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140]；
 * 2、作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams]；
 * 3、作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任。
 */
package cn.itganhuo.app.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

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
		if (logger.isDebugEnabled()) {
			logger.debug("Custom listener execution start.");
		}

		servletContext = servletContextEvent.getServletContext();
		this.getRealPath();

		if (logger.isDebugEnabled()) {
			logger.debug("End Custom listener execution.");
		}
	}

	/**
	 * 获取当前项目部署的真实路径（绝对地址）
	 */
	private void getRealPath() {
		String real_path = servletContext.getRealPath("/").replace('\\', '/');
		if (!real_path.endsWith("/")) {
			real_path = real_path.concat("/");
		}
		ConstantPool.REAL_PATH = real_path;
	}

}
