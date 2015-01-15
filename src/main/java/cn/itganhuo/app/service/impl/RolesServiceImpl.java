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

import cn.itganhuo.app.dao.RolesDao;
import cn.itganhuo.app.entity.Roles;
import cn.itganhuo.app.service.RolesService;

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
@Service
public class RolesServiceImpl implements RolesService {

	private RolesDao rolesDao;

	@Autowired
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public int addRoless(Roles roles) {
		return rolesDao.insert(roles);
	}

	public boolean updateInfoRoles(Roles roles) {
		return rolesDao.updateInfo(roles) == 1;
	}

	public boolean delRoles(int id) {
		Roles roles = new Roles();
		roles.setId(id);
		roles.setIsAvailable(0);
		return rolesDao.updateInfo(roles) == 1;
	}

	public Roles getRolesModelById(int id) {
		return rolesDao.loadById(id);
	}

	public Map<String, List<Object>> getRolesList(Map<Object, Object> condition) {
		List<Object> list = rolesDao.getRolesList(condition);
		List<Object> total = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			total.add(rolesDao.countRolesList(condition));
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

}
