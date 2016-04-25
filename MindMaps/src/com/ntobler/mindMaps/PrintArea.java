package com.ntobler.mindMaps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;

public class PrintArea implements Paintable{

	private PageFormat pageFormat;
	
	double scaleFactor = 1;
	
	private Rectangle2D pageArea;
	private Rectangle2D imageableArea;
	
	private Point2D origin;
	
	public PrintArea(PageFormat pageFormat) {
		this.pageFormat = pageFormat;
		
		pageArea = new Rectangle2D.Double();
		imageableArea = new Rectangle2D.Double();
		
	}
	
	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}
	
	public void setDest(Point2D dest) {
		
		scaleFactor = (dest.getX() - origin.getX()) / pageFormat.getWidth();;
		
		pageArea.setRect(
				origin.getX(),
				origin.getY(),
				pageFormat.getWidth()  * scaleFactor,
				pageFormat.getHeight() * scaleFactor
				);
		
		imageableArea.setRect(
				origin.getX() + (pageFormat.getImageableX() * scaleFactor),
				origin.getY() + (pageFormat.getImageableY() * scaleFactor),
				(pageFormat.getImageableX() + pageFormat.getImageableWidth()) * scaleFactor,
				(pageFormat.getImageableY() + pageFormat.getImageableHeight()) * scaleFactor
				);
		
	}
	
	public AffineTransform getTransformation() {

		AffineTransform transform = new AffineTransform();
		
		transform.translate(-pageArea.getX(), -pageArea.getY());
		transform.scale(1/scaleFactor, 1/scaleFactor);
		
		return transform;
	}
	
	
	@Override
	public void paint(Graphics2D g2, DrawingLayer layer) {

		if (layer.equals(DrawingLayer.AREAS)) {
			g2.setColor(new Color(0xEEEEEE));
			g2.fill(pageArea);
			//g2.setColor(Color.BLACK);
			//g2.draw(pageArea);
			//g2.draw(imageableArea);
		}
		
	}

}
