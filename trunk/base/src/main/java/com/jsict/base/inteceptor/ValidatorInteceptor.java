package com.jsict.base.inteceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.ClassUtils;

import com.jsict.base.IBaseService;
import com.jsict.base.IBaseValidator;
import com.jsict.base.exception.ApplicationException;

public class ValidatorInteceptor implements MethodBeforeAdvice {
	private List<IBaseValidator<IBaseService>> validators;

	private final static Map<Class<? extends IBaseValidator<? extends IBaseService>>, Method[]> catchedMethods = new ConcurrentHashMap<Class<? extends IBaseValidator<? extends IBaseService>>, Method[]>();

	private final static Map<Class<? extends IBaseService>, IBaseValidator<IBaseService>> hits = new ConcurrentHashMap<Class<? extends IBaseService>, IBaseValidator<IBaseService>>();

	private final Log log = LogFactory.getLog(ValidatorInteceptor.class);

	public void before(Method method, Object[] params, Object service)
			throws Throwable {
		IBaseService baseService = (IBaseService) service;

		IBaseValidator<IBaseService> validator = hits.get(baseService
				.getClass());

		if (validator == null) {
			validator = findValidator(baseService);

			if (validator != null) {
				hits.put(baseService.getClass(), validator);
			}
		}

		if (validator != null) {
			invokeValidation(validator, method, params);
		} else {
			log.debug("Service does not bind to a validator ["
					+ ClassUtils.getUserClass(service.getClass()) + "]");
		}

	}

	private IBaseValidator<IBaseService> findValidator(IBaseService service) {
		boolean find = false;
		for (IBaseValidator<IBaseService> validator : validators) {
			find = validator.supports((IBaseService) service);

			if (find) {
				return validator;
			}
		}

		return null;

	}

	private void invokeValidation(IBaseValidator<IBaseService> validator,
			Method method, Object[] params) throws Exception {
		Method[] methods = catchedMethods.get(validator.getClass());

		if (methods == null || methods.length == 0) {
			methods = validator.getClass().getMethods();
		}

		Object[] converted = params == null ? null : new Object[params.length];
		if (methods != null) {
			for (Method mtd : methods) {
				if (mtd.getName().equalsIgnoreCase(method.getName())) {
					Class[] clazzs = mtd.getParameterTypes();

					if (clazzs.length != params.length) {
						continue;
					}
					int i = 0;
					boolean match = true;
					for (; i < params.length; i++) {
						Class clazz = clazzs[i];
						if (!clazz.isAssignableFrom(params[i].getClass())) {
							match = false;
							break;
						} else {
							converted[i] = params[i];
						}
					}

					if (match) {
						try {
							mtd.invoke(validator, converted);
						} catch (InvocationTargetException ite) {
							if (ite.getTargetException() instanceof ApplicationException) {
								ApplicationException ae = (ApplicationException) ite
										.getTargetException();
								if (ae.getMessageInfo() != null) {
									throw new ApplicationException(ae
											.getMessageInfo().getId(), ae
											.getMessageInfo().getParams(), ae
											.getForward());
								} else {
									throw new ApplicationException();
								}
							} else {
								throw ite;
							}
						}
					}

				}
			}
		} else {
			log.debug("No validation methods defined in validator ["
					+ validator.getClass() + "]");
		}
	}

	@Required
	public void setValidators(List<IBaseValidator<IBaseService>> validators) {
		this.validators = validators;
	}

}
