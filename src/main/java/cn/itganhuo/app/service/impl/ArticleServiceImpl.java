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

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.dao.ArticleDao;
import cn.itganhuo.app.dao.ArticleLabelDao;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;
import cn.itganhuo.app.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger log = LogManager.getLogger(ArticleServiceImpl.class.getName());

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleLabelDao articleLabelDao;

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.ArticleService#findArticleByCondition(java.util.Map)
	 */
	@Override
	public List<Article> findArticleByCondition(Map<String, Object> map) {
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
	public List<Article> getArticleByLabel(int id) {
		return articleDao.getArticleByLabel(id);
	}

}
