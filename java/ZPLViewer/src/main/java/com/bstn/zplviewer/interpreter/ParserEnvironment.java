package com.bstn.zplviewer.interpreter;

import com.bstn.zplviewer.zpl.Field;
import com.bstn.zplviewer.zpl.Label;
import com.bstn.zplviewer.zpl.ZPLDocument;
import com.bstn.zplviewer.zpl.command.Command;
import com.bstn.zplviewer.zpl.command.Layer;

public class ParserEnvironment {
	private ZPLDocument zplDocument;
	private Label currentLabel;
	private Field currentField;
	
	public ParserEnvironment(String text) {
		this.zplDocument = new ZPLDocument(text);
		this.currentLabel = null;
		this.currentField = null;
	}
	
	public void startLabel() {
		if(currentLabel == null) {
			currentLabel = new Label();
		}else {
			System.err.println("Warning: Please ensure to close your label with '^XZ' before starting a new one.");
		}
	}
	
	public void endLabel() {
		if(currentLabel != null) {
			zplDocument.addLabel(currentLabel);
			currentLabel = null;
		}else {
			System.err.println("Warning: Please ensure to open your label with '^XA' before attempting to close it.");
		}
	}
	
	public void startField() {
		if(currentField == null) {
			currentField = new Field();
		}
	}
	
	public void endField() {
		if(currentField != null) {
			currentLabel.add(currentField);
			currentField = null;
		}
	}
	
	public void addCommand(Command command, String parameters) {
		if(command.getShortName().contains("^")) {
			if(currentField == null) {
				startField();
			}
			
			command.parse(parameters);
			
			if(command.getLayer() == Layer.PROPERTIES) {
				currentField.addPropertyCommand(command);
			}else if(command.getLayer() == Layer.RENDERING) {
				currentField.addRenderingCommand(command);
			}
		}else if(command.getShortName().contains("~")){
			command.parse(parameters);
			zplDocument.addConfiguration(command);
		}
	}
	
	
	public Label getCurrentLabel() {
		return currentLabel;
	}
	
	public void setCurrentLabel(Label currentLabel) {
		this.currentLabel = currentLabel;
	}
	
	public Field getCurrentField() {
		return currentField;
	}
	
	public void setCurrentField(Field currentField) {
		this.currentField = currentField;
	}

	public ZPLDocument getZplDocument() {
		return zplDocument;
	}

	public void setZplDocument(ZPLDocument zplDocument) {
		this.zplDocument = zplDocument;
	}
	
}
