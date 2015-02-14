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

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.UserDao;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.UserService;

/**
 * 用户业务处理实现类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.UserService#login(cn.itganhuo.model.User)
	 */
	public User login(String r_account) throws AuthenticationException {
		// 1、判断账号是否存在并且是否锁定状态
		User d_user = userDao.loadByAccount(r_account);
		if (d_user == null) {
			throw new UnknownAccountException();
		}
		if (0 != d_user.getIsLock()) {
			throw new LockedAccountException();
		}
		return d_user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.UserService#insert(cn.itganhuo.model.User)
	 */
	public int insert(User user) {
		return userDao.insert(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.UserService#loadByAccount(java.lang.String)
	 */
	public User loadByAccount(String account) {
		if (StringUtil.hasText(account))
			return userDao.loadByAccount(account);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.UserService#loadById(java.lang.String)
	 */
	public User loadById(int id) {
		if (id > 0)
			return userDao.loadById(id);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.UserService#updateInfoByAccount(cn.itganhuo.app.entity.User)
	 */
	public int updateInfoByAccount(User user) {
		return userDao.updateInfoByAccount(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.UserService#updatePasswordByAccount(cn.itganhuo.app.entity.User)
	 */
	public boolean updatePasswordByAccount(User user) {
		String algorithmName = "SHA-512";
		String salt1 = user.getAccount();
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1.concat(salt2), hashIterations);
		user.setPassword(hash.toBase64());
		user.setSalt(salt2);
		return this.userDao.updatePasswordByAccount(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.UserService#getUserList(java.util.Map)
	 */
	public Map<String, List<Object>> getUserList(Map<Object, Object> condition) {
		List<Object> users = userDao.getUserList(condition);
		List<Object> total = new ArrayList<Object>();
		if (users != null && users.size() > 0) {
			total.add(userDao.countUserList(condition));
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("total", total);
		map.put("rows", users);
		return map;
	}

}
