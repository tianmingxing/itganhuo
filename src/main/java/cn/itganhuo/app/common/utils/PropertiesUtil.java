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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <h2>读取指定路径下的Properties文件</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
public class PropertiesUtil {
	private static PropertiesUtil util = null;
	private static Map<String, Properties> props = null;

	private PropertiesUtil() {
	}

	/**
	 * <h2>对外的构造方法</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @return 如果实例存在则直接返回缓存了的实例
	 */
	public static PropertiesUtil getInstance() {
		if (util == null) {
			synchronized (PropertiesUtil.class) {
				if (util == null) {
					props = new HashMap<String, Properties>();
					util = new PropertiesUtil();
				}
			}
		}
		return util;
	}

	/**
	 * <h2>[功能用途简述]</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param name properties文件名称，不用带后缀。
	 * @return 返回Properties对象
	 */
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
