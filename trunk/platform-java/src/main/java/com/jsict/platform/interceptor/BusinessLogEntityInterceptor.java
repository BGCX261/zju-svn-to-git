/**
 * HistoryEntityInterceptor.java 2008-11-22 下午08:21:55 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.annotation.BusinessLogAnnotation;
import com.jsict.base.annotation.LogType;
import com.jsict.base.security.User;
import com.jsict.base.util.ClassUtil;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.entity.BusinessLog;
import com.jsict.platform.entity.BusinessLogDetail;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class BusinessLogEntityInterceptor extends EmptyInterceptor
{
    private static Map<Class, List<LogEntry>[]> _historyFields = new HashMap<Class, List<LogEntry>[]>();

    private SessionFactory sessionFactory;

    /**
     * The subsystem name
     * */
    private String subSystem;

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types)
    {

        Transaction transaction = null;
        try
        {

            boolean internalOnFlushDirty = internalHandleEntity(entity, id,
                currentState, previousState, propertyNames, types,
                CodeKey.PLF_BUSINESSLOG_TYPE_MODIFY);

            return internalOnFlushDirty;
        }
        catch (Throwable e)
        {
            //TODO make a log
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types)
    {
        Transaction transaction = null;
        try
        {
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            internalHandleEntity(entity, id, null, state, propertyNames, types,
                CodeKey.PLF_BUSINESSLOG_TYPE_DELETE);
            transaction.commit();
        }
        catch (Throwable e)
        {
            //TODO make a log
            e.printStackTrace();
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types)
    {

        try
        {
            return internalHandleEntity(entity, id, state, null, propertyNames,
                types, CodeKey.PLF_BUSINESSLOG_TYPE_NEW);
        }
        catch (Throwable e)
        {
            //TODO make a log
            //e.printStackTrace();

            return false;
        }
    }

    private LogType getLogTypeFromCodeKey(String s)
    {
        if(CodeKey.PLF_BUSINESSLOG_TYPE_DELETE.equals(s))
        {
            return LogType.WHEN_DELETE;
        }

        if(CodeKey.PLF_BUSINESSLOG_TYPE_MODIFY.equals(s))
        {
            return LogType.WHEN_UPDATE;
        }

        if(CodeKey.PLF_BUSINESSLOG_TYPE_NEW.equals(s))
        {
            return LogType.WHEN_INSERT;
        }
        return LogType.WHEN_ALL;
    }

    private static LogEntry getLogEntry(List<LogEntry> list, String fieldName)
    {
        for (LogEntry entry : list)
        {
            if(StringUtils.equals(entry.fieldName, fieldName))
                return entry;
        }
        return null;
    }

    private boolean internalHandleEntity(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types, String type)
    {

        boolean changed = false;
        Class clazz = HibernateProxyHelper
                .getClassWithoutInitializingProxy(entity);
        LogType logTypeFromCodeKey = getLogTypeFromCodeKey(type);

        List<LogEntry> historyFields = getHistoryFields(clazz,
            logTypeFromCodeKey);

        if(historyFields.size() > 0)
        {
            BusinessLog businessLog = new BusinessLog();
            businessLog.setCreatedDate(new Timestamp(new Date().getTime()));
            businessLog.setObjectName(clazz.getCanonicalName());
            businessLog.setType(type);
            businessLog.setSubSystem(subSystem);
            businessLog.setOperatorId(getUserId());

            //        businessLog.setBusinessLogDetails(new HashSet<BusinessLogDetail>());
            for (int i = 0; i < propertyNames.length; i++)
            {
                String propertyName = propertyNames[i];
                LogEntry logEntry = getLogEntry(historyFields, propertyName);
                if(logEntry != null)
                {
                    Object currentValue = currentState == null ? null
                            : currentState[i];
                    Object previousValue = previousState == null ? null
                            : previousState[i];
                    if(logEntry.businessLogAnnotation != null)
                    {
                        //如果是对象
                        String property = logEntry.businessLogAnnotation
                                .property();

                        try
                        {
                            currentValue = BeanUtils.getProperty(currentValue,
                                property);
                            previousValue = BeanUtils.getProperty(
                                previousValue, property);
                        }
                        catch (Exception e)
                        {

                            currentValue = null;
                            previousValue = null;
                        }

                    }
                    if(currentValue == null && previousValue == null)
                    {
                        //no change
                        continue;
                    }

                    if(currentValue != null
                            && currentValue.equals(previousValue)
                            || previousValue != null
                            && previousValue.equals(currentValue))
                        continue;
                    if(currentValue != null
                            && StringUtils.isBlank(currentValue.toString())
                            && previousValue == null)
                        continue;
                    if(previousValue != null
                            && StringUtils.isBlank(previousValue.toString())
                            && currentValue == null)
                        continue;
                    changed = true;
                    sessionFactory.getCurrentSession().save(businessLog);
                    BusinessLogDetail businessLogDetail = new BusinessLogDetail();
                    businessLogDetail.setBusinessLog(businessLog);
                    businessLogDetail.setFieldName(propertyName);
                    if(currentValue != null)
                        businessLogDetail.setNewValue(currentValue.toString());
                    if(previousValue != null)
                        businessLogDetail.setOriginValue(previousValue
                                .toString());
                    if(currentValue != null)
                        businessLogDetail.setType(getDataType(currentValue
                                .getClass()));
                    if(previousValue != null)
                        businessLogDetail.setType(getDataType(previousValue
                                .getClass()));

                    sessionFactory.getCurrentSession().save(businessLogDetail);

                }
            }
            if(changed)
            {

                sessionFactory.getCurrentSession().flush();
            }
        }

        //        if(true)
        //            throw new RuntimeException();
        return false;
    }

    private String getDataType(Class clazz)
    {
        return clazz.getSimpleName();
    }

    private String getUserId()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        return String.valueOf(user.getUserId());
    }

    private static List<LogEntry> getHistoryFields(Class clazz, LogType logType)
    {
        List<LogEntry> deleteFieldList = new ArrayList<LogEntry>();
        List<LogEntry> insertFieldList = new ArrayList<LogEntry>();
        List<LogEntry> updateFieldList = new ArrayList<LogEntry>();

        int logTypeIndex = 0;
        switch (logType)
        {

            case WHEN_DELETE:
                logTypeIndex = 0;
                break;

            case WHEN_INSERT:
                logTypeIndex = 1;
                break;

            case WHEN_UPDATE:
                logTypeIndex = 2;
                break;

            default:
                logTypeIndex = 0;
                break;
        }

        if(!_historyFields.containsKey(clazz))
        {
            BusinessLogAnnotation classAnnotation = (BusinessLogAnnotation) clazz
                    .getAnnotation(BusinessLogAnnotation.class);

            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields)
            {
                BusinessLogAnnotation businessLogAnnotation = field
                        .getAnnotation(BusinessLogAnnotation.class);
                LogType[] affectedLogType = (businessLogAnnotation != null ? businessLogAnnotation
                        .logType()
                        : classAnnotation != null ? classAnnotation.logType()
                                : new LogType[0]);
                List<LogType> logTypeList = Arrays.asList(affectedLogType);

                if(affectedLogType.length > 0
                        && !logTypeList.contains(LogType.WHEN_NONE)
                        && ((ClassUtil.isSimpleType(field.getType())) || (ClassUtil
                                .isSimpleType(field.getType()) == false
                                && businessLogAnnotation != null && StringUtils
                                .isBlank(businessLogAnnotation.property()) == false)))
                {
                    boolean all = logTypeList.contains(LogType.WHEN_ALL);

                    if(logTypeList.contains(LogType.WHEN_DELETE) || all)
                        deleteFieldList.add(new LogEntry(field.getName(),
                            businessLogAnnotation));

                    if(logTypeList.contains(LogType.WHEN_INSERT) || all)
                        insertFieldList.add(new LogEntry(field.getName(),
                            businessLogAnnotation));

                    if(logTypeList.contains(LogType.WHEN_UPDATE) || all)
                        updateFieldList.add(new LogEntry(field.getName(),
                            businessLogAnnotation));

                }
            }
            _historyFields.put(clazz, new List[]{deleteFieldList,
                    insertFieldList, updateFieldList});
        }

        return _historyFields.get(clazz)[logTypeIndex];
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param subSystem
     *            The subSystem to set.
     */
    @Required
    public void setSubSystem(String subSystem)
    {
        this.subSystem = subSystem;
    }

}

class LogEntry
{
    String fieldName;

    BusinessLogAnnotation businessLogAnnotation;

    public LogEntry(String fieldName,
            BusinessLogAnnotation businessLogAnnotation)
    {
        super();
        this.fieldName = fieldName;
        this.businessLogAnnotation = businessLogAnnotation;
    }

}
