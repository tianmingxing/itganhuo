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
 * EasyUI tree模型<br>
 * <ol>
 * <li>为方便和easyui做对接，这个类的属性设计对应easyui树控件数据格式化json。</li>
 * </ol>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class Menu implements Serializable {

	public Menu() {
		
	}
	
	public Menu(Integer id, Integer pid, String text, String state,
			Boolean checked, String iconCls, String url, Integer sort) {
		super();
		this.id = id;
		this.pid = pid;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.iconCls = iconCls;
		this.url = url;
		this.sort = sort;
	}

	private static final long serialVersionUID = -2634219513448488376L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 本表主键作外键
	 */
	private Integer pid;
	/*
	 * 菜单名称
	 */
	private String text;
	/*
	 * 菜单节点状态（''open'' 或 ''closed''，默认：''open''）
	 */
	private String state;
	/*
	 * 该节点是否被选中
	 */
	private Boolean checked;
	/*
	 * 菜单图标路径
	 */
	private String iconCls;
	/*
	 * 菜单链接到页面的地址
	 */
	private String url;
	/*
	 * 菜单自定义排序字段
	 */
	private Integer sort;
	/*
	 * 存放子级菜单信息
	 */
	private List<Menu> children;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
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
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", text=" + text + ", state=" + state + ", checked=" + checked + ", iconCls=" + iconCls + ", url="
				+ url + ", sort=" + sort + ", children=" + children + "]";
	}

}
