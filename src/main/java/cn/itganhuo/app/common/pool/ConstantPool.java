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

/**
 * <h2>自定义常量属性池</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>本系统中所用到的常量值都统一在这里进行配置。</dd>
 * <dt>使用规范</dt>
 * <dd>在此配置的常量属性值应该是静态不可修改的，如果不符合这两个条件那么请将到resources.properties中配置你要的
 * 属性。在程序中反复使用且二次变动性较小的常量属性可以在本类中配置，从而可以避免重复命名提高程序可维护性。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ConstantPool {

	/**
	 * 用户信息在登录时会存储在Shiro会话中，这个常量是用来作为KEY的一个标识。
	 */
	public static final String USER_SHIRO_SESSION_ID = "user_shiro_session_id";

	/**
	 * 用户信息临时存放到会话中，这个常量是用来作为KEY的一个标识。
	 */
	public static final String USER_TEMP_INFO = "user_temp_info";
	
	/**
	 * 系统配置文件所在相对路径
	 */
	public static final String CONFIG_BEAN_FILE_PATH = "config-pool.properties";
	
	/**
	 * 当前项目部署的真实路径（绝对地址）
	 */
	public static String REAL_PATH = null;

}
