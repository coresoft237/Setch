<%@ include file="header.jsp" %>
<%@page import="setch.beans.famille"%>
<%@page import="setch.beans.articles"%>
<%
Boolean executionoperation = (Boolean)request.getAttribute("executionoperation");
%>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
              <div class="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading">Article</h2>
         <%
        if(executionoperation==false)
        {
        	%>
        	<h3 style='color:red;'>Echec</h3>
        	<%
        }
         articles articles=(articles)request.getAttribute("article");
         List<famille> Listefamille=(List<famille>)request.getAttribute("listfam");
        %>
        <div class="login-wrap">
        <select class="form-control" placeholder="Famille" name=famille required>
        <%
        for(int i=0;i<Listefamille.size();i++)
        {
        	%>
        	<option value="<%out.print(Listefamille.get(i).getId());%>"<%if(Listefamille.get(i).getId()==articles.getIdfamille()){%>SELECTED<%} %>><%out.print(Listefamille.get(i).getNom());%></option>
        	<%
        }
        %>
          
        </select>
          <br>
          <input type="text" name="nom" pattern="[^']{1,100}" value="<%out.print(articles.getNom()); %>" class="form-control" placeholder="Nom commercial" autofocus required>
          <br>
           <select class="form-control" placeholder="article" name=statut>
          <option value="actif"<%if(articles.getStatut().equals("actif")){%>SELECTED<%} %>>Actif</option>
           <option value="nonactif" <%if(articles.getStatut().equals("nonactif")){%>SELECTED<%} %>>Non actif</option>
        </select>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Creation</button>
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