package entity;

import java.util.List;

public class TR {

	String id;
	
	String type;
	
	List<TD> list;
	
	public TR(String id,String type,List<TD> list ) {
		this.id=id;
		this.type=type;
		this.list=list;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TD> getList() {
		return list;
	}

	public void setList(List<TD> list) {
		this.list = list;
	}
	
	

}
