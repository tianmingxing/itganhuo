/*
 * Solemnly declare(2014-11-11):
 * 1 This project is a collective creation of its copyrighted Java private school online learning community (QQ group number 329 232 140) of all, is prohibited without the author's permission for commercial profit without permission;
 * 2 See the list of IT ganhuo sharing network http://www.itganhuo.cn/teams;
 * 3, the author does not guarantee the quality of the project and its stability, reliability, and security does not assume any responsibility if you get the source code for this project in some way, all the consequences of the subsequent occurrence of ego nothing to do with this group and author;
 * 4 mx.tian@qq.com have any questions, please contact us.
 *
 * 郑重声明(2014-11-11)：
 * 1、本项目属集体创作其版权归Java私塾在线学习社区（QQ群号329232140）作者所有，禁止未经作者授权擅自用于商业盈利；
 * 2、作者名单详见IT干货技术分享网http://www.itganhuo.cn/teams；
 * 3、作者不保证项目质量并对其稳定性、可靠性和安全性不承担任何责任，如果你通过某些途径获取本项目源代码，其后发生的一切后果自负与本群及作者无关；
 * 4、有任何问题请与我们联系mx.tian@qq.com。
 */
package cn.itganhuo.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 针对前台请求（主要是异步请求）后台进行处理后返回的状态码
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
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-11-21
 */
public class Message implements Serializable {

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
  private String message = "Treatment success.";
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
    Message other = (Message) obj;
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
    return "{time:" + time + ", status:" + status + ", message:" + message
        + ", appendInfo:" + appendInfo + "}";
  }

}
