package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.device.Device;
import com.bstn.zplviewer.zpl.constants.device.Graphic;
import com.bstn.zplviewer.zpl.constants.device.ZDevice;

public class RecallGraphic extends Command {
	
	/*
	 * Accepted Values: R:, E:, B:, A:
	 * Default Value: search priority (R:, E:, B:, and A:)
	 */
	private Device sourceDeviceOfStoredImage;
	
	/*
	 * Accepted Values: 1 to 8 alphanumeric characters
	 * Default Value: if a name is not specified, UNKNOWN is used
	 */
	private String nameOfStoredImage;
	
	/*
	 * Fixed Value: .GRF
	 */
	private String extension;
	
	/*
	 * Accepted Values: 1 to 10
     * Default Value: 1
	 */
	private int magnificationFactorXAxis;
	
	/*
	 * Accepted Values: 1 to 10
	 * Default Value: 1
	 */
	private int magnificationFactorYAxis;

	@Override
	public void execute(InterpreterEnvironment env) {
		String fileName = nameOfStoredImage + extension;
		
		Graphic graphic = sourceDeviceOfStoredImage.retrieve(fileName);
		
		if(graphic != null) {
			env.drawGraphic(graphic.getData(), graphic.getNumberOfBytesPerRow(), magnificationFactorXAxis, magnificationFactorYAxis);
		}else {
			System.out.println("Graphic: " + fileName + " not found in storage " + sourceDeviceOfStoredImage);
		}
		
	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 5, String.valueOf("[\\" + ZPL.delimiter + ":]"));
		
		this.sourceDeviceOfStoredImage = ZDevice.getDeviceByName(Converter.parametersToCharacter(this, "device to store image", new char[] {'R', 'E', 'B', 'A'}, parameterList.get(0), 'R'));
		
		String nameWithExt = parameterList.get(1);
		if(nameWithExt != Converter.parameterDefault) {
			String tempImageName = nameWithExt.split("\\.")[0];

			if(tempImageName.equals("GRF") || tempImageName.isEmpty()) {
				this.nameOfStoredImage = "UNKNOWN";
			} else {
				this.nameOfStoredImage = Converter.parameterToString(this, "image name", 8, tempImageName, "UNKNOWN");
			}
			
		}else {
			this.nameOfStoredImage = "UNKNOWN";
		}
		
		this.extension = ".GRF";
		
        this.magnificationFactorXAxis = Converter.parameterToIntWithRange(this, "magnification factor on the x axis", parameterList.get(3), 1, 10, 1);
        this.magnificationFactorYAxis = Converter.parameterToIntWithRange(this, "magnification factor on the y axis", parameterList.get(4), 1, 10, 1);
	}

	@Override
	public String getShortName() {
		return "^XG";
	}

	@Override
	public String getLongName() {
		return "Recall Graphic";
	}

	@Override
	public Layer getLayer() {
		return Layer.RENDERING;
	}

	@Override
	public String toString() {
		return "RecallGraphic [sourceDeviceOfStoredImage=" + sourceDeviceOfStoredImage + ", nameOfStoredImage="
				+ nameOfStoredImage + ", extension=" + extension + ", magnificationFactorXAxis="
				+ magnificationFactorXAxis + ", magnificationFactorYAxis=" + magnificationFactorYAxis + "]";
	}

	public Device getSourceDeviceOfStoredImage() {
		return sourceDeviceOfStoredImage;
	}

	public void setSourceDeviceOfStoredImage(Device sourceDeviceOfStoredImage) {
		this.sourceDeviceOfStoredImage = sourceDeviceOfStoredImage;
	}

	public String getNameOfStoredImage() {
		return nameOfStoredImage;
	}

	public void setNameOfStoredImage(String nameOfStoredImage) {
		this.nameOfStoredImage = nameOfStoredImage;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getMagnificationFactorXAxis() {
		return magnificationFactorXAxis;
	}

	public void setMagnificationFactorXAxis(int magnificationFactorXAxis) {
		this.magnificationFactorXAxis = magnificationFactorXAxis;
	}

	public int getMagnificationFactorYAxis() {
		return magnificationFactorYAxis;
	}

	public void setMagnificationFactorYAxis(int magnificationFactorYAxis) {
		this.magnificationFactorYAxis = magnificationFactorYAxis;
	}
}
