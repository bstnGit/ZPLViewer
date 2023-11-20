package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;

public class BarcodeDefault extends Command {
	
	/*
	 * Accepted Values: 1 - 10
	 * Default Value: 2
	 */
	private int moduleWidth;
	
	/*
	 * Accepted Values: 2.0 to 3.0, in 0.1 increments
	 * Default Value: 3.0
	 */
	private float widthRatio;
	
	/*
	 * Accepted Values: 0 - 320
	 * Default Value: 10
	 */
	private int barCodeHeight;
	
	public BarcodeDefault() { /* --- */ }
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.setBarcodeModuleWidth(moduleWidth);
		env.setBarcodeWidthRatio(widthRatio);
		env.setGlobalBarcodeHeight(barCodeHeight);

	}
	
	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 3, String.valueOf(ZPL.delimiter));
		
		this.moduleWidth = Converter.parameterToIntWithRange(this, "module width", parameterList.get(0), 1, 10, 2);
		this.widthRatio = Converter.parameterToFloatWithRange(this, "wide bar to narrow bar width ratio", parameterList.get(1), 2.0f, 3.0f, 1, 2.0f);
		this.barCodeHeight = Converter.parameterToIntWithRange(this, "bar code height", parameterList.get(2), 0, 320, 10);
	}
	
	@Override
	public String getShortName() {
		return "^BY";
	}

	@Override
	public String getLongName() {
		return "Barcode Default";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}
	
	@Override
	public String toString() {
		return "BarCodeDefault [moduleWidth=" + moduleWidth + ", widthRatio=" + widthRatio + ", barCodeHeight="
				+ barCodeHeight + "]";
	}

	public int getModuleWidth() {
		return moduleWidth;
	}

	public void setModuleWidth(int moduleWidth) {
		this.moduleWidth = moduleWidth;
	}

	public double getWidthRatio() {
		return widthRatio;
	}

	public void setWidthRatio(float widthRatio) {
		this.widthRatio = widthRatio;
	}

	public int getBarCodeHeight() {
		return barCodeHeight;
	}

	public void setBarCodeHeight(int barCodeHeight) {
		this.barCodeHeight = barCodeHeight;
	}
}
