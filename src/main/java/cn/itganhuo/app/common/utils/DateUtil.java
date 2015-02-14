/*
 * Copyright 2014-2024 the https://github.com/xiaoxing598/itganhuo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * This project consists of JAVA private school online learning community group Friends co-creator [QQ group 329232140].
 * 本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140];
 * See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams];
 * The author does not guarantee the quality of the project and its stability, reliability, and security does not bear any responsibility.
 * 作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任.
 */
package cn.itganhuo.app.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <h2>封装了对日期处理、转换、比较等操作，具体可看每个方法上的注释。</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>在本类中添加新方法前仔细对比不要弄重复了，作为工具类建议每个方法都为static，这样可避免new太多的实例造成
       内存紧张。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class DateUtil {

	/**
	 * 获取当前时间的毫秒值
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param formatStr
	 *            格式化字符
	 * @return 将时间日期格式化后的字符串表现形式
	 */
	public static String getNowDateTimeStr(String formatStr) {
		if (formatStr == null || !StringUtil.hasText(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(formatStr).format(DateUtil.getNowDateTime());
	}

	/**
	 * 获取N月前或后的时间（与当前时间相比）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param month
	 *            月数可正可负
	 * @return
	 */
	public static Date getAfterOrBeforMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 获取N天前或后的时间（与当前时间相比）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param day
	 *            天数可正可负
	 * @return
	 */
	public static Date getAfterOrBeforDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 获取N小时前或后的时间（与当前时间相比）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param hour
	 *            小时可正可负
	 * @return
	 */
	public static Date getAfterOrBeforHour(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

}
