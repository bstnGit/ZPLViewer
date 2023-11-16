package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.ZJustification;

public class FieldTypeset extends Command {

	/*
	 * Accepted Values: 0 - 32000
	 * Default Value: 0
	 */
	private int xAxisLocation;
	
	/*
	 * Accepted Values: 0 - 32000
	 * Default Value: 0
	 */
	private int yAxisLocation;
	
	public FieldTypeset() { /* --- */ }
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.setFieldX(xAxisLocation);
		env.setFieldY(yAxisLocation);
		env.setJustification(ZJustification.BOTTOM_LEFT);
	}
	
	@Override
	public void parse(String parameters) {
        List<String> parameterList = Converter.parametersStringToList(parameters, 2, String.valueOf(ZPL.delimiter));
        this.xAxisLocation = Converter.parameterToIntWithRange(this, "x axis location", parameterList.get(0), 0, 32000, 0);
        this.yAxisLocation = Converter.parameterToIntWithRange(this, "y axis location", parameterList.get(1), 0, 32000, 0);
	}
	
	@Override
	public String getShortName() {
		return "^FT";
	}

	@Override
	public String getLongName() {
		return "Field Typeset";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}
	
	@Override
	public String toString() {
		return "FieldTypeset [x=" + xAxisLocation + ", y=" + yAxisLocation + "]";
	}
	
	public int getX() {
		return xAxisLocation;
	}
	
	public void setX(int x) {
		this.xAxisLocation = x;
	}
	
	public int getY() {
		return yAxisLocation;
	}
	
	public void setY(int y) {
		this.yAxisLocation = y;
	}
}
