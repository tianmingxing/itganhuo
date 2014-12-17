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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 根据properties文件获得对应的信息
 * 
 * @author 朱塞佩
 * 
 */
public class PropertiesUtil {
  private static PropertiesUtil util = null;
  private static Map<String, Properties> props = null;

  private PropertiesUtil() {
  }

  public static PropertiesUtil getInstance() {
    if (util == null) {
      props = new HashMap<String, Properties>();
      util = new PropertiesUtil();
    }
    return util;
  }

  public Properties load(String name) {
    if (props.get(name) != null) {
      return props.get(name);
    } else {
      Properties properties = new Properties();
      try {
        properties.load(PropertiesUtil.class.getResourceAsStream("/" + name + ".properties"));
        properties.put(name, properties);
        return properties;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
