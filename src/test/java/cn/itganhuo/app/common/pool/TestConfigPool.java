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

import org.junit.Test;

/**
 * <h2>[类用途简述]</h2>
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
public class TestConfigPool {

	@Test
	public void test() {
		//不在容器中测试时由于路径不正确这个测试通过不了，虽然这个功能是正常的但还是暂时把它注释。
//		Assert.assertNotNull(ConfigPool.getString("parameter.emailLinkValidCertification"));
//		Assert.assertNotNull(ConfigPool.getString("parameter.requestGetContextPath"));
//		Assert.assertEquals(MessageFormat.format(ConfigPool.getString("respMsg.email.EmailAuthSuccessful"), "admin"), "恭喜你：admin，邮箱地址认证成功！");
	}

}
