package cn.itcast.bbs.service.article.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.service.article.ArticleService;
import cn.itcast.bbs.service.base.BaseService;

@Service("articleService")
@Transactional(readOnly = true)
public class ArticleServiceImpl extends BaseService implements ArticleService {

	public Article getArticle(int id) {
		return articleDao.get(id);
	}

}
