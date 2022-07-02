package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.contenuachat;
import setch.beans.contenuachat2;
import setch.connection.connection;


public class contenuachatservice implements setch.Dao.idao<setch.beans.contenuachat> {
	private Statement stmt; 
	setch.service.articleservice articleservice=new setch.service.articleservice();
	setch.beans.articles article=null;
	@Override
	public boolean add(contenuachat obj) {
		// TODO Auto-generated method stub
				connection c1=new connection();
				stmt=c1.connecter();
						boolean t=true;
						String sql="INSERT INTO contenuachat VALUES(NULL,"+obj.getUser()+","+obj.getArticle()+","+obj.getQuantite()+")";
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
	public boolean update(int a, contenuachat obj) {
		// TODO Auto-generated method stub
				connection c1=new connection();
				stmt=c1.connecter();
						boolean t=true;
						String sql ="UPDATE contenuachat SET user="+obj.getUser()+",idarticle="+obj.getArticle()+",quantite="+obj.getQuantite()+" WHERE id="+a+"";
						
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
	public boolean delete(contenuachat obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public contenuachat getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.contenuachat pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM contenuachat WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.contenuachat(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idarticle"),resultat.getInt("quantite"));
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
	public contenuachat getByid1(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.contenuachat pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM contenuachat WHERE idarticle="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.contenuachat(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idarticle"),resultat.getInt("quantite"));
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
	public List<contenuachat> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.contenuachat> t = new ArrayList<setch.beans.contenuachat>();
		String sql="SELECT * FROM contenuachat order by idarticle asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			
			while(resultat.next())
			{
				
			setch.beans.contenuachat c=new setch.beans.contenuachat(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("idarticle"),resultat.getInt("quantite"));
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
