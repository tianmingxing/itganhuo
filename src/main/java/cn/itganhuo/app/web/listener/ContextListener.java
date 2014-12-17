/*
 * Solemnly declare(2014-11-11):
 * 1 This project is a collective creation of its copyrighted Java private school online learning community (QQ group number 329 232 140) of all, is prohibited without the author's permission for commercial profit without permission;
 * 2 See the list of IT ganhuo sharing network http://www.itganhuo.cn/teams;
 * 3, the author does not guarantee the quality of the project and its stability, reliability, and security does not assume any responsibility if you get the source code for this project in some way, all the consequences of the subsequent occurrence of ego nothing to do with this group and author;
 * 4 mx.tian@qq.com have any questions, please contact us.
 *
 * 郑重声明(2014-11-11)：
 * 1、本项目属集体创作其版权归Java私塾在线学习社区（QQ群号329232140）作者所有，禁止未经作者授权擅自用于商业盈利；
 * 2、作者名单详见IT干货技术分享网http://www.itganhuo.cn/teams；
 * 3、作者不保证项目质量并对其稳定性、可靠性和安全性不承担任何责任，如果你通过某些途径获取本项目源代码，其后发生的一切后果自负与本群及作者无关；
 * 4、有任何问题请与我们联系mx.tian@qq.com。
 */
package cn.itganhuo.app.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;

/**
 * 自定义监听器
 * 
 * @author 深圳-小兴(504487927) 2014-8-19
 * @since itganhuo1.0
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
   * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet
   * .ServletContextEvent)
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
   * 获取当前项目部署的真实文件路径（绝对地址）
   */
  private void getRealPath() {
    String real_path = servletContext.getRealPath("/").replace('\\', '/');
    if (!real_path.endsWith("/")) {
      real_path = real_path.concat("/");
    }
    ConstantPool.REAL_PATH = real_path;
    ConfigPool.readerFile();
  }

}
