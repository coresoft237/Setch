package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.facturation;
import setch.beans.validite;
import setch.connection.connection;

public class facturationservice  implements setch.Dao.idao<setch.beans.facturation> {
	private Statement stmt; 
	public facturationservice(){
		
	}
	@Override
	public boolean add(facturation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO facturation VALUES(NULL,'"+obj.getDate()+"','"+obj.getType()+"')";
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
	public boolean update(int a, facturation obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE facturation SET date='"+obj.getDate()+"',type='"+obj.getType()+"' WHERE id="+a+"";
		
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
	public boolean delete(facturation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public facturation getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.facturation pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM facturation WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.facturation(resultat.getInt("id"),resultat.getString("date"),resultat.getString("type"));
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
	public List<facturation> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.facturation> t = new ArrayList<setch.beans.facturation>();
				String sql="SELECT * FROM facturation order by date desc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.facturation c=new setch.beans.facturation(resultat.getInt("id"),resultat.getString("date"),resultat.getString("type"));
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
