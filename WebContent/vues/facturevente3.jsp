<!DOCTYPE html>
<%@page import="setch.beans.reductionfacture"%>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.beans.facturevente"%>
<%@page import="setch.beans.utilisateur"%>
<%@page import="setch.beans.contenuautorisation"%>
 <%@ page import="java.util.List" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Core stock</title>

  <!-- Favicons -->
  <link href="http:./vues/Dashio/img/favicon.png" rel="icon">
  <link href="http:./vues/Dashio/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="http:./vues/Dashio/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="http:./vues/Dashio/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="http:./vues/Dashio/css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="http:./vues/Dashio/lib/gritter/css/jquery.gritter.css" />
  <!-- Custom styles for this template -->
  <link href="http:./vues/Dashio/css/style.css" rel="stylesheet">
  <link href="http:./vues/css/style1.css" rel="stylesheet">
  <link href="http:./vues/css/style2.css" rel="stylesheet" media="print">
  <link href="http:./vues/Dashio/css/style-responsive.css" rel="stylesheet">
  <script src="http:./vues/Dashio/lib/chart-master/Chart.js"></script>
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body onLoad="imprimer1();">
<%
utilisateur user=(utilisateur)request.getAttribute("utilisateur");
List<setch.beans.contenuautorisation> listecontenuautorisation=(List<contenuautorisation>)request.getAttribute("listecontenuautorisation");
%>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="http:./vues/Dashio/index.html" class="logo"><b>CO<span>RE</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
          <!-- settings start -->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-tasks"></i>
              <span class="badge bg-theme">4</span>
              </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">You have 4 pending tasks</p>
              </li>
              <li>
                <a href="index.html#">
                  <div class="task-info">
                    <div class="desc">Dashio Admin Panel</div>
                    <div class="percent">40%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                      <span class="sr-only">40% Complete (success)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <div class="task-info">
                    <div class="desc">Database Update</div>
                    <div class="percent">60%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                      <span class="sr-only">60% Complete (warning)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <div class="task-info">
                    <div class="desc">Product Development</div>
                    <div class="percent">80%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                      <span class="sr-only">80% Complete</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <div class="task-info">
                    <div class="desc">Payments Sent</div>
                    <div class="percent">70%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                      <span class="sr-only">70% Complete (Important)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li class="external">
                <a href="#">See All Tasks</a>
              </li>
            </ul>
          </li>
          <!-- settings end -->
          <!-- inbox dropdown start-->
          <li id="header_inbox_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="http:./vues/Dashio/index.html#">
              <i class="fa fa-envelope-o"></i>
              <span class="badge bg-theme">5</span>
              </a>
            <ul class="dropdown-menu extended inbox">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">You have 5 new messages</p>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="photo"><img alt="avatar" src="http:./vues/Dashio/img/ui-zac.jpg"></span>
                  <span class="subject">
                  <span class="from">Zac Snider</span>
                  <span class="time">Just now</span>
                  </span>
                  <span class="message">
                  Hi mate, how is everything?
                  </span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="photo"><img alt="avatar" src="http:./vues/Dashio/img/ui-divya.jpg"></span>
                  <span class="subject">
                  <span class="from">Divya Manian</span>
                  <span class="time">40 mins.</span>
                  </span>
                  <span class="message">
                  Hi, I need your help with this.
                  </span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="photo"><img alt="avatar" src="http:./vues/Dashio/img/ui-danro.jpg"></span>
                  <span class="subject">
                  <span class="from">Dan Rogers</span>
                  <span class="time">2 hrs.</span>
                  </span>
                  <span class="message">
                  Love your new Dashboard.
                  </span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="photo"><img alt="avatar" src="http:./vues/Dashio/img/ui-sherman.jpg"></span>
                  <span class="subject">
                  <span class="from">Dj Sherman</span>
                  <span class="time">4 hrs.</span>
                  </span>
                  <span class="message">
                  Please, answer asap.
                  </span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">See all messages</a>
              </li>
            </ul>
          </li>
          <!-- inbox dropdown end -->
          <!-- notification dropdown start-->
          <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="http:./vues/Dashio/index.html#">
              <i class="fa fa-bell-o"></i>
              <span class="badge bg-warning">7</span>
              </a>
            <ul class="dropdown-menu extended notification">
              <div class="notify-arrow notify-arrow-yellow"></div>
              <li>
                <p class="yellow">You have 7 new notifications</p>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  Server Overloaded.
                  <span class="small italic">4 mins.</span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="label label-warning"><i class="fa fa-bell"></i></span>
                  Memory #2 Not Responding.
                  <span class="small italic">30 mins.</span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  Disk Space Reached 85%.
                  <span class="small italic">2 hrs.</span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">
                  <span class="label label-success"><i class="fa fa-plus"></i></span>
                  New User Registered.
                  <span class="small italic">3 hrs.</span>
                  </a>
              </li>
              <li>
                <a href="http:./vues/Dashio/index.html#">See all notifications</a>
              </li>
            </ul>
          </li>
          <!-- notification dropdown end -->
        </ul>
        <!--  notification end -->
         <div class="fleche">
         <a href=javascript:history.go(-1) style='color:#4ECDC4;'>retour</a>
         <a href=javascript:history.go(1) style='color:#4ECDC4;'>suivant</a>
         </div>
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li><a class="logout" href="http:./login?option=logout">Logout</a></li>
        </ul>
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
          <p class="centered"><a href="http:./vues/Dashio/profile.html"><img src="http:./vues/Dashio/img/ui-sam.jpg" class="img-circle" width="80"></a></p>
          <h5 class="centered"></h5>
          <li class="mt">
            <a href="http:./login?option=dashboard">
              <i class="fa fa-dashboard"></i>
              <span>Dashboard</span>
              </a>
          </li>
          <li class="sub-menu">
            <a class="active" href="#">
             <i class="fa fa-cogs"></i>
              <span>Strutures</span>
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
            		<li><a href="http:./login?option=compte">Comptes</a></li>
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
            		 <li><a href="http:./login?option=entreprise">Infos entreprise</a></li>
            		<%
            	}
            }
            //--------validite
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=validite">Validite</a></li>
            	  <% 
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
              		   <li class="active"><a href="http:./login?option=autorisation">Autorisations</a></li>
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
              		    <li><a href="http:./login?option=famillearticles">Familles articles</a></li>
              		<%
              	}
              }
            //--------chargement famille
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=chargementfamille">Chargement famille</a></li>
            	  <% 
              }
              //--------chargement commission
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=chargementcommission">Chargement commission</a></li>
            	  <% 
              }
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
              		     <li><a href="http:./login?option=pourcentagecommission">Pourcentages Commissions</a></li>
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
              		     <li><a href="http:./login?option=articles">Articles</a></li>
              		<%
              	}
              }
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=chargementarticle">Chargement article</a></li>
            	  <% 
              }
              //chargement personne
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=chargementprescripteur">Chargement prescripteur</a></li>
            	  <% 
              }
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
              		      <li><a href="http:./login?option=personnes">personnes</a></li>
              		<%
              	}
              }
              if(user.getAutorisation()==1)
              {
            	  %>
            	   <li class="active"><a href="http:./login?option=chargementprix">Chargement prix</a></li>
            	  <% 
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
              		     <li><a href="http:./login?option=prixvente">Gestion prix vente</a></li>
              		<%
              	}
              }
              %>
            </ul>
          </li>
          <li class="sub-menu">
            <a href="http:./vues/header.jspjavascript:;">
              <i class="fa fa-th"></i>
              <span>Traitement</span>
              </a>
            <ul class="sub">
              
              
               <li class="sub-menu">
            <a href="http:./vues/header.jspjavascript:;">
              <i class="fa fa-th"></i>
              <span>Facturation</span>
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
            		 <li><a href="http:./login?option=vente">Ventes normales</a></li>
              		 <li><a href="http:./login?option=ventespecifique">Ventes specifiques</a></li>
            		<%
            	}
            }
            %>
            </ul>
          </li>
              
             
            </ul>
          </li>
          <li class="sub-menu">
            <a href="http:./vues/header.jspjavascript:;">
               <i class="fa fa-book"></i>
              <span>Etats</span>
              </a>
            <ul class="sub">
            <%
            //----etat vente
            int etatvente=-1;
            do
            {
            	etatvente=etatvente+1;
            }
            while(etatvente<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatvente).getTable().equals("etatvente"));
            if(etatvente>=0)
            {
            	if(listecontenuautorisation.get(etatvente).getVisualiser().equals("true"))
            	{
            		%>
            		<li><a href="http:./login?option=etatvente">Ventes</a></li>
            		<%
            	}
            }
          //----etat commissions
            int etatcommission=-1;
            do
            {
            	etatcommission=etatcommission+1;
            }
            while(etatcommission<listecontenuautorisation.size()&&!listecontenuautorisation.get(etatcommission).getTable().equals("etatcommissions"));
            if(etatcommission>=0)
            {
            	if(listecontenuautorisation.get(etatcommission).getVisualiser().equals("true"))
            	{
            		%>
            		 <li><a href="http:./login?option=etatcommission">Commissions</a></li>
            		<%
            	}
            }
            %>
            </ul>
            
          </li>
         
      </div>
    </aside>

    <section id="main-content">
      <section class="wrapper">
        <div class="col-lg-12 mt">
          <div class="row content-panel col1">
            <div class="col-lg-10 col-lg-offset-1">
              <div class="invoice-body invoice-body1">
                <div class="pull-left">
                <%
                facturevente facture=(facturevente)request.getAttribute("listevente");
                personneservice personneservice=new personneservice();
                articleservice articleservice=new articleservice();
                prixventeservice prixventeservice=new prixventeservice();
                reductionfacture reduction=(reductionfacture)request.getAttribute("reductionretour");
                reductionfacture reduction1=(reductionfacture)request.getAttribute("reductionnouveau");
                System.out.println("reduction"+reduction.getReduction());
                
                double total1=0;
                %>
                  <h1><%out.print(facture.getEntreprise().getName()); %></h1>
                  <address>
                <strong><%out.print( facture.getEntreprise().getFormejuridique()); %></strong><br>
                <%out.print("NIU"+ facture.getEntreprise().getNiu()); %>,<%out.print("BP:"+facture.getEntreprise().getBp()); %><br>
                ,<%out.print(facture.getEntreprise().getWeb()); %><br>
                <abbr title="Phone"></abbr>  <%out.print("TEL:(237)"+facture.getEntreprise().getTelephone()); %>
              </address>
                </div>
                <!-- /pull-left -->
                <div class="pull-right">
                  <h2>RECU</h2>
                </div>
                <!-- /pull-right -->
                <div class="clearfix"></div>
                <br>
                <br>
                <br>
                <div class="row row1">
                  <div class="col-md-9">
                  <h4>Patient</h4>
                   <h5><%out.print(personneservice.getByid(facture.getListevente().get(0).getIdpatient()).getNom()); %></h5>
                  </div>
                  <!-- /col-md-9 -->
                  <div class="col-md-3">
                    <br>
                    <div class="row2">
                      <div class="pull-left"> Facture NO : </div>
                      <div class="pull-right "> <%out.print(facture.getListevente().get(0).getFacture()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <div class="row2">
                      <!-- /col-md-3 -->
                      <div class="pull-left"> Date facture : </div>
                      <div class="pull-right"><%out.print(facture.getListevente().get(0).getDate()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <div class="row2">
                      <!-- /col-md-3 -->
                      <div class="pull-left"> Prescripteur : </div>
                      <div class="pull-right"><%out.print(personneservice.getByid(facture.getListevente().get(0).getIdprescripteur()).getId()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <!-- /row -->
                    <br>
                    
                  </div>
                  <!-- /invoice-body -->
                </div>
                <!-- /col-lg-10 -->
                <table class="table">
                  <thead>
                    <tr>
                      <th style="width:60px" class="text-center">QTE</th>
                      <th class="text-left">SOINS</th>
                      <th style="width:140px" class="text-right">PRIX UNIT</th>
                      <th style="width:90px" class="text-right">TOTAL</th>
                    </tr>
                  </thead>
                  <tbody>
                  <%
                  for(int i=0;i<facture.getListevente().size();i++)
                  {
                	  double total=0;
                	  if(facture.getListevente().get(i).getQuantite()<0)
                      {
                		   total=(facture.getListevente().get(i).getQuantite()*prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction.getReduction()))/100;
                      }
                	  else
                	  {
                		   total=(facture.getListevente().get(i).getQuantite()*prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction1.getReduction()))/100;
                	  }
                	  %>
                	  <tr>
                      <td class="text-center"><%out.print(facture.getListevente().get(i).getQuantite()); %></td>
                      <td><%out.print(articleservice.getByid(facture.getListevente().get(i).getIdarticle()).getNom()); %></td>
                      <%
                      if(facture.getListevente().get(i).getQuantite()<0)
                      {
                    	  %>
                    	   <td class="text-right">F.CFA<%out.print((prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction.getReduction()))/100); %></td>
                    	  <% 
                      }
                      else
                      {
                    	  %>
                    	  <td class="text-right">F.CFA<%out.print((prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction1.getReduction()))/100); %></td>
                    	  <%   
                      }
                      %>
                     
                      <td class="text-right">F.CFA<%out.print(total); %></td>
                    </tr>
                    <% 
                    total1=total1+total;
                  }
                  
                  %>
                    
                    <tr>
                      <td colspan="2" rowspan="4">
                        <h4>Conditions de vente</h4>
                        <p>Les soins achetes ne peuvent etre remboursés .Ce recu doit etre conservé aussi longtemps que possible.</p>
                        <td class="text-right"><strong>Total HT</strong></td>
                        <td class="text-right">F.CFA<%out.print(total1); %></td>
                    </tr>
                    <tr>
                      <td class="text-right no-border"><strong>TVA(19.25%)</strong></td>
                      <td class="text-right">F.CFA0.00</td>
                    </tr>
                    <tr>
                      <td class="text-right no-border">
                        <div class="well well-small green"><strong>Total</strong></div>
                      </td>
                      <td class="text-right"><strong>F.CFA<%out.print(total1); %></strong></td>
                    </tr>
                  </tbody>
                </table>
                <br>
                <br>
              </div>
              <!--/col-lg-12 mt -->
      </section>
      <!-- /wrapper -->
    </section>
<%@ include file="footer.jsp" %>