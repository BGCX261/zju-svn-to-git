package cn.itcast.bbs.dao.article.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.AttachmentDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.article.Attachment;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("attachmentDao")
public class AttachmentDaoImpl extends GenericDaoImpl<Attachment> implements AttachmentDao {}
