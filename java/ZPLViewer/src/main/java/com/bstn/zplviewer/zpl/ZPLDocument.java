package com.bstn.zplviewer.zpl;

import java.util.ArrayList;
import java.util.List;

import com.bstn.zplviewer.zpl.command.Command;

public class ZPLDocument {
	private String content;
	private List<Label> labels;
	private List<Command> configurations;
	
	public ZPLDocument(String content) {
		this.content = content;
		labels = new ArrayList<>();
		configurations = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "Document [content=" + content + ", label=" + labels + ", configuration=" + configurations + "]";
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Label> getLabels() {
		return labels;
	}
	
	public void addLabel(Label label) {
		this.labels.add(label);
	}
	
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
	public List<Command> getConfigurations() {
		return configurations;
	}
	
	public void addConfiguration(Command command) {
		this.configurations.add(command);
	}
	
	public void setConfigurations(List<Command> configurations) {
		this.configurations = configurations;
	}
	
	
}
