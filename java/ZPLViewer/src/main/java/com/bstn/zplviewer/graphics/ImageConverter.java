package com.bstn.zplviewer.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageConverter {
    
    public static void main(String args[]) {
    	String data = "0001fffffc0003fffff80003fffff00007fffff0000fffffe0001fffffc0001fffffc0003fffff80007fffff0000fffffe0000fffffe0001fffffc0003fffff80007fffff00007fffff0000fffffe0001fffffc0003fffff80003fffff80007fffff00000000000000";
        
    	ImageConverter imageConverter = new ImageConverter();

    	try {
            ImageIO.write(imageConverter.dataToBufferedImage(data, 12), "PNG", new File("test.png"));
            System.out.println("Created file!");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public BufferedImage dataToBufferedImage(String data, int bytesPerRow) {
    	data = data.replaceAll("\\s+","").replaceAll("\n", "");
    	
    	int width = (bytesPerRow * 2) * 4;
    	int height = (data.length() / bytesPerRow) / 2;
    	
    	BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
    	bufferedImage.getRaster().setDataElements(0, 0, width, height, dataToByteArray(data));
        
        return bufferedImage;
    }
    
	private byte[] dataToByteArray(String data) {
        byte[] converted = new byte[data.length() * 4];
        
        for (int i = 0, j = 0; i < data.length(); i++) {
            byte digit  = (byte) Character.getNumericValue(data.charAt(i));
            
            for (int mask = 0x8; mask != 0; mask >>= 1) {
                converted[j++] = (byte)((digit & mask) != 0 ? 0 : 255);
            }
        }
        
        return converted;
    }
}