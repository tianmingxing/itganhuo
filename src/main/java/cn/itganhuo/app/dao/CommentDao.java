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
package cn.itganhuo.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Comment;

/**
 * 评论表数据层接口
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-夕落，深圳-小兴
 */
@Repository
public interface CommentDao {

	/**
	 * 插入一条评论数据到t_comment
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param comment
	 * @return
	 */
	public int insert(Comment comment);

	/**
	 * 根据主键查询评论详情
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            评论id
	 * @return
	 */
	public Comment getCommentById(int id);

	/**
	 * 评论点赞
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            评论id
	 * @return
	 */
	public int addPraiseById(int id);

	/**
	 * 评论点踩
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            评论id
	 * @return
	 */
	public int addTrampleById(int id);

	/**
	 * 查询当前文章是否有被当前用户赞或踩的记录
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @return 一条评论数据
	 */
	public List<Comment> isInvolvedComment(Map<String, Object> param);

}
