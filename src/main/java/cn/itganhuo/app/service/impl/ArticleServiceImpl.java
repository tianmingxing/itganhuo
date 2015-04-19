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
package cn.itganhuo.app.service.impl;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.ArticleDao;
import cn.itganhuo.app.dao.ArticleLabelDao;
import cn.itganhuo.app.dao.AttentionDao;
import cn.itganhuo.app.entity.*;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.AttentionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger log = LogManager.getLogger(ArticleServiceImpl.class.getName());

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleLabelDao articleLabelDao;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private AttentionDao attentionDao;

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#findArticleByCondition(java.util.Map)
     */
    @Override
    public List<Article> findArticleByCondition(Map<String, Object> map) {
        log.debug("offrow=" + map.get("offrow") + ", rows=" + map.get("rows"));
        List<Article> articles = articleDao.findArticleByCondition(map);
        for (int i = 0; i < articles.size(); i++) {
            // 查询所属的标签
            int article_id = articles.get(i).getId();
            List<ArticleLabel> articleLabel = articleLabelDao.getArticleLabelById(article_id);
            if (articleLabel != null && articleLabel.size() > 0) {
                articles.get(i).setArticleLabels(articleLabel);
            }
        }
        return articles;
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#getArticleById(int)
     */
    @Override
    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#getArticleDetailById(int)
     */
    @Override
    public Article getArticleDetailById(Map<String, Object> param) {
        return articleDao.getArticleDetailById(param);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#countArticleRows()
     */
    @Override
    public int countArticleRows(Map<String, Object> param) {
        return articleDao.countArticleRows(param);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#addArticle(cn.itganhuo.app.entity.Article)
     */
    @Override
    public int addArticle(Article article) {
        if (article == null) {
            log.warn("Incoming value: article is a null value.");
            return 0;
        }
        article.setYmd(DateUtil.getNowDateTimeStr("yyyy-MM-dd"));
        article.setHms(DateUtil.getNowDateTimeStr("HH:mm:ss"));
        return articleDao.insert(article);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#getArticleByUserId(int, int)
     */
    @Override
    public List<Article> getArticleByUserId(Map<String, Object> param) {
        return articleDao.getArticleByUserId(param);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#addVisitorVolumeById(int)
     */
    @Override
    public boolean addVisitorNumById(int id) {
        if (id <= 0) {
            log.warn("Incoming value: id is a null value.");
            return false;
        }
        return articleDao.addVisitorNumById(id);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#saveArticleSubject(cn.itganhuo.app.entity.ArticleLabel)
     */
    @Override
    public int saveArticleLabel(ArticleLabel articleLabel) {
        return articleLabelDao.insert(articleLabel);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#addUseful(int)
     */
    @Override
    public boolean addPraiseNum(int article_id) {
        return articleDao.addPraiseNumById(article_id);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#addUseless(int)
     */
    @Override
    public boolean addTrampleNum(int article_id) {
        return articleDao.addTrampleNumById(article_id);
    }

    /* (non-Javadoc)
     * @see cn.itganhuo.app.service.ArticleService#getArticleByLabel(int)
     */
    @Override
    public List<Article> getSameLabelArticleById(int id) {
        return articleDao.getSameLabelArticleById(id);
    }

    @Override
    public List<Article> getArticleByLabelId(Map<String, Object> param) {
        return articleDao.getArticleByLabelId(param);
    }

    @Override
    public List<Article> getDynamicArticleByUserId(Map<String, Object> param) {
        if (!StringUtil.hasText(String.valueOf(param.get("userId")))) {
            log.warn("Query failed! Because do not get to the current user number.");
            return null;
        }
        return articleDao.getDynamicArticleByUserId(param);
    }

    @Override
    public int countDynamicArticleRows(Map<String, Object> param) {
        return articleDao.countDynamicArticleRows(param);
    }

    @Override
    public List<Map<String, Object>> queryPopularAuthors(int limit) {
        limit = (limit == 0) ? 10 : limit;
        return articleDao.queryPopularAuthors(limit);
    }

    @Override
    public ModelAndView getArticleById(String ymd, Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        // 查询文章详细信息，包括作者、补充、补充人信息、评论、评论人信息、回复、回复人信息、标签
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("ymd", ymd);
        Article article_detail = this.getArticleDetailById(param);
        if (article_detail != null && article_detail.getId() > 0) {
            // 统计并更新文章访问人数，同一个会话期内只统计一次。
            Object obj = request.getSession().getAttribute(ConstantPool.VISITS_FLAG);
            if (obj == null) {
                this.addVisitorNumById(id);
                request.getSession().setAttribute(ConstantPool.VISITS_FLAG, 1);
            }

            // 获取当前登录用户信息
            Subject current_user = SecurityUtils.getSubject();
            User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);

            // 查询当前文章相关联的其它文章
            List<Article> related_article = this.getSameLabelArticleById(id);

            //统计该文章收藏数量
            Map<String, String> param2 = new HashMap<String, String>();
            param2.put("articleId", String.valueOf(id));
            param2.put("type", String.valueOf(3));
            int collectionNumber = attentionDao.countAttentionByCondition(param2);

            //统计当前作者话题数量
            param.put("userId", article_detail.getUserId());
            int articleNumber = articleDao.countArticleRows(param);


            // 返回封装数据到控制器
            mav.addObject("articleNumber", articleNumber);
            mav.addObject("collectionNumber", collectionNumber);
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

}
