package com.jsict.base.action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;

import com.jsict.base.util.MessageReader;
import com.jsict.base.util.WebConsts;
import com.jsict.base.util.codebook.CodeBookHelper;

/**
 * 
 * @author qipf
 * 
 */
public class ActionServlet extends org.apache.struts.action.ActionServlet
{

    private static final long serialVersionUID = 7083275712824061784L;

    protected String codeBookHelperId = "codeBookHelper";

    protected String codeKeyHelperId = "codeKeyHelper";

    public void init() throws ServletException
    {

        super.init();

        MessageReader.init();

        //inject the codeBookHelper instance from spring to Web Application scope
        ServletContext servletContext = this.getServletContext();
        WebApplicationContext webApplicationContext = (WebApplicationContext) servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        CodeBookHelper codeBookHelper = (CodeBookHelper) webApplicationContext
                .getBean(codeBookHelperId);
        codeBookHelper.reload();
        servletContext.setAttribute(codeBookHelperId, codeBookHelper);

        //inject the codeKeyHelper instance from spring to Web Application scope
        servletContext.setAttribute("codeKeyHelper", webApplicationContext
                .getBean(codeKeyHelperId));

        servletContext.setAttribute("permissionsKeyHelper",
            webApplicationContext.getBean("permissionsKeyHelper"));

        servletContext.setAttribute("webParams", webApplicationContext
                .getBean("webParams"));

        //webconst
        WebConsts webConsts = (WebConsts) webApplicationContext
                .getBean("webConsts");
        servletContext.setAttribute("webConsts", webConsts);
    }

}