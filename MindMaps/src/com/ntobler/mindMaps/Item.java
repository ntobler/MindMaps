package com.ntobler.mindMaps;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 7579530358694230L;
	
	Complex pos;
	
	public Item() {
		
	}
	
	public void setPos(Complex pos) {
		this.pos = pos;
	}
	
	public Complex getPos() {
		return pos;
	}
	
	public void paintAbsolute(Graphics2D g2, DrawingLayer layer) {
		
		AffineTransform absolutTransform = g2.getTransform();
		
		AffineTransform translatedTransform = new AffineTransform(absolutTransform);
		translatedTransform.translate(pos.x, pos.y);
    	g2.setTransform(translatedTransform);
    	
    	paintTranslated(g2, layer);
    	
    	g2.setTransform(absolutTransform);
	}

	protected void paintTranslated(Graphics2D g2, DrawingLayer layer) {
		
	}
	
	public Rectangle2D getBounds() {
		return null;
	}
}
