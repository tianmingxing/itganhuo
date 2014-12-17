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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.User;

/**
 * <h2>用户表数据层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>本接口的实现有两种方式：<br>
 * <ol>
 * <li>通过*Mapper.xml文件来实现本接口中的方法；</li>
 * <li>直接在本接口中通过注解的方式直接写SQL语句；</li>
 * <li>本类定义的接口要和*Mapper.xml里一一对应，如果接口中有定义而xml中没有则会报错。</li>
 * </ol>
 * </dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Repository
public interface UserDao {

	/**
	 * 根据主键删除对应的一条数据
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            主键
	 * @return 返回删除的数据行数
	 */
	@Delete("DELETE FROM T_USER WHERE ID=#{id}")
	public int deleteById(int id);

	/**
	 * 根据主键修改对应一名用户的密码字段
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param record
	 *            用户
	 * @return 返回修改的数据行数
	 */
	@Update("UPDATE T_USER SET PASSWORD=#{password} WHERE ID=#{id}")
	public int updateById(User record);

	/**
	 * 添加一条用户信息
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param user
	 *            用户
	 * @return 如果插入成功则返回1，否则返回0
	 */
	public int insert(User user);

	/**
	 * 根据用户主键查询对应的唯一一条数据
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            主键
	 * @return User
	 */
	public User loadById(int id);

	/**
	 * <h2>根据账号查询一名用户信息</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param account 账号
	 * @return 一个用户
	 */
	public User loadByAccount(String account);

	/**
	 * 根据帐户修改用户信息，包括用户名，性别，email，qq， 电话号码
	 * 
	 * @param record
	 *            用户
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-朱塞佩
	 * @return
	 */
	public int updateInfoByAccount(User record);

	/**
	 * 根据帐户修改密码
	 * 
	 * @param record
	 *            用户
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-朱塞佩
	 * @return
	 */
	public boolean updatePasswordByAccount(User record);

	/**
	 * 根据账户修改头像
	 * 
	 * @param record
	 * @return
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-朱塞佩
	 */
	public int updateImg(User record);

	/**
	 * 
	 * <h2>根据条件查询用户列表</h2>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<Object> getUserList(Map<Object, Object> condition);

	/**
	 * <h2>根据条件统计数据行为分页做准备</h2>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public int countUserList(Map<Object, Object> condition);

}