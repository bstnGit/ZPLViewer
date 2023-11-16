package com.bstn.zplviewer.zpl;

import java.util.ArrayList;
import java.util.List;

import com.bstn.zplviewer.zpl.command.Command;

public class Field {
	private List<Command> propertiesLayer;
	private List<Command> renderingLayer;
	
	public Field() {
		this.propertiesLayer = new ArrayList<>();
		this.renderingLayer = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Field [propertiesLayer=" + propertiesLayer + ", renderingLayer=" + renderingLayer + "]";
	}

	public List<Command> getPropertiesLayer() {
		return propertiesLayer;
	}
	
	public void addPropertyCommand(Command command) {
	    propertiesLayer.add(command);
	}

	public void setPropertiesLayer(List<Command> propertiesLayer) {
		this.propertiesLayer = propertiesLayer;
	}

	public List<Command> getRenderingLayer() {
		return renderingLayer;
	}
	
	public void addRenderingCommand(Command command) {
	    renderingLayer.add(command);
	}

	public void setRenderingLayer(List<Command> renderingLayer) {
		this.renderingLayer = renderingLayer;
	}
}
