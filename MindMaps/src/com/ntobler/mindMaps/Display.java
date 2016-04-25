package com.ntobler.mindMaps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class Display extends JPanel {

	private Render render;
	private RenderTransformer transformer;
	
	public Display(Render render, RenderTransformer transformer) {
		
		this.render = render;
		this.transformer = transformer;
		
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(800,1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        
        g2.setTransform(transformer.getTransformation());
        
        
        g2.setPaint(Color.BLACK);
		
		AffineTransform gameTransform = transformer.getTransformation();
    	g2.setTransform(gameTransform);
        
        render.paint(g2);
        
        g2.dispose();
    }
    
	@Override
	public void setSize(Dimension size) {
    	
    }
	
}
