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
package cn.itganhuo.app.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.itganhuo.app.exception.InternalException;

/**
 * 针对HTTP常用操作做方法封装，具体可看每个方法上的注释。
 * <ol>
 * <li>在本类中添加新方法前仔细对比不要弄重复了</li>
 * <li>作为工具类建议每个方法都为static，这样可避免new太多的实例造成内存紧张。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年8月3日 上午11:51:19
 * @author 只要修改了这个类就到类注释上写上你的名字
 * @since itganhuo1.0
 */
public class HttpUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

  private HttpUtil() {
  }

  /**
   * 从request中按指定名称取出对应的值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:13:14
   * @since itganhuo1.0
   * @param httpsession
   * @param name
   *          要取的key
   * @return
   */
  public static String getString(HttpServletRequest request, String name) {
    if (StringUtil.hasText(name) && request.getParameter(name) != null)
      return request.getParameter(name).toString().trim();
    else
      return "";
  }

  /**
   * 从request中按指定名称取出对应的值
   * 
   * @author 深圳-小兴(504487927) 2014-8-11
   * @since itganhuo1.0
   * @param request
   * @param name
   * @return
   */
  public static int getInt(HttpServletRequest request, String name) {
    int i = 0;
    if (StringUtil.hasText(name) && request.getParameter(name) != null) {
      String t = request.getParameter(name).toString().trim();
      if (StringUtil.hasText(t)) {
        try {
          i = Integer.parseInt(t);
        } catch (NumberFormatException e) {
          i = 0;
        }
      }
    }
    return i;
  }

  /**
   * 从request中按指定名称取出对应的值，如果取不倒值可设置一个默认值返回来
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:09:16
   * @since itganhuo1.0
   * @param httpsession
   * @param name
   *          要取的名称
   * @param defaultStr
   *          默认字符
   * @return
   */
  public static String getString(HttpServletRequest request, String name, String defaultStr) {
    if (StringUtil.hasText(name) && request.getParameter(name) != null)
      return request.getParameter(name).toString().trim();
    else
      return defaultStr;
  }

  /**
   * 从Session中按指定名称取出对应的值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:13:14
   * @since itganhuo1.0
   * @param httpsession
   * @param name
   *          要取的key
   * @return
   */
  public static String getString(HttpSession httpsession, String name) {
    if (StringUtil.hasText(name) && httpsession.getAttribute(name) != null)
      return httpsession.getAttribute(name).toString().trim();
    else
      return "";
  }

  /**
   * 从Session中按指定名称取出对应的值，如果取不倒值可设置一个默认值返回来
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:09:16
   * @since itganhuo1.0
   * @param httpsession
   * @param name
   *          要取的名称
   * @param defaultStr
   *          默认字符
   * @return
   */
  public static String getString(HttpSession httpsession, String name, String defaultStr) {
    if (StringUtil.hasText(name) && httpsession.getAttribute(name) != null)
      return httpsession.getAttribute(name).toString().trim();
    else
      return defaultStr;
  }

  /**
   * 把一个字符写入到客户端cookie中，数据有效期可自由指定
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:14:54
   * @since itganhuo1.0
   * @param response
   * @param name
   *          cookie名称
   * @param str
   *          要保存的value
   * @param millisecond
   *          数据有效时间（毫秒值）
   * @throws ItGanHuoException
   *           自定义异常处理类
   */
  public static void setCookie(HttpServletResponse response, String name, String str,
      int millisecond) {
    try {
      Cookie cookie = new Cookie(name, URLEncoder.encode(str, "utf-8"));
      cookie.setMaxAge(millisecond);
      cookie.setPath("/");
      response.addCookie(cookie);
    } catch (UnsupportedEncodingException e) {
      throw new InternalException(logger, e);
    }
  }

  /**
   * 把一个字符写入到客户端cookie中，数据有效期仅在当前session范围内
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:20:25
   * @since itganhuo1.0
   * @param response
   * @param name
   *          cookie名称
   * @param str
   *          要保存的value
   * @param millisecond
   *          数据有效时间（毫秒值）
   * @throws ItGanHuoException
   *           自定义异常处理类
   */
  public static void setCookie(HttpServletResponse response, String name, String str) {
    try {
      Cookie cookie = new Cookie(name, URLEncoder.encode(str, "utf-8"));
      cookie.setPath("/");
      response.addCookie(cookie);
    } catch (UnsupportedEncodingException e) {
      throw new InternalException(logger, e);
    }
  }

  /**
   * 从客户端cookie中按名称取值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:26:10
   * @since itganhuo1.0
   * @param request
   * @param name
   *          cookie名称
   * @return 如果取到值就返回这个值，否则返回“”
   * @throws ItGanHuoException
   */
  public static String getCookie(HttpServletRequest request, String name) {
    try {
      Cookie acookie[] = request.getCookies();
      if (acookie == null)
        return null;
      for (int i = 0; i < acookie.length; i++)
        if (acookie[i] != null && acookie[i].getName().equals(name))
          return URLDecoder.decode(acookie[i].getValue(), "utf-8");
      return "";
    } catch (UnsupportedEncodingException e) {
      throw new InternalException(logger, e);
    }
  }

  /**
   * 把客户端cookie中的所有值都取出来
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:29:37
   * @since itganhuo1.0
   * @param request
   * @return 返回客户端cookie中的多个值，key和value中间用=号分隔
   * @throws ItGanHuoException
   */
  public static String[] getCookie(HttpServletRequest request) {
    try {
      Cookie acookie[] = request.getCookies();
      ArrayList<String> arraylist = new ArrayList<String>();
      if (acookie == null)
        return null;
      for (int i = 0; i < acookie.length; i++) {
        arraylist.add(new StringBuffer().append(acookie[i].getName()).append(" = ")
            .append(URLDecoder.decode(acookie[i].getValue(), "utf-8")).toString());
      }
      return (String[]) arraylist.toArray(new String[0]);
    } catch (UnsupportedEncodingException e) {
      throw new InternalException(logger, e);
    }
  }

  /**
   * 根据名称从客户端cookie中删除对应的值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午12:32:28
   * @since itganhuo1.0
   * @param response
   * @param request
   * @param name
   *          要删除cookie的名称
   * @throws ItGanHuoException
   */
  public static void delCookie(HttpServletResponse response, HttpServletRequest request, String name) {
    Cookie acookie[] = request.getCookies();
    if (acookie != null) {
      for (int i = 0; i < acookie.length; i++) {
        String s1 = acookie[i].getName();
        if (s1.equals(name)) {
          acookie[i].setMaxAge(0);
          response.addCookie(acookie[i]);
        }
      }
    }
  }

  /**
   * 把值放到Http请求中
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param request
   * @param obj
   *          要保存的值
   * @return
   */
  public static void setValue(HttpServletRequest request, String key, Object obj) {
    if (StringUtil.hasText(key) && obj != null) {
      request.setAttribute(key, obj);
    }
  }

  /**
   * 把值放到Http会话中
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param request
   * @param obj
   *          要保存的值
   * @return
   */
  public static void setValue(HttpSession session, String key, Object obj) {
    if (StringUtil.hasText(key) && obj != null) {
      session.setAttribute(key, obj);
    }
  }

  /**
   * 把数据放到Shiro会话范围
   * 
   * @author 深圳-小兴(504487927) 2014-9-17
   * @since itganhuo1.0
   * @param key
   * @param value
   */
  public static void setValue(String key, Object value) {
    Subject current_user = SecurityUtils.getSubject();
    current_user.getSession().setAttribute(key, value);
  }

  /**
   * 从Shiro会话范围取值
   * 
   * @author 深圳-小兴(504487927) 2014-9-17
   * @since itganhuo1.0
   * @param key
   * @return
   */
  public static Object getValue(String key) {
    Subject current_user = SecurityUtils.getSubject();
    return current_user.getSession().getAttribute(key);
  }

}
