package com.bstn.zplviewer.zpl.command;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;
import com.bstn.zplviewer.util.Converter;

public class FieldData extends Command {
	
	/*
	 * Accepted Values: any data string up to 3072 bytes
	 * Default Value: none — a string of characters must be entered
	 */
	private String data;
	
	public FieldData() { /* --- */ }
	
	@Override
	public void execute(InterpreterEnvironment env) {
		env.drawFieldData(data);
	}
	
	@Override
	public void parse(String parameter) {
		data = Converter.parameterToString(this, "data", 3072, parameter, null);
	}
	
	@Override
	public String getShortName() {
		return "^FD";
	}

	@Override
	public String getLongName() {
		return "Field Data";
	}

	@Override
	public Layer getLayer() {
		return Layer.RENDERING;
	}
	
	@Override
	public String toString() {
		return "FieldData [data=" + data + "]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
