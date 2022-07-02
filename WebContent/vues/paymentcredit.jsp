<%@ include file="header.jsp" %>
<%@page import="setch.beans.paymentcredit"%>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");

paymentcredit paymentcredit=(paymentcredit)request.getAttribute("payement");
Double total=(Double)request.getAttribute("total");

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
          		<input type="text" id="idfacturecredit" name="idfacturecredit" value ="<%out.print(paymentcredit.getId()); %>" class="form-control"  autofocus readonly>
        	<br>
          		<input type="text" id="numerofacturecredit" name="numerofacturecredit" value ="<%out.print(paymentcredit.getFacture()); %>" class="form-control"  autofocus readonly>
          		<br>
              <input type="text" id="montantfacture" name="montantfacture"  class="form-control" value ="<%out.print(total); %>"  autofocus readonly>
          		<br>
          		
          		<input type="number" id="versement" name="versement" value="<%out.print(paymentcredit.getMontant()); %>" class="form-control"  autofocus required>
          		<br>
          		
              </div>
              <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Reglement</button>
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