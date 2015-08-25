package cn.itcast.bbs.entities.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import cn.itcast.bbs.entities.Sex;

/**
 * 在数据库表中使用一个varchar类型的列保存枚举常量的name值。
 * @author tyg
 *@deprecated hibernateDemo中用
 */
public class SexType implements UserType {

	/** 用于设定nullSafeGet所返回的数据的类型。即我们的自定义的数据类型。 */
	public Class returnedClass() {
		return null;
	}

	/** 对应的数据库表中列的类型，可选值在java.sql.Types中定义 */
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	/** 在PreparedStatement执行之前会调用本方法。
	 *  一般都是在本方法中将自定义类型数据转换成数据库中的数据类型。  
	 *  要处理值为null时的情况。
	 *  其中的value表示的是要写入的值；index表示的是在statement的参数中的索引。 */
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			Sex sex = (Sex) value;
			st.setString(index, sex.name());
		}
	}

	/** 从ResultSet中取出相应的值并转（生）成与属性类型对应的类型，
	 *  要处理当值为null时的情况 */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if(rs.wasNull()){
			return null;
		}
		
		return Sex.valueOf(value);
	}
	
	/** 对象是否可变（例如我们这里的Sex不可变） */
	public boolean isMutable() {
		return false;
	}
	
	/** 要求返回一个自定义类型的完全复制对象，如果此对象是不可变的（如枚举）或null，可以直接返回value参数 */
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	/** 对对象调用merge方法时对这个属性的拷贝，
	 *  如果是不可变对象（如枚举）或null，应直接返回第一个参数值original */
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if(x == null ){
			return false;
		}
		return x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	/** 把这个数据放入缓存时要调用的方法 */
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	/** 从缓存中取这个对象数据时要调用的方法 */
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value;
	}

}
