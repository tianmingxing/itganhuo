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
import java.util.List;

/**
 * 评论表数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = -2228593117166707020L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 操作类型，1文章评价2文章点赞3文章点踩
	 */
	private Integer type;
	/*
	 * 用来记录文章的主键，前提是对评论点赞或点踩，根据TYPE来判断数据类型
	 */
	private Integer articleId;
	/*
	 * 评论发布人主键
	 */
	private Integer userId;
	/*
	 * 评论内容
	 */
	private String content;
	/*
	 * 评论时间
	 */
	private String postDate;
	/*
	 * 记录针对本评论所赞数量，仅针对评论时有效
	 */
	private Integer praiseNum;
	/*
	 * 记录针对本评论所踩数量，仅针对评论时有效
	 */
	private Integer trampleNum;
	/*
	 * 评论人信息
	 */
	private User user;
	/*
	 * 针对评论回复信息
	 */
	private List<Reply> replys;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", type=" + type + ", articleId=" + articleId + ", content=" + content
				+ ", postDate=" + postDate + ", praiseNum=" + praiseNum + ", trampleNum=" + trampleNum + ", user=" + user
				+ ", replys=" + replys + "]";
	}

}
