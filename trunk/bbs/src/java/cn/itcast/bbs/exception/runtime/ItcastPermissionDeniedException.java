package cn.itcast.bbs.exception.runtime;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
public class ItcastPermissionDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ItcastPermissionDeniedException() {
		super();
	}
	
	public ItcastPermissionDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ItcastPermissionDeniedException(String message) {
		super(message);
	}
	
	public ItcastPermissionDeniedException(Throwable cause) {
		super(cause);
	}
	
}
