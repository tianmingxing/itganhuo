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
package cn.itganhuo.app.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h2>所有错误被拦截到这里返回错误视图。</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>为提供给使用者更好的体验，请求错误的页面都会跳转到中间页。发生的错误信息会隐藏输出到页面，查看源码可看到
 * 错误内容。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.2-SNAPSHOT
 * @author 深圳-小兴
 */
public class RequestHandlerExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(RequestHandlerExceptionResolver.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (logger.isErrorEnabled()) {
			logger.error("Page request was blocked into the wrong.", ex);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StackTraceElement astacktraceelement[] = ex.getStackTrace();
		StringBuffer str_buffer = new StringBuffer();
		str_buffer.append(ex.getMessage()).append("\r\n");
		for (int i = 0, len = astacktraceelement.length; i < len; i++) {
			str_buffer.append(astacktraceelement[i].toString()).append("\r\n");
		}
		map.put("errorMsg", str_buffer.toString());
		return new ModelAndView("error/error", map);
	}

}
