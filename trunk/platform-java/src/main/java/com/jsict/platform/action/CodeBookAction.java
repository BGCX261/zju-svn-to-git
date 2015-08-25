package com.jsict.platform.action;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import com.jsict.base.util.Consts;
import com.jsict.base.util.codebook.CodeBookHelper;
import com.jsict.base.util.codebook.CodeBookVO;
import com.jsict.platform.form.CodeBookForm;

public class CodeBookAction extends Action
{

    public final ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {

        CodeBookForm codeBookForm = (CodeBookForm) form;
        if(form != null)
        {
            String actionType = codeBookForm.getActionType();

            if(Consts.ACTION_TYPE_REFRESH.equalsIgnoreCase(actionType))
            {
                ServletContext servletContext = getServlet()
                        .getServletContext();
                WebApplicationContext webApplicationContext = (WebApplicationContext) servletContext
                        .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
                CodeBookHelper codeBookHelper = (CodeBookHelper) webApplicationContext
                        .getBean("plfCodeBookHelper");

                codeBookHelper.reload();
            }
        }
        Map<String, List<CodeBookVO>[]> codeMap = CodeBookHelper.getCodeMap();

        codeBookForm.setResultList(codeMap);
        return mapping.getInputForward();
    }
}
