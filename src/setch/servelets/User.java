package setch.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setch.service.cryptpwds;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
	setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
	setch.beans.contenuautorisation contenuautorisation=null;
	setch.beans.utilisateur utilisateur=null;
	setch.beans.autorisation autorisation=null;
	 List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
	 List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	 List<setch.beans.utilisateur> Listeutilisateur = new ArrayList<setch.beans.utilisateur>();
	 List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
     setch.service.familleservice familleservice=new setch.service.familleservice();
     private javax.servlet.http.HttpSession compte;
	 String option="";
	 String p="";
	 int matricule=0;
	 boolean executionoperation=false;
	 cryptpwds a=new cryptpwds();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		compte = request.getSession();
		utilisateur=(setch.beans.utilisateur)compte.getAttribute("utilisateur");
		option=request.getParameter("option");
		request.setAttribute("option",option );
		if(option.equals("addUserAdmin")) {
		for(int i=0;i<login.listefacture.size();i++)
		{
			contenuautorisation=new setch.beans.contenuautorisation(1,2,login.listefacture.get(i),"true","true","true","true","true");
			contenuautorisationservice.add(contenuautorisation);
		}
		boolean executionoperation=true;
		autorisation=autorisationservice.getByid(2);
		Listeautorisation.add(autorisation);
		request.setAttribute("Titre","Administrateur" );
		request.setAttribute("Listeautorisation",Listeautorisation );
		request.setAttribute("executionoperation",executionoperation );
		this.getServletContext().getRequestDispatcher( "/vues/utilisateur.jsp" ).forward( request, response ); 
		}
		else if(option.equals("UserList")) {
		request.setAttribute("print","en attente");
		Listeutilisateur=utilisateurservice.getAll1();
		request.setAttribute("Titre","Liste des comptes utilisateurs" );
		request.setAttribute("option",option);
		request.setAttribute("liste",Listeutilisateur);
		request.setAttribute("utilisateur",utilisateur);
		Listefamille=familleservice.getAll("actif");
		request.setAttribute("listfam",Listefamille );
		listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
		request.setAttribute("listecontenuautorisation",listecontenuautorisation );
		this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("AddUser")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listeautorisation=autorisationservice.getAll1();
			 setch.beans.utilisateur utilisateurs=new setch.beans.utilisateur("","","","",2);
				request.setAttribute("Titre","Utilisateur" );
				request.setAttribute("Listeautorisation",Listeautorisation );
				request.setAttribute("executionoperation",executionoperation );
				request.setAttribute("utilisateur",utilisateurs );
				Listefamille=familleservice.getAll("actif");
				 request.setAttribute("listfam",Listefamille );
				listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
				this.getServletContext().getRequestDispatcher( "/vues/compte.jsp" ).forward( request, response );	
		}
		else if(option.equals("UpdateUserStatut")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 matricule = Integer.parseInt(request.getParameter("id"));
			 Listeautorisation=autorisationservice.getAll1();
			 setch.beans.utilisateur utilisateurs=utilisateurservice.getByid(matricule);
			 request.setAttribute("Titre","Utilisateur" );
			 request.setAttribute("Listeautorisation",Listeautorisation );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",utilisateurs );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			 request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/compte.jsp" ).forward( request, response );	
		}

		else if(option.equals("UpdateUserPwd")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listeautorisation=autorisationservice.getAll1();
			 request.setAttribute("Titre","Utilisateur" );
			 request.setAttribute("Listeautorisation",Listeautorisation );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",utilisateur );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			 request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/compte.jsp" ).forward( request, response );	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(option.equals("addUserAdmin")) {
			
			String name =request.getParameter("Name");
			String login=request.getParameter("Login");
			String pwd=request.getParameter("Pwd");
			//------crypt
			 p="";
			try {
				p=a.pwdcrypte(pwd);;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String statut=request.getParameter("Statut");
			int autorisations= Integer.parseInt(request.getParameter("autorisations"));
			utilisateur=new setch.beans.utilisateur(name, login, p, statut, autorisations);
			executionoperation=utilisateurservice.add(utilisateur);
			if(executionoperation=true)
			{
				
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Entreprise?option=addEntreprise");
			}
			else
			{
				executionoperation=false;
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./User?option=addUser");
			}
		}
		else if(option.equals("AddUser")) {
			String name =request.getParameter("Name");
			String login=request.getParameter("Login");
			String pwd=request.getParameter("Pwd");
			String statut=request.getParameter("Statut");
			int autorisations= Integer.parseInt(request.getParameter("autorisations"));
			//------crypt
			 p="";
			try {
				p=a.pwdcrypte(pwd);;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setch.beans.utilisateur user=new setch.beans.utilisateur(name, login, p, statut, autorisations);
			executionoperation=utilisateurservice.add(user);
			if(executionoperation==true)
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./User?option=UserList");
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./User?option=AddUser");	 
			}
		}
		else if(option.equals("UpdateUserStatut")) {
			String name =request.getParameter("Name");
			String login=request.getParameter("Login");
			String pwd=request.getParameter("Pwd");
			String statut=request.getParameter("Statut");
			int autorisations= Integer.parseInt(request.getParameter("autorisations"));
			setch.beans.utilisateur user=new setch.beans.utilisateur(name, login, pwd, statut, autorisations);
			executionoperation=utilisateurservice.update(matricule, user);
			if(executionoperation==true)
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./User?option=UserList");
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./User?option=UpdateUser&id="+matricule+"");	
			}
		}
		else if(option.equals("UpdateUserPwd")) {
			 String ancienpwd=request.getParameter("lastPwd");
			 String nouveaupwd=request.getParameter("Pwd");
			 
			 String decry="";
			 //---dcrypt
			 try {
				decry=a.pwddecrypte(utilisateur.getPassword());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 if(decry.equals(ancienpwd))
			 {
				//------crypt
				 p="";
				try {
					p=a.pwdcrypte(nouveaupwd);;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 utilisateur.setPassword(p);
				executionoperation= utilisateurservice.update(utilisateur.getId(),utilisateur);
				if(executionoperation==true) {
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./connect");
				}
				else {
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./connect");
				}
			 }
			 else
			 {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./connect");
			 }
			
		}
		
	}

}
