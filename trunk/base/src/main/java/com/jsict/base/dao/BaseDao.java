package com.jsict.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jsict.base.util.StringUtil;

/**
 * The Class BaseManager.
 */
@SuppressWarnings("serial")
abstract public class BaseDao<E> extends HibernateDaoSupport implements
		IBaseDao<E> {
	/** The Constant NULL_PAGE_NO. */
	protected final static Integer NULL_PAGE_NO = null;

	/** The Constant NULL_PAGE_SIZE. */
	protected final static Integer NULL_PAGE_SIZE = null;

	/** The Constant NULL_FILTER. */
	protected final static EntityFilter NULL_FILTER = null;

	/**
	 * Gets the entity class.
	 * 
	 * @return the entity class
	 */
	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		return (Class<E>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#save(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public E save(E o) {
		return (E) getHibernateTemplate().merge(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#delete(java.lang.Object)
	 */
	public void delete(E o) {
		getHibernateTemplate().delete(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#get(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public E get(Serializable id) {
		return (E) getHibernateTemplate().get(getEntityClass(), id);
	}

	/**
	 * Execute query.
	 * 
	 * @param queryString
	 *            the query string
	 * @param parameters
	 *            the parameters
	 * @return the list< e>
	 */
	@SuppressWarnings("unchecked")
	protected List<E> executeQuery(String queryString, Object... parameters) {
		return rawQuery(queryString, parameters);
	}

	protected void executeUpdate(String updateString, Object... parameters) {
		getHibernateTemplate().bulkUpdate(updateString, parameters);
		getHibernateTemplate().flush();
	}

	/**
	 * Raw query.
	 * 
	 * @param queryString
	 *            the query string
	 * @param parameters
	 *            the parameters
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	protected List rawQuery(String queryString, Object... parameters) {
		return getHibernateTemplate().find(queryString, parameters);
	}

	/**
	 * Single result.
	 * 
	 * @param queryString
	 *            the query string
	 * @param parameters
	 *            the parameters
	 * @return the object
	 */
	protected Object singleResult(String queryString, Object... parameters) {
		return singleResult(rawQuery(queryString, parameters));
	}

	@SuppressWarnings("unchecked")
	protected E singleResult(String ejbql, EntityFilter tf) {
		return (E) singleResult(executeQuery(ejbql, tf));
	}

	/**
	 * Gets the first.
	 * 
	 * @param list
	 *            the list
	 * @return the first
	 */
	protected E getFirst(List<E> list) {
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * Single result.
	 * 
	 * @param list
	 *            the list
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	protected Object singleResult(List list) {
		return list != null && list.size() > 0 ? list.get(0) : null;

	}

	/**
	 * Execute query.
	 * 
	 * @param ejbql
	 *            the ejbql
	 * @param tf
	 *            the tf
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the paged list
	 */
	@SuppressWarnings("unchecked")
	protected PagedList executeQuery(String ejbql, EntityFilter tf,
			Integer pageNo, Integer pageSize) {
		boolean withFilter = tf != null;
		String renderred = withFilter ? tf.renderQL(ejbql) : ejbql;

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(renderred);

		if (withFilter) {
			tf.setQueryParameter(query);
			if (!StringUtil.isNullString(tf.getGroupByClause())) {
				throw new RuntimeException(
						"This method does not suppot group by syntax,please use"
								+ "List executeQuery() method!");
			}
		}

		PageCalculator pc = new PageCalculator(renderred, tf, pageNo, pageSize);

		query.setFirstResult(pc.getFirstResult());
		query.setMaxResults(pc.getPageSize());

		PagedList pl = new PagedList();
		pl.setWrappedList(query.list());
		if (pageNo != null && pageSize != null) {
			pl.setPageNo(pc.getPageNo());
			pl.setPageSize(pc.getPageSize());
			pl.setTotalCount(pc.getTotalRowsCount());
			pl.setTotalPages(pc.getTotalPages());

		} else {
			pl.setPageNo(1);
			pl.setPageSize(pc.getPageSize());
			pl.setTotalCount(pc.getTotalRowsCount());
			pl.setTotalPages(pc.getTotalPages());
		}

		return pl;
	}

	@SuppressWarnings("unchecked")
	protected List executeQuery(String ejbql, EntityFilter tf) {
		boolean withFilter = tf != null;
		String renderred = withFilter ? tf.renderQL(ejbql) : ejbql;

		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(renderred);

		if (withFilter) {
			tf.setQueryParameter(query);
		}
		return query.list();
	}

	/**
	 * Binding parameters.
	 * 
	 * @param query
	 *            the query
	 * @param fromIndex
	 *            the from index
	 * @param parameters
	 *            the parameters
	 */
	private void bindingParameters(Query query, int fromIndex,
			Object... parameters) {
		if (query == null || parameters == null)
			return;
		for (int i = 0; i < parameters.length; i++) {
			query.setParameter(fromIndex + i, parameters[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#getPagedList(com.jsict.base.dao.EntityFilter ,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public PagedList<E> getPagedList(EntityFilter tf, Integer pageNo,
			Integer pageSize) {
		String entityName = ClassUtils.getShortClassName(getEntityClass());
		String alias = entityName.substring(0, 1).toLowerCase()
				+ entityName.substring(1);
		String ejbql = "select " + alias + " from " + entityName + " " + alias;

		return executeQuery(ejbql, tf, pageNo, pageSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#getPagedList(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public PagedList<E> getPagedList(Integer pageNo, Integer pageSize) {
		return getPagedList(NULL_FILTER, pageNo, pageSize);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.jsict.base.dao.IBaseManager#getFilteredList(com.jsict.base.dao.
	 * EntityFilter)
	 */
	@SuppressWarnings("unchecked")
	public PagedList<E> getFilteredList(EntityFilter tf) {
		return getPagedList(tf, NULL_PAGE_NO, NULL_PAGE_SIZE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jsict.base.dao.IBaseManager#getAllList()
	 */
	public PagedList<E> getAllList() {
		return getPagedList(NULL_FILTER, NULL_PAGE_NO, NULL_PAGE_SIZE);
	}

	/**
	 * The Class PageCalculator.
	 */
	private class PageCalculator {

		/** The ejb ql. */
		private String ejbQL;

		/** The target page no. */
		private Integer targetPageNo;

		/** The page size. */
		private Integer pageSize;

		/** The first result. */
		private int firstResult = 0;

		/** The page no. */
		private int pageNo = 0;

		/** The total rows count. */
		private int totalRowsCount = 0;

		/** The total pages. */
		private int totalPages = 0;

		/** The table filter. */
		private EntityFilter tableFilter;

		/**
		 * Instantiates a new page calculator.
		 * 
		 * @param ejbQL
		 *            the ejb ql
		 * @param tf
		 *            the tf
		 * @param targetPageNo
		 *            the target page no
		 * @param pageSize
		 *            the page size
		 */
		PageCalculator(String ejbQL, EntityFilter tf, Integer targetPageNo,
				Integer pageSize) {
			this.ejbQL = ejbQL;
			this.targetPageNo = targetPageNo;
			this.pageSize = pageSize;
			this.tableFilter = tf;
			preparePage();
		}

		/**
		 * Prepare page.
		 */
		private void preparePage() {
			String countQL = getCountQL(ejbQL);

			Query q = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(countQL);

			if (this.tableFilter != null) {
				this.tableFilter.setQueryParameter(q);
			}

			totalRowsCount = Integer.parseInt(q.uniqueResult().toString());
			if (targetPageNo == null) {
				targetPageNo = 1;
			}

			if (pageSize == null) {
				targetPageNo = 1;
				pageSize = totalRowsCount;
			}

			if (pageSize > 0) {
				totalPages = (int) Math.ceil(((double) totalRowsCount)
						/ ((double) pageSize));
			} else {
				totalPages = 1;
				pageSize = totalRowsCount;
			}
			pageNo = targetPageNo;
			if (pageNo > totalPages) {
				pageNo = totalPages;
			}
			if (pageNo < 1) {
				pageNo = totalPages;
				firstResult = 0;
			} else {
				firstResult = (pageNo - 1) * pageSize;
			}

		}

		/**
		 * Gets the first result.
		 * 
		 * @return the first result
		 */
		public int getFirstResult() {
			return firstResult;
		}

		/**
		 * Gets the page size.
		 * 
		 * @return the page size
		 */
		public int getPageSize() {
			return pageSize;
		}

		/**
		 * Gets the page no.
		 * 
		 * @return the page no
		 */
		public int getPageNo() {
			return pageNo;
		}

		/**
		 * Gets the total rows count.
		 * 
		 * @return the total rows count
		 */
		public int getTotalRowsCount() {
			return totalRowsCount;
		}

		/**
		 * Gets the total pages.
		 * 
		 * @return the total pages
		 */
		public int getTotalPages() {
			return totalPages;
		}

		/**
		 * Gets the count ql.
		 * 
		 * @param origin
		 *            the origin
		 * @return the count ql
		 */
		private String getCountQL(String origin) {
			assert (origin != null);
			return origin.replaceFirst("(select.*)?from\\b",
					"select count(\\*) from");
		}
	}
}
