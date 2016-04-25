package com.ntobler.mindMaps;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entry extends Item {

	private String text;
	
	private Entry parent;
	private List<Entry> subEntries;
	
	private Rectangle2D bounds;
	
	public Entry() {
		super();
		
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void addSubEntry (Entry subEntry) {
		
		if (subEntries == null) {
			subEntries = new ArrayList<Entry>();
		}
		subEntries.add(subEntry);
	}
	
	
	
	
	@Override
	public void paintAbsolute(Graphics2D g2, DrawingLayer layer) {
		super.paintAbsolute(g2, layer);
		
		if (layer.equals(DrawingLayer.CONNECTIONS)) {
			if (subEntries != null) {
				for (Entry subEntry: subEntries) {
					drawConnection(g2, subEntry);
				}
			}
		}
	}

	@Override
	protected void paintTranslated(Graphics2D g2, DrawingLayer layer) {
		super.paintTranslated(g2, layer);
		
		if (layer.equals(DrawingLayer.ITEMS)) {
			drawEntry(g2);
		}
		
	}
	
	private void drawConnection(Graphics2D g2, Entry destEntry) {
		
		Point2D thisPos = this.getPos();
		Point2D destPos = destEntry.getPos();
		
		g2.setPaint(Color.BLACK);
	    g2.drawLine((int)thisPos.getX(), (int)thisPos.getY(), (int)destPos.getX(), (int)destPos.getY());
		
	}
	
	private void drawEntry(Graphics2D g2) {
		
		g2.setFont(Workspace.NORMAL_FONT);

		FontMetrics metrics = g2.getFontMetrics(g2.getFont());
		
		double ascent = metrics.getAscent();
		double descent = metrics.getDescent();
		double width;
		if (text != null) {
			width = metrics.stringWidth(text);
		}
		else {
			width = 0;
		}
	    double recWidth = width + 20;
	    
	    Rectangle2D rect = new Rectangle2D.Double(-recWidth / 2, -ascent - 5, recWidth, ascent + descent + 10);
	    bounds = new Rectangle2D.Double(getPos().getX() - (recWidth / 2), getPos().getY() - ascent - 5, recWidth, ascent + descent + 10);
	    
	    g2.setPaint(Color.WHITE);
	    
	    g2.fill(rect);
		
	    g2.setPaint(Color.BLACK);
	    
	    if (text != null) {
			g2.drawString(text, (int) (-width / 2), (int)0);
		}

	}
	
	@Override
	public Rectangle2D getBounds() {
		return bounds;
	}

	public Entry getParent() {
		return parent;
	}

	public void setParent(Entry parent) {
		this.parent = parent;
	}
}