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
import cn.itganhuo.app.entity.Menu;
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
public class TestMenuDao extends AbstractContextControllerTests {

	private MenuDao menuDao = null;

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Test
	public void testFindMenuById() {
		Assert.notNull(menuDao.findMenuById(1), "根据ID查询数据没有值");
	}

	@Test
	public void testFindMenuByPid() {
		Assert.notNull(menuDao.findMenuByPid(0), "根据PID查询数据没有值");
	}

	@Test
	public void testGetMenuList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.notEmpty(menuDao.getMenuList(condition), "查询数据没有值");
	}

	@Test
	public void testCountMenuList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.isTrue(menuDao.countMenuList(condition) > 0, "没有统计到数据行");
	}

	@Test
	public void testAddMenu() {
		Menu menu = new Menu();
		menu.setPid(1);
		menu.setText("环境配置");
		menu.setState("open");
		menu.setChecked(true);
		menu.setIconCls("/root/a.jpg");
		menu.setUrl("www.baidu.com");
		menu.setSort(10);
		Assert.isTrue(menuDao.addMenu(menu) == 1, "添加菜单数据失败");
	}

	@Test
	public void testDelMenu() {
		Assert.isTrue(menuDao.delMenu(1), "删除菜单失败");
	}

	@Test
	public void testUpdateMenu() {
		Menu menu = new Menu();
		menu.setId(1);
		menu.setPid(1);
		menu.setText("环境配置");
		menu.setState("open");
		menu.setChecked(true);
		menu.setIconCls("/root/a.jpg");
		menu.setUrl("www.baidu.com");
		menu.setSort(10);
		Assert.isTrue(menuDao.updateMenu(menu), "根据ID修改菜单失败");
	}
}
