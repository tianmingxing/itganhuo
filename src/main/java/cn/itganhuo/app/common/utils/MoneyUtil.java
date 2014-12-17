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

import java.math.BigDecimal;

/**
 * <h2>针对金额高精度计算（普通计算会丢失精度），具体可看每个方法上的注释。</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>在本类中添加新方法前仔细对比不要弄重复了，作为工具类建议每个方法都为static，这样可避免new太多的实例造成
 * 内存紧张。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class MoneyUtil {

	private MoneyUtil() {
	}

	private static final int _$1 = 2; // 默认保留2位小数

	/**
	 * 将两个double数相加
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            前一个数
	 * @param d1
	 *            后一个数
	 * @return 相加得到的值
	 */
	public static double add(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相加（传入的字符串必须要能够造型成浮点型）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param s
	 *            前一个数
	 * @param s1
	 *            后一个数
	 * @return 相加得到的值
	 */
	public static double add(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相减
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            前一个数
	 * @param d1
	 *            后一个数
	 * @return 相减得到的值
	 */
	public static double sub(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相减（传入的字符串必须要能够造型成浮点型）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            前一个数
	 * @param d1
	 *            后一个数
	 * @return 相减得到的值
	 */
	public static double sub(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相乘
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            前一个数
	 * @param d1
	 *            后一个数
	 * @return 相乘得到的值
	 */
	public static double mul(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相乘（传入的字符串必须要能够造型成浮点型）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            前一个数
	 * @param d1
	 *            后一个数
	 * @return 相乘得到的值
	 */
	public static double mul(String s, String s1) {
		BigDecimal bigdecimal = new BigDecimal(s);
		BigDecimal bigdecimal1 = new BigDecimal(s1);
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	/**
	 * 将两个数字相除
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            被除数
	 * @param d1
	 *            除数
	 * @return 相除得到的值
	 */
	public static double div(double d, double d1) {
		return div(d, d1, _$1);
	}

	/**
	 * 将两个数字相除
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            被除数
	 * @param d1
	 *            除数
	 * @param i
	 *            保留几位小数
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
	 * 将两个数字相除（传入的字符串必须要能够造型成浮点型）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param s
	 *            被除数
	 * @param s1
	 *            除数
	 * @return 相除得到的值
	 */
	public static double div(String s, String s1) {
		return div(Double.parseDouble(s), Double.parseDouble(s1), _$1);
	}

	/**
	 * 将两个数字相除（传入的字符串必须要能够造型成浮点型）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param s
	 *            被除数
	 * @param s1
	 *            除数
	 * @param i
	 *            保留几位小数
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
	 * 将一个数按照指定的保留位四舍五入
	 * <ol>
	 * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            传进来的值
	 * @param i
	 *            保留位数
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
	 * 将一个数按照指定的保留位四舍五入
	 * <ol>
	 * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            传进来的值
	 * @param i
	 *            保留位数
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
	 * 将一个数按照指定的保留位四舍五入
	 * <ol>
	 * <li>比如你想将4.456保留二位小数并四舍五入，那通过这个方法的结果应该是4.46</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param d
	 *            传进来的值
	 * @param i
	 *            保留位数，分0-4档
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
