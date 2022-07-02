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
 * Servlet implementation class ContenuAutorisation
 */
@WebServlet("/ContenuAutorisation")
public class ContenuAutorisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpSession compte;
	String option="";
	int matricule=0;
	boolean executionoperation=false;
	setch.beans.utilisateur utilisateur=null;
	List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	   List<setch.beans.contenuautorisation> listecontenuautorisation1 = new ArrayList<setch.beans.contenuautorisation>();
	List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	setch.service.familleservice familleservice=new setch.service.familleservice();
	setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
	setch.beans.autorisation autorisation=null;
	setch.beans.contenuautorisation contenuautorisation=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContenuAutorisation() {
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
		if(option.equals("DetailAutorisationsView")) {
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 autorisation=autorisationservice.getByid(matricule);
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(matricule);
			 listecontenuautorisation1=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			 request.setAttribute("Titre","Liste des autorisations de '"+autorisation.getNom()+"'" );
			 request.setAttribute("option",option);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("liste",listecontenuautorisation);
			request.setAttribute("listecontenuautorisation",listecontenuautorisation1 );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
		}
		if(option.equals("DetailAutorisationsActiveAll")) {
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 contenuautorisation=new setch.beans.contenuautorisation(utilisateur.getId(),utilisateur.getAutorisation(),"","true","true","true","true","true");
			 executionoperation=contenuautorisationservice.updateActiveAll(matricule, contenuautorisation);
			 response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location","./Autorisations?option=ListAutorisations");
		}
		if(option.equals("DetailAutorisationsDesactiveAll")) {
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 contenuautorisation=new setch.beans.contenuautorisation(utilisateur.getId(),utilisateur.getAutorisation(),"","false","false","false","false","false");
			 executionoperation=contenuautorisationservice.updateActiveAll(matricule, contenuautorisation);
			 response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location","./Autorisations?option=ListAutorisations");
		}
		else if(option.equals("UpdateContenuAutorisation")) {
			request.setAttribute("print","en attente");
			 String etat=request.getParameter("id2");
			String colone=request.getParameter("nom");
			int numero=Integer.parseInt(request.getParameter("id"));
			int idauto=Integer.parseInt(request.getParameter("idauto"));
			if(etat.equals("true"))
			{
				executionoperation=contenuautorisationservice.update(numero, colone, "true");
			}
			else
			{
				executionoperation=contenuautorisationservice.update(numero, colone, "false");
			}
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location","./ContenuAutorisation?option=DetailAutorisationsView&id="+matricule+"");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
