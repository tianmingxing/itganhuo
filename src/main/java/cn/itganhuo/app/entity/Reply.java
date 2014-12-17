/*
 * 1. This project consists of JAVA private school online learning community group Friends co-creator
 *  [QQ group 329232140].
 * 2. See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 3. The author does not guarantee the quality of the project and its stability, reliability, and security
 *  does not bear any responsibility.
 * 1、本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140]；
 * 2、作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams]；
 * 3、作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任。
 */
package cn.itganhuo.app.entity;

import java.io.Serializable;

/**
 * 评论回复数据封装类<br>
 * <ol>
 * <li>本类对应数据库表t_reply，主要用来存放对别人评论回复的信息。</li>
 * </ol>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class Reply implements Serializable {

	private static final long serialVersionUID = -5246329673149346098L;

	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 对应本表的主键
	 */
	private Integer parentId;
	/*
	 * 回复人主键
	 */
	private Integer userId;
	/*
	 * 被回复评论主键
	 */
	private Integer commentId;
	/*
	 * 回复内容
	 */
	private String content;
	/*
	 * 回复时间
	 */
	private String postDate;
	/*
	 * 回复人信息
	 */
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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
		Reply other = (Reply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", parentId=" + parentId + ", userId=" + userId + ", commentId=" + commentId + ", content=" + content + ", postDate="
				+ postDate + ", user=" + user + "]";
	}

}
