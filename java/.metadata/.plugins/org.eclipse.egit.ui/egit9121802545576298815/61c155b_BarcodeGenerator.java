package com.bstn.zplviewer.graphics.barcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bstn.zplviewer.zpl.constants.ZCode128Mode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class BarcodeGenerator {	
	
	public byte[] getBarCode128(String data, int width, int height, boolean checkDigitEnabled, ZCode128Mode mode) {
	    Code128Writer writer = new Code128Writer();

	    BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128, width, height);
	    
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    
	    try {
	        MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
	        return outputStream.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	private void calculateSubset(String data, ZCode128Mode mode) {
		Map<EncodeHintType, String> hints = new HashMap<>();
	    
	    switch (mode) {
			case NO_SELECTED_MODE -> {
				if(data.startsWith(">9")) {
				    hints.put(EncodeHintType.FORCE_CODE_SET, "A");
				    data = data.substring(2, data.length() -1);
				    
				}else if(data.startsWith(">:")) {
				    hints.put(EncodeHintType.FORCE_CODE_SET, "B");
				    data = data.substring(2, data.length() -1);
				    
				}else if(data.startsWith(">;")) {
				    hints.put(EncodeHintType.FORCE_CODE_SET, "C");
				    data = data.substring(2, data.length()).replaceAll("[a-zA-Z]", "");
				    
				}else if(data.startsWith(">;>8")) {
				    hints.put(EncodeHintType.FORCE_CODE_SET, "C");
				    data = data.substring(4, data.length()).replaceAll("/[^A-Za-z]/", "");
				}
			}

			case UCC_CASE_MODE -> {
				// More than 19 digits in data are eliminated
				data = data.substring(0, (data.length() <= 19) ? data.length() : 19);
				// Fewer than 19 digits in data are filled with zeros
				data = String.format("%-" + 19 + "s", data).replace(' ', '0');
			}
			
			case AUTOMATIC_MODE -> { /** is automatically assigned **/ }
			
			case UCC_EAN_MODE-> {
				
			}
		
			default ->
				throw new IllegalArgumentException("Unexpected value: " + mode);
		}		
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
