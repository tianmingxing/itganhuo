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

/**
 * 用户数据封装类
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-07-07
 */
public class User implements Serializable {

  private static final long serialVersionUID = -5147036028865697127L;
  private Integer id;
  private String account;
  private String password;
  private String salt;
  private Integer isLock = 1;
  private String nickname;
  private Integer sex;
  private String email;
  private Integer qq;
  private Integer phone;
  private String tel;
  private String postDate;
  private Integer type = 1;
  private String lastLoginIp;
  private String lastLoginDate;
  private Integer isValidateEmail = 0;
  private String emailValidateCode;
  private String emailValideteDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public Integer getIsLock() {
    return isLock;
  }

  public void setIsLock(Integer isLock) {
    this.isLock = isLock;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getQq() {
    return qq;
  }

  public void setQq(Integer qq) {
    this.qq = qq;
  }

  public Integer getPhone() {
    return phone;
  }

  public void setPhone(Integer phone) {
    this.phone = phone;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getPostDate() {
    return postDate;
  }

  public void setPostDate(String postDate) {
    this.postDate = postDate;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public String getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(String lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public Integer getIsValidateEmail() {
    return isValidateEmail;
  }

  public void setIsValidateEmail(Integer isValidateEmail) {
    this.isValidateEmail = isValidateEmail;
  }

  public String getEmailValidateCode() {
    return emailValidateCode;
  }

  public void setEmailValidateCode(String emailValidateCode) {
    this.emailValidateCode = emailValidateCode;
  }

  public String getEmailValideteDate() {
    return emailValideteDate;
  }

  public void setEmailValideteDate(String emailValideteDate) {
    this.emailValideteDate = emailValideteDate;
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
    User other = (User) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", account=" + account + ", password=" + password + ", salt=" + salt
        + ", isLock=" + isLock + ", nickname=" + nickname + ", sex=" + sex + ", email=" + email
        + ", qq=" + qq + ", phone=" + phone + ", tel=" + tel + ", postDate=" + postDate + ", type="
        + type + ", lastLoginIp=" + lastLoginIp + ", lastLoginDate=" + lastLoginDate
        + ", isValidateEmail=" + isValidateEmail + ", emailValidateCode=" + emailValidateCode
        + ", emailValideteDate=" + emailValideteDate + "]";
  }

}