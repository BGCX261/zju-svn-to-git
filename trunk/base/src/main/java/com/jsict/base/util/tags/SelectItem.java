/**
 * SelectItem.java        Sep 24, 2008 2:39:23 PM
 * Administrator
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.tags;

/**
 * <P>Description: The Class SelectItem.</p>
 * 
 * @author Administrator
 * @version 1.0
 */
public class SelectItem
{
    private String selectText;

    private String selectValue;

    public SelectItem()
    {

    }

    public SelectItem(String selectText, String selectValue)
    {
        super();
        this.selectText = selectText;
        this.selectValue = selectValue;
    }

    /**
     * Gets the select text.
     * 
     * @return the select text
     */
    public String getSelectText()
    {
        return this.selectText;
    }

    /**
     * Sets the select text.
     * 
     * @param selectText the new select text
     */
    public void setSelectText(String selectText)
    {
        this.selectText = selectText;
    }

    /**
     * Gets the select value.
     * 
     * @return the select value
     */
    public String getSelectValue()
    {
        return this.selectValue;
    }

    /**
     * Sets the select value.
     * 
     * @param selectValue the new select value
     */
    public void setSelectValue(String selectValue)
    {
        this.selectValue = selectValue;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((selectText == null) ? 0 : selectText.hashCode());
        result = prime * result
                + ((selectValue == null) ? 0 : selectValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final SelectItem other = (SelectItem) obj;
/*        if(selectText == null)
        {
            if(other.selectText != null)
                return false;
        }
        else if(!selectText.equals(other.selectText))
            return false;*/
        if(selectValue == null)
        {
            if(other.selectValue != null)
                return false;
        }
        else if(!selectValue.equals(other.selectValue))
            return false;
        return true;
    }

}
