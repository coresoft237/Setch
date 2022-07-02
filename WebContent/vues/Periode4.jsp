
<%@ include file="header.jsp" %>

  <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading"> ${Titre}</h2>
        <div class="login-wrap">
        	<input type="date" name="dateinitiale" class="form-control" placeholder="Date initiale" value ="" autofocus>
          <br>
          <input type="date" name="datefinale" class="form-control" placeholder="Date finale" value ="" autofocus>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Recherche</button>
          <hr>

        </div>
        
      </form>
    </div>
  </div>
  
<%@ include file="footer.jsp" %>
