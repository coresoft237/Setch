package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.autorisation;
import setch.beans.entreprise;
import setch.connection.connection;

public class autorisationservice  implements setch.Dao.idao<setch.beans.autorisation> {

	private Statement stmt; 
	public autorisationservice(){
		
	}
	
	@Override
	public boolean add(autorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO autorisation VALUES(NULL,"+obj.getuser()+",'"+obj.getNom()+"')";		
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
	public boolean update(int a, autorisation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE autorisation SET user="+obj.getuser()+",nom='"+obj.getNom()+"' WHERE id="+a+"";
		
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
	public boolean delete(autorisation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public autorisation getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.autorisation pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM autorisation WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.autorisation(resultat.getInt("id"),resultat.getString("nom"),resultat.getInt("user"));
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
	public List<autorisation> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<autorisation> t = new ArrayList<autorisation>();
				String sql="SELECT * FROM autorisation order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.autorisation c=new setch.beans.autorisation(resultat.getInt("id"),resultat.getString("nom"),resultat.getInt("user"));
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
	public List<autorisation> getAll1() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<autorisation> t = new ArrayList<autorisation>();
				String sql="SELECT * FROM autorisation where id> 1 order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.autorisation c=new setch.beans.autorisation(resultat.getInt("id"),resultat.getString("nom"),resultat.getInt("user"));
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
