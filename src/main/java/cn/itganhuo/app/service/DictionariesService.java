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

import cn.itganhuo.app.entity.Dictionaries;

/**
 * <h2>数据字典服务层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>用来对数据字典进行常规的CRUD操作。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
public interface DictionariesService {

	/**
	 * <h2>添加一条数据</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param dictionaries
	 *            字典数据
	 * @return 返回主键
	 */
	public int addDictionaries(Dictionaries dictionaries);

	/**
	 * <h2>修改一条信息</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param dictionaries
	 *            字典数据
	 * @return 修改成功返回ture，否则返回false。
	 */
	public boolean updateInfoDictionaries(Dictionaries dictionaries);

	/**
	 * <h2>根据id删除一条信息</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param id
	 *            字典ID
	 * @return 修改成功返回ture，否则返回false。
	 */
	public boolean delDictionaries(int id);

	/**
	 * <h2>根据id删除一条信息</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param id
	 *            字典ID
	 * @return 一条数据字典
	 */
	public Dictionaries getDictionariesModelById(int id);

	/**
	 * <h2>分页按条件查询数据字典列表</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param condition
	 *            查询条件
	 * @return 数据集合
	 */
	public Map<String, List<Object>> getDictionariesList(Map<Object, Object> condition);
}
