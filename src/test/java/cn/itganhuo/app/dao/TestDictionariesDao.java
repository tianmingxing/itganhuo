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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.Dictionaries;
import cn.itganhuo.app.entity.Paging;
import cn.itganhuo.app.entity.User;

/**
 * <h2>测试MenuDao</h2>
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
public class TestDictionariesDao extends AbstractContextControllerTests {

	private DictionariesDao dictionariesDao = null;

	@Autowired
	public void setDictionariesDao(DictionariesDao dictionariesDao) {
		this.dictionariesDao = dictionariesDao;
	}

	@Test
	public void testInsert() {
		Dictionaries dictionaries = new Dictionaries();
		dictionaries.setPid(1);
		dictionaries.setAttrName("baomi");
		dictionaries.setAttrValue("保密");
		dictionaries.setDescription("性别属性组的一个属性");
		dictionaries.setSort(0);
		Assert.isTrue(dictionariesDao.insert(dictionaries) == 1, "保存数据字典失败");
	}

	@Test
	public void testUpdateInfo() {
		Dictionaries dictionaries = new Dictionaries();
		dictionaries.setId(3);
		dictionaries.setPid(1);
		dictionaries.setAttrName("baomi");
		dictionaries.setAttrValue("保密");
		dictionaries.setDescription("性别属性组的一个属性");
		dictionaries.setSort(0);
		Assert.isTrue(dictionariesDao.updateInfo(dictionaries) == 1, "修改数据字典失败");
	}

	@Test
	public void testDelete() {
		Assert.isTrue(dictionariesDao.delete(3), "根据ID删除数据字典失败");
	}

	@Test
	public void testLoadById() {
		Assert.notNull(dictionariesDao.loadById(1), "根据ID查询数据没有值");
	}

	@Test
	public void testGetDictionariesList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.notEmpty(dictionariesDao.getDictionariesList(condition),
				"根据条件查询数据字典信息没有值");
	}

	@Test
	public void testCountDictionariesList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.isTrue(dictionariesDao.countDictionariesList(condition) > 0,
				"统计数据行数没有值");
	}
}
