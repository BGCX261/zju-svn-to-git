package com.jsict.base.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    public static Date now()
    {
        return Calendar.getInstance().getTime();

    }

    public static Date setToMidNight(Date target)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(target.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date addDay(Date target)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(target.getTime());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date minusDay(Date target)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(target.getTime());
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date minusDay(Date target, int Days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(target.getTime());
        cal.add(Calendar.DATE, -Days);
        return cal.getTime();
    }

    public static Date getTimeByInt(int hours, int minutes, int seconds)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        return cal.getTime();
    }
}
