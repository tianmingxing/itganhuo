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

import cn.itganhuo.app.entity.ArticleLabel;

/**
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-10-16
 */
@Repository
public interface ArticleLabelDao {

	/**
	 * 保存文章和标签之间的关系
	 * 
	 * @param articleLabel
	 * @return
	 */
	public int insert(ArticleLabel articleLabel);

	/**
	 * 根据文章主键查询对应的标签集合
	 * 
	 * @author 深圳-小兴(504487927)
	 * @version 2014-11-18
	 * @param article_id
	 */
	public List<ArticleLabel> getArticleLabelById(int article_id);
}
