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
package cn.itganhuo.app.common.page;

/**
 * <h2>生成前台页面分页代码</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>这是一个用于生成通用分页的类，使用之前需要给它传递几个参数，在页面上显示时直接代码出来即可。 
 * 因为生成的代码会在toString方法里打印出来。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
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
	 *            当前页数
	 * @param rows
	 *            每页显示多少条
	 * @param page_num
	 *            在页面上一次展示多少页
	 * @param total
	 *            数据总共多少条
	 * @param url
	 *            点击翻页时的链接
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
	 * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
	 * @return
	 */
	public String getTurnPage() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<div class=\"page-nav\" >\n");
		buffer.append("<ul class=\"pagination\">\n");
		// 回到首页
		if (1 == page) {
			buffer.append("<li class=\"disabled\"><a>«</a></li>\n");
		} else {
			buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/1\">«</a></li>\n");
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
				buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/").append(i).append("\">").append(i).append("</a></li>\n");
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
			buffer.append("<li class=\"next\"><a href=\"").append(url).append("/").append(parameters).append("/").append(page_total).append("\">»</a></li>\n");
		}

		buffer.append("</ul>\n");
		buffer.append("</div>\n");
		return buffer.toString();
	}

	/**
	 * 用户中心：生成翻页代码
	 * 
	 * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
	 * @return
	 */
	public String getTurnPage2() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<div class=\"pagination\" current_page=\"").append(page).append("\">\n");
		buffer.append("<ul>\n");
		// 回到首页
		if (1 == page) {
			buffer.append("<li class=\"disabled\"><a>«</a></li>\n");
		} else {
			buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/1\">«</a></li>\n");
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
				buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/").append(i).append("\">").append(i).append("</a></li>\n");
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
			buffer.append("<li><a href=\"").append(url).append("/").append(parameters).append("/").append(page_total).append("\">»</a></li>\n");
		}

		buffer.append("</ul>\n");
		buffer.append("</div>\n");
		return buffer.toString();
	}

}
