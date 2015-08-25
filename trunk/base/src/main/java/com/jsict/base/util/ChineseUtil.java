/**
 * ChineseUtil.java 2008-12-1 下午03:36:58 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public abstract class ChineseUtil
{
    public static String getFirstPinyin(String chinese)
    {
        StringBuilder sb = new StringBuilder();
        for (char char1 : chinese.toCharArray())
        {
            sb.append(getFirstPinyin(char1));
        }
        return sb.toString();
    }

    public static String getFirstPinyin(char s)
    {
        try
        {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(s,
                format);
            if(pinyinArray.length > 0)
                return pinyinArray[0].substring(0, 1);
            else
                return String.valueOf(s);
        }
        catch (BadHanyuPinyinOutputFormatCombination e)
        {
            
            return String.valueOf(s);
        }
        catch (Throwable e)
        {
            // TODO: handle exception
            return String.valueOf(s);
        }

    }
}
