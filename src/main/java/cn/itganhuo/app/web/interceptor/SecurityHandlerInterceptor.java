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

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.utils.HttpUtil;
import cn.itganhuo.app.common.utils.StringUtil;

/**
 * 拦截器类
 * <ol>
 * <li>防御重复提交（根据页面标识选择性拦截）</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年7月20日 下午6:00:03
 * @since itganhuo1.0
 */
public class SecurityHandlerInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(SecurityHandlerInterceptor.class);

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
   * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (logger.isInfoEnabled()) {
      logger.info("request_path => " + request.getRequestURI() + " | parameter => "
          + request.getQueryString());
    }

    /*
     * 1、防御重复提交（根据页面标识选择性拦截） 如果你需要在表单提交时做重复提交拦截，那么请在表单里面隐藏一个字段放token的值。 （<input type="hidden"
     * name="request_token" value="<%=HttpUtil.getString(session, "session_token")%>">）。
     */
    String request_token = request.getParameter("request_token");
    String session_token = HttpUtil.getString(request.getSession(), "session_token");
    if (StringUtil.hasText(session_token)) {
      if (!StringUtil.hasText(request_token) || request_token.equals(session_token)) {
        HttpUtil.setValue(request.getSession(), "session_token", UUID.randomUUID().toString());
      } else {
        return false;
      }
    } else {
      HttpUtil.setValue(request.getSession(), "session_token", UUID.randomUUID().toString());
    }

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
   * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * org.springframework.web.servlet.ModelAndView)
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
   * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * java.lang.Exception)
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
  }

}
