package com.jsict.platform.util;

import java.sql.Connection;

import javax.sql.DataSource;

import com.jsict.base.test.BaseTest;

public class BasePlfTest extends BaseTest
{

    @Override
    protected void deleteAllTables()
    {
        String[] tables = new String[]{"BUSINESS_LOG", "BUSINESS_LOG_DETAIL",
                "PERMISSION", "ROLE", "ROLE_PERMISSION", "USER_ACCOUNT",
                "USER_PROFILE", "USER_ROLE", "COMPANY",};

        deleteFromTables(tables);
    }

    @Override
    protected String[] getConfigLocations()
    {
        return new String[]{"classpath:plfBeanRefContext.xml"};
    }

    @Override
    protected void setSpecificAutowireMode()
    {
        setAutowireMode(AUTOWIRE_BY_TYPE);

    }
    
    protected String getOwnerName()
    {
        Connection connection = null;
        try
        {
            DataSource dataSource = (DataSource) getApplicationContext()
                    .getBean("plfDatasource");
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


}
