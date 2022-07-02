package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.prixvente;
import setch.connection.connection;

public class prixventeservice  implements setch.Dao.idao<setch.beans.prixvente> {
	private Statement stmt; 
	public prixventeservice(){
		
	}
	@Override
	public boolean add(prixvente obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql="INSERT INTO prixvente VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"',"+obj.getIdentreprise()+","+obj.getIdarticle()+","+obj.getPrixvente()+")";
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
	public boolean update(int a, prixvente obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE prixvente SET user="+obj.getUser()+",date='"+obj.getDate()+"',identreprise="+obj.getIdentreprise()+",idarticle="+obj.getIdarticle()+",prixvente="+obj.getPrixvente()+" WHERE id="+a+"";
				
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
	public boolean delete(prixvente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public prixvente getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.prixvente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM prixvente WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
	public prixvente getByid(int a,String date) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.prixvente pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM prixvente WHERE idarticle="+a+" and date='"+date+"'";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
	public List<prixvente> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.prixvente> t = new ArrayList<setch.beans.prixvente>();
		String sql="SELECT * FROM prixvente order by idarticle asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.prixvente c=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
	
	
	public List<prixvente> getAll(int idarticle) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.prixvente> t = new ArrayList<setch.beans.prixvente>();
		String sql="SELECT * FROM prixvente where idarticle="+idarticle+" order by date desc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.prixvente c=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
	public List<prixvente> getAll(String date ,int idarticle) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.prixvente> t = new ArrayList<setch.beans.prixvente>();
		String sql="SELECT * FROM prixvente where idarticle="+idarticle+" and date<= '"+date+"' order by date desc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.prixvente c=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
	public List<setch.beans.prixvente>getfacture1(String date1,String date2,int idarticle) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<setch.beans.prixvente> t = new ArrayList<setch.beans.prixvente>();
				String sql="SELECT * FROM prixvente where date between '"+date1+"' and '"+date2+"' and idarticle="+idarticle+" order by date desc"; 
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						setch.beans.prixvente c=new setch.beans.prixvente(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getInt("identreprise"),resultat.getInt("idarticle"),resultat.getInt("prixvente"));
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
