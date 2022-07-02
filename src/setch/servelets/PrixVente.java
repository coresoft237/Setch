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
 * Servlet implementation class PrixVente
 */
@WebServlet("/PrixVente")
public class PrixVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpSession compte; 
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
	setch.service.prixventeservice prixventeservice=new setch.service.prixventeservice();
	List<setch.beans.prixvente> Listeprixvente = new ArrayList<setch.beans.prixvente>();
	setch.beans.prixvente prixvente=null;
	setch.beans.articles article=null;
	 List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
	 setch.service.familleservice familleservice=new setch.service.familleservice();
	    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
	    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
	    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
	    setch.service.articleservice articleservice=new setch.service.articleservice();
	    int matricule=0;
	     boolean executionoperation=false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrixVente() {
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
		 if(option.equals("ListPrixVente"))
		 {
			 request.setAttribute("print","en attente");
			 Listeprixvente=prixventeservice.getAll();
			 request.setAttribute("Titre","Liste des prix de vente" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listeprixvente);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 request.setAttribute("utilisateur",utilisateur);
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
			 
		 }
		 else if(option.equals("AddPrixVente"))
		 {
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 Listearticle=articleservice.getAll("actif");
			 prixvente=new setch.beans.prixvente();
			prixvente.setDate("");
			prixvente.setIdarticle(0);
			prixvente.setPrixvente(0);
			 request.setAttribute("prixvente",prixvente );
			 request.setAttribute("Listearticle",Listearticle );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Creation prix vente" );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/prixvente.jsp" ).forward( request, response ); 
			 
		 }
		 else if(option.equals("UpdatePrixVente"))
		 {
			 request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 prixvente=prixventeservice.getByid(matricule);
			 article=articleservice.getByid(prixvente.getIdarticle());
			 Listearticle.add(article);
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );				 
			 request.setAttribute("prixvente",prixvente );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Listearticle",Listearticle );
			 request.setAttribute("Titre","Modifier prix de vente" );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/prixvente.jsp" ).forward( request, response ); 
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(option.equals("AddPrixVente"))
		 {
				String date =request.getParameter("date");
				int article = Integer.parseInt(request.getParameter("article"));
				double pv = Double.parseDouble(request.getParameter("pv"));
				prixvente=new setch.beans.prixvente(utilisateur.getId(), date,1,article,pv);
				if(prixventeservice.getByid(article, date)==null)
				{
					executionoperation=prixventeservice.add(prixvente);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./PrixVente?option=ListPrixVente");
					}
					else
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./PrixVente?option=AddPrixVente");
					}	
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./PrixVente?option=AddPrixVente"); 
				} 
			 
		 }
		 else if(option.equals("UpdatePrixVente"))
		 {
			 String date =request.getParameter("date");
				int articles = Integer.parseInt(request.getParameter("article"));
				double pv = Double.parseDouble(request.getParameter("pv"));
				prixvente=new setch.beans.prixvente(utilisateur.getId(), date,1,articles,pv);
				/*if(prixventeservice.getByid(articles, date).getId()==matricule)
				{*/
					executionoperation=prixventeservice.update(matricule, prixvente);
					if(executionoperation==true)
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./PrixVente?option=ListPrixVente"); 
					}
					else
					{
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location","./PrixVente?option=UpdatePrixVente&&id="+matricule+""); 
					}
				
		 }
	}

}
