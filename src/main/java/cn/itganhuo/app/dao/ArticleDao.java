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

import cn.itganhuo.app.entity.Article;

/**
 * <h2>文章表的数据层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Repository
public interface ArticleDao {

	/**
	 * 根据条件分页查询文章列表
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param map
	 *            查询条件
	 * @return
	 */
	public List<Article> findArticleByCondition(Map<String, Object> map);

	/**
	 * 根据主键查询文章详情
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            文章ID
	 * @return
	 */
	public Article getArticleById(int id);

	/**
	 * 根据条件查询文章表符合条件的记录总数
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 返回记录数
	 */
	public int countArticleRows(Map<String, Object> param);

	/**
	 * 新增一篇文章
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param article
	 * @return 新增成功返回true，反之返回false。
	 */
	public int insert(Article article);

	/**
	 * 根据用户主键到文章表中查询属于他的文章集合
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            用户主键
	 *  @param row_num
	 *            返回行数量
	 * @return 返回文章结果集
	 */
	public List<Article> getArticleByUserId(Map<String, Object> param);

	/**
	 * 增加浏览次数
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            文章ID
	 * @return 新增成功返回true，反之返回false。
	 */
	public boolean addVisitorNumById(int id);

	/**
	 * 文章点赞
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            文章id
	 * @return 新增成功返回true，反之返回false。
	 */
	public boolean addPraiseNumById(int id);

	/**
	 * 文章点踩
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-夕落
	 * @param id
	 *            文章id
	 * @return 新增成功返回true，反之返回false。
	 */
	public boolean addTrampleNumById(int id);

	/**
	 * 查询文章详细信息，包括作者、补充、评论、评论人信息、回复、回复人信息、标签
	 * <ol>
	 * <li>联表查询数据比较多，这个查询主要是给文章详情页用，如果你用不到这么多信息，建议还是另外写一个方法为好。</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param id
	 *            文章主键
	 * @return 返回一篇文章非常详情的信息
	 */
	public Article getArticleDetailById(Map<String, Object> param);

	/**
	 * 根据文章id查询相同标签下相关的文章
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param id
	 *            文章ID
	 * @return 返回文章结果集
	 */
	public List<Article> getSameLabelArticleById(int id);
	
	/**
	 * 获取某个标签下对应的文章，默认按插入顺序，只取出5条。
	 * @param labelId 标签ID
	 * @return 返回文章列表
	 */
	public List<Article> getArticleByLabelId(Map<String, Object> param);
}
