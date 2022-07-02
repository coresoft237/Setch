<%@ include file="header.jsp" %>
<%@page import="setch.beans.famille"%>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");

famille familles=(famille)request.getAttribute("famille");


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
        	<input type="text" name="name" class="form-control" pattern="[^']{1,100}" placeholder="Name" value ="<%out.print(familles.getNom()); %>" autofocus required>
          <br>
           <select class="form-control" name=statut>
                  <option value="actif" <%if(familles.getStatut().equals("actif")){%>selected<%} %>>Actif</option>
                  <option value="nonactif"<%if(familles.getStatut().equals("nonactif")){%>selected<%} %>>Non actif</option>
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