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
import java.util.List;

/**
 * 文章数据封装类
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-08-21
 */
public class Article implements Serializable {

  private static final long serialVersionUID = 4760983314644169654L;

  private Integer id;
  private Integer userId;
  private String title;
  private String content;
  private String ymd;
  private String hms;
  private String postDate;
  private String updateDate;
  private Integer praiseNum;
  private Integer trampleNum;
  private String visitorVolume;
  private User user;
  private List<ArticleLabel> labels;
  private List<ArticleLine> lines;
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

  public String getVisitorVolume() {
    return visitorVolume;
  }

  public void setVisitorVolume(String visitorVolume) {
    this.visitorVolume = visitorVolume;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ArticleLabel> getLabels() {
    return labels;
  }

  public void setLabels(List<ArticleLabel> labels) {
    this.labels = labels;
  }

  public List<ArticleLine> getLines() {
    return lines;
  }

  public void setLines(List<ArticleLine> lines) {
    this.lines = lines;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
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
    Article other = (Article) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Article [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
        + ", yMd=" + ymd + ", Hms=" + hms + ", postDate=" + postDate + ", updateDate=" + updateDate
        + ", praiseNum=" + praiseNum + ", trampleNum=" + trampleNum + ", visitorVolume="
        + visitorVolume + ", user=" + user + ", labels=" + labels + ", lines=" + lines
        + ", comments=" + comments + "]";
  }

}
