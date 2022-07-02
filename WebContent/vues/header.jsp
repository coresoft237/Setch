<!DOCTYPE html>
<%@page import="setch.beans.personne"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.beans.famille"%>
<%@page import="setch.beans.utilisateur"%>
<%@page import="setch.beans.facturation"%>
<%@page import="setch.beans.contenuautorisation"%>
<%@page import="setch.service.facturationservice"%>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="java.util.List" %>
 
<html lang="en">
<%
setch.service.facturationservice facturationservice=new setch.service.facturationservice();
setch.beans.facturation facturations=null;
List<setch.beans.facturation> listefacturation = new ArrayList<setch.beans.facturation>();
%>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Core stock</title>

  <!-- Favicons -->
  <link href="./vues/Dashio/img/favicon.png" rel="icon">
  <link href="./vues/Dashio/img/Logo.jpg" rel="apple-touch-icon">
	<!-- date -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css"/>
	<link rel="stylesheet" href="/resources/demos/style.css"/>
  <!-- Bootstrap core CSS -->
  <link href="./vues/Dashio/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="./vues/Dashio/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/gritter/css/jquery.gritter.css" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/bootstrap-fileupload/bootstrap-fileupload.css" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/bootstrap-datepicker/css/datepicker.css" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/bootstrap-daterangepicker/daterangepicker.css" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/bootstrap-timepicker/compiled/timepicker.css" />
  <link rel="stylesheet" type="text/css" href="./vues/Dashio/lib/bootstrap-datetimepicker/datertimepicker.css" />
  <!-- Custom styles for this template -->
  <link href="./vues/Dashio/css/style.css" rel="stylesheet">
  <link href="./vues/css/style1.css" rel="stylesheet">
  <link href="./vues/css/style.css" rel="stylesheet">
  <link href="./vues/css/style-responsive.css" rel="stylesheet">
  <link href="./vues/css/style2.css" rel="stylesheet" media="print">
  <link href="./vues/Dashio/css/style-responsive.css" rel="stylesheet">
  <script src="./vues/Dashio/lib/chart-master/Chart.js"></script>

  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>
<%
String etat=(String)request.getAttribute("print");

%>
<body>
<%
utilisateur user=(utilisateur)request.getAttribute("utilisateur");
List<setch.beans.contenuautorisation> listecontenuautorisation=(List<contenuautorisation>)request.getAttribute("listecontenuautorisation");
List<famille> listefam=(List<famille>)request.getAttribute("listfam");
%>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      
      <!--logo start-->
      <a href="#" class="logo"><b>CO<span>RE</span></b></a>
      <!--logo end-->
      <div class="grand">
      
      <%
      personneservice persservice=new personneservice();
      List<personne> listepers=persservice.getAll1();
      if(listepers.size()>0)
      {
    	  %>
    	   <div class="infos"><marquee>Vous avez des patients sans numeros de telephone.Veuillez ouvrir la liste des personnes pour corriger</marquee></div>
    	  <%
      }
      %>
      
      <div class="top-menu">
      
        <ul class="nav pull-right top-menu ">
          <li><a class="logout" href="./logout">Logout</a></li>
        </ul>
      </div>
      </div>
    </header>
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          
          <h5 class="centered"></h5>
          <li class="mt">
            <a href="./login?option=connexion">
              <i class="fa fa-dashboard"></i>
              <span>Dashboard</span>
              </a>
          </li>
          <li class="sub-menu">
          <!-- mettre class="active" dans la balise a suivante -->
            <a  href="#">
             <i class="fa fa-cogs"></i>
              <span>Parametrages</span>
              </a>
            <ul class="sub">
            <%
            //----compte
            int compte=-1;
            do
            {
            	compte=compte+1;
            }
            while(compte<listecontenuautorisation.size()&&!listecontenuautorisation.get(compte).getTable().equals("utilisateur"));
            if(compte>=0)
            {
            	if(listecontenuautorisation.get(compte).getVisualiser().equals("true"))
            	{
            		%>
            		<li><a href="./User?option=UserList">Utilisateurs</a></li>
            		<%
            	}
            }
          //-----entreprise
            int entreprise=-1;
            do
            {
            	entreprise=entreprise+1;
            }
            while(entreprise<listecontenuautorisation.size()&&!listecontenuautorisation.get(entreprise).getTable().equals("entreprise"));
            if(entreprise>=0)
            {
            	if(listecontenuautorisation.get(entreprise).getVisualiser().equals("true"))
            	{
            		%>
            		 <li><a href="./Entreprise?option=entreprise">Infos entreprise</a></li>
            		<%
            	}
            }
            //-----	autorisation
            int autorisation=-1;
            do
            {
          	  autorisation=autorisation+1;
            }
            while(autorisation<listecontenuautorisation.size()&&!listecontenuautorisation.get(autorisation).getTable().equals("autorisation"));
            if(autorisation>=0)
            {
            	if(listecontenuautorisation.get(autorisation).getVisualiser().equals("true"))
            	{
            		%>
            		   <li><a href="./Autorisations?option=ListAutorisations">Autorisations</a></li>
            		<%
            	}
            } 
          //----services
            int service=-1;
            do
            {
            	service=service+1;
            }
            while(service<listecontenuautorisation.size()&&!listecontenuautorisation.get(service).getTable().equals("service"));
            if(service>=0)
            {
            	if(listecontenuautorisation.get(service).getVisualiser().equals("true"))
            	{
            		%>
            		<li><a href="./login?option=service">Services</a></li>
            		<%
            	}
            }
           
           
          //-----facturation
            int facturation=-1;
            do
            {
            	facturation=facturation+1;
            }
            while(facturation<listecontenuautorisation.size()&&!listecontenuautorisation.get(facturation).getTable().equals("facturation"));
            if(facturation>=0)
            {
            	if(listecontenuautorisation.get(facturation).getVisualiser().equals("true"))
            	{
            		%>
            		 <li><a href="./TypeFacturation?option=ListTypeFacturation">Type facturation</a></li>
            		<%
            	}
            }
            //--------validite
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li ><a href="./Validite?option=ValiditeList">Validite</a></li>
            	  <% 
              }
              
              %>
            </ul>
          </li>
          <!-- Menu 2 -->
                    <li class="sub-menu">
          <!-- mettre class="active" dans la balise a suivante -->
            <a  href="#">
             <i class="fa fa-cogs"></i>
              <span>Extras</span>
              </a>
            <ul class="sub">
            <%
           
          
            //--------chargement famille
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li><a href="./login?option=chargementfamille">Chargement familles Services</a></li>
            	  <% 
              }
              //--------chargement commission
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li ><a href="./login?option=chargementcommission">Chargement commission</a></li>
            	  <% 
              }
           
              //chargement personne
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li ><a href="./login?option=chargementprescripteur">Chargement prescripteur</a></li>
            	  <% 
              }
            
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li><a href="./login?option=chargementprix">Chargement prix vente </a></li>
            	  <% 
              }
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li><a href="./login?option=chargementoffre">Chargement offre </a></li>
            	  <% 
              }
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li ><a href="./login?option=chargementarticle">Chargement Articles</a></li>
            	  <% 
              }
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li ><a href="./login?option=chargementunite">Chargement unite</a></li>
            	  <% 
              }
              %>
            </ul>
          </li>
          <!-- Fin menu 2 -->
          <!-- gestion stock -->
                    <li class="sub-menu">
          <!-- mettre class="active" dans la balise a suivante -->
            <a  href="#">
             <i class="fa fa-cogs"></i>
              <span>Gestion de stock</span>
              </a>
            <ul class="sub">
            <%
          //-----personne
            int personne=-1;
            do
            {
          	  personne=personne+1;
            }
            while(personne<listecontenuautorisation.size()&&!listecontenuautorisation.get(personne).getTable().equals("personne"));
            if(personne>=0)
            {
            	if(listecontenuautorisation.get(personne).getVisualiser().equals("true"))
            	{
            		%>
            		      <li><a href="./Personnes?option=ListPersonnes">personnes</a></li>
            		<%
            	}
            }
              //-----familles
              int famille=-1;
              do
              {
            	  famille=famille+1;
              }
              while(famille<listecontenuautorisation.size()&&!listecontenuautorisation.get(famille).getTable().equals("famille"));
              if(famille>=0)
              {
              	if(listecontenuautorisation.get(famille).getVisualiser().equals("true"))
              	{
              		%>
              		    <li><a href="./Familles?option=ListFamilles">Familles articles</a></li>
              		<%
              	}
              }
          
            //-----article
              int article=-1;
              do
              {
            	  article=article+1;
              }
              while(article<listecontenuautorisation.size()&&!listecontenuautorisation.get(article).getTable().equals("article"));
              if(article>=0)
              {
              	if(listecontenuautorisation.get(article).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="./Articles?option=ListArticles">Articles</a></li>
              		<%
              	}
              }
            //-----offre
              int offre=-1;
              do
              {
            	  offre=offre+1;
              }
              while(offre<listecontenuautorisation.size()&&!listecontenuautorisation.get(offre).getTable().equals("offre"));
              if(offre>=0)
              {
            	  %>
            	  
            	  <% 
              	if(listecontenuautorisation.get(offre).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="./OffrePrixAchat?option=ListOffre">Gestion offres Achat</a></li>
              		<%
              	}
              }
            //-----inventaire
              int inventaire=-1;
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
              		    
              		     <li><a href="./inventaire?option=ListeInventaire">Inventaires</a></li>
              		
              		<%
              	}
              }
              //-----besoin
              int besoin=-1;
              do
              {
            	  besoin=besoin+1;
              }
              while(besoin<listecontenuautorisation.size()&&!listecontenuautorisation.get(besoin).getTable().equals("besoin"));
              if(besoin>=0)
              {
              	if(listecontenuautorisation.get(besoin).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="#">Gestion besoins</a>
		          			<ul class="sub">
		            		 <li><a href="./Besoin?option=ListeBesoin">Besoins</a></li>
		              		 <!-- <li><a href="./login?option=ventespecifique">Rapport besoins</a></li>  -->    
		            		</ul>
              		     </li>
              		<%
              	}
              }
            //-----commande
              int commande=-1;
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
              		     <!--<li><a href="#">Gestion commandes</a>
		          			<ul class="sub">
		          			<li><a href="./login?option=commande">Liste des commandes</a></li>
		            		 <li><a href="./login?option=creationcommande1">Generer commande</a></li>
		              		 <li><a href="./login?option=ventespecifique">Rapport besoins</a></li>    
		            		</ul>
              		     </li>-->
              		<%
              	}
              }
            //-----prixvente
              int prix=-1;
              do
              {
            	  prix=prix+1;
              }
              while(prix<listecontenuautorisation.size()&&!listecontenuautorisation.get(prix).getTable().equals("prixvente"));
              if(prix>=0)
              {
              	if(listecontenuautorisation.get(prix).getVisualiser().equals("true"))
              	{
              		%>
              		      <li><a href="./PrixVente?option=ListPrixVente">Prix vente</a></li>
              		<%
              	}
              }
            //-----unitevente
              int unite=-1;
              do
              {
            	  unite=unite+1;
              }
              while(unite<listecontenuautorisation.size()&&!listecontenuautorisation.get(unite).getTable().equals("unitevente"));
              if(unite>=0)
              {
              	if(listecontenuautorisation.get(unite).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="./UniteVente?option=ListUniteVente">Unite vente</a></li>
              		<%
              	}
              }
            //-----contenuachat
              int contenuachat=-1;
              do
              {
            	  contenuachat=contenuachat+1;
              }
              while(contenuachat<listecontenuautorisation.size()&&!listecontenuautorisation.get(contenuachat).getTable().equals("contenuachat"));
              if(contenuachat>=0)
              {
              	if(listecontenuautorisation.get(contenuachat).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="./ContenuAchat?option=ListContenuAchat">Contenu Achat</a></li>
              		<%
              	}
              }
            //-----livraison
              		%>
              		
              		      <!--  <li><a href="#">Gestion livraison</a>-->
		          			<ul class="sub">
		          			<%
		          			int livraison=-1;
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
		                    		<!-- <li><a href="./login?option=livraison">Livraison achat</a></li> -->
		                    		<%
		                    	}
		                    }
		                    int livraisonvente=-1;
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
		                    		<!-- <li><a href="./login?option=livraisonvente">Livraison vente</a></li> -->
		                    		<%
		                    	}
		                    }
		          			%>		
		            		</ul>
              		     </li>
              <% 
            //-----Transfert
              int transfert=-1;
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
              		     <li><a href="./login?option=transfert">Gestion des transferts</a> </li>
              		<%
              	}
              }
              %>
               
            </ul>
          </li>
          <!-- fin gestion des stocks -->
          <li class="sub-menu">
            <a href="#" class="active">
              <i class="fa fa-th"></i>
              <span>Traitement</span>
              </a>
            <ul class="sub">
              
              
               <li class="sub-menu">
            <a href="./vues/header.jspjavascript:;">
              <i class="fa fa-shopping-cart"></i>
              <span>Ventes</span>
              </a>
            <ul class="sub">
              <%
            //----vente
            int vente=-1;
            do
            {
            	vente=vente+1;
            }
            while(vente<listecontenuautorisation.size()&&!listecontenuautorisation.get(vente).getTable().equals("vente"));
            if(vente>=0)
            {
            	if(listecontenuautorisation.get(vente).getVisualiser().equals("true"))
            	{
            		%>
            		 <li><a href="./VenteNormale?option=ListVenteNormale">Ventes normales</a></li>
            		 
              		 <!--<li><a href="./login?option=ventespecifique">Ventes specifiques</a></li>  -->
              		 <li><a href="./login?option=ventecredit">Ventes à credit</a></li>
              		 <!--  -->
              		 
              		 <li><a href="./vues/header.jspjavascript:;">
             		 <!--  <i class="fa fa-th"></i>-->
              		<span>Retours</span>
             		 </a>
              		  <ul class="sub">
              		  <li><a href="./login?option=retourventenormale"> normaux</a></li>
              		<!--   <li><a href="./login?option=retourventespecifique"> specifiques</a></li>-->
              		  </ul></li>
              		 <!--  -->
            		<%
            	}
            }
            %>
            </ul>
          </li>
              
             
            </ul>
          </li>
          <!-- Menu personnes et commissions -->
          <li class="sub-menu">
          <!-- mettre class="active" dans la balise a suivante -->
            <a  href="#">
             <i class="fa fa-address-book-o"></i>
              <span>Personnes et commissions</span>
              </a>
            <ul class="sub">
            <%
           
            
          
            //-----commissions
            
              int commissions=-1;
              do
              {
            	  commissions=commissions+1;
              }
              while(commissions<listecontenuautorisation.size()&&!listecontenuautorisation.get(commissions).getTable().equals("commissions"));
              if(commissions>=0)
              {
              	if(listecontenuautorisation.get(commissions).getVisualiser().equals("true"))
              	{
              		%>
              		     <li><a href="./login?option=pourcentagecommission">Pourcentages Commissions</a></li>
              		<%
              	}
              }
              %>
            </ul>
          </li>
          <!-- Fin menu personnes et commissions -->
          <li class="sub-menu">
            <a href="./vues/header.jspjavascript:;">
               <i class="fa fa-book"></i>
              <span>Etats</span>
              </a>
            <ul class="sub">
            		<li class="sub-menu">
            		<a href="./vues/header.jspjavascript:;">
               		<i class="fa fa-book"></i>
             		 <span>Ventes</span>
             		 </a>
           			 <ul class="sub">
           			 <%
           		//----------etat journalier
                     int etatjournalier=-1;
           do
           {
           	etatjournalier=etatjournalier+1;
           }
           while(etatjournalier<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatjournalier).getTable().equals("etatjournalier"));
           if(etatjournalier>=0)
           {
           	if(listecontenuautorisation.get(etatjournalier).getVisualiser().equals("true"))
           	{
           		%>
           		 <!--<li><a href="./login?option=etatjournalier1">Ventes journalieres</a></li>  -->
           		<%
           	}
           }
           		//----etat vente
                     int etatvente=-1;
                     do
                     {
                     	etatvente=etatvente+1;
                     }
                     while(etatvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatvente).getTable().equals("etatcommissions"));
                     if(etatvente>=0)
                     {
                     	if(listecontenuautorisation.get(etatvente).getVisualiser().equals("true"))
                     	{
                     	%>
                     	<li><a href="./login?option=etatvente">Etat commissions</a></li>
                     	
                     	<%
                     	
                     	}
                     }
                     for(int i=0;i<listefam.size();i++)
                     {
                    	 int a=-1;
                         do
                         {
                         	a=a+1;
                         	
                         }
                         while(a<listecontenuautorisation.size()&&!listecontenuautorisation.get(a).getTable().equals("etatvente"+listefam.get(i).getNom()));
                         if(a>=0)
                         {
                         	if(listecontenuautorisation.get(a).getVisualiser().equals("true"))
                         	{
                         	%>
                         	<!--<li><a href="./login?option=etatvente1&type=<%out.print(listefam.get(i).getId()); %>"><%out.print(listefam.get(i).getNom()); %></a></li>  -->
                         	
                         	<%
                         	
                         	}
                         } 
                     }
                     //----------etat marge
                      int etatmarge=-1;
            do
            {
            	etatmarge=etatmarge+1;
            }
            while(etatmarge<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatmarge).getTable().equals("etatmarge"));
            if(etatmarge>=0)
            {
            	if(listecontenuautorisation.get(etatmarge).getVisualiser().equals("true"))
            	{
            		%>
            		 <li><a href="./login?option=etatmarge">Marges ventes</a></li>
            		<%
            	}
            }
           			 %>
           			 </ul>
            		</li>
            		<li class="sub-menu">
            		<a href="./vues/header.jspjavascript:;">
               		<i class="fa fa-book"></i>
             		 <span>Stock</span>
             		 </a>
           			 <ul class="sub">
           			 <%
           		//----------etat stock
                     int etatstock=-1;
           do
           {
           	etatstock=etatstock+1;
           }
           while(etatstock<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatstock).getTable().equals("etatstock"));
           if(etatstock>=0)
           {
           	if(listecontenuautorisation.get(etatstock).getVisualiser().equals("true"))
           	{
           		%>
           		 <li><a href="./login?option=etatstock">Etat stock global</a></li>
           		<%
           	}
           }
         //----------etat stock
           int etatstock1=-1;
 do
 {
 	etatstock1=etatstock+1;
 }
 while(etatstock1<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatstock1).getTable().equals("etatstock1"));
 if(etatstock1>=0)
 {
 	if(listecontenuautorisation.get(etatstock1).getVisualiser().equals("true"))
 	{
 		%>
 		<!--<li><a href="./login?option=etatmarge">Etat stock d un article</a></li>  --> 
 		<%
 	}
 }
           		//---correctionstock
                     int correctionstock=-1;
                     do
                     {
                     	correctionstock=correctionstock+1;
                     }
                     while(correctionstock<listecontenuautorisation.size()&&!listecontenuautorisation.get(correctionstock).getTable().equals("correctionstock"));
                     if(correctionstock>=0)
                     {
                     	if(listecontenuautorisation.get(correctionstock).getVisualiser().equals("true"))
                     	{
                     	%>
                     	<li><a href="./login?option=listecorrectionstock">Correction stock</a></li>
                     	
                     	<%
                     	
                     	}
                     }
           			 %>
           			 </ul>
           			 
           			 
            		</li>
            	
            </ul>
            
          </li>
          <li><a href="./User?option=UpdateUserPwd">Modifier mot de passe</a></li>
        
      </div>
       
    </aside>