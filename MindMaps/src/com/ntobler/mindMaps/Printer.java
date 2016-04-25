package com.ntobler.mindMaps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Printer implements Printable {

	private final PrintArea printArea;
	private final Render render;
	
	public Printer(PrintArea printArea, Render render) {
		this.printArea = printArea;
		this.render = render;
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		
		if (page > 0) {
	         return NO_SUCH_PAGE;
	    }
		
		Graphics2D g2 = (Graphics2D)g;
		
	    g2.transform(printArea.getTransformation());

	    render.paint(g2);
	    
	    return PAGE_EXISTS;
		
	}

}
