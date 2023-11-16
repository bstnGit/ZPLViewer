package com.bstn.zplviewer.zpl;

import java.util.ArrayList;
import java.util.List;

public class Label {
	private List<Field> fields;

	public Label() {
		fields = new ArrayList<>();
	}
	
	public void add(Field field) {
		fields.add(field);
	}

	@Override
	public String toString() {
		return "Label [fields=" + fields + "]";
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
