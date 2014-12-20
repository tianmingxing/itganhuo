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

import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;

/**
 * 对文章做业务操作的接口
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public interface ArticleService {

	/**
	 * 分页按条件查询文章列表
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param map
	 *            分页条件
	 * @return 文章列表
	 */
	public List<Article> findArticleByCondition(Map<String, Object> map);

	/**
	 * <h3>根据主键查询文章详情及其对应的作者</h3>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            文章ID
	 * @return 一篇文章
	 */
	public Article getArticleById(int id);

	/**
	 * 查询文章详细信息，包括文章、作者、补充、补充人信息、评论、评论人信息、回复、回复人信息、标签<br>
	 * <ol>
	 * <li>联表查询数据比较多，这个查询主要是给文章详情页用，如果你用不到这么多信息，建议还是另外写一个方法为好。</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            文章主键
	 * @return 一篇文章
	 */
	public Article getArticleDetailById(int id);

	/**
	 * 统计文章总数
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 数据总行数
	 */
	public int countArticleRows();

	/**
	 * 会员添加一遍文章
	 * <ol>
	 * <li>由会员登录后在用户中心提交表单</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param article
	 *            文章
	 * @return 返回主键
	 */
	public int addArticle(Article article);

	/**
	 * 在用户中心查询它最近发布的文章
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param user_id
	 *            用户主键
	 * @param row_num
	 *            数据行数
	 * @return 文章集合
	 */
	public List<Article> getArticleByUserId(int user_id, int row_num);

	/**
	 * 增加浏览次数
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            文章id
	 * @return 修改成功返回true，反之返回false。
	 */
	public boolean addVisitorVolumeById(int id);

	/**
	 * 文章和标签被保存到数据后返回两者的ID，这个方法是将文章和ID绑定到。<br>
	 * 对于中间表的操作比较单一所以服务层的逻辑就全部放入到这里。
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param articleLabel
	 *            文章和标签中间表数据
	 * @return 返回主键
	 */
	public int saveArticleLabel(ArticleLabel articleLabel);

	/**
	 * 文章点赞
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param article_id
	 *            文章主键
	 * @return 返回主键
	 */
	public boolean addUseful(int article_id);

	/**
	 * 文章点踩
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param article_id
	 *            文章主键
	 * @return
	 */
	public boolean addUseless(int article_id);

	/**
	 * <h2>根据文章id得到相同标签下的文章（不包含自己）</h2>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            文章ID
	 * @return 文章集合
	 */
	public List<Article> getArticleByLabel(int id);
}
