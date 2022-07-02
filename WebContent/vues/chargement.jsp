<%@page import="setch.beans.famille"%>
<%@ include file="header.jsp" %>

 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <div id="login-page">
    <div class="container">
      <form  class="form-login" method="POST" enctype="multipart/form-data">
        <h2 class="form-login-heading"> ${Titre}</h2>
        <div class="login-wrap">
        	<input type="file" name="fichier" class="form-control" required   autofocus >
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>chargement</button>
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