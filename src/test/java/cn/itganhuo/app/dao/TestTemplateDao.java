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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.Template;

/**
 * <h2>测试TemplateDao</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>测试的前提就是数据库中必须有测试数据，测试数据我已经备份成SQL脚本放到测试资源文件目录中。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestTemplateDao extends AbstractContextControllerTests {

	private TemplateDao templateDao = null;

	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	@Test
	public void testInsert() {
		Template template = new Template();
		template.setType(1);
		template.setEnName("hello");
		template.setChName("用户接收欢迎注册信息");
		template.setContent("#account#，欢迎你加入IT干货技术分享网。");
		template.setIsAvailable(1);
		Assert.isTrue(1 == templateDao.insert(template), "保存消息模板失败了");
	}

	@Test
	public void testLoadByName() {
		Assert.notNull(templateDao.loadByName("用户邮箱地址认证通知"), "根据模板名称查询不到对应的信息");
	}

	@Test
	public void testLoadById() {
		Assert.notNull(templateDao.loadById(1), "根据模板ID查询不到对应的信息");
	}

	@Test
	public void testLoadAll() {
		Assert.notNull(templateDao.loadAll(), "查询不到模板信息");
	}
}
