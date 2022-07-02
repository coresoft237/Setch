package setch.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setch.service.autorisationservice;

/**
 * Servlet implementation class Familles
 */
@WebServlet("/Familles")
public class Familles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	setch.beans.utilisateur utilisateur=null;
    private javax.servlet.http.HttpSession compte;
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
    setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
    setch.beans.famille famille=null;
    setch.beans.contenuautorisation contenuautorisation=null;
    boolean executionoperation=false;
    int matricule=0;
    String anciennom;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Familles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		compte = request.getSession();
		utilisateur=connect.use;
		if(option.equals("ListFamilles")) {
			request.setAttribute("print","en attente");
			 Listefamille=familleservice.getAll();
			 request.setAttribute("Titre","Liste des familles" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listefamille);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		else if(option.equals("AddFamilles")) {
			request.setAttribute("print","en attente");
			 executionoperation=true;
			 famille=new setch.beans.famille();
			 famille.setNom("");
			 famille.setStatut("");
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("option",option );
			 request.setAttribute("Titre","Creation Famille" );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("famille",famille );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/famille.jsp" ).forward( request, response );
		}
		else if(option.equals("UpdateFamilles")) {
			 request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 famille=familleservice.getByid(matricule);
			 anciennom=famille.getNom();
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("option",option );
			 request.setAttribute("famille",famille );
			 request.setAttribute("Titre","Modifier Famille" );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("matricule",matricule );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/famille.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(option.equals("AddFamilles")) {
			String name =request.getParameter("name");
			String statut=request.getParameter("statut");
			famille=new setch.beans.famille(utilisateur.getId(),name,statut);
			if(familleservice.getByid(name)==null)
			{
				executionoperation=familleservice.add(famille);
				if(executionoperation==true)
				{
					//creer contenuautorisation
					Listeautorisation=autorisationservice.getAll();
					for(int i=0;i<Listeautorisation.size();i++)
					{
						if(Listeautorisation.get(i).getId()<3)
						{
						contenuautorisation=new setch.beans.contenuautorisation(1,Listeautorisation.get(i).getId(),"etatvente"+name+"","true","true","true","true","true");
						}
						else
						{
							contenuautorisation=new setch.beans.contenuautorisation(1,Listeautorisation.get(i).getId(),"etatvente"+name+"","false","false","false","false","false");	
						}
						
						contenuautorisationservice.add(contenuautorisation);
						
					}
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Familles?option=ListFamilles");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Familles?option=AddFamilles");
				}
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Familles?option=AddFamilles");
			}
			
		}
		else if(option.equals("UpdateFamilles")) {
			String name =request.getParameter("name");
			String statut=request.getParameter("statut");
			famille=new setch.beans.famille(utilisateur.getId(),name,statut);
			contenuautorisation=new setch.beans.contenuautorisation();
			contenuautorisation.setUser(utilisateur.getId());
				executionoperation=familleservice.update(matricule, famille);
				if(executionoperation==true)
				{
					contenuautorisationservice.update1(anciennom,"etatvente"+name+"", contenuautorisation);
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Familles?option=ListFamilles");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Familles?option=UpdateFamilles&&id="+matricule+"");
				}
			
		}
	}

}
