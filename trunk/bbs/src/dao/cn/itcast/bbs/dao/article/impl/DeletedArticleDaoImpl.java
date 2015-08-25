package cn.itcast.bbs.dao.article.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.DeletedArticleDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.article.DeletedArticle;

@Repository("deletedArticleDao")
public class DeletedArticleDaoImpl extends GenericDaoImpl<DeletedArticle> implements DeletedArticleDao {

}
