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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.Paging;
import cn.itganhuo.app.entity.User;

/**
 * <h2>测试</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>测试的前提就是数据库中必须有测试数据，测试数据我已经备份成SQL脚本放到测试资源文件目录中。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestArticleDao extends AbstractContextControllerTests {

	@Autowired
	private ArticleDao articleDao;
	
	@Test
	public void testInsert() {
		Article article = new Article();
		article.setUserId(1);
		article.setTitle("领先的 Web 技术教程");
		article.setContent("W3School将为您回答这个问题，在您成为专业Web开发者的路上助一臂之力，从而更好地应对未来的挑战。");
		article.setYmd("2014-2-1");
		article.setHms("12:12:23");
		article.setUpdateDate("2015-1-1 12:21:21");
		article.setPraiseNum(2);
		article.setTrampleNum(3);
		article.setVisitorNum(5);
		article.setAnswerNum(1);
		Assert.isTrue(articleDao.insert(article) > 0);;
	}
	
	@Test
	public void testFindArticleByCondition() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("paging", new Paging());
		condition.put("user", new User());
		Assert.notEmpty(articleDao.findArticleByCondition(condition));
	}
	
	@Test
	public void testGetArticleById() {
		Assert.notNull(articleDao.getArticleById(1));
	}
	
	@Test
	public void testCountArticleRows() {
		Assert.isTrue(articleDao.countArticleRows(null) > 0);
	}
	
	@Test
	public void testGetArticleByUserId() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", 1);
		param.put("rowNum", 5);
		Assert.notEmpty(articleDao.getArticleByUserId(param));
	}
	
	@Test
	public void testAddVisitorNumById() {
		Assert.isTrue(articleDao.addVisitorNumById(1));
	}
	
	@Test
	public void testAddPraiseNumById() {
		Assert.isTrue(articleDao.addPraiseNumById(1));
	}
	
	@Test
	public void testAddTrampleNumById() {
		Assert.isTrue(articleDao.addTrampleNumById(1));
	}
	
	@Test
	public void testGetArticleDetailById() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 1);
		Assert.notNull(articleDao.getArticleDetailById(param));
	}
	
	@Test
	public void testGetArticleByLabel() {
		Assert.notNull(articleDao.getArticleByLabel(1));
	}
}
