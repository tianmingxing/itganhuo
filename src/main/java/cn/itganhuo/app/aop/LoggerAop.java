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
package cn.itganhuo.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用切面做日记记录
 * 
 * @author 深圳-小兴(504487927) 2014年7月16日 下午11:32:30
 * @since itganhuo1.0
 */
@Aspect
@Component
public class LoggerAop {

  private static final Logger logger_service = LoggerFactory.getLogger("cn.itganhuo.core.service");
  private static final Logger logger_controller = LoggerFactory
      .getLogger("cn.itganhuo.core.web.controller");

  /**
   * 声明控制层切入点
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @Pointcut("execution(* cn.itganhuo.core.web.controller.*.*(..))")
  public void pointcut1() {
  }

  /**
   * 声明服务层切入点
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @Pointcut("execution(* cn.itganhuo.core.service.*.*(..))")
  public void pointcut2() {
  }

  /**
   * 控制层之方法环绕通知，主要是对方法进去做系统运行期日志记录。
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @Around(value = "pointcut1()")
  public Object aroundLog1(ProceedingJoinPoint pjp) throws Throwable {
    if (logger_service.isDebugEnabled()) {
      logger_service.debug("==> ".concat(pjp.toString()));
    }

    Object retVal = pjp.proceed();

    if (logger_service.isDebugEnabled()) {
      logger_service.debug("<== ".concat(pjp.toString()));
    }
    return retVal;
  }

  /**
   * 控制层之异常通知，主要是捕捉方法异常并将其写入文件和打印到控制台。
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @AfterThrowing(value = "pointcut1()", throwing = "e")
  public void afterThrowingLog1(JoinPoint jp, Exception e) {
    if (logger_service.isErrorEnabled()) {
      StackTraceElement astacktraceelement[] = e.getStackTrace();
      StringBuffer str_buffer = new StringBuffer();
      str_buffer.append(e.getMessage()).append("\r\n");
      for (int i = 0, len = astacktraceelement.length; i < len; i++) {
        str_buffer.append(astacktraceelement[i].toString()).append("\r\n");
      }
      logger_service.error(" [".concat(jp.toString()).concat("] ").concat(str_buffer.toString()));
    }
  }

  /**
   * 服务层之方法环绕通知，主要是对方法进去做系统运行期日志记录。
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @Around(value = "pointcut2()")
  public Object aroundLog2(ProceedingJoinPoint pjp) throws Throwable {
    if (logger_controller.isDebugEnabled()) {
      logger_controller.debug("==> ".concat(pjp.toString()));
    }
    Object retVal = pjp.proceed();
    if (logger_controller.isDebugEnabled()) {
      logger_controller.debug("<== ".concat(pjp.toString()));
    }
    return retVal;
  }

  /**
   * 服务层之异常通知，主要是捕捉方法异常并将其写入文件和打印到控制台。
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 上午11:23:02
   * @since itganhuo1.0
   */
  @AfterThrowing(value = "pointcut2()", throwing = "e")
  public void afterThrowingLog2(JoinPoint jp, Exception e) {
    if (logger_controller.isErrorEnabled()) {
      StackTraceElement astacktraceelement[] = e.getStackTrace();
      StringBuffer str_buffer = new StringBuffer();
      str_buffer.append(e.getMessage()).append("\r\n");
      for (int i = 0, len = astacktraceelement.length; i < len; i++) {
        str_buffer.append(astacktraceelement[i].toString()).append("\r\n");
      }
      logger_controller
          .error(" [".concat(jp.toString()).concat("] ").concat(str_buffer.toString()));
    }
  }

}
