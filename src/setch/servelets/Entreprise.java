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
 * Servlet implementation class Entreprise
 */
@WebServlet("/Entreprise")
public class Entreprise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option="";
	int matricule=0;
	boolean executionoperation=false;
	setch.beans.entreprise entreprise=null;
	setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
	List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
	List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	setch.service.familleservice familleservice=new setch.service.familleservice();
	setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	private javax.servlet.http.HttpSession compte;
	setch.beans.utilisateur utilisateur=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entreprise() {
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
		if(option.equals("addEntreprise")) {
			 executionoperation=true;
			entreprise=new setch.beans.entreprise("","","","","", "","", "");
			request.setAttribute("entreprise",entreprise );
			request.setAttribute("executionoperation",executionoperation );
			this.getServletContext().getRequestDispatcher( "/vues/Entreprise.jsp" ).forward( request, response );
		}
		else if(option.equals("entreprise")) {
			 request.setAttribute("print","en attente");
			 listeentreprise=entrepriseservice.getAll();
			 request.setAttribute("Titre","Liste des entreprises" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",listeentreprise);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("UpdateEntreprise")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 matricule = Integer.parseInt(request.getParameter("id"));
			 entreprise=entrepriseservice.getByid(matricule);
			 request.setAttribute("entreprise",entreprise );
			 request.setAttribute("executionoperation",executionoperation );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/Entreprise.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(option.equals("addEntreprise")) {
			String name =request.getParameter("name");
			String sigle =request.getParameter("sigle");
			String formejuridique =request.getParameter("formejuridique");
			String activite =request.getParameter("activite");
			String niu =request.getParameter("niu");
			String bp =request.getParameter("bp");
			String telephone =request.getParameter("telephone");
			String siteweb =request.getParameter("siteweb");
			entreprise=new setch.beans.entreprise(sigle,name, formejuridique, activite, niu, bp, telephone, siteweb);
			executionoperation=entrepriseservice.add(entreprise);
			if(executionoperation==true)
			{
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./connect");
			}
			else {
				 response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Entreprise?option=addEntreprise");
			}
		}
		else if(option.equals("UpdateEntreprise")) {
			String sigle =request.getParameter("sigle");
			String name =request.getParameter("name");
			String formejuridique =request.getParameter("formejuridique");
			String activite =request.getParameter("activite");
			String niu =request.getParameter("niu");
			String bp =request.getParameter("bp");
			String telephone =request.getParameter("telephone");
			String siteweb =request.getParameter("siteweb");
			entreprise=new setch.beans.entreprise(sigle,name, formejuridique, activite, niu, bp, telephone, siteweb);
			executionoperation=entrepriseservice.update(matricule,entreprise);
			if(executionoperation==true)
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Entreprise?option=entreprise");
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Entreprise?option=UpdateEntreprise&id="+matricule+"");
			}
			
		}
	}

}
