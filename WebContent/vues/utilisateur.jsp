<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title> ${Titre}</title>
<%
	String statututilisateur=(String)request.getAttribute("Titre");
List<setch.beans.autorisation>listeautorisation=(List<setch.beans.autorisation>)request.getAttribute("Listeautorisation");
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
%>
  <!-- Favicons -->
  <link href="http:./vues/Dashio/img/favicon.png" rel="icon">
  <link href="http:./vues/Dashio/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="http:./vues/Dashio/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="http:./vues/Dashio/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="http:./vues/Dashio/css/style.css" rel="stylesheet">
  <link href="http:./vues/Dashio/css/style-responsive.css" rel="stylesheet">
  
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
  <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
  <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading">${Titre}</h2>
        <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        %>
        <div class="login-wrap">
          <input type="text" name="Name" pattern="[A-Za-z0-9\ }]{1,50}" class="form-control" placeholder="Name" autofocus>
          <br>
          <input type="text" name="Login" pattern="[A-Za-z0-9\ }]{1,50}" class="form-control" placeholder="Login" autofocus>
          <br>
          <input type="password" name="Pwd" pattern="[A-Za-z0-9\ }]{6,20}" class="form-control" placeholder="Mot de passe" autofocus>
          <br>
           <select class="form-control autorisation" name="autorisations" required>
           <%
           for(int i=0;i<listeautorisation.size();i++)
           {
        	   %>
        	   <option value="<%out.print(listeautorisation.get(i).getId()); %>"><%out.print(listeautorisation.get(i).getNom()); %></option>
        	   <% 
           }
           %>
           </select>
          <br>
           <select class="form-control" name=Statut>
                  <option value="actif">Actif</option>
                  <%
                  if(!statututilisateur.equals("Administrateur"))
                  {
                  %>
                  <option value="nonactif">Non actif</option>
                  <%
                  }
                  %>
                </select>
          <br>
          
          
          
          <hr>
           <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Connexion</button>
        </div>
       
      </form>
     
    </div>

  </div>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="http:./vues/Dashio/lib/jquery/jquery.min.js"></script>
  <script src="http:./vues/Dashio/lib/bootstrap/js/bootstrap.min.js"></script>
  <!--BACKSTRETCH-->
  <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
  <script type="text/javascript" src="http:./vues/Dashio/lib/jquery.backstretch.min.js"></script>
  <script>
    $.backstretch("img/login-bg.jpg", {
      speed: 500
    });
  </script>
</body>

</html>
