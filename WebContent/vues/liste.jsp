<%@ include file="header.jsp" %>
<%@page import="setch.service.reductionservice"%>
<%@page import="setch.beans.reductionfacture"%>
<%@page import="setch.beans.contenuautorisation"%>
<%@page import="setch.service.autorisationservice"%>
<%@page import="setch.service.paymentcreditservice"%>
<%@page import="setch.beans.autorisation"%>
<%@page import="setch.beans.entreprise"%>
<%@page import="setch.beans.utilisateur"%>
<%@page import="setch.beans.contenuachat2"%>
<%@page import="setch.beans.contenuachat"%>
<%@page import="setch.beans.validite"%>
<%@page import="setch.beans.commissions"%>
<%@page import="setch.beans.statistiquecommission"%>
<%@page import="setch.beans.statistiquevente2"%>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.service.utilisateurservice"%>
<%@page import="setch.beans.vente"%>
<%@page import="setch.beans.recapcorrectionstock"%>
<%@page import="setch.beans.paymentcredit"%>
<%@page import="setch.service.venteservice"%>
<%@page import="setch.service.paymentcreditservice"%>
<%@page import="setch.service.cryptpwds"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.correctionstock"%>
<%@page import="setch.beans.unitevente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.service"%>
<%@page import="setch.service.serviceservice"%>
<%@page import="setch.service.familleservice"%>
<%@page import="setch.service.offreservice"%>
<%@page import="setch.service.livraisonservice"%>
<%@page import="setch.service.livraisonventeservice"%>
<%@page import="setch.service.correctionstockservice"%>
<%@page import="setch.service.besoinservice"%>
<%@page import="setch.service.inventaireservice"%>
<%@page import="setch.service.transfertservice"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.recapoffre"%>
<%@page import="setch.beans.recapbesoin"%>
<%@page import="setch.beans.recapinventaire"%>
<%@page import="setch.beans.recapcommande"%>
<%@page import="setch.beans.recaplivraison"%>
<%@page import="setch.beans.recaplivraisonvente"%>
<%@page import="setch.beans.recapvente"%>
<%@page import="setch.beans.offre"%>
<%@page import="setch.beans.besoin"%>
<%@page import="setch.beans.inventaire"%>
<%@page import="setch.beans.livraison"%>
<%@page import="setch.beans.livraisonvente"%>
<%@page import="setch.beans.marge"%>
<%@page import="setch.beans.famille"%>
<%@page import="setch.beans.transfert"%>
<%@page import="setch.beans.recaptransfert"%>
<%@ page import="java.util.List" %>
  <%@ page import="java.util.ArrayList" %>
<%
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->

          <div class="col-md-12 ">
            <div class="content-panel pane">
             <%
             	List<commissions>listecommissions=null;
                                       personne personnes=null;
                                       articles articles=null;
                                       service services=null;
                                       List<validite>listevalidite=null;
                                       List<utilisateur>listeutilisateur=null;
                                       List<entreprise>listeentreprise=null;
                                        List<famille>listefamille=null;
                                        List<service>listeservice=null;
                                        List<articles>listearticle=null;
                                        List<contenuachat>listecontenuachat=null;
                                        List<paymentcredit>listepayementcredit=null;
                                        List<personne>listepersonne=null;
                                        List<prixvente>listeprixvente=null;
                                        List<unitevente>listeunitevente=null;
                                        List<statistiquecommission>listecommission=null;
                                        List<String>listefacture=null;
                                        List<vente>listevente=null;
                                        List<recapoffre>listeoffre=null;
                                        List<marge>listemarge=null;
                                        List<recapbesoin>listecommande=null;
                                        List<recapinventaire>listeinventaire=null;
                                        List<correctionstock>listecorrectionstock=null;
                                        List<recapvente>listerecapvente=null;
                                        List<recapcommande>listecommande1=null;
                                        List<recaplivraison>listerecaplivraison=null;
                                        List<recaplivraisonvente>listerecaplivraisonvente=null;
                                        List<livraison>listelivraison=null;
                                        List<livraisonvente>listelivraisonvente=null;
                                        List<offre>detailoffre=null;
                                        List<besoin>detailcommande=null;
                                        List<livraison>detaillivraison=null;
                                        List<livraisonvente>detaillivraisonvente=null;
                                        List<recapcorrectionstock>listerecapcorrectionstock=null;
                                        List<autorisation>listeautorisation=null;
                                        List<contenuautorisation>listecontenuautorisations=null;
                                        List<statistiquevente2>listeetatvente=null;
                                        List<recaptransfert>listerecaptransfert=null;
                                        List<transfert>listetransfert=null;
                                        familleservice familleservice=new familleservice();
                                        articleservice articleservice=new articleservice();
                                        venteservice venteservice=new venteservice();
                                        besoinservice besoinservice=new besoinservice();
                                        inventaireservice inventaireservice=new inventaireservice();
                                        paymentcreditservice payementservice=new paymentcreditservice();
                                        livraisonservice livraisonservice=new livraisonservice();
                                        livraisonventeservice livraisonventeservice=new livraisonventeservice();
                                        utilisateurservice utilisateurservice=new utilisateurservice();
                                        autorisationservice autorisationservice=new autorisationservice();
                                        personneservice personneservice=new personneservice();
                                        serviceservice serviceservice=new serviceservice();
                                       transfertservice transfertservice=new transfertservice();
                                        prixventeservice prixventeservice=new prixventeservice();
                                        reductionfacture reduction=new reductionfacture();
                                        reductionservice reductionservice=new reductionservice();
                                        paymentcreditservice paymentcreditservice=new paymentcreditservice();
                                        correctionstockservice correctionstockservice=new correctionstockservice();
                                        offreservice offreservice=new offreservice();
                                        String option=(String)request.getAttribute("option");
                                        double totalgeneral=0;
                                        String periode1="";
                                        String periode2="";
                                        String a="";
                                        int t=0;
                                        if(option.equals("ListFamilles"))
                                        {
                                        listefamille=(List<famille>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListPersonnes"))
                                        {
                                        listepersonne=(List<personne>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("service"))
                                        {
                                        listeservice=(List<service>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListArticles"))
                                        {
                                        listearticle=(List<articles>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListContenuAchat"))
                                        {
                                        listecontenuachat=(List<contenuachat>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListPrixVente"))
                                        {
                                        listeprixvente=(List<prixvente>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListUniteVente"))
                                        {
                                        listeunitevente=(List<unitevente>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListOffre"))
                                        {
                                        listeoffre=(List<recapoffre>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("DetailOffre"))
                                        {
                                        detailoffre=(List<offre>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListeLivraison"))
                                        {
                                        listerecaplivraison=(List<recaplivraison>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("livraisonvente"))
                                        {
                                        listerecaplivraisonvente=(List<recaplivraisonvente>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("visualiserlivraison"))
                                        {
                                        detaillivraison=(List<livraison>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("visualiserlivraisonvente"))
                                        {
                                        detaillivraisonvente=(List<livraisonvente>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("transfert"))
                                        {
                                        listerecaptransfert=(List<recaptransfert>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("listecorrectionstock"))
                                        {
                                        listerecapcorrectionstock=(List<recapcorrectionstock>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("detailcorrectionstock"))
                                        {
                                        listecorrectionstock=(List<correctionstock>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListeBesoin"))
                                        {
                                        listecommande=(List<recapbesoin>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListeInventaire"))
                                        {
                                        listeinventaire=(List<recapinventaire>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("listepayementcredit"))
                                        {
                                        listepayementcredit=(List<paymentcredit>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("creationcommande2"))
                                        {
                                        listecommande1=(List<recapcommande>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("commande"))
                                        {
                                        listecommande1=(List<recapcommande>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("visualiserbesoin"))
                                        {
                                        detailcommande=(List<besoin>)request.getAttribute("liste");
                                        t=(int)request.getAttribute("t");
                                        }
                                        else if(option.equals("visualisercommande"))
                                        {
                                        detailcommande=(List<besoin>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListVenteNormale")||option.equals("ventespecifique")||option.equals("ventecredit")||option.equals("retourventespecifique")||option.equals("retourventenormale"))
                                        {
                                        listefacture=(List<String>)request.getAttribute("listefacture");
                                        listevente=venteservice.getAll("facture");
                                        if(listevente.size()>0)
                                        { 
                                        for(int i=0;i<listevente.size();i++)
                                        {
                                        a=a+":"+listevente.get(i).getFacture();
                                        
                                        }
                                        }
                                        }
                                       
                                        else if(option.equals("etatvente")||option.equals("etatvente1"))
                                        {
                                        listeetatvente=(List<statistiquevente2>)request.getAttribute("listefacture");
                                        }
                                        else if(option.equals("etatjournalier1"))
                                        {
                                        listerecapvente=(List<recapvente>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("etatventeparfamille"))
                                        {
                                        listevente=(List<vente>)request.getAttribute("listefacture");
                                        }
                                        else if(option.equals("etatcommission"))
                                        {
                                        listeetatvente=(List<statistiquevente2>)request.getAttribute("listefacture");
                                        periode1=(String)request.getAttribute("periode1");
                                        periode2=(String)request.getAttribute("periode2");
                                        }
                                        else if(option.equals("visualiservente"))
                                        {
                                      	listevente=(List<vente>)request.getAttribute("listevente");
                                        }
                                        else if(option.equals("visualisercommission"))
                                        {
                                        listecommission=(List<statistiquecommission>)request.getAttribute("listefacture");
                                        }
                                        else if(option.equals("pourcentagecommission"))
                                        {
                                        listecommissions=(List<commissions>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ValiditeList"))
                                        {
                                        listevalidite=(List<validite>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListTypeFacturation"))
                                        {
                                        listefacturation=(List<facturation>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("UserList"))
                                        {
                                        listeutilisateur=(List<utilisateur>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("entreprise"))
                                        {
                                        listeentreprise=(List<entreprise>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("ListAutorisations"))
                                        {
                                        listeautorisation=(List<autorisation>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("DetailAutorisationsView"))
                                        {
                                        listecontenuautorisations=(List<contenuautorisation>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("etatmarge1"))
                                        {
                                        listemarge=(List<marge>)request.getAttribute("liste");
                                        }
                                        else if(option.equals("visualisertransfert"))
                                        {
                                        listetransfert=(List<transfert>)request.getAttribute("liste");
                                        }
                                        if(option.equals("ListFamilles"))
                                        {
             %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="bande">
                <div class="creation">
                <%
                famille=-1;
                do
                {
              	  famille=famille+1;
                }
                while(famille<listecontenuautorisation.size()&&!listecontenuautorisation.get(famille).getTable().equals("famille"));
                if(famille>=0)
                {
                	if(listecontenuautorisation.get(famille).getCreer().equals("true"))
                	{
                		%>
                		   <a href="./Familles?option=AddFamilles" class="creation1"><h5>Nouvelle famille</h5></a>
                		  
                		<%
                	}
                }
                %>
                
                </div>
               
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>id</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom</th>
                    <th><i class=" fa fa-edit"></i> Status</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listefamille.size()>0)
                {
                	for(int i=0;i<listefamille.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listefamille.get(i).getId()); %></td>
                    <td class="hidden-phone"><%out.print(listefamille.get(i).getNom()); %></td>
                    <td><%if(listefamille.get(i).getStatut().equals("actif")){%><span class="label label-info label-mini"><%out.print(listefamille.get(i).getStatut()); %></span><%}else{%><span class="label label-success label-mini"><%out.print(listefamille.get(i).getStatut()); %></span><%} %></td>
                    <td class="modif">
                    <%
                    famille=-1;
                do
                {
              	  famille=famille+1;
                }
                while(famille<listecontenuautorisation.size()&&!listecontenuautorisation.get(famille).getTable().equals("famille"));
                if(famille>=0)
                {
                	if(listecontenuautorisation.get(famille).getModifier().equals("true"))
                	{
                		%>
                		    <a href="./Familles?option=UpdateFamilles&id=<%out.print(listefamille.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              if(option.equals("service"))
              {
             %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                service=-1;
                do
                {
              	  service=service+1;
                }
                while(service<listecontenuautorisation.size()&&!listecontenuautorisation.get(service).getTable().equals("service"));
                if(service>=0)
                {
                	if(listecontenuautorisation.get(service).getCreer().equals("true"))
                	{
                		%>
                		   <a href="./login?option=creationservice" class="creation1"><h5>Nouveau service</h5></a>
                		<%
                	}
                }
                %>
                
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>id</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom</th>
                    <th><i class=" fa fa-edit"></i> Status</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeservice.size()>0)
                {
                	for(int i=0;i<listeservice.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listeservice.get(i).getId()); %></td>
                    <td class="hidden-phone"><%out.print(listeservice.get(i).getNom()); %></td>
                    <td><%if(listeservice.get(i).getStatut().equals("actif")){%><span class="label label-info label-mini"><%out.print(listeservice.get(i).getStatut()); %></span><%}else{%><span class="label label-success label-mini"><%out.print(listeservice.get(i).getStatut()); %></span><%} %></td>
                    <td class="modif">
                    <%
                    service=-1;
                do
                {
              	  service=service+1;
                }
                while(service<listecontenuautorisation.size()&&!listecontenuautorisation.get(service).getTable().equals("service"));
                if(service>=0)
                {
                	if(listecontenuautorisation.get(service).getModifier().equals("true"))
                	{
                		%>
                		    <a href="./login?option=modifierservice&id=<%out.print(listeservice.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }                         
              else if(option.equals("ListArticles"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                 article=-1;
                do
                {
              	  article=article+1;
                }
                while(article<listecontenuautorisation.size()&&!listecontenuautorisation.get(article).getTable().equals("article"));
                if(article>=0)
                {
                	if(listecontenuautorisation.get(article).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./Articles?option=AddArticles" class="creation1"><h5>Nouvel article</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>famille</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom</th>
                    
                    <th><i class=" fa fa-edit"></i> Status</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listearticle.size()>0)
                {
                	for(int i=0;i<listearticle.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(familleservice.getByid(listearticle.get(i).getIdfamille()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listearticle.get(i).getNom()); %></td>
                    <td><%if(listearticle.get(i).getStatut().equals("actif")){%><span class="label label-info label-mini"><%out.print(listearticle.get(i).getStatut()); %></span><%}else{%><span class="label label-success label-mini"><%out.print(listearticle.get(i).getStatut()); %></span><%} %></td>
                    <td class="modif">
                    <%
                 article=-1;
                do
                {
              	  article=article+1;
                }
                while(article<listecontenuautorisation.size()&&!listecontenuautorisation.get(article).getTable().equals("article"));
                if(article>=0)
                {
                	if(listecontenuautorisation.get(article).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./Articles?option=UpdateArticles&id=<%out.print(listearticle.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>    
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListContenuAchat"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                 contenuachat=-1;
                do
                {
              	  contenuachat=contenuachat+1;
                }
                while(contenuachat<listecontenuautorisation.size()&&!listecontenuautorisation.get(contenuachat).getTable().equals("contenuachat"));
                if(contenuachat>=0)
                {
                	if(listecontenuautorisation.get(contenuachat).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./ContenuAchat?option=AddContenuAchat" class="creation1"><h5>Nouveau Contenu Achat</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Article</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Contenu</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecontenuachat.size()>0)
                {
                	for(int i=0;i<listecontenuachat.size();i++)
                	{
                		String val="B/"+listecontenuachat.get(i).getQuantite()+"";
                	%>
                	<tr>
                    <td><%out.print(articleservice.getByid(listecontenuachat.get(i).getArticle()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(val); %></td>
                    <td class="modif">
                    <%
                 contenuachat=-1;
                do
                {
              	  contenuachat=contenuachat+1;
                }
                while(contenuachat<listecontenuautorisation.size()&&!listecontenuautorisation.get(contenuachat).getTable().equals("contenuachat"));
                if(contenuachat>=0)
                {
                	if(listecontenuautorisation.get(contenuachat).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./ContenuAchat?option=UpdateContenuAchat&id=<%out.print(listecontenuachat.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>    
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListPersonnes"))
              {
            	  %>
            	             	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                personne=-1;
                do
                {
              	  personne=personne+1;
                }
                while(personne<listecontenuautorisation.size()&&!listecontenuautorisation.get(personne).getTable().equals("personne"));
                if(personne>=0)
                {
                	if(listecontenuautorisation.get(personne).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./Personnes?option=AddPersonnes" class="creation1"><h5>Nouvelle personne</h5></a>
                		<%
                	}
                } 
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>id</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Telephone</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Titre</th>
                    <th><i class=" fa fa-edit"></i> Status</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listepersonne.size()>0)
                {
                	for(int i=0;i<listepersonne.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listepersonne.get(i).getId()); %></td>
                    <td class="hidden-phone"><%out.print(listepersonne.get(i).getNom()); %></td>
                     <td class="hidden-phone"><%out.print(listepersonne.get(i).getPhone()); %></td>
                    <td class="hidden-phone"><%out.print(listepersonne.get(i).getTitre()); %></td>
                    <td><%if(listepersonne.get(i).getStatut().equals("actif")){%><span class="label label-info label-mini"><%out.print(listepersonne.get(i).getStatut()); %></span><%}else{%><span class="label label-success label-mini"><%out.print(listepersonne.get(i).getStatut()); %></span><%} %></td>
                    <td class="modif">
                    <%
                personne=-1;
                do
                {
              	  personne=personne+1;
                }
                while(personne<listecontenuautorisation.size()&&!listecontenuautorisation.get(personne).getTable().equals("personne"));
                if(personne>=0)
                {
                	if(listecontenuautorisation.get(personne).getModifier().equals("true"))
                	{
                		%>
                		      <a href="./Personnes?option=UpdatePersonnes&id=<%out.print(listepersonne.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                } 
                %>    
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListPrixVente"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                prix=-1;
                do
                {
              	  prix=prix+1;
                }
                while(prix<listecontenuautorisation.size()&&!listecontenuautorisation.get(prix).getTable().equals("prixvente"));
                if(prix>=0)
                {
                	if(listecontenuautorisation.get(prix).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./PrixVente?option=AddPrixVente" class="creation1"><h5>Nouveau prix de vente</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix vente</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeprixvente.size()>0)
                {
                	for(int i=0;i<listeprixvente.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listeprixvente.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(articleservice.getByid((listeprixvente.get(i).getIdarticle())).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listeprixvente.get(i).getPrixvente()); %></td>
                    <td class="modif">
                    <%
                prix=-1;
                do
                {
              	  prix=prix+1;
                }
                while(prix<listecontenuautorisation.size()&&!listecontenuautorisation.get(prix).getTable().equals("prixvente"));
                if(prix>=0)
                {
                	if(listecontenuautorisation.get(prix).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./PrixVente?option=UpdatePrixVente&id=<%out.print(listeprixvente.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListUniteVente"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                unite=-1;
                do
                {
              	  unite=unite+1;
                }
                while(unite<listecontenuautorisation.size()&&!listecontenuautorisation.get(unite).getTable().equals("unitevente"));
                if(unite>=0)
                {
                	if(listecontenuautorisation.get(unite).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./UniteVente?option=AddUniteVente" class="creation1"><h5>Nouvelle unite de vente</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite vendue</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeunitevente.size()>0)
                {
                	for(int i=0;i<listeunitevente.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listeunitevente.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(articleservice.getByid((listeunitevente.get(i).getIdarticle())).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listeunitevente.get(i).getUnitevente()); %></td>
                    <td class="modif">
                    <%
                unite=-1;
                do
                {
              	  unite=unite+1;
                }
                while(unite<listecontenuautorisation.size()&&!listecontenuautorisation.get(unite).getTable().equals("unitevente"));
                if(unite>=0)
                {
                	if(listecontenuautorisation.get(unite).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./UniteVente?option=UpdateUniteVente&id=<%out.print(listeunitevente.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("listepayementcredit"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Paiement</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listepayementcredit.size()>0)
                {
                	for(int i=0;i<listepayementcredit.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listepayementcredit.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listepayementcredit.get(i).getMontant()); %></td>
                    <td class="modif">
                    <%
               int credit=-1;
                do
                {
              	  credit=credit+1;
                }
                while(credit<listecontenuautorisation.size()&&!listecontenuautorisation.get(credit).getTable().equals("paymentcredit"));
                if(credit>=0)
                {
                	if(listecontenuautorisation.get(credit).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./login?option=modifierpayementcredit&id=<%out.print(listepayementcredit.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListOffre"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                offre=-1;
                do
                {
              	  offre=offre+1;
                }
                while(unite<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
                if(offre>=0)
                {
                	if(listecontenuautorisation.get(offre).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./OffrePrixAchat?option=AddOffreFournisseur" class="creation1"><h5>Nouvelle offre</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>numero</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>fournisseur</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeoffre.size()>0)
                {
                	for(int i=0;i<listeoffre.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listeoffre.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listeoffre.get(i).getNumero()); %></td>
                    <td class="hidden-phone"><%out.print(personneservice.getByid((listeoffre.get(i).getFournisseur())).getNom()); %></td>
                    <td class="modif">
                    <%
                offre=-1;
                do
                {
              	  offre=offre+1;
                }
                while(offre<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
                if(offre>=0)
                {
                	if(listecontenuautorisation.get(offre).getVisualiser().equals("true"))
                	{
                		%>
                		     <a href="./OffrePrixAchat?option=DetailOffre&id=<%out.print(listeoffre.get(i).getNumero()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                    <td class="modif">
                     <%
                offre=-1;
                do
                {
              	  offre=offre+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
                if(offre>=0)
                {
                	if(listecontenuautorisation.get(offre).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./OffrePrixAchat?option=PrintOffre&id=<%out.print(listeoffre.get(i).getNumero()); %>&fournisser=<%out.print(personneservice.getByid((listeoffre.get(i).getFournisseur())).getId()); %>" class="btn btn-primary btn-xs" target="_blank"><i class="fa fa-print"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                    
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("DetailOffre"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                 <div class="creation">
                <%
                offre=-1;
                do
                {
              	  offre=offre+1;
                }
                while(unite<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
                if(offre>=0)
                {
                	if(listecontenuautorisation.get(offre).getImprimer().equals("true"))
                	{
                		%>
                		    <!--   <a href="./login?option=imprimeroffre"" class="creation1"><h5>Imprimer</h5></a> -->
                		<%
                	}
                }
                %>
                </div>
                <thead>
                  <tr>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Statut</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detailoffre.size()>0)
                {
                	for(int i=0;i<detailoffre.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(articleservice.getByid(detailoffre.get(i).getIdarticle()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detailoffre.get(i).getPrixvente()); %></td>
                    <td class="hidden-phone"><%out.print(detailoffre.get(i).getStatut()); %></td>
                    <td class="modif">
                    <%
                offre=-1;
                do
                {
              	  offre=offre+1;
                }
                while(offre<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
                if(offre>=0)
                {
                	if(listecontenuautorisation.get(offre).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./OffrePrixAchat?option=UpdateOffre&id=<%out.print(detailoffre.get(i).getId()); %>" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 
            	  <%
              }
              else if(option.equals("ListeInventaire"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                inventaire=-1;
                do
                {
              	  inventaire=inventaire+1;
                }
                while(inventaire<listecontenuautorisation.size()&&!listecontenuautorisation.get(inventaire).getTable().equals("inventaire"));
                if(inventaire>=0)
                {
                	if(listecontenuautorisation.get(inventaire).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./inventaire?option=AddInventaire" class="creation1"><h5>Nouvel inventaire</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeinventaire.size()>0)
                {
                	for(int i=0;i<listeinventaire.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listeinventaire.get(i).getNumero()); %></td>
                    <td class="hidden-phone"><%out.print(listeinventaire.get(i).getDate()); %></td>
                    <td class="modif">
                    <%
                inventaire=-1;
                do
                {
              	  inventaire=inventaire+1;
                }
                while(inventaire<listecontenuautorisation.size()&&!listecontenuautorisation.get(inventaire).getTable().equals("inventaire"));
                if(inventaire>=0)
                {
                	if(listecontenuautorisation.get(inventaire).getVisualiser().equals("true"))
                	{
                		%>
                		     <a href="./inventaire?option=UpdateInventaire&id=<%out.print(listeinventaire.get(i).getNumero()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                    <td class="modif">
                    <%
                inventaire=-1;
                do
                {
              	  inventaire=inventaire+1;
                }
                while(inventaire<listecontenuautorisation.size()&&!listecontenuautorisation.get(inventaire).getTable().equals("inventaire"));
                if(inventaire>=0)
                {
                	if(listecontenuautorisation.get(inventaire).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./inventaire?option=PrintInventaire&id=<%out.print(listeinventaire.get(i).getNumero()); %>" class="btn btn-primary btn-xs" target="_blank"><i class="fa fa-print"></i></a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("ListeBesoin"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./Besoin?option=ChoixType"" class="creation1"><h5>Nouveau besoin</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Type</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Montant</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecommande.size()>0)
                {
                	for(int i=0;i<listecommande.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listecommande.get(i).getType()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande.get(i).getNumero()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande.get(i).getDate()); %></td>
                     <td class="hidden-phone"><%out.print(listecommande.get(i).getMontant()); %></td>
                    <td class="modif">
                    <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getModifier().equals("true") && !listecommande.get(i).getStatut().equals("transforme"))
                	{
                		%>
                		     <a href="./Besoin?option=UpdateBesoin&id=<%out.print(listecommande.get(i).getNumero()); %> " class="btn btn-primary btn-xs" >Update</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                    <td class="modif">
                    <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./Besoin?option=PrintBesoin&id=<%out.print(listecommande.get(i).getNumero()); %>" class="btn btn-primary btn-xs" target="_blank">Print</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                     <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./Besoin?option=PrintCommande&id=<%out.print(listecommande.get(i).getNumero()); %>" class="btn btn-primary btn-xs" >Commande</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                     <td class="modif">
                    <%
                livraison=-1;
                do
                {
              	  livraison=livraison+1;
                }
                while(livraison<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraison).getTable().equals("livraison"));
                if(livraison>=0)
                {
                	if(listecontenuautorisation.get(livraison).getVisualiser().equals("true"))
                	{
                		%>
                		     <a href="./Livraison?option=ListeLivraison&id=<%out.print(listecommande.get(i).getNumero()); %>" class="btn btn-primary btn-xs" >Livraison</a>
                		<%
                	}
                	
                }
                
                %>  
               </td>
                <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./Livraison?option=RapportLivraison&id=<%out.print(listecommande.get(i).getNumero()); %>" class="btn btn-primary btn-xs" target="_blank">Rapport Livraison</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("visualiserbesoin"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div id="besoin">
                 <div class="creation">
                <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./login?option=printfacturevente1&id=<%out.print(detailcommande.get(0).getNumero()); %>" class="creation1" target="_blank"><h5>Imprimer</h5></a>
                		<%
                	}
                }
                
                %>
                </div>
                <br>
                <div class="creation">
                <%
                
                if(t>0){
                		%>
       		     			<a href="./login?option=ajouterlignebesoin" class="creation1"><h5>Ajouter ligne</h5></a>
       					<%
                }
                %>
                </div>
                </div>
                
                <thead>
                  <tr>
                 	 <th class="hidden-phone"><i class="fa fa-question-circle"></i>Fourniseur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix unit</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix total</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detailcommande.size()>0)
                {
                	for(int i=0;i<detailcommande.size();i++)
                	{
                		detailoffre=offreservice.getAll3(detailcommande.get(i).getIdarticle(),"actif");
                		personnes=personneservice.getByid(detailoffre.get(0).getIdfournisseur());
                		articles=articleservice.getByid(detailcommande.get(i).getIdarticle());
                		Double quantite=detailcommande.get(i).getQuantite();
                		Double p=detailoffre.get(0).getPrixvente();
                		Double t1=quantite*p;
                		
                	%>
                	<tr>
                    <td><%out.print(personnes.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detailcommande.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(detailoffre.get(0).getPrixvente()); %></td>
                    <td class="hidden-phone"><%out.print(t1); %></td>
                    <td class="modif">
                    <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getModifier().equals("true"))
                	{
                		%>
                		
                		     <a href="./login?option=modifierbesoin&id=<%out.print(detailcommande.get(i).getId()); %>" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 <!-- Modal -->
           
            	  <%
              }
              else if(option.equals("creationcommande2"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <%
                if(listecommande1.size()>0){
                	%>
                	<div class="creation">
                	<a href="./login?option=modifiercommande&id=<%out.print(listecommande1.get(0).getNumero()); %>&fournisser=<%out.print(listecommande1.get(0).getFournisseur()); %>" class="creation1"><h5>Transformer en commande</h5></a>	
                	</div>
                	<%
                }
                %> 
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Type</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Fournisseur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Montant</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecommande1.size()>0)
                {
                	for(int i=0;i<listecommande1.size();i++)
                	{
                	int t1=besoinservice.getAll(listecommande1.get(i).getNumero(),"en attente",listecommande1.get(i).getFournisseur()).size();
                	%>
                	<tr <%if(t1>0){%>style='color:red;'<%} %>>
                    <td><%out.print(listecommande1.get(i).getType()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande1.get(i).getNumero()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande1.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande1.get(i).getFournisseur()); %></td>
                     <td class="hidden-phone"><%out.print(listecommande1.get(i).getMontant()); %></td>
                    <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getVisualiser().equals("true"))
                	{
                		%>
                		     <a href="./login?option=visualisercommande&id=<%out.print(listecommande1.get(i).getNumero()); %>&fournisser=<%out.print(listecommande1.get(i).getFournisseur()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                }
                if(t1<=0)
                {
                %>  
                    </td>
                     <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./login?option=printfacturevente1&id=<%out.print(listecommande1.get(i).getNumero()); %>&fournisser=<%out.print(listecommande1.get(i).getFournisseur()); %>" class="btn btn-primary btn-xs" target="_blank"><i class="fa fa-print"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                    <%
                    
                }
                else{
                	 %>  
                     </td>
                      
                     <%
                	
                }
                    %>
                  </tr>
                	<%	
                
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("commande"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Type</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Fournisseur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Montant</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecommande1.size()>0)
                {
                	for(int i=0;i<listecommande1.size();i++)
                	{
                		personnes=personneservice.getByid((listecommande1.get(i).getFournisseur()));
                	%>
                	<tr >
                    <td><%out.print(listecommande1.get(i).getType()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande1.get(i).getNumero()); %></td>
                    <td class="hidden-phone"><%out.print(listecommande1.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(personnes.getNom()); %></td>
                     <td class="hidden-phone"><%out.print(listecommande1.get(i).getMontant()); %></td>
                    <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getVisualiser().equals("true"))
                	{
                		%>
                		     <a href="./login?option=visualisercommande&id=<%out.print(listecommande1.get(i).getNumero()); %>&fournisser=<%out.print(listecommande1.get(i).getFournisseur()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                }
                
                %>  
                    </td>
                     <td class="modif">
                    <%
                commande=-1;
                do
                {
              	  commande=commande+1;
                }
                while(commande<listecontenuautorisation.size()&&!listecontenuautorisation.get(commande).getTable().equals("commande"));
                if(commande>=0)
                {
                	if(listecontenuautorisation.get(commande).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./login?option=printfacturevente1&id=<%out.print(listecommande1.get(i).getNumero()); %>&fournisser=<%out.print(listecommande1.get(i).getFournisseur()); %>" class="btn btn-primary btn-xs" target="_blank"><i class="fa fa-print"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                    
                  </tr>
                	<%	
                
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("visualisercommande"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>              
                <thead>
                  <tr>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix unit</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix total</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detailcommande.size()>0)
                {
                	for(int i=0;i<detailcommande.size();i++)
                	{
                		detailoffre=offreservice.getAll3(detailcommande.get(i).getIdarticle(),"actif");
                		personnes=personneservice.getByid(detailoffre.get(0).getIdfournisseur());
                		articles=articleservice.getByid(detailcommande.get(i).getIdarticle());
                		Double quantite=detailcommande.get(i).getQuantite();
                		Double p=detailoffre.get(0).getPrixvente();
                		Double t1=quantite*p;
                		
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detailcommande.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(detailoffre.get(0).getPrixvente()); %></td>
                    <td class="hidden-phone"><%out.print(t1); %></td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListeLivraison"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                livraison=-1;
                do
                {
              	  livraison=livraison+1;
                }
                while(livraison<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraison).getTable().equals("livraison"));
                if(livraison>=0)
                {
                	if(listecontenuautorisation.get(livraison).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./Livraison?option=AddLivraison1"" class="creation1"><h5>Nouvelle livraison</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Statut</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero commande</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero livraison</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date livraison</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>fournisseur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Montant</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listerecaplivraison.size()>0)
                {
                	for(int i=0;i<listerecaplivraison.size();i++)
                	{
                		personnes=personneservice.getByid(listerecaplivraison.get(i).getFournisseur());
                	%>
                	<tr>
                    <td><%out.print(listerecaplivraison.get(i).getStatut()); %></td>
                    <td class="hidden-phone"><%out.print(listerecaplivraison.get(i).getNumerobc()); %></td>
                    <td class="hidden-phone"><%out.print(listerecaplivraison.get(i).getNumerobl()); %></td>
                    <td class="hidden-phone"><%out.print(listerecaplivraison.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(personnes.getNom()); %></td>
                     <td class="hidden-phone"><%out.print(listerecaplivraison.get(i).getMontant()); %></td>
                     <td class="modif">
                    <%
                livraison=-1;
                do
                {
              	  livraison=livraison+1;
                }
                while(livraison<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraison).getTable().equals("livraison"));
                if(livraison>=0)
                {
                	if(listecontenuautorisation.get(livraison).getModifier().equals("true"))
                	{
                		%>
                		     <a href="./Livraison?option=UpdateLivraison&id=<%out.print(listerecaplivraison.get(i).getNumerobl()); %>&date=<%out.print(listerecaplivraison.get(i).getDate()); %>&bc=<%out.print(listerecaplivraison.get(i).getNumerobc()); %>&fournisseur=<%out.print(listerecaplivraison.get(i).getFournisseur()); %> " class="btn btn-primary btn-xs" >Update</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                    <td class="modif">
                    <%
                livraison=-1;
                do
                {
              	  livraison=livraison+1;
                }
                while(livraison<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraison).getTable().equals("livraison"));
                if(livraison>=0)
                {
                	if(listecontenuautorisation.get(livraison).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./Livraison?option=PrintLivraison&id=<%out.print(listerecaplivraison.get(i).getNumerobl()); %>&date=<%out.print(listerecaplivraison.get(i).getDate()); %>&bc=<%out.print(listerecaplivraison.get(i).getNumerobc()); %>&fournisseur=<%out.print(listerecaplivraison.get(i).getFournisseur()); %> "target="_blank" class="btn btn-primary btn-xs" >Print</a>
                		<%
                	}
                	
                }
                
                %>  
                    </td>
                    
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("visualiserlivraison"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <thead>
                  <tr>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix unit</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix total</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detaillivraison.size()>0)
                {
                	for(int i=0;i<detaillivraison.size();i++)
                	{
                		articles=articleservice.getByid(detaillivraison.get(i).getIdarticle());	
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detaillivraison.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(detaillivraison.get(i).getPu()); %></td>
                    <td class="hidden-phone"><%out.print(detaillivraison.get(i).getQuantite()*detaillivraison.get(i).getPu()); %></td>
                    <td class="modif">
                    <%
                livraison=-1;
                do
                {
              	  livraison=livraison+1;
                }
                while(livraison<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraison).getTable().equals("livraison"));
                if(livraison>=0)
                {
                	if(listecontenuautorisation.get(livraison).getModifier().equals("true"))
                	{
                		%>
                		     <a href="#" onClick="showlivraison(<%out.print(detaillivraison.get(i).getId());%>,'<%out.print(articleservice.getByid(detaillivraison.get(i).getIdarticle()).getNom()); %>',<%out.print(detaillivraison.get(i).getQuantite()); %>,'<%out.print(detaillivraison.get(i).getPu()); %>')" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModiflivraison" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification livraison</div></h4>
              </div>
              <div class="modal-body">
                <input type="number" id="idlivraison" name="idlivraison"  class="form-control"   autofocus readonly>
          		<br>
          		<input type="text" id="idarticlelivraison" name="article"  class="form-control"  autofocus readonly>
          		<br>
          		<input type="number" id="quantitelivraison" name="quantitebesoin"  class="form-control"  autofocus required>
          		<br>
          		<input type="number" id="pulivraison" name="pubesoin"  class="form-control"  autofocus required>
          		<br>
          		
              </div>
              <div class="modal-footer">
                <input type="button" id="modiflivraison" value="Soumettre"/>
              </div>
            </div>
          </div>
        </div>
        </form>
            	  <%
              }
              else if(option.equals("livraisonvente"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                
                <%
                livraisonvente=-1;
                do
                {
              	  livraisonvente=livraisonvente+1;
                }
                while(livraisonvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraisonvente).getTable().equals("livraisonvente"));
                if(livraisonvente>=0)
                {
                	if(listecontenuautorisation.get(livraisonvente).getCreer().equals("true"))
                	{
                		listefacturation=facturationservice.getAll();
                		if(listefacturation.get(0).getType().equals("facturationindirecte"))
                		{
                			%>
                    		<div class="creation">
               		     	<a href="./login?option=creationlivraisonvente1"" class="creation1"><h5>Nouvelle livraison</h5></a>
               		     	</div>
               				<%
                		}
                		
                	}
                }
                %>
                
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Statut</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero livraison</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date Document</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Client</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Montant</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listerecaplivraisonvente.size()>0)
                {
                	for(int i=0;i<listerecaplivraisonvente.size();i++)
                	{
                		personnes=personneservice.getByid(listerecaplivraisonvente.get(i).getClient());
                	%>
                	<tr>
                    <td><%out.print(listerecaplivraisonvente.get(i).getStatut()); %></td>
                    <td class="hidden-phone"><%out.print(listerecaplivraisonvente.get(i).getNumerobl()); %></td>
                    <td class="hidden-phone"><%out.print(listerecaplivraisonvente.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(personnes.getNom()); %></td>
                     <td class="hidden-phone"><%out.print(listerecaplivraisonvente.get(i).getMontant()); %></td>
                    <td class="modif">
                    <%
                livraisonvente=-1;
                do
                {
              	  livraisonvente=livraisonvente+1;
                }
                while(livraisonvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraisonvente).getTable().equals("livraisonvente"));
                if(livraisonvente>=0)
                {
                	if(listecontenuautorisation.get(livraisonvente).getVisualiser().equals("true"))
                	{
                		%>
                		<acronym title="Visualiser le document">
                		     <a href="./login?option=visualiserlivraisonvente&id=<%out.print(listerecaplivraisonvente.get(i).getNumerobl()); %>&date=<%out.print(listerecaplivraisonvente.get(i).getDate()); %>&client=<%out.print(listerecaplivraisonvente.get(i).getClient()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a></acronym>
                			
                			 
                		<%
                	}
                }
                
                livraisonvente=-1;
                do
                {
              	  livraisonvente=livraisonvente+1;
                }
                while(livraisonvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraisonvente).getTable().equals("livraisonvente"));
                if(livraisonvente>=0)
                {
                	if(listecontenuautorisation.get(livraisonvente).getImprimer().equals("true"))
                	{
                		%>
                		 <acronym title="Imprimer livraison"><a href="./login?option=printfacturevente1&id=<%out.print(listerecaplivraisonvente.get(i).getNumerobl()); %>&client=<%out.print(listerecaplivraisonvente.get(i).getClient()); %>&date=<%out.print(listerecaplivraisonvente.get(i).getDate()); %>" class="btn btn-primary btn-xs" target=_blank ><i class="fa fa-print"></i></a></acronym>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("visualiserlivraisonvente"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <thead>
                  <tr>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix unit</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix total</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detaillivraisonvente.size()>0)
                {
                	for(int i=0;i<detaillivraisonvente.size();i++)
                	{
                		articles=articleservice.getByid(detaillivraisonvente.get(i).getIdarticle());	
                		Double prixs=prixventeservice.getByid(detaillivraisonvente.get(i).getPu()).getPrixvente();
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detaillivraisonvente.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(prixs); %></td>
                    <td class="hidden-phone"><%out.print(detaillivraisonvente.get(i).getQuantite()*prixs); %></td>
                    <td class="modif">
                    <%
                livraisonvente=-1;
                do
                {
              	  livraisonvente=livraisonvente+1;
                }
                while(livraisonvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(livraisonvente).getTable().equals("livraisonvente"));
                if(livraisonvente>=0)
                {
                	if(listecontenuautorisation.get(livraisonvente).getModifier().equals("true"))
                	{
                		%>
                		     <a href="#" onClick="showlivraisonvente(<%out.print(detaillivraisonvente.get(i).getId());%>,'<%out.print(articleservice.getByid(detaillivraisonvente.get(i).getIdarticle()).getNom()); %>',<%out.print(detaillivraisonvente.get(i).getQuantite()); %>)" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModiflivraisonvente" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification livraison vente</div></h4>
              </div>
              <div class="modal-body">
                <input type="number" id="idlivraisonvente" name="idlivraisonvente"  class="form-control"   autofocus readonly>
          		<br>
          		<input type="text" id="idarticlelivraisonvente" name="articlevente"  class="form-control"  autofocus readonly>
          		<br>
          		<input type="number" id="quantitelivraisonvente" name="quantitebesoinvente"  class="form-control"  autofocus required>
          		<br>
          		
          		
              </div>
              <div class="modal-footer">
                <input type="button" id="modiflivraisonvente" value="Soumettre"/>
              </div>
            </div>
          </div>
        </div>
        </form>
            	  <%
              }
              else if(option.equals("visualiserbesoin"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div id="besoin">
                 <div class="creation">
                <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getImprimer().equals("true"))
                	{
                		%>
                		     <a href="./login?option=imprimerbesoin"" class="creation1"><h5>Imprimer</h5></a>
                		<%
                	}
                }
                
                %>
                </div>
                <br>
                <div class="creation">
                <%
                
                if(t>0){
                		%>
       		     			<a href="./login?option=ajouterlignebesoin" class="creation1"><h5>Ajouter ligne</h5></a>
       					<%
                }
                %>
                </div>
                </div>
                
                <thead>
                  <tr>
                 	 <th class="hidden-phone"><i class="fa fa-question-circle"></i>Fourniseur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix unit</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix total</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(detailcommande.size()>0)
                {
                	for(int i=0;i<detailcommande.size();i++)
                	{
                		detailoffre=offreservice.getAll3(detailcommande.get(i).getIdarticle(),"actif");
                		personnes=personneservice.getByid(detailoffre.get(0).getIdfournisseur());
                		articles=articleservice.getByid(detailcommande.get(i).getIdarticle());
                		Double quantite=detailcommande.get(i).getQuantite();
                		Double p=detailoffre.get(0).getPrixvente();
                		Double t1=quantite*p;
                		
                	%>
                	<tr>
                    <td><%out.print(personnes.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(detailcommande.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(detailoffre.get(0).getPrixvente()); %></td>
                    <td class="hidden-phone"><%out.print(t1); %></td>
                    <td class="modif">
                    <%
                besoin=-1;
                do
                {
              	  besoin=besoin+1;
                }
                while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
                if(besoin>=0)
                {
                	if(listecontenuautorisation.get(besoin).getModifier().equals("true"))
                	{
                		%>
                		     <a href="#" onClick="showbesoin(<%out.print(detailcommande.get(i).getId());%>,'<%out.print(articleservice.getByid(detailoffre.get(0).getIdarticle()).getNom()); %>',<%out.print(detailcommande.get(i).getQuantite()); %>,'<%out.print(detailoffre.get(0).getStatut()); %>','<%out.print(detailcommande.get(i).getNumero()); %>')" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModifbesoin" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification besoin</div></h4>
              </div>
              <div class="modal-body">
          		<input type="text" id="numerobesoin" name="numerobesoin"  class="form-control"  autofocus readonly>
          		<br>
                <input type="number" id="idbesoin" name="idbesoin"  class="form-control"   autofocus readonly>
          		<br>
          		<input type="text" id="idarticlebesoin" name="article"  class="form-control"  autofocus readonly>
          		<br>
          		<input type="number" id="quantitebesoin" name="quantitebesoin"  class="form-control"  autofocus required>
          		<br>
          		
              </div>
              <div class="modal-footer">
                <input type="button" id="modifbesoin" value="Soumettre"/>
              </div>
            </div>
          </div>
        </div>
        </form>
            	  <%
              }
              else if(option.equals("ListVenteNormale")||option.equals("ventespecifique")||option.equals("ventecredit")||option.equals("retourventenormale")||option.equals("retourventespecifique"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                vente=-1;
                do
                {
                	vente=vente+1;
                }
                while(vente<listecontenuautorisation.size()&&!listecontenuautorisation.get(vente).getTable().equals("vente"));
                if(vente>=0)
                {
	                if(listecontenuautorisation.get(vente).getCreer().equals("true"))
	                {
	                	listefacturation=facturationservice.getAll();
		                if(option.equals("ListVenteNormale"))
		                {
		                	
		                	if(listefacturation.get(0).getType().equals("facturationindirecte")){
		                		%>
			                	 <a href="./login?option=blvente" class="creation1"><h5>Nouvelle vente</h5></a>
			                	<% 
		                	}
		                	else{
		                		%>
			                	 <a href="./NomalSale?option=AddVenteNormale" class="creation1"><h5>Nouvelle vente</h5></a>
			                	<% 
		                	}
		                	
		                }
		                if(option.equals("ventecredit"))
		                {
		                	if(listefacturation.get(0).getType().equals("facturationindirecte")){
		                		%>
		                	 	 <a href="./login?option=blvente" class="creation1"><h5>Nouvelle vente à credit</h5></a>
		                		<% 
		                	}
		                	else{
		                		%>
		                		 <a href="./login?option=creationventecredit" class="creation1"><h5>Nouvelle vente à credit</h5></a>
		                		<% 
		                	}
	
		                }
		                else if(option.equals("ventespecifique"))
		                {
		                	if(listefacturation.get(0).getType().equals("facturationindirecte")){
		                		%>
		               			  <a href="./login?option=blvente" class="creation1"><h5>Nouvelle vente specifique</h5></a>
		               			<%  
		                	}
		                	else{
		                		%>
		               	 		<a href="./login?option=creationventespecifique" class="creation1"><h5>Nouvelle vente specifique</h5></a>
		               			<%  
		                	}
		                	
		                } 
		                else if(option.equals("retourventenormale"))
		                {
		                	if(listefacturation.get(0).getType().equals("facturationindirecte")){
		                		%>
		               	 		 <a href="./login?option=blvente" class="creation1"><h5>Nouveau retour normal</h5></a>
		               			<%   
		                	}
		                	else{
		                		%>
		               	 		<a href="#" class="creation1" id="choix" type="creationretourventenormale" value="choix" onClick="choixfacture('<%out.print(a);%>','creationretourventenormale');"><h5>Nouveau retour normal</h5></a>
		               			<% 
		                	}
		                	
		                } 
		                else if(option.equals("retourventespecifique"))
		                {
		                	if(listefacturation.get(0).getType().equals("facturationindirecte")){
		                		%>
		               	 		 <a href="./login?option=blvente" class="creation1"><h5>Nouveau retour specifique</h5></a>
		               			<%   
		                	}
		                	else{
		                		%>
		               	 		<a href="#" class="creation1" id="choix" type="creationretourventespecifique" onClick="choixfacture('<%out.print(a);%>','creationretourventespecifique');"><h5>Nouveau retour specifique</h5></a>
		               			<%  
		                	}
		                	
		                } 
                	}
                }
                //-
                
                %>
               
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Utilisateur</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Facture</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prescripteur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Patients</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Total</th>
                     <%
                     if(option.equals("ventecredit")){
                    	 %>
                    	  <th class="hidden-phone"><i class="fa fa-question-circle"></i>Reglement</th>
                    	   <th class="hidden-phone"><i class="fa fa-question-circle"></i>Solde</th>
                    	 <%
                     }
                     %>
                  </tr>
                </thead>
                <tbody>
                <%
               
                	for(int i=0;i<listefacture.size();i++)
                	{
                	listevente=venteservice.getByfacture(listefacture.get(i));	
                	%>
                	<tr>
                    <td><%out.print(listevente.get(0).getDate()); %></td>
                     <td class="hidden-phone"><%out.print(utilisateurservice.getByid(listevente.get(0).getIduser()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listefacture.get(i)); %></td>
                     <td class="hidden-phone"><%out.print(personneservice.getByid(listevente.get(0).getIdprescripteur()).getNom()); %></td>
                      <td class="hidden-phone"><%out.print(personneservice.getByid(listevente.get(0).getIdpatient()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(venteservice.gettotalfacture(listefacture.get(i))); %></td>
                    <%
                     if(option.equals("ventecredit")){
                    	 Double f=paymentcreditservice.totalpayment(listefacture.get(i));
                    	 Double g=venteservice.gettotalfacture(listefacture.get(i))-f;
                    	 %>
                    	   <td class="hidden-phone"><%out.print(f); %></td>
                    	   <td class="hidden-phone"><%out.print(g); %></td>
                    	 <%
                     }
                     %>
                  
                   <td class="modif">
                    	<a href="./VenteNormale?option=PrintVenteNormale&id=<%out.print(listefacture.get(i)); %>" class="btn btn-primary btn-xs" ><i class="fa fa-print"></i></a>
                    	
                    </td>
                         <td class="modif">
                         <%
                         if((option.equals("ventecredit")))
                         {
                        	 //---
                        	 int credit=-1;
                    do
                    {
                    	credit=credit+1;
                    }
                    while(credit<listecontenuautorisation.size()&&!listecontenuautorisation.get(credit).getTable().equals("paymentcredit"));
                    if(credit>=0)
                    {
                    	 Double g=paymentcreditservice.totalpayment(listefacture.get(i));
                    	if(listecontenuautorisation.get(credit).getCreer().equals("true"))
                    	{
                    		 Double val=venteservice.gettotalfacture(listefacture.get(i));
                             if(val>g){
                         	%>
                         	<a href="./login?option=payementcredit&id=<%out.print(listefacture.get(i)); %>&val=<%out.print(venteservice.gettotalfacture(listefacture.get(i))); %>" class="btn btn-primary btn-xs" ><i class="fa fa-money"></i></a>
                         	<%
                         		}
                    	}
                    	if((listecontenuautorisation.get(credit).getVisualiser().equals("true"))&& (g>0))
                    	{
                    		 %>
                             
                          	<a href="./login?option=listepayementcredit&id=<%out.print(listefacture.get(i)); %>&val=<%out.print(venteservice.gettotalfacture(listefacture.get(i))); %>" class="btn btn-primary btn-xs" ><i class="fa fa-list"></i></a>
                          	<%
                    	}
                    	
                    }    
                         }
                         %>
                           
                         </td>
                  </tr>
                	<%	
                  
                	}
                %>
                </tbody>
              </table>
              
            	  <div class="recherche">
            	  <form method="POST">
	        	<input type="hidden"  class="form-control" id="datepicker1"  autofocus disabled="disabled"/>
	        	<input type="date" name="recherche" class="form-control" id="datepicker"  autofocus/>
	          <br>
	          <button class="btn btn-theme btn-block boutonfacture" name="button"  type="button" OncliCk="rechercheventenormale();"><i class="fa fa-lock"></i>Recherche</button>
	          <hr>
	          </form>
       			 </div>
       			  
            	  <% 
            	  //----
              }
              else if(option.equals("etatventeparfamille"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Utilisateur</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Facture</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Service</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Reduction(%)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prescripteur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Patients</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Total</th>
                  </tr>
                </thead>
                <tbody>
                <%
               
                	for(int i=0;i<listevente.size();i++)
                	{
                	reduction=reductionservice.getByfacture(listevente.get(i).getFacture());
                	if(reduction==null)
                	{
                		reduction=new reductionfacture();
                		reduction.setReduction(0.0);
                	}
                	double totaux=prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente()*listevente.get(i).getQuantite()*(1-(reduction.getReduction()/100));
                	%>
                	<tr>
                    <td><%out.print(listevente.get(i).getDate()); %></td>
                     <td class="hidden-phone"><%out.print(utilisateurservice.getByid(listevente.get(i).getIduser()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listevente.get(i).getFacture()); %></td>
                    <td class="hidden-phone"><%out.print(articleservice.getByid(listevente.get(i).getIdarticle()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(reduction.getReduction()); %></td>
                     <td class="hidden-phone"><%out.print(personneservice.getByid(listevente.get(i).getIdprescripteur()).getNom()); %></td>
                      <td class="hidden-phone"><%out.print(personneservice.getByid(listevente.get(i).getIdpatient()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(totaux); %></td>
                   
                  </tr>
                	<%	
                	}
                
                %>
                </tbody>
              </table>
             
            	  <% 
            	  //----
              }
              else if(option.equals("etatvente")||option.equals("etatvente1"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Famille</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Commissions</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Reste</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>total encaisse</th>
                  </tr>
                </thead>
                <tbody>
                <%
               double commission=0;
                double reste=0;
                	for(int i=0;i<listeetatvente.size();i++)
                	{	
                	%>
                	<tr>
                     <td class="hidden-phone"><%out.print(familleservice.getByid(listeetatvente.get(i).getFamille()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listeetatvente.get(i).getCommission()); %></td>
                     <td class="hidden-phone"><%out.print(listeetatvente.get(i).getReste()+listeetatvente.get(i).getCommission()-listeetatvente.get(i).getCommission()); %></td>
                      <td class="hidden-phone"><%out.print(listeetatvente.get(i).getReste()+listeetatvente.get(i).getCommission()); %></td>
                 <td class="hidden-phone"><a href="./login?option=etatventeparfamille&id=<%out.print(familleservice.getByid(listeetatvente.get(i).getFamille()).getId());%>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a></td>
                  </tr>
                	<%	
                	commission=commission+listeetatvente.get(i).getCommission();
                	reste=reste+listeetatvente.get(i).getReste();
                	}
                
                %>
                </tbody>
                <tfoot>
                <tr>
                <th>Totaux</th>
                <th><%out.print(commission); %></th>
                <th><%out.print(reste); %></th>
                <th><%out.print(commission+reste); %></th>
                </tr>
                </tfoot>
              </table>
            	  <%
              }
              else if(option.equals("etatcommission"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Prescripteur</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Commissions</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Reste</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>total prescrit</th>
                  </tr>
                </thead>
                <tbody>
                <%
                double commission=0;
                double reste=0;
               
                	for(int i=0;i<listeetatvente.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(personneservice.getByid(listeetatvente.get(i).getPrescripteur()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listeetatvente.get(i).getCommission()); %></td>
                     <td class="hidden-phone"><%out.print(listeetatvente.get(i).getReste()+listeetatvente.get(i).getCommission()-listeetatvente.get(i).getCommission()); %></td>
                      <td class="hidden-phone"><%out.print(listeetatvente.get(i).getReste()+listeetatvente.get(i).getCommission()); %></td>
                    <td class="modif">
                      <a href="./login?option=visualisercommission&id=<%out.print(listeetatvente.get(i).getPrescripteur()); %>&id1=<%out.print(periode1); %>&id2=<%out.print(periode2); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                    </td>
                  </tr>
                	<%	
                	commission=commission+listeetatvente.get(i).getCommission();
                	reste=reste+listeetatvente.get(i).getReste();
                	}
                %>
                </tbody>
                <tfoot>
                <tr>
                <th>Totaux</th>
                <th><%out.print(commission); %></th>
                <th><%out.print(reste); %></th>
                <th><%out.print(commission+reste); %></th>
                </tr>
                </tfoot>
              </table>
            	  <%
              }
              else if(option.equals("etatjournalier1"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Rubriques</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Vente normale(1)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Retour positif (2)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Retour negatif(3)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Credit facture(4)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Credit encaisse(5)</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Total(1+2+5)</th>
                  </tr>
                </thead>
                <tbody>
                <%
                	for(int i=0;i<listerecapvente.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listerecapvente.get(i).getFamille()); %></td>
                    <td class="hidden-phone"><%out.print(listerecapvente.get(i).getVN()); %></td>
                     <td class="hidden-phone"><%out.print(listerecapvente.get(i).getRetour1()); %></td>
                      <td class="hidden-phone"><%out.print(listerecapvente.get(i).getRetour2()); %></td>
                      <td class="hidden-phone"><%out.print(listerecapvente.get(i).getCredit1()); %></td>
                     <td class="hidden-phone"><%out.print(listerecapvente.get(i).getCredit2()); %></td>
                      <td class="hidden-phone"><%out.print(listerecapvente.get(i).getTotal()); %></td>
                   
                  </tr>
                	<%
                	}
                	%>
                </tbody>
                <tfoot>
               
                </tfoot>
              </table>
            	  <%
              }
              
              else if(option.equals("visualisercommission"))
              {
            	  %>
             	 <table class="table table-striped table-advance table-hover ok">
                 <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                 <hr>
                 <thead>
                   <tr>
                     <th><i class="fa fa-bullhorn"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Facture</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>Patient</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>Soins</th>
                       <th class="hidden-phone"><i class="fa fa-question-circle"></i>Famille</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>P.unit</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>Pourcentage</th>
                      <th class="hidden-phone"><i class="fa fa-question-circle"></i>P.Total</th>
                       <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quote part</th>
                   </tr>
                 </thead>
                <tbody>
                <%
                double total1=0;
                double total2=0;
               
                	for(int i=0;i<listecommission.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getFacture()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getPatient()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getArticle()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getFamille()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getQuantite()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getPu()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getPourcentage()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getPtixtotal()); %></td>
                    <td class="hidden-phone"><%out.print(listecommission.get(i).getQuotepart()); %></td>
                  </tr>
                	<%
                	total2=total2+listecommission.get(i).getQuotepart();
                	}
                %>
                </tbody>
                <tfoot>
                <tr>
                <th colspan="9">Totaux</th>
                <th><%out.print(total2);%></th>
                </tr>
                </tfoot>
                </table>
				<% 
              }
              else if(option.equals("pourcentagecommission"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                commissions=-1;
                do
                {
              	  commissions=commissions+1;
                }
                while(commissions<listecontenuautorisation.size()&&!listecontenuautorisation.get(commissions).getTable().equals("commissions"));
                if(commissions>=0)
                {
                	if(listecontenuautorisation.get(commissions).getCreer().equals("true"))
                	{
                		%>
                		 <a href="./login?option=creationpourcentage" class="creation1"><h5>Nouveau pourcentage</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Famille</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Pourcentage</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecommissions.size()>0)
                {
                	for(int i=0;i<listecommissions.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(familleservice.getByid(listecommissions.get(i).getFamille()).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listecommissions.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listecommissions.get(i).getPourcentage()); %>%</td>
                    <td class="modif">
                    <%
                    commissions=-1;
                    do
                    {
                  	  commissions=commissions+1;
                    }
                    while(commissions<listecontenuautorisation.size()&&!listecontenuautorisation.get(commissions).getTable().equals("commissions"));
                    if(commissions>=0)
                    {
                    	if(listecontenuautorisation.get(commissions).getModifier().equals("true"))
                    	{
                    		%>
                    		  <a href="./login?option=modifierpourcentage&id=<%out.print(listecommissions.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                    		<%
                    	}
                    }
                    %>
                     
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ValiditeList"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <a href="./Validite?option=ValiditeAdd"" class="creation1"><h5>Nouvelle date</h5></a>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date d expiration</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listevalidite.size()>0)
                {
                	cryptpwds o=new cryptpwds();
                	for(int i=0;i<listevalidite.size();i++)
                	{
                		String p=o.pwddecrypte(listevalidite.get(i).getDate());
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(p); %></td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListTypeFacturation"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <a href="./TypeFacturation?option=AddTypeFacturation" class="creation1"><h5>Nouveau type</h5></a>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Type facturation</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listefacturation.size()>0)
                {
                	for(int i=0;i<listefacturation.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listefacturation.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listefacturation.get(i).getType()); %></td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("entreprise"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
              
                <hr>
                <thead>
                  <tr>
                  <th class="hidden-phone"><i class="fa fa-question-circle"></i>Sigle</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Raison sociale</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Forme juridique</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Activite</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>N identifiant</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>B Postale</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Telephone</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Site web</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeentreprise.size()>0)
                {
                	for(int i=0;i<listeentreprise.size();i++)
                	{
                	%>
                	<tr>
                	 <td class="hidden-phone"><%out.print(listeentreprise.get(i).getSigle()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getName()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getFormejuridique()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getActivite()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getNiu()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getBp()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getTelephone()); %></td>
                    <td class="hidden-phone"><%out.print(listeentreprise.get(i).getWeb()); %></td>
                    <td class="modif">
                      <a href="./Entreprise?option=UpdateEntreprise&id=<%out.print(listeentreprise.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("UserList"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                compte=-1;
                do
                {
                	compte=compte+1;
                }
                while(compte<listecontenuautorisation.size()&&!listecontenuautorisation.get(compte).getTable().equals("utilisateur"));
                if(compte>=0)
                {
                	if(listecontenuautorisation.get(compte).getCreer().equals("true"))
                	{
                		%>
                		 <a href="./User?option=AddUser" class="creation1"><h5>Nouvel utilisateur</h5></a>
                		<%
                	}
                }
                %>
                </div>  
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>login</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Statut</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>autorisation</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listeutilisateur.size()>0)
                {
                	for(int i=0;i<listeutilisateur.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listeutilisateur.get(i).getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listeutilisateur.get(i).getLogin()); %></td>
                    <td class="hidden-phone"><%out.print(listeutilisateur.get(i).getStatut()); %></td>
                    <td class="hidden-phone"><%out.print(autorisationservice.getByid(listeutilisateur.get(i).getAutorisation()).getNom()); %></td> 
                    <td class="modif">
                     <%
                compte=-1;
                do
                {
                	compte=compte+1;
                }
                while(compte<listecontenuautorisation.size()&&!listecontenuautorisation.get(compte).getTable().equals("utilisateur"));
                if(compte>=0)
                {
                	if(listecontenuautorisation.get(compte).getCreer().equals("true"))
                	{
                		%>
                		 <a href="./User?option=UpdateUserStatut&id=<%out.print(listeutilisateur.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>
                      
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("ListAutorisations"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                autorisation=-1;
                do
                {
              	  autorisation=autorisation+1;
                }
                while(autorisation<listecontenuautorisation.size()&&!listecontenuautorisation.get(autorisation).getTable().equals("autorisation"));
                if(autorisation>=0)
                {
                	if(listecontenuautorisation.get(autorisation).getCreer().equals("true"))
                	{
                		%>
                		   <a href="./Autorisations?option=AddAutorisations" class="creation1"><h5>Nouvelle autorisation</h5></a>
                		<%
                	}
                }
                %>
                </div>  
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Ordre</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Nom autorisation</th>
                  </tr>
                </thead>
                <tbody>
                <%
               
                	for(int i=0;i<listeautorisation.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listeautorisation.get(i).getId()); %></td>
                    <td class="hidden-phone"><%out.print(listeautorisation.get(i).getNom()); %></td>
                    <%
                    if(listeautorisation.get(i).getId()>2)
                    {
                    	%>
                    <td class="modif">
                    <%
                autorisation=-1;
                do
                {
              	  autorisation=autorisation+1;
                }
                while(autorisation<listecontenuautorisation.size()&&!listecontenuautorisation.get(autorisation).getTable().equals("autorisation"));
                if(autorisation>=0)
                {
                	if(listecontenuautorisation.get(autorisation).getModifier().equals("true"))
                	{
                		%>
                		   <a href="./Autorisations?option=UpdateAutorisations&id=<%out.print(listeautorisation.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %> 
                    </td>
                     <td class="modif">
                     <%
                    int autorisations=-1;
                     do
                     {
                   	  autorisations=autorisations+1;
                     }
                     while(autorisations<listecontenuautorisation.size()&&!listecontenuautorisation.get(autorisations).getTable().equals("contenuautorisation"));
                     if(autorisations>=0)
                     {
                     	if(listecontenuautorisation.get(autorisations).getVisualiser().equals("true"))
                     	{
                     		%>
                     		  <a href="./ContenuAutorisation?option=DetailAutorisationsView&id=<%out.print(listeautorisation.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                     		<%
                     	}
                     }
                     %>
                    </td>
                    <% 
                    }
                    %> 
                  </tr>
                	<%	
                	}
                
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("DetailAutorisationsView"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <hr>
                <thead>
                  <tr >
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Rubriques</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Acceder</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Creer</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Modifier</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Supprimer</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Imprimer</th>
                  </tr>
                </thead>
                <tbody>
                <%
               
                	for(int i=0;i<listecontenuautorisations.size();i++)
                	{
                	%>
                	<tr>
                    <td ><%out.print(listecontenuautorisations.get(i).getTable()); %></td>            	
                     <td></><input class="checkbox1" type="checkbox" name="visualiser" id="<%out.print(listecontenuautorisations.get(i).getId()); %>" idauto="<%out.print(listecontenuautorisations.get(i).getIdautorisation()); %>" value="visualiser" onClick="ex();" <%if(listecontenuautorisations.get(i).getVisualiser().equals("true")){%>checked<%}  %> /></td>
                      <td><input class="checkbox1" type="checkbox" name="creer" id="<%out.print(listecontenuautorisations.get(i).getId()); %>" idauto="<%out.print(listecontenuautorisations.get(i).getIdautorisation()); %>" value="creer" onClick="ex();" <%if(listecontenuautorisations.get(i).getCreer().equals("true")){%>checked<%}  %> /></td>
                       <td><input class="checkbox1" type="checkbox" name="modifier" id="<%out.print(listecontenuautorisations.get(i).getId()); %>" idauto="<%out.print(listecontenuautorisations.get(i).getIdautorisation()); %>" value="modifier" onClick="ex();" <%if(listecontenuautorisations.get(i).getModifier().equals("true")){%>checked<%}  %> /></td>
                        <td><input class="checkbox1" type="checkbox" name="supprimer" id="<%out.print(listecontenuautorisations.get(i).getId()); %>" idauto="<%out.print(listecontenuautorisations.get(i).getIdautorisation()); %>" value="supprimer" onClick="ex();" <%if(listecontenuautorisations.get(i).getSupprimer().equals("true")){%>checked<%}  %> /></td>
                        <td><input class="checkbox1" type="checkbox" name="imprimer" id="<%out.print(listecontenuautorisations.get(i).getId()); %>" idauto="<%out.print(listecontenuautorisations.get(i).getIdautorisation()); %>" value="imprimer" onClick="ex();" <%if(listecontenuautorisations.get(i).getImprimer().equals("true")){%>checked<%}  %> /></td>
					
                  </tr>
                	<%	
                	}
                
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("etatmarge1"))
              {
            	  %>
            	             	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Nom article</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite vendue</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Cout achat moyen</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Prix de vente moyen</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Marge</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listemarge.size()>0)
                {
                	for(int i=0;i<listemarge.size();i++)
                	{
                	%>
                	<tr>
                    <td><%out.print(listemarge.get(i).getArticle()); %></td>
                    <td class="hidden-phone"><%out.print(listemarge.get(i).getQuantite()); %></td>
                     <td class="hidden-phone"><%out.print(listemarge.get(i).getPa()); %></td>
                    <td class="hidden-phone"><%out.print(listemarge.get(i).getPv()); %></td>
                    <td class="hidden-phone"><%out.print(listemarge.get(i).getMarge()); %></td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("listecorrectionstock"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                correctionstock=-1;
                do
                {
                	correctionstock=correctionstock+1;
                }
                while(correctionstock<listecontenuautorisation.size()&&!listecontenuautorisation.get(correctionstock).getTable().equals("correctionstock"));
                if(correctionstock>=0)
                {
                	if(listecontenuautorisation.get(correctionstock).getCreer().equals("true"))
                	{
                		%>
                		 <a href="./login?option=correctionstock" class="creation1"><h5>Nouvelle correction</h5></a>
                		<%
                	}
                }
                %>
                </div>  
                <hr>
                <thead>
                  <tr>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listerecapcorrectionstock.size()>0)
                {
                	for(int i=0;i<listerecapcorrectionstock.size();i++)
                	{
                	%>
                	<tr>
                    <td class="hidden-phone"><%out.print(listerecapcorrectionstock.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(listerecapcorrectionstock.get(i).getNumero()); %></td>
                    <td class="modif">
                     <%
                correctionstock=-1;
                do
                {
                	correctionstock=correctionstock+1;
                }
                while(correctionstock<listecontenuautorisation.size()&&!listecontenuautorisation.get(correctionstock).getTable().equals("correctionstock"));
                if(correctionstock>=0)
                {
                	if(listecontenuautorisation.get(correctionstock).getCreer().equals("true"))
                	{
                		%>
                		 <a href="./login?option=detailcorrectionstock&id=<%out.print(listerecapcorrectionstock.get(i).getNumero()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                }
                %>
                      
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
            	  <%
              }
              else if(option.equals("detailcorrectionstock"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                
                
                <thead>
                  <tr>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Date</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Article</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listecorrectionstock.size()>0)
                {
                	for(int i=0;i<listecorrectionstock.size();i++)
                	{
                		articles=articleservice.getByid(listecorrectionstock.get(i).getArticle());
                	%>
                	<tr>
                    <td><%out.print(listecorrectionstock.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listecorrectionstock.get(i).getQuantite()); %></td>
                    <td class="modif">
                    <%
               correctionstock=-1;
                do
                {
              	  correctionstock=correctionstock+1;
                }
                while(correctionstock<listecontenuautorisation.size()&&!listecontenuautorisation.get(correctionstock).getTable().equals("correctionstock"));
                if(correctionstock>=0)
                {
                	if(listecontenuautorisation.get(correctionstock).getModifier().equals("true"))
                	{
                		if(listecorrectionstock.get(i).getQuantite()>=0)
                		{
                			%>
               		     <a href="#" onClick="showcorrectionstock(<%out.print(listecorrectionstock.get(i).getId());%>,'<%out.print(articleservice.getByid(listecorrectionstock.get(i).getArticle()).getNom()); %>',<%out.print(listecorrectionstock.get(i).getQuantite()); %>,'<%out.print(listecorrectionstock.get(0).getNumero()); %>')" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
               				<%
                		}
                		else{
                			%>
                  		     <a href="#" onClick="showcorrectionstock1(<%out.print(listecorrectionstock.get(i).getId());%>,'<%out.print(articleservice.getByid(listecorrectionstock.get(i).getArticle()).getNom()); %>',<%out.print(listecorrectionstock.get(i).getQuantite()); %>,'<%out.print(listecorrectionstock.get(0).getNumero()); %>')" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                  				<%
                		}
                		
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>
 <!-- Modal -->
            <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModifcorrectionstock" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification correction</div></h4>
              </div>
              <div class="modal-body">
          		
                <input type="number" id="idcorrection" name="idcorrection"  class="form-control"   autofocus readonly>
          		<br>
          		 <input type="text" id="nc" name="nc"  class="form-control"   autofocus readonly>
          		<br>
          		<input type="text" id="idarticlecorrection" name="article"  class="form-control"  autofocus readonly>
          		<br>
          		<input type="number" id="quantitecorrection" name="quantitecorrection"   class="form-control" min="0" autofocus required>
          		<br>
          		
              </div>
              <div class="modal-footer">
                <input type="button" id="modifcorrection" value="Soumettre"/>
              </div>
            </div>
          </div>
        </div>
        </form>
         <form>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModifcorrectionstock1" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><div id="titreboite">Modification correction</div></h4>
              </div>
              <div class="modal-body">
          		
                <input type="number" id="idcorrection1" name="idcorrection"  class="form-control"   autofocus readonly>
          		<br>
          		 <input type="text" id="nc1" name="nc1"  class="form-control"   autofocus readonly>
          		<br>
          		<input type="text" id="idarticlecorrection1" name="article"  class="form-control"  autofocus readonly>
          		<br>
          		<input type="number" id="quantitecorrection1" name="quantitecorrection"   class="form-control" max="0" autofocus required>
          		<br>
          		
              </div>
              <div class="modal-footer">
                <input type="button" id="modifcorrection1" value="Soumettre"/>
              </div>
            </div>
          </div>
        </div>
        </form>
            	  <%
              }
              else if(option.equals("transfert"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
                <div class="creation">
                <%
                transfert=-1;
                do
                {
              	  transfert=transfert+1;
                }
                while(transfert<listecontenuautorisation.size()&&!listecontenuautorisation.get(transfert).getTable().equals("transfert"));
                if(transfert>=0)
                {
                	if(listecontenuautorisation.get(transfert).getCreer().equals("true"))
                	{
                		%>
                		     <a href="./login?option=transfert1"" class="creation1"><h5>Nouveau transfert</h5></a>
                		<%
                	}
                }
                %>
                </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Date</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Service emetteur</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Service recepteur</th>
                     <th class="hidden-phone"><i class="fa fa-question-circle"></i>Numero</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listerecaptransfert.size()>0)
                {
                	for(int i=0;i<listerecaptransfert.size();i++)
                	{
                		String eme=serviceservice.getByid(listerecaptransfert.get(i).getEmetteur()).getNom();
                		String recoit=serviceservice.getByid(listerecaptransfert.get(i).getRecepteur()).getNom();
                	%>
                	<tr>
                    <td><%out.print(listerecaptransfert.get(i).getDate()); %></td>
                    <td class="hidden-phone"><%out.print(eme); %></td>
                    <td class="hidden-phone"><%out.print(recoit); %></td>
                    <td class="hidden-phone"><%out.print(listerecaptransfert.get(i).getNumero()); %></td>
                    <td class="modif">
                    <%
                transfert=-1;
                do
                {
              	  transfert=transfert+1;
                }
                while(transfert<listecontenuautorisation.size()&&!listecontenuautorisation.get(transfert).getTable().equals("transfert"));
                if(transfert>=0)
                {
                	if(listecontenuautorisation.get(transfert).getModifier().equals("true"))
                	{
                		%>
                		<a href="./login?option=modifiertransfert&id=<%out.print(listerecaptransfert.get(i).getNumero()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                    <td class="modif">
                    <%
                transfert=-1;
                do
                {
              	  transfert=transfert+1;
                }
                while(transfert<listecontenuautorisation.size()&&!listecontenuautorisation.get(transfert).getTable().equals("transfert"));
                if(transfert>=0)
                {
                	if(listecontenuautorisation.get(transfert).getVisualiser().equals("true"))
                	{
                		%>
                		<a href="./login?option=visualisertransfert&id=<%out.print(listerecaptransfert.get(i).getNumero()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-desktop"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              else if(option.equals("visualisertransfert"))
              {
            	  %>
            	 <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i>${Titre}</h4>
               
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i>Article</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>Quantite</th>
                  </tr>
                </thead>
                <tbody>
                <%
                if(listetransfert.size()>0)
                {
                	for(int i=0;i<listetransfert.size();i++)
                	{
                		articles=articleservice.getByid(listetransfert.get(i).getArticle());
                	%>
                	<tr>
                    <td><%out.print(articles.getNom()); %></td>
                    <td class="hidden-phone"><%out.print(listetransfert.get(i).getQuantite()); %></td>
                    <td class="modif">
                    <%
                transfert=-1;
                do
                {
              	  transfert=transfert+1;
                }
                while(transfert<listecontenuautorisation.size()&&!listecontenuautorisation.get(transfert).getTable().equals("transfert"));
                if(transfert>=0)
                {
                	if(listecontenuautorisation.get(transfert).getModifier().equals("true"))
                	{
                		%>
                		<a href="./login?option=modifiertransfert1&id=<%out.print(listetransfert.get(i).getId()); %>" class="btn btn-primary btn-xs" ><i class="fa fa-pencil"></i></a>
                		<%
                	}
                }
                %>  
                    </td>
                  </tr>
                	<%	
                	}
                }
                %>
                </tbody>
              </table>

            	  <%
              }
              %>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
        <!-- /row --> 
            <!-- fin -->
          </div>
        </div>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
   
      </div>

  
<%@ include file="footer.jsp" %>