package com.ntobler.mindMaps;

public class DrawingLayer {

	public static final DrawingLayer AREAS = new DrawingLayer(0);
	public static final DrawingLayer CONNECTIONS = new DrawingLayer(1);
	public static final DrawingLayer ITEMS = new DrawingLayer(2);
	
	private final int id;
	
	public DrawingLayer(int id) {
		this.id = id;
	}
	
}
