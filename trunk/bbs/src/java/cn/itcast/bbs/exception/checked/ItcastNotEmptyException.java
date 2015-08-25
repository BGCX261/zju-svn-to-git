package cn.itcast.bbs.exception.checked;

@SuppressWarnings("serial")
public class ItcastNotEmptyException extends Exception {

	public ItcastNotEmptyException() {
		super();
	}

	public ItcastNotEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItcastNotEmptyException(String message) {
		super(message);
	}

	public ItcastNotEmptyException(Throwable cause) {
		super(cause);
	}

}
