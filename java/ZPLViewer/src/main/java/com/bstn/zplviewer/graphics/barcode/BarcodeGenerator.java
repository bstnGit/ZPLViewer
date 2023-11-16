package com.bstn.zplviewer.graphics.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.bstn.zplviewer.zpl.constants.ZCode128Mode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class BarcodeGenerator {	
	
	public BufferedImage getBarCode128(String data, int width, int height, boolean checkDigitEnabled, ZCode128Mode mode) {
	    Code128Writer writer = new Code128Writer();
	    
		Map<EncodeHintType, String> hints = new HashMap<>();
		
	    if(data.startsWith(">9")) {
		    hints.put(EncodeHintType.FORCE_CODE_SET, "A");
		    data = data.substring(2, data.length());
	    }else if(data.startsWith(">:")) {
		    hints.put(EncodeHintType.FORCE_CODE_SET, "B");
		    data = data.substring(2, data.length());
	    }else if(data.startsWith(">;")) {
		    hints.put(EncodeHintType.FORCE_CODE_SET, "C");
		    data = data.substring(2, data.length());

		    if(data.startsWith(">8")) {
			    data = data.substring(2, data.length());
		    }
	    }



	    BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128, width, height, hints);
        
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	    try {
	        MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
	        
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            BufferedImage image = ImageIO.read(byteArrayInputStream);

            return removeWhiteMargins(image);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	

	public static BufferedImage removeWhiteMargins(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Find left boundary
        int left = 0;
        for (int x = 0; x < width; x++) {
            if (!isWhiteColumn(originalImage, x)) {
                left = x;
                break;
            }
        }

        // Find right boundary
        int right = width - 1;
        for (int x = width - 1; x >= 0; x--) {
            if (!isWhiteColumn(originalImage, x)) {
                right = x;
                break;
            }
        }

        // Create a new image without white margins
        int newWidth = right - left + 1;
        BufferedImage trimmedImage = new BufferedImage(newWidth, height, originalImage.getType());

        // Copy non-white pixels to the new image
        for (int x = left; x <= right; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = originalImage.getRGB(x, y);
                trimmedImage.setRGB(x - left, y, rgb);
            }
        }

        return trimmedImage;
    }

    private static boolean isWhiteColumn(BufferedImage image, int x) {
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            if ((image.getRGB(x, y) & 0xFFFFFF) != 0xFFFFFF) {
                return false; // Found a non-white pixel
            }
        }
        return true; // All pixels in the column are white
    }

	public char calculateCheckDigit(String data) {
	    int sum = 0;
	    boolean evenPosition = false;

	    for (int i = data.length() - 1; i >= 0; i--) {
	        int digit = Character.getNumericValue(data.charAt(i));

	        if (evenPosition) {
	            digit *= 2;
	            digit = digit > 9 ? digit - 9 : digit;
	        }

	        sum += digit;
	        evenPosition = !evenPosition;
	    }

	    int mod = sum % 10;
	    return mod == 0 ? '0' : (char) (10 - mod + '0');
	}
}