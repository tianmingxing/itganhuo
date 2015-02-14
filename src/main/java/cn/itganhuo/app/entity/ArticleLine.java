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

/**
 * 文章项表数据封装类<br>
 * 用于记录其它会员为文章作者所补充的内容，经过原作者审核同意后可展示到前台。
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ArticleLine implements Serializable {

	private static final long serialVersionUID = 6247891029436503652L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 问题补充人主键
	 */
	private Integer userId;
	/*
	 * 被补充的文章主键
	 */
	private Integer articleId;
	/*
	 * 补充内容
	 */
	private String content;
	/*
	 * 补充问题时间
	 */
	private String postDate;
	/*
	 * 问题被点赞同次数
	 */
	private Integer praiseNum;
	/*
	 * 问题被点踩次数
	 */
	private Integer trampleNum;
	/*
	 * 是否通过，1待审核2审核通过3审核不通过
	 */
	private Integer isPass = 1;
	/*
	 * 审核意见
	 */
	private String describe;
	/*
	 * 问题补充人信息
	 */
	private User user;

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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
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

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleLine other = (ArticleLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleLine [id=" + id + ", userId=" + userId + ", articleId=" + articleId + ", content=" + content + ", postDate=" + postDate + ", praiseNum="
				+ praiseNum + ", trampleNum=" + trampleNum + ", isPass=" + isPass + ", describe=" + describe + ", user=" + user + "]";
	}

}
