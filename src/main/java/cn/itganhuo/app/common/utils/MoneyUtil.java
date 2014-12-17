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

import java.math.BigDecimal;

/**
 * 针对金额高精度计算（普通计算会丢失精度），具体可看每个方法上的注释。
 * 
 * <ol>
 * <li>在本类中添加新方法前仔细对比不要弄重复了</li>
 * <li>作为工具类建议每个方法都为static，这样可避免new太多的实例造成内存紧张。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年8月3日 上午11:51:19
 * @author 只要修改了这个类就到类注释上写上你的名字
 * @since itganhuo1.0
 */
public class MoneyUtil {

  private MoneyUtil() {
  }

  private static final int _$1 = 2; // 默认保留2位小数

  /**
   * <p>
   * 将两个double数相加
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param d
   *          前一个数
   * @param d1
   *          后一个数
   * @return 相加得到的值
   */
  public static double add(double d, double d1) {
    BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
    BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
    return bigdecimal.add(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相加（传入的字符串必须要能够造型成浮点型）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param s
   *          前一个数
   * @param s1
   *          后一个数
   * @return 相加得到的值
   */
  public static double add(String s, String s1) {
    BigDecimal bigdecimal = new BigDecimal(s);
    BigDecimal bigdecimal1 = new BigDecimal(s1);
    return bigdecimal.add(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相减
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param d
   *          前一个数
   * @param d1
   *          后一个数
   * @return 相减得到的值
   */
  public static double sub(double d, double d1) {
    BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
    BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
    return bigdecimal.subtract(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相减（传入的字符串必须要能够造型成浮点型）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param d
   *          前一个数
   * @param d1
   *          后一个数
   * @return 相减得到的值
   */
  public static double sub(String s, String s1) {
    BigDecimal bigdecimal = new BigDecimal(s);
    BigDecimal bigdecimal1 = new BigDecimal(s1);
    return bigdecimal.subtract(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相乘
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param d
   *          前一个数
   * @param d1
   *          后一个数
   * @return 相乘得到的值
   */
  public static double mul(double d, double d1) {
    BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
    BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
    return bigdecimal.multiply(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相乘（传入的字符串必须要能够造型成浮点型）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:15:37
   * @since itganhuo1.0
   * @param d
   *          前一个数
   * @param d1
   *          后一个数
   * @return 相乘得到的值
   */
  public static double mul(String s, String s1) {
    BigDecimal bigdecimal = new BigDecimal(s);
    BigDecimal bigdecimal1 = new BigDecimal(s1);
    return bigdecimal.multiply(bigdecimal1).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相除
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:20:51
   * @since itganhuo1.0
   * @param d
   *          被除数
   * @param d1
   *          除数
   * @return 相除得到的值
   */
  public static double div(double d, double d1) {
    return div(d, d1, _$1);
  }

  /**
   * <p>
   * 将两个数字相除
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:20:51
   * @since itganhuo1.0
   * @param d
   *          被除数
   * @param d1
   *          除数
   * @param i
   *          保留几位小数
   * @return 相除得到的值
   */
  public static double div(double d, double d1, int i) {
    if (i < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
    BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
    return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
  }

  /**
   * <p>
   * 将两个数字相除（传入的字符串必须要能够造型成浮点型）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:20:51
   * @since itganhuo1.0
   * @param s
   *          被除数
   * @param s1
   *          除数
   * @return 相除得到的值
   */
  public static double div(String s, String s1) {
    return div(Double.parseDouble(s), Double.parseDouble(s1), _$1);
  }

  /**
   * <p>
   * 将两个数字相除（传入的字符串必须要能够造型成浮点型）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:20:51
   * @since itganhuo1.0
   * @param s
   *          被除数
   * @param s1
   *          除数
   * @param i
   *          保留几位小数
   * @return 相除得到的值
   */
  public static double div(String s, String s1, int i) {
    if (i < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal bigdecimal = new BigDecimal(s);
    BigDecimal bigdecimal1 = new BigDecimal(s1);
    return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
  }

  /**
   * <p>
   * 将一个数按照指定的保留位四舍五入
   * </p>
   * <ol>
   * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:54:08
   * @since itganhuo1.0
   * @param d
   *          传进来的值
   * @param i
   *          保留位数
   * @return
   */
  public static double round(double d, int i) {
    if (i < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
    BigDecimal bigdecimal1 = new BigDecimal("1");
    return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
  }

  /**
   * <p>
   * 将一个数按照指定的保留位四舍五入
   * </p>
   * <ol>
   * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:54:08
   * @since itganhuo1.0
   * @param d
   *          传进来的值
   * @param i
   *          保留位数
   * @return
   */
  public static double round(String s, int i) {
    if (i < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal bigdecimal = new BigDecimal(s);
    BigDecimal bigdecimal1 = new BigDecimal("1");
    return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
  }

  /**
   * <p>
   * 将一个数按照指定的保留位四舍五入
   * </p>
   * <ol>
   * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014年8月3日 下午6:54:08
   * @since itganhuo1.0
   * @param d
   *          传进来的值
   * @param i
   *          保留位数，分0-4档
   * @return 返回的是四舍五入格式化后的字符串表现形式
   */
  public static String roundFormat(double d, int i) {
    String num = null;
    if (i == 1) {
      num = "0.0";
    }
    if (i == 2) {
      num = "0.00";
    }
    if (i == 3) {
      num = "0.000";
    }
    if (i == 4) {
      num = "0.0000";
    }
    if (i == 0) {
      num = "0";
    }
    java.text.DecimalFormat df = new java.text.DecimalFormat(num);
    return df.format(d);
  }
}
