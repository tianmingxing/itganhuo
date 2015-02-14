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
package cn.itganhuo.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 文章表数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 4760983314644169654L;

	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 作者主键
	 */
	private Integer userId;
	/*
	 * 文章标题
	 */
	private String title;
	/*
	 * 文章内容
	 */
	private String content;
	/*
	 * 文章发布时间之年月日，便于做索引提高查询效率。
	 */
	private String ymd;
	/*
	 * 文章发布时间之时分秒
	 */
	private String hms;
	/*
	 * 文章发布时间，由年月日和时分秒拼接而成。
	 */
	private String postDate;
	/*
	 * 文章最后修改时间
	 */
	private String updateDate;
	/*
	 * 文章被点赞次数
	 */
	private Integer praiseNum;
	/*
	 * 文章被点踩次数
	 */
	private Integer trampleNum;
	/*
	 * 文章被浏览次数（不去重复IP访问次数）
	 */
	private Integer visitorNum;
	/*
	 * 文章主评论数量（不统计回复数量）
	 */
	private Integer answerNum;
	/*
	 * 文章发布人信息
	 */
	private User user;
	/*
	 * 文章附加标签信息
	 */
	private List<ArticleLabel> articleLabels;
	/*
	 * 文章补充信息
	 */
	private List<ArticleLine> articleLines;
	/*
	 * 文章评论信息
	 */
	private List<Comment> comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getHms() {
		return hms;
	}

	public void setHms(String hms) {
		this.hms = hms;
	}

	public String getPostDate() {
		return this.ymd.concat(" ").concat(this.hms);
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getTrampleNum() {
		return trampleNum;
	}

	public void setTrampleNum(Integer trampleNum) {
		this.trampleNum = trampleNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ArticleLabel> getArticleLabels() {
		return articleLabels;
	}

	public void setArticleLabels(List<ArticleLabel> articleLabels) {
		this.articleLabels = articleLabels;
	}

	public List<ArticleLine> getArticleLines() {
		return articleLines;
	}

	public void setArticleLines(List<ArticleLine> articleLines) {
		this.articleLines = articleLines;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Integer getVisitorNum() {
		return visitorNum;
	}

	public void setVisitorNum(Integer visitorNum) {
		this.visitorNum = visitorNum;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", userId=" + userId + ", title=" + title
				+ ", content=" + content + ", ymd=" + ymd + ", hms=" + hms
				+ ", postDate=" + postDate + ", updateDate=" + updateDate
				+ ", praiseNum=" + praiseNum + ", trampleNum=" + trampleNum
				+ ", visitorVolume=" + visitorNum + ", answerNumber="
				+ answerNum + ", user=" + user + ", articleLabels="
				+ articleLabels + ", articleLines=" + articleLines
				+ ", comments=" + comments + "]";
	}

}
