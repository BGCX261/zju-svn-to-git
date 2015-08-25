package cn.itcast.bbs.web.struts.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * 
 * @author 汤阳光 Nov 28, 2008
 */
public class ActionFormDateConverter implements Converter {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof ActionFormDate) {
			return value;
		}

		if (value instanceof Date) {
			return new ActionFormDate((Date) value);
		}

		String source = (String) value;
		try {
			return new ActionFormDate(sdf.parse(source));
		} catch (ParseException e) {
			return null;
		}
	}

}
