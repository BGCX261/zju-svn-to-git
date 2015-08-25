/**
 * 
 */
package com.jsict.jszju.action.frontMenu;

import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Op;
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.service.IFileService;

/**
 * @author Administrator
 *
 */
public class ExcellentSchoolFellowListAction extends BaseListAction {

	private static final String VIEW = "view";

	private static final String EXCELENTSFEDIT = "excellentsfedit";

	private IFileService fileService;

	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

	/* (non-Javadoc)
	 * @see com.jsict.base.action.BaseListAction#doListProcess(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {

		String actionType = request.getParameter("actionType");

		if (VIEW.equalsIgnoreCase(actionType)) {

			EntityFilter tf = new EntityFilter();
			tf.addFilter("dep", Op.EQUAL, "1");
			PagedList<Fileinfo> list = fileService.getFilePagedList(
					getEntityFilter(), null, 5);
			Collections.sort(list, new FileComparator());
			request.setAttribute("filelist", list);

			return mapping.findForward(VIEW);
		}
		return mapping.findForward(VIEW);
	}

	class FileComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Fileinfo s1 = (Fileinfo) o1;
			if (s1.getFilename() == null) {
				s1.setFilename("20091010000000.jpg");
			}
			String[] s1A = s1.getFilename().split("\\.");
			Fileinfo s2 = (Fileinfo) o2;
			if (s2.getFilename() == null) {
				s2.setFilename("20091010000000.jpg");
			}
			String[] s2A = s2.getFilename().split("\\.");
			int result1 = s1A[0].compareTo(s2A[0]);
			int result = (result1 < 0) ? 1 : -1;
			return result;
		}
	}

}
