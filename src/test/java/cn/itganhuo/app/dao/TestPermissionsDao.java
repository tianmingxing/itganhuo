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
import cn.itganhuo.app.entity.Permissions;
import cn.itganhuo.app.entity.User;

/**
 * <h2>测试ReplyDao</h2>
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
public class TestPermissionsDao extends AbstractContextControllerTests {

	private PermissionsDao permissionsDao = null;

	@Autowired
	public void setPermissionsDao(PermissionsDao permissionsDao) {
		this.permissionsDao = permissionsDao;
	}
	
	@Test
	public void testInsert() {
		Permissions permissions = new Permissions();
		permissions.setPermission("删除权限");
		permissions.setDescription("删除数据的权限");
		permissions.setIsAvailable(1);
		Assert.isTrue(permissionsDao.insert(permissions)==1, "添加权限数据失败");
	}
	
	@Test
	public void testUpdateInfo() {
		Permissions permissions = new Permissions();
		permissions.setId(1);
		permissions.setPermission("删除权限2");
		permissions.setDescription("删除数据的权限2");
		permissions.setIsAvailable(0);
		Assert.isTrue(permissionsDao.insert(permissions)==1, "修改权限数据失败");
	}
	
	@Test
	public void testLoadById() {
		Assert.notNull(permissionsDao.loadById(1), "根据ID查询数据没有值");
	}
	
	@Test
	public void testGetPermissionsList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.notEmpty(permissionsDao.getPermissionsList(condition), "查询数据没有值");
	}
	
	@Test
	public void testCountPermissionsList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.isTrue(permissionsDao.countPermissionsList(condition) > 0, "没有统计到数据行");
	}
}
