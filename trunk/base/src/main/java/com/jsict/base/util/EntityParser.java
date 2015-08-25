package com.jsict.base.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.FilterFormatNotValidException;

public class EntityParser
{
    private EntityFilter entityFilter;

    private Integer pageNo;

    private Integer pageSize;

    public void parse(Map<String, Object> params)
        throws FilterFormatNotValidException
    {
        if(params == null)
        {
            return;
        }
        String orderBy = null;
        String orderDirection = null;
        Iterator<String> it = params.keySet().iterator();
        String param = null;
        String value = null;
        entityFilter = new EntityFilter();
        while (it.hasNext())
        {
            param = it.next();
            value = (String) params.get(param);

            if(StringUtils.isBlank(value))
            {
                continue;
            }

            // check if this param is a filter item.
            String[] param_splitted = param.split("_");

            /*
             * filter naming rules: normal filter use 'filter_' prefix, means
             * 'like'; date fileds, need 2 filter, 'filter_xxx_begin' and
             * 'filter_xxx_end'. if you need exactly compare, use
             * 'filter_xxx_exactly'.
             */

            if(param_splitted[0].equalsIgnoreCase("filter"))
            {
                Op op = Op.LIKE;
                String filter = null;
                String filterType = "java.lang.String";
                if(param_splitted.length >= 2)
                {
                    filter = param_splitted[1];
                }
                if(param_splitted.length == 3 || param_splitted.length == 4)
                {
                    for (int i = 2; i < param_splitted.length; i++)
                    {
                        if(param_splitted[i].equalsIgnoreCase("begin")
                                || param_splitted[i].equalsIgnoreCase("gt")
                                || param_splitted[i].equalsIgnoreCase("from"))
                        {
                            op = Op.GREATER_EQUAL;
                        }
                        else if(param_splitted[i].equalsIgnoreCase("end")
                                || param_splitted[i].equalsIgnoreCase("lt")
                                || param_splitted[i].equalsIgnoreCase("to"))
                        {
                            op = Op.LESS_EQUAL;
                        }
                        else if(param_splitted[i].equalsIgnoreCase("exactly")
                                || param_splitted[i].equalsIgnoreCase("eq"))
                            op = Op.EQUAL;

                        if(param_splitted[i].equalsIgnoreCase("date"))
                            filterType = "java.util.Date";
                        else if(param_splitted[i].equalsIgnoreCase("datetime"))
                            filterType = "java.util.Calendar";
                        else if(param_splitted[i].equalsIgnoreCase("int"))
                            filterType = "int";
                        else if(param_splitted[i].equalsIgnoreCase("long"))
                            filterType = "long";
                    }
                }
                if(param_splitted.length > 4 || param_splitted.length < 1)
                    throw new FilterFormatNotValidException();

                if(filterType.equals("java.lang.String"))
                    entityFilter.addFilter(filter, op, value);
                if(filterType.equals("java.util.Date"))
                {
                    Date date;
                    try
                    {
                        date = DateUtils.parseDate(value,
                            new String[]{Consts.DATE_FORMAT});
                        if(date != null)
                        {
                            if(op == Op.LIKE)
                            {
                                op = Op.EQUAL;
                            }
                            else if(op == Op.GREATER_EQUAL
                                    || op == Op.GREATER_THAN)
                            {
                                date.setHours(0);
                                date.setMinutes(0);
                                date.setSeconds(0);
                            }
                            else if(op == Op.LESS_EQUAL || op == Op.LESS_THAN)
                            {
                                date = DateUtils.addDays(date, 1);
                                date.setHours(0);
                                date.setMinutes(0);
                                date.setSeconds(0);
                            }
                            entityFilter.addFilter(filter, op, date);
                        }
                    }
                    catch (ParseException e1)
                    {
                        throw new FilterFormatNotValidException(
                            "Date format invalid +" + value);
                    }
                }
                if(filterType.equals("java.util.Calander"))
                {
                    Date date;
                    try
                    {
                        date = DateUtils.parseDate(value,
                            new String[]{Consts.DATE_TIME_FORMAT});
                        if(op == Op.LIKE)
                        {
                            op = Op.EQUAL;
                        }
                        else if(op == Op.GREATER_EQUAL || op == Op.GREATER_THAN)
                        {
                            date.setHours(0);
                            date.setMinutes(0);
                            date.setSeconds(0);
                        }
                        else if(op == Op.LESS_EQUAL || op == Op.LESS_THAN)
                        {
                            DateUtils.addDays(date, 1);
                            date.setHours(0);
                            date.setMinutes(0);
                            date.setSeconds(0);
                        }
                        if(date != null)
                        {
                            entityFilter.addFilter(filter, op, date);
                        }
                    }
                    catch (ParseException e1)
                    {
                        throw new FilterFormatNotValidException(
                            "Date format invalid +" + value);
                    }
                }
                if(filterType.equals("int"))
                {
                    if(op == Op.LIKE)
                    {
                        op = Op.EQUAL;
                    }
                    try
                    {
                        entityFilter.addFilter(filter, op, Integer
                                .parseInt(value));
                    }
                    catch (NumberFormatException e2)
                    {
                        throw new FilterFormatNotValidException(filter
                                + " is a int, but value is invalid.");
                    }
                }
                if(filterType.equals("long"))
                {
                    if(op == Op.LIKE)
                    {
                        op = Op.EQUAL;
                    }
                    try
                    {
                        entityFilter.addFilter(filter, op, Long
                                .parseLong(value));
                    }
                    catch (NumberFormatException e2)
                    {
                        throw new FilterFormatNotValidException(filter
                                + " is a long, but value is invalid.");
                    }
                }
            }

            // check if this param is a sort item.

            if(param.equalsIgnoreCase("orderby"))
            {
                orderBy = value;
            }
            if(param.equalsIgnoreCase("orderDirection"))
            {
                orderDirection = value;
            }

            // check if this param is a page no.

            if(param.equalsIgnoreCase("pageNo"))
            {
                try
                {
                    this.setPageNo(Integer.parseInt(value));
                }
                catch (NumberFormatException nfe)
                {
                    this.setPageNo(1);
                }
            }

            if(param.equalsIgnoreCase("pageSize"))
            {
                try
                {
                    this.setPageSize(Integer.parseInt(value));
                }
                catch (NumberFormatException nfe)
                {
                    this.setPageSize(null);
                }

            }
        }
        if(orderBy != null)
        {
            entityFilter.addOrder(orderBy, orderDirection);
        }

    }

    public EntityFilter getEntityFilter()
    {
        return entityFilter;
    }

    public void setEntityFilter(EntityFilter entityFilter)
    {
        this.entityFilter = entityFilter;
    }

    public Integer getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

}
