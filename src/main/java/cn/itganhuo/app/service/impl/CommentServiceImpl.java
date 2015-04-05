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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ArticleDao articleDao;

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.CommentService#addComment(cn.itganhuo.app.entity.Comment)
	 */
	@Override
	public int addComment(Comment comment) {
		comment.setPostDate(DateUtil.getNowDateTimeStr(null));
		if (comment.getType() == 2) {
			articleDao.addPraiseNumById(comment.getObjId());
		} else if (comment.getType() == 3) {
			articleDao.addTrampleNumById(comment.getObjId());
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 1);
		param.put("objId", articleId);
		param.put("userId", userId);
		List<Comment> comments = commentDao.isInvolvedComment(param);
		if (comments == null || comments.size() <= 0)
			return false;
		else
			return true;
	}
	
}
