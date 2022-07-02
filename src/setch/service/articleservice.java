package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.articles;
import setch.beans.famille;
import setch.connection.connection;

public class articleservice implements setch.Dao.idao<setch.beans.articles> {
	setch.service.prixventeservice prixventeservice=new setch.service.prixventeservice();
	setch.service.uniteventeservice uniteventeservice=new setch.service.uniteventeservice();
	List<setch.beans.prixvente> Listeprixvente = new ArrayList<setch.beans.prixvente>();
	List<setch.beans.unitevente> Listeunitevente = new ArrayList<setch.beans.unitevente>();
	private Statement stmt; 
	public articleservice(){
		
		
	}
	
	@Override
	
	public boolean add(articles obj) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		stmt=c1.connecter();
				boolean t=true;
				String sql="INSERT INTO article VALUES(NULL,"+obj.getUser()+","+obj.getIdfamille()+",'"+obj.getNom()+"','"+obj.getStatut()+"')";
				try {
						int  nbre=stmt.executeUpdate(sql);
									
					} catch (Exception e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
						t=false;
					}
				finally {
					c1.close();
				}
					return t;
	}

	@Override
	public boolean update(int a, articles obj) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		stmt=c1.connecter();
				boolean t=true;
				String sql ="UPDATE article SET user="+obj.getUser()+",idfamille="+obj.getIdfamille()+",nom='"+obj.getNom()+"',statut='"+obj.getStatut()+"' WHERE id="+a+"";
				
				try {
					int  nbre=stmt.executeUpdate(sql);
					t=true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					t=false;
				}
				finally{
					c1.close();
				}
				return t;
	}

	@Override
	public boolean delete(articles obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public articles getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.articles pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM article WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}
		
		return pers;
	}
	public articles getByid(String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.articles pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM article WHERE nom='"+a+"'";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}
		
		return pers;
	}
	@Override
	public List<articles> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.articles> t = new ArrayList<setch.beans.articles>();
		String sql="SELECT * FROM article order by nom asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			
			while(resultat.next())
			{
				
			setch.beans.articles c=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<setch.beans.articles> getAll(String statut) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		stmt=c1.connecter();
				List<setch.beans.articles> t = new ArrayList<setch.beans.articles>();
				String sql="SELECT * FROM article where statut='"+statut+"' order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
						setch.beans.articles c=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<setch.beans.articles> getAll(List<setch.beans.vente>vente) {
		// TODO Auto-generated method stub
				List<setch.beans.articles> t = new ArrayList<setch.beans.articles>();
				for(int i=0;i<vente.size();i++)
				{
					setch.beans.articles article=new setch.service.articleservice().getByid(vente.get(i).getIdarticle());
					t.add(article);
				}
				return t;
	}
	public List<setch.beans.articles> getAll1(String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.articles> t = new ArrayList<setch.beans.articles>();
				String sql="SELECT * FROM article where statut='"+statut+"' order by nom asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.articles c=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
					Listeprixvente=prixventeservice.getAll(c.getId());
					Listeunitevente=uniteventeservice.getAll(c.getId());
						if(Listeprixvente.size()>0&Listeunitevente.size()>0)
						{
							t.add(c);
						}
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
	
	public List<setch.beans.articles> getAll2(String statut,int idfamille) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		stmt=c1.connecter();
				List<setch.beans.articles> t = new ArrayList<setch.beans.articles>();
				List<setch.beans.offre> t1 = new ArrayList<setch.beans.offre>();
				offreservice offreservice=new offreservice();
				String sql="SELECT * FROM article where statut='"+statut+"' and idfamille="+idfamille+" order by nom asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.articles c=new setch.beans.articles(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idfamille"),resultat.getString("nom"),resultat.getString("statut"));
						t1=offreservice.getAll3(c.getId(),"actif");
					
						if(t1.size()>0)
						{
							t.add(c);
						}
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
