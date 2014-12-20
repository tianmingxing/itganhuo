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

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;

import cn.itganhuo.app.entity.User;

/**
 * 用户业务处理接口
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public interface UserService {

	/**
	 * 根据登录账号查询对应的唯一一条记录
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param account
	 *            账号
	 * @return 会员信息
	 */
	public User login(String account) throws AuthenticationException;

	/**
	 * 会员注册
	 * <ol>
	 * <li>收集会员账号和密码保存到数据库</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param user
	 * @return
	 */
	public int insert(User user);

	/**
	 * 根据账户查找用户信息
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 朱塞佩
	 * @param account 账号
	 * @return 用户
	 */
	public User loadByAccount(String account);

	/**
	 * 根据主键查询一条用户信息
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            主键
	 * @return 用户
	 */
	public User loadById(int id);

	/**
	 * 修改用户信息
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 朱塞佩
	 * @param user
	 * @return
	 */
	public int updateInfoByAccount(User user);

	/**
	 * 修改用户密码
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 朱塞佩
	 * @param user
	 * @return
	 */
	public boolean updatePasswordByAccount(User user);

	/**
	 * <h2>根据条件查询用户列表信息</h2>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param condition 查询条件
	 * @return 经过筛选后的用户集合
	 */
	public Map<String, List<Object>> getUserList(Map<Object, Object> condition);

}
