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
import cn.itganhuo.app.entity.Paging;
import cn.itganhuo.app.entity.Roles;

/**
 * <h2>测试RolesDao</h2>
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
public class TestRolesDao extends AbstractContextControllerTests {

	private RolesDao rolesDao = null;

	@Autowired
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	@Test
	public void testInsert() {
		Roles roles = new Roles();
		roles.setRole("系统管理员");
		roles.setDescription("具有最高权限");
		roles.setIsAvailable(1);
		Assert.isTrue(1 == rolesDao.insert(roles), "添加角色失败");
	}

	@Test
	public void testUpdateInfo() {
		Roles roles = new Roles();
		roles.setId(1);
		roles.setRole("系统管理员");
		roles.setDescription("具有最高权限");
		roles.setIsAvailable(1);
		Assert.isTrue(1 == rolesDao.insert(roles), "修改角色失败");
	}

	@Test
	public void testLoadById() {
		Assert.notNull(rolesDao.loadById(1), "根据ID查询不到角色信息");
	}

	@Test
	public void testGetRolesList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("roles", new Roles());
		Assert.notNull(rolesDao.getRolesList(condition), "根据条件查询不到角色信息");
	}

	@Test
	public void testCountRolesList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("roles", new Roles());
		Assert.isTrue(rolesDao.countRolesList(condition) == 1, "统计角色行数不正确");
	}
}
