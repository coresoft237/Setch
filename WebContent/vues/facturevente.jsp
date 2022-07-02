<%@page import="setch.beans.reductionfacture"%>
<%@page import="setch.service.prixventeservice"%>
<%@page import="setch.service.articleservice"%>
<%@page import="setch.service.personneservice"%>
<%@page import="setch.beans.facturevente"%>
<%@ include file="header.jsp" %>
    <section id="main-content" >
      <section class="wrapper">
        <div class="col-lg-12 mt">
          <div class="row content-panel col1">
            <div class="col-lg-10 col-lg-offset-1">
              <div class="invoice-body invoice-body1">
                <div class="pull-left">
                <%
                facturevente facture=(facturevente)request.getAttribute("listevente");
                personneservice personneservice=new personneservice();
                articleservice articleservice=new articleservice();
                prixventeservice prixventeservice=new prixventeservice();
                reductionfacture reduction=(reductionfacture)request.getAttribute("reduction");
                double total1=0;
                %>
                  <h1><%out.print(facture.getEntreprise().getName()); %></h1>
                  <address>
                <strong><%out.print( facture.getEntreprise().getFormejuridique()); %></strong><br>
                <%out.print("NIU"+ facture.getEntreprise().getNiu()); %>,<%out.print("BP:"+facture.getEntreprise().getBp()); %><br>
                ,<%out.print(facture.getEntreprise().getWeb()); %><br>
                <abbr title="Phone"></abbr>  <%out.print("TEL:(237)"+facture.getEntreprise().getTelephone()); %>
              </address>
                </div>
                <!-- /pull-left -->
                <div class="pull-right">
                  <h2>RECU</h2>
                </div>
                <!-- /pull-right -->
                <div class="clearfix"></div>
                <br>
                <br>
                <br>
                <div class="row row1">
                  <div class="col-md-9">
                  <h4>Patient</h4>
                   <h5><%out.print(personneservice.getByid(facture.getListevente().get(0).getIdpatient()).getNom()); %></h5>
                  </div>
                  <!-- /col-md-9 -->
                  <div class="col-md-3">
                    <br>
                    <div class="row2">
                      <div class="pull-left"> Facture NO : </div>
                      <div class="pull-right "> <%out.print(facture.getListevente().get(0).getFacture()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <div class="row2">
                      <!-- /col-md-3 -->
                      <div class="pull-left"> Date facture : </div>
                      <div class="pull-right"><%out.print(facture.getListevente().get(0).getDate()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <div class="row2">
                      <!-- /col-md-3 -->
                      <div class="pull-left"> Prescripteur : </div>
                      <div class="pull-right"><%out.print(personneservice.getByid(facture.getListevente().get(0).getIdprescripteur()).getId()); %> </div>
                      <div class="clearfix"></div>
                    </div>
                    <!-- /row -->
                    <br>
                    
                  </div>
                  <!-- /invoice-body -->
                </div>
                <!-- /col-lg-10 -->
                <table class="table">
                  <thead>
                    <tr>
                      <th style="width:60px" class="text-center">QTE</th>
                      <th class="text-left">SOINS</th>
                      <th style="width:140px" class="text-right">PRIX UNIT</th>
                      <th style="width:90px" class="text-right">TOTAL</th>
                    </tr>
                  </thead>
                  <tbody>
                  <%
                  for(int i=0;i<facture.getListevente().size();i++)
                  {
                	  double total=(facture.getListevente().get(i).getQuantite()*prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction.getReduction()))/100;
                	  %>
                	  <tr>
                      <td class="text-center"><%out.print(facture.getListevente().get(i).getQuantite()); %></td>
                      <td><%out.print(articleservice.getByid(facture.getListevente().get(i).getIdarticle()).getNom()); %></td>
                      <td class="text-right">F.CFA<%out.print((prixventeservice.getByid(facture.getListevente().get(i).getIdprixvente()).getPrixvente()*(100-reduction.getReduction()))/100); %></td>
                      <td class="text-right">F.CFA<%out.print(total); %></td>
                    </tr>
                    <% 
                    total1=total1+total;
                  }
                  
                  %>
                    
                    <tr>
                      <td colspan="2" rowspan="4">
                        <h4>Conditions de vente</h4>
                        <p>Les soins achetes ne peuvent etre remboursés .Ce recu doit etre conservé aussi longtemps que possible.</p>
                        <td class="text-right"><strong>Total HT</strong></td>
                        <td class="text-right">F.CFA<%out.print(total1); %></td>
                    </tr>
                    <tr>
                      <td class="text-right no-border"><strong>TVA(19.25%)</strong></td>
                      <td class="text-right">F.CFA0.00</td>
                    </tr>
                    <tr>
                      <td class="text-right no-border">
                        <div class="well well-small green"><strong>Total</strong></div>
                      </td>
                      <td class="text-right"><strong>F.CFA<%out.print(total1); %></strong></td>
                    </tr>
                  </tbody>
                </table>
                <br>
                <br>
              </div>
              <!--/col-lg-12 mt -->
      </section>
      <!-- /wrapper -->
    </section>
<%@ include file="footer.jsp" %>