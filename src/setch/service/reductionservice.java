package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.reductionfacture;
import setch.beans.utilisateur;
import setch.connection.connection;

public class reductionservice  implements setch.Dao.idao<setch.beans.reductionfacture> {
	private Statement stmt; 
	public reductionservice(){
		
	}
	@Override
	public boolean add(reductionfacture obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql="INSERT INTO reduction VALUES(NULL,'"+obj.getuser()+"','"+obj.getDate()+"','"+obj.getFacture()+"',"+obj.getReduction()+")";		
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
	public boolean update(int a, reductionfacture obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE reduction SET date='"+obj.getDate()+"',user="+obj.getuser()+",facture='"+obj.getFacture()+"',reduction='"+obj.getReduction()+"' WHERE id="+a+"";
		
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
	public boolean update(String a,String b,int user) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE reduction SET facture='"+b+"' WHERE facture='"+a+"' and user="+user+"";
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
	public boolean delete(reductionfacture obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean delete(int user,String statut) {
		connection c1=new connection();
		 stmt=c1.connecter();
		boolean t=true;
		String sql="DELETE FROM reduction WHERE user="+user+" and facture='"+statut+"'";
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
	public reductionfacture getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.reductionfacture pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM reduction WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.reductionfacture(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("reduction"));
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
	
	public reductionfacture getByfacture(String a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.reductionfacture pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM reduction WHERE facture='"+a+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.reductionfacture(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("reduction"));
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
	public List<reductionfacture> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<setch.beans.reductionfacture> t = new ArrayList<setch.beans.reductionfacture>();
		String sql="SELECT * FROM reduction order by date desc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.reductionfacture c=new setch.beans.reductionfacture(resultat.getInt("id"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("reduction"));
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
