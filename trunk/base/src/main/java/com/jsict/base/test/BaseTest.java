/*
 * 
 */
package com.jsict.base.test;

import java.lang.reflect.TypeVariable;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * The Class BaseTest.
 */
public abstract class BaseTest extends
        AbstractTransactionalDataSourceSpringContextTests
{

    @Override
    protected String[] getConfigLocations()
    {
        return new String[]{"classpath:beanRefContext.xml",
                "classpath:plfBeanRefContext.xml"};
    }

    /**
     * Delete all tables.
     */
    protected abstract void deleteAllTables();

    protected String getOwnerName()
    {
        Connection connection = null;
        try
        {
            DataSource dataSource = (DataSource) getApplicationContext()
                    .getBean("dataSource");
            connection = dataSource.getConnection();
            return connection.getMetaData().getUserName().toUpperCase();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    protected Object getFirst(List list)
    {
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    protected void prepareTestInstance() throws Exception
    {
        setSpecificAutowireMode();
        super.prepareTestInstance();
    }

    protected void onSetUpInTransaction()
    {
        toggleConstraints(false);
        deleteAllTables();
    }

    protected abstract void setSpecificAutowireMode();

    protected void onTearDownAfterTransaction()
    {
        toggleConstraints(true);
    }

    protected void flush()
    {
        SessionFactory sessionFactory = (SessionFactory) getApplicationContext()
                .getBean("sessionFactory");
        sessionFactory.getCurrentSession().flush();
    }

    @SuppressWarnings("unchecked")
    protected <E> E attach(E obj)
    {
        SessionFactory sessionFactory = (SessionFactory) getApplicationContext()
                .getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        if(!session.contains(obj))
        {
            return (E) session.merge(obj);
        }

        return obj;
    }

    final protected void toggleConstraints(boolean enable)
    {
        String enableString = enable ? "enable" : "disable";

        String query = "DECLARE tablename varchar2(50);"
                + "alt_str varchar2(200);" + "BEGIN " + "   FOR tb IN "
                + "   (select T.table_name,T.constraint_name "
                + "   from USER_CONSTRAINTS T " + "   where t.owner like \'"
                + getOwnerName().toUpperCase() + "%\' "
                + "   and t.r_owner = \'" + getOwnerName().toUpperCase()
                + "\') " + "LOOP DBMS_OUTPUT.PUT_LINE(tb.table_name);"
                + "EXECUTE IMMEDIATE \'ALTER TABLE \'||tb.table_name||\' "
                + enableString + " constraint \'||tb.constraint_name;"
                + "END LOOP;" + "END;";
        getJdbcTemplate().execute(query);
    }
}
