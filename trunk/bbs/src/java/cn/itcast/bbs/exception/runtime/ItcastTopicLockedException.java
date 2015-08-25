package cn.itcast.bbs.exception.runtime;

/**
 * 主题锁定异常
 * 
 * @author 传智播客.汤阳光 Apr 26, 2009
 * 
 */
public class ItcastTopicLockedException extends ItcastException {
	private static final long serialVersionUID = 1L;

	public ItcastTopicLockedException() {
		super();
	}

	public ItcastTopicLockedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItcastTopicLockedException(String message) {
		super(message);
	}

	public ItcastTopicLockedException(Throwable cause) {
		super(cause);
	}

}
