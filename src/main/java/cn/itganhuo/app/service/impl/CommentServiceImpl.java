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
package cn.itganhuo.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.dao.ArticleDao;
import cn.itganhuo.app.dao.CommentDao;
import cn.itganhuo.app.entity.Comment;
import cn.itganhuo.app.service.CommentService;

/**
 * 评论服务层实现类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao = null;
	private ArticleDao articleDao = null;

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#addComment(cn.itganhuo.app.entity.Comment)
	 */
	@Override
	public int addComment(Comment comment) {
		comment.setPostDate(DateUtil.getNowDateTimeStr(null));
		if (comment.getType() == 2) {
			articleDao.addUsefulById(comment.getArticleId());
		} else if (comment.getType() == 3) {
			articleDao.addUselessById(comment.getArticleId());
		}
		return commentDao.insert(comment);
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#getCommentById(int)
	 */
	@Override
	public Comment getCommentById(int id) {
		return commentDao.getCommentById(id);
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#addTrampleById(int)
	 */
	@Override
	public int addTrampleById(int id) {
		return commentDao.addTrampleById(id);
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#addPraiseById(int)
	 */
	@Override
	public int addPraiseById(int id) {
		return commentDao.addPraiseById(id);
	}

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#isInvolvedComment(int, int, int)
	 */
	@Override
	public boolean isInvolvedComment(int articleId, int userId) {
		Comment comment = commentDao.isInvolvedComment(1, articleId, userId);
		if (comment == null || comment.getId() <= 0)
			return false;
		else
			return true;
	}
	
}
