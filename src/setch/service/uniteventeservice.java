package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.prixvente;
import setch.beans.unitevente;
import setch.connection.connection;

public class uniteventeservice  implements setch.Dao.idao<setch.beans.unitevente> {
	private Statement stmt; 
	public uniteventeservice(){
		
	}
	@Override
	public boolean add(unitevente obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO unitevente VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"',"+obj.getIdentreprise()+","+obj.getIdarticle()+","+obj.getUnitevente()+")";
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
	public boolean update(int a, unitevente obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE unitevente SET user="+obj.getUser()+",date='"+obj.getDate()+"',identreprise="+obj.getIdentreprise()+",idarticle="+obj.getIdarticle()+",unitevente="+obj.getUnitevente()+" WHERE id="+a+"";
		
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
	public boolean delete(unitevente obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public unitevente getByid(int a,String date) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.unitevente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM unitevente WHERE idarticle="+a+" and date='"+date+"'";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
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
	public unitevente getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.unitevente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM unitevente WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
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
	public List<unitevente> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.unitevente> t = new ArrayList<setch.beans.unitevente>();
				String sql="SELECT * FROM unitevente order by idarticle asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.unitevente c=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
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
	public List<unitevente> getAll(int idarticle) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.unitevente> t = new ArrayList<setch.beans.unitevente>();
		String sql="SELECT * FROM unitevente where idarticle="+idarticle+" order by date desc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.unitevente c=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
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
	public Double unitevente(String date,int idarticle) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.unitevente> t = new ArrayList<setch.beans.unitevente>();
		Double t1=0.0;
		String sql="SELECT * FROM unitevente where idarticle="+idarticle+" and date <= '"+date+"' order by date desc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.unitevente c=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
			t.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			c1.close();
		}
		if(t.size()>0) {
			t1=t.get(0).getUnitevente();
		}
		return t1;
	}
	public List<setch.beans.unitevente>getfacture1(String date1,String date2,int idarticle) {
		// TODO Auto-generated method stub
		connection c1=new connection();
		stmt=c1.connecter();
				List<setch.beans.unitevente> t = new ArrayList<setch.beans.unitevente>();
				String sql="SELECT * FROM unitevente where date between '"+date1+"' and '"+date2+"' and idarticle="+idarticle+" order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.unitevente c=new setch.beans.unitevente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("unitevente"));
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
