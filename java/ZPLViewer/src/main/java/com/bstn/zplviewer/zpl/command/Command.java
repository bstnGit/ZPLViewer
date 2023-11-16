package com.bstn.zplviewer.zpl.command;

import com.bstn.zplviewer.interpreter.InterpreterEnvironment;

public abstract class Command {
	
	private int lineNumber;
	private int startIndex;
	
	public abstract void execute(InterpreterEnvironment env);

	public abstract void parse(String parameters);

	public abstract String getShortName();

	public abstract String getLongName();

	public abstract Layer getLayer();

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
}
