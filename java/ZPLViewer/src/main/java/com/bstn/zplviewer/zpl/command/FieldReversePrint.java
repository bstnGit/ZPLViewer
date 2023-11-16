package com.bstn.zplviewer.zpl.command;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;

public class FieldReversePrint extends Command {

	@Override
	public void execute(InterpreterEnvironment env) {
		env.setFieldReversed(true);
	}

	@Override
	public void parse(String parameters) { /* --- */ }
	
	@Override
	public String getShortName() {
		return "^FR";
	}

	@Override
	public String getLongName() {
		return "Field Reverse Print";
	}

	@Override
	public Layer getLayer() {
		return Layer.PROPERTIES;
	}
	
	@Override
	public String toString() {
		return "FieldReversePrint";
	}
}
