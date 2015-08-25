package com.jsict.base.util;

import org.apache.commons.lang.StringUtils;

public enum Op {
    EQUAL("eq", "%s = :%s"), BETWEEN("bt", "%s between :%s and :%s"), IN("in",
            "%s in (:%s)"), LESS_THAN("lt", "%s < :%s"), GREATER_THAN("gt",
            "%s > :%s"), LESS_EQUAL("le", "%s <= :%s"), GREATER_EQUAL("ge",
            "%s >= :%s"), LIKE("like", "%s like :%s"), IS_NULL("nvl",
            "%s is null"), NOT_EQUAL("ne", "not %s = :%s"), NOT_IN("notin",
            "%s not in (:%s)");

    private final String format;

    private final String shortName;

    Op(String shortName, String format)
    {
        this.shortName = shortName;
        this.format = format;
    }

    public String toString()
    {
        return this.shortName + " --> " + this.format;
    }

    public String format(String... properties)
    {
        return String.format(format, properties);
    }

    public static Op getOp(String parameter)
    {
        if(StringUtils.isBlank(parameter))
            return null;
        Op[] ops = Op.values();
        for (Op op : ops)
        {
            if(parameter.endsWith("_" + op.shortName))
            {
                return op;
            }
        }

        return null;
    }

}