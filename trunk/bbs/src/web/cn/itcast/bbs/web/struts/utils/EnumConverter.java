package cn.itcast.bbs.web.struts.utils;

import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class EnumConverter implements Converter {

	private Class<? extends Enum> enumClass;

	public EnumConverter(Class<? extends Enum> enumClass) {
		this.enumClass = enumClass;
	}

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}

		if (value.getClass().equals(enumClass)) {
			return value;
		}

		try {
			return Enum.valueOf(enumClass, (String) value);
		} catch (RuntimeException e) {
			return null;
		}
	}
}
