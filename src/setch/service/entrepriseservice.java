package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.entreprise;
import setch.beans.utilisateur;
import setch.connection.connection;

public class entrepriseservice implements setch.Dao.idao<setch.beans.entreprise> {
	private Statement stmt; 
	public entrepriseservice(){
		
	}
	@Override
	public boolean add(entreprise obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql="INSERT INTO entreprise VALUES(NULL,'"+obj.getSigle()+"','"+obj.getName()+"','"+obj.getFormejuridique()+"','"+obj.getActivite()+"','"+obj.getNiu()+"','"+obj.getBp()+"','"+obj.getTelephone()+"','"+obj.getWeb()+"')";		
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
	public boolean update(int a, entreprise obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				boolean t=true;
				String sql ="UPDATE entreprise SET sigle='"+obj.getSigle()+"' ,name='"+obj.getName()+"',formejuridique='"+obj.getFormejuridique()+"',activite='"+obj.getActivite()+"',niu='"+obj.getNiu()+"',bp='"+obj.getBp()+"',telephone='"+obj.getTelephone()+"',siteweb='"+obj.getWeb()+"' WHERE id="+a+"";
				System.out.print(sql);
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
	public boolean delete(entreprise obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public entreprise getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.entreprise pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM entreprise WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.entreprise(resultat.getInt("id"),resultat.getString("sigle"),resultat.getString("name"),resultat.getString("formejuridique"),resultat.getString("activite"),resultat.getString("niu"),resultat.getString("bp"),resultat.getString("telephone"),resultat.getString("siteweb"));
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
	public List<entreprise> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		List<entreprise> t = new ArrayList<entreprise>();
		String sql="SELECT * FROM entreprise order by name asc";
		
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
				
			setch.beans.entreprise c=new setch.beans.entreprise(resultat.getInt("id"),resultat.getString("sigle"),resultat.getString("name"),resultat.getString("formejuridique"),resultat.getString("activite"),resultat.getString("niu"),resultat.getString("bp"),resultat.getString("telephone"),resultat.getString("siteweb"));
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
