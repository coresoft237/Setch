package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.validite;
import setch.connection.connection;

public class validiteservice  implements setch.Dao.idao<setch.beans.validite> {
	private Statement stmt; 
	public validiteservice(){
		
	}
	@Override
	public boolean add(validite obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO validite VALUES(NULL,'"+obj.getDate()+"')";
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
	public boolean update(int a, validite obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE vente SET date='"+obj.getDate()+"' WHERE id="+a+"";
		
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
	public boolean delete(validite obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public validite getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.validite pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM validite WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.validite(resultat.getInt("id"),resultat.getString("date"));
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
	public List<validite> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.validite> t = new ArrayList<setch.beans.validite>();
				String sql="SELECT * FROM validite order by id desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.validite c=new setch.beans.validite(resultat.getInt("id"),resultat.getString("date"));
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
