package com.jsict.jszju.form;
import javax.servlet.http.HttpServletRequest;   
import org.apache.struts.action.ActionErrors;   
import org.apache.struts.action.ActionForm;   
import org.apache.struts.action.ActionMapping;   
import org.apache.struts.upload.FormFile;   
  
/**   
 * @author Chris  
 * Creation date: 6-27-2008  
 *   
 * XDoclet definition:  
 * @struts.form name="fileForm"  
 */  
public class FileForm extends ActionForm {   
    /*  
     * Generated Methods  
     */  
    private FormFile file1;   
    private String dep = "1";
    private String display = "1";
    private String title;
    private String linkaddress;
    private String discrption;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkaddress() {
		return linkaddress;
	}

	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}

	/**   
     * Method validate  
     * @param mapping  
     * @param request  
     * @return ActionErrors  
     */  
    public ActionErrors validate(ActionMapping mapping,   
            HttpServletRequest request) {   
        // TODO Auto-generated method stub   
        return null;   
    }   
  
    public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	/**   
     * Method reset  
     * @param mapping  
     * @param request  
     */  
    public void reset(ActionMapping mapping, HttpServletRequest request) {   
        // TODO Auto-generated method stub   
    }   
  
    public FormFile getFile1() {   
        return file1;   
    }   
  
    public void setFile1(FormFile file1) {   
        this.file1 = file1;   
    }

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDiscrption() {
		return discrption;
	}

	public void setDiscrption(String discrption) {
		this.discrption = discrption;
	}


}  