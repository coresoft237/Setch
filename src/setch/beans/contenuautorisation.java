package setch.beans;

public class contenuautorisation {
	private int id;
	private int user;
	private int idautorisation;
	private String table;
	private String visualiser;
	private String creer;
	private String modifier;
	private String supprimer;
	private String imprimer;
	public contenuautorisation() {
		super();
	}
	public contenuautorisation(int user, int idautorisation, String table, String visualiser, String creer,
			String modifier, String supprimer, String imprimer) {
		super();
		this.user = user;
		this.idautorisation = idautorisation;
		this.table = table;
		this.visualiser = visualiser;
		this.creer = creer;
		this.modifier = modifier;
		this.supprimer = supprimer;
		this.imprimer = imprimer;
	}
	public contenuautorisation(int id, int user, int idautorisation, String table, String visualiser, String creer,
			String modifier, String supprimer, String imprimer) {
		super();
		this.id = id;
		this.user = user;
		this.idautorisation = idautorisation;
		this.table = table;
		this.visualiser = visualiser;
		this.creer = creer;
		this.modifier = modifier;
		this.supprimer = supprimer;
		this.imprimer = imprimer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getIdautorisation() {
		return idautorisation;
	}
	public void setIdautorisation(int idautorisation) {
		this.idautorisation = idautorisation;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getVisualiser() {
		return visualiser;
	}
	public void setVisualiser(String visualiser) {
		this.visualiser = visualiser;
	}
	public String getCreer() {
		return creer;
	}
	public void setCreer(String creer) {
		this.creer = creer;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getSupprimer() {
		return supprimer;
	}
	public void setSupprimer(String supprimer) {
		this.supprimer = supprimer;
	}
	public String getImprimer() {
		return imprimer;
	}
	public void setImprimer(String imprimer) {
		this.imprimer = imprimer;
	}
	
}
