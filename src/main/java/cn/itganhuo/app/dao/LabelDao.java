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

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Label;

/**
 * 标签表数据层接口
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-09-30
 */
@Repository
public interface LabelDao {

	/**
	 * 保存标签
	 * 
	 * @param label
	 * @return
	 */
	public int insert(Label label);

	/**
	 * 根据主键删除标签
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public boolean delById(int id);

	/**
	 * 根据主键修改标签
	 * 
	 * @param label
	 * @return
	 */
	public boolean updateById(Label label);

	/**
	 * 按条件查询标签
	 * 
	 * @param label
	 * @return
	 */
	public List<Label> getLabelByCondition(Label label);
}
