<%@ include file="header.jsp" %>

 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
             <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading"> ${Titre}</h2>
        <div class="login-wrap">
        	<input type="date" name="dateinitiale" id="dateinitialestock" class="form-control" placeholder="Date initiale" value ="" autofocus>
          <br>
          <input type="date" name="datefinale" id="datefinalestock" class="form-control" placeholder="Date finale" value ="" autofocus>
          <br>
          <button class="btn btn-theme btn-block" type="button"  OncliCk="marge1()"><i class="fa fa-lock"></i>Rechercher</button>
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