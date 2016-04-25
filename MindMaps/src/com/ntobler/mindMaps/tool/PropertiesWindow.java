package com.ntobler.mindMaps.tool;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.ntobler.mindMaps.Entry;
import com.ntobler.mindMaps.MindMaps;
import com.ntobler.mindMaps.Workspace;

public class PropertiesWindow extends JFrame {
	
	private static final long serialVersionUID = -6420861426405481264L;
	
	private JFormattedTextField textField;
	
	private final Entry entry;
	private Workspace w;
	
	public PropertiesWindow(Entry entry, Workspace w) {
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					keyPressed(e);
	            }
	            return false;
			}
        	
        });
		
		this.entry = entry;
	    this.w = w;
	    JPanel panel = new JPanel();
	    this.add(panel);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    
	    textField = new JFormattedTextField();
	    textField.setText(entry.getText());
	    panel.add(textField);
		
	    
		//add(panel);
		textField.setSize(300, 200);
		pack();
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(arg0.isShiftDown()) {
				textField.setText(textField.getText() + "\n");
			}
			else {
				submit();
				this.dispose();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.dispose();
		}
		
	}
	
	private void submit() {
		
		if (entry.getParent() != null) {
			entry.getParent().addSubEntry(entry);
		}
		
		entry.setText(textField.getText());
		w.addItem(entry);
		MindMaps.onSomethingChanged();
		
	}
}
