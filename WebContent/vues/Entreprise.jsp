<!DOCTYPE html>
<%@page import="setch.service.entrepriseservice"%>
<%
entrepriseservice entrepriseservice=new entrepriseservice();
if(entrepriseservice.getAll().size()>0)
{
	%>
	<%@ include file="header.jsp" %>
	<%
}
%>
<%@page import="setch.beans.entreprise"%>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Create Business</title>
<%
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
entreprise entreprise = (entreprise)request.getAttribute("entreprise");
%>
  <!-- Favicons -->
  <link href="http://localhost:8011setch/vues/Dashio/img/favicon.png" rel="icon">
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
  <div id="login-page entreprise">
    <div class="container">
      <form class="form-login entreprise" method="POST" >
        <h2 class="form-login-heading">Business</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        %>
        <div class="login-wrap">
        <input type="text" name="sigle" pattern="[^']{1,10}" class="form-control" placeholder="Sigle" value="<%out.print(entreprise.getSigle()); %>" autofocus required>
          <br>
          <input type="text" name="name" pattern="[^']{1,100}" class="form-control" placeholder="Name" value="<%out.print(entreprise.getName()); %>" autofocus required>
          <br>
          <input type="text" name="formejuridique" pattern="[A-Za-z]{1,10}" class="form-control" placeholder="Forme juridique" value="<%out.print(entreprise.getFormejuridique()); %>" autofocus required>
          <br>
          <input type="text" name="activite" pattern="[^']{1,100}" class="form-control" placeholder="Activity" value="<%out.print(entreprise.getActivite()); %>"  autofocus required>
          <br>
          <input type="text" name="niu" class="form-control" pattern="[A-Za-z0-9\ }]{0,100}" placeholder="NIU" value="<%out.print(entreprise.getNiu()); %>" autofocus>
          <br>
          <input type="text" name="bp" class="form-control" placeholder="BP" value="<%out.print(entreprise.getBp()); %>" autofocus>
          <br>
          <input type="text" name="telephone" class="form-control" pattern="[0-9]{9}" placeholder="Telephone" value="<%out.print(entreprise.getTelephone()); %>" autofocus required>
          <br>
          <input type="text" name="siteweb" class="form-control" placeholder="Site web" value="<%out.print(entreprise.getWeb()); %>" autofocus>
          <br>
          
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Creation</button>
          <hr>
          
         
        </div>
        
      </form>
    </div>
  </div>
  <%

if(entrepriseservice.getAll().size()>0)
{
	%>
	<%@ include file="footer.jsp" %>
	<%
}
else
{
%>
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

<%	
}
%>
 