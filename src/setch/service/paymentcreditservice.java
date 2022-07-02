package setch.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import setch.beans.entreprise;
import setch.beans.famille;
import setch.beans.paymentcredit;
import setch.connection.connection;

public class paymentcreditservice  implements setch.Dao.idao<setch.beans.paymentcredit> {
	private Statement stmt; 
	public paymentcreditservice(){
		
	}
	@Override
	public boolean add(paymentcredit obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO paymentcredit VALUES(NULL,"+obj.getUser()+",'"+obj.getDate()+"','"+obj.getFacture()+"',"+obj.getMontant()+")";
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
	public boolean update(int a, paymentcredit obj) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE paymentcredit SET user="+obj.getUser()+",date='"+obj.getDate()+"',facture='"+obj.getFacture()+"',montant="+obj.getMontant()+" WHERE id="+a+"";
		
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
	public boolean delete(paymentcredit obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public paymentcredit getByid(int a) {
		connection c1=new connection();
		 stmt=c1.connecter();
		setch.beans.paymentcredit pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM paymentcredit WHERE id="+a+"";

		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.paymentcredit(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("montant"));
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
	public List<paymentcredit> getAll() {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<paymentcredit> t = new ArrayList<paymentcredit>();
				String sql="SELECT * FROM paymentcredit order by facture asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.paymentcredit c=new setch.beans.paymentcredit(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("montant"));
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
	public List<paymentcredit> getAll(String facture) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<paymentcredit> t = new ArrayList<paymentcredit>();
				String sql="SELECT * FROM paymentcredit where facture='"+facture+"' order by facture asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.paymentcredit c=new setch.beans.paymentcredit(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("montant"));
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
	public Double totalpayment(String facture) {
		connection c1=new connection();
		 stmt=c1.connecter();
		// TODO Auto-generated method stub
				Double t =0.0;
				String sql="SELECT * FROM paymentcredit where facture='"+facture+"' order by facture asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.paymentcredit c=new setch.beans.paymentcredit(resultat.getInt("id"),resultat.getInt("user"),resultat.getString("date"),resultat.getString("facture"),resultat.getDouble("montant"));
					t=t+c.getMontant();
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
