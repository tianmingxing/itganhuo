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
package cn.itganhuo.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Comment;

/**
 * 评论表数据层接口
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-09-15
 */
@Repository
public interface CommentDao {

  /**
   * 插入一条评论数据到t_comment
   * 
   * @author 深圳-小兴(504487927) 2014-9-15
   * @since itganhuo1.0
   * @param comment_model
   * @return
   */
  public boolean insert(Comment comment_model);

  /**
   * 根据主键查询评论详情
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年10月7日 下午5:16:13
   * @since itganhuo1.0
   * @param id
   *          评论id
   * @return
   */
  public Comment getCommentById(String id);

  /**
   * 评论点赞
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年11月19日 下午3:31:45
   * @since itganhuo1.0
   * @param id
   *          评论id
   * @return
   */
  public boolean addPraiseById(String id);

  /**
   * 评论点踩
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年11月19日 下午3:33:12
   * @since itganhuo1.0
   * @param id
   *          评论id
   * @return
   */
  public boolean addTrampleById(String id);

  /**
   * 查询当前登陆用户是否已经对同一对象执行点赞/踩操作
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年11月20日 下午5:33:53
   * @since itganhuo1.0
   * @param object_id
   *          对象id(文章、评论Id)
   * @param type
   *          1文章评价，2文章赞踩，3评论赞踩
   * @param user_id
   *          登陆用户Id
   * @return
   */
  public Comment getCommentModelBySomeThing(Map<Object, Object> condition);

}
