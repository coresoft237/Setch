package setch.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Autorisations
 */
@WebServlet("/Autorisations")
public class Autorisations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpSession compte;
	List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
	setch.beans.utilisateur utilisateur=null;
	List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	setch.service.familleservice familleservice=new setch.service.familleservice();
	setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
	setch.beans.autorisation autorisation=null;
	String option="";
	int matricule=0;
	boolean executionoperation=false;
	static List<String> listefacture = new ArrayList<String>();
	setch.service.databaseservice databaseservice=new setch.service.databaseservice();
	setch.beans.contenuautorisation contenuautorisation=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autorisations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		compte = request.getSession();
		utilisateur=(setch.beans.utilisateur)compte.getAttribute("utilisateur");
		option=request.getParameter("option");
		if(option.equals("ListAutorisations")) {
			request.setAttribute("print","en attente");
			 Listeautorisation=autorisationservice.getAll();
			 request.setAttribute("Titre","Liste des autorisations" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listeautorisation);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		if(option.equals("AddAutorisations")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 autorisation=new setch.beans.autorisation();
			 autorisation.setNom("");
			 request.setAttribute("Titre","Creation autorisation" );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("autorisation",autorisation );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/autorisation.jsp" ).forward( request, response ); 
		}
		else if(option.equals("UpdateAutorisations")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 matricule = Integer.parseInt(request.getParameter("id"));
			 autorisation=autorisationservice.getByid(matricule);
			 request.setAttribute("Titre","Creation autorisation" );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("autorisation",autorisation );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/autorisation.jsp" ).forward( request, response ); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(option.equals("AddAutorisations")) {
			String name =request.getParameter("name");
			autorisation=new setch.beans.autorisation(name, utilisateur.getId());
			executionoperation=autorisationservice.add(autorisation);
			if(executionoperation==true)
			{
				Listeautorisation=autorisationservice.getAll();
				listefacture=databaseservice.listetables();
				for(int i=0;i<listefacture.size();i++)
				{
					contenuautorisation=new setch.beans.contenuautorisation(utilisateur.getId(),Listeautorisation.get(0).getId(),listefacture.get(i),"false","false","false","false","false");
					contenuautorisationservice.add(contenuautorisation);
				}
				
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Autorisations?option=ListAutorisations");
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Autorisations?option=AddAutorisations"); 
			}
		}
		else if(option.equals("UpdateAutorisations")) {
			String name =request.getParameter("name");
			autorisation=new setch.beans.autorisation(name, utilisateur.getId());
			executionoperation=autorisationservice.update(matricule, autorisation);
			if(executionoperation==true)
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Autorisations?option=ListAutorisations"); 
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Autorisations?option=UpdateAutorisations&&id="+matricule+""); 
			}
		}
	}

}
