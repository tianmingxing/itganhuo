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

import cn.itganhuo.app.dao.PermissionsDao;
import cn.itganhuo.app.entity.Permissions;
import cn.itganhuo.app.service.PermissionsService;

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
 * @author 天津-朱塞佩
 */
@Service
public class PermissionsServiceImpl implements PermissionsService {

	private PermissionsDao permissionsDao;

	@Autowired
	public void setPermissionsDao(PermissionsDao permissionsDao) {
		this.permissionsDao = permissionsDao;
	}

	public int addpermissions(Permissions permissions) {
		return permissionsDao.insert(permissions);
	}

	public boolean updateInfoPermissions(Permissions permissions) {
		return permissionsDao.updateInfo(permissions);
	}

	public boolean delPermissions(int id) {
		Permissions permissions = new Permissions();
		permissions.setId(1);
		permissions.setIsAvailable(1);
		return permissionsDao.updateInfo(permissions);
	}

	public Permissions getPermissionsModelById(int id) {
		return permissionsDao.loadById(id);
	}

	public Map<String, List<Object>> getPermissionsList(Map<Object, Object> condition) {
		List<Object> list = permissionsDao.getPermissionsList(condition);
		List<Object> total = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			total.add(permissionsDao.countPermissionsList(condition));
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
}
