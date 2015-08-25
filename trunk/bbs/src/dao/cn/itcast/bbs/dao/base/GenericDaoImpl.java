package cn.itcast.bbs.dao.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.exception.runtime.ItcastException;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T> {
	protected static Log log = LogFactory.getLog(GenericDaoImpl.class);

	private final Class<T> clazz;
	private final String entityName;

	@Resource(name = "hibernateTemplate")
	public final void setHibernateTemplate_0(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

	public void flushAndClearSession() {
		// FIXME 通过getCurrentSession()可以得到相应的session吗?
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.flush();
		session.clear();
	}

	public GenericDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			throw new ItcastException("没有传递类型参数T");
		}

		ParameterizedType pt = (ParameterizedType) type;
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		this.entityName = this.clazz.getName();
	}

	public int save(T t) {
		return (Integer) getHibernateTemplate().save(getEntityName(), t);
	}

	// TODO use hql: where id in (?) . and set parameter(0, idsList)??
	public void delete(int... ids) {
		if (ids == null || ids.length == 0) {
			return;
		}

		for (int id : ids) {
			Object obj = getHibernateTemplate().get(getEntityName(), id);
			if (obj != null) {
				getHibernateTemplate().delete(getEntityName(), obj);
			}
		}
	}

	public void delete(T t) {
		getHibernateTemplate().delete(getEntityName(), t);
	}

	public void update(T t) {
		getHibernateTemplate().update(getEntityName(), t);
	}
	public void merge(T t) {
		getHibernateTemplate().merge(getEntityName(), t);
	}

	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(getEntityName(), t);
	}

	public T get(int id) {
		return (T) getHibernateTemplate().get(getEntityName(), id);
	}

	public List<T> findAll() {
		String queryString = "from " + getEntityName();
		return getHibernateTemplate().find(queryString);
	}

	public int getTotal() {
		return findIntResult("select count(o) from " + getEntityName() + " o", null);
	}

	public QueryResult<T> findAll(int firstResult, int maxResult) {
		String queryString = "select count(o) from " + getEntityName() + " o";
		int total = findIntResult(queryString, new Object[] {});

		DetachedCriteria dc = DetachedCriteria.forEntityName(getEntityName());
		List<T> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<T>(total, items);
	}

	/**
	 * 获取实体的名字, 默认为持久类的全限定名
	 * 
	 * @return
	 */
	protected String getEntityName() {
		return this.entityName;
	}

	/**
	 * 查询结果列表中的第一个结果
	 */
	protected Object findFirstResult(DetachedCriteria dc) {
		List list = getHibernateTemplate().findByCriteria(dc, 0, 1);
		return list.size() > 0 ? list.get(0) : null;
	}

	protected int findIntResult(String queryString, Object value) {
		return findIntResult(queryString, new Object[] { value });
	}

	protected int findIntResult(String queryString, Object[] values) {
		Object result = findUniqueResult(queryString, values);
		if (result == null) {// TODO 会为null吗？
			return 0;
		}
		return ((Number) result).intValue();
	}

	protected Object findUniqueResult(String queryString, Object value) {
		return findUniqueResult(queryString, new Object[] { value });
	}

	protected Object findUniqueResult(String queryString, Object[] values) {
		// TODO 如果没有 findObject(String, Object[]), 并不报错, 而是去找 findObject(String, Object)
		// 如果没有记录，则返回一个空的List
		List list = getHibernateTemplate().find(queryString, values);
		return uniqueElement(list);
	}

	protected Object uniqueElement(List list) throws NonUniqueResultException {
		int size = list.size();
		if (size == 0)
			return null;
		Object first = list.get(0);
		for (int i = 1; i < size; i++) {
			if (list.get(i) != first) {
				throw new ItcastException(new NonUniqueResultException(list.size()));
			}
		}
		return first;
	}

}
