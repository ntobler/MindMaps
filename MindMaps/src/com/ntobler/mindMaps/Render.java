package com.ntobler.mindMaps;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Render {

	private List<Paintable> paintables;
	private List<DrawingLayer> drawingLayers;
	
	
	public Render() {
		
		paintables = new ArrayList<Paintable>();
		drawingLayers = new ArrayList<DrawingLayer>();
	}
	
	public void addDrawingLayer(DrawingLayer layer) {
		drawingLayers.add(layer);
	}
	
	public void addPaintable(Paintable p) {
		paintables.add(p);
	}
	
	public void removePaintable(Paintable p) {
		paintables.remove(p);
	}
	
    protected void paint(Graphics2D g2) {
        
    	for(DrawingLayer layer: drawingLayers) {
	        for (Paintable p: paintables) {
	        	
	        	p.paint(g2, layer);
	        }
        }

    }

}
