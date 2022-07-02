package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.entreprise;
import setch.beans.famille;
import setch.connection.connection;

public class familleservice implements setch.Dao.idao<setch.beans.famille> {
	private Statement stmt; 
	public familleservice(){
		
	}
	@Override
	public boolean add(famille obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO famille VALUES(NULL,'"+obj.getUser()+"','"+obj.getNom()+"','"+obj.getStatut()+"')";
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
	public boolean update(int a, famille obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE famille SET user='"+obj.getUser()+"',nom='"+obj.getNom()+"',statut='"+obj.getStatut()+"' WHERE id="+a+"";
		
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
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
	public boolean delete(famille obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public famille getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.famille pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM famille WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.famille(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public famille getByid(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.famille pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM famille WHERE nom='"+a+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.famille(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<famille> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<famille> t = new ArrayList<famille>();
				String sql="SELECT * FROM famille order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.famille c=new setch.beans.famille(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<famille> getAll(String statut) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		 stmt=c1.connecter();
				List<famille> t = new ArrayList<famille>();
				String sql="SELECT * FROM famille where statut='"+statut+"' order by nom asc";				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.famille c=new setch.beans.famille(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
