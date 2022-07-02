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
 * Servlet implementation class Validite
 */
@WebServlet("/Validite")
public class Validite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String option;
       boolean executionoperation=false;
       List<setch.beans.validite> listevalidite = new ArrayList<setch.beans.validite>();
       setch.service.validiteservice validiteservice=new setch.service.validiteservice();
       setch.beans.validite validite=null;
       List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
       setch.service.familleservice familleservice=new setch.service.familleservice();
       List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
       setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
       setch.beans.utilisateur utilisateur=null;
       private javax.servlet.http.HttpSession compte;
       cryptpwds a=new cryptpwds();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		option=request.getParameter("option");
		compte = request.getSession();
		utilisateur=(setch.beans.utilisateur)compte.getAttribute("utilisateur");
		if(option.equals("ValiditeList")) {
			request.setAttribute("print","en attente");
			 listevalidite=validiteservice.getAll();
			 request.setAttribute("Titre","Liste des validites" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",listevalidite);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("ValiditeAdd")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 listevalidite=validiteservice.getAll();
			 request.setAttribute("Titre","Creation validite" );
			 request.setAttribute("executionoperation",executionoperation );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/validite.jsp" ).forward( request, response );
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(option.equals("ValiditeAdd")) {
				String date =request.getParameter("date");
				String p="";
				try {
					p=a.pwdcrypte(date);;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
				validite=new setch.beans.validite(p);
				executionoperation=validiteservice.add(validite);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Validite?option=ValiditeList");
				}
				else
				{
					 response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Validite?option=ValiditeAdd"); 
				}
		}
	}

}
