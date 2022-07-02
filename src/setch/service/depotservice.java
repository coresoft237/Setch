package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.depot;
import setch.beans.famille;
import setch.connection.connection;

public class depotservice  implements setch.Dao.idao<setch.beans.depot> {
	private Statement stmt; 
	public depotservice(){
		
	}
	@Override
	public boolean add(depot obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql="INSERT INTO depot VALUES(NULL,'"+obj.getUser()+"','"+obj.getNom()+"','"+obj.getStatut()+"')";
				System.out.println(sql);
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
	public boolean update(int a, depot obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE depot SET user='"+obj.getUser()+"',nom='"+obj.getNom()+"',statut='"+obj.getStatut()+"' WHERE id="+a+"";
				
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
	public boolean delete(depot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public depot getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.depot pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM depot WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.depot(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<depot> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<depot> t = new ArrayList<depot>();
		String sql="SELECT * FROM depot order by nom asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.depot c=new setch.beans.depot(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<depot> getAll(String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<depot> t = new ArrayList<depot>();
				String sql="SELECT * FROM depot where statut='"+statut+"' order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.depot c=new setch.beans.depot(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
