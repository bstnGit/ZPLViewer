package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.ZColor;

public class GraphicBox extends Command {
	
	/*
	 * Accepted Values: t - 32000
	 * Default Value: value used for thickness (t) or 1
	 */
	private int boxWidth;
	
	/*
	 * Accepted Values: t - 32000
	 * Default Value: value used for thickness (t) or 1
	 */
	private int boxHeight;
	
	/*
	 * Accepted Values: 1 to 32000
	 * Default Value: 1
	 */
	private int borderThickness;
	
	/*
	 * Accepted Values: B / W
	 * Default Value: B
	 */
	private ZColor lineColor;
	
	/*
	 * Accepted Values: 0 (no rounding) to 8 (heaviest rounding)
	 * Default Value: 0
	 */
	private int degreeOfCornerRounding;
	
	public GraphicBox() { /* --- */ }
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.drawRectangle(boxWidth, boxHeight, borderThickness, lineColor, degreeOfCornerRounding);
	}
	
	@Override
	public void parse(String parameters) {
        List<String> parameterList = Converter.parametersStringToList(parameters, 5, String.valueOf(ZPL.delimiter));

        this.borderThickness = Converter.parameterToIntWithRange(this, "border thickness", parameterList.get(2), 1, 32000, 1);
        this.boxWidth = Converter.parameterToIntWithRange(this, "box witdh", parameterList.get(0), borderThickness, 32000, borderThickness);
        this.boxHeight = Converter.parameterToIntWithRange(this, "box height", parameterList.get(1), borderThickness, 32000, borderThickness);
        
        this.lineColor = Converter.parameterToColor(this, "line color", parameterList.get(3), ZColor.BLACK);
        this.degreeOfCornerRounding = Converter.parameterToIntWithRange(this, "box height", parameterList.get(4), 0, 8, 0);
	}

	@Override
	public String getShortName() {
		return "^GB";
	}

	@Override
	public String getLongName() {
		return "Graphic Box";
	}

	@Override
	public Layer getLayer() {
		return Layer.RENDERING;
	}
	
	@Override
	public String toString() {
		return "GraphicBox [boxWidth=" + boxWidth + ", boxHeight=" + boxHeight + ", borderThickness=" + borderThickness
				+ ", lineColor=" + lineColor + ", degreeOfCornerRounding=" + degreeOfCornerRounding + "]";
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}

	public int getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	public ZColor getLineColor() {
		return lineColor;
	}

	public void setLineColor(ZColor lineColor) {
		this.lineColor = lineColor;
	}

	public int getDegreeOfCornerRounding() {
		return degreeOfCornerRounding;
	}

	public void setDegreeOfCornerRounding(int degreeOfCornerRounding) {
		this.degreeOfCornerRounding = degreeOfCornerRounding;
	}
}
