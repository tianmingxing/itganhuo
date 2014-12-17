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
 * 翻页数据封装类
 * 
 * @author 深圳-小兴(504487927) 2014-8-12
 * @since itganhuo1.0
 */
public class Page implements Serializable {

  private static final long serialVersionUID = 4222776003608625892L;
  private Integer page = 1;
  private Integer rows = 10;
  private Integer offrow = 0;
  private String sort;
  private String order;

  /**
   * @return the page
   */
  public int getPage() {
    return page;
  }

  /**
   * @param page
   *          the page to set
   */
  public void setPage(int page) {
    if (page > 0) {
      this.page = page;
    }
  }

  /**
   * @return the rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * @param rows
   *          the rows to set
   */
  public void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * @return the offrow
   */
  public int getOffrow() {
    return (page - 1) * rows;
  }

  /**
   * @return the sort
   */
  public String getSort() {
    return sort;
  }

  /**
   * @param sort
   *          the sort to set
   */
  public void setSort(String sort) {
    this.sort = sort;
  }

  /**
   * @return the order
   */
  public String getOrder() {
    return order;
  }

  /**
   * @param order
   *          the order to set
   */
  public void setOrder(String order) {
    this.order = order;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Page [page=" + page + ", rows=" + rows + ", offrow=" + offrow + ", sort=" + sort
        + ", order=" + order + "]";
  }

}
