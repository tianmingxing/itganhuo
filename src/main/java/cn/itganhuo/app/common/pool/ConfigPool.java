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
package cn.itganhuo.app.common.pool;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.exception.InternalException;

/**
 * <h2>从config-pool.properties配置文件读取值</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ConfigPool {

	private static final Logger logger = LoggerFactory.getLogger(ConfigPool.class);

	private static String fname = ConstantPool.CONFIG_BEAN_FILE_PATH;
	private static Configuration config = null;

	private ConfigPool() {
	}

	/**
	 * <h2>从缓存的Configuration中取值，第一次取值时会先创建这个对象实例。</h2>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param fname
	 *            配置文件的路径
	 * @param key
	 *            键
	 * @return 返回值
	 */
	public static String getString(String key) {
		if (!StringUtil.hasText(fname)) {
			throw new InternalException(logger, "Configuration file path is incorrect.");
		}
		if (!StringUtil.hasText(key)) {
			throw new InternalException(logger, "The key you entered is not legitimate.");
		}
		try {
			if (config == null) {
				synchronized (ConfigPool.class) {
					if (config == null) {
						config = new PropertiesConfiguration(fname);
					}
				}
			}
		} catch (ConfigurationException e) {
			throw new InternalException(logger, "Profiles read exception.", e);
		}
		return config.getString(key.trim());
	}
}
