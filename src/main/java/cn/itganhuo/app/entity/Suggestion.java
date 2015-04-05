package cn.itganhuo.app.entity;

import java.io.Serializable;

/**
 * 
 * <p>网站意见</p>
 * <ol>
 * <li></li>
 * </ol>
 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:08:36
 * @since   itganhuo1.0
 */
public class Suggestion implements Serializable{
	
	private Integer id;//意见ID
	
	private String title;//意见标题
	
	private String comment;//提交意见
	
	private String committer;//意见提交人
	
	private String commitDate;//意见提交时间
	
	private String treatmentSuggestion;//处理意见

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommitter() {
		return committer;
	}

	public void setCommitter(String committer) {
		this.committer = committer;
	}

	public String getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTreatmentSuggestion() {
		return treatmentSuggestion;
	}

	public void setTreatmentSuggestion(String treatmentSuggestion) {
		this.treatmentSuggestion = treatmentSuggestion;
	}

	@Override
	public String toString() {
		return "Suggestion [id=" + id + ", committer=" + committer
				+ ", commitDate=" + commitDate + ", comment=" + comment
				+ ", treatmentSuggestion=" + treatmentSuggestion + "]";
	}
}
