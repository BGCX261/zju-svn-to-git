/**
 * @(#)OrderChangeInteceptor.java 2008-10-9 16:13:18 Administrator 版权所有 (c)
 *                                2007-2008 江苏鸿信系统集成有限公司
 */
package com.jsict.base.inteceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.util.Consts;
import com.jsict.base.util.DateUtil;
import com.jsict.base.util.GeneratedNo;
import com.jsict.base.util.StringUtil;

/**
 * <P>
 * Description: The Class OrderChangeInteceptor.
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 */
public class GenerateNoInteceptor implements MethodBeforeAdvice
{
    private List<String> methodNames;

    private final static Log log = LogFactory
            .getLog(GenerateNoInteceptor.class);

    private static int currentSeq = 0;

    public void before(Method method, Object[] params, Object dao)
        throws Throwable
    {
        if(!methodNames.contains(method.getName()))
        {
            return;
        }
        BaseDao baseDao = (BaseDao) dao;
        Class entityClazz = baseDao.getEntityClass();
        Method[] entityMethods = entityClazz.getMethods();
        for (int i = 0; i < entityMethods.length; i++)
        {
            Method entityMethod = entityMethods[i];
            String entityMethodName = entityMethod.getName();
            String peropertyName = StringUtil.lowerFirstChar(entityMethodName
                    .replaceFirst("get", ""));
            String setMethodName = "set"
                    + StringUtil.upperFirstChar(peropertyName);
            Annotation[] annotations = entityMethod.getAnnotations();
            Class propertyClazz = entityMethod.getReturnType();
            for (Annotation anno : annotations)
            {
                if(!(anno instanceof GeneratedNo))
                {
                    continue;
                }
                Object entity = params[0];
                Method getMethod = entityClazz.getMethod(entityMethodName);
                String codeNo = (String) getMethod.invoke(entity);
                if(!StringUtil.isNullString(codeNo))
                {
                    continue;
                }
                GeneratedNo opAnno = (GeneratedNo) anno;
                String pre = opAnno.pre();
                String timeFormat = opAnno.format();
                String end = opAnno.end();
                String composedCode = "";
                String time = formatDate(DateUtil.now(), timeFormat);
                String noEnd = generateNoEnd(end);
                composedCode = pre + time + noEnd;
                Method setMethod = entityClazz.getMethod(setMethodName,
                    propertyClazz);
                setMethod.invoke(entity, composedCode);
            }
        }
    }

    private static String formatDate(Date param, String format)
    {
        if(param == null || StringUtil.isNullString(format))
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(param);
    }

    private static String generateNoEnd(String mode)
    {
        if(mode.equals(Consts.GENERATED_NO_MODE_RAN))
        {
            return generateRan();
        }
        else if(mode.equals(Consts.GENERATED_NO_MODE_SEQ))
        {
            return generateSeq();
        }
        else
        {
            return "";
        }

    }

    private static String generateRan()
    {
        Random rand = new Random();
        return supplyZero(rand.nextInt(10000));
    }

    private static String generateSeq()
    {

        if(currentSeq == Integer.MAX_VALUE)
        {
            currentSeq = 0;
        }
        int temp = currentSeq % 10000;
        currentSeq++;
        return supplyZero(temp);
    }

    /**
     * <p>
     * Description: The supplyZero
     * </p>
     * 
     * @param buf
     * @param temp
     * @return
     * @author: Administrator
     * @update: [updatedate] [changer][change description]
     */
    private static String supplyZero(int temp)
    {
        StringBuffer buf = new StringBuffer();
        String tempString = Integer.toString(temp);
        for (int i = 0; i < 4 - tempString.length(); i++)
        {
            buf.append("0");
        }
        return buf.append(tempString).toString();
    }

    /**
     * Sets the method names.
     * 
     * @param methodNames
     *            the new method names
     */
    @Required
    public void setMethodNames(List<String> methodNames)
    {
        this.methodNames = methodNames;
    }

}
