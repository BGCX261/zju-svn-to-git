/**
 * ICodeBookProvider.java        2008-9-24 下午03:22:19
 * Liu GuanQun(liugq@jsict.com)
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.codebook;

import java.util.List;

import com.jsict.base.dao.EntityFilter;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$ 
 */
public interface ICodeBookProvider
{
    List<CodeBookVO> getFilteredList(EntityFilter tf);
}
