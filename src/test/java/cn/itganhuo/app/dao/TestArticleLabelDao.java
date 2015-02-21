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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.entity.ArticleLabel;

/*
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
//@Transactional
public class TestArticleLabelDao extends AbstractContextControllerTests {
	
	@Autowired
	private ArticleLabelDao articleLabelDao;
	
	@Test
	public void testInsert() {
		ArticleLabel articleLabel = new ArticleLabel();
		articleLabel.setArticleId(1);
		articleLabel.setLabelId(1);
		articleLabel.setUserId(1);
		int i = articleLabelDao.insert(articleLabel);
		System.out.println("i====>" + i + ", articleLabel.id=" + articleLabel.getId());
		Assert.isTrue(i > 0);
	}
	
	@Test
	public void testGetArticleLabelById() {
		Assert.notEmpty(articleLabelDao.getArticleLabelById(1), "没有查询到数据");
	}

}
