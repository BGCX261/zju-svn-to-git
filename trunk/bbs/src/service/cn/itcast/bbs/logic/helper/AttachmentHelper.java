package cn.itcast.bbs.logic.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.article.Attachment;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class AttachmentHelper {
	private static final Log log = LogFactory.getLog(AttachmentHelper.class);

	/**
	 * 保存附件文件, 保存完附件文件后, 会把{@link Attachment#getInputStream()}关闭<br>
	 * 返回List<File>对象，以便在数据库操作失败后删掉已保存的附件
	 * 
	 * @param attachments
	 * @return 成功保存的附件
	 */
	public static List<File> storeAttachmentFiles(Collection<Attachment> attachments) {
		// 1. 如果不包含附件
		if (attachments == null || attachments.size() == 0) {
			return new ArrayList<File>(0);
		}

		List<File> files = new ArrayList<File>();
		for (Attachment att : attachments) {
			String basePath = SystemGlobals.getSettings().getAttachmentStorePath();
			String subPath = makeStoreSubPath(basePath); // 服务器上保存的路径(相对于附件保存的基路径).
			att.setPath(subPath);// 在数据库中保存的是相对路径.

			// 保存到文件系统中
			File file = saveAttachmentFile(att, basePath + "/" + subPath);
			files.add(file);
		}

		return files;
	}

	

	/**
	 * 删除附件
	 * 
	 * @param attachments
	 */
	public static void deleteAttachmentFiles(List<Attachment> attachments) {
		if (attachments == null || attachments.size() == 0) {
			return;
		}

		String basePath = SystemGlobals.getSettings().getAttachmentStorePath();
		for (Attachment att : attachments) {
			File f = new File(basePath + "/" + att.getPath());

			if (!f.exists()) {
				log.error("附件不存在" + att.toString());
				continue;
			}

			if (!f.delete()) {
				log.info("附件删除失败" + att.toString());
				continue;
			}
		}
	}

	/**  */
	private static final SimpleDateFormat sdfForMakePath = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 根据日期创建文件夹，并生成在服务器端保存应使用的文件名
	 * 
	 * @return 在服务器端保存的文件名（相对于附件保存的路径的一个相对路径）
	 */
	protected static String makeStoreSubPath(String basePath) {
		// 目录结构为：${基路径}/${年}/${月}/${日}/
		String datePath = sdfForMakePath.format(new Date());
		String absolutePath = basePath + "/" + datePath;

		// 如果目录不存在, 就递归的创建
		File dir = new File(absolutePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 使用UUID 作为保存文件名
		String filename = UUID.randomUUID().toString();
		return datePath + "/" + filename;
	}
	
	/**
	 * 保存附件文件, 保存完附件文件后, 会把{@link Attachment#getInputStream()}关闭<br>
	 * 
	 * @param att
	 * @param path
	 * @return 保存后的文件
	 */
	public static File saveAttachmentFile(Attachment att, String path) {
		OutputStream out = null;
		InputStream in = null;
		try {
			File file = new File(path);
			out = new FileOutputStream(file);
			in = att.getInputStream();

			byte[] buf = new byte[81920];
			for (int len = -1; (len = in.read(buf)) > -1;) {
				out.write(buf, 0, len);
			}
			out.flush();

			return file;
		} catch (Exception e) {
			throw new RuntimeException("附件保存失败" + att.toString());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			} finally {
				// TODO close Attachment.inputStream ?
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						log.error(e.getMessage());
					}
				}
			}
		}
	}
}
