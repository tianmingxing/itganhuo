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

import cn.itganhuo.app.entity.Menu;

/**
 * <h2>后台菜单管理服务层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>后台使用easy ui作为视图平台，这个接口用来管理运营后台的菜单。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public interface MenuService {

	/**
	 * 获取菜单数据并组装到JsonTree对象中，让控制层返回json。
	 * <ol>
	 * <li>参数传根节点的PID也就是0即可，目前已经实现无限级查询tree。</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 返回菜单模型
	 */
	public List<Menu> getMenuTree(int pid);

	/**
	 * 为后台菜单管理查询一个列表
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 菜单列表
	 */
	public Map<String, List<Object>> getMenuList(Map<Object, Object> condition);

	/**
	 * 为后台增加一个菜单
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param menu
	 *            菜单数据
	 * @return 返回主键
	 */
	public int addMenu(Menu menu);

	/**
	 * 删除一个菜单
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            菜单ID
	 * @return 删除成功返回ture，否则返回false。
	 */
	public boolean delMenu(int id);

	/**
	 * 修改一条菜单
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param menu
	 *            菜单数据，其中包含要修改的ID。
	 * @return 修改成功返回ture，否则返回false。
	 */
	public boolean updateMenuById(Menu menu);

}
