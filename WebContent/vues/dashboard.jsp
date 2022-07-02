<%@ include file="header.jsp" %>
<%@page import="setch.beans.utilisateur"%>
<%@page import="setch.beans.entreprise"%>
<%
 utilisateur utilisateur=(utilisateur)request.getAttribute("utilisateur");
entreprise entr=(entreprise)request.getAttribute("entrep");
 %>
 <section id="main-content">
      <section class="wrapper site-min-height">
        
        <div class="row mt">
          <div class="col-lg-12">
            <!-- debut -->
            <h1>INFORMATIONS GENERALES</h1>
            <h3><span class="tb">Nom de l 'entreprise:<%out.print(entr.getName());%></span></h3>
            <h3>Activite:<%out.print(entr.getActivite());%></h3>
            <h3>Boite postale:<%out.print(entr.getBp());%></h3>
            <h3>Telephone:<%out.print(entr.getTelephone());%></h3>
            <h3>Web:<%out.print(entr.getWeb());%></h3>
            <h3>NIU:<%out.print(entr.getNiu());%></h3>
            <h3>Forme juridique:<%out.print(entr.getFormejuridique());%></h3>
            <h3>Nom Utilisateur connecte : <%out.print(utilisateur.getNom());%></h3>
            <h3>Statut utilisateur : <%out.print(utilisateur.getStatut());%></h3>
            <h3>Adresse a contacter en cas de probleme:(+237) 699253722/borisadong@yahoo.fr</h3>
           
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