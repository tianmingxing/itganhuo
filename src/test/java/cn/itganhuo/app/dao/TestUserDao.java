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
package cn.itganhuo.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.Paging;
import cn.itganhuo.app.entity.User;

/**
 * <h2>测试UserDao</h2>
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
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestUserDao extends AbstractContextControllerTests {

	private UserDao dao = null;
	
	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	@Test
	public void testGetUserList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.assertTrue(dao.countUserList(condition) > 0);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setAccount("superadmin");
		user.setPassword("8888");
		user.setSalt("8908");
		user.setIsLock(1);
		user.setNickname("超级管理员");
		user.setSex(2);
		user.setEmail("tianmx@mas-china.com");
		user.setQq(168303836);
		user.setPhone("18612345678");
		user.setTel("0755 12345678");
		user.setPostDate("2014-12-18 17:36:59");
		user.setType(999);
		user.setLastLoginIp("127.0.0.1");
		user.setLastLoginDate("2014-12-18 17:40:10");
		user.setIsValidateEmail(1);
		user.setEmailValidateCode("JOHKHIH879HGU899HKHJLKJ");
		user.setEmailValidateDate("2014-12-18 17:40:12");
		dao.insert(user);
	}

}
