package com.jsict.platform.form;

import java.util.List;
import java.util.Map;

import com.jsict.base.form.BaseForm;
import com.jsict.base.util.codebook.CodeBookVO;

public class CodeBookForm extends BaseForm
{
    private Map<String, List<CodeBookVO>[]> resultList;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return Map<String,List<CodeBookVO>[]> resultList.
     */
    public Map<String, List<CodeBookVO>[]> getResultList()
    {
        return resultList;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param resultList
     *            The resultList to set.
     */
    public void setResultList(Map<String, List<CodeBookVO>[]> resultList)
    {
        this.resultList = resultList;
    }

}
