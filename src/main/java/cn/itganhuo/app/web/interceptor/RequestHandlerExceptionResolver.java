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
 * 所有错误被拦截到这里返回错误视图。
 * 
 * @author 深圳-小兴(504487927) 2014年9月6日
 * @since itganhuo1.0
 */
public class RequestHandlerExceptionResolver implements HandlerExceptionResolver {

  private static final Logger logger = LoggerFactory
      .getLogger(RequestHandlerExceptionResolver.class);

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException
   * (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
   * java.lang.Object, java.lang.Exception)
   */
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
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
