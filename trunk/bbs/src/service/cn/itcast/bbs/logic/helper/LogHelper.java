package cn.itcast.bbs.logic.helper;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class LogHelper {

	/**
	 * @param ex
	 * @return 异常的详细错误信息, {@link Exception#printStackTrace()}
	 */
	public static String getDetailMessage(Exception ex) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteArrayOutputStream);
		ex.printStackTrace(printStream);
		return byteArrayOutputStream.toString();
	}

}
