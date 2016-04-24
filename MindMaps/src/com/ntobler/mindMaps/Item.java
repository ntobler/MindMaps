package com.ntobler.mindMaps;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Item {

	Complex pos;
	
	public Item() {
		
	}
	
	public void setPos(Complex pos) {
		this.pos = pos;
	}
	
	public Complex getPos() {
		return pos;
	}
	
	public void paintAbsolute(Graphics2D g2) {
		
		AffineTransform absolutTransform = g2.getTransform();
		
		AffineTransform translatedTransform = new AffineTransform(absolutTransform);
		translatedTransform.translate(pos.x, pos.y);
    	g2.setTransform(translatedTransform);
    	
    	paintTranslated(g2);
    	
    	g2.setTransform(absolutTransform);
	}

	protected void paintTranslated(Graphics2D g2) {
		
	}
}
