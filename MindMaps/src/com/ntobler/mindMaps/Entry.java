package com.ntobler.mindMaps;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Entry extends Item {

	private String text;
	
	//private Entry parentEntry;
	private List<Entry> subEntries;
	
	public Entry() {
		super();
		
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void addSubEntry (Entry subEntry) {
		
		if (subEntries == null) {
			subEntries = new ArrayList<Entry>();
		}
		subEntries.add(subEntry);
	}
	
	
	
	
	@Override
	public void paintAbsolute(Graphics2D g2) {
		super.paintAbsolute(g2);
		
		if (subEntries != null) {
			for (Entry subEntry: subEntries) {
				drawConnection(g2, subEntry);
			}
		}
		
	}

	@Override
	protected void paintTranslated(Graphics2D g2) {
		super.paintTranslated(g2);
		
		drawEntry(g2);
		
	}
	
	private void drawConnection(Graphics2D g2, Entry destEntry) {
		
		Complex thisPos = this.getPos();
		Complex destPos = destEntry.getPos();
		
	    g2.drawLine((int)thisPos.x, (int)thisPos.y, (int)destPos.x, (int)destPos.y);
		
	}
	
	private void drawEntry(Graphics2D g2) {
		
		g2.setFont(Workspace.NORMAL_FONT);

		g2.setPaint(Color.BLACK);
		
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
	    double recWidth = width + 10;
	    
	    g2.setPaint(Color.GRAY);
	    
	    g2.fillRect((int)(-recWidth / 2), (int)-ascent-5, (int)recWidth, (int)(ascent + descent+10));
		
	    g2.setPaint(Color.BLACK);
	    
	    if (text != null) {
			g2.drawString(text, (int) (-width / 2), (int)0);
		}

	    
	}
}