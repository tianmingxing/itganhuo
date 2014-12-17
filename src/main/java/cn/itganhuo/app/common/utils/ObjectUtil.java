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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 主要是判断对象时常用方法封装，具体可看每个方法上的注释。
 * <ol>
 * <li>在本类中添加新方法前仔细对比不要弄重复了</li>
 * <li>作为工具类建议每个方法都为static，这样可避免new太多的实例造成内存紧张。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年8月3日 上午11:51:19
 * @since itganhuo1.0
 */
public class ObjectUtil {

  private ObjectUtil() {
  }

  /**
   * 判断一个List是否有值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午7:08:38
   * @since itganhuo1.0
   * @param list
   *          传一个ArrayList参数
   * @return 如果有值返回true，否则返回false
   */
  public static boolean isNotEmpty(List<Object> list) {
    if (list != null && list.size() > 0)
      return true;
    else
      return false;
  }

  /**
   * 判断一个Set是否有值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午7:08:38
   * @since itganhuo1.0
   * @param list
   *          传一个HashSet参数
   * @return 如果有值返回true，否则返回false
   */
  public static boolean isNotEmpty(Set<Object> set) {
    if (set != null && set.size() > 0)
      return true;
    else
      return false;
  }

  /**
   * 判断一个Map是否有值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午7:08:38
   * @since itganhuo1.0
   * @param list
   *          传一个HashMap参数
   * @return 如果有值返回true，否则返回false
   */
  public static boolean isNotEmpty(Map<Object, Object> map) {
    if (map != null && map.size() > 0)
      return true;
    else
      return false;
  }

  /**
   * 判断一个数组是否有值
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午7:08:38
   * @since itganhuo1.0
   * @param list
   *          传一个数组参数
   * @return 如果有值返回true，否则返回false
   */
  public static boolean isNotEmpty(Object[] ary) {
    if (ary != null && ary.length > 0)
      return true;
    else
      return false;
  }

}
