package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.entreprise;
import setch.beans.famille;
import setch.beans.service;
import setch.connection.connection;

public class serviceservice implements setch.Dao.idao<setch.beans.service> {
	private Statement stmt; 
	public serviceservice(){
		
	}
	@Override
	public boolean add(service obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO service VALUES(NULL,'"+obj.getUser()+"','"+obj.getNom()+"','"+obj.getStatut()+"')";
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
	public boolean update(int a, service obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE service SET user='"+obj.getUser()+"',nom='"+obj.getNom()+"',statut='"+obj.getStatut()+"' WHERE id="+a+"";
		
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
	public boolean delete(service obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public service getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.service pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM service WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.service(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public service getByid(String a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.service pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM service WHERE nom='"+a+"'";
		System.out.println(sql);
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.service(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<service> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<service> t = new ArrayList<service>();
				String sql="SELECT * FROM service order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.service c=new setch.beans.service(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
	public List<service> getAll(String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<service> t = new ArrayList<service>();
				String sql="SELECT * FROM service where statut='"+statut+"' order by nom asc";				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.service c=new setch.beans.service(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("nom"),resultat.getString("statut"));
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
