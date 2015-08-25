/**
 * @(#)CodeBookHelper.java 2008-9-16 下午08:54:39 Liu GuanQun(liugq@jsict.com)
 *                         版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.codebook;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.util.tags.SelectItem;

/**
 * 
 * This class
 * 
 * @author Liu GuanQun(liugq@jsict.com)
 * 
 * @version 0.1
 * 
 */
@Transactional
public class CodeBookHelper
{

    protected static Log logger = LogFactory.getLog(CodeBookHelper.class
            .getName());

    protected static int CODE_STATUS_USED = 1;

    protected static int CODE_STATUS_UNUSED = 1;

    protected static int INDEX_TOTALLIST = 2;

    protected static int INDEX_UNUSEDLIST = 0;

    protected static int INDEX_USEDLIST = 1;

    ICodeBookProvider codeBookProvider;

    public CodeBookHelper()
    {

    }

    /**
     * Key:type Value:map with key=code,value=
     */
    static Map<String, List<CodeBookVO>[]> codeMap;

    static Map<String, CodeBookVO> codeBookMap;

    protected static boolean isCodeBookUsed(CodeBookVO codeBook)
    {
        return codeBook.getCodeStatus() == CODE_STATUS_USED;
    }

    private static int status2Index(Integer status)
    {
        return status == null ? 2 : status;
    }

    protected static CodeBookVO getByKey(String key)
    {
        return codeBookMap.get(key);
    }

    protected static List<CodeBookVO> getByType(String type)
    {
        return getByType(type, 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getByType(java.lang.String,
     *      java.lang.Integer)
     */
    public static List<CodeBookVO> getByType(String type, Integer status)
    {
        logger.info(MessageFormat.format("CodeBookHelper.getByType {0} {1}",
            type, status));
        List<CodeBookVO>[] lists = codeMap.get(type);
        if(lists != null)
        {
            List<CodeBookVO> list = codeMap.get(type)[status2Index(status)];

            if(list == null)
                return Collections.EMPTY_LIST;
            else
                return Collections.unmodifiableList(list);
        }
        else
        {
            return Collections.EMPTY_LIST;

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getTypes()
     */
    public static List<String> getTypes()
    {
        return Collections.unmodifiableList(new ArrayList(codeMap.keySet()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getAllTypes()
     */
    public List<CodeBookVO> getAllCodeBook()
    {
        EntityFilter ef = new EntityFilter();
        ef.addOrder("codeType", "ASC");
        ef.addOrder("codeKey", "ASC");

        return codeBookProvider.getFilteredList(ef);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getSelectItemsByType(java.lang.String)
     */
    public static List<SelectItem> getSelectItemsByType(String type)
    {
        logger.info(MessageFormat.format(
            "CodeBookHelper.getSelectItemsByType {0}", type));
        return getSelectItemsByType(type, 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getSelectItemsByType(java.lang.String,
     *      int)
     */
    public static List<SelectItem> getSelectItemsByType(String type, int status)
    {
        logger.info(MessageFormat.format(
            "CodeBookHelper.getSelectItemsByType {0} {1}", type, status));

        List<SelectItem> list = new ArrayList<SelectItem>();

        List<CodeBookVO> list2 = getByType(type, status);
        for (CodeBookVO codeBook : list2)
        {
            SelectItem selectItem = new SelectItem();
            selectItem.setSelectText(codeBook.getCodeValue());
            selectItem.setSelectValue(codeBook.getCodeKey());
            list.add(selectItem);
        }

        return Collections.unmodifiableList(list);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#getValueByKey(java.lang.String)
     */
    public static String getValueByKey(String key)
    {
        if(StringUtils.isBlank(key))
            return "";
        CodeBookVO codeBookVO = codeBookMap.get(StringUtils.trim(key));
        if(codeBookVO == null)
        {
            String msg = "CodeKey " + key + " does not exist!";
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return codeBookMap.get(key).getCodeValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jsict.etm.util.ICodeBookHelper#reLoad()
     */

    public synchronized void reload()
    {
        logger.info("CodeBookHelper.reload");
        if(codeMap == null)
        {
            codeMap = new LinkedMap();
        }
        else
        {
            codeMap.clear();
        }
        if(codeBookMap == null)
        {
            codeBookMap = new LinkedMap();
        }
        else
        {
            codeBookMap.clear();
        }

        String currentCategory = null;
        List<CodeBookVO> currentTotalList = null;
        List<CodeBookVO> currentUsedList = null;
        List<CodeBookVO> currentUnusedList = null;

        List<CodeBookVO> list = getAllCodeBook();

        for (CodeBookVO codeBook : list)
        {
            codeBookMap.put(codeBook.getCodeKey(), codeBook);
            String category = codeBook.getCodeType();
            if(!StringUtils.equals(category, currentCategory))
            {
                // a new category
                currentCategory = category;

                currentTotalList = new ArrayList<CodeBookVO>();
                currentUsedList = new ArrayList<CodeBookVO>();
                currentUnusedList = new ArrayList<CodeBookVO>();

                codeMap.put(category, new List[]{currentUnusedList,
                        currentUsedList, currentTotalList});

            }
            else
            {

            }

            if(isCodeBookUsed(codeBook))
            {
                currentUsedList.add(codeBook);
            }
            else
            {
                currentUnusedList.add(codeBook);
            }
            currentTotalList.add(codeBook);
        }

    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param codeBookProvider
     *            The codeBookProvider to set.
     */
    @Required
    public void setCodeBookProvider(ICodeBookProvider codeBookProvider)
    {
        this.codeBookProvider = codeBookProvider;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return Map<String,List<CodeBookVO>[]> codeMap.
     */
    public static Map<String, List<CodeBookVO>[]> getCodeMap()
    {
        return codeMap;
    }

}
