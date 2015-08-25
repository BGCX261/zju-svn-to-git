package cn.itcast.bbs.test.enumtest;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import cn.itcast.bbs.entities.article.Topic.TopicType;

public class TestEnum {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Object bean = TopicType.ANNOUNCE;
		Object[] values = {};
		Method method = bean.getClass().getDeclaredMethod("getTitle");
		Class clazz = method.getDeclaringClass();

		if (Modifier.isPublic(clazz.getModifiers())) { // 如果是enum 这里会返回 false
			// return (method);
			System.out.println("Get the method!");

			// Call the property getter and return the value
			Object value = method.invoke(bean, values);
			System.out.println(value);
		}

		method.setAccessible(true); // 这样才能执行
		Object value = method.invoke(bean, values);
		System.out.println(value);

		System.out.println(TopicType.class);
		System.out.println(TopicType.ANNOUNCE.getClass());

		System.out.println(Integer.toHexString(16384));
	}

}
