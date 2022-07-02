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
 * Servlet implementation class Articles
 */
@WebServlet("/Articles")
public class Articles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String option;
	setch.beans.utilisateur utilisateur=connect.use;
    private javax.servlet.http.HttpSession compte; 
    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
    setch.service.articleservice articleservice=new setch.service.articleservice();
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.beans.articles article=null;
    boolean executionoperation=false;
    int matricule=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Articles() {
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
		if(option.equals("ListArticles")) {
			request.setAttribute("print","en attente");
			 Listearticle=articleservice.getAll();
			 request.setAttribute("Titre","Liste des articles" );
			 request.setAttribute("option",option);
			 request.setAttribute("liste",Listearticle);
			 request.setAttribute("utilisateur",utilisateur);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			 request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
		}
		else if(option.equals("AddArticles")){
			 request.setAttribute("print","en attente");
			 executionoperation=true;
			 article=new setch.beans.articles();
			 article.setNom("");
			 article.setStatut("");
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("Titre","Creation Famille" );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("article",article );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/article.jsp" ).forward( request, response );
		}
		else if(option.equals("UpdateArticles")){
			request.setAttribute("print","en attente");
			 matricule = Integer.parseInt(request.getParameter("id"));
			 article=articleservice.getByid(matricule);
			 Listefamille=familleservice.getAll("actif");
			 executionoperation=true;
			 request.setAttribute("executionoperation",executionoperation );
			 request.setAttribute("article",article );
			 request.setAttribute("utilisateur",utilisateur);
			 request.setAttribute("Titre","Modifier article" );
			 request.setAttribute("listefamille",Listefamille );
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/article.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(option.equals("AddArticles")){
			int famille =Integer.parseInt(request.getParameter("famille"));
			String nomcommercials=request.getParameter("nom");
			String statut=request.getParameter("statut");
			article=new setch.beans.articles(utilisateur.getId(),famille,nomcommercials,statut);
			if(articleservice.getByid(nomcommercials)==null)
			{
				executionoperation=articleservice.add(article);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Articles?option=ListArticles");
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Articles?option=AddArticles");   
				}
			}
			else
			{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location","./Articles?option=AddArticles"); 
			}
			
		}
		else if(option.equals("UpdateArticles")) {
			int famille =Integer.parseInt(request.getParameter("famille"));
			String nomcommercials=request.getParameter("nom");
			String statut=request.getParameter("statut");
			article=new setch.beans.articles(utilisateur.getId(),famille,nomcommercials,statut);
				executionoperation=articleservice.update(matricule, article);
				if(executionoperation==true)
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Articles?option=ListArticles"); 
				}
				else
				{
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location","./Articles?option=UpdateArticles&&id="+matricule+"");  
				}
		}
	}

}
