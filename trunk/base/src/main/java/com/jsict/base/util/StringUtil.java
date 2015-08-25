/**
 * @(#)StringUtil.java        2009-7-22 16:00:49
 * Administrator
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */
package com.jsict.base.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jsict.base.exception.SystemException;

/**
 * 
 * @author qipf
 * 
 */
public class StringUtil {

	/**
	 * Checks if is null string.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return true, if is null string
	 */
	public static boolean isNullString(String param) {

		return param == null || "".equals(param.trim());
	}

	/**
	 * <p>
	 * Description: The String.
	 * </p>
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the string
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String lowerFirstChar(String param) {
		if (isNullString(param)) {
			return param;
		}
		return param.substring(0, 1).toLowerCase() + param.substring(1);

	}

	/**
	 * <p>
	 * Description: The String.
	 * </p>
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the string
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String upperFirstChar(String param) {
		if (isNullString(param)) {
			return param;
		}
		return param.substring(0, 1).toUpperCase() + param.substring(1);

	}

	/**
	 * Checks if is integer.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return true, if is integer
	 */
	public static boolean isInteger(String param) {

		try {
			new Integer(param);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is long.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return true, if is long
	 */
	public static boolean isLong(String param) {

		try {
			new Long(param);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is double.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return true, if is double
	 */
	public static boolean isDouble(String param) {

		try {
			new Double(param);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the precision.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the precision
	 */
	public static int getPrecision(Integer param) {
		if (param == null) {
			return 0;
		}
		int precision = 0;
		int tempValue = param.intValue();
		while (tempValue > 1) {
			precision++;
			tempValue /= 10;
		}

		return precision;
	}

	/**
	 * Gets the precision.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the precision
	 */
	public static int getPrecision(Long param) {
		if (param == null) {
			return 0;
		}
		int precision = 0;
		long tempValue = param.longValue();
		while (tempValue > 1) {
			precision++;
			tempValue /= 10;
		}

		return precision;
	}

	/**
	 * Gets the precision.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the precision
	 */
	public static int getPrecision(Double param) {
		if (param == null || param == 0d) {
			return 0;
		}
		int precision = 0;
		long tempValue = param.longValue();
		while (tempValue > 1) {
			precision++;
			tempValue /= 10;
		}

		return precision;
	}

	/**
	 * Gets the scale.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the scale
	 */
	public static int getScale(Double param) {
		if (param == null || param == 0d) {
			return 0;
		}
		int scale = 0;
		float tempValue = param.floatValue();
		while (tempValue < 1) {
			scale++;
			tempValue *= 10;
		}

		return scale;
	}

	/**
	 * Gets the current time.
	 * 
	 * @return the current time
	 */
	public static Date getCurrentTime() {

		return new Date(System.currentTimeMillis());
	}

	/**
	 * <p>
	 * Description: The String.
	 * </p>
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the string
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String nvl(String param) {
		return param == null ? "" : param.trim();
	}

	/**
	 * Gets the start tag.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the start tag
	 */
	public static String getStartTag(String param) {
		return "<" + param + ">";
	}

	/**
	 * Gets the end tag.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the end tag
	 */
	public static String getEndTag(String param) {
		return "</" + param + ">";
	}

	/**
	 * <p>
	 * Description: The String.
	 * </p>
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the string
	 * 
	 * @throws SystemException
	 *             the system exception
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String formatParameter(String param) throws SystemException {
		if (isNullString(param)) {
			return param;
		}
		try {
			return new String(param.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	/**
	 * <p>
	 * Description: The Date.
	 * </p>
	 * 
	 * @param dateString
	 *            the date string
	 * @param dateFormat
	 *            the date format
	 * 
	 * @return the date
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static Date parseDate(String dateString, String dateFormat) {
		assert !isNullString(dateFormat) && !isNullString(dateString);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			return null;

		}

	}

	/**
	 * <p>
	 * Description: The E.
	 * </p>
	 * 
	 * @param integers
	 *            the integers
	 * 
	 * @return the e
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static <E extends Number> E max(E... integers) {
		List<E> list = Arrays.asList(integers);

		Collections.sort(list, new NumberSorter(false));

		return list.get(0);

	}

	/**
	 * <p>
	 * Description: The E.
	 * </p>
	 * 
	 * @param integers
	 *            the integers
	 * 
	 * @return the e
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static <E extends Number> E min(E... integers) {
		List<E> list = Arrays.asList(integers);

		Collections.sort(list, new NumberSorter(true));

		return list.get(0);

	}

	/**
	 * <p>
	 * Description: The E.
	 * </p>
	 * 
	 * @param integers
	 *            the integers
	 * 
	 * @return the e
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static <E extends Number> E middle(E... integers) {
		int size = integers.length;

		if (size <= 2 || size % 2 == 0)
			return null;

		List<E> list = Arrays.asList(integers);

		Collections.sort(list, new NumberSorter(true));

		return list.get((size - 1) / 2);

	}

	/**
	 * <p>
	 * Description: The Int.
	 * </p>
	 * 
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @param scale
	 *            the scale
	 * 
	 * @return the int
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static <E extends Number> int compare(E a, E b, int scale) {
		BigDecimal num1 = new BigDecimal(a.toString());
		BigDecimal num2 = new BigDecimal(b.toString());
		return num1.setScale(scale, Consts.ROUND_MODE).compareTo(
				num2.setScale(scale, Consts.ROUND_MODE));
	}

	private static class NumberSorter implements Comparator<Number> {
		private boolean asc;

		public NumberSorter(boolean asc) {
			this.asc = asc;
		}

		public int compare(Number o1, Number o2) {

			if (o1.doubleValue() > o2.doubleValue())
				return asc ? 1 : -1;
			if (o2.doubleValue() > o1.doubleValue())
				return asc ? -1 : 1;
			return 0;
		}

	}

	/**
	 * Gets the system time milis.
	 * 
	 * @return the system time milis
	 */
	public static Long getSystemTimeMilis() {
		return System.currentTimeMillis();
	}

	/**
	 * <p>
	 * Description: The Int.
	 * </p>
	 * 
	 * @param cstHour
	 *            the cst hour
	 * 
	 * @return the int
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static int cstHourToGmtHour(int cstHour) {
		if (cstHour >= 0 && cstHour <= 24)
			return (cstHour + 16) % 24;
		else
			return -1;
	}

	/**
	 * <p>
	 * Description: The Int.
	 * </p>
	 * 
	 * @param gmtHour
	 *            the gmt hour
	 * 
	 * @return the int
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static int gmtHourToCstHour(int gmtHour) {
		if (gmtHour >= 0 && gmtHour <= 24)
			return (gmtHour + 8) % 24;
		else
			return -1;
	}

	/**
	 * <p>
	 * Description: The String.
	 * </p>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the string
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String escapeXML(String str) {
		return str.replaceAll("\"", "&quot;").replaceAll("\'", "&apos;")
				.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
						">", "&gt;");
	}

	/**
	 * <p>
	 * 用于删除字符串中的空格、回车、换行、tab等特殊字符.
	 * </p>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the string
	 * 
	 * @author: JinLiang
	 * @update:[updatedate] [changer][change description]
	 */
	public static String replaceBlank(String str) {
		if (str == null) {
			return "";
		}
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		return after;
	}

	/**
	 * <p>
	 * 取得任意对象运行时的数据状态，用于log显示.
	 * </p>
	 * 
	 * 
	 * @return the string
	 * 
	 * @author: Kay King
	 * @update:[updatedate] [changer][change description]
	 */
	public static String getObjectDesString(Object o) throws Exception {
		String returnString = "\r\n";
		for (Field f : o.getClass().getDeclaredFields()) {
			boolean accessible = f.isAccessible();
			f.setAccessible(true);
			returnString = returnString + f.getName() + ":" + f.get(o) + "\r\n";
			f.setAccessible(accessible);
		}
		return returnString;
	}
	
	/**
	 * <p>Description: 百分比转数值(乘100)</p>
	 * @param param
	 * @return
	 * @author: JinLiang
	 * @update: [updatedate] [changer][change description]
	 */
	public static String percentToNumber(String param){
	    return multi(param, 100);
	}
	
	/**
	 * <p>Description: 数值转百分比(除100)</p>
	 * @param param
	 * @return
	 * @author: JinLiang
	 * @update: [updatedate] [changer][change description]
	 */
    public static String numberToPercent(String param)
    {
        return div(param, 100);
    }
	
    /* 乘整数 */
    public static String multi(String param, int rate)
    {
        try
        {
            if(!StringUtil.isNullString(param))
            {
                Double parmDouble = new Double(param);
                return Double.toString(parmDouble * rate);
            }
            else
            {
                return param;
            }
        }
        catch (Exception e)
        {
            return param;
        }
    }

    /* 除整数 */
    public static String div(String param, int rate)
    {
        try
        {
            if(!StringUtil.isNullString(param))
            {
                Double parmDouble = new Double(param);
                return Double.toString(parmDouble / rate);
            }
            else
            {
                return param;
            }
        }
        catch (Exception e)
        {
            return param;
        }
    }
}
