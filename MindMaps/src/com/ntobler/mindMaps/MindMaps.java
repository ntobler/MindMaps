package com.ntobler.mindMaps;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MindMaps {

	private static GUI gui;
	private static Render render;
	private static JTextField textField;	
	
	private static Workspace workspace;
	private static EntryPlacer entryPlacer;
	private static RenderTransformer transformer;
	
	public static void main(String [] args) {
		
		render = new Render();
		
		gui = GUI.getInstance();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        render = new Render();
        panel.add(render);
        
        textField = new JTextField();
        textField.setText("hellol");
        
        textField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				entryPlacer.setText(textField.getText());
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });

        panel.add(textField);
        
		gui.add(panel);
		
		transformer = new RenderTransformer(render.getSize());
		workspace = new Workspace(transformer);
		entryPlacer = new EntryPlacer(workspace);
		render.addPaintable(workspace);
		
		gui.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent evt) {
		            Component c = (Component)evt.getSource();
		            //render.setSize(c.getSize());
		        }
		});
		
		setupMouseListeners();
		
		
		
		Entry a = new Entry();
		a.setPos(Complex.ZERO);
		a.setText("Parent");
		workspace.addItem(a);
		
		entryPlacer.setParent(a);
		
		transformer.setFocus(a);
	
		
		onSomethingChanged();
	}

	
	private static void onSomethingChanged() {
		
		transformer.setScreenDimension(render.getSize());
        render.revalidate();
        gui.repaint();
	}
	
	private static void setupMouseListeners() {
		
		
		gui.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				switch (arg0.getKeyCode()) {
					case KeyEvent.VK_SPACE:
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
		
		render.addMouseMotionListener(new MouseMotionListener() {


			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
			}
			
		});
		
		render.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				transformer.addZoomTicks(0 - arg0.getWheelRotation());
				onSomethingChanged();
			}
			
		});
		
		render.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Complex screenPos = new Complex(arg0.getX(), arg0.getY());
				Complex pos = new Complex(transformer.getGamePos(screenPos));
				
				entryPlacer.pick(pos);
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
