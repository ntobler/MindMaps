package com.ntobler.mindMaps;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class PrintAreaTransformer {

	public static final Rectangle2D A4 = new Rectangle2D.Double(0, 0, 210, 297);
	
	private Rectangle2D printArea;
	private Rectangle2D sheet;

	
	public PrintAreaTransformer (Rectangle2D sheet, Rectangle2D printArea) {
		this.sheet = sheet;
		this.printArea = printArea;
	}
	
	public AffineTransform getTransformation() {

		AffineTransform transform = new AffineTransform();
		
		double xTranslate = printArea.getX() - sheet.getX();
		double yTranslate = printArea.getY() - sheet.getY();
		
		transform.translate(xTranslate, yTranslate);
		
		double xScale = sheet.getWidth() / printArea.getWidth();
		double yScale = sheet.getHeight() / printArea.getHeight();
		
		transform.scale(xScale, yScale);
		
		return transform;
	}

	public Rectangle2D getPrintArea() {
		return printArea;
	}

	public void setPrintArea(Rectangle2D printArea) {
		this.printArea = printArea;
	}

	public Rectangle2D getSheet() {
		return sheet;
	}

	public void setSheet(Rectangle2D sheet) {
		this.sheet = sheet;
	}
	
}
