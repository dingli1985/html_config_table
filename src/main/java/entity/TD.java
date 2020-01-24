package entity;

public class TD {
	int width;
	int height;
	int editable;
	int colspan;
	int rowspan;
	String value;

	public TD(int width, int height, int editable, int colspan, int rowspan, String value) {
		this.width = width;
		this.height = height;
		this.editable = editable;
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.value = value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	
	

}
