<%@page import="setch.beans.famille"%>
<%@ include file="header.jsp" %>

  <div id="login-page">
    <div class="container">
      <form class="form-login" method="POST" >
        <h2 class="form-login-heading"> ${Titre}</h2>
        <div class="login-wrap">
        	<input type="date" name="date" class="form-control" placeholder="Date" value ="" autofocus>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i>Creation</button>
          <hr>

        </div>
        
      </form>
    </div>
  </div>
  
<%@ include file="footer.jsp" %>
