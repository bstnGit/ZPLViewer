package com.bstn.zplviewer.interpreter;

public class Token {
	private Type type;
	private String value;
	
	public Token(Type type, String value) {
		this.type = type;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return type + " : " + value;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
