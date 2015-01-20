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
import cn.itganhuo.app.entity.Label;

/**
 * <h2>测试Dao</h2>
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
public class TestLabelDao extends AbstractContextControllerTests {
	
	private LabelDao labelDao = null;

	@Autowired
	public void setLabelDao(LabelDao labelDao) {
		this.labelDao = labelDao;
	}
	
	@Test
	public void testInsert() {
		Label label = new Label();
		label.setUserId(1);
		label.setName("jQuery");
		label.setDescription("Jquery是继prototype之后又一个优秀的Javascript库。它是轻量级的js库 ，它兼容CSS3，还兼容各种浏览器（IE 6.0+, FF 1.5+, Safari 2.0+, Opera 9.0+），jQuery2.0及后续版本将不再支持IE6/7/8浏览器。");
		label.setPostDate("2015-1-11 22:03:30");
		Assert.isTrue(labelDao.insert(label) == 1, "保存标签信息失败");
	}
	
	@Test
	public void testDelById() {
		Assert.isTrue(labelDao.delById(1), "根据ID删除标签信息失败");
	}

	@Test
	public void testUpdateById() {
		Label label = new Label();
		label.setId(1);
		label.setUserId(1);
		label.setName("jQuery");
		label.setDescription("Jquery是继prototype之后又一个优秀的Javascript库。它是轻量级的js库 ，它兼容CSS3，还兼容各种浏览器（IE 6.0+, FF 1.5+, Safari 2.0+, Opera 9.0+），jQuery2.0及后续版本将不再支持IE6/7/8浏览器。");
		label.setPostDate("2015-1-11 22:03:30");
		Assert.isTrue(labelDao.updateById(label), "根据ID修改标签信息失败");
	}
	
	@Test
	public void testGetLabelByCondition() {
		Label label = new Label();
		label.setId(1);
		Assert.notEmpty(labelDao.getLabelByCondition(label), "查询数据没有值");
	}
	
	
}
