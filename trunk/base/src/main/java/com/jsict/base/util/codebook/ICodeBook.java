/**
 * ICodeBook.java 2008-11-13 下午02:28:48 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.codebook;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface ICodeBook
{

    public String getCodeKey();

    public String getCodeType();

    public String getCodeValue();

    public Integer getCodeStatus();

    public String getCodeDesc();

    public void setCodeDesc(String codeDesc);
}
