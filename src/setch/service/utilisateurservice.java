package setch.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import setch.beans.utilisateur;
import setch.connection.connection;
import setch.servelets.connect;


public class utilisateurservice implements setch.Dao.idao<setch.beans.utilisateur> {
	private Statement stmt; 
	cryptpwds a=new cryptpwds();
	public utilisateurservice(){
		
		
	}
	@Override
	
	public boolean add(utilisateur obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql="INSERT INTO utilisateur VALUES(NULL,'"+obj.getNom()+"','"+obj.getLogin()+"','"+obj.getPassword()+"','"+obj.getStatut()+"',"+obj.getAutorisation()+")";		
		try {
				int  nbre=stmt.executeUpdate(sql);
							
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				t=false;
				InetAddress addr = null;
				try {
					addr = InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String hote=""+addr;
				String dateerreur=""+LocalDateTime.now();
				String no=connect.use.getNom();
				erreurservice.remplir(dateerreur,hote,no,"erreur creation utlisateur");
			}
		finally {
			c1.close();
		}
			return t;
	}

	@Override
	public boolean update(int a, utilisateur obj) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE utilisateur SET nom='"+obj.getNom()+"',login='"+obj.getLogin()+"',password='"+obj.getPassword()+"',statut='"+obj.getStatut()+"',autorisation="+obj.getAutorisation()+" WHERE id="+a+"";
		
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
			//-----erreur
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String hote=""+addr;
			String dateerreur=""+LocalDateTime.now();
			String no=connect.use.getNom();
			erreurservice.remplir(dateerreur,hote,no,"erreur de modification compte utilisateur");
			//----------
		}
		finally {
			c1.close();
		}
		return t;
	}
	public boolean update1(int a,String p) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
		boolean t=true;
		String sql ="UPDATE utilisateur SET password='"+p+"' WHERE id="+a+"";
		
		try {
			int  nbre=stmt.executeUpdate(sql);
			t=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t=false;
			//-----erreur
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String hote=""+addr;
			String dateerreur=""+LocalDateTime.now();
			String no=connect.use.getNom();
			erreurservice.remplir(dateerreur,hote,no,"erreur de modification compte utilisateur");
			//----------
		}
		finally {
			c1.close();
		}
		return t;
	}

	@Override
	public boolean delete(utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utilisateur getByid(int a) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.utilisateur pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM utilisateur WHERE id="+a+"";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.utilisateur(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("login"),resultat.getString("password"),resultat.getString("statut"),resultat.getInt("autorisation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//-----erreur
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String hote=""+addr;
			String dateerreur=""+LocalDateTime.now();
			String no=connect.use.getNom();
			erreurservice.remplir(dateerreur,hote,no,"erreur de recherche compte utilisatuer id="+a+"");
			//--erreur
		}
		finally {
			c1.close();
		}
		
		return pers;
	}

	@Override
	public List<utilisateur> getAll() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<utilisateur> t = new ArrayList<utilisateur>();
				String sql="SELECT * FROM utilisateur order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.utilisateur c=new setch.beans.utilisateur(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("login"),resultat.getString("password"),resultat.getString("statut"),resultat.getInt("autorisation"));
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//------erreur
					InetAddress addr = null;
					try {
						addr = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String hote=""+addr;
					String dateerreur=""+LocalDateTime.now();
					String no=connect.use.getNom();
					erreurservice.remplir(dateerreur,hote,no,"erreur visualisation liste utilisateurs");
					//--------
				}
				finally {
					c1.close();
				}
				return t;
	}
	public List<utilisateur> getAll(String statut) {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<utilisateur> t = new ArrayList<utilisateur>();
				String sql="SELECT * FROM utilisateur where statut='"+statut+"' order by nom asc";
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.utilisateur c=new setch.beans.utilisateur(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("login"),resultat.getString("password"),resultat.getString("statut"),resultat.getInt("autorisation"));
					t.add(c);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//----erreur
					InetAddress addr = null;
					try {
						addr = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String hote=""+addr;
					String dateerreur=""+LocalDateTime.now();
					String no=connect.use.getNom();
					erreurservice.remplir(dateerreur,hote,no,"erreur de visualisation liste utilisateurs avec statut="+statut+"");
					//--------
				}
				finally {
					c1.close();
				}
				return t;
	}
	public List<utilisateur> getAll1() {
		connection c1=new connection();
		stmt=c1.connecter();
		// TODO Auto-generated method stub
				List<utilisateur> t = new ArrayList<utilisateur>();
				String sql="SELECT * FROM utilisateur where autorisation!='1' order by nom asc";
				
				try {
					ResultSet resultat=stmt.executeQuery(sql);
					while(resultat.next())
					{
						
					setch.beans.utilisateur c=new setch.beans.utilisateur(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("login"),resultat.getString("password"),resultat.getString("statut"),resultat.getInt("autorisation"));
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
	public setch.beans.utilisateur verifcompte(String login,String pwd) {
		connection c1=new connection();
		stmt=c1.connecter();
		setch.beans.utilisateur pers = null;
		// TODO Auto-generated method stub		
		String sql="SELECT * FROM utilisateur WHERE login='"+login+"' and password='"+pwd+"'";
		try {
			ResultSet resultat=stmt.executeQuery(sql);
			while(resultat.next())
			{
			pers=new setch.beans.utilisateur(resultat.getInt("id"),resultat.getString("nom"),resultat.getString("login"),resultat.getString("password"),resultat.getString("statut"),resultat.getInt("autorisation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String hote=""+addr;
			String dateerreur=""+LocalDateTime.now();
			String no=connect.use.getNom();
			erreurservice.remplir(dateerreur,hote,no,"erreur de verification mot de passe");
		}
		finally {
			c1.close();
			
		}
		
		return pers;
	}
	public setch.beans.utilisateur verifcompte1(String login,String pwd) {
		setch.beans.utilisateur pers = null;
		List<utilisateur> Actif=this.getAll("actif");
		String p="";
		for(int i=0;i<Actif.size();i++) {
	            //---decrypter
	    		try {		
	    			p=a.pwddecrypte(Actif.get(i).getPassword());
	    			System.out.println(p);
	    			
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	            //--------
	    		
			if(p.equals(pwd) && login.equals(Actif.get(i).getLogin())) {
				pers=Actif.get(i);
	            
	            
	            break;
			}
		}
		return pers;
	}
	

}
