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
package cn.itganhuo.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.ArticleDao;
import cn.itganhuo.app.dao.ArticleLabelDao;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;
import cn.itganhuo.app.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

  private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

  private ArticleDao articleDao;
  private ArticleLabelDao articleLabelDao;

  @Autowired
  public void setArticleSubjectMapper(ArticleLabelDao articleLabelDao) {
    this.articleLabelDao = articleLabelDao;
  }

  @Autowired
  public void setArticleMapper(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.ArticleService#findArticleByCondition(cn.itganhuo .model.ArticleModel)
   */
  public List<Article> findArticleByCondition(Map<String, Object> map) {
    /*List<Article> articles = articleDao.findArticleByCondition(map);
    for (int i = 0; i < articles.size(); i++) {
      // 查询所属的标签
      String article_id = articles.get(i).getId();
      List<ArticleLabel> articleLabel = articleLabelDao
          .getArticleSubjectByArticleId(article_id);
      if (articleLabel != null && articleLabel.size() > 0) {
        articles.get(i).setArticleSubjects(articleLabel);
      }
    }
    return articles;*/
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.ArticleService#getArticleById(java.lang.String)
   */
  public Article getArticleById(String id) {
    return articleDao.getArticleById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.ArticleService#countArticleRows()
   */
  public int countArticleRows() {
    return (Integer) articleDao.countArticleRows();
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.ArticleService#addArticle(cn.itganhuo.model.ArticleModel )
   */
  public String addArticle(Article article_model) {
    /*if (article_model == null) {
      return null;
    }
    String id = UUID.randomUUID().toString();
    article_model.setId(id);
    article_model.setPost_date(new Date());
    if (articleDao.insert(article_model)) {
      return id;
    } else {
      return null;
    }*/
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.UserService#getArticleById(java.lang.String)
   */
  public List<Article> getArticleByUserId(String user_id) {
    if (!StringUtil.hasText(user_id)) {
      return null;
    }
    return articleDao.getArticleByUserId(user_id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.ArticleService#addVisitor_volumeById(java.lang.String )
   */
  public boolean addVisitor_volumeById(String id) {
    if (!StringUtil.hasText(id)) {
      return false;
    }
    return articleDao.addVisitor_volumeById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.ArticleService#insertArticleSubject(cn.itganhuo
   * .core.model.ArticleSubjectModel)
   */
  public boolean insertArticleSubject(ArticleLabel articleLabel) {
    /*if (articleLabel == null) {
      return false;
    }
    if (!StringUtil.hasText(articleLabel.getId())) {
      articleLabel.setId(UUID.randomUUID().toString());
    }
    return articleLabelDao.insert(articleLabel);*/
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.ArticleService#getArticleDetailById(java.lang .String)
   */
  public Article getArticleDetailById(String id) {
    return articleDao.getArticleDetailById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.ArticleService#addUseful(java.lang.String)
   */
  public boolean addUseful(String article_id) {
    return articleDao.addUsefulById(article_id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.ArticleService#addUseless(java.lang.String)
   */
  public boolean addUseless(String article_id) {
    return articleDao.addUselessById(article_id);
  }

  /*
   * 根据文章id得到相同标签下的文章（不包含自己）
   */
  public List<Article> getArticleBySubject(String id) {
    return articleDao.getArticleBySubject(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.core.service.ArticleService#addAnswer_number(java.lang.String )
   */
  public boolean addAnswer_number(String article_id) {
    return articleDao.addAnswer_number(article_id);
  }

}
