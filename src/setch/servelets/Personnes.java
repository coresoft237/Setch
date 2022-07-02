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
 * Servlet implementation class Personnes
 */
@WebServlet("/Personnes")
public class Personnes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	 setch.beans.utilisateur utilisateur=null;
     private javax.servlet.http.HttpSession compte;
     List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
     setch.service.personneservice personneservice=new setch.service.personneservice();
     List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
     setch.service.familleservice familleservice=new setch.service.familleservice();
     List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
     setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
     setch.beans.personne personne=null;
     String val="";
     int matricule=0;
     boolean executionoperation=false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personnes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		compte = request.getSession();
		utilisateur=(setch.beans.utilisateur)compte.getAttribute("utilisateur");
		if(option.equals("ListPersonnes")) {
			request.setAttribute("print","en attente");
			 Listepersonne=personneservice.getAll();
			 request.setAttribute("Titre","Liste des personnes" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listepersonne);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("AddPersonnes")) {
			personne=new setch.beans.personne();
			request.setAttribute("print","en attente");
			 executionoperation=true;
			if(OffrePrixAchat.action=="creationoffreachat"||OffrePrixAchat.action=="creationlivraison") {
				personne=OffrePrixAchat.person;
			}
			else if(OffrePrixAchat.action==null) {
				personne.setNom("");
				personne.setPhone("");
				personne.setTtitre("");
				personne.setStatut("");
			}
			
			 request.setAttribute("personne",personne );
			 request.setAttribute("executionoperation",executionoperation );
			if(OffrePrixAchat.action==null) {
				val="";
			}
			else {
				val=OffrePrixAchat.action;
			}
			 request.setAttribute("action",val);
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Creation personne" );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/personne.jsp" ).forward( request, response );
		}
		else if(option.equals("UpdatePersonnes")) {
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 personne=personneservice.getByid(matricule);
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("option",option );
			 request.setAttribute("action",val);
			 request.setAttribute("personne",personne );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Modifier personne" );
			 request.setAttribute("matricule",matricule );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/personne.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(option.equals("AddPersonnes")){
			String name =request.getParameter("name");
			String telephone =request.getParameter("telephone");
			String titre ="";
			String statut="";
			if(OffrePrixAchat.action=="creationoffreachat" ||OffrePrixAchat.action=="creationlivraison") {
				 titre ="fournisseur";
				 statut="actif";
			}
			else {
				 titre =request.getParameter("titre");
				 statut=request.getParameter("statut");
			}
			personne=new setch.beans.personne(name,telephone,statut,titre);
			if(personneservice.getByid(name, titre)==null)
			{
				executionoperation=personneservice.add(personne);
				if(executionoperation==true)
				{
					if(OffrePrixAchat.action=="creationoffreachat") {
						OffrePrixAchat.fournisseur=personneservice.getByid(name,"fournisseur");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./OffrePrixAchat?option=AddOffre");
					}
					else if(OffrePrixAchat.action=="creationlivraison") {
						OffrePrixAchat.fournisseur=personneservice.getByid(name,"fournisseur");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						 response.setHeader("Location","./Livraison?option=AddLivraison2");
					}
					else {
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./Personnes?option=ListPersonnes");
					}
					
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Personnes?option=AddPersonnes"); 
				}
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Personnes?option=AddPersonnes");
			}
		}
		else if(option.equals("UpdatePersonnes")) {
			String name =request.getParameter("name");
			String telephone =request.getParameter("telephone");
			String titre=request.getParameter("titre");
			String statut=request.getParameter("statut");
			personne=new setch.beans.personne(name,telephone,statut,titre);
				executionoperation=personneservice.update(matricule, personne);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Personnes?option=ListPersonnes");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Personnes?option=UpdatePersonnes&&id="+matricule+""); 
				}
		}
	}

}
