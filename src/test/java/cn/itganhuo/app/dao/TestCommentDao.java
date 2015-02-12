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
import cn.itganhuo.app.entity.Comment;

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
public class TestCommentDao extends AbstractContextControllerTests {

	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void testInsert() {
		Comment comment = new Comment();
		comment.setType(1);
		comment.setArticleId(1);
		comment.setUserId(1);
		comment.setContent("你的经验分享的太棒了");
		comment.setPostDate("2014-1-2 12:43:32");
		comment.setPraiseNum(2);
		comment.setTrampleNum(4);
		Assert.isTrue(commentDao.insert(comment) > 0, "插入数据失败");
	}
	
	@Test
	public void testGetCommentById() {
		Assert.notNull(commentDao.getCommentById(1), "没有查询到指定ID记录");
	}
	
	@Test
	public void testAddPraiseById() {
		Assert.isTrue(commentDao.addPraiseById(1) > 0, "评论点赞同失败");
	}
	
	@Test
	public void testAddTrampleById() {
		Assert.isTrue(commentDao.addTrampleById(1) > 0, "评论点踩失败");
	}
	
	@Test
	public void testIsInvolvedComment() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 1);
		param.put("articleId", 1);
		param.put("userId", 1);
		Assert.isNull(commentDao.isInvolvedComment(param), "查询当前文章是否有被当前用户赞或踩的记录失败");
	}
}
