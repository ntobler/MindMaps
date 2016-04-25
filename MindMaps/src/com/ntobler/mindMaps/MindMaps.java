package com.ntobler.mindMaps;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MindMaps {

	
	
		
	
	private static Workspace workspace;
	private static EntryPlacer entryPlacer;
	private static RenderTransformer transformer;
	private static Render displayRender;
	private static Render printRender;
	
	private static PageFormat pageFormat;
	private static PrintArea printArea;
	
	private static GUI gui;
	
	private static Display display;
	
	private static JMenuBar menuBar;
	private static JMenu menu;
	
	private static JTextField textField;
	
	public static void main(String [] args) {
		
		displayRender = new Render();
		displayRender.addDrawingLayer(DrawingLayer.AREAS);
		displayRender.addDrawingLayer(DrawingLayer.CONNECTIONS);
        displayRender.addDrawingLayer(DrawingLayer.ITEMS);
        
        printRender = new Render();
        printRender.addDrawingLayer(DrawingLayer.AREAS);
        printRender.addDrawingLayer(DrawingLayer.CONNECTIONS);
        printRender.addDrawingLayer(DrawingLayer.ITEMS);
        
        
        transformer = new RenderTransformer();
		display = new Display(displayRender,transformer);
		
		transformer.setScreenDimension(display.getSize());
		
		
		gui = GUI.getInstance();
		
		gui.setJMenuBar(setupMenuBar());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
		
		
        panel.add(display);
        
        textField = new JTextField();
        textField.setText("hellol");
        
        textField.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
            	//this section will execute only when user is editing the JTextField
                if(!(e.getKeyChar() == 27 || e.getKeyChar() == 65535)) {
                	entryPlacer.setText(textField.getText());
                }
            }
        });
        textField.requestFocus();
        panel.add(textField);
        
		gui.add(panel);
		
		workspace = new Workspace(transformer);
		entryPlacer = new EntryPlacer(workspace);
		entryPlacer.setListener(new EntryPlacer.Listener() {
			
			@Override
			public void onSubmited() {
				textField.setText("");
				
			}
		});
		displayRender.addPaintable(workspace);
		printRender.addPaintable(workspace);
		
		pageFormat = PrinterJob.getPrinterJob().defaultPage();
		pageFormat.setOrientation(PageFormat.LANDSCAPE);
		
		printArea = new PrintArea(pageFormat);
		printArea.setOrigin(new Point2D.Double(0, 0));
		printArea.setOrigin(new Point2D.Double(210, 297));
		displayRender.addPaintable(printArea);
		
		
		
		gui.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent evt) {
		            Component c = (Component)evt.getSource();
		            //render.setSize(c.getSize());
		        }
		});
		
		setupMouseListeners();
		
		onSomethingChanged();
	}
	
	private static void onSomethingChanged() {
		
		transformer.setScreenDimension(display.getSize());
        display.revalidate();
        gui.repaint();
	}
	
	private static JMenuBar setupMenuBar() {
		
		//Create the menu bar.
		menuBar = new JMenuBar();
		
		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		//a group of JMenuItems
		JMenuItem menuItem;
		menuItem= new JMenuItem("Save",  KeyEvent.VK_S);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File file = new File("save.mmp");
				workspace.save(file);
			}
			
		});

		menuItem = new JMenuItem("Open", KeyEvent.VK_O);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				File file = new File("save.mmp");
				workspace.open(file);
				onSomethingChanged();
			}
			
		});
		
		menuItem = new JMenuItem("Print", KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Printer printer = new Printer(printArea, displayRender);
				
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(printer, pageFormat);
				
				if(job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		return menuBar;
	}
	
	private static void setupMouseListeners() {
		
		
		gui.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				File file = new File("save.mmp");
				
				switch (arg0.getKeyCode()) {
					case KeyEvent.VK_A:
					/*Point p = MouseInfo.getPointerInfo().getLocation();
					Complex screenPos = new Complex(p.getY(), p.getY());
					Complex pos = new Complex(transformer.getGamePos(screenPos));
					*/
					//entryPlacer.pickParent();
						

					break;
				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		display.addMouseMotionListener(new MouseMotionListener() {


			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			}
			
		});
		
		display.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				transformer.addZoomTicks(0 - arg0.getWheelRotation());
				onSomethingChanged();
			}
			
		});
		
		display.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Complex screenPos = new Complex(arg0.getX(), arg0.getY() + gui.getJMenuBar().getHeight());
				Complex pos = new Complex(transformer.getGamePos(screenPos));
				Point2D pick = new Point2D.Double(pos.x, pos.y);
				
				if (arg0.isShiftDown()) {
					printArea.setOrigin(pick);
				}
				else if (arg0.isAltDown()) {
					printArea.setDest(pick);
				}
				else if (arg0.isControlDown()) {
					Item i = workspace.getPickedItem(pick);
					if (i != null) {
						entryPlacer.setParent((Entry)i);
					}
				}
				else {
					entryPlacer.pick(pos);
				}
				onSomethingChanged();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {


			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

				
			}


		});
		
	}
	

}
