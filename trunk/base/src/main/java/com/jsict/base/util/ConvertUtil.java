package com.jsict.base.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.proxy.HibernateProxyHelper;

import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;

/**
 * @author qipf
 */
public class ConvertUtil {

	public static boolean domain2entity(Object domain, Object entity)
			throws SystemException {
		boolean hasError = false;

		if (domain == null || entity == null) {
			return hasError;
		}

		try {
			Class domainClazz = domain.getClass();
			Class entityClazz = HibernateProxyHelper
					.getClassWithoutInitializingProxy(entity);
			Method[] entityMethods = entityClazz.getMethods();

			for (int i = 0; i < entityMethods.length; i++) {

				Method entityMethod = entityMethods[i];
				String entityMethodName = entityMethod.getName();
				if (!entityMethodName.startsWith("get")) {
					continue;
				}

				String peropertyName = StringUtil
						.lowerFirstChar(entityMethodName
								.replaceFirst("get", ""));
				Class propertyClazz = entityMethod.getReturnType();
				if (!isPrimitiveType(propertyClazz)) {
					continue;
				}

				String domainMethodName = "get"
						+ StringUtil.upperFirstChar(peropertyName);
				Method domainMethod = null;
				try {
					domainMethod = domainClazz.getMethod(domainMethodName);
				} catch (Exception e) {
					continue;
				}

				Annotation[] domainAnnotations = domainMethod.getAnnotations();
				if (domainAnnotations.length == 0) {
					continue;
				}
				Annotation domainAnnotation = domainAnnotations[0];
				Text text = (Text) domainAnnotation;
				String propertyLabel = text.label();
				String propertyFormat = text.format();
				boolean convert = text.convert();
				if (!convert) {
					continue;
				}

				String propertyValue = (String) domainMethod.invoke(domain);

				boolean primaryKey = false;
				boolean nullable = false;
				int length = 0;
				int precision = 0;
				int scale = 0;

				Annotation[] entityAnnotations = entityMethod.getAnnotations();
				for (int j = 0; j < entityAnnotations.length; j++) {
					Annotation entityAnnotation = entityAnnotations[j];
					if (entityAnnotation instanceof Id) {
						primaryKey = true;
					} else if (entityAnnotation instanceof Column) {
						Column column = (Column) entityAnnotation;
						nullable = column.nullable();
						length = column.length();
						precision = column.precision();

						// scale = column.scale();
						scale = Integer.MAX_VALUE;
					}
				}

				if (primaryKey) {
					continue;
				}
				if (propertyClazz.isAssignableFrom(String.class)) {
					if (StringUtil.isNullString(propertyValue)) {
						if (!nullable) {
							ProjectContext.getErrorList().add(
									new MessageInfo("001",
											new String[] { propertyLabel }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, propertyValue);
						}
					} else {
						if (propertyValue.length() > length) {
							ProjectContext.getErrorList().add(
									new MessageInfo("002", new String[] {
											propertyLabel, length + "" }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, propertyValue);
						}
					}
				} else if (propertyClazz.isAssignableFrom(Integer.class)) {
					Integer tempValue = null;
					if (StringUtil.isNullString(propertyValue)) {
						if (!nullable) {
							ProjectContext.getErrorList().add(
									new MessageInfo("001",
											new String[] { propertyLabel }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					} else {
						try {
							tempValue = new Integer(propertyValue);
						} catch (Exception e) {
							ProjectContext.getErrorList().add(
									new MessageInfo("003",
											new String[] { propertyLabel }));
							hasError = true;
						}
						if (StringUtil.getPrecision(tempValue) > precision) {
							ProjectContext.getErrorList().add(
									new MessageInfo("004", new String[] {
											propertyLabel, precision + "" }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					}
				} else if (propertyClazz.isAssignableFrom(Long.class)) {
					Long tempValue = null;
					if (StringUtil.isNullString(propertyValue)) {
						if (!nullable) {
							ProjectContext.getErrorList().add(
									new MessageInfo("001",
											new String[] { propertyLabel }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					} else {
						try {
							tempValue = new Long(propertyValue);
						} catch (Exception e) {
							ProjectContext.getErrorList().add(
									new MessageInfo("003",
											new String[] { propertyLabel }));
							hasError = true;
						}
						if (StringUtil.getPrecision(tempValue) > precision) {
							ProjectContext.getErrorList().add(
									new MessageInfo("004", new String[] {
											propertyLabel, precision + "" }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					}
				} else if (propertyClazz.isAssignableFrom(Double.class)) {
					Double tempValue = null;
					if (StringUtil.isNullString(propertyValue)) {
						if (!nullable) {
							ProjectContext.getErrorList().add(
									new MessageInfo("001",
											new String[] { propertyLabel }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					} else {
						try {
							tempValue = new Double(propertyValue);
						} catch (Exception e) {
							ProjectContext.getErrorList().add(
									new MessageInfo("006",
											new String[] { propertyLabel }));
							hasError = true;
						}
						if (StringUtil.getPrecision(tempValue) > precision) {
							ProjectContext.getErrorList().add(
									new MessageInfo("007", new String[] {
											propertyLabel, precision + "" }));
							hasError = true;
						} else if (StringUtil.getScale(tempValue) > scale) {
							ProjectContext.getErrorList().add(
									new MessageInfo("008", new String[] {
											propertyLabel, scale + "" }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					}
				} else if (propertyClazz.isAssignableFrom(Date.class)) {
					Date tempValue = null;
					if (StringUtil.isNullString(propertyValue)) {
						if (!nullable) {
							ProjectContext.getErrorList().add(
									new MessageInfo("001",
											new String[] { propertyLabel }));
							hasError = true;
						} else if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					} else {
						try {
							tempValue = formatDate(propertyValue,
									propertyFormat);
						} catch (Exception e) {
							ProjectContext.getErrorList().add(
									new MessageInfo("005", new String[] {
											propertyLabel, propertyFormat }));
							hasError = true;
						}
						if (!hasError) {
							String setMethodName = "set"
									+ StringUtil.upperFirstChar(peropertyName);
							Method setMethod = entityClazz.getMethod(
									setMethodName, propertyClazz);
							setMethod.invoke(entity, tempValue);
						}
					}
				}
			}
			return hasError;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new SystemException();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new SystemException();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new SystemException();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	public static void entity2domain(Object entity, Object domain)
			throws ApplicationException, SystemException {

		if (domain == null || entity == null) {
			return;
		}
		try {
			Class entityClazz = HibernateProxyHelper
					.getClassWithoutInitializingProxy(entity);
			Class domainClazz = domain.getClass();

			Method[] domainMethods = domainClazz.getMethods();

			for (int i = 0; i < domainMethods.length; i++) {

				Method domainMethod = domainMethods[i];
				String domainMethodName = domainMethod.getName();
				if (!domainMethodName.startsWith("get")) {
					continue;
				}

				if (!domainMethod.getReturnType()
						.isAssignableFrom(String.class)) {
					continue;
				}

				String propertyFormat = null;
				Annotation[] domainAnnotations = domainMethod.getAnnotations();
				if (domainAnnotations.length == 0) {
					continue;
				}
				for (int j = 0; j < domainAnnotations.length; j++) {
					Annotation domainAnnotation = domainAnnotations[j];
					if (domainAnnotation instanceof Text) {
						Text text = (Text) domainAnnotation;
						propertyFormat = text.format();
						// boolean convert = text.convert();
						// if (!convert)
						// {
						// continue;
						// }
					}
				}
				String peropertyName = StringUtil
						.lowerFirstChar(domainMethodName
								.replaceFirst("get", ""));

				String entityMethodName = "get"
						+ StringUtil.upperFirstChar(peropertyName);
				Method entityMethod = null;
				try {
					entityMethod = entityClazz.getMethod(entityMethodName);
				} catch (Exception e) {
					continue;
				}
				Class propertyClazz = entityMethod.getReturnType();
				if (!isPrimitiveType(propertyClazz)) {
					continue;
				}

				String tempValue = null;
				if (propertyClazz.isAssignableFrom(String.class)) {
					String propertyValue = (String) entityMethod.invoke(entity);
					if (propertyValue == null) {
						continue;
					}
					tempValue = propertyValue;
				} else if (propertyClazz.isAssignableFrom(Integer.class)) {
					Integer propertyValue = (Integer) entityMethod
							.invoke(entity);
					if (propertyValue == null) {
						continue;
					}
					tempValue = String.valueOf(propertyValue);
				} else if (propertyClazz.isAssignableFrom(Long.class)) {
					Long propertyValue = (Long) entityMethod.invoke(entity);
					if (propertyValue == null) {
						continue;
					}
					if (Consts.MONEY_FORMAT.equals(propertyFormat)) {
						NumberFormat nf = NumberFormat
								.getCurrencyInstance(Locale.CHINA);
						tempValue = nf.format(propertyValue);
					} else {
						tempValue = String.valueOf(propertyValue);
					}

				} else if (propertyClazz.isAssignableFrom(Double.class)) {
					Double propertyValue = (Double) entityMethod.invoke(entity);
					if (propertyValue == null) {
						continue;
					}
					if (Consts.MONEY_FORMAT.equals(propertyFormat)) {
						NumberFormat nf = NumberFormat
								.getCurrencyInstance(Locale.CHINA);
						tempValue = nf.format(propertyValue);
					} else {
						tempValue = String.valueOf(propertyValue);
					}
				} else if (propertyClazz.isAssignableFrom(Date.class)) {
					Date propertyValue = (Date) entityMethod.invoke(entity);
					if (propertyValue == null) {
						continue;
					}
					tempValue = formatDate(propertyValue, propertyFormat);
				}

				String setMethodName = "set"
						+ StringUtil.upperFirstChar(peropertyName);
				Method setMethod = domainClazz.getMethod(setMethodName,
						String.class);
				setMethod.invoke(domain, tempValue);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	public static String domain2xml(Object domain) throws SystemException {

		if (domain == null) {
			return "";
		}
		try {
			StringBuffer xml = new StringBuffer();
			Class domainClazz = domain.getClass();
			Method[] domainMethods = domainClazz.getMethods();

			for (int i = 0; i < domainMethods.length; i++) {

				Method domainMethod = domainMethods[i];
				String domainMethodName = domainMethod.getName();
				if (!domainMethodName.startsWith("get")) {
					continue;
				}

				Class propertyClazz = domainMethod.getReturnType();

				String peropertyName = StringUtil
						.lowerFirstChar(domainMethodName
								.replaceFirst("get", ""));
				if (propertyClazz.isAssignableFrom(String.class)) {
					xml.append(StringUtil.getStartTag(peropertyName));
					String propertyValue = (String) domainMethod.invoke(domain);
					xml.append(StringUtil.escapeXML(StringUtil
							.nvl(propertyValue)));
					xml.append(StringUtil.getEndTag(peropertyName));
				}
			}
			return xml.toString();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new SystemException();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new SystemException();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	public static String bean2xml(String name, String value) {
		StringBuffer xml = new StringBuffer();
		xml.append("<");
		xml.append(name);
		xml.append(">");

		xml.append(value);

		xml.append("</");
		xml.append(StringUtil.escapeXML(name));
		xml.append(">");

		return xml.toString();
	}

	public static String domainList2xml(List domainList) throws SystemException {
		if (domainList == null || domainList.isEmpty()) {
			return "";
		}
		StringBuffer xml = new StringBuffer();
		for (int i = 0; i < domainList.size(); i++) {
			xml.append(domain2xml(domainList.get(i)));
		}
		return xml.toString();

	}

	private static boolean isPrimitiveType(Class clazz) {

		if (clazz.isAssignableFrom(String.class)) {
			return true;
		} else if (clazz.isAssignableFrom(Integer.class)) {
			return true;
		} else if (clazz.isAssignableFrom(Long.class)) {
			return true;
		} else if (clazz.isAssignableFrom(Double.class)) {
			return true;
		} else if (clazz.isAssignableFrom(Date.class)) {
			return true;
		}
		return false;
	}

	public static Date formatDate(String param, String format)
			throws SystemException {
		if (StringUtil.isNullString(param) || StringUtil.isNullString(format)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(param);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new SystemException();
		}
	}

	public static String formatDate(Date param, String format) {
		if (param == null || StringUtil.isNullString(format)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(param);
	}
}
