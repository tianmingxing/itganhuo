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
package cn.itganhuo.app.common.pool;

/**
 * 自定义常量属性池
 * <ol>
 * <li>在此配置的常量属性值应该是静态不可修改的，如果不符合这两个条件那么请将到config.pro中配置你要的属性。</li>
 * <li>在程序中反复使用且二次变动性较小的常量属性可以在本类中配置，从而可以避免重复命名提高程序可维护性</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014-8-19
 * @since itganhuo1.0
 */
public class ConstantPool {

  // 键值对文件所在文件路径，值固定
  public static final String CONF_FILE_FOLDER = "/WEB-INF/classes/config.pro";

  // 记录存储在Shiro中session当前登录用户的信息，值固定
  public static final String USER_SHIRO_SESSION_ID = "user_shiro_session_id";

  // 用户信息临时存放名，值固定
  public static final String USER_TEMP_INFO = "user_temp_info";

  // 项目部署真实文件地址（绝对路径），项目启动时监听器会为它再次赋真实值。
  public static String REAL_PATH = "/home/tomcat/apache-tomcat-7.0.52/webapps/ROOT";
}
