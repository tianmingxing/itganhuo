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
package cn.itganhuo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.Menu;

/**
 * <h2>测试</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>测试的前提就是数据库中必须有测试数据，测试数据我已经备份成SQL脚本放到测试资源文件目录中。</dd>
 * </dl>
 * 
 * @version 0.0.3-SNAPSHOT
 * @author 深圳-小兴
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestMenuService extends AbstractContextControllerTests {
	
	private static List<Menu> menus = new ArrayList<Menu>();
	
	@Autowired
	private MenuService menuService;
	
	@BeforeClass
	public static void init() {
		Menu m1 = new Menu(1,0,"系统管理", "open", true, "", "/admin/sysList", 1);
		Menu m2 = new Menu(2,1,"参数配置", "open", true, "", "/admin/paramItem", 2);
		Menu m3 = new Menu(3,1,"数据字典", "close", true, "", "/admin/shujuList", 3);
		Menu m4 = new Menu(4,0,"会员管理", "open", true, "", "/admin/memberManger", 4);
		Menu m5 = new Menu(5,4,"会员列表", "open", true, "", "/admin/memberList", 5);
		menus.add(m1);
		menus.add(m2);
		menus.add(m3);
		menus.add(m4);
		menus.add(m5);
	}
	
	private List<Menu> findMenuByPid(int pid) {
		List<Menu> retMenus = new ArrayList<Menu>();
		for (int i = 0; i < menus.size(); i++) {
			if (menus.get(i).getPid() == pid) {
				retMenus.add(menus.get(i));
			}
		}
		return retMenus;
	}
	
	private List<Menu> getMenuTree(int pid) {
		List<Menu> list1 = findMenuByPid(pid);
		if (list1 != null && list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				list1.get(i).setChildren(this.getMenuTree(list1.get(i).getId()));
			}
		}
		return list1;
	}

	@Test
	public void testGetMenuTree() {
		List<Menu> tmpMenus = getMenuTree(0);
		for (int i = 0; i < tmpMenus.size(); i++) {
			System.out.println(tmpMenus.get(i));
		}
	}
	
	@Test
	public void testGetMenuTree2() {
		List<Menu> tmpMenus = menuService.getMenuTree(0);
		for (int i = 0; i < tmpMenus.size(); i++) {
			System.out.println(tmpMenus.get(i));
		}
	}
	
}
