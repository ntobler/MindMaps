package com.ntobler.mindMaps;

public class EntryPlacer {

	private Workspace workspace;
	
	private Entry parent;
	private Entry editing;
	
	private String text;
	
	private enum PickingMode {
		IDLE,
		MOVING,
		ADDING
	};
	
	public interface Listener {
		public void onSubmited();
	}
	
	private Listener listener;
	
	private PickingMode pickingMode;
	
	public EntryPlacer(Workspace workspace) {
		
		this.workspace = workspace;
		pickingMode = PickingMode.ADDING;
	}

	
	
	
	public void pick(Complex pos) {
		switch (pickingMode) {
		case IDLE:
			break;
		case MOVING:
			break;
		case ADDING:
			add(pos);
			break;
		default:
			break;
		}
	}
	
	
	
	private void add(Complex pos) {
		Entry e = new Entry();
		//e.setPos(pos);
		e.setText(text);
		
		if (parent != null) {
			parent.addSubEntry(e);
		}
		listener.onSubmited();
		workspace.addItem(e);
	}
	
	
	
	
	public Entry getParent() {
		return parent;
	}


	public void setParent(Entry parent) {
		this.parent = parent;
	}


	public Entry getEditing() {
		return editing;
	}


	public void setEditing(Entry editing) {
		this.editing = editing;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
