/*
 * Copyright 2014-2024 the https://github.com/xiaoxing598/itganhuo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * This project consists of JAVA private school online learning community group Friends co-creator [QQ group 329232140].
 * 本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140];
 * See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams];
 * The author does not guarantee the quality of the project and its stability, reliability, and security does not bear any responsibility.
 * 作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任.
 */
package cn.itganhuo.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.itganhuo.app.entity.*;
import cn.itganhuo.app.service.AttentionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.CommentService;
import cn.itganhuo.app.service.ReplyService;

/**
 * <h2>文章类请求控制器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 *
 * @author 深圳-小兴，深圳-夕落
 * @version 0.0.1-SNAPSHOT
 */
@Controller
public class ArticleController {

    private static final Logger log = LogManager.getLogger(ArticleController.class.getName());

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private AttentionService attentionService;

    /**
     * <h2>文章列表页</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>本控制器返回的页面做首页用</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param article     对文章各个字段的查询条件
     * @param search_type 按字段排序类型（type=1（最新）、type=2（热门）、type=3（冷门））
     * @param now_page    当前页码（默认1）
     * @param request     Http请求
     * @return 返回文章列表，包括文章标签和发布人信息
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/articles/{search_type}/{now_page}", method = RequestMethod.GET)
    public ModelAndView articles(Article article, @PathVariable String search_type, @PathVariable String now_page, HttpServletRequest request) {
        // 请求路径
        String request_get_context_path = request.getContextPath();

        // 组织分页参数
        Paging paging = new Paging();
        if (!search_type.matches("^[123]?$")) {
            search_type = "1";
        }
        if ("1".equalsIgnoreCase(search_type)) { // 最新查询
            paging.setSort("id");
            paging.setOrder("DESC");
        } else if ("2".equalsIgnoreCase(search_type)) { // 热门查询
            paging.setSort("visitorNum");
            paging.setOrder("DESC");
        } else if ("3".equalsIgnoreCase(search_type)) { // 冷门查询
            paging.setSort("visitorNum");
            paging.setOrder("ASC");
        }
        if (now_page.matches("^([1-9]|[1-9][0-9]+)+$")) {
            paging.setPage(StringUtil.getInt(now_page, 1));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article", article);
        map.put("paging", paging);

        // 根据条件查询文章列表
        List<Article> articles = articleService.findArticleByCondition(map);
        int total = articleService.countArticleRows(null);
        Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), paging.getRows(), 5, total,
                request_get_context_path.concat("/articles"), search_type);

        // 返回数据
        ModelAndView mav = new ModelAndView();
        mav.addObject("articles", articles);
        mav.addObject("pagination", pagination);
        mav.addObject("search_type", search_type);
        mav.addObject("path", request.getContextPath());
        mav.addObject("servletPath", request_get_context_path);
        mav.setViewName("article_list");
        return mav;
    }

    /**
     * <h2>文章列表页，根据标签进行区分查询。也就是说只查询某一个标签下的所有文章列表。</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>本控制器返回的页面做首页用</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param article     对文章各个字段的查询条件
     * @param search_type 按字段排序类型（type=1（最新）、type=2（热门）、type=3（冷门））
     * @param now_page    当前页码（默认1）
     * @param label_id    标签ID
     * @param request     Http请求
     * @return 返回文章列表，包括文章标签和发布人信息
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/articles/{search_type}/{now_page}/{label_id}", method = RequestMethod.GET)
    public ModelAndView articlesByLabelId(Article article, @PathVariable String search_type, @PathVariable String now_page,
                                          @PathVariable String label_id, HttpServletRequest request) {
        // 请求路径
        String request_get_context_path = request.getContextPath();

        // 组织分页参数
        Paging paging = new Paging();
        if (!search_type.matches("^[123]?$")) {
            search_type = "1";
        }
        if ("1".equalsIgnoreCase(search_type)) { // 最新查询
            paging.setSort("id");
            paging.setOrder("DESC");
        } else if ("2".equalsIgnoreCase(search_type)) { // 热门查询
            paging.setSort("visitorNum");
            paging.setOrder("DESC");
        } else if ("3".equalsIgnoreCase(search_type)) { // 冷门查询
            paging.setSort("visitorNum");
            paging.setOrder("ASC");
        }
        if (now_page.matches("^([1-9]|[1-9][0-9]+)+$")) {
            paging.setPage(StringUtil.getInt(now_page, 1));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article", article);
        map.put("paging", paging);
        map.put("labelId", label_id);

        // 根据条件查询文章列表
        List<Article> articles = articleService.findArticleByCondition(map);
        int total = articleService.countArticleRows(map);
        Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), paging.getRows(), 5, total, request_get_context_path.concat("/articles"),
                search_type, label_id);

        // 返回数据
        ModelAndView mav = new ModelAndView();
        mav.addObject("articles", articles);
        mav.addObject("pagination", pagination);
        mav.addObject("search_type", search_type);
        mav.addObject("path", request.getContextPath());
        mav.addObject("servletPath", request_get_context_path);
        mav.addObject("label_id", label_id);
        mav.setViewName("article_list");
        return mav;
    }

    /**
     * <h2>进入文章详情页</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param id 文章ID
     * @return 转发到文章详情页
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/article/{ymd}/{id}", method = RequestMethod.GET)
    public ModelAndView getArticleById(@PathVariable(value = "ymd") String ymd, @PathVariable(value = "id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        // 查询文章详细信息，包括作者、补充、补充人信息、评论、评论人信息、回复、回复人信息、标签
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("ymd", ymd);
        Article article_detail = articleService.getArticleDetailById(param);
        if (article_detail != null && article_detail.getId() > 0) {
            // 统计并更新文章访问人数，同一个会话期内只统计一次。
            Object obj = request.getSession().getAttribute(ConstantPool.VISITS_FLAG);
            if (obj == null) {
                articleService.addVisitorNumById(id);
            }

            // 获取当前登录用户信息
            Subject current_user = SecurityUtils.getSubject();
            User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);

            // 查询当前文章相关联的其它文章
            List<Article> related_article = articleService.getSameLabelArticleById(id);

            // 返回封装数据到控制器
            mav.addObject("article", article_detail);
            mav.addObject("user", user);
            mav.addObject("path", request.getContextPath());
            mav.addObject("related_article", related_article);
            mav.setViewName("article_detail");
        } else {
            mav.setViewName("error/error");
        }
        return mav;
    }

    /**
     * 对用户回复别人的评论保存到数据库回复表（t_reply）
     *
     * @param reply
     * @return 返回JSON格式的处理信息
     * @author 深圳-小兴(504487927)
     * @version 2014-11-21
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/article/saveReply")
    @ResponseBody
    public RespMsg saveReply(Reply reply, @RequestParam Integer comment_id) {
        // 过滤并替换特殊字符
        String content = StringUtil.ifContainsSpecialStrReplace(reply.getContent());
        reply.setContent(content);
        // 获取当前登录用户信息
        Subject current_user = SecurityUtils.getSubject();
        User user_model = (User)
                current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        // 重新组织回复数据
        reply.setUserId(user_model.getId());
        reply.setCommentId(comment_id);
        reply.setPostDate(DateUtil.getNowDateTimeStr(null));
        reply.setParentId(0);
        RespMsg respMsg = new RespMsg();
        if (replyService.addReply(reply) > 0) {
            respMsg.setStatus("0000");
            respMsg.setMessage(ConfigPool.getString("respMsg.reply.SaveReplySuccess"));
        } else {
            respMsg.setStatus("9999");
            respMsg.setMessage(ConfigPool.getString("respMsg.reply.SaveReplyFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>文章或评论点赞和点踩的通用方法</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param id   文章或评论ID
     * @param type 评价类型：1文章评价,2文章点赞,3文章点踩,4评论点赞,5评论点踩
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-夕落
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/article/appraise", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg addUsefulById(@RequestParam Integer id, @RequestParam Integer type) {
        RespMsg respMsg = new RespMsg();
        // 获取当前登陆用户
        Subject current_user = SecurityUtils.getSubject();
        if (current_user == null) {
            respMsg.setStatus("9000");
            return respMsg;
        }
        User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user == null) {
            respMsg.setStatus("9000");
            return respMsg;
        }
        // 判断评价类型
        if (2 == type || 3 == type || 4 == type || 5 == type) {
            // 获取该文章对象
            Article article = articleService.getArticleById(id);
            // 用户不能对自己发表的文章进行点赞
            if (!article.getUserId().equals(user.getId())) {
                // 检查当前用户是否已经有过对这篇文章的点评
                if (!commentService.isInvolvedComment(id, user.getId())) {
                    // 增加点赞记录
                    Comment comment_model = new Comment();
                    comment_model.setType(type);
                    comment_model.setObjId(id);
                    comment_model.setUserId(user.getId());
                    comment_model.setPostDate(DateUtil.getNowDateTimeStr(null));
                    if (2 == type) {
                        comment_model.setContent("文章点赞");
                    } else if (3 == type) {
                        comment_model.setContent("文章点踩");
                    } else if (4 == type) {
                        comment_model.setContent("评论点赞");
                    } else if (5 == type) {
                        comment_model.setContent("评论点踩");
                    }
                    commentService.addComment(comment_model);
                    // 更新被赞或被踩次数
                    if (2 == type) {
                        articleService.addPraiseNum(id);
                    } else if (3 == type) {
                        articleService.addTrampleNum(id);
                    } else if (4 == type) {
                        commentService.addPraiseById(id);
                    } else if (5 == type) {
                        commentService.addTrampleById(id);
                    }
                } else {
                    respMsg.setStatus("1001");
                    respMsg.setMessage(ConfigPool.getString("respMsg.comment.AddUsefulOrUseless.RepetitiveOperation"));
                }
            } else {
                respMsg.setStatus("1000");
                respMsg.setMessage(ConfigPool.getString("respMsg.comment.AddUsefulOrUseless.SamePerson"));
            }
        } else {
            respMsg.setStatus("9999");
            respMsg.setMessage(ConfigPool.getString("respMsg.EvaluationTypeIncorrect"));
        }
        return respMsg;
    }


    /**
     * 这是一个通用的用来保存关注信息的请求处理方法
     *
     * @param attention 关注信息
     * @return 返回处理状态信息
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/article/saveAttentionInfo", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg saveAttentionInfo(Attention attention) {
        RespMsg respMsg = new RespMsg();
        Subject current_user = SecurityUtils.getSubject();
        User user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user_model != null) {
            // 判断用户是否有关注过该标签
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId", user_model.getId());
            param.put("labelId", attention.getLabelId());
            List<Attention> attentions = attentionService.find(param);
            if (attentions == null || attentions.size() == 0) {
                attention.setUserId(user_model.getId());
                if (!attentionService.save(attention)) {
                    respMsg.setStatus("9999");
                    respMsg.setMessage(ConfigPool.getString("respMsg.attention.SaveConcernInfoFailed"));
                }
            } else {
                respMsg.setStatus("2000");
                respMsg.setMessage(ConfigPool.getString("respMsg.attention.YouBeenConcernedAboutLabel"));
            }
        } else {
            respMsg.setStatus("1000");
        }
        return respMsg;
    }

}
