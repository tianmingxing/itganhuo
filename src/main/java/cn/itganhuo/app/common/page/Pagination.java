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
package cn.itganhuo.app.common.page;

/**
 * 生成分页代码
 * 
 * <ol>
 * <li>这是一个用于生成通用分页的类，使用之前需要给它传递几个参数，在页面上显示时直接代码出来即可。 因为生成的代码会在toString方法里打印出来。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014-8-22
 * @since itganhuo1.0
 */
public class Pagination {

  private int page;
  private int rows;
  private int page_num;
  private int page_total;
  private int total;
  private String url;
  private String parameters;

  /**
   * @param page
   *          当前页数
   * @param rows
   *          每页显示多少条
   * @param page_num
   *          在页面上一次展示多少页
   * @param total
   *          数据总共多少条
   * @param url
   *          点击翻页时的链接
   */
  public Pagination(int page, int rows, int page_num, int total, String url, String parameters) {
    this.page = page;
    this.rows = rows;
    this.page_num = page_num;
    this.page_total = (int) Math.ceil((total * 1.0) / (rows * 1.0));
    this.total = total;
    this.url = url;
    this.parameters = parameters;
  }

  /**
   * 前台首页：生成翻页代码
   * 
   * @author 深圳-小兴(504487927) 2014-8-26
   * @since itganhuo1.0
   * @return
   */
  public String getTurnPage() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("<div class=\"page-nav\" >\n");
    buffer.append("<ul class=\"pagination\">\n");
    // 回到首页
    if (1 == page) {
      buffer.append("<li class=\"disabled\"><a>«</a></li>\n");
    } else {
      buffer.append("<li><a href=\"").append(url).append("/").append(parameters)
          .append("/1\">«</a></li>\n");
    }

    // 显示中间页
    int length = ((page_total > page_num) ? page_num : page_total);
    int index = 1;
    if (page > page_num) {
      length += page_num * ((page - 1) / page_num);
      index += page_num * ((page - 1) / page_num);
    }
    for (int i = index; i <= length; i++) {
      if (i == page) {
        buffer.append("<li class=\"active\"><a>").append(i).append("</a></li>\n");
      } else {
        buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/")
            .append(i).append("\">").append(i).append("</a></li>\n");
      }
    }

    // 如果还有剩下页则显示...
    if (page <= (page_total - page_num)) {
      buffer.append("<li class=\"disabled\"><a>&hellip;</a></li>\n");
    }

    // 回到尾页
    if (page_total == page) {
      buffer.append("<li class=\"disabled\"><a>»</a></li>\n");
    } else {
      buffer.append("<li class=\"next\"><a href=\"").append(url).append("/").append(parameters)
          .append("/").append(page_total).append("\">»</a></li>\n");
    }

    buffer.append("</ul>\n");
    buffer.append("</div>\n");
    return buffer.toString();
  }

  /**
   * 用户中心：生成翻页代码
   * 
   * @author 深圳-小兴(504487927) 2014-8-24
   * @since itganhuo1.0
   * @return
   */
  public String getTurnPage2() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("<div class=\"pagination\" current_page=\"").append(page).append("\">\n");
    buffer.append("<ul>\n");
    // 回到首页
    if (1 == page) {
      buffer.append("<li class=\"disabled\"><a>«</a></li>\n");
    } else {
      buffer.append("<li><a href=\"").append(url).append("/").append(parameters)
          .append("/1\">«</a></li>\n");
    }

    // 显示中间页
    int length = ((page_total > page_num) ? page_num : page_total);
    int index = 1;
    if (page > page_num) {
      length += page_num * ((page - 1) / page_num);
      index += page_num * ((page - 1) / page_num);
    }
    for (int i = index; i <= length; i++) {
      if (i == page) {
        buffer.append("<li class=\"disabled\"><a>").append(i).append("</a></li>\n");
      } else {
        buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/")
            .append(i).append("\">").append(i).append("</a></li>\n");
      }
    }

    // 如果还有剩下页则显示...
    if (page <= (page_total - page_num)) {
      buffer.append("<li class=\"disabled\"><a>&hellip;</a></li>\n");
    }

    // 回到尾页
    if (page_total == page) {
      buffer.append("<li class=\"disabled\"><a>»</a></li>\n");
    } else {
      buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/")
          .append(page_total).append("\">»</a></li>\n");
    }

    buffer.append("</ul>\n");
    buffer.append("</div>\n");
    return buffer.toString();
  }

}
