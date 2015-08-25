/**
 * BusinessLogListForm.java 2008-11-26 上午09:59:39 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.form;

import java.util.List;

import com.jsict.base.form.BaseListForm;
import com.jsict.base.util.tags.SelectItem;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class BusinessLogListForm extends BaseListForm
{
    private List<SelectItem> subSystems;

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param subSystems The subSystems to set.
     */
    public void setSubSystems(List<SelectItem> subSystems)
    {
        this.subSystems = subSystems;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return List<SelectItem> subSystems.
     */
    public List<SelectItem> getSubSystems()
    {
        return subSystems;
    }
}
