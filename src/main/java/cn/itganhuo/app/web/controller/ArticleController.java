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
package cn.itganhuo.app.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.Comment;
import cn.itganhuo.app.entity.Message;
import cn.itganhuo.app.entity.Page;
import cn.itganhuo.app.entity.Reply;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.CommentService;
import cn.itganhuo.app.service.ReplyService;

/**
 * 文章类请求控制器
 * 
 * @author 深圳-小兴(504487927)
 * @version 2014-08-25
 */
@Controller
public class ArticleController {

  private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

  private ArticleService articleService;
  private CommentService commentService;
  private ReplyService replyService;

  @Autowired
  public void setReplyService(ReplyService replyService) {
    this.replyService = replyService;
  }

  @Autowired
  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Autowired
  public void setCommentService(CommentService commentService) {
    this.commentService = commentService;
  }

  /**
   * 文章列表页
   * <ol>
   * <li>本控制器返回的页面做首页用</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-19
   * @param article
   *          对文章各个字段的查询条件
   * @param search_type
   *          按字段排序类型（type=1（最新）、type=2（热门）、type=3（冷门））
   * @param now_page
   *          当前页码（默认1）
   * @param request
   * @return 返回文章列表，包括文章标签和发布人信息
   */
  @RequestMapping(value = "/articles/{search_type}/{now_page}", method = RequestMethod.GET)
  public ModelAndView articles(Article article, @PathVariable String search_type,
      @PathVariable String now_page, HttpServletRequest request) {
    // 请求路径
    String request_get_context_path = request.getContextPath();

    // 组织分页参数
    Page page = new Page();
    if (!search_type.matches("^[123]?$")) {
      search_type = "1";
    }
    if ("1".equalsIgnoreCase(search_type)) { // 最新查询
      page.setSort("ROWNUM");
      page.setOrder("DESC");
    } else if ("2".equalsIgnoreCase(search_type)) { // 热门查询
      page.setSort("VISITOR_VOLUME");
      page.setOrder("DESC");
    } else if ("3".equalsIgnoreCase(search_type)) { // 冷门查询
      page.setSort("VISITOR_VOLUME");
      page.setOrder("ASC");
    }
    if (now_page.matches("^([1-9]|[1-9][0-9]+)+$")) {
      page.setPage(StringUtil.getInt(now_page, 1));
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("article", article);
    map.put("page", page);

    // 根据条件查询文章列表
    List<Article> articles = articleService.findArticleByCondition(map);
    int total = articleService.countArticleRows();
    Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), 10, 5, total,
        request_get_context_path.concat("/articles"), search_type);

    // 返回数据
    ModelAndView mav = new ModelAndView();
    mav.addObject("articles", articles);
    mav.addObject("pagination", pagination);
    mav.addObject("search_type", search_type);
    mav.setViewName("article_list");
    return mav;
  }

  /**
   * 进入文章详情页
   * 
   * @author 深圳-小兴(504487927) 2014年8月22日
   * @since itganhuo1.0
   * @param id
   *          文章主键
   * @return
   */
  @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
  public ModelAndView getArticleById(@PathVariable(value = "id") String id) {
    // 统计并更新文章访问人数
    articleService.addVisitor_volumeById(id);

    // 查询文章详细信息，包括作者、补充、补充人信息、评论、评论人信息、回复、回复人信息、标签
    Article article_detail = articleService.getArticleDetailById(id);

    // 获取当前登录用户信息
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);

    // 查询当前文章相关联的其它文章
    List<Article> related_article = articleService.getArticleBySubject(id);

    // 返回封装数据到控制器
    ModelAndView mav = new ModelAndView();
    mav.addObject("article", article_detail);
    mav.addObject("user_model", user_model);
    mav.addObject("related_article", related_article);
    mav.setViewName("article_detail");
    return mav;
  }

  /**
   * 对用户回复别人的评论保存到数据库回复表（t_reply）
   * 
   * @author 深圳-小兴(504487927)
   * @version 2014-11-21
   * @param reply
   * @return 返回JSON格式的处理信息
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/article/saveReply")
  @ResponseBody
  public Message saveReply(Reply reply, @RequestParam String comment_id) {
    /*// 过滤并替换特殊字符
    // TODO 小兴后续做这个功能
    // 获取当前登录用户信息
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);
    reply.setId(UUID.randomUUID().toString());
    reply.setUser_id(user_model.getId());
    reply.setComment_id(comment_id);
    reply.setPost_date(new Date());
    reply.setParent_id("0");
    Message message = new Message();
    boolean bool = replyService.addReply(reply);
    if (bool) {
      message.setStatus("0000");
      message.setMessage(ConfigPool.getString2("msg.saveReply.success"));
    } else {
      message.setStatus("9999");
      message.setMessage(ConfigPool.getString2("msg.saveReply.failure"));
    }
    return message;*/
    return null;
  }

  /**
   * 
   * <p>
   * 文章点赞
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年11月20日 下午5:07:48
   * @author 只要修改了这个类就到类注释上写上你的名字
   * @since itganhuo1.0
   * @param id
   *          文章id
   * @return
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/article/addUsefulById/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String addUsefulById(@PathVariable(value = "id") String id) {
    logger.info("into addUsefulById method");
    // 获取登陆用户
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);
    // 执行该操作限制登陆
    if (null == user_model) {
      logger.info("unlogin");
      return "{\"msg\":\"unlogin\", \"status\": \"0\"}";
    }
    // 获取该文章对象
    Article article_model = articleService.getArticleById(id);
    // 用户不能对自己发表的文章进行点赞
   /* if (article_model.getUser_id().equals(user_model.getId())) {
      logger.info("addUsefulOrUseless_onOwn");
      return "{\"msg\":\"addUsefulOrUseless_onOwn\", \"status\": \"0\"}";
    }
    Comment comment = commentService.getCommentModelBySomeThing(id, "2", user_model.getId());
    if (null != comment) {
      logger.info("addUsefulOrUseless_again");
      return "{\"msg\":\"addUsefulOrUseless_again\", \"status\": \"0\"}";
    }
    // 增加点赞记录
    Comment comment_model = new Comment();
    comment_model.setUser_id(user_model.getId());
    comment_model.setObject_id(id);
    comment_model.setOperation_type("1");*/
//    comment_model.setType(2);
//    boolean res = commentService.addComment(comment_model);
    String msg = "";
//    if (res) {
      // 增加点赞次数
      /*res = articleService.addUseful(id);
      if (res) {
        Article article_model1 = articleService.getArticleById(id);
        logger.info("addSuccess");
        msg = "{\"msg\":\"addSuccess\", \"status\": \"" + article_model1.getUseful() + "\"}";
      } else {
        logger.info("addFailure");
        msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
      }*/
    /*} else {
      logger.info("addFailure");
      msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
    }*/
    return msg;
  }

  /**
   * 
   * <p>
   * 文章点踩
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年11月20日 下午5:08:24
   * @author 只要修改了这个类就到类注释上写上你的名字
   * @since itganhuo1.0
   * @param id
   *          文章主键
   * @return
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/article/addUselessById/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String addUselessById(@PathVariable(value = "id") String id) {
    /*logger.info("into addUselessById method");
    // 获取登陆用户
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);
    // 执行该操作限制登陆
    if (null == user_model) {
      logger.info("unlogin");
      return "{\"msg\":\"unlogin\", \"status\": \"0\"}";
    }
    // 获取该文章对象
    Article article_model = articleService.getArticleById(id);
    // 用户不能对自己发表的文章进行点踩
    if (article_model.getUser_id().equals(user_model.getId())) {
      logger.info("addUsefulOrUseless_onOwn");
      return "{\"msg\":\"addUsefulOrUseless_onOwn\", \"status\": \"0\"}";
    }
    Comment comment = commentService.getCommentModelBySomeThing(id, "2", user_model.getId());
    if (null != comment) {
      logger.info("addUsefulOrUseless_again");
      return "{\"msg\":\"addUsefulOrUseless_again\", \"status\": \"0\"}";
    }
    // 增加点踩记录
    Comment comment_model = new Comment();
    comment_model.setUser_id(user_model.getId());
    comment_model.setObject_id(id);
    comment_model.setOperation_type("0");
    comment_model.setType(2);
    boolean res = commentService.addComment(comment_model);
    String msg = "";
    if (res) {
      // 增加点踩次数
      res = articleService.addUseless(id);
      if (res) {
        Article article_model1 = articleService.getArticleById(id);
        logger.info("addSuccess");
        msg = "{\"msg\":\"addSuccess\", \"status\": \"" + article_model1.getUseless() + "\"}";
      } else {
        logger.info("addFailure");
        msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
      }
    } else {
      logger.info("addFailure");
      msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
    }*/
//    return msg;
    return null;
  }

  /**
   * 评论点赞
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年10月7日 下午4:33:47
   * @since itganhuo1.0
   * @param id
   *          评论id
   * @return
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/article/addPraiseById/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String addPraiseById(@PathVariable(value = "id") String id) {
    /*logger.info("into addPraiseById method");
    // 获取登陆用户
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);
    // 执行该操作限制登陆
    if (null == user_model) {
      logger.info("unlogin");
      return "{\"msg\":\"unlogin\", \"status\": \"0\"}";
    }
    // 获取该评论对象
    Comment comment_model = commentService.getCommentById(id);
    // 用户不能对自己发表的评论进行点赞
    if (comment_model.getUser_id().equals(user_model.getId())) {
      logger.info("addUsefulOrUseless_onOwn");
      return "{\"msg\":\"addUsefulOrUseless_onOwn\", \"status\": \"0\"}";
    }
    Comment comment = commentService.getCommentModelBySomeThing(id, "3", user_model.getId());
    if (null != comment) {
      logger.info("addUsefulOrUseless_again");
      return "{\"msg\":\"addUsefulOrUseless_again\", \"status\": \"0\"}";
    }
    // 增加评论点赞记录
    comment_model = new Comment();
    comment_model.setUser_id(user_model.getId());
    comment_model.setObject_id(id);
    comment_model.setOperation_type("1");
    comment_model.setType(3);
    boolean res = commentService.addComment(comment_model);
    String msg = "";
    if (res) {
      // 增加评论点赞次数
      res = commentService.addPraiseById(id);
      if (res) {
        Comment comment2 = commentService.getCommentById(id);
        logger.info("addSuccess");
        msg = "{\"msg\":\"addSuccess\", \"status\": \"" + comment2.getPraise() + "\"}";
      } else {
        logger.info("addFailure");
        msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
      }
    } else {
      logger.info("addFailure");
      msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
    }
    return msg;*/
    return null;
  }

  /**
   * 评论点踩
   * 
   * @author Java私塾在线学习社区（329232140）深圳-夕落946594780 2014年10月7日 下午4:33:47
   * @since itganhuo1.0
   * @param id
   *          评论id
   * @return
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/article/addTrampleById/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String addTrampleById(@PathVariable(value = "id") String id) {
    /*logger.info("into addPraiseById method");
    // 获取登陆用户
    Subject current_user = SecurityUtils.getSubject();
    User user_model = (User) current_user.getSession().getAttribute(
        ConstantPool.USER_SHIRO_SESSION_ID);
    // 执行该操作限制登陆
    if (null == user_model) {
      logger.info("unlogin");
      return "{\"msg\":\"unlogin\", \"status\": \"0\"}";
    }
    // 获取该评论对象
    Comment comment_model = commentService.getCommentById(id);
    // 用户不能对自己发表的评论进行点踩
    if (comment_model.getUser_id().equals(user_model.getId())) {
      logger.info("addUsefulOrUseless_onOwn");
      return "{\"msg\":\"addUsefulOrUseless_onOwn\", \"status\": \"0\"}";
    }
    Comment comment = commentService.getCommentModelBySomeThing(id, "3", user_model.getId());
    if (null != comment) {
      logger.info("addUsefulOrUseless_again");
      return "{\"msg\":\"addUsefulOrUseless_again\", \"status\": \"0\"}";
    }
    // 增加评论点踩记录
    comment_model = new Comment();
    comment_model.setUser_id(user_model.getId());
    comment_model.setObject_id(id);
    comment_model.setOperation_type("0");
    comment_model.setType(3);
    boolean res = commentService.addComment(comment_model);
    String msg = "";
    if (res) {
      // 增加评论点踩次数
      res = commentService.addTrampleById(id);
      if (res) {
        Comment comment3 = commentService.getCommentById(id);
        logger.info("addSuccess");
        msg = "{\"msg\":\"addSuccess\", \"status\": \"" + comment3.getTrample() + "\"}";
      } else {
        logger.info("addFailure");
        msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
      }
    } else {
      logger.info("addFailure");
      msg = "{\"msg\":\"addFailure\", \"status\": \"0\"}";
    }
    return msg;*/
    return null;
  }

}
