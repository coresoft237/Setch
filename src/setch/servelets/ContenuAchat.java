package setch.servelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setch.beans.articles;

/**
 * Servlet implementation class ContenuAchat
 */
@WebServlet("/ContenuAchat")
public class ContenuAchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
	List<setch.beans.contenuachat> Listecontenuachat = new ArrayList<setch.beans.contenuachat>();
	List<setch.beans.contenuachat2> Listecontenuachat2 = new ArrayList<setch.beans.contenuachat2>();
    setch.service.contenuachatservice contenuachatservice=new setch.service.contenuachatservice();
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    List<articles> Listearticle = new ArrayList<articles>();
    setch.service.articleservice articleservice=new setch.service.articleservice();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.beans.contenuachat contenuachat=null;
    boolean executionoperation=false;
    int matricule=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContenuAchat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		option=request.getParameter("option");
		if(option.equals("ListContenuAchat")) {
			request.setAttribute("print","en attente");
			 Listecontenuachat=contenuachatservice.getAll();
			 request.setAttribute("Titre","Liste des contenus achats" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listecontenuachat);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			 request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
		}
		else if(option.equals("AddContenuAchat")){
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 contenuachat=new setch.beans.contenuachat();
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("Titre","Creation Contenu Achat" );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("contenu",contenuachat );
			 Listearticle=articleservice.getAll();
			 request.setAttribute("listearticle",Listearticle );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/contenuachat.jsp" ).forward( request, response );
		}
		else if(option.equals("UpdateContenuAchat")){
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 contenuachat=contenuachatservice.getByid(matricule);
			 Listefamille=familleservice.getAll("actif");
			 executionoperation=true;
			 Listearticle=articleservice.getAll();
			 request.setAttribute("listearticle",Listearticle );
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("contenu",contenuachat );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Modifier contenu achat" );
			 request.setAttribute("listefamille",Listefamille );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/contenuachat.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(option.equals("AddContenuAchat")){
			int article =Integer.parseInt(request.getParameter("article"));
			int  contenu=Integer.parseInt(request.getParameter("contenu"));
			contenuachat=new setch.beans.contenuachat(utilisateur.getId(),article,contenu);
			if(contenuachatservice.getByid(article)==null)
			{
				executionoperation=contenuachatservice.add(contenuachat);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./ContenuAchat?option=ListContenuAchat");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./ContenuAchat?option=ListContenuAchat");   
				}
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./ContenuAchat?option=AddContenuAchat"); 
			}
			
		}
		else if(option.equals("UpdateContenuAchat")) {
			int article =Integer.parseInt(request.getParameter("article"));
			int  contenu=Integer.parseInt(request.getParameter("contenu"));
			contenuachat=new setch.beans.contenuachat(utilisateur.getId(),article,contenu);
				executionoperation=contenuachatservice.update(matricule, contenuachat);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./ContenuAchat?option=ListContenuAchat"); 
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./ContenuAchat?option=UpdateContenuAchat&&id="+matricule+"");  
				}
		}
	}

}
