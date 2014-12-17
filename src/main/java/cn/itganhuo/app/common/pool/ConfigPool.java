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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.itganhuo.app.common.utils.FileUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.exception.InternalException;

/**
 * 读取config.pro中的键值对并将其保存到map中。
 * 
 * @author 深圳-小兴(504487927) 2014-8-20
 * @since itganhuo1.0
 */
public class ConfigPool {

  private static final Logger logger = LoggerFactory.getLogger(ConfigPool.class);

  /**
   * 数据存放池
   */
  private static Map<Object, Object> map = new HashMap<Object, Object>();

  /**
   * 开始从指定配置文件中读取数据
   * <ol>
   * <li>逐行读取并用等号作为分隔符将键值存放到数据存放池中</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-8-20
   */
  public static void readerFile() {
    if (logger.isDebugEnabled()) {
      logger.debug("Start reading config.pro file.");
    }
    try {
      String[] as = FileUtil.readFile2Array(ConstantPool.REAL_PATH + ConstantPool.CONF_FILE_FOLDER,
          "UTF-8");
      for (int i = 0; i < as.length; i++) {
        if (StringUtil.hasText(as[i]) && as[i].indexOf("#") != 0) {
          String as1[] = as[i].split("=");
          map.put(as1[0].trim(), as1[1].trim());
        }
      }
    } catch (Exception e) {
      throw new InternalException(logger, e);
    }
    if (logger.isDebugEnabled()) {
      logger.debug("Read config.pro file end.");
    }
  }

  /**
   * 从数据池中根据名字取值
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-15
   * @param s
   *          名称
   * @param s1
   *          默认值
   * @param b
   *          如果为true则将取出值进行unicode编码，否则取出原字符
   * @return 返回键对应的值
   */
  public static String getString(String s, String s1, boolean b) {
    if (map != null && StringUtil.hasText(s)) {
      if (b) {
        return StringUtil.toEncodedUnicode((String) map.get(s), false);
      } else {
        return (String) map.get(s);
      }
    }
    return s1;
  }

  /**
   * 从数据池中根据名字取值，并将取出的值转换成unicoded编码的格式。
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-15
   * @param s
   *          名称
   * @param s1
   *          默认值
   * @return
   */
  public static String getString(String s, String s1) {
    return getString(s, s1, true);
  }

  /**
   * 从数据池中根据名字取值，并将取出的值转换成unicoded编码的格式。<br>
   * 注意：并非任何情况下都需要将值进行unicoded编码，只有在前端页面获取响应回来的信息中文乱码时才比较适合。<br>
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-15
   * @param s
   *          名称
   * @return 如果名字找不到则返回空字符串
   */
  public static String getString(String s) {
    return getString(s, "", true);
  }

  /**
   * 从数据池中根据名字取值（不转码）<br>
   * 注意：如果在不需要将字符串转码的情况下优先用这个方法。<br>
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-15
   * @param s
   *          名称
   * @return 如果名字找不到则返回空字符串
   */
  public static String getString2(String s) {
    return getString(s, "", false);
  }

  /**
   * 销毁数据池
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-15
   */
  public static void destroy() {
    if (logger.isDebugEnabled()) {
      logger.debug("Clear map data.");
    }
    map.clear();
    map = null;
  }

}
