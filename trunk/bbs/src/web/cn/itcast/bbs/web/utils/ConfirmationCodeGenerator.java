package cn.itcast.bbs.web.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 * @deprecated use framework instead
 */
@Deprecated
public class ConfirmationCodeGenerator {
	private Random random = new Random();

	private int imgWidth = 300;
	private int imgHeight = 80;
	private int codeLength = 6;
	private String codeStuff = "abcdefghijklmnopqrstuvwxyz123456789";

	private Font codeFont;
	private Color[] fontColors = new Color[] { Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.MAGENTA, };

	private char[] confirmationCode;

	public ConfirmationCodeGenerator() {

		int fontSize = imgHeight / 2;
		codeFont = new Font("Comic Sans MS", Font.ITALIC/* | Font.BOLD */, fontSize);

		this.confirmationCode = generateCode();
	}

	public void write(OutputStream out) throws IOException {
		BufferedImage image = generateImage();
		ImageIO.write(image, "JPEG", out);
		out.flush();
	}

	public void writeFile(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			throw new RuntimeException("文件已存在[filename=" + filename + "]");
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			write(fos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public BufferedImage generateImage() {
		// 生成图像
		BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

		// 先画背景, 再画验证码
		Graphics g = image.getGraphics();
		drawBackground(g);
		drawCode(g, confirmationCode);
		g.dispose();

		return image;
	}

	public String getCode() {
		return new String(this.confirmationCode);
	}

	// 生成验证码
	private char[] generateCode() {
		// 随机取出字符串中的字符.
		char[] rands = new char[codeLength];
		int rand = -1;
		for (int i = 0; i < rands.length; i++) {
			rand = random.nextInt(codeStuff.length());
			rands[i] = codeStuff.charAt(rand);
		}
		return rands;
	}

	private void drawCode(Graphics g, char[] rands) {
		g.setColor(fontColors[random.nextInt(fontColors.length)]);
		g.setFont(codeFont);

		int x = imgWidth / 10;
		int y = (int) (codeFont.getSize() * 1.3);
		// 除去左右的空白, 把中间的区域分成 rands.length, 每一块写一个字
		int width = (imgWidth - 2 * x) / rands.length;

		for (int i = 0; i < rands.length; i++) {
			g.drawString(String.valueOf(rands[i]), x, y);
			x += width;
		}
	}

	private void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xdcdcdc));
		g.fillRect(0, 0, imgWidth, imgHeight);
		for (int i = 0; i < 500; i++) {
			// 随机生成500个位置随机，颜色随机的干扰点，并画在背景上
			int x = random.nextInt(imgWidth);
			int y = random.nextInt(imgHeight);
			int width = random.nextInt(10);
			int height = random.nextInt(10);

			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, width, height);
		}
	}

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 10; i++) {
			ConfirmationCodeGenerator codeGenerator = new ConfirmationCodeGenerator();
			FileOutputStream fos = new FileOutputStream("c:/test/" + i + ".jpg");
			codeGenerator.write(fos);
			fos.close();

			System.out.println(codeGenerator.getCode());
			System.out.println(codeGenerator.getCode());
			System.out.println(codeGenerator.getCode().length());
		}
	}
}
