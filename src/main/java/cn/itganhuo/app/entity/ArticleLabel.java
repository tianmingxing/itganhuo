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
 * 文章和标签中间表数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class ArticleLabel implements Serializable {

	private static final long serialVersionUID = -1351075036355527623L;

	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 文章主键
	 */
	private Integer articleId;
	/*
	 * 标签主键
	 */
	private Integer labelId;
	/*
	 * 作者主键
	 */
	private Integer userId;
	/*
	 * 对应多个标签
	 */
	private List<Label> labels;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Label> getLabel() {
		return labels;
	}

	public void setLabel(List<Label> labels) {
		this.labels = labels;
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
		ArticleLabel other = (ArticleLabel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleLabel [id=" + id + ", articleId=" + articleId + ", labelId=" + labelId + ", userId=" + userId + ", labels=" + labels + "]";
	}

}
