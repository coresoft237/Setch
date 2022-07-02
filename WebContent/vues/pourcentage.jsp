<%@page import="setch.beans.commissions"%>
<%@page import="setch.beans.articles"%>
<%@page import="setch.beans.prixvente"%>
<%@page import="setch.beans.personne"%>
<%@page import="setch.beans.depot"%>
<%@page import="setch.beans.famille"%>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%
Boolean executionoperation =(Boolean)request.getAttribute("executionoperation");
  List<famille> Listefamille=(List<famille>)request.getAttribute("liste");
  commissions commission=(commissions)request.getAttribute("commission");
%>
  
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
        <select class="form-control" placeholder="Famille" name=famille required>
        <%
        for(int i=0;i<Listefamille.size();i++)
        {
        	%>
        	<option value="<%out.print(Listefamille.get(i).getId());%>" <%if(commission.getFamille()==Listefamille.get(i).getId()){%>SELECTED <%}%>><%out.print(Listefamille.get(i).getNom()); %></option>
        	<%
        }
        %>
          
        </select>
        <br>
          <input type="date" name="date" class="form-control" placeholder="Date finale" value ="<%out.print(commission.getDate()); %>" <%if(!commission.getDate().equals("")){%>READONLY<%} %> autofocus>
          <br>
          <input type="number" name="pourcentage" max="100" class="form-control" value="<%out.print(commission.getPourcentage()); %>"placeholder="pourcentage en %" autofocus required>
          <br>
          <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> Creation</button>
          <hr>

        </div>
        
      </form>
    </div>
  </div>
  
<%@ include file="footer.jsp" %>
