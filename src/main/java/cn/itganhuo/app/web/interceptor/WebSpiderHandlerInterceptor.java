package cn.itganhuo.app.web.interceptor;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h2>防止网络蜘蛛偷盗干货文章的请求拦截器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>在一定程度上对蜘蛛爬行造成防碍，实现思路：
 * <ol><li>如果在3秒钟内被监测的路径有重复访问则认为是非正常访问，因为正常人浏览文章时不会单击这么频繁；</li>
 * <li>如果在会话期内同一用户累计发起了超过50个请求则认为非正常访问，如果他超过了会话期(30m)再访问计数重新开始。</li></ol></dd>
 * <dt>使用规范</dt>
 * <dd>在配置文件中已经注册，目前只拦截进入文章详情页的请求。</dd>
 * </dl>
 *
 * @author 深圳-小兴
 * @version 0.0.1-SNAPSHOT
 */
public class WebSpiderHandlerInterceptor implements HandlerInterceptor {

    private final static Logger log = LogManager.getLogger(WebSpiderHandlerInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        if (null == session.getAttribute(ConstantPool.LAST_REQUEST_TIME)) {
            session.setAttribute(ConstantPool.LAST_REQUEST_TIME, new Date());
            session.setAttribute(ConstantPool.VISITS_DURING_SESSION, 1);
        } else {
            Date currentTime = new Date();
            Date lastRequestTime = (Date) session.getAttribute(ConstantPool.LAST_REQUEST_TIME);
            int visitsDuringSession = (int) session.getAttribute(ConstantPool.VISITS_DURING_SESSION);
            httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
            log.debug("lastRequestTime={} - currentTime={}, visitsDuringSession={}.", lastRequestTime.toString(),
                    currentTime.toString(), visitsDuringSession);
            if (currentTime.getTime() - lastRequestTime.getTime() <= 3000) {
                PrintWriter out = httpServletResponse.getWriter();
                out.print(ConfigPool.getString("respMsg.FoundSiteReptileTips"));
                out.flush();
                out.close();
                return false;
            } else if (visitsDuringSession >= 50) {
                PrintWriter out = httpServletResponse.getWriter();
                out.print(ConfigPool.getString("respMsg.MoreSpecifiedNumberTimesDuringSessionRequest"));
                out.flush();
                out.close();
                return false;
            } else {
                session.setAttribute(ConstantPool.LAST_REQUEST_TIME, currentTime);
                session.setAttribute(ConstantPool.VISITS_DURING_SESSION, ++visitsDuringSession);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
