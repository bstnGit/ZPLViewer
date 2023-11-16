package com.bstn.zplviewer.zpl.command;

import java.util.List;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;
import com.bstn.zplviewer.zpl.ZPL;
import com.bstn.zplviewer.zpl.constants.ZFont;
import com.bstn.zplviewer.zpl.constants.ZOrientation;

public class ScalableBitmappedFont extends Command {
	
	/*
	 * Accepted Values: A through Z, and 0 to 9
	 * Default Value: 0
	 */
	private ZFont font;
	
	/*
	 * Accepted Values:
	 * N = normal
	 * R = rotated 90 degrees (clockwise)
	 * I = inverted 180 degrees
	 * B = read from bottom up, 270 degrees
	 * Default Value: the last accepted ^FW value or the ^FW default
	 */
	private ZOrientation fieldOrientation;
	
	/*
	 * Accepted Values: 10 to 32000
	 * Default Value: last accepted ^CF
	 */
	private int characterHeight;
	
	/*
	 * Accepted Values: 10 to 32000
	 * Default Value: last accepted ^CF
	 */
	private int characterWidth;
	
	
	@Override
	public void execute(InterpreterEnvironment env) {

	}

	@Override
	public void parse(String parameters) {
		List<String> parameterList = Converter.parametersStringToList(parameters, 3, String.valueOf(ZPL.delimiter));
		
		this.font = ZFont.getByCharacter(this, Converter.parametersToFontName(this, "Specified Default Font", parameterList.get(0), '0'));
		this.fieldOrientation = ZOrientation.getRotationByName(Converter.parametersToCharacter(this, "orientation", new char[] {'N', 'R', 'I', 'B'}, parameterList.get(0), 'N'));
		
	}

	@Override
	public String getShortName() {
		return "^A";
	}

	@Override
	public String getLongName() {
		return "Scalable/Bitmapped Font";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}

}
