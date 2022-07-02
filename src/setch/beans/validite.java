package setch.beans;

public class validite {
	private int id;
	private String date;
	public validite(String date) {
		super();
		this.date = date;
	}
	public validite(int id, String date) {
		super();
		this.id = id;
		this.date = date;
	}
	public validite() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
