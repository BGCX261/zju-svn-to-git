package cn.itcast.bbs.web.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts.upload.FormFile;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.utils.ImageUtils;
import cn.itcast.bbs.web.OnlineUsersManager;
import cn.itcast.bbs.web.WebConstants;

/**
 * User有关功能(web层)的工具类
 * 
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
public class WebUserHelper {
	// private static Log log = LogFactory.getLog(UserActionHelper.class);

	public static byte[] handleAvatar(FormFile formFile) throws IOException {
		if(formFile.getFileSize() == 0){
			return null;
		}
		
		String imgName = formFile.getFileName();
		String extension = FilenameUtils.getExtension(imgName).toLowerCase();

		int type = ImageUtils.IMAGE_UNKNOWN;

		if (extension.equals("jpg") || extension.equals("jpeg")) {
			type = ImageUtils.IMAGE_JPEG;
		}

		else if (extension.equals("png") || extension.equals("gif")) {
			type = ImageUtils.IMAGE_PNG;
			extension = "png";
		}

		if (type == ImageUtils.IMAGE_UNKNOWN) {
			return null;
		}

		String tempFilename = SystemGlobals.getSettings().getApplicationPath() + "/WEB-INF/temp/" + imgName;
		try { // 先保存为临时文件到临时文件夹中
			saveAvatarFile(formFile.getInputStream(), tempFilename);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		int maxWidth = SystemGlobals.getSettings().getAvatarMaxWidth();
		int maxHeight = SystemGlobals.getSettings().getAvatarMaxHeight();
		BufferedImage image = ImageUtils.resizeImage(tempFilename, ImageUtils.IMAGE_GIF, maxWidth, maxHeight);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, type == ImageUtils.IMAGE_JPEG ? "jpg" : "png", out);

		try { // 删除临时文件
			new File(tempFilename).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	/**
	 * 保存头像文件
	 * 
	 * @param inputStream
	 * @param filename
	 */
	private static void saveAvatarFile(InputStream inputStream, String filename) {
		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(filename);

			int c;
			byte[] b = new byte[4096];
			while ((c = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, c);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * <pre>
	 * 1, 设置user到session作用域
	 * 2, 在session作用域放置一个已登陆标记
	 * 3, 替换掉原先在HttpSessionManager中的session
	 * </pre>
	 * 
	 * @param request
	 * @param user
	 */
	public static void login(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute(WebConstants.SESSION_KEY_CURRENTLY_LOGGED_ON_USER, user); // 把用户放到session作用域
		
		OnlineUsersManager.getInstance().register(session, user);
	}

}
