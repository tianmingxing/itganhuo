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
 * 数据字典数据封装类<br>
 * 保存一些高度重复的属性值到数据库
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-天津-小朱
 */
public class Dictionaries implements Serializable {

	private static final long serialVersionUID = 7859141307753281819L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 所属组主键
	 */
	private Integer pid;
	/*
	 * 属性名称，如果PID为0时则表示组名称
	 */
	private String attrName;
	/*
	 * 属性值
	 */
	private String attrValue;
	/*
	 * 对属性组的描述
	 */
	private String description;
	/*
	 * 排序字段
	 */
	private Integer sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
		Dictionaries other = (Dictionaries) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dictionaries [id=" + id + ", pid=" + pid + ", attrName=" + attrName + ", attrValue=" + attrValue + ", description=" + description + ", sort=" + sort + "]";
	}

}
