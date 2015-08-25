package cn.itcast.bbs.exception.runtime;

/**
 *  @author 传智播客.汤阳光 Dec 15, 2008
 */
public class ItcastMaxLengthExceededException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItcastMaxLengthExceededException() {
		super();
	}
	
	public ItcastMaxLengthExceededException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ItcastMaxLengthExceededException(String message) {
		super(message);
	}
	
	public ItcastMaxLengthExceededException(Throwable cause) {
		super(cause);
	}
	
}
