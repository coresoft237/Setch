<%@ include file="header.jsp" %>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ page import="java.util.List" %>
<%
List<famille>listefamille=null;
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
String option=(String)request.getAttribute("option");
  listefamille=(List<famille>)request.getAttribute("Listefamille");
 
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading"> ${Titre}</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
        %>
        <div class="login-wrap">
           <br>
           <select class="form-control" placeholder="famille" name=famille id=famillecommande required >
		        <%
		        for(int i=0;i<listefamille.size();i++)
		        {
		        	%>
		        	<option value="<%out.print(listefamille.get(i).getId());%>"><%out.print(listefamille.get(i).getNom());%></option>
		        	<%
		        }
		        %>
		          
		        </select>
        <br>
          <button class="btn btn-theme btn-block" type="submit" ><i class="fa fa-lock"></i> Valider</button>
          <hr>
          
         
        </div>
        
      </form>
    </div>
  </div>
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