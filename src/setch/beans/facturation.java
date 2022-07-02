package setch.beans;

public class facturation {
	private int id;
	private String date;
	private String type;
	public facturation(int id, String date, String type) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
	}
	public facturation(String date, String type) {
		super();
		this.date = date;
		this.type = type;
	}
	public facturation() {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
