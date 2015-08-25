package cn.itcast.bbs.dao.article.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.VoteDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.article.Vote;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("voteDao")
public class VoteDaoImpl extends GenericDaoImpl<Vote> implements VoteDao {}
