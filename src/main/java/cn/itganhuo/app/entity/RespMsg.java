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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 针对前台请求（主要是异步请求）后台进行处理后返回的状态码<br>
 * <ol>
 * <li>根据返回的状态码在前台页面上可以进行相应的DOM节点修改操作或显示相应的提示信息。</li>
 * <li>
 * 状态码说明：以四位字符表示，具体含义请自行根据业务合理定义。<br>
 * 0000处理成功<br>
 * 1000某某状态<br>
 * 1001某某状态<br>
 * 2000某某状态<br>
 * ...</li>
 * </ol>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class RespMsg implements Serializable {

	/*
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -5359114826567574500L;
	/*
	 * 消息产生时间，默认当前时间
	 */
	private Date time = new Date();
	/*
	 * 响应状态，默认处理成功
	 */
	private String status = "0000";
	/*
	 * 响应信息，默认处理成功
	 */
	private String message = "SUCCESS";
	/*
	 * 响应信息之附加信息
	 */
	private List<Object> appendInfo = new ArrayList<Object>();

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getAppendInfo() {
		return appendInfo;
	}

	public void setAppendInfo(List<Object> appendInfo) {
		this.appendInfo = appendInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appendInfo == null) ? 0 : appendInfo.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		RespMsg other = (RespMsg) obj;
		if (appendInfo == null) {
			if (other.appendInfo != null)
				return false;
		} else if (!appendInfo.equals(other.appendInfo))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{time:" + time + ", status:" + status + ", message:" + message + ", appendInfo:" + appendInfo + "}";
	}

}
