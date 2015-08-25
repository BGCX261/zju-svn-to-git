/**
 * SelectOneTag.java        Sep 24, 2008 2:51:04 PM
 * Administrator
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */
package com.jsict.base.util.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.jsict.base.util.codebook.CodeBookHelper;

/**
 * <P>Description: The Class SelectOneTag.</p>
 * 
 * @author Administrator
 * @version 1.0
 */
public class SelectOneTag extends SimpleTagSupport implements DynamicAttributes
{
    private static final String ATTR_TEMPLATE = "%s='%s'";

    private String defaultText;

    private String codeType;

    private List selectItemList;

    private Map<String, Object> tagAttrs = new HashMap<String, Object>();

    private CodeBookHelper codeBookHelper;

    private String property;

    private String value;

    private String name;

    /**
     * <p>
     * Description: 实现了DynamicAttributes接口中的setDynamicAttribute方法
     * 当控件需要多事件控制时，进行动态事件添加
     * </p>
     * @param uri
     * @param name
     * @param value
     * @throws JspException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    public void setDynamicAttribute(String uri, String name, Object value)
            throws JspException
    {
        this.tagAttrs.put(name, value);
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param defaultText The defaultText to set.
     */
    public void setDefaultText(String defaultText)
    {
        this.defaultText = defaultText;
    }

    /**
     * Sets the code type.
     * 
     * @param codeType the new code type
     */
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    /**
     * Sets the select item list.
     * 
     * @param selectItemList the new select item list
     */
    public void setSelectItemList(List selectItemList)
    {
        this.selectItemList = selectItemList;
    }

    /**
     * <p>Description: The doTag</p>
     * @throws JspException
     * @throws IOException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = this.getJspContext().getOut();
        if (this.selectItemList != null)
        {
            this.outputTag(out, this.selectItemList);
        }
        else if (this.codeType != null)
        {
            // TODO 添加字典表数据查询
            selectItemList = codeBookHelper.getSelectItemsByType(this.codeType);
            this.outputTag(out, this.selectItemList);
        }
        else
        {
            out.print("<select></select>");
        }
    }

    private void outputTag(JspWriter out, List selectItemList)
            throws JspException, IOException
    {
        if (value == null)
        {
            try
            {
                Object bean = this.getJspContext().findAttribute(
                        name == null ? "" : name);
                if (bean != null)
                {
                    value = BeanUtils.getProperty(bean, property);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        out.print("<select ");
        out.print(" id='" + property + "' ");
        out.print(" name='" + property + "' ");
        for (String attrName : this.tagAttrs.keySet())
        {
            String attrDefinition = String.format(SelectOneTag.ATTR_TEMPLATE,
                    attrName, this.tagAttrs.get(attrName));
            out.print(attrDefinition);
        }
        out.print(">");
        if (defaultText != null)
        {
            out.print("<option value=''>" + defaultText + "</option>");
        }
        for (Object o : selectItemList)
        {
            SelectItem selectItem = (SelectItem)o;
            if (StringUtils.equals(selectItem.getSelectValue(), value))
            {
                out.print("<option selected='true' value="
                        + selectItem.getSelectValue() + ">"
                        + selectItem.getSelectText() + "</option>");
            }
            else
            {
                out.print("<option value=" + selectItem.getSelectValue() + ">"
                        + selectItem.getSelectText() + "</option>");
            }
        }
        out.print("</select>");
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeBookHelper The codeBookHelper to set.
     */
    public void setCodeBookHelper(CodeBookHelper codeBookHelper)
    {
        this.codeBookHelper = codeBookHelper;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param property The property to set.
     */
    public void setProperty(String property)
    {
        this.property = property;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

}