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
package cn.itganhuo.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将系统内部抛出到本类的异常记录到日志
 * 
 * @author 深圳-小兴(504487927) 2014年7月20日 下午1:11:52
 * @since itganhuo1.0
 */
public class InternalException extends RuntimeException {

  private static final long serialVersionUID = 7267973861644368332L;
  private static final Logger logger = LoggerFactory.getLogger(InternalException.class);

  public InternalException(String message) {
    super(message);
    if (logger.isErrorEnabled()) {
      logger.error(message);
    }
  }

  public InternalException(Logger logger, Exception e) {
    if (logger.isErrorEnabled()) {
      StackTraceElement astacktraceelement[] = e.getStackTrace();
      StringBuffer buffer = new StringBuffer();
      buffer.append(e.getMessage()).append("\r\n");
      for (int i = 0, len = astacktraceelement.length; i < len; i++) {
        buffer.append(astacktraceelement[i].toString()).append("\r\n");
      }
      logger.error(buffer.toString());
    }
  }

  public InternalException(Logger logger, String message) {
    if (logger.isErrorEnabled()) {
      logger.error(message);
    }
  }

  public InternalException(Logger logger, String message, Exception e) {
    if (logger.isErrorEnabled()) {
      StackTraceElement astacktraceelement[] = e.getStackTrace();
      StringBuffer buffer = new StringBuffer();
      buffer.append(e.getMessage()).append("\r\n");
      for (int i = 0, len = astacktraceelement.length; i < len; i++) {
        buffer.append(astacktraceelement[i].toString()).append("\r\n");
      }
      logger.error(" [".concat(message).concat("] ").concat(buffer.toString()));
    }
  }

}
