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
package cn.itganhuo.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.dao.MenuDao;
import cn.itganhuo.app.entity.Menu;
import cn.itganhuo.app.service.MenuService;

/**
 * <h2>后台菜单管理之服务层实现类</h2>
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
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.AdminService#getMenuTreeJson()
	 */
	public List<Menu> getMenuTree() {
		List<Menu> list1 = menuDao.findMenuByPid(0);
		if (list1 != null && list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				int id = list1.get(i).getId();
				if (id >= 0) {
					List<Menu> list2 = menuDao.findMenuByPid(id);
					if (list2 != null && list2.size() > 0) {
						list1.get(i).setChildren(list2);
						for (int j = 0; j < list2.size(); j++) {
							int id2 = list2.get(j).getId();
							if (id2 >= 0) {
								List<Menu> list3 = menuDao.findMenuByPid(id2);
								if (list3 != null && list3.size() > 0) {
									list2.get(j).setChildren(list3);
								}
							}
						}
					}
				}
			}
		}
		return list1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.AdminService#getMenuListJson()
	 */
	public Map<String, List<Object>> getMenuList(Map<Object, Object> condition) {
		List<Object> list = menuDao.getMenuList(condition);
		List<Object> total = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			total.add(menuDao.countMenuList(condition));
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.AdminService#addMenu(cn.itganhuo.model.MenuModel)
	 */
	public int addMenu(Menu menu) {
		return menuDao.addMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.AdminService#delMenu(java.lang.String)
	 */
	public boolean delMenu(int id) {
		return menuDao.delMenu(id);
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.MenuService#updateMenuById(cn.itganhuo.app.entity.Menu)
	 */
	@Override
	public boolean updateMenuById(Menu menu) {
		return menuDao.updateMenu(menu);
	}

}
