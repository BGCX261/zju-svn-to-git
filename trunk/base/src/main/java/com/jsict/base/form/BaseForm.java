/**
 * $Id: PJBaseForm.java,v 1.3 2006/02/14 08:51:16 helj Exp $
 */

package com.jsict.base.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author qipf
 * 
 */
public abstract class BaseForm extends ActionForm
{

    protected String actionType = null;

    public String getActionType()
    {
        return actionType;
    }

    public void setActionType(String actionType)
    {
        this.actionType = actionType;
    }

}