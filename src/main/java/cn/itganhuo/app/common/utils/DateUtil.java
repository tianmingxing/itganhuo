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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 封装了对日期处理、转换、比较等操作，具体可看每个方法上的注释。
 * 
 * <ol>
 * <li>在本类中添加新方法前仔细对比不要弄重复了</li>
 * <li>作为工具类建议每个方法都为static，这样可避免new太多的实例造成内存紧张。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年8月3日 下午5:52:00
 * @author 只要修改了这个类就到类注释上写上你的名字
 * @since itganhuo1.0
 */
public class DateUtil {

  /**
   * 获取当前时间的毫秒值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午5:59:40
   * @since itganhuo1.0
   * @return 返回long型时间毫秒值
   */
  public static long getNowDateTime() {
    return System.currentTimeMillis();
  }

  /**
   * 将当前日期格式化指定形式的字符串
   * <ol>
   * <li>如果传进来的字符为空则默认使用“年-月-日 时:分:秒”这种形式来格式化</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:00:18
   * @since itganhuo1.0
   * @param formatStr
   *          格式化字符
   * @return 将时间日期格式化后的字符串表现形式
   */
  public static String getNowDateTimeStr(String formatStr) {
    if (!StringUtil.hasText(formatStr)) {
      formatStr = "yyyy-MM-dd HH:mm:ss";
    }
    return new SimpleDateFormat(formatStr).format(DateUtil.getNowDateTime());
  }

  /**
   * 获取N月前或后的时间（与当前时间相比）
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param month
   *          月数可正可负
   * @return
   * @throws Exception
   */
  public static Date getAfterOrBeforMonth(int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, month);
    return calendar.getTime();
  }

  /**
   * 获取N天前或后的时间（与当前时间相比）
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param day
   *          天数可正可负
   * @return
   * @throws Exception
   */
  public static Date getAfterOrBeforDay(int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, day);
    return calendar.getTime();
  }

  /**
   * 获取N小时前或后的时间（与当前时间相比）
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param hour
   *          小时可正可负
   * @return
   * @throws Exception
   */
  public static Date getAfterOrBeforHour(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, hour);
    return calendar.getTime();
  }

  /**
   * 获取当前所在年
   * 
   * @author 深圳-小兴(504487927) 2014-9-26
   * @since itganhuo1.0
   * @return
   */
  public static String getNowYear() {
    return DateUtil.getNowDateTimeStr("yyyy");
  }

  public static void main(String[] args) throws Exception {
    // System.out.println(getAfterOrBeforMonth(- 4));
  }

}
