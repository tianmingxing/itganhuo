/*
 * Solemnly declare(2014-11-11):
 * 1 This project is a collective creation of its copyrighted Java private school online learning community (QQ group number 329 232 140) of all, is prohibited without the author's permission for commercial profit without permission;
 * 2 See the list of IT ganhuo sharing network http://www.itganhuo.cn/teams;
 * 3, the author does not guarantee the quality of the project and its stability, reliability, and security does not assume any responsibility if you get the source code for this project in some way, all the consequences of the subsequent occurrence of ego nothing to do with this group and author;
 * 4 mx.tian@qq.com have any questions, please contact us.
 *
 * 郑重声明(2014-11-11)：
 * 1、本项目属集体创作其版权归Java私塾在线学习社区（QQ群号329232140）作者所有，禁止未经作者授权擅自用于商业盈利；
 * 2、作者名单详见IT干货技术分享网http://www.itganhuo.cn/teams；
 * 3、作者不保证项目质量并对其稳定性、可靠性和安全性不承担任何责任，如果你通过某些途径获取本项目源代码，其后发生的一切后果自负与本群及作者无关；
 * 4、有任何问题请与我们联系mx.tian@qq.com。
 */
package cn.itganhuo.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.dao.ArticleDao;
import cn.itganhuo.app.dao.CommentDao;
import cn.itganhuo.app.entity.Comment;
import cn.itganhuo.app.service.CommentService;

/**
 * 评论
 * 
 * @author 深圳-小兴(504487927) 2014-9-15
 * @since itganhuo1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

  private CommentDao commentDao = null;
  private ArticleDao articleDao = null;

  @Autowired
  public void setCommentMapper(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Autowired
  public void setArticleMapper(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.CommentService#addComment(cn.itganhuo.model.CommentModel )
   */
  public boolean addComment(Comment comment_model) {
    /*comment_model.setId(UUID.randomUUID().toString());
    comment_model.setPost_date(new Date());
    if (comment_model.getType() == 1) {
      // 增加评论总数
      boolean res = articleDao.addAnswer_number(comment_model.getObject_id());
      if (!res) {
        return res;
      }
    }
    return commentDao.insert(comment_model);*/
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.CommentService#getCommentById(java.lang.String)
   */
  public Comment getCommentById(String id) {
    return commentDao.getCommentById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.CommentService#addTrampleById(java.lang.String)
   */
  public boolean addTrampleById(String id) {
    return commentDao.addTrampleById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.CommentService#addPraiseById(java.lang.String)
   */
  public boolean addPraiseById(String id) {
    return commentDao.addPraiseById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.CommentService#getCommentModelBySomeThing(java
   * .lang.String,java.lang.String,java.lang.String)
   */
  public Comment getCommentModelBySomeThing(String object_id, String type, String user_id) {
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.put("object_id", object_id);
    map.put("type", type);
    map.put("user_id", user_id);
    return commentDao.getCommentModelBySomeThing(map);
  }

}
