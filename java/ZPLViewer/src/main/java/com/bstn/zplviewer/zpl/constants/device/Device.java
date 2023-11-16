package com.bstn.zplviewer.zpl.constants.device;

import java.util.HashMap;
import java.util.Map;

public class Device {
	private String name;
	private Map<String, Graphic> storage;

	public Device(String name) {
		this.name = name;
		this.storage = new HashMap<>();
	}

	@Override
	public String toString() {
		return getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStorage(Map<String, Graphic> storage) {
		this.storage = storage;
	}

	public void store(String fileName, Graphic data) {
		storage.put(fileName, data);
	}

	public Graphic retrieve(String fileName) {
		return storage.get(fileName);
	}

	public String getName() {
		return name + ":";
	}

	public Map<String, Graphic> getStorage() {
		return storage;
	}
}
