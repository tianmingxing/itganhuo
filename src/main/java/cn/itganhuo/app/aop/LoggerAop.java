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
 * <h2>切面管理类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>采用面向切面的方法给指定的类添加额外的功能，本类主要是用来记录运行状态日志。</dd>
 * <dt>使用规范</dt>
 * <dd>如果项目处于开发期间建议不要开启，因为这将直接导致不能够debug调试。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Aspect
@Component
public class LoggerAop {

	private static final Logger logger_service = LoggerFactory.getLogger("cn.itganhuo.app.service");
	private static final Logger logger_controller = LoggerFactory.getLogger("cn.itganhuo.app.web.controller");

	/**
	 * 声明控制层切入点
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 */
	@Pointcut("execution(* cn.itganhuo.app.web.controller.*.*(..))")
	public void pointcut1() {
	}

	/**
	 * 声明服务层切入点
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 */
	@Pointcut("execution(* cn.itganhuo.app.service.*.*(..))")
	public void pointcut2() {
	}

	/**
	 * 控制层之方法环绕通知，主要是对方法进出做系统运行状态日志记录。
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 */
	@AfterThrowing(value = "pointcut1()", throwing = "e")
	public void afterThrowingLog1(JoinPoint jp, Exception e) {
		if (logger_service.isErrorEnabled()) {
			logger_service.error(jp.toString(), e);
		}
	}

	/**
	 * 服务层之方法环绕通知，主要是对方法进出做系统运行状态日志记录。
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 */
	@AfterThrowing(value = "pointcut2()", throwing = "e")
	public void afterThrowingLog2(JoinPoint jp, Exception e) {
		if (logger_controller.isErrorEnabled()) {
			logger_controller.error(jp.toString(), e);
		}
	}

}
