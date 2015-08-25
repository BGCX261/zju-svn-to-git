package cn.itcast.bbs.exception.runtime;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class ItcastException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItcastException() {
		super();
	}

	public ItcastException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItcastException(String message) {
		super(message);
	}

	public ItcastException(Throwable cause) {
		super(cause);
	}

}
