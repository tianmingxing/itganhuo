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

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Menu;

/**
 * <h2>后台菜单表数据层接口</h2>
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
@Repository
public interface MenuDao {

	/**
	 * 根据主键查询一条对应的值
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            主键
	 * @return 返回一条菜单
	 */
	public List<Menu> findMenuById(int id);

	/**
	 * 根据外键查询对应的菜单集合
	 * <ol>
	 * <li>注意：最高父级别的外键默认是0</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param pid
	 *            外键（本表的主键）
	 * @return 返回一个菜单集合
	 */
	public List<Menu> findMenuByPid(int pid);

	/**
	 * 到菜单表中分布按条件查询列表数据
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 菜单集合
	 */
	public List<Object> getMenuList(Map<Object, Object> condition);

	/**
	 * 根据条件统计记录数（不分页的情况下）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param condition 查询条件
	 * @return 数据行数
	 */
	public int countMenuList(Map<Object, Object> condition);

	/**
	 * 新增一条记录到菜单表（t_menu）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param menu 菜单数据
	 * @return 返回主键
	 */
	public int addMenu(Menu menu);

	/**
	 * 根据主键到菜单表（t_menu）删除一条记录
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id 菜单ID
	 * @return 删除成功返回true，否则返回false。
	 */
	public boolean delMenu(int id);

	/**
	 * 修改一条菜单
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param menu 菜单数据
	 * @return 修改成功返回true，否则返回false。
	 */
	public boolean updateMenu(Menu menu);
}
