package setch.service;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.depot;
import setch.beans.famille;
import setch.beans.recapbesoin;
import setch.beans.recapinventaire;
import setch.connection.connection;

public class recapbesoinservice  implements setch.Dao.idao<setch.beans.recapbesoin> {
	articleservice articleservice=new articleservice();
	familleservice familleservice=new familleservice();
	besoinservice besoinservice=new besoinservice();
	offreservice offreservice=new offreservice();
	private Statement stmt; 
	public recapbesoinservice(){
		
	}
	@Override
	public boolean add(recapbesoin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int a, recapbesoin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(recapbesoin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public recapbesoin getByid(int a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<recapbesoin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<recapbesoin> getAll2() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recapbesoin> t = new ArrayList<setch.beans.recapbesoin>();
				List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
				List<setch.beans.offre> t2 = new ArrayList<setch.beans.offre>();
				String sql="SELECT DISTINCT numero,date FROM besoin order by date desc";
				
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
						String a=(resultat1.getString("numero"));
						String b=(resultat1.getString("date"));
						t3=besoinservice.getAll(resultat1.getString("numero"));
						articles article=articleservice.getByid(t3.get(0).getIdarticle());
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
						
						for(int i=0;i<t3.size();i++)
						{
							t2=offreservice.getAll3(t3.get(i).getIdarticle(),"actif");
							total=total+(t3.get(i).getQuantite()*t2.get(0).getPrixvente());
						}
					recapbesoin c=new recapbesoin(famille.getNom(),a,b,total,t3.get(0).getStatut());
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				return t;
	}
	public List<recapbesoin> getAll2(String numero) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		
				List<setch.beans.recapbesoin> t = new ArrayList<setch.beans.recapbesoin>();
				List<setch.beans.besoin> t3 = new ArrayList<setch.beans.besoin>();
				List<setch.beans.offre> t2 = new ArrayList<setch.beans.offre>();
				String sql="SELECT DISTINCT numero,date FROM besoin where numero='"+numero+"' order by date desc";
				
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
						String a=(resultat1.getString("numero"));
						String b=(resultat1.getString("date"));
						t3=besoinservice.getAll(resultat1.getString("numero"));
						articles article=articleservice.getByid(t3.get(0).getIdarticle());
						famille famille=familleservice.getByid(article.getIdfamille());
						Double total=0.0;
						
						for(int i=0;i<t3.size();i++)
						{
							t2=offreservice.getAll3(t3.get(i).getIdarticle(),"actif");
							total=total+(t3.get(i).getQuantite()*t2.get(0).getPrixvente());
						}
					recapbesoin c=new recapbesoin(famille.getNom(),a,b,total,t3.get(0).getStatut());
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				return t;
	}
	public List<recapinventaire> getAll3() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.recapinventaire> t = new ArrayList<setch.beans.recapinventaire>();
				String sql="SELECT DISTINCT numero,date FROM inventaire  order by date desc";
				
				try {
					ResultSet resultat1=stmt.executeQuery(sql);
					while(resultat1.next())
					{
					recapinventaire c=new recapinventaire(resultat1.getString("numero"),resultat1.getString("date"));
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					c1.close();
				}
				return t;
	}
	
	
}
