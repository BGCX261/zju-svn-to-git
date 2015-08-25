package cn.itcast.bbs.exception.runtime;

/**
 * 访问管理功能没有权限异常.
 * 
 * @author 传智播客.汤阳光 Apr 30, 2009
 * 
 */
@SuppressWarnings("serial")
public class ItcastIllegalAdminAccessException extends ItcastPermissionDeniedException {

	public ItcastIllegalAdminAccessException() {
		super();
	}

	public ItcastIllegalAdminAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItcastIllegalAdminAccessException(String message) {
		super(message);
	}

	public ItcastIllegalAdminAccessException(Throwable cause) {
		super(cause);
	}

}
