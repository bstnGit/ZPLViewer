package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.device.Device;
import com.bstn.zplviewer.zpl.constants.device.Graphic;
import com.bstn.zplviewer.zpl.constants.device.ZDevice;

public class DownloadGraphics extends Command {
	
	/*
	 * Accepted Values: R:, E:, B:, A:
	 * Default Value R:
	 */
	private Device deviceToStoreImage;
	
	/*
	 * Accepted Values 1 - 8 alphanumeric characters
	 * Default Value: if a name is not specified, UNKNOWN is used
	 */
	private String imageName;
	
	/*
	 * Fixed Value: .GRF
	 */
	private String extension;
	
	private int totalNumberOfBytesInGraphic;
	
	private int numberOfBytesPerRow;
	
	/*
	 * The data string defines the image and is an ASCII hexadecimal
	 * representation of the image. Each character represents a horizontal
 	 * nibble of four dots.
	 */ 
	private String data;

	@Override
	public void execute(InterpreterEnvironment env) { /* --- */ }

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 5, String.valueOf("[\\" + ZPL.delimiter + ":]"));
		
		this.deviceToStoreImage = ZDevice.getDeviceByName(Converter.parametersToCharacter(this, "device to store image", new char[] {'R', 'E', 'B', 'A'}, parameterList.get(0), 'R'));
		
		String nameWithExt = parameterList.get(1);
		if(nameWithExt != Converter.parameterDefault) {
			String tempImageName = nameWithExt.split("\\.")[0];

			if(tempImageName.equals("GRF") || tempImageName.isEmpty()) {
				this.imageName = "UNKNOWN";
			} else {
				this.imageName = Converter.parameterToString(this, "image name", 8, tempImageName, "UNKNOWN");
			}
			
		}else {
			this.imageName = "UNKNOWN";
		}
		
		this.extension = ".GRF";
				
		
		this.totalNumberOfBytesInGraphic = Converter.parameterToInt(this, "total number of bytes in graphic", parameterList.get(2), 0);
		this.numberOfBytesPerRow = Converter.parameterToInt(this, "number of bytes per row", parameterList.get(3), 0);
		this.data = Converter.parameterToString(this, "defining image data", -1, parameterList.get(4), "");
		
		String fileName = imageName + extension;
		deviceToStoreImage.store(fileName, new Graphic(fileName, data, totalNumberOfBytesInGraphic, numberOfBytesPerRow));
	}

	@Override
	public String toString() {
		return "DownloadGraphics [deviceToStoreImage=" + deviceToStoreImage + ", imageName=" + imageName
				+ ", extension=" + extension + ", totalNumberOfBytesInGraphic=" + totalNumberOfBytesInGraphic
				+ ", numberOfBytesPerRow=" + numberOfBytesPerRow + ", data=" + data + "]";
	}

	@Override
	public String getShortName() {
		return "~DG";
	}

	@Override
	public String getLongName() {
		return "Download Graphic";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}

	public Device getDeviceToStoreImage() {
		return deviceToStoreImage;
	}

	public void setDeviceToStoreImage(Device deviceToStoreImage) {
		this.deviceToStoreImage = deviceToStoreImage;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getTotalNumberOfBytesInGraphic() {
		return totalNumberOfBytesInGraphic;
	}

	public void setTotalNumberOfBytesInGraphic(int totalNumberOfBytesInGraphic) {
		this.totalNumberOfBytesInGraphic = totalNumberOfBytesInGraphic;
	}

	public int getNumberOfBytesPerRow() {
		return numberOfBytesPerRow;
	}

	public void setNumberOfBytesPerRow(int numberOfBytesPerRow) {
		this.numberOfBytesPerRow = numberOfBytesPerRow;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
