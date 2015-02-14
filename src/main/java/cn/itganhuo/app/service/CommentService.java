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
package cn.itganhuo.app.service;

import cn.itganhuo.app.entity.Comment;

/**
 * 评论业务接口
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public interface CommentService {

	/**
	 * 保存一条评论
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param comment
	 *            评论数据
	 * @return 返回主键
	 */
	public int addComment(Comment comment);

	/**
	 * 根据主键查询评论详情
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            评论id
	 * @return 一条评论
	 */
	public Comment getCommentById(int id);

	/**
	 * 用户针对当前评论点踩
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            评论ID
	 * @return 返回主键
	 */
	public int addTrampleById(int id);

	/**
	 * 用户针对当前评论点赞
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            评论ID
	 * @return 返回主键
	 */
	public int addPraiseById(int id);

	/**
	 * <h2>判断当前文章有无被当前用户点评（赞或踩）过</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>用户在页面上针对评论点赞或点踩时判断它是否之前已经有过这样的操作，
	 * 我们要限制同一个人只能对同一评论点评一次。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param articleId 文章ID
	 * @param userId 用户ID
	 * @return 如果当前用户在之前已经点评过了则返回true，否则返回false。
	 */
	public boolean isInvolvedComment(int articleId, int userId);
}
