package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.commissions;
import setch.connection.connection;

public class commissionservice implements setch.Dao.idao<setch.beans.commissions> {
	private Statement stmt; 
	public commissionservice(){
		
	}
	@Override
	public boolean add(commissions obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO commissions VALUES(NULL,"+obj.getUser()+","+obj.getFamille()+",'"+obj.getDate()+"',"+obj.getPourcentage()+")";
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
	public boolean update(int a, commissions obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE commissions SET user="+obj.getUser()+",famille="+obj.getFamille()+",date='"+obj.getDate()+"',pourcentage="+obj.getPourcentage()+" WHERE id="+a+"";
		
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
	public boolean delete(commissions obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public commissions getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.commissions pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM commissions WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.commissions(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("famille"),resultat.getString("date"),resultat.getDouble("pourcentage"));
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
	public commissions getByid(int a,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.commissions pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM commissions WHERE famille="+a+" and date='"+date+"'";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.commissions(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("famille"),resultat.getString("date"),resultat.getDouble("pourcentage"));
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
	public List<commissions> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.commissions> t = new ArrayList<setch.beans.commissions>();
				String sql="SELECT * FROM commissions order by famille asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.commissions c=new setch.beans.commissions(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("famille"),resultat.getString("date"),resultat.getDouble("pourcentage"));
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
	public List<setch.beans.commissions>getfacture1(String date1,String date2,int idfamille) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.commissions> t = new ArrayList<setch.beans.commissions>();
				String sql="SELECT * FROM commissions where date between '"+date1+"' and '"+date2+"' and famille="+idfamille+" order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.commissions c=new setch.beans.commissions(resultat.getInt("id"),resultat.getInt("user"),resultat.getInt("famille"),resultat.getString("date"),resultat.getDouble("pourcentage"));
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
