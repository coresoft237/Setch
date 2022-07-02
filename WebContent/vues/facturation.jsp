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
        	 <select class="form-control" name=Statut>
                  <option value="facturationdirecte">Facturation directe</option>
                  <option value="facturationindirecte">Facturation indirecte</option> 
              </select>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Creation</button>
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