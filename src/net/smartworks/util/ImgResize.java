package net.smartworks.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;

public class ImgResize {

	public static final int SAME = -1;
	public static final int RATIO = 0;
	
	public static void resize(File src, File dest, int width, int height) throws IOException{
		ImageInputStream srcFile = null;
		try{
			srcFile = new FileImageInputStream(src);
			ImgResize.resize(srcFile, dest, width, height);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void resize(ImageInputStream src, File dest, int width, int height) throws NullPointerException, IOException{
		BufferedImage srcImg = ImageIO.read(src);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		
		int destWidth = -1;
		int destHeight = -1;
		
		if(width == SAME){
			destWidth = srcWidth;
		}else if(width > 0){
			destWidth = width;
		}
		
		if(height == SAME){
			destHeight = srcHeight;
		}else if(height > 0){
			destHeight = height;
		}
		
		if(width == RATIO && height == RATIO){
			destWidth = srcWidth;
			destHeight = srcHeight;
		}else if(width == RATIO){
			double ratio = ((double)destHeight) / ((double)srcHeight);
			destWidth = (int)((double)srcWidth * ratio);
		}else if(height == RATIO){
			double ratio = ((double)destWidth) / ((double)srcWidth);
			destHeight = (int)((double)srcHeight * ratio);
		}
		
		BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = destImg.createGraphics();
		g.drawImage(srcImg, 0, 0, destWidth, destHeight, null);
		ImageIO.write(destImg, "jpg", dest);
	}
}
