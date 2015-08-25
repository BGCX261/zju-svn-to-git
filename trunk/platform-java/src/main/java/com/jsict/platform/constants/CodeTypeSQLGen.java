/**
 * CodeTypeSQLGen.java 2008-10-2 下午11:17:51 Liu GuanQun(liugq@jsict.com) 版权所有
 * (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.constants;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jsict.base.annotation.CodeKeyDef;
import com.jsict.base.annotation.CodeTypeDef;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CodeTypeSQLGen
{

    /**
     * <p>
     * Description: The main
     * </p>
     * 
     * @param args
     * @author: Liu GuanQun(liugq@jsict.com)
     * @update: 2008-10-2-下午11:17:51 [变更人姓名] [变更内容]
     */
    static Map<String, String> codeTypeDesc = new HashMap<String, String>();

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        Field[] declaredFields = CodeType.class.getDeclaredFields();
        for (Field field2 : declaredFields)
        {
            CodeTypeDef codeTypeDef = field2.getAnnotation(CodeTypeDef.class);
            String memberValue = String.valueOf(field2.get(null));
            if(codeTypeDesc.containsKey(memberValue))
            {
                throw new Exception("重复的Type值" + field2.getName() + "="
                        + memberValue);
            }
            if(codeTypeDef != null)
            {
                codeTypeDesc.put(memberValue, codeTypeDef.value());
            }
            //检查是否有重复的值

        }

        Field[] fields = CodeKey.class.getDeclaredFields();
        String oldCodeDesc = null;
        System.out.println("DELETE FROM CODE_BOOK;");
        List<String> codeKeys = new ArrayList<String>();
        for (Field field : fields)
        {
            CodeKeyDef codeKeyDef = field.getAnnotation(CodeKeyDef.class);
            String codeKey = String.valueOf(field.get(null));

            if(codeKeys.contains(codeKey))
            {
                throw new Exception("Key" + field.getName() + "=" + codeKey);
            }
            codeKeys.add(codeKey);
            if(codeKeyDef != null)
            {
                String sql = "INSERT INTO CODE_BOOK(" + "CODE_KEY,"
                        + "CODE_TYPE," + "CODE_VALUE," + "CODE_STATUS,"
                        + "CODE_DESC) "
                        + "VALUES(''{0}'',''{1}'',''{2}'',{3},''{4}'');";

                String codeType = codeKey.substring(0, 2);
                String codeValue = codeKeyDef.value();
                int codeStatus = codeKeyDef.status();
                String codeDesc = codeTypeDesc.get(codeType);
                if(!StringUtils.equals(oldCodeDesc, codeDesc))
                {
                    oldCodeDesc = codeDesc;
                    System.out.println();
                    System.out.println("---" + codeDesc);
                }
                String finalsql = MessageFormat.format(sql, codeKey, codeType,
                    codeValue, codeStatus, codeDesc);
                System.out.println(finalsql);
            }
        }

        System.out.println(Long.class.isAssignableFrom(Number.class));
        System.out.println(Number.class.isAssignableFrom(Long.class));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
