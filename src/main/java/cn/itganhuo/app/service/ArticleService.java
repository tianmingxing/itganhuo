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

import java.util.List;
import java.util.Map;

import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;

/**
 * 对文章做业务操作的接口
 *
 * @author 深圳-小兴
 * @version 0.0.1-SNAPSHOT
 */
public interface ArticleService {

    /**
     * 分页按条件查询文章列表
     *
     * @param map 分页条件
     * @return 文章列表
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public List<Article> findArticleByCondition(Map<String, Object> map);

    /**
     * <h3>根据主键查询文章详情及其对应的作者</h3>
     *
     * @param id 文章ID
     * @return 一篇文章
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public Article getArticleById(int id);

    /**
     * 查询文章详细信息，包括文章、作者、补充、补充人信息、评论、评论人信息、回复、回复人信息、标签<br>
     * <ol>
     * <li>联表查询数据比较多，这个查询主要是给文章详情页用，如果你用不到这么多信息，建议还是另外写一个方法为好。</li>
     * </ol>
     *
     * @param id 文章主键
     * @return 一篇文章
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public Article getArticleDetailById(Map<String, Object> param);

    /**
     * 统计文章总数
     *
     * @return 数据总行数
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public int countArticleRows(Map<String, Object> param);

    /**
     * 会员添加一遍文章
     * <ol>
     * <li>由会员登录后在用户中心提交表单</li>
     * </ol>
     *
     * @param article 文章
     * @return 返回主键
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public int addArticle(Article article);

    /**
     * 在用户中心查询它最近发布的文章
     *
     * @param user_id 用户主键
     * @param row_num 数据行数
     * @return 文章集合
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public List<Article> getArticleByUserId(Map<String, Object> param);

    /**
     * 增加浏览次数
     *
     * @param id 文章id
     * @return 修改成功返回true，反之返回false。
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-夕落
     */
    public boolean addVisitorNumById(int id);

    /**
     * 文章和标签被保存到数据后返回两者的ID，这个方法是将文章和ID绑定到。<br>
     * 对于中间表的操作比较单一所以服务层的逻辑就全部放入到这里。
     *
     * @param articleLabel 文章和标签中间表数据
     * @return 返回主键
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public int saveArticleLabel(ArticleLabel articleLabel);

    /**
     * 文章点赞
     *
     * @param article_id 文章主键
     * @return 返回主键
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-夕落
     */
    public boolean addPraiseNum(int article_id);

    /**
     * 文章点踩
     *
     * @param article_id 文章主键
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-夕落
     */
    public boolean addTrampleNum(int article_id);

    /**
     * <h2>根据文章id得到相同标签下的文章（不包含自己）</h2>
     *
     * @param id 文章ID
     * @return 文章集合
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public List<Article> getSameLabelArticleById(int id);

    /**
     * 获取某个标签下对应的文章，默认按插入顺序，只取出5条。
     *
     * @param labelId 标签ID
     * @return 返回文章列表
     */
    public List<Article> getArticleByLabelId(Map<String, Object> param);

    /**
     * 在用户中心查询指定行数的动态文章，目前只查询当前用户有评论过的文章。
     *
     * @param param 查询参数
     * @return 返回文章列表
     */
    public List<Article> getDynamicArticleByUserId(Map<String, Object> param);

    /**
     * 在用户中心查询全部行数的动态文章，目前只查询当前用户有评论过的文章。
     * @param param 查询参数
     * @return 返回符合条件的总行数
     */
    public int countDynamicArticleRows(Map<String, Object> param);
}
