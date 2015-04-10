package com.quya.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectLine implements Serializable{
	private List<Object> objects=new ArrayList<Object>();

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	
}
