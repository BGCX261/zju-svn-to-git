package com.jsict.base.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.jsict.base.util.Op;
import com.jsict.base.util.Turple;

public @SuppressWarnings("serial")
class EntityFilter implements Serializable
{

    private List<String> filterName = new ArrayList<String>();

    private List<Op> filterOp = new ArrayList<Op>();

    @SuppressWarnings("unchecked")
    private List filterValue = new ArrayList();

    private List<String> filterType = new ArrayList<String>();

    private List<String> orderName = new ArrayList<String>();

    private List<String> orderDirection = new ArrayList<String>();

    private List<String> filterLogic = new ArrayList<String>();

    private String groupByClause = null;

    private boolean autoLike = true;

    private boolean autoUpperCase = true;

    private static final Pattern FROM_PATTERN = Pattern.compile(
        "(^|\\s)(from)\\s", Pattern.CASE_INSENSITIVE);

    private static final Pattern ORDER_PATTERN = Pattern.compile(
        "\\s(order)(\\s)+by\\s", Pattern.CASE_INSENSITIVE);

    private static final Pattern WHERE_PATTERN = Pattern.compile(
        "\\s(where)\\s", Pattern.CASE_INSENSITIVE);

    private boolean autoAddWhere = true;

    private final static Log log = LogFactory.getLog(EntityFilter.class);

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String groupByClause.
     */
    public String getGroupByClause()
    {
        return groupByClause;
    }

    public EntityFilter()
    {
        super();
    }

    public void enableAutoLike()
    {
        setAutoLike(true);
    }

    public void disableAutoLike()
    {
        setAutoLike(false);
    }

    public void enableAutoUpperCase()
    {
        setAutoUpperCase(true);
    }

    public void disableAutoUpperCase()
    {
        setAutoUpperCase(false);
    }

    public void disableAutoAddWhere()
    {
        setAutoAddWhere(false);
    }

    public void enableAutoAddWhere()
    {
        setAutoAddWhere(true);
    }

    @SuppressWarnings("unchecked")
    private EntityFilter addFilter(String name, Op op, Object value,
            boolean logic)
    {
        if(value instanceof String)
        {
            String stringValue = (String) value;

            if(!StringUtils.isEmpty(stringValue))
            {
                filterName.add(name.trim());
                filterType.add("STRING");
                filterOp.add(op);
                filterValue.add(value);
            }
        }
        else if(value != null)
        {
            filterName.add(name.trim());

            if(value instanceof Date)
            {
                filterType.add("DATE");
            }
            else if(value instanceof Calendar)
            {
                filterType.add("CALANDER");
            }
            else if(value instanceof Timestamp)
            {
                filterType.add("TIMESTAMP");
            }
            else if(value.getClass().isArray())
            {
                filterType.add("ARRAY");
            }
            else if(value instanceof Collection)
            {
                filterType.add("LIST");
            }
            else
            {
                filterType.add(null);
            }
            filterOp.add(op);
            filterValue.add(value);

        }
        else
        {
            //提前返回
            return this;
        }

        if(logic)
        {
            filterLogic.add("AND");
        }
        else
        {
            filterLogic.add("OR");
        }

        return this;
    }

    public EntityFilter addFilter(String name, Op op, Object value)
    {
        return addFilter(name, op, value, true);
    }

    public EntityFilter addOrFilter(String name, Op op, Object value)
    {
        return addFilter(name, op, value, false);
    }

    public EntityFilter addFilter(String name, Object value)
    {
        return addFilter(name, Op.EQUAL, value);
    }

    @SuppressWarnings("unchecked")
    public EntityFilter addFilter(String customFilter)
    {
        if(!StringUtils.isEmpty(customFilter))
        {
            filterName.add(customFilter);
            filterOp.add(null);
            filterValue.add(null);
            filterType.add("CUSTOM");
            filterLogic.add("AND");
        }
        return this;
    }

    public EntityFilter addOrder(String name)
    {
        return addOrder(name, "ASC");
    }

    public EntityFilter addOrder(String name, String direction)
    {
        if(isOrderExist(name))
        {
            removeOrder(name);
        }
        if((name != null) && (name.length() > 0))
        {
            orderName.add(name.trim());
            String tmpDirection = StringUtils.upperCase(direction);
            orderDirection.add(tmpDirection.equals("DESC") ? "DESC" : "ASC");
        }
        return this;
    }

    public EntityFilter addGroupBy(String... name)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < name.length; i++)
        {
            if(i == 0)
            {
                buf.append(name[i]);
            }
            else
            {
                buf.append(", ");
                buf.append(name[i]);
            }
        }
        groupByClause = "group by " + buf.toString();
        return this;
    }

    public void clear()
    {
        filterName.clear();
        filterOp.clear();
        filterValue.clear();
        filterType.clear();
        orderName.clear();
        orderDirection.clear();
    }

    public int getFilterIndex(String name)
    {
        if((name == null) || name.equals(""))
        {
            return -1;
        }
        for (int i = 0; i < filterName.size(); i++)
        {
            if(name.equalsIgnoreCase((String) filterName.get(i)))
            {
                return i;
            }
        }
        return -1;
    }

    public String getFilterName(int pos)
    {
        return (String) filterName.get(pos);
    }

    public Op getFilterOp(int pos)
    {
        return filterOp.get(pos);
    }

    public int getFilterSize()
    {
        return filterName.size();
    }

    public String getFilterType(int pos)
    {
        return (String) filterType.get(pos);
    }

    public Object getFilterValue(int pos)
    {
        return filterValue.get(pos);
    }

    public String getOrderDirection(int pos)
    {
        return (String) orderDirection.get(pos);
    }

    public String getOrderName(int pos)
    {
        return (String) orderName.get(pos);
    }

    public int getOrderSize()
    {
        return orderName.size();
    }

    private String setOrders(String origin)
    {
        StringBuffer buf = new StringBuffer();

        if(getOrderSize() > 0)
        {
            for (int i = 0; i < getOrderSize(); i++)
            {
                String fName = getOrderName(i);
                if(fName.length() == 0)
                {
                    continue;
                }

                // add
                if(buf.length() > 0)
                {
                    buf.append(", ");
                }
                buf.append(fName);
                buf.append(" ");
                buf.append(getOrderDirection(i));
            }

            if(!ORDER_PATTERN.matcher(origin).find())
            {
                buf.insert(0, " ORDER BY ");
            }

        }
        buf.insert(0, origin);

        return buf.toString();
    }

    private String setRestrictions(String origin)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < getFilterSize(); i++)
        {
            String fName = getFilterName(i);
            if(fName.length() == 0
                    && !"CUSTOM".equalsIgnoreCase(getFilterType(i)))
            {
                continue;
            }

            // add
            if(buf.length() > 0)
            {
                buf.append(" " + filterLogic.get(i) + " ");
            }

            if("CUSTOM".equalsIgnoreCase(getFilterType(i)))
            {
                buf.append(getFilterName(i));
                buf.append(" ");
                continue;
            }
            String p1 = null;
            String p2 = null;
            String p3 = null;
            if(getFilterOp(i) == Op.LIKE
                    && "STRING".equalsIgnoreCase(getFilterType(i))
                    && isAutoUpperCase())
            {
                p1 = "upper(" + fName + ")";
            }
            else
            {
                p1 = fName;
            }
            if(getFilterValue(i) != null)
            {
                if(getFilterOp(i) == Op.BETWEEN)
                {
                    p2 = getParameterName(getFilterName(i)) + "_from_" + i;
                    p3 = getParameterName(getFilterName(i)) + "_to_" + i;
                }
                else
                {
                    p2 = getParameterName(getFilterName(i), i);
                }
            }

            buf.append(getFilterOp(i).format(p1, p2, p3));
        }

        if(getFilterSize() > 0)
        {
            if(!WHERE_PATTERN.matcher(origin).find())
            {
                buf.insert(0, " where ");
            }
            else
            {
                buf.insert(0, " " + filterLogic.get(0) + " ");
            }

        }
        buf.append(" ");
        buf.insert(0, origin);
        return buf.toString();
    }

    public String setGroupBy(String ql)
    {
        if(groupByClause == null || groupByClause.trim() == "")
            return ql;
        return ql + groupByClause;
    }

    public String renderQL(String ejbql)
    {
        String rendered = setRestrictions(ejbql);
        String re = setGroupBy(rendered);
        String ql = setOrders(re);
        log.debug(ql);
        return ql;
    }

    private String getParameterName(String filterName, int index)
    {
        return getParameterName(filterName) + "_" + index;
    }

    private String getParameterName(String filterName)
    {
        return StringUtils.replace(filterName, ".", "_");
    }

    public boolean isFilterExist(String aFilterName)
    {
        for (int i = 0; i < filterName.size(); i++)
        {
            if(((String) filterName.get(i)).equalsIgnoreCase(aFilterName))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isOrderExist(String aOrderName)
    {
        for (int i = 0; i < orderName.size(); i++)
        {
            if(((String) orderName.get(i)).equalsIgnoreCase(aOrderName))
            {
                return true;
            }
        }
        return false;
    }

    public void removeFilter(int pos)
    {
        if(pos < 0 || pos >= getFilterSize())
            return;
        filterName.remove(pos);
        filterOp.remove(pos);
        filterValue.remove(pos);
        filterType.remove(pos);
    }

    public void removeFilter(String name)
    {
        if((name == null) || name.equals(""))
        {
            return;
        }
        for (int i = 0; i < filterName.size(); i++)
        {
            if(name.equalsIgnoreCase((String) filterName.get(i)))
            {
                removeFilter(i);
            }
        }
    }

    public void removeOrder(int pos)
    {
        if(pos < 0 || pos >= getOrderSize())
            return;
        orderName.remove(pos);
        orderDirection.remove(pos);
    }

    public void removeOrder(String name)
    {
        while (isOrderExist(name))
        {
            int pos = orderName.indexOf(name);
            removeOrder(pos);
        }
    }

    public void setQueryParameter(Query query)
    {
        if(query == null)
        {
            return;
        }

        // int id = paraIndex;
        for (int i = 0; i < getFilterSize(); i++)
        {
            if(getFilterValue(i) == null)
            {
                continue;
            }
            String fType = getFilterType(i);
            String fName = getParameterName(getFilterName(i), i);
            if("DATE".equalsIgnoreCase(fType))
            {
                query.setParameter(fName, (Date) getFilterValue(i));
            }
            else if("CALENDAR".equalsIgnoreCase(fType))
            {
                query.setParameter(fName, (Calendar) getFilterValue(i));
            }
            else if("TIMESTAMP".equalsIgnoreCase(fType))
            {
                query.setParameter(fName, (Timestamp) getFilterValue(i));
            }
            else if("STRING".equalsIgnoreCase(fType))
            {
                String d = (String) getFilterValue(i);
                d = StringUtils.trimToEmpty(d);
                if(getFilterOp(i) == Op.LIKE)
                {
                    d = StringUtils.replace(d, "*", "%");
                    if(isAutoUpperCase())
                    {
                        d = d.toUpperCase();
                    }
                    if(isAutoLike())
                    {
                        d = "%" + d + "%";
                    }
                }
                query.setParameter(fName, d);
            }
            else if("CUSTOM".equalsIgnoreCase(fType))
            {
                continue;
            }
            else if("LIST".equalsIgnoreCase(fType))
            {
                query.setParameterList(fName, (Collection) getFilterValue(i));
            }
            else if("ARRAY".equalsIgnoreCase(fType))
            {
                query.setParameterList(fName, (Object[]) getFilterValue(i));
            }
            else if(getFilterValue(i) instanceof Turple)
            {
                Turple turple = (Turple) getFilterValue(i);
                query.setParameter(getParameterName(getFilterName(i))
                        + "_from_" + i, turple.from);
                query.setParameter(getParameterName(getFilterName(i)) + "_to_"
                        + i, turple.to);
            }
            else
            {
                query.setParameter(fName, getFilterValue(i));
            }
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < getFilterSize(); i++)
        {
            String fName = getFilterName(i);
            if(fName.length() == 0)
            {
                continue;
            }

            // add
            if(buf.length() > 0)
            {
                buf.append(",");
            }
            buf.append(fName);
            buf.append(" ");
            buf.append(getFilterOp(i));
            buf.append(" ");
            buf.append("`");
            buf.append(getFilterValue(i));
            buf.append("`");
        }
        buf.insert(0, "[");
        buf.append("] order by [");
        for (int i = 0; i < getOrderSize(); i++)
        {
            String fName = getOrderName(i);
            if(fName.length() == 0)
            {
                continue;
            }

            // add
            if(i > 0)
            {
                buf.append(", ");
            }
            buf.append(fName);
            buf.append(" ");
            buf.append(getOrderDirection(i));
        }
        buf.append("]");
        return buf.toString();
    }

    public void updateFilter(String name, Op op, Object value)
    {
        removeFilter(name);
        addFilter(name, op, value);
    }

    public boolean isAutoLike()
    {
        return autoLike;
    }

    public void setAutoLike(boolean b)
    {
        autoLike = b;
    }

    /**
     * @return
     */
    public boolean isAutoUpperCase()
    {
        return autoUpperCase;
    }

    /**
     * @param b
     */
    public void setAutoUpperCase(boolean b)
    {
        autoUpperCase = b;
    }

    /**
     * @return
     */
    public boolean isAutoAddWhere()
    {
        return autoAddWhere;
    }

    /**
     * @param b
     */
    public void setAutoAddWhere(boolean b)
    {
        autoAddWhere = b;
    }

    List<String> getFilterName()
    {
        return filterName;
    }

    List<Op> getFilterOp()
    {
        return filterOp;
    }

    @SuppressWarnings("unchecked")
    List getFilterValue()
    {
        return filterValue;
    }

    List<String> getFilterType()
    {
        return filterType;
    }

    List<String> getOrderName()
    {
        return orderName;
    }

    List<String> getOrderDirection()
    {
        return orderDirection;
    }
}