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
package cn.itganhuo.app.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.itganhuo.app.exception.InternalException;

/**
 * <h2>针对HTTP常用操作做方法封装，具体可看每个方法上的注释。</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>在本类中添加新方法前仔细对比不要弄重复了，作为工具类建议每个方法都为static，这样可避免new太多的实例造成 内存紧张。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class HttpUtil {

	private static final Logger logger = LogManager.getLogger(HttpUtil.class);

	private HttpUtil() {
	}

	/**
	 * 从request中按指定名称取出对应的值
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param httpsession
	 * @param name
	 *            要取的key
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param httpsession
	 * @param name
	 *            要取的名称
	 * @param defaultStr
	 *            默认字符
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param httpsession
	 * @param name
	 *            要取的key
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param httpsession
	 * @param name
	 *            要取的名称
	 * @param defaultStr
	 *            默认字符
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param response
	 * @param name
	 *            cookie名称
	 * @param str
	 *            要保存的value
	 * @param millisecond
	 *            数据有效时间（毫秒值）
	 * @throws ItGanHuoException
	 *             自定义异常处理类
	 */
	public static void setCookie(HttpServletResponse response, String name, String str, int millisecond) {
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param response
	 * @param name
	 *            cookie名称
	 * @param str
	 *            要保存的value
	 * @param millisecond
	 *            数据有效时间（毫秒值）
	 * @throws ItGanHuoException
	 *             自定义异常处理类
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param request
	 * @param name
	 *            cookie名称
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
				arraylist.add(new StringBuffer().append(acookie[i].getName()).append(" = ").append(URLDecoder.decode(acookie[i].getValue(), "utf-8"))
						.toString());
			}
			return (String[]) arraylist.toArray(new String[0]);
		} catch (UnsupportedEncodingException e) {
			throw new InternalException(logger, e);
		}
	}

	/**
	 * 根据名称从客户端cookie中删除对应的值
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param response
	 * @param request
	 * @param name
	 *            要删除cookie的名称
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param request
	 * @param obj
	 *            要保存的值
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param request
	 * @param obj
	 *            要保存的值
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param key
	 * @return 返回会话中的值
	 */
	public static Object getValue(String key) {
		Subject current_user = SecurityUtils.getSubject();
		return current_user.getSession().getAttribute(key);
	}

}
