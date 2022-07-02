package setch.servelets;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import setch.beans.facturevente;
import setch.beans.paymentcredit;
import setch.beans.syntheselivraison;
import setch.service.commandeservice;
import setch.service.cryptpwds;
import setch.service.erreurservice;
import setch.service.essai;
import setch.service.headerpdf;
import setch.service.stockservice;
import setch.service.utilisateurservice;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
@MultipartConfig
public class login extends HttpServlet {
	private static final int TAILLE_TAMPON=10240;
	//public static final String CHEMIN_FICHIERS="C:\\Users\\BORIS DE DIEU\\eclipse-workspace\\SETCH\\WebContent\\file\\";
	
	public static final String CHEMIN_FICHIERS="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\HCNDA\\file\\";
	public static final String CHEMIN_FICHIERS1="C:\\Users\\BORIS DE DIEU\\eclipse-workspace\\SETCH\\WebContent\\file\\";
	private static final long serialVersionUID = 1L;
	//------------------service---------------
	setch.service.upload upload=new setch.service.upload();
    setch.service.databaseservice databaseservice=new setch.service.databaseservice();
    setch.service.utilisateurservice utilisateurservice=new setch.service.utilisateurservice();
    setch.service.erreurservice erreurservice=new setch.service.erreurservice();
    setch.service.reductionservice reductionservice=new setch.service.reductionservice();
    setch.service.entrepriseservice entrepriseservice=new setch.service.entrepriseservice();
    setch.service.autorisationservice autorisationservice=new setch.service.autorisationservice();
    setch.service.inventaireservice inventaireservice=new setch.service.inventaireservice();
    setch.service.stockservice stockservice=new setch.service.stockservice();
    setch.service.contenuautorisationservice contenuautorisationservice=new setch.service.contenuautorisationservice();
    setch.service.margeservice margeservice=new setch.service.margeservice();
    setch.service.familleservice familleservice=new setch.service.familleservice();
    setch.service.facturationservice facturationservice=new setch.service.facturationservice();
    setch.service.personneservice personneservice=new setch.service.personneservice();
    setch.service.articleservice articleservice=new setch.service.articleservice();
    setch.service.offreservice offreservice=new setch.service.offreservice();
    setch.service.besoinservice besoinservice=new setch.service.besoinservice();
    setch.service.livraisonservice livraisonservice=new setch.service.livraisonservice();
    setch.service.livraisonventeservice livraisonventeservice=new setch.service.livraisonventeservice();
    setch.service.prixventeservice prixventeservice=new setch.service.prixventeservice();
    setch.service.recapbesoinservice recapbesoinservice=new setch.service.recapbesoinservice();
    setch.service.uniteventeservice uniteventeservice=new setch.service.uniteventeservice();
    setch.service.paymentcreditservice paymentcreditservice=new setch.service.paymentcreditservice();
    setch.service.venteservice venteservice=new setch.service.venteservice();
    setch.service.commissionservice commissionservice=new setch.service.commissionservice();
    setch.service.validiteservice validiteservice=new setch.service.validiteservice();
    setch.service.statistiqueventeservice statistiqueventeservice=new setch.service.statistiqueventeservice();
    setch.service.correctionstockservice correctionstockservice=new setch.service.correctionstockservice();
    setch.service.serviceservice serviceservice=new setch.service.serviceservice();
    setch.service.transfertservice transfertservice=new setch.service.transfertservice();
    setch.service.commandeservice commandeservice=new setch.service.commandeservice();
    cryptpwds g=new cryptpwds();
    //----------------beans---------------
    setch.beans.utilisateur utilisateur=null;
    setch.beans.autorisation autorisation=null;
    setch.beans.contenuautorisation contenuautorisation=null;
    setch.beans.entreprise entreprise=null;
    setch.beans.famille famille=null;
    setch.beans.paymentcredit payementcredit=null;
    setch.beans.personne personne=null;
    setch.beans.inventaire inventaire=null;
    setch.beans.articles article=null;
    setch.beans.prixvente prixvente=null;
    setch.beans.unitevente unitevente=null;
    setch.beans.offre offre=null;
    setch.beans.besoin besoin=null;
    setch.beans.livraison livraison=null;
    setch.beans.livraisonvente livraisonvente=null;
    setch.beans.recaplivraisonvente recaplivraisonvente=null;
    setch.beans.vente vente=null;
    setch.beans.vente stock=null;
    setch.beans.commissions commission=null;
    setch.beans.validite validite=null;
    setch.beans.marge marge=null;
    setch.beans.reductionfacture reduction=null;
    setch.beans.reductionfacture reduction2=null;
    setch.beans.correctionstock correctionstock=null;
    setch.beans.recapcorrectionstock recapcorrectionstock=null;
    setch.beans.service service=null;
    setch.beans.transfert transfert=null;
    setch.beans.comande comande=null;
   setch.beans.facturation facturation=null;
    //----------------session-------------
    private javax.servlet.http.HttpSession variable;
    private javax.servlet.http.HttpSession variable1;
    private javax.servlet.http.HttpSession compte;
    private javax.servlet.http.HttpSession variable2;
    private javax.servlet.http.HttpSession variable3;
    private javax.servlet.http.HttpSession variable4;
    private javax.servlet.http.HttpSession variable5;
    public static  File file;
    //----------------variable------
    String operation="";
    String option="";
    boolean executionoperation=false;
    int matricule=0;
    int typeetat=0;
    LocalDate date;
    Date dateactuelle;
    Double reduc=0.0;
    Double reduc1=0.0;
    String numerofacture="";
    //------------Liste------------
    List<setch.beans.autorisation> Listeautorisation = new ArrayList<setch.beans.autorisation>();
    List<setch.beans.contenuautorisation> listecontenuautorisation = new ArrayList<setch.beans.contenuautorisation>();
    List<setch.beans.contenuautorisation> listecontenuautorisation1 = new ArrayList<setch.beans.contenuautorisation>();
    List<setch.beans.utilisateur> Listeutilisateur = new ArrayList<setch.beans.utilisateur>();
    List<setch.beans.famille> Listefamille = new ArrayList<setch.beans.famille>();
    List<setch.beans.personne> Listepersonne = new ArrayList<setch.beans.personne>();
    List<setch.beans.articles> Listearticle = new ArrayList<setch.beans.articles>();
    List<setch.beans.prixvente> Listeprixvente = new ArrayList<setch.beans.prixvente>();
    List<setch.beans.unitevente> Listeunitevente = new ArrayList<setch.beans.unitevente>();
    List<setch.beans.vente>listevente = new ArrayList<setch.beans.vente>();
    List<setch.beans.vente>listevente1 = new ArrayList<setch.beans.vente>();
    List<setch.beans.personne> listepatient = new ArrayList<setch.beans.personne>();
    List<setch.beans.personne> listeprescripteur = new ArrayList<setch.beans.personne>();
    List<setch.beans.entreprise> listeentreprise = new ArrayList<setch.beans.entreprise>();
    List<setch.beans.recapoffre> listesyntheseoffre = new ArrayList<setch.beans.recapoffre>();
    List<setch.beans.recapcommande> listecommande = new ArrayList<setch.beans.recapcommande>();
    List<setch.beans.recapbesoin> listesynthesebesoin = new ArrayList<setch.beans.recapbesoin>();
    List<setch.beans.recapinventaire> listesyntheseinventaire = new ArrayList<setch.beans.recapinventaire>();
    List<setch.beans.recaplivraison> listerecaplivraison = new ArrayList<setch.beans.recaplivraison>();
    List<setch.beans.recaplivraisonvente> listerecaplivraisonvente = new ArrayList<setch.beans.recaplivraisonvente>();
    List<setch.beans.livraison> listelivraison = new ArrayList<setch.beans.livraison>();
    List<setch.beans.livraisonvente> listelivraisonvente = new ArrayList<setch.beans.livraisonvente>();
    List<setch.beans.stock> listestock = new ArrayList<setch.beans.stock>();
    List<setch.beans.offre> listeoffre = new ArrayList<setch.beans.offre>();
    List<setch.beans.besoin> listebesoin = new ArrayList<setch.beans.besoin>();
    List<setch.beans.inventaire> listeinventaire = new ArrayList<setch.beans.inventaire>();
    List<setch.beans.marge> listemarge = new ArrayList<setch.beans.marge>();
    List<setch.beans.commissions> listecommission = new ArrayList<setch.beans.commissions>();
    List<setch.beans.validite> listevalidite = new ArrayList<setch.beans.validite>();
    List<setch.beans.paymentcredit> listepayementcredit = new ArrayList<setch.beans.paymentcredit>();
    List<setch.beans.statistiquevente2> listestatistiquevente = new ArrayList<setch.beans.statistiquevente2>();
    List<setch.beans.statistiquevente> listestatistiquevente1 = new ArrayList<setch.beans.statistiquevente>();
    List<setch.beans.statistiquecommission> listestatistiquecommission = new ArrayList<setch.beans.statistiquecommission>();
    List<setch.beans.reductionfacture> listereduction = new ArrayList<setch.beans.reductionfacture>();
    List<setch.beans.correctionstock> listecorrectionstock = new ArrayList<setch.beans.correctionstock>();
    List<setch.beans.recapcorrectionstock> listerecapcorrectionstock = new ArrayList<setch.beans.recapcorrectionstock>();
    List<setch.beans.service> listeservice = new ArrayList<setch.beans.service>();
    List<setch.beans.transfert> listetransfert = new ArrayList<setch.beans.transfert>();
    List<setch.beans.facturation> listefacturation = new ArrayList<setch.beans.facturation>();
    List<setch.beans.recaptransfert> listerecaptransfert = new ArrayList<setch.beans.recaptransfert>();
    List<setch.beans.comande> listecomande = new ArrayList<setch.beans.comande>();
    static List<String> listefacture = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//-------------fichier des ereurs
		file=new File(this.CHEMIN_FICHIERS1+""+"erreurs.txt");
		if(file.exists()==false) {
		file.createNewFile();
		
		}
		//----------------
					variable = request.getSession();
					variable1 = request.getSession();
					variable2 = request.getSession();
					variable3 = request.getSession();
					variable4 = request.getSession();
					variable5 = request.getSession();
					 compte = request.getSession();
					operation = (String)variable.getAttribute("operation");
					//utilisateur=(setch.beans.utilisateur)compte.getAttribute("utilisateur");
					
					if(databaseservice.databasesexits("setch")==false)
					{
						boolean creationstock=databaseservice.createdatabase();
						if(creationstock==true)
						{
							boolean creationutilisateur=databaseservice.createtableautilisateur();
							if(creationutilisateur==true)
							{
								boolean creationentreprise=databaseservice.createtableentreprise();
								if(creationentreprise==true)
								{
									boolean creationautorisation=databaseservice.createtableautorisation();
									if(creationautorisation==true)
									{
										boolean creationfamille=databaseservice.createtablefamille();
										if(creationfamille==true)
										{
											boolean creationpersonne=databaseservice.createtableapersonne();
											if(creationpersonne==true)
											{
												boolean creationarticle=databaseservice.createtablearticle();
												if(creationarticle==true)
												{
													boolean creationprixvente=databaseservice.createprixvente();
													if(creationprixvente==true)
													{
														boolean creationvente=databaseservice.createtablevente();
														if(creationvente==true)
														{
															boolean creationcommission=databaseservice.createtablecommission();
															if(creationcommission==true)
															{
																boolean creationvalidite=databaseservice.createtablevalidite();
																if(creationvalidite==true)
																{
																	boolean creationcontenuautorisation=databaseservice.createtablecontenuautorisation();
																	if(creationcontenuautorisation==true)
																	{
																		boolean creationunitevente=databaseservice.createunitevente();
																		if(creationunitevente==true)
																		{
																			boolean creationoffre=databaseservice.createoffre();
																			if(creationoffre==true)
																			{
																				boolean creationreduction=databaseservice.createtablereduction();
																				if(creationreduction==true)
																				{
																					boolean creationbesoin=databaseservice.createtablebesoin();
																					if(creationbesoin==true) {
																						boolean creationlivraison=databaseservice.createtablelivraison();
																						if(creationlivraison==true) {
																							boolean creationregltcredit=databaseservice.createtablepaymentcredit();
																							if(creationregltcredit==true) {
																								boolean creationservice=databaseservice.createtableservice();
																								if(creationservice==true) {
																									boolean creationtransfert=databaseservice.createtabletransfert();
																									if(creationtransfert==true) {
																										boolean creationcorrectionstock=databaseservice.createtablecorrectiostock();
																										if(creationcorrectionstock==true) {
																											boolean createfacturation=databaseservice.createtablefacturation();
																											if(createfacturation==true) {
																												boolean creationlivvente=databaseservice.createtablelivraisonvente();
																												if(creationlivvente==true) {
																													boolean creationinventaire=databaseservice.createtableinventaire();
																													if(creationinventaire==true) {
																														boolean creationcontenuachat=databaseservice.createtablecontenuachat();
																														if(creationcontenuachat==true) {
																															//creer super administrateur
																															autorisation=new setch.beans.autorisation("Super administrateur",1);
																															autorisationservice.add(autorisation);
																															//creer contenu super administrateur
																															listefacture=databaseservice.getAlltables();
																															listefacture.add("etatvente");
																															listefacture.add("etatcommissions");
																															listefacture.add("commande");
																															listefacture.add("etatmarge");
																															listefacture.add("etatjournalier");
																															listefacture.add("etatstock");
																															listefacture.add("etatstock1");
																															for(int i=0;i<listefacture.size();i++)
																															{
																																contenuautorisation=new setch.beans.contenuautorisation(1,1,listefacture.get(i),"true","true","true","true","true");
																																contenuautorisationservice.add(contenuautorisation);
																															}
																															//creer compte du super administrateur
																															//------crypt
																															cryptpwds a=new cryptpwds();
																															String p="";
																															try {
																																p=a.pwdcrypte("audeladuconseil");
																															} catch (Exception e) {
																																// TODO Auto-generated catch block
																																e.printStackTrace();
																															}
																															//crypt
																															setch.beans.utilisateur utilisateur=new setch.beans.utilisateur("CORE","CORE",p,"actif",1);
																															utilisateurservice.add(utilisateur);
																															//-------------------------
																															executionoperation=true;
																															variable.setAttribute("operation","creationadministrateur");
																															autorisation=new setch.beans.autorisation("Administrateur",2);
																															autorisationservice.add(autorisation);
																															
																															 response.setStatus(response.SC_MOVED_TEMPORARILY);
																																response.setHeader("Location","./User?option=addUserAdmin");
																														//response.setHeader("ok", "ok");
																															
																														}																											
																														
																													}

																												}
	
																											}
																										}
																									}
																								}
				
																							}
																							
																						}
																						}
			
																				}
																			}
			
																		}
													
			
																	}
																		
			
																}
															}
														
														}
													}	
												}
												
											}
											
										}
										
									}
								}
								
							}
						}
					}
					else
					{
						utilisateur= (setch.beans.utilisateur)compte.getAttribute("utilisateur");
						request.setAttribute("utilisateur",utilisateur );
						
						if(utilisateur==null)
						{
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location","./connect");
						}
						else
						{
							request.setAttribute("print","en attente");
							 option=request.getParameter("option");	
							  if(option.equals("null")) {
								 response.setStatus(response.SC_MOVED_TEMPORARILY);
									response.setHeader("Location","./connect");
							 }
							 //-----------connexion
							 else if(option.equals("connexion"))
							 {
								 listeentreprise=entrepriseservice.getAll();
									request.setAttribute("entrep",listeentreprise.get(0) );
									if(utilisateur.getAutorisation()==1)
									{
									
										Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("utilisateur",utilisateur );
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response );
									}
									else
									{
										listevalidite=validiteservice.getAll();
										if(listevalidite.size()>0)
										{
											String val1="";
											try {
												val1=g.pwddecrypte(listevalidite.get(0).getDate());
												
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											LocalDate val=LocalDate.parse(val1);
											if(LocalDate.now().compareTo(val)<=0)
											{
											compte.setAttribute("utilisateur",utilisateur);
											request.setAttribute("utilisateur",utilisateur );
											listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											Listefamille=familleservice.getAll("actif");
											 request.setAttribute("listfam",Listefamille );
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
											this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response );
											}
											else
											{
												response.setStatus(response.SC_MOVED_TEMPORARILY);
												response.setHeader("Location","./connect");
											}
										}
										else
										{
											response.setStatus(response.SC_MOVED_TEMPORARILY);
											response.setHeader("Location","./connect");
										}
									}
							 }
							//-------------------creation patient
							 else if(option.equals("creationpatient"))
								{
								 String nom=(request.getParameter("nom"));
								 String telephone=(request.getParameter("telephone"));
								 String titre=(request.getParameter("titre"));
								 String statut=(request.getParameter("statut"));
								 personne=new setch.beans.personne(nom, telephone, statut, titre);
								 personneservice.add(personne);
								
								}
							
							
							
							
							//-----------------article----------------------
							 else if(option.equals("chargementarticle")||option.equals("chargementfamille")||option.equals("chargementprescripteur")||option.equals("chargementcommission")||option.equals("chargementprix")||option.equals("chargementunite")||option.equals("chargementoffre"))
							 {
								 request.setAttribute("print","en attente");
								 request.setAttribute("Titre",option );
								 request.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/chargement.jsp" ).forward( request, response ); 
							 }
							
							//---------------------prixvente---------------
							 else if(option.equals("prixvente"))
							 {
								
							 }
							 
							
							 else if(option.equals("visualiseroffre"))
							 {
								 request.setAttribute("print","en attente");
								 String numero=request.getParameter("id");
								 variable1.setAttribute("numero",numero);
									 listeoffre=offreservice.getAll(numero);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listeoffre);
									request.setAttribute("option",option);
									personne=personneservice.getByid(listeoffre.get(0).getIdfournisseur());
									request.setAttribute("Titre","Offre "+listeoffre.get(0).getNumero()+" de "+personne.getNom()+ " du " + listeoffre.get(0).getDate()+"" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifieroffre"))
							 {
								 request.setAttribute("print","en attente");
								 int id=Integer.parseInt(request.getParameter("id"));
								 double prix=Double.parseDouble(request.getParameter("prix"));
								 String statut=request.getParameter("statut");
								 String numerooffre=request.getParameter("numero");
								 offre=offreservice.getByid(id);
								 offre.setUser(utilisateur.getId());
								 offre.setPrixvente(prix);
								 offre.setStatut(statut);
								 executionoperation=offreservice.update(id,offre);
								 if(executionoperation==true) {
									 listeoffre=offreservice.getAll(numerooffre); 
									
									 request.setAttribute("liste",listeoffre);
									request.setAttribute("option","visualiseroffre");
									personne=personneservice.getByid(listeoffre.get(0).getIdfournisseur());
									request.setAttribute("Titre","Offre "+listeoffre.get(0).getNumero()+" de "+personne.getNom()+ " du " + listeoffre.get(0).getDate()+"" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								 }		 
									 
							 }
							 else if(option.equals("imprimeroffre"))
							 {
								 request.setAttribute("print","en attente");
								 String numero=(String)variable1.getAttribute("numero");
									executionoperation=offreservice.update(utilisateur.getId(),"en attente",numero);
									if(executionoperation==true)
									{
										listeoffre=offreservice.getAll(numero);
										request.setAttribute("valeur",operation);
										 request.setAttribute("listeoffre",listeoffre);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/offre3.jsp" ).forward( request, response );
									}
								 		 		 
							 }
							
							
						
							 else if(option.equals("visualiserlivraison"))
							 {
								 request.setAttribute("print","en attente");
								 String numerobl=request.getParameter("id");
								 String bc=request.getParameter("bc");
								 String date=request.getParameter("date");
								 int idfournisseur=Integer.parseInt(request.getParameter("fournisseur"));
								 
								 variable1.setAttribute("numerobl",numerobl);
								 variable2.setAttribute("fournisseur",idfournisseur);
								 variable4.setAttribute("date",date);
								 variable5.setAttribute("numerobc",bc);
									 listelivraison=livraisonservice.getAll(numerobl,bc,idfournisseur,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraison);
									request.setAttribute("option",option);
									request.setAttribute("Titre","DETAIL LIVRAISON '"+numerobl+"'  DE '"+personneservice.getByid(idfournisseur).getNom()+"' DU '"+date+"' DE LA COMMANDE '"+bc+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifierlivraison"))
							 {
								 request.setAttribute("print","en attente");
								 String date=(String)variable4.getAttribute("date");
								 String numerobl=(String)variable1.getAttribute("numerobl");
								 String numerobc=(String)variable5.getAttribute("numerobc");
								 int fournisseur=(int)variable2.getAttribute("fournisseur");
								 int id=Integer.parseInt(request.getParameter("id"));
								 double pu=Double.parseDouble(request.getParameter("pu"));
								 double quantite=Double.parseDouble(request.getParameter("quantite"));
								 livraison=livraisonservice.getByid(id);
								 livraison.setUser(utilisateur.getId());
								 livraison.setQuantite(quantite);
								 livraison.setPu(pu);
								 executionoperation=livraisonservice.update(id,livraison);
								 if(executionoperation==true) {
									 listelivraison=livraisonservice.getAll(numerobl,numerobc,fournisseur,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraison);
									request.setAttribute("option","visualiserlivraison");
									request.setAttribute("Titre","DETAIL LIVRAISON '"+numerobl+"'  DE '"+personneservice.getByid(fournisseur).getNom()+"' DU '"+date+"' DE LA COMMANDE '"+numerobc+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								 }
								 else {
									 listelivraison=livraisonservice.getAll(numerobl,numerobc,fournisseur,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraison);
									request.setAttribute("option",option);
									request.setAttribute("Titre","DETAIL LIVRAISON '"+numerobl+"'  DE '"+personneservice.getByid(fournisseur).getNom()+"' DU '"+date+"' DE LA COMMANDE '"+numerobc+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								 }
							 }
							
							//-------livraison vente
							 else if(option.equals("livraisonvente"))
							 {
								 request.setAttribute("print","en attente");
									//suprimer factures en attente;
									livraisonventeservice.delete1(utilisateur.getId());
									livraisonventeservice.delete1("en attente");
										//------------
									 listerecaplivraisonvente=livraisonventeservice.getAll3();
									 request.setAttribute("Titre","Liste des livraisons vendues" );
									 request.setAttribute("option",option);
									 variable3.setAttribute("option",option);
									 request.setAttribute("liste",listerecaplivraisonvente);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationlivraisonvente1"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 Listepersonne=personneservice.getAll("client","actif");
								 listefacture=besoinservice.listefacture("transforme");
								 request.setAttribute("Listepersonne",Listepersonne );
								 request.setAttribute("Listefacture",listefacture );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("Titre","Creation livraison vente" );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente1.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("supprimerlivraisonvente"))
							 {
								 request.setAttribute("print","en attente");
								 String date=(String)variable4.getAttribute("date");
								 int client=(int)variable2.getAttribute("client");
								 String clients=personneservice.getByid(client).getNom();
									 Double id=Double.parseDouble(request.getParameter("id"));
									 executionoperation=livraisonventeservice.delete(id);
									 if(executionoperation==true){
										 listelivraisonvente=livraisonventeservice.getAll1("en attente",utilisateur.getId(),date,client,"en attente");
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listelivraisonvente",listelivraisonvente);
										 request.setAttribute("date",date);
										 variable.setAttribute("operation","creationlivraisonvente2");
										 request.setAttribute("fournisseur",clients);
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente2.jsp" ).forward( request, response );
									 }
									 else {
										 listelivraison=livraisonservice.getAll1("en attente",utilisateur.getId(),date,client,"en attente");
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listelivraison",listelivraison);
										 request.setAttribute("date",date);
										 variable.setAttribute("operation","creationlivraisonvente2");
										 request.setAttribute("client",clients);
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/livraison2.jsp" ).forward( request, response );
									 }
									 
							 }
							 else if(option.equals("visualiserlivraisonvente"))
							 {
								 request.setAttribute("print","en attente");
								 String numerobl=request.getParameter("id");
								 String date=request.getParameter("date");
								 int client=Integer.parseInt(request.getParameter("client"));
								 variable1.setAttribute("numerobl",numerobl);
								 variable2.setAttribute("client",client);
								 variable4.setAttribute("date",date);
									 listelivraisonvente=livraisonventeservice.getAll(numerobl,client,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraisonvente);
									request.setAttribute("option",option);
									request.setAttribute("Titre","DETAIL LIVRAISON VENTE   DE '"+personneservice.getByid(client).getNom()+"' " );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifierlivraisonvente"))
							 {
								 request.setAttribute("print","en attente");
								 String date=(String)variable4.getAttribute("date");
								 String numerobl=(String)variable1.getAttribute("numerobl");
								 int client=(int)variable2.getAttribute("client");
								 int id=Integer.parseInt(request.getParameter("id"));
								 double quantite=Double.parseDouble(request.getParameter("quantite"));
								 livraisonvente=livraisonventeservice.getByid(id);
								 livraisonvente.setUser(utilisateur.getId());
								 livraisonvente.setQuantite(quantite);
								 executionoperation=livraisonventeservice.update1(id,livraisonvente);
								 if(executionoperation==true) {
									 listelivraisonvente=livraisonventeservice.getAll(numerobl,client,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraisonvente);
									request.setAttribute("option","visualiserlivraisonvente");
									request.setAttribute("Titre","DETAIL LIVRAISON VENTE  DE '"+personneservice.getByid(client).getNom()+"' DU '"+date+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								 }
								 else {
									 listelivraisonvente=livraisonventeservice.getAll(numerobl,client,date);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listelivraisonvente);
									request.setAttribute("option",option);
									request.setAttribute("Titre","DETAIL LIVRAISON VENTE DE '"+personneservice.getByid(client).getNom()+"' DU '"+date+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								 }
							 }
							 else if(option.equals("cloturelivraisonvente"))
							 {
								 request.setAttribute("print","en attente");
								 String numerobl=request.getParameter("id");
								 String date=request.getParameter("date");
								 int client=Integer.parseInt(request.getParameter("client"));
								 executionoperation=livraisonventeservice.update(numerobl, date,client,"cloture");
								 if(executionoperation==true) {
									//suprimer factures en attente;
										livraisonventeservice.delete1(utilisateur.getId());
											//------------
										 listerecaplivraisonvente=livraisonventeservice.getAll3();
										 request.setAttribute("Titre","Liste des livraisons vente" );
										 request.setAttribute("option","livraisonvente");
										 request.setAttribute("liste",listerecaplivraisonvente);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
								 }
								 else {
									//suprimer factures en attente;
										livraisonventeservice.delete1(utilisateur.getId());
											//------------
										 listerecaplivraisonvente=livraisonventeservice.getAll3();
										 request.setAttribute("Titre","Liste des livraisons vente" );
										 request.setAttribute("option","livraisonvente");
										 request.setAttribute("liste",listerecaplivraisonvente);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
								 }
							 }
							//---------------------unitevente---------------
							 
							
							
							//---------------------vente---------------
							
							 else if(option.equals("ventecredit"))
							 {
								 request.setAttribute("print","en attente");
								 String date1=LocalDate.now().toString()+" 00:00:00";
								 String date2=LocalDate.now().toString()+" 23:59:59";
								 listefacture=venteservice.getfacture(date1,date2,"en attente","CN");
								 request.setAttribute("Titre","Factures de vente à credit du jour" );
								 request.setAttribute("option",option);
								 variable3.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("listefacture",listefacture);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("ventespecifique"))
							 {
								 request.setAttribute("print","en attente");
								 String date1=LocalDate.now().toString()+" 00:00:00";
								 String date2=LocalDate.now().toString()+" 23:59:59";
								 listefacture=venteservice.getfacture(date1,date2,"en attente","VS");
								 request.setAttribute("Titre","Factures de vente specifiqes du jour" );
								 request.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("listefacture",listefacture);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("retourventenormale"))
							 {
								 request.setAttribute("print","en attente");
								 String date1=LocalDate.now().toString()+" 00:00:00";
								 String date2=LocalDate.now().toString()+" 23:59:59";
								 listefacture=venteservice.getfacture(date1,date2,"en attente","RN");
								 request.setAttribute("Titre","Factures des avoirs normaux du jour" );
								 request.setAttribute("option",option);
								 variable3.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("listefacture",listefacture);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("retourventespecifique"))
							 {
								 request.setAttribute("print","en attente");
								 String date1=LocalDate.now().toString()+" 00:00:00";
								 String date2=LocalDate.now().toString()+" 23:59:59";
								 listefacture=venteservice.getfacture(date1,date2,"en attente","RS");
								 request.setAttribute("Titre","Factures des avoirs specifiqes du jour" );
								 request.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("listefacture",listefacture);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("etatventeparfamille"))
								{
								 request.setAttribute("print","en attente");
									typeetat=Integer.parseInt(request.getParameter("id"));
									String type=familleservice.getByid(typeetat).getNom();
									String dateinitiale =(String)variable2.getAttribute("date1");
									String datefinale =(String)variable3.getAttribute("date2");
									listevente=venteservice.getfacture1(dateinitiale,datefinale,"en attente");
									listevente1=venteservice.getfacture1(listevente, typeetat);
									 request.setAttribute("option",option);
									 request.setAttribute("listefacture",listevente1);
									 request.setAttribute("Titre","Detail vente  '" + type + "'  du '"+dateinitiale+"' au '"+datefinale+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
								}
							 else if(option.equals("visualiservente"))
							 {
								 request.setAttribute("print","en attente");
								 String facture =request.getParameter("id");
								 reduction=reductionservice.getByfacture(facture);
								 if(reduction==null)
								 {
									 reduction=new setch.beans.reductionfacture();
									 reduction.setReduction(0.0);
								 }
								 listevente=venteservice.getByfacture(facture);
								 listeentreprise=entrepriseservice.getAll();
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 request.setAttribute("listevente",facturevente);
									 request.setAttribute("reduction",reduction);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/facturevente.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("visualiserventeretour"))
							 {
								 request.setAttribute("print","en attente");
								 listeentreprise=entrepriseservice.getAll();
								 String facture =request.getParameter("id");
								 //----------nouvelle reduction
								 reduction2=reduction=reductionservice.getByfacture(facture);
								 if(reduction2==null)
									{
										reduction2=new setch.beans.reductionfacture();
										reduction2.setReduction(0);
									}
								 //----------ancienne reduction
								 String[] b =facture.split("/");
									int idnumerofacture=Integer.parseInt(b[2]);
									String factureretour=venteservice.getByid(idnumerofacture).getFacture();
									reduction=reductionservice.getByfacture(factureretour);
									if(reduction==null)
									{
										reduction=new setch.beans.reductionfacture();
										reduction.setReduction(0);
									}
								 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									
									request.setAttribute("reductionretour",reduction);
									request.setAttribute("reductionnouveau",reduction2);
									 request.setAttribute("listevente",facturevente);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/facturevente3.jsp" ).forward( request, response );
							 }
							 else if(option.equals("blvente"))
							 {
								 request.setAttribute("print","en attente");
								reduc=0.0;
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
									//------------
								listefacture=livraisonventeservice.getAll3("en attente");
								 request.setAttribute("executionoperation",true );
								 variable.setAttribute("operation",option);
								 request.setAttribute("Titre","Choix BL vente" );
								 request.setAttribute("listebl",listefacture);
								 request.setAttribute("option",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/choixblvente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationvente"))
							 {
								 request.setAttribute("print","en attente");
								reduc=0.0;
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
									//------------
								 listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("option",option);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("reduction",reduc);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationvente1")) {
								 listefacturation=facturationservice.getAll();
								 String facture="";
								 String date= LocalDateTime.now().toString();
								 String bl="";
								 String option = (String)variable3.getAttribute("option");
								 listeentreprise=entrepriseservice.getAll();
								 entreprise=listeentreprise.get(0);
								 if(option.equals("vente")) {
									 listefacture=venteservice.getfacture("en attente","VN");
										if(listefacture.size()<=0)
										{
											facture="1/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
											
										}
										else
										{
											String[] b =listefacture.get(0).split("/");
											int annee=Integer.parseInt(b[3]);
											if(LocalDate.now().getYear()==annee)
											{
												int numerotateur=Integer.parseInt(b[0])+1;
												facture=numerotateur+"/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												
											}
											else
											{
												facture="1/VN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												
											}
										}
										if(listefacturation.get(0).getType().equals("facturationdirecte")) {
											 listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
											 //------numero bl vente
											 if(listelivraisonvente.size()<=0)
												{
													bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												}
												else
												{
													String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
													int annee=Integer.parseInt(b[3]);
													if(LocalDate.now().getYear()==annee)
													{
														int numerotateur=Integer.parseInt(b[0])+1;
														bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
													}
													else
													{
														bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
													}
												}
											 /*mettre dans livraison vente*/
												listevente=venteservice.getByfacture(facture);
												for(int i=0;i<listevente.size();i++) {
													livraisonvente=new setch.beans.livraisonvente(listevente.get(i).getIduser(),date,bl,listevente.get(i).getIdpatient(),listevente.get(i).getIdarticle(),listevente.get(i).getQuantite(),listevente.get(i).getIdprixvente(),"cloture");
													livraisonventeservice.add(livraisonvente);
												}
										 }
										else {
											String numerobl =request.getParameter("livraisonvente");
											listelivraisonvente=livraisonventeservice.getAll(numerobl);
											//-----mettre dans vente
											for(int i=0;i<listelivraisonvente.size();i++) {
												vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",listelivraisonvente.get(i).getIdclient(),0,listelivraisonvente.get(i).getIdarticle(),listelivraisonvente.get(i).getQuantite(),listelivraisonvente.get(i).getPu(),"en attente");
											venteservice.add(vente);
											}
										}

										executionoperation=venteservice.update(facture,date,"en attente",utilisateur.getId(),bl);
										if(executionoperation==true) {
											
											request.setAttribute("print","facturenormale");
											reduc=0.0;
											
												//------------
											 listevente=venteservice.getAll("en attente",utilisateur.getId());
											 listeprescripteur=personneservice.getAll("prescripteur","actif");
											 listepatient=personneservice.getAll("patient","actif");
											 Listearticle=articleservice.getAll1("actif");
											 request.setAttribute("executionoperation",executionoperation );
											 variable.setAttribute("operation","creationvente");
											 variable4.setAttribute("numfacture",facture);
											 request.setAttribute("listevente",listevente);
											 request.setAttribute("option",option);
											 request.setAttribute("listeprescripteur",listeprescripteur);
											 request.setAttribute("listepatient",listepatient);
											 request.setAttribute("listearticle",Listearticle);
											 request.setAttribute("reduction",reduc);
											 Listefamille=familleservice.getAll("actif");
											 request.setAttribute("listfam",Listefamille );
											 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
												request.setAttribute("listecontenuautorisation",listecontenuautorisation );
											 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
										}
								 }
								 else if(option.equals("ventecredit")) {
										listefacture=venteservice.getfacture("en attente","CN");
										if(listefacture.size()<=0)
										{
											facture="1/CN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
										}
										else
										{
											String[] b =listefacture.get(0).split("/");
											int annee=Integer.parseInt(b[3]);
											if(LocalDate.now().getYear()==annee)
											{
												int numerotateur=Integer.parseInt(b[0])+1;
												facture=numerotateur+"/CN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
											}
											else
											{
												facture="1/CN/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
											}
										}
										if(listefacturation.get(0).getType().equals("facturationdirecte")) {
											 listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
											 //------numero bl vente
											 if(listelivraisonvente.size()<=0)
												{
													bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												}
												else
												{
													String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
													int annee=Integer.parseInt(b[3]);
													if(LocalDate.now().getYear()==annee)
													{
														int numerotateur=Integer.parseInt(b[0])+1;
														bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
													}
													else
													{
														bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
													}
												}
											 /*mettre dans livraison vente*/
												listevente=venteservice.getByfacture(facture);
												for(int i=0;i<listevente.size();i++) {
													livraisonvente=new setch.beans.livraisonvente(listevente.get(i).getIduser(),date,bl,listevente.get(i).getIdpatient(),listevente.get(i).getIdarticle(),listevente.get(i).getQuantite(),listevente.get(i).getIdprixvente(),"cloture");
													livraisonventeservice.add(livraisonvente);
												}
										 }
										else {
											String numerobl =request.getParameter("livraisonvente");
											listelivraisonvente=livraisonventeservice.getAll(numerobl);
											//-----mettre dans vente
											for(int i=0;i<listelivraisonvente.size();i++) {
												vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",listelivraisonvente.get(i).getIdclient(),0,listelivraisonvente.get(i).getIdarticle(),listelivraisonvente.get(i).getQuantite(),listelivraisonvente.get(i).getPu(),"en attente");
											venteservice.add(vente);
											}
										}
										
										executionoperation=venteservice.update(facture,date,"en attente",utilisateur.getId(),bl);
										if(executionoperation==true)
										{
											/*mettre dans livraison vente*/
											listevente=venteservice.getByfacture(facture);
											for(int i=0;i<listevente.size();i++) {
												livraisonvente=new setch.beans.livraisonvente(listevente.get(i).getIduser(),date,bl,listevente.get(i).getIdpatient(),listevente.get(i).getIdarticle(),listevente.get(i).getQuantite(),listevente.get(i).getIdprixvente(),"cloture");
												livraisonventeservice.add(livraisonvente);
											}
											request.setAttribute("print","facturenormale");
												reduc=0.0;
												//suprimer factures en attente;
												venteservice.delete(utilisateur.getId(),"en attente");
													//------------
												 listevente=venteservice.getAll("en attente",utilisateur.getId());
												 listeprescripteur=personneservice.getAll("prescripteur","actif");
												 listepatient=personneservice.getAll("patient","actif");
												 Listearticle=articleservice.getAll1("actif");
												 request.setAttribute("executionoperation",executionoperation );
												 variable.setAttribute("operation","creationventecredit");
												 variable4.setAttribute("numfacture",facture);
												 request.setAttribute("listevente",listevente);
												 request.setAttribute("option",option);
												 request.setAttribute("listeprescripteur",listeprescripteur);
												 request.setAttribute("listepatient",listepatient);
												 request.setAttribute("listearticle",Listearticle);
												 request.setAttribute("reduction",reduc);
												 Listefamille=familleservice.getAll("actif");
												 request.setAttribute("listfam",Listefamille );
												 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
													request.setAttribute("listecontenuautorisation",listecontenuautorisation );
												 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
										}
										}
										else if(option.equals("livraison")) {

										}
										else if(option.equals("livraisonvente")) {
											 listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
											 if(listelivraisonvente.size()<=0)
												{
													bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												}
												else
												{
													
														String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
														int annee=Integer.parseInt(b[3]);
														if(LocalDate.now().getYear()==annee)
														{
															int numerotateur=Integer.parseInt(b[0])+1;
															bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
														}
														else
														{
															bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
														}
													
													
												}
											 
											executionoperation=livraisonventeservice.update("en attente",bl,utilisateur.getId());	
											if(executionoperation==true) {
												 request.setAttribute("print","facturenormale");
													//suprimer factures en attente;
													livraisonventeservice.delete1(utilisateur.getId());
														//------------
													 listerecaplivraisonvente=livraisonventeservice.getAll3();
													 request.setAttribute("Titre","Liste des livraisons vente" );
													 request.setAttribute("option",option);
													 variable3.setAttribute("option",option);
													 variable1.setAttribute("numerobl",bl);
													 request.setAttribute("liste",listerecaplivraisonvente);
													 Listefamille=familleservice.getAll("actif");
													 request.setAttribute("listfam",Listefamille );
													 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
													request.setAttribute("listecontenuautorisation",listecontenuautorisation );
													 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );	
											}
											else {
												 request.setAttribute("print","en attente");
													//suprimer factures en attente;
													livraisonventeservice.delete1(utilisateur.getId());
														//------------
													 listerecaplivraisonvente=livraisonventeservice.getAll3();
													 request.setAttribute("Titre","Liste des livraisons vente" );
													 request.setAttribute("option",option);
													 variable3.setAttribute("option",option);
													 variable1.setAttribute("numerobl",bl);
													 request.setAttribute("liste",listerecaplivraisonvente);
													 Listefamille=familleservice.getAll("actif");
													 request.setAttribute("listfam",Listefamille );
													 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
													request.setAttribute("listecontenuautorisation",listecontenuautorisation );
													 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
											}
										}
								 
								 else if(option.equals("retourventenormale")) {
									 numerofacture = (String)variable2.getAttribute("numerofacture");
										listevente1=venteservice.getByfacture1(numerofacture);
										listeentreprise=entrepriseservice.getAll();
										listefacture=venteservice.getfacture("en attente","RN");
											
											if(listefacture.size()<=0)
											{
												facture="1/RN/"+listevente1.get(0).getId()+"/"+listeentreprise.get(0).getSigle()+"/"+LocalDate.now().getYear();
											}
											else
											{
												String[] b =listefacture.get(0).split("/");
												int annee=Integer.parseInt(b[4]);
												if(LocalDate.now().getYear()==annee)
												{
													int numerotateur=Integer.parseInt(b[0])+1;
													facture=numerotateur+"/RN/"+listevente1.get(0).getId()+"/"+listeentreprise.get(0).getSigle()+"/"+LocalDate.now().getYear();
												}
												else
												{
													facture="1/RN/"+listevente1.get(0).getId()+"/"+listeentreprise.get(0).getSigle()+"/"+LocalDate.now().getYear();
												}
											}
											listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
											 if(listelivraisonvente.size()<=0)
												{
													bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
												}
												else
												{
													
														String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
														int annee=Integer.parseInt(b[3]);
														if(LocalDate.now().getYear()==annee)
														{
															int numerotateur=Integer.parseInt(b[0])+1;
															bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
														}
														else
														{
															bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
														}
													
													
												}
											executionoperation=venteservice.update(facture, LocalDateTime.now().toString(),"en attente",utilisateur.getId(),bl);
										if(executionoperation==true)
										{
											request.setAttribute("print","facturenormale");
											 String date1=LocalDate.now().toString()+" 00:00:00";
											 String date2=LocalDate.now().toString()+" 23:59:59";
											 listefacture=venteservice.getfacture(date1,date2,"en attente","RN");
											 request.setAttribute("Titre","Factures des avoirs normaux du jour" );
											 request.setAttribute("option",option);
											 variable4.setAttribute("numfacture",facture);
											 variable3.setAttribute("option",option);
											 variable.setAttribute("operation",option);
											 request.setAttribute("listefacture",listefacture);
											 Listefamille=familleservice.getAll("actif");
											 request.setAttribute("listfam",Listefamille );
											 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
												request.setAttribute("listecontenuautorisation",listecontenuautorisation );
											 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
												
										}
								 }
								
							 }
							 else if(option.equals("printfacturevente")) {
								 
								 listeentreprise=entrepriseservice.getAll();
								 String option = (String)variable3.getAttribute("option");
								 if(option.equals("vente")) {
									 String facture = (String)variable4.getAttribute("numfacture");
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
							           
							            
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doit:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("RECU N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
							            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            	table.addCell(titre5);
							            	table.addCell(titre6);
							            	table.addCell(titre7);
							            	table.addCell(titre8);
							            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
							            	
							            }
							            
							            document.add(table);
							            Paragraph par5 = new Paragraph("Arreter la presente facture a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								 else if(option.equals("ventecredit")) {
									 String facture = (String)variable4.getAttribute("numfacture");
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doit:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("FACTURE N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
							            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            	table.addCell(titre5);
							            	table.addCell(titre6);
							            	table.addCell(titre7);
							            	table.addCell(titre8);
							            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
							            	
							            }
							            
							            document.add(table);
							            Paragraph par5 = new Paragraph("Arreter la presente facture a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 } 
								 else if(option.equals("retourventenormale")) {
									 String facture = (String)variable4.getAttribute("numfacture");
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doit:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("RECU AVOIR NORMAL N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            Paragraph par6 = new Paragraph("Soins retournes",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par6.setAlignment(Element.ALIGN_CENTER);
								         document.add(par6);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
								            	if(listevente.get(i).getQuantite()<0) {
								            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre5);
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
								            	
								            }
							            }
							            document.add(table);
							            Paragraph par5 = new Paragraph("Total retourne "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 //------
										 Paragraph a=new Paragraph(".");
										 document.add(a);
								            Paragraph ar6 = new Paragraph("Soins Pris",FontFactory.getFont(FontFactory.COURIER, 11f,0));
									         ar6.setAlignment(Element.ALIGN_CENTER);
									         document.add(ar6);
								            
								            PdfPTable tabl = new PdfPTable(4);
								            tabl.getDefaultCell().setBorder(0);
								            //-
								            Phrase titre5=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre6=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre7=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre8=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Stream.of(titre5,titre6,titre7,titre8)
								            .forEach(columnTitle -> {
								            	
								              PdfPCell header = new PdfPCell();
								             // header.setBackgroundColor(BaseColor.CYAN);
								              header.setPhrase(new Phrase(columnTitle));
								              header.setBorder(0);
								              header.setBorderWidthBottom(1);
								              header.setBorderColorBottom(BaseColor.GREEN);
								              header.setPaddingBottom(10);
								              tabl.addCell(header);
								             
								          });
								            //-
								            Paragraph p11=new Paragraph(".");
								            document.add(p11);
								            //--
								            Double t1=0.0;
								            for(int i=0;i<listevente.size();i++) {
								            	if(listevente.get(i).getQuantite()>0) {
								            		Phrase titre9=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre10=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre11=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre12=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            	tabl.addCell(titre9);
									            	tabl.addCell(titre10);
									            	tabl.addCell(titre11);
									            	tabl.addCell(titre12);
									            	t1=t1+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
								            	}  	
								            }
								            
								            document.add(tabl);
								            Paragraph par7 = new Paragraph("Soins pris "+ t1 ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            par7.setAlignment(Element.ALIGN_CENTER);
								            document.add(par7);
								            
								            Paragraph par8 = new Paragraph("Arreter la presente facture a la somme de "+ (t1+t) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            par8.setAlignment(Element.ALIGN_CENTER);
								            document.add(par8);
											 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								 else if(option.equals("livraison")) {
									 String numerobc=(String)variable5.getAttribute("numerobc");
									 String date=(String)variable4.getAttribute("date");
									 String numerobl=(String)variable1.getAttribute("numerobl");
									 int fournisseur=(int)variable2.getAttribute("fournisseur");
									 listelivraison=livraisonservice.getAll(numerobl, numerobc, fournisseur, date);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, livraison");
							            document.getPageNumber();
										document.open();
										//-------
										
										 String fournisseurs=personneservice.getByid(fournisseur).getNom();
										//-------
										 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
							            Paragraph par3 = new Paragraph("edité par:"+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_LEFT);
							            document.add(par3);
							            Chunk ck = new Chunk("LIVRAISON N "+numerobl+" DU FOURNISSEUR "+fournisseurs+" DU "+date+" DE LA COMMANDE "+numerobc+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Qte liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            //-
							            Paragraph p11=new Paragraph(".");
							            document.add(p11);
							            //--
							            Double t1=0.0;
							            for(int i=0;i<listelivraison.size();i++) {
							            	
							            		Phrase titre5=new Phrase(articleservice.getByid(listelivraison.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre6=new Phrase(""+listelivraison.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+listelivraison.get(i).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+listelivraison.get(i).getQuantite()*listelivraison.get(i).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre5);
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	t1=t1+(listelivraison.get(i).getQuantite()*listelivraison.get(i).getPu());
							            	 	
							            }
							            
							            document.add(table);
							            Paragraph par8 = new Paragraph("Arreter la presente livraison a la somme de "+ (t1) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par8.setAlignment(Element.ALIGN_CENTER);
							            document.add(par8);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								 else if(option.equals("livraisonvente")) {
									 String date=(String)variable4.getAttribute("date");
									 String numerobl=(String)variable1.getAttribute("numerobl");
									 int client=(int)variable2.getAttribute("client");
									 listelivraisonvente=livraisonventeservice.getAll(numerobl, client, date);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("Bl vente");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, livraison");
							            document.getPageNumber();
										document.open();
										//-------
										
										 String clients=personneservice.getByid(client).getNom();
										//-------
										 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
							            Paragraph par3 = new Paragraph("edité par:"+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_LEFT);
							            document.add(par3);
							            Chunk ck = new Chunk("LIVRAISON N "+listelivraisonvente.get(0).getNumerobl()+" DU CLIENT "+clients+" DU "+date+"  ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Qte liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            //-
							            Paragraph p11=new Paragraph(".");
							            document.add(p11);
							            //--
							            Double t1=0.0;
							            for(int i=0;i<listelivraisonvente.size();i++) {
							            	prixvente=prixventeservice.getByid(listelivraisonvente.get(i).getPu());
							            	
							            	
							            	
							            		Phrase titre5=new Phrase(articleservice.getByid(listelivraisonvente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre6=new Phrase(""+listelivraisonvente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+prixvente.getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+listelivraisonvente.get(i).getQuantite()*prixvente.getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre5);
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	t1=t1+(listelivraisonvente.get(i).getQuantite()*prixvente.getPrixvente());
							            	 	
							            }
							            
							            document.add(table);
							            /*Paragraph par8 = new Paragraph("Arreter la presente livraison a la somme de "+ (t1) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par8.setAlignment(Element.ALIGN_CENTER);
							            document.add(par8);*/
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								 else if(option.equals("etatstock1")) {
									String date1=(String)variable4.getAttribute("date1");
									String date2=(String)variable5.getAttribute("date2");
									listestock = new ArrayList<setch.beans.stock>();
									 try {
										listestock=stockservice.getAll(date1, date2,articleservice.getAll());
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 Document document = new Document(PageSize.A4.rotate());
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("Etat stock");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
										document.open();
										//-------
										 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
										Paragraph par1 = new Paragraph(""+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Chunk ck = new Chunk("ETAT DE STOCK DU"+date1+" AU "+date2+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(6);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Entree",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Sortie",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Correction entree",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre5=new Phrase("Correction sortie",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre6=new Phrase("Solde",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4,titre5,titre6)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            for(int i=0;i<listestock.size();i++) {
							            	Phrase titre7=new Phrase(listestock.get(i).getArticle(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listestock.get(i).getEntree(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre9=new Phrase(""+listestock.get(i).getVentes(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre10=new Phrase(""+listestock.get(i).getCorrectionentree(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre11=new Phrase(""+listestock.get(i).getCorrectionsortie(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre12=new Phrase(""+listestock.get(i).getReste(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            table.addCell(titre7);
							            	table.addCell(titre8);
							            	table.addCell(titre9);
							            	table.addCell(titre10);
							            	table.addCell(titre11);
							            	table.addCell(titre12);
							            }
							            
							            document.add(table);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 } 
							 }
							 else if(option.equals("printfacturevente1")) {
								 listeentreprise=entrepriseservice.getAll();
								 String option = (String)variable3.getAttribute("option");
								
								 String facture=request.getParameter("id");
								 if(option.equals("vente")) {
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
							            
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doits:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("FACTURE N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
							            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            	table.addCell(titre5);
							            	table.addCell(titre6);
							            	table.addCell(titre7);
							            	table.addCell(titre8);
							            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
							            	
							            }
							            
							            document.add(table);
							            Paragraph par5 = new Paragraph("Arreter la presente facture a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								
								 else if(option.equals("commande")) {
									int fournisseur=Integer.parseInt(request.getParameter("fournisser"));
									 listebesoin=besoinservice.getAll(facture);
									 listecomande=commandeservice.getAll2(fournisseur, listebesoin);
									 listeentreprise=entrepriseservice.getAll();
									 /*personne=personneservice.getByid(listebesoin.get(0).getUser());*/
									 Document document = new Document(PageSize.A4.rotate());
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("commande");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText,commande");
							            document.getPageNumber();
										document.open();
										//-------
										Paragraph par3 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par3.setAlignment(Element.ALIGN_CENTER);
								         document.add(par3);
										 Paragraph par2 = new Paragraph("Date impression:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par0 = new Paragraph("Date document:"+listecomande.get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par0.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par0);
								         Paragraph par4 = new Paragraph("Editeur:"+utilisateurservice.getByid(listecomande.get(0).getUser()).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
								         Paragraph par5 = new Paragraph("Fournisseur:"+personneservice.getByid(fournisseur).getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par5.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par5);
							            Chunk ck = new Chunk("BON DE COMMANDE  N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(5);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre5=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre11=new Phrase("Qte livree",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre3,titre4,titre5,titre11)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listecomande.size();i++) {
							            	Phrase titre6=new Phrase(articleservice.getByid(listecomande.get(i).getArticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listecomande.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre9=new Phrase(""+listecomande.get(i).getPrix(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre10=new Phrase(""+listecomande.get(i).getQuantite()*listecomande.get(i).getPrix(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre12=new Phrase("",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            	table.addCell(titre6);
							            	table.addCell(titre8);
							            	table.addCell(titre9);
							            	table.addCell(titre10);
							            	table.addCell(titre12);
							            	t=t+(listecomande.get(i).getQuantite()*listecomande.get(i).getPrix());
							            	
							            }
							            
							            document.add(table);
							            Paragraph par6 = new Paragraph("Arreter la presente commande a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par6.setAlignment(Element.ALIGN_CENTER);
							            document.add(par6);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 }
								 else if(option.equals("ventecredit")) {
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doit:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("FACTURE CREDIT N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
							            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            	table.addCell(titre5);
							            	table.addCell(titre6);
							            	table.addCell(titre7);
							            	table.addCell(titre8);
							            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
							            	
							            }
							            
							            document.add(table);
							            Paragraph par5 = new Paragraph("Arreter la presente facture a la somme de "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 } 
								 else if(option.equals("retourventenormale")) {
									 listevente=venteservice.getfacture1(facture);
									 personne=personneservice.getByid(listevente.get(0).getIdpatient());
									 listevente=venteservice.getByfacture(facture);
									setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("facture");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, facturation");
							            document.getPageNumber();
										document.open();
										//-------
										Paragraph par33 = new Paragraph(""+listeentreprise.get(0).getName()+" ",FontFactory.getFont(FontFactory.COURIER, 15f,Font.BOLD));
								         par33.setAlignment(Element.ALIGN_CENTER);
								         document.add(par33);
										 Paragraph par2 = new Paragraph("Le:"+facturevente.getListevente().get(0).getDate()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
								         Paragraph par4 = new Paragraph("Doit:",FontFactory.getFont(FontFactory.COURIER, 20f,0));
								         par4.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par4);
										Paragraph par1 = new Paragraph(""+personne.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par1.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par1);
							            Paragraph par3 = new Paragraph("Telephone:"+personne.getPhone()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_RIGHT);
							            document.add(par3);
							            Chunk ck = new Chunk("FACTURE RETOUR NORMAL N"+facture+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            Paragraph par6 = new Paragraph("Soins retournes",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par6.setAlignment(Element.ALIGN_CENTER);
								         document.add(par6);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            Paragraph p1=new Paragraph(".");
							            document.add(p1);
							            //--
							            Double t=0.0;
							            for(int i=0;i<listevente.size();i++) {
								            	if(listevente.get(i).getQuantite()<0) {
								            	Phrase titre5=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre6=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre5);
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	t=t+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
								            	
								            }
							            }
							            document.add(table);
							            Paragraph par5 = new Paragraph("Total retourne "+ t ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par5.setAlignment(Element.ALIGN_CENTER);
							            document.add(par5);
										 //------
										 Paragraph a=new Paragraph(".");
										 document.add(a);
								            Paragraph ar6 = new Paragraph("Soins Pris",FontFactory.getFont(FontFactory.COURIER, 11f,0));
									         ar6.setAlignment(Element.ALIGN_CENTER);
									         document.add(ar6);
								            
								            PdfPTable tabl = new PdfPTable(4);
								            tabl.getDefaultCell().setBorder(0);
								            //-
								            Phrase titre5=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre6=new Phrase("Quantite",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre7=new Phrase("Prix Unit",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre8=new Phrase("Prix total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Stream.of(titre5,titre6,titre7,titre8)
								            .forEach(columnTitle -> {
								            	
								              PdfPCell header = new PdfPCell();
								             // header.setBackgroundColor(BaseColor.CYAN);
								              header.setPhrase(new Phrase(columnTitle));
								              header.setBorder(0);
								              header.setBorderWidthBottom(1);
								              header.setBorderColorBottom(BaseColor.GREEN);
								              header.setPaddingBottom(10);
								              tabl.addCell(header);
								             
								          });
								            //-
								            Paragraph p11=new Paragraph(".");
								            document.add(p11);
								            //--
								            Double t1=0.0;
								            for(int i=0;i<listevente.size();i++) {
								            	if(listevente.get(i).getQuantite()>0) {
								            		Phrase titre9=new Phrase(articleservice.getByid(listevente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre10=new Phrase(""+listevente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre11=new Phrase(""+prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre12=new Phrase(""+listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            	tabl.addCell(titre9);
									            	tabl.addCell(titre10);
									            	tabl.addCell(titre11);
									            	tabl.addCell(titre12);
									            	t1=t1+(listevente.get(i).getQuantite()*prixventeservice.getByid(listevente.get(i).getIdprixvente()).getPrixvente());
								            	}  	
								            }
								            
								            document.add(tabl);
								            Paragraph par7 = new Paragraph("Soins pris "+ t1 ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            par7.setAlignment(Element.ALIGN_CENTER);
								            document.add(par7);
								            
								            Paragraph par8 = new Paragraph("Arreter la presente facture a la somme de "+ (t1+t) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            par8.setAlignment(Element.ALIGN_CENTER);
								            document.add(par8);
											 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 } 
								 else if(option.equals("livraison")) {
									 String option1=request.getParameter("option1");
									 String numerobl=request.getParameter("id");
									 String numerobc=request.getParameter("bc");
									 String date=request.getParameter("date");
									 int fournisseur=Integer.parseInt(request.getParameter("fournisseur"));
									 if(option1.equals("print")) {
										 listelivraison=livraisonservice.getAll(numerobl, numerobc, fournisseur, date);
										 Document document = new Document();
										 try {
											 response.setContentType("application/pdf");
											 response.setHeader("Content-Disposition","inline; filename=report.pdf");
											//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
											PdfWriter.getInstance(document, response.getOutputStream());
											document.addTitle("facture");
								            document.addAuthor("core");
								            document.addSubject("Génération de PDF.");
								            document.addKeywords("iText, livraison");
								            document.getPageNumber();
											document.open();
											//-------
											
											 String fournisseurs=personneservice.getByid(fournisseur).getNom();
											//-------
											 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
									         par2.setAlignment(Element.ALIGN_RIGHT);
									         document.add(par2);
								            Paragraph par3 = new Paragraph("edité par:"+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            par3.setAlignment(Element.ALIGN_LEFT);
								            document.add(par3);
								            Chunk ck = new Chunk("LIVRAISON N "+numerobl+" DU FOURNISSEUR "+fournisseurs+" DU "+date+" DE LA COMMANDE "+numerobc+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            ck.setUnderline((float) 1, -2);
								            Paragraph para = new Paragraph(ck);
								            para.setAlignment(Paragraph.ALIGN_CENTER);
								            document.add(para);
								            Paragraph p=new Paragraph(".");
								            document.add(p);
								            PdfPTable table = new PdfPTable(4);
								            table.getDefaultCell().setBorder(0);
								            //-
								            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre2=new Phrase("Qte liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre3=new Phrase("Prix liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre4=new Phrase("Total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Stream.of(titre1,titre2,titre3,titre4)
								            .forEach(columnTitle -> {
								            	
								              PdfPCell header = new PdfPCell();
								             // header.setBackgroundColor(BaseColor.CYAN);
								              header.setPhrase(new Phrase(columnTitle));
								              header.setBorder(0);
								              header.setBorderWidthBottom(1);
								              header.setBorderColorBottom(BaseColor.GREEN);
								              header.setPaddingBottom(10);
								              table.addCell(header);
								             
								          });
								            //-
								            //-
								            Paragraph p11=new Paragraph(".");
								            document.add(p11);
								            //--
								            Double t1=0.0;
								            for(int i=0;i<listelivraison.size();i++) {
								            	
								            		Phrase titre5=new Phrase(articleservice.getByid(listelivraison.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre6=new Phrase(""+listelivraison.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre7=new Phrase(""+listelivraison.get(i).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
										            Phrase titre8=new Phrase(""+listelivraison.get(i).getQuantite()*listelivraison.get(i).getPu(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            	table.addCell(titre5);
									            	table.addCell(titre6);
									            	table.addCell(titre7);
									            	table.addCell(titre8);
									            	t1=t1+(listelivraison.get(i).getQuantite()*listelivraison.get(i).getPu());
								            	 	
								            }
								            
								            document.add(table);
								            Paragraph par8 = new Paragraph("Arreter la presente livraison a la somme de "+ (t1) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            par8.setAlignment(Element.ALIGN_CENTER);
								            document.add(par8);
											 document.close();
										} catch (DocumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} 
									 }
									 else if(option1.equals("rapport")) {
										 listelivraison=livraisonservice.getAll(numerobl, numerobc, fournisseur, date);
										 List<setch.beans.syntheselivraison> t = new ArrayList<setch.beans.syntheselivraison>();
										 for(int i=0;i<listelivraison.size();i++) {
											Double a=livraisonservice.totalqteliv(numerobc,listelivraison.get(i).getIdarticle(),date);
											Double b=livraisonservice.totalpuliv(numerobc,listelivraison.get(i).getIdarticle(),date);
											Double prix=0.0;
											if(besoinservice.pucde(numerobc, listelivraison.get(i).getIdarticle())>0){
												prix=offreservice.getByid(besoinservice.pucde(numerobc, listelivraison.get(i).getIdarticle())).getPrixvente();
											}
											
											Double qte=besoinservice.qtecde(numerobc,listelivraison.get(i).getIdarticle());
											syntheselivraison c=new syntheselivraison(numerobc,articleservice.getByid(listelivraison.get(i).getIdarticle()).getNom(),qte,a,prix,b);
											t.add(c);
										 }
										 Document document = new Document();
										 try {
											 response.setContentType("application/pdf");
											 response.setHeader("Content-Disposition","inline; filename=report.pdf");
											//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
											PdfWriter.getInstance(document, response.getOutputStream());
											document.addTitle("Rapport livraison");
								            document.addAuthor("core");
								            document.addSubject("Génération de PDF.");
								            document.addKeywords("iText, livraison");
								            document.getPageNumber();
											document.open();
											//-------
											 String fournisseurs=personneservice.getByid(fournisseur).getNom();
											//-------
											 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
									         par2.setAlignment(Element.ALIGN_RIGHT);
									         document.add(par2);
								            Paragraph par3 = new Paragraph("edité par:"+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            par3.setAlignment(Element.ALIGN_LEFT);
								            document.add(par3);
								            Chunk ck = new Chunk("RAPPORT LIVRAISON DU BL "+numerobl+" DU FOURNISSEUR "+fournisseurs+" DU "+date+" ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            ck.setUnderline((float) 1, -2);
								            Paragraph para = new Paragraph(ck);
								            para.setAlignment(Paragraph.ALIGN_CENTER);
								            document.add(para);
								            Paragraph p=new Paragraph(".");
								            document.add(p);
								            PdfPTable table = new PdfPTable(5);
								            table.getDefaultCell().setBorder(0);
								            //-
								            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre2=new Phrase("Qte cde",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre3=new Phrase("Qte liv cumul",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre4=new Phrase("Prix cde",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Phrase titre5=new Phrase("Prix moyen liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
								            Stream.of(titre1,titre2,titre3,titre4,titre5)
								            .forEach(columnTitle -> {
								            	
								              PdfPCell header = new PdfPCell();
								             // header.setBackgroundColor(BaseColor.CYAN);
								              header.setPhrase(new Phrase(columnTitle));
								              header.setBorder(0);
								              header.setBorderWidthBottom(1);
								              header.setBorderColorBottom(BaseColor.GREEN);
								              header.setPaddingBottom(10);
								              table.addCell(header);
								             
								          });
								            //-
								            Paragraph p1=new Paragraph(".");
								            document.add(p1);
								            //-
								            for(int i=0;i<t.size();i++) {
								            	Phrase titre6=new Phrase(t.get(i).getArticle(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+t.get(i).getQtecde(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+t.get(i).getQteliv(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre9=new Phrase(""+t.get(i).getPc(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre10=new Phrase(""+t.get(i).getPl(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	table.addCell(titre9);
								            	table.addCell(titre10);
								            }
								            
								            document.add(table);
											 document.close();
										} catch (DocumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} 
									 }
									 
								 }
								 else if(option.equals("livraisonvente")) {
									 String date1=request.getParameter("date");
									 String numerobl1=request.getParameter("id");
									 int client=Integer.parseInt(request.getParameter("client"));
									
									 
									 listelivraisonvente=livraisonventeservice.getAll(numerobl1, client, date1);
									 Document document = new Document();
									 try {
										 response.setContentType("application/pdf");
										 response.setHeader("Content-Disposition","inline; filename=report.pdf");
										//PdfWriter.getInstance(document, new FileOutputStream("C:/Users/BORIS DE DIEU/Desktop/attestation/B.pdf"));
										PdfWriter.getInstance(document, response.getOutputStream());
										document.addTitle("Bl vente");
							            document.addAuthor("core");
							            document.addSubject("Génération de PDF.");
							            document.addKeywords("iText, livraison");
							            document.getPageNumber();
										document.open();
										//-------
										
										 String clients=personneservice.getByid(client).getNom();
										//-------
										 Paragraph par2 = new Paragraph("Le:"+LocalDate.now()+" ",FontFactory.getFont(FontFactory.COURIER, 11f,0));
								         par2.setAlignment(Element.ALIGN_RIGHT);
								         document.add(par2);
							            Paragraph par3 = new Paragraph("edité par:"+utilisateur.getNom()+" ",FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            par3.setAlignment(Element.ALIGN_LEFT);
							            document.add(par3);
							            Chunk ck = new Chunk("LIVRAISON N "+numerobl1+" DU CLIENT "+clients+" DU "+date1+"  ", FontFactory.getFont(FontFactory.COURIER, 12f,0));
							            ck.setUnderline((float) 1, -2);
							            Paragraph para = new Paragraph(ck);
							            para.setAlignment(Paragraph.ALIGN_CENTER);
							            document.add(para);
							            Paragraph p=new Paragraph(".");
							            document.add(p);
							            PdfPTable table = new PdfPTable(4);
							            table.getDefaultCell().setBorder(0);
							            //-
							            Phrase titre1=new Phrase("Article",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre2=new Phrase("Qte liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre3=new Phrase("Prix liv",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Phrase titre4=new Phrase("Total",FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            Stream.of(titre1,titre2,titre3,titre4)
							            .forEach(columnTitle -> {
							            	
							              PdfPCell header = new PdfPCell();
							             // header.setBackgroundColor(BaseColor.CYAN);
							              header.setPhrase(new Phrase(columnTitle));
							              header.setBorder(0);
							              header.setBorderWidthBottom(1);
							              header.setBorderColorBottom(BaseColor.GREEN);
							              header.setPaddingBottom(10);
							              table.addCell(header);
							             
							          });
							            //-
							            //-
							            Paragraph p11=new Paragraph(".");
							            document.add(p11);
							            //--
							            Double t1=0.0;
							            for(int i=0;i<listelivraisonvente.size();i++) {
							            	prixvente=prixventeservice.getByid(listelivraisonvente.get(i).getPu());
							            	
							            	
							            		Phrase titre5=new Phrase(articleservice.getByid(listelivraisonvente.get(i).getIdarticle()).getNom(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre6=new Phrase(""+listelivraisonvente.get(i).getQuantite(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre7=new Phrase(""+prixvente.getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
									            Phrase titre8=new Phrase(""+listelivraisonvente.get(i).getQuantite()*prixvente.getPrixvente(),FontFactory.getFont(FontFactory.COURIER, 12f,0));
								            	table.addCell(titre5);
								            	table.addCell(titre6);
								            	table.addCell(titre7);
								            	table.addCell(titre8);
								            	t1=t1+(listelivraisonvente.get(i).getQuantite()*prixvente.getPrixvente());
							            	 	
							            }
							            
							            document.add(table);
							            /*Paragraph par8 = new Paragraph("Arreter la presente livraison a la somme de "+ (t1) ,FontFactory.getFont(FontFactory.COURIER, 12f,Font.BOLD));
							            par8.setAlignment(Element.ALIGN_CENTER);
							            document.add(par8);*/
										 document.close();
									} catch (DocumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								 } 

							 }
							 else if(option.equals("creationventecredit"))
							 {
								 request.setAttribute("print","en attente");
								reduc=0.0;
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
									//------------
								 listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("option",option);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("reduction",reduc);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationventespecifique"))
							 {
								 request.setAttribute("print","en attente");
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
								reductionservice.delete(utilisateur.getId(),"en attente");
									//------------
								reduc=0.0;
								 listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option);
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("reduction",reduc);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationretourventenormale"))
							 {
								 request.setAttribute("print","en attente");
								reduc=0.0;
								 numerofacture=request.getParameter("facture");
								 variable2.setAttribute("numerofacture",numerofacture);
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
									//------------
								List<setch.beans.vente>listevente1=venteservice.getByfacture(numerofacture);
								List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
								List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
								 listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listevente1",listevente2);
								 request.setAttribute("option",option);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("numerofacture",numerofacture);
								 request.setAttribute("utilisateurs",utilisateur.getId());
								 request.setAttribute("listearticlearetourner",listearticle1);
								 request.setAttribute("reduction",reduc);
								 request.setAttribute("reduction1",reduc1);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationretourventespecifique"))
							 {
								 request.setAttribute("print","en attente");
								//suprimer factures en attente;
								venteservice.delete(utilisateur.getId(),"en attente");
								reductionservice.delete(utilisateur.getId(),"en attente");
									//------------
								reduc=0.0;
								 numerofacture=request.getParameter("facture");
								variable2.setAttribute("numerofacture",numerofacture);
								List<setch.beans.vente>listevente1=venteservice.getByfacture(numerofacture);
								List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
								List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
								 listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option);
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listevente1",listevente2);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("numerofacture",numerofacture);
								 request.setAttribute("listearticlearetourner",listearticle1);
								 request.setAttribute("utilisateurs",utilisateur.getId());
								 request.setAttribute("reduction",reduc);
								 request.setAttribute("reduction1",reduc1);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
							 }
							 else if(option.equals("creationretourventespecifiquebis"))
							 {
								 request.setAttribute("print","en attente");
								 String facture=request.getParameter("facture");
								 int idpatientretour=venteservice.getfacture1(facture).get(0).getIdpatient();
								 int idprescripteurretour=venteservice.getfacture1(facture).get(0).getIdprescripteur();
								 int idarticle=Integer.parseInt(request.getParameter("article"));
								 Double quantite=-Double.parseDouble(request.getParameter("quantite"));
								 Listeprixvente=prixventeservice.getAll(idarticle);
								 //controles
								 List<setch.beans.vente>listeventecontrol=venteservice.getByfacture(idarticle,utilisateur.getId(),"en attente");
								 Double qtesaisieinit=venteservice.getByfactureandarticle(facture, idarticle).getQuantite();
								 Double qteretour=venteservice.getfacture(idarticle, "/"+facture);
								 if(listeventecontrol.size()==0&&(qtesaisieinit-qteretour-quantite>=0))
								 {
								 setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",idpatientretour,idprescripteurretour,idarticle, quantite,Listeprixvente.get(0).getId(),"en attente");
									executionoperation=venteservice.add(vente);
								 } 
									if(executionoperation==true)
									{
										reduction=reductionservice.getByfacture(facture);
										if(reduction==null)
										{
											reduc=0.0;
										}
										else
										{
										reduc=reductionservice.getByfacture(facture).getReduction();
										}
										
										 variable2.setAttribute("numerofacture",facture);
										List<setch.beans.vente>listevente1=venteservice.getByfacture(facture);
										List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
										List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
										 listevente=venteservice.getAll("en attente",utilisateur.getId());
										 listeprescripteur=personneservice.getAll("prescripteur","actif");
										 listepatient=personneservice.getAll("patient","actif");
										 Listearticle=articleservice.getAll1("actif");
										 request.setAttribute("executionoperation",executionoperation );
										 variable.setAttribute("operation",option);
										 request.setAttribute("listevente",listevente);
										 request.setAttribute("listevente1",listevente2);
										 request.setAttribute("option","creationretourventenormale");
										 request.setAttribute("listeprescripteur",listeprescripteur);
										 request.setAttribute("listepatient",listepatient);
										 request.setAttribute("listearticle",Listearticle);
										 request.setAttribute("numerofacture",numerofacture);
										 request.setAttribute("utilisateurs",utilisateur.getId());
										 request.setAttribute("listearticlearetourner",listearticle1);
										 request.setAttribute("reduction",reduc);
										 request.setAttribute("reduction1",reduc1);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
									}
									else
									{
										 
										//erreur enregistrement retour normal;
									} 
							 }
							 else if(option.equals("creationretourventespecifiquebissss"))
							 {
								 request.setAttribute("print","en attente");
								 String facture=request.getParameter("facture");
								 int idpatientretour=venteservice.getfacture1(facture).get(0).getIdpatient();
								 int idprescripteurretour=venteservice.getfacture1(facture).get(0).getIdprescripteur();
								 int idarticle=Integer.parseInt(request.getParameter("article"));
								 Double quantite=-Double.parseDouble(request.getParameter("quantite"));
								 Listeprixvente=prixventeservice.getAll(idarticle);
								 //controles
								 List<setch.beans.vente>listeventecontrol=venteservice.getByfacture(idarticle,utilisateur.getId(),"en attente");
								 Double qtesaisieinit=venteservice.getByfactureandarticle(facture, idarticle).getQuantite();
								 Double qteretour=venteservice.getfacture(idarticle, "/"+facture);
								 if(listeventecontrol.size()==0&&(qtesaisieinit-qteretour-quantite>=0))
								 {
								 setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",idpatientretour,idprescripteurretour,idarticle, quantite,Listeprixvente.get(0).getId(),"en attente");
									executionoperation=venteservice.add(vente);
								 } 
									if(executionoperation==true)
									{
										reduction=reductionservice.getByfacture(facture);
										if(reduction==null)
										{
											reduc=0.0;
										}
										else
										{
										reduc=reductionservice.getByfacture(facture).getReduction();
										}
										
										 variable2.setAttribute("numerofacture",facture);
										List<setch.beans.vente>listevente1=venteservice.getByfacture(facture);
										List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
										List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
										 listevente=venteservice.getAll("en attente",utilisateur.getId());
										 listeprescripteur=personneservice.getAll("prescripteur","actif");
										 listepatient=personneservice.getAll("patient","actif");
										 Listearticle=articleservice.getAll1("actif");
										 request.setAttribute("executionoperation",executionoperation );
										 variable.setAttribute("operation",option);
										 request.setAttribute("listevente",listevente);
										 request.setAttribute("listevente1",listevente2);
										 request.setAttribute("option","creationretourventespecifique");
										 request.setAttribute("listeprescripteur",listeprescripteur);
										 request.setAttribute("listepatient",listepatient);
										 request.setAttribute("listearticle",Listearticle);
										 request.setAttribute("numerofacture",numerofacture);
										 request.setAttribute("utilisateurs",utilisateur.getId());
										 request.setAttribute("listearticlearetourner",listearticle1);
										 request.setAttribute("reduction",reduc);
										 request.setAttribute("reduction1",reduc1);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
									}
									else
									{
										 
										//erreur enregistrement retour normal;
									} 
							 }
							 else if(option.equals("creationretourventespecifiquebiss"))
							 {
								 
								 request.setAttribute("print","en attente");
								 String facture=request.getParameter("facture");
								 int idpatientretour=venteservice.getfacture1(facture).get(0).getIdpatient();
								 int idprescripteurretour=Integer.parseInt(request.getParameter("prescripteur"));
								 int idarticle=Integer.parseInt(request.getParameter("article"));
								 Double quantite=Double.parseDouble(request.getParameter("quantite"));
								 Listeprixvente=prixventeservice.getAll(idarticle);
								 //control
								 List<setch.beans.vente>listeventecontrol=venteservice.getByfacture(idarticle,utilisateur.getId(),"en attente");
								 if(listeventecontrol.size()==0)
								 {
								 setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",idpatientretour,idprescripteurretour,idarticle, quantite,Listeprixvente.get(0).getId(),"en attente");
									executionoperation=venteservice.add(vente);
								 }
									if(executionoperation==true)
									{
										reduction=reductionservice.getByfacture(facture);
										if(reduction==null)
										{
											reduc=0.0;
										}
										else
										{
										reduc=reductionservice.getByfacture(facture).getReduction();
										}
										 variable2.setAttribute("numerofacture",facture);
										List<setch.beans.vente>listevente1=venteservice.getByfacture(facture);
										List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
										List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
										 listevente=venteservice.getAll("en attente",utilisateur.getId());
										 listeprescripteur=personneservice.getAll("prescripteur","actif");
										 listepatient=personneservice.getAll("patient","actif");
										 Listearticle=articleservice.getAll1("actif");
										 request.setAttribute("executionoperation",executionoperation );
										 variable.setAttribute("operation",option);
										 request.setAttribute("listevente",listevente);
										 request.setAttribute("listevente1",listevente2);
										 request.setAttribute("option",option);
										 request.setAttribute("listeprescripteur",listeprescripteur);
										 request.setAttribute("listepatient",listepatient);
										 request.setAttribute("listearticle",Listearticle);
										 request.setAttribute("numerofacture",numerofacture);
										 request.setAttribute("utilisateurs",utilisateur.getId());
										 request.setAttribute("listearticlearetourner",listearticle1);
										 request.setAttribute("reduction1",reduc1);
										 request.setAttribute("reduction",reduc);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
									}
									else
									{
										 
										//erreur enregistrement retour normal;
									} 
							 }
							 else if(option.equals("creationretourventespecifiquebisss"))
							 {
								 request.setAttribute("print","en attente");
								 Double reductions =Double.parseDouble(request.getParameter("reduction"));
									//enregistrer reduction
									reduction=new setch.beans.reductionfacture(utilisateur.getId(),LocalDate.now().toString(),"en attente", reductions);
									reductionservice.add(reduction);
								 String facture=request.getParameter("facture");
								 int idpatientretour=venteservice.getfacture1(facture).get(0).getIdpatient();
								 int idprescripteurretour=Integer.parseInt(request.getParameter("prescripteur"));
								 int idarticle=Integer.parseInt(request.getParameter("article"));
								 Double quantite=Double.parseDouble(request.getParameter("quantite"));
								 Listeprixvente=prixventeservice.getAll(idarticle);
								 //control
								 List<setch.beans.vente>listeventecontrol=venteservice.getByfacture(idarticle,utilisateur.getId(),"en attente");
								 if(listeventecontrol.size()==0)
								 {
								 setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",idpatientretour,idprescripteurretour,idarticle, quantite,Listeprixvente.get(0).getId(),"en attente");
									executionoperation=venteservice.add(vente);
								 }
									if(executionoperation==true)
									{
										reduction=reductionservice.getByfacture(facture);
										if(reduction==null)
										{
											reduc=0.0;
										}
										else
										{
										reduc=reductionservice.getByfacture(facture).getReduction();
										}
										 variable2.setAttribute("numerofacture",facture);
										List<setch.beans.vente>listevente1=venteservice.getByfacture(facture);
										List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
										List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
										 listevente=venteservice.getAll("en attente",utilisateur.getId());
										 listeprescripteur=personneservice.getAll("prescripteur","actif");
										 listepatient=personneservice.getAll("patient","actif");
										 Listearticle=articleservice.getAll1("actif");
										 request.setAttribute("executionoperation",executionoperation );
										 variable.setAttribute("operation",option);
										 request.setAttribute("listevente",listevente);
										 request.setAttribute("listevente1",listevente2);
										 request.setAttribute("option","creationretourventespecifique");
										 request.setAttribute("listeprescripteur",listeprescripteur);
										 request.setAttribute("listepatient",listepatient);
										 request.setAttribute("listearticle",Listearticle);
										 request.setAttribute("numerofacture",numerofacture);
										 request.setAttribute("utilisateurs",utilisateur.getId());
										 request.setAttribute("listearticlearetourner",listearticle1);
										 request.setAttribute("reduction1",reductions);
										 request.setAttribute("reduction",reduc);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
									}
									else
									{
										 
										//erreur enregistrement retour normal;
									} 
							 }
							 else if(option.equals("modifiervente"))
							 {
								
							 }
							else if(option.equals("supprimervente"))
							{
								request.setAttribute("print","en attente");
								Double matricule = Double.parseDouble(request.getParameter("id"));
								vente=venteservice.getByid(matricule);
								executionoperation=venteservice.delete1(vente,"en attente");
								if(executionoperation==true)
								{
									reduc=0.0;
									listevente=venteservice.getAll("en attente",utilisateur.getId());
									listeprescripteur=personneservice.getAll("prescripteur","actif");
									listepatient=personneservice.getAll("patient","actif");
									Listearticle=articleservice.getAll("actif");
									request.setAttribute("executionoperation",executionoperation );
									request.setAttribute("listevente",listevente);
									request.setAttribute("listeprescripteur",listeprescripteur);
									request.setAttribute("listepatient",listepatient);
									request.setAttribute("listearticle",Listearticle);
									Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 request.setAttribute("reduction",reduc);
									request.setAttribute("option","creationvente");
									this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response ); 
								}
								else
								{
									reduc=0.0;
									listevente=venteservice.getAll("en attente",utilisateur.getId());
									listeprescripteur=personneservice.getAll("prescripteur","actif");
									listepatient=personneservice.getAll("patient","actif");
									Listearticle=articleservice.getAll("actif");
									request.setAttribute("executionoperation",executionoperation );
									request.setAttribute("listevente",listevente);
									request.setAttribute("listeprescripteur",listeprescripteur);
									request.setAttribute("listepatient",listepatient);
									request.setAttribute("listearticle",Listearticle);
									Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 request.setAttribute("reduction",reduc);
									request.setAttribute("option","creationvente");
									this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );  
								}	
							}
							else if(option.equals("supprimerventespecifique"))
							{
								request.setAttribute("print","en attente");
								matricule = Integer.parseInt(request.getParameter("id"));
								vente=venteservice.getByid(matricule);
								executionoperation=venteservice.delete(vente);
								if(executionoperation==true)
								{
									reduc= Double.parseDouble(request.getParameter("reduction"));
									listevente=venteservice.getAll("en attente",utilisateur.getId());
									listeprescripteur=personneservice.getAll("prescripteur","actif");
									listepatient=personneservice.getAll("patient","actif");
									Listearticle=articleservice.getAll("actif");
									request.setAttribute("executionoperation",executionoperation );
									request.setAttribute("listevente",listevente);
									request.setAttribute("listeprescripteur",listeprescripteur);
									request.setAttribute("listepatient",listepatient);
									request.setAttribute("listearticle",Listearticle);
									Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									request.setAttribute("option","creationventespecifique");
									 request.setAttribute("reduction",reduc);
									this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response ); 
								}
								else
								{
									reduc=0.0;
									listevente=venteservice.getAll("en attente",utilisateur.getId());
									listeprescripteur=personneservice.getAll("prescripteur","actif");
									listepatient=personneservice.getAll("patient","actif");
									Listearticle=articleservice.getAll("actif");
									request.setAttribute("executionoperation",executionoperation );
									request.setAttribute("listevente",listevente);
									request.setAttribute("listeprescripteur",listeprescripteur);
									request.setAttribute("listepatient",listepatient);
									request.setAttribute("listearticle",Listearticle);
									Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									request.setAttribute("option","creationventespecifique");
									 request.setAttribute("reduction",reduc);
									this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );  
								}	
							}
							else if(option.equals("supprimerretourventenormale"))
							{
								request.setAttribute("print","en attente");
								Double matricule = Double.parseDouble(request.getParameter("id"));
								vente=venteservice.getByid(matricule);
								executionoperation=venteservice.delete1(vente,"en attente");
								if(executionoperation==true)
								{
									String facture = (String)variable2.getAttribute("numerofacture");
									reduction=reductionservice.getByfacture(facture);
									if(reduction==null)
									{
										reduc=0.0;
									}
									else
									{
									reduc=reductionservice.getByfacture(facture).getReduction();
									}
									 variable2.setAttribute("numerofacture",facture);
									List<setch.beans.vente>listevente1=venteservice.getByfacture(facture);
									List<setch.beans.articles>listearticle1=articleservice.getAll(listevente1);
									 listevente=venteservice.getAll("en attente",utilisateur.getId());
									 List<setch.beans.vente>listevente2=venteservice.getAll1("en attente",utilisateur.getId());
									 listeprescripteur=personneservice.getAll("prescripteur","actif");
									 listepatient=personneservice.getAll("patient","actif");
									 Listearticle=articleservice.getAll1("actif");
									 request.setAttribute("executionoperation",executionoperation );
									 variable.setAttribute("operation",option);
									 request.setAttribute("listevente",listevente);
									 request.setAttribute("listevente1",listevente2);
									 request.setAttribute("option",option);
									 request.setAttribute("listeprescripteur",listeprescripteur);
									 request.setAttribute("listepatient",listepatient);
									 request.setAttribute("listearticle",Listearticle);
									 request.setAttribute("numerofacture",numerofacture);
									 request.setAttribute("utilisateurs",utilisateur.getId());
									 request.setAttribute("listearticlearetourner",listearticle1);
									 request.setAttribute("reduction",reduc);
									 request.setAttribute("reduction1",reduc1);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/retourvente.jsp" ).forward( request, response );
								}
								else
								{
									//echec suppression retour normaux
								}	
							}
							
							 //-------------etat vente
							 else if(option.equals("etatvente"))
							 {
								 request.setAttribute("print","en attente");
								 request.setAttribute("Titre","Choix periode de vente" );
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/Periode.jsp" ).forward( request, response );
							 } 
							//-------------etat vente detaillée
							 else if(option.equals("etatvente1"))
							 {
								 request.setAttribute("print","en attente");
								typeetat=Integer.parseInt(request.getParameter("type"));
								 request.setAttribute("Titre","Choix periode de vente" );
								 variable.setAttribute("operation",option);
								 variable1.setAttribute("typeetatvente",typeetat);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/Periode2.jsp" ).forward( request, response );
							 } 
							 else if(option.equals("etatcommission"))
							 {
								 request.setAttribute("print","en attente");
								 request.setAttribute("Titre","Choix periode de commissions" );
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/Periode2.jsp" ).forward( request, response );
							 } 
							 else if(option.equals("etatmarge"))
							 {
								 request.setAttribute("print","en attente");
								 request.setAttribute("Titre","Choix periode de marge" );
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/periodemarge.jsp" ).forward( request, response );
							 }
							 else if(option.equals("etatmarge1"))
							 {
								 request.setAttribute("print","en attente");
								 String date1=request.getParameter("date1");
								 String date2=request.getParameter("date2");
								 String heure1=request.getParameter("heure1");
								 String heure2=request.getParameter("heure2");
								 String date3=""+date1+" "+heure1+":00";
								 String date4=""+date2+" "+heure2+":00";
								 listefacture=venteservice.getfacture1(date3, date4);
								 listemarge=margeservice.getAll(listefacture, date3, date4);
								 request.setAttribute("Titre","Etat marge du '"+date3 +"' au '"+date4+"'" );
								 request.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("liste",listemarge);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 } 
							 //----------stock global
							 else if(option.equals("etatstock"))
							 {
								 request.setAttribute("print","en attente");
								 request.setAttribute("Titre","Choix periode de calcul" );
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/etatstock.jsp" ).forward( request, response );
							 } 
							 else if(option.equals("etatstock1"))
							 {
								 request.setAttribute("print","facturenormale");
								 String date1=request.getParameter("date1");
								 String date2=request.getParameter("date2");
								 variable4.setAttribute("date1",date1);
								 variable5.setAttribute("date2",date2);
								 variable3.setAttribute("option",option);
								 request.setAttribute("option",option);
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response );
							 } 
							 else if(option.equals("visualisercommission"))
							 {
								 request.setAttribute("print","en attente");
								int idprescripteur = Integer.parseInt(request.getParameter("id"));
								String personne=personneservice.getByid(idprescripteur).getNom();
								 String date1=request.getParameter("id1");
								 String date2=request.getParameter("id2");
								 listevente=venteservice.getfacture1(idprescripteur, date1, date2,"facture");
								 listestatistiquecommission=statistiqueventeservice.fonctioncommission(listevente);
								 request.setAttribute("Titre","Detail prescriptions de '"+personne +"' du '"+date1+"' au '"+date2+"'" );
								 request.setAttribute("option",option);
								 variable.setAttribute("operation",option);
								 request.setAttribute("listefacture",listestatistiquecommission);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 } 
							 //-------------pourcentage
							 else if(option.equals("pourcentagecommission"))
							 {
								 request.setAttribute("print","en attente");
								 listecommission=commissionservice.getAll();
								 request.setAttribute("Titre","Liste des pourcentages commissions" );
								 request.setAttribute("option",option);
								 request.setAttribute("liste",listecommission);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("creationpourcentage"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 commission=new setch.beans.commissions();
								 commission.setDate("");
								 commission.setPourcentage(0);
								 commission.setFamille(0);
								 request.setAttribute("commission",commission);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("Titre","Creation pourcentage" );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("liste",Listefamille);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifierpourcentage"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 matricule = Integer.parseInt(request.getParameter("id"));
								 commission=commissionservice.getByid(matricule);
								 request.setAttribute("commission",commission);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("Titre","Creation pourcentage" );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 variable1.setAttribute("matricule",matricule);
								 request.setAttribute("liste",Listefamille);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 
							 }
							 //-----------facturation
							 else if(option.equals("facturation"))
							 {
								 request.setAttribute("print","en attente");
								 listefacturation=facturationservice.getAll();
								 request.setAttribute("Titre","Historique types facturations" );
								 request.setAttribute("option",option);
								 request.setAttribute("liste",listefacturation);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("creationfacturation"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 request.setAttribute("Titre","Type facturation" );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/facturation.jsp" ).forward( request, response ); 
							 }					
							
							 //-----------contenuautorisation
							 else if(option.equals("modifiercontenuautorisation"))
							 {
								 request.setAttribute("print","en attente");
									 String etat=request.getParameter("id2");
									String colone=request.getParameter("nom");
									int numero=Integer.parseInt(request.getParameter("id"));
									int idauto=Integer.parseInt(request.getParameter("idauto"));
									if(etat.equals("true"))
									{
										executionoperation=contenuautorisationservice.update(numero, colone, "true");
									}
									else
									{
										executionoperation=contenuautorisationservice.update(numero, colone, "false");
									}
									
									 autorisation=autorisationservice.getByid(idauto);
									 listecontenuautorisation=contenuautorisationservice.getAll(idauto);
									 request.setAttribute("Titre","Liste des autorisations de '"+autorisation.getNom()+"'" );
									 request.setAttribute("option","visualiserautorisation");
									 request.setAttribute("liste",listecontenuautorisation);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
									
							 }
							 //------------payement credit
							 else if(option.equals("payementcredit"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 String facture=request.getParameter("id");
								 Double total=Double.parseDouble(request.getParameter("val"));
								 
								 payementcredit=new paymentcredit();
								 payementcredit.setFacture(facture);
								 payementcredit.setId(0);
								 payementcredit.setMontant(0.0);
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option );
								 request.setAttribute("Titre","Reglement credit" );
								 request.setAttribute("payement",payementcredit );
								 request.setAttribute("total",total );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/paymentcredit.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifierpayementcredit"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 int id=Integer.parseInt(request.getParameter("id"));
								 Double total=(Double)variable1.getAttribute("val");
								
								 payementcredit=paymentcreditservice.getByid(id);
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option );
								 request.setAttribute("Titre","Modification reglement credit" );
								 request.setAttribute("payement",payementcredit );
								 request.setAttribute("total",total );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/paymentcredit.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("listepayementcredit"))
							 {
								 request.setAttribute("print","en attente");
								 String facture=request.getParameter("id");
								 Double val=Double.parseDouble(request.getParameter("val"));
								 listepayementcredit=paymentcreditservice.getAll(facture);
								 variable1.setAttribute("val",val);
								 request.setAttribute("Titre","Liste des paiemens de la facture '"+listepayementcredit.get(0).getFacture()+"' " );
								 request.setAttribute("option",option);
								 request.setAttribute("liste",listepayementcredit);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							 //------------service
							 else if(option.equals("service"))
							 {
								 request.setAttribute("print","en attente");
								 listeservice=serviceservice.getAll();
								 request.setAttribute("Titre","Liste des services" );
								 request.setAttribute("option",option);
								 request.setAttribute("liste",listeservice);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 
							 else if(option.equals("creationservice"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 service=new setch.beans.service();
								 service.setNom("");
								 service.setStatut("");
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option );
								 request.setAttribute("Titre","Creation service" );
								 request.setAttribute("famille",service );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/service.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifierservice"))
							 {
								 request.setAttribute("print","en attente");
								 matricule = Integer.parseInt(request.getParameter("id"));
								 service=serviceservice.getByid(matricule);
								 executionoperation=true;
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("option",option );
								 request.setAttribute("famille",service );
								 request.setAttribute("Titre","Modifier service" );
								 request.setAttribute("matricule",matricule );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/service.jsp" ).forward( request, response ); 
							 }
							 
							 //----------correction stock
							 else if(option.equals("listecorrectionstock"))
							 {
								 request.setAttribute("print","en attente");
								 //suprimer factures en attente;
									correctionstockservice.delete1(utilisateur.getId());
									correctionstockservice.delete("en attente",utilisateur.getId());
										//------------
									 listerecapcorrectionstock=correctionstockservice.recapcorrection();
									 request.setAttribute("Titre","Liste des corrections stock" );
									 request.setAttribute("option",option);
									 request.setAttribute("liste",listerecapcorrectionstock);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							 else if(option.equals("correctionstock"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 Listefamille=familleservice.getAll("actif");
								 variable.setAttribute("operation",option);
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/Periode3.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("supprimercorrectionstock"))
							 {
								 request.setAttribute("print","en attente");
								 String date=(String)variable2.getAttribute("date");
								 String type=(String)variable3.getAttribute("type");
								 Double id=Double.parseDouble(request.getParameter("id"));
									 executionoperation=correctionstockservice.delete(id);
									 if(executionoperation==true){
										 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listecorrectionstock",listecorrectionstock);
										 request.setAttribute("type",type);
										 request.setAttribute("date",date);
										 variable.setAttribute("operation","lignecorrectionstock");
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
									 }
									 else {
										 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listecorrectionstock",listecorrectionstock);
										 request.setAttribute("type",type);
										 request.setAttribute("date",date);
										 variable.setAttribute("operation","lignecorrectionstock");
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
									 }
									 
							 }
							 else if(option.equals("imprimercorrectionstock")) {
								 request.setAttribute("print","en attente");
								 String numero="";
								 int taille=correctionstockservice.listefacture("en attente").size();
								 if(taille==0) {
									 numero="C/1";
								 }
								 else {
									 String [] tab=correctionstockservice.listefacture("en attente").get(0).split("/");
									 int y=Integer.parseInt(tab[1])+1;
									 numero="C/"+y+"";
								 }
									 executionoperation=correctionstockservice.update(numero,utilisateur.getId(),"en attente");
												if(executionoperation==true)
												{
													//suprimer factures en attente;
													correctionstockservice.delete1(utilisateur.getId());
													correctionstockservice.delete("en attente",utilisateur.getId());
														//------------
													 listerecapcorrectionstock=correctionstockservice.recapcorrection();
													 request.setAttribute("Titre","Liste des corrections stock" );
													 request.setAttribute("option","listecorrectionstock");
													 request.setAttribute("liste",listerecapcorrectionstock);
													 Listefamille=familleservice.getAll("actif");
													 request.setAttribute("listfam",Listefamille );
													 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
													request.setAttribute("listecontenuautorisation",listecontenuautorisation );
													 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
												}
								}
							 else if(option.equals("detailcorrectionstock"))
							 {
								 request.setAttribute("print","en attente");
								 String numero=request.getParameter("id");
									 listecorrectionstock=correctionstockservice.getAll(numero);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listecorrectionstock);
									request.setAttribute("option",option);
									request.setAttribute("Titre","CORRECTION STOCK N '"+numero+"'  DU '"+listecorrectionstock.get(0).getDate()+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifiercorrectionstock"))
							 {
								 request.setAttribute("print","en attente");
								 Double numeros=Double.parseDouble(request.getParameter("id"));
								 Double quantite=Double.parseDouble(request.getParameter("quantite"));
								 correctionstock=correctionstockservice.getByid(numeros);
								 correctionstock.setUser(utilisateur.getId());
								 correctionstock.setQuantite(quantite);
								 executionoperation=correctionstockservice.update(numeros,correctionstock);
								 String numero=correctionstock.getNumero();
								 listecorrectionstock=correctionstockservice.getAll(numero);
								 variable.setAttribute("operation",option);
								 request.setAttribute("liste",listecorrectionstock);
								request.setAttribute("option","detailcorrectionstock");
								request.setAttribute("Titre","CORRECTION STOCK N '"+numero+"'  DU '"+listecorrectionstock.get(0).getDate()+"'" );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );  
							 }
							 //----------transfert
							 else if(option.equals("transfert"))
							 {
								 request.setAttribute("print","en attente");
									//suprimer factures en attente;
									transfertservice.delete1(utilisateur.getId());
									transfertservice.delete1("en attente",utilisateur.getId());
										//------------
									 listerecaptransfert=transfertservice.getAll1();
									 request.setAttribute("Titre","Liste des transferts" );
									 request.setAttribute("option",option);
									 request.setAttribute("liste",listerecaptransfert);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							 else if(option.equals("transfert1"))
							 {
								 request.setAttribute("print","en attente");
								 executionoperation=true;
								 listeservice=serviceservice.getAll("actif");
								 request.setAttribute("Listeservice",listeservice );
								 request.setAttribute("listerecaptransfert",listerecaptransfert );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("Titre","Lieux d affectation" );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/transfert1.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("supprimertransfert"))
							 {
								 request.setAttribute("print","en attente");
								 String date=(String)variable2.getAttribute("date");
								 int emetteur=(int)variable3.getAttribute("emetteur");
								 int recepteur=(int)variable4.getAttribute("recepteur");
								 Double id=Double.parseDouble(request.getParameter("id"));
									 executionoperation=transfertservice.delete(id);
									 if(executionoperation==true){
										 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listetransfert",listetransfert);
										 request.setAttribute("date",date);
										 request.setAttribute("emetteur",emetteur);
										 variable.setAttribute("operation","lignetransfert");
										 request.setAttribute("recepteur",recepteur);
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
									 }
									 else {
										 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
										 Listearticle=articleservice.getAll();
										 request.setAttribute("executionoperation",true);
										 request.setAttribute("listetransfert",listetransfert);
										 request.setAttribute("date",date);
										 request.setAttribute("emetteur",emetteur);
										 variable.setAttribute("operation","lignetransfert");
										 request.setAttribute("recepteur",recepteur);
										 request.setAttribute("listearticle",Listearticle);
										 Listefamille=familleservice.getAll("actif");
										 request.setAttribute("listfam",Listefamille );
										 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
											request.setAttribute("listecontenuautorisation",listecontenuautorisation );
										 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
									 }
									 
							 }
							 else if(option.equals("imprimertransfert")) {
								 request.setAttribute("print","en attente");
								 String numero="";
								 int taille=transfertservice.listefacture("en attente").size();
								 if(taille==0) {
									 numero="T/1";
								 }
								 else {
									 String [] tab=transfertservice.listefacture("en attente").get(0).split("/");
									 int y=Integer.parseInt(tab[1])+1;
									 numero="T/"+y+"";
								 }
									 executionoperation=transfertservice.update1(utilisateur.getId(),"en attente",numero);
												if(executionoperation==true)
												{
													//suprimer factures en attente;
													transfertservice.delete1(utilisateur.getId());
													transfertservice.delete1("en attente",utilisateur.getId());
														//------------
													 listerecaptransfert=transfertservice.getAll1();
													 request.setAttribute("Titre","Liste des transferts" );
													 request.setAttribute("option","transfert");
													 request.setAttribute("liste",listerecaptransfert);
													 Listefamille=familleservice.getAll("actif");
													 request.setAttribute("listfam",Listefamille );
													 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
													request.setAttribute("listecontenuautorisation",listecontenuautorisation );
													 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
												}
												
								}
							 else if(option.equals("visualisertransfert"))
							 {
								 request.setAttribute("print","en attente");
								 String numero=request.getParameter("id");
									 listetransfert=transfertservice.getAll(numero);
									 variable.setAttribute("operation",option);
									 request.setAttribute("liste",listetransfert);
									request.setAttribute("option",option);
									String service1=serviceservice.getByid(listetransfert.get(0).getEmetteur()).getNom();
									String service2=serviceservice.getByid(listetransfert.get(0).getRecepteur()).getNom();
									request.setAttribute("Titre","DETAIL TRANSFERT '"+numero+"'  DE '"+service1+"' VERS  '"+service2+"' DU '"+listetransfert.get(0).getDate()+"'" );
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
										request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifiertransfert"))
							 {
								 request.setAttribute("print","en attente");
								 String numero=request.getParameter("id");
								 listerecaptransfert=transfertservice.getAll1(numero);
								
								 executionoperation=true;
								 listeservice=serviceservice.getAll("actif");
								 request.setAttribute("Listeservice",listeservice );
								 request.setAttribute("listerecaptransfert",listerecaptransfert );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("Titre","Lieux d affectation" );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/transfert1.jsp" ).forward( request, response ); 
							 }
							 else if(option.equals("modifiertransfert1"))
							 {
								 request.setAttribute("print","en attente");
								 Double numero=Double.parseDouble(request.getParameter("id"));
								 transfert=transfertservice.getByid(numero);
								 Listearticle=articleservice.getAll();
								 executionoperation=true;
								 request.setAttribute("Listearticle",Listearticle );
								 request.setAttribute("transfert",transfert );
								 request.setAttribute("executionoperation",executionoperation );
								 variable.setAttribute("operation",option);
								 request.setAttribute("Titre","Correction transfert" );
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/transfert3.jsp" ).forward( request, response ); 
							 }
							
						}
					}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		variable = request.getSession();
		variable1 = request.getSession();
		variable2 = request.getSession();
		variable3 = request.getSession();
		compte=request.getSession();
		operation = (String)variable.getAttribute("operation");
		
		request.setAttribute("print","en attente");
		request.setAttribute("utilisateur",utilisateur );
		
		
		//--------------Famille--------------
		 if(operation.equals("chargementfamille"))
		{
			File fichier =new File(request.getParameter("fichier"));
			String path=fichier.getAbsolutePath();
			databaseservice.chargementfamille(path);
			 request.setAttribute("option",option);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
			request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

		}
		
		else if(operation.equals("modifierfamille"))
		{
			String name =request.getParameter("name");
			String statut=request.getParameter("statut");
			famille=new setch.beans.famille(utilisateur.getId(),name,statut);
			/*if(familleservice.getByid(name).getId()==matricule)
			{*/
				executionoperation=familleservice.update(matricule, famille);
				if(executionoperation==true)
				{
					Listefamille=familleservice.getAll();
					 request.setAttribute("Titre","Liste des familles" );
					 request.setAttribute("option","famillearticles");
					 request.setAttribute("liste",Listefamille);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				else
				{
					matricule = Integer.parseInt(request.getParameter("id"));
					 famille=familleservice.getByid(matricule);
					 executionoperation=true;
					 request.setAttribute("executionoperation",executionoperation );
					 variable.setAttribute("operation",option);
					 request.setAttribute("option",option );
					 request.setAttribute("famille",famille );
					 request.setAttribute("Titre","Modifier Famille" );
					 request.setAttribute("matricule",matricule );
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/famille.jsp" ).forward( request, response ); 
				}
			
		}
		//---------------------personne-------------------
		else if(operation.equals("chargementprescripteur"))
		{
			File fichier =new File(request.getParameter("fichier"));
			String path=fichier.getAbsolutePath();
			databaseservice.chargementprescripteur(path);
			 request.setAttribute("option",option);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

		}
		
		else if(operation.equals("modifierpersonne"))
		{
			String name =request.getParameter("name");
			String telephone =request.getParameter("telephone");
			String titre=request.getParameter("titre");
			String statut=request.getParameter("statut");
			personne=new setch.beans.personne(name,telephone,statut,titre);
			/*if(matricule==personneservice.getByid(name, titre).getId())
			{*/
				executionoperation=personneservice.update(matricule, personne);
				if(executionoperation==true)
				{
					Listepersonne=personneservice.getAll();
					 request.setAttribute("Titre","Liste des personnes" );
					 request.setAttribute("option","personnes");
					 request.setAttribute("liste",Listepersonne);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				else
				{
					matricule = Integer.parseInt(request.getParameter("id"));
					 personne=personneservice.getByid(matricule);
					 request.setAttribute("executionoperation",executionoperation );
					 variable.setAttribute("operation",option);
					 request.setAttribute("option",option );
					 request.setAttribute("personne",personne );
					 request.setAttribute("Titre","Modifier personne" );
					 request.setAttribute("matricule",matricule );
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/personne.jsp" ).forward( request, response ); 
				}
			
			
		}
		//--------------article--------------
		else if(operation.equals("chargementarticle"))
		{
			
			Part filePart=request.getPart("fichier");
			String fileName=filePart.getSubmittedFileName();
			for(Part part:request.getParts()) {
				part.write(CHEMIN_FICHIERS+fileName);
				String paths=CHEMIN_FICHIERS+fileName;
				databaseservice.chargementarticle(paths);	
				
			}
			//--------
			 request.setAttribute("option",option);
			 Listefamille=familleservice.getAll("actif");
			 request.setAttribute("listfam",Listefamille );
			 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
				request.setAttribute("listecontenuautorisation",listecontenuautorisation );
			 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

		}
			
		//---------------------prix vente-------------------
				else if(operation.equals("chargementprix"))
				{
					Part filePart=request.getPart("fichier");
					String fileName=filePart.getSubmittedFileName();
					for(Part part:request.getParts()) {
						part.write(CHEMIN_FICHIERS+fileName);
						String paths=CHEMIN_FICHIERS+fileName;
					databaseservice.chargementprix(paths);
					}
					 request.setAttribute("option",option);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

				}
				
		
		//---------------------unite vente-------------------
				else if(operation.equals("chargementunite"))
				{
					File fichier =new File(request.getParameter("fichier"));
					String path=fichier.getAbsolutePath();
					databaseservice.chargementunite(path);
					 request.setAttribute("option",option);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

				}

		//---------------------vente-------------------
				else if(operation.equals("vente"))
				{
					String date =request.getParameter("recherche");
					 String date1=date+" 00:00:00";
					 String date2=date+" 23:59:59";
					 listefacture=venteservice.getfacture(date1,date2,"en attente","VN");
					 request.setAttribute("Titre","Factures de vente du jour" );
					 request.setAttribute("option",option);
					 variable.setAttribute("operation",option);
					 request.setAttribute("listefacture",listefacture);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				else if(operation.equals("ventecredit"))
				{
					String date =request.getParameter("recherche");
					 String date1=date+" 00:00:00";
					 String date2=date+" 23:59:59";
					 listefacture=venteservice.getfacture(date1,date2,"en attente","CN");
					 request.setAttribute("Titre","Factures de vente à credit du jour" );
					 request.setAttribute("option",operation);
					 variable.setAttribute("operation",option);
					 request.setAttribute("listefacture",listefacture);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				 else if(operation.equals("ventespecifique"))
				 {
					 String date =request.getParameter("daterecherche");
					 String date1=date+" 00:00:00";
					 String date2=date+" 23:59:59";
					 listefacture=venteservice.getfacture(date1,date2,"en attente","VS");
					 request.setAttribute("Titre","Factures de vente specifique du jour" );
					 request.setAttribute("option",option);
					 variable.setAttribute("operation",option);
					 request.setAttribute("listefacture",listefacture);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
				 }
				 else if(operation.equals("retourventenormale"))
				 {
					 String date =request.getParameter("daterecherche");
					 
					 String date1=date+" 00:00:00";
					 String date2=date+" 23:59:59";
					 listefacture=venteservice.getfacture(date1,date2,"en attente","RN");
					 request.setAttribute("Titre","Factures de vente specifique du jour" );
					 request.setAttribute("option",option);
					 variable.setAttribute("operation",option);
					 request.setAttribute("listefacture",listefacture);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
				 }
				 else if(operation.equals("retourventespecifique"))
				 {
					 String date =request.getParameter("daterecherche");
					 String date1=date+" 00:00:00";
					 String date2=date+" 23:59:59";
					 listefacture=venteservice.getfacture(date1,date2,"en attente","RS");
					 request.setAttribute("Titre","Factures de vente specifique du jour" );
					 request.setAttribute("option",option);
					 variable.setAttribute("operation",option);
					 request.setAttribute("listefacture",listefacture);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
				 }
				else if(operation.equals("creationvente")|| operation.equals("creationventecredit")|| operation.equals("creationretourventespecifiquebiss")|| operation.equals("creationretourventespecifiquebis"))
				{
					variable.setAttribute("operation",operation);
					String choix =request.getParameter("button");
					if(choix.equals("Enregistrement"))
					{
						String patient =request.getParameter("patient");
						personne=personneservice.getByid(patient);
						int article =Integer.parseInt(request.getParameter("article"));
						Listeprixvente=prixventeservice.getAll(article);
						int prescripteur =Integer.parseInt(request.getParameter("prescripteur"));
						Double quantite =Double.parseDouble(request.getParameter("quantite"));
						setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",personne.getId(),prescripteur,article, quantite,Listeprixvente.get(0).getId(),"en attente");
						if(venteservice.getByfacture(article, utilisateur.getId(), "en attente").size()>0)
						{
							reduc=0.0;
							executionoperation=false;
							listevente=venteservice.getAll("en attente",utilisateur.getId());
							 listeprescripteur=personneservice.getAll("prescripteur","actif");
							 listepatient=personneservice.getAll("patient","actif");
							 Listearticle=articleservice.getAll1("actif");
							 request.setAttribute("executionoperation",executionoperation );
							 request.setAttribute("listevente",listevente);
							 request.setAttribute("listeprescripteur",listeprescripteur);
							 request.setAttribute("listepatient",listepatient);
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							request.setAttribute("option",operation);
							 request.setAttribute("reduction",reduc);
							 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
						}
						else
						{
							executionoperation=venteservice.add(vente);
							if(executionoperation==true)
							{
								reduc=0.0;
								listevente=venteservice.getAll("en attente",utilisateur.getId());
								// listeprescripteur=personneservice.getAll("prescripteur","actif");
								listeprescripteur=new  ArrayList<setch.beans.personne>();
								listeprescripteur.add(personneservice.getByid(prescripteur));
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								request.setAttribute("option",operation);
								 request.setAttribute("reduction",reduc);
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							}
							else
							{
								 reduc=0.0;
								listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("reduction",reduction);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								request.setAttribute("option",operation);
								 request.setAttribute("reduction",reduc);
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							}
						}
					}
					
				}
				else if(operation.equals("creationventespecifique")|| operation.equals("creationretourventespecifiquebisss")||operation.equals("creationretourventespecifiquebissss"))
				{
					variable.setAttribute("operation",operation);
					String choi =request.getParameter("button");
					if(choi.equals("Enregistrement"))
					{
						String patient =request.getParameter("patient");
						personne=personneservice.getByid(patient);
						int article =Integer.parseInt(request.getParameter("article"));
						Listeprixvente=prixventeservice.getAll(article);
						int prescripteur =Integer.parseInt(request.getParameter("prescripteur"));
						Double quantite =Double.parseDouble(request.getParameter("quantite"));
						setch.beans.vente vente=new setch.beans.vente(utilisateur.getId(),"en attente","2000-01-01 00:00:00",personne.getId(),prescripteur,article, quantite,Listeprixvente.get(0).getId(),"en attente");
						if(venteservice.getByfacture(article, utilisateur.getId(), "en attente").size()>0)
						{
							reduc=Double.parseDouble(request.getParameter("reduction"));
							executionoperation=false;
							listevente=venteservice.getAll("en attente",utilisateur.getId());
							 listeprescripteur=personneservice.getAll("prescripteur","actif");
							 listepatient=personneservice.getAll("patient","actif");
							 Listearticle=articleservice.getAll1("actif");
							 request.setAttribute("executionoperation",executionoperation );
							 request.setAttribute("listevente",listevente);
							 request.setAttribute("listeprescripteur",listeprescripteur);
							 request.setAttribute("listepatient",listepatient);
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							request.setAttribute("option",operation);
							 request.setAttribute("reduction",reduc);
							 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
						}
						else
						{
							executionoperation=venteservice.add(vente);
							if(executionoperation==true)
							{
								reduc=Double.parseDouble(request.getParameter("reduction"));
								listevente=venteservice.getAll("en attente",utilisateur.getId());
								// listeprescripteur=personneservice.getAll("prescripteur","actif");
								listeprescripteur=new  ArrayList<setch.beans.personne>();
								listeprescripteur.add(personneservice.getByid(prescripteur));
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								request.setAttribute("option",operation);
								 request.setAttribute("reduction",reduc);
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							}
							else
							{
								reduc=Double.parseDouble(request.getParameter("reduction"));
								listevente=venteservice.getAll("en attente",utilisateur.getId());
								 listeprescripteur=personneservice.getAll("prescripteur","actif");
								 listepatient=personneservice.getAll("patient","actif");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",executionoperation );
								 request.setAttribute("listevente",listevente);
								 request.setAttribute("listeprescripteur",listeprescripteur);
								 request.setAttribute("listepatient",listepatient);
								 request.setAttribute("listearticle",Listearticle);
								 request.setAttribute("reduction",reduction);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								request.setAttribute("option",operation);
								 request.setAttribute("reduction",reduc);
								 this.getServletContext().getRequestDispatcher( "/vues/vente.jsp" ).forward( request, response );
							}
						}
					}
					else if(choi.equals("impression"))
					{
						String facture="";
						String bl="";
						String date="";
						
						if(operation.equals("creationventespecifique"))
						{
						listeentreprise=entrepriseservice.getAll();;
						listefacture=venteservice.getfacture("en attente","VS");
						 listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
						if(listefacture.size()<=0)
						{
							facture="1/VS/"+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
						}
						else
						{
							String[] b =listefacture.get(0).split("/");
							int annee=Integer.parseInt(b[3]);
							if(LocalDate.now().getYear()==annee)
							{
								int numerotateur=Integer.parseInt(b[0])+1;
								facture=numerotateur+"/VS/"+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
							}
							else
							{
								facture="1/VS/"+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
							}
						}
						//-----------numero bl vente
						if(listelivraisonvente.size()<=0)
						{
							bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
						}
						else
						{
							String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
							int annee=Integer.parseInt(b[3]);
							if(LocalDate.now().getYear()==annee)
							{
								int numerotateur=Integer.parseInt(b[0])+1;
								bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
							}
							else
							{
								bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
							}
						}
						date= LocalDateTime.now().toString();
						executionoperation=venteservice.update(facture,date,"en attente",utilisateur.getId(),bl);
						if(executionoperation==true)
						{
							/*mettre dans livraison vente*/
							listevente=venteservice.getByfacture(facture);
							for(int i=0;i<listevente.size();i++) {
								livraisonvente=new setch.beans.livraisonvente(listevente.get(i).getIduser(),date,bl,listevente.get(i).getIdpatient(),listevente.get(i).getIdarticle(),listevente.get(i).getQuantite(),listevente.get(i).getIdprixvente(),"cloture");
								livraisonventeservice.add(livraisonvente);
							}	
							//enregistrer reduction
							reductionservice.update("en attente",facture, utilisateur.getId());
							listevente=venteservice.getByfacture(facture);
							setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
							 request.setAttribute("listevente",facturevente);
							 request.setAttribute("reduction",reduction);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 request.setAttribute("valeur",operation);
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/facturevente2.jsp" ).forward( request, response );
						}
					}

				
				else if(operation.equals("creationretourventespecifiquebisss")||operation.equals("creationretourventespecifiquebissss"))
				{
					numerofacture = (String)variable2.getAttribute("numerofacture");
					listevente1=venteservice.getByfacture1(numerofacture);
						listeentreprise=entrepriseservice.getAll();
						listefacture=venteservice.getfacture("en attente","RS");
						if(listefacture.size()<=0)
						{
							facture="1/RS/"+listevente1.get(0).getId()+"/"+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
						}
						else
						{
							String[] b =listefacture.get(0).split("/");
							int annee=Integer.parseInt(b[4]);
							if(LocalDate.now().getYear()==annee)
							{
								int numerotateur=Integer.parseInt(b[0])+1;
								facture=numerotateur+"/RN/"+listevente1.get(0).getId()+"/"+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
							}
							else
							{
								facture="1/RS/"+listevente1.get(0).getId()+listeentreprise.get(0).getName()+"/"+LocalDate.now().getYear();
							}
						}
						listelivraisonvente=livraisonventeservice.getlastnumero("en attente");
						 if(listelivraisonvente.size()<=0)
							{
								bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
							}
							else
							{
								
									String[] b =listelivraisonvente.get(0).getNumerobl().split("/");
									int annee=Integer.parseInt(b[3]);
									if(LocalDate.now().getYear()==annee)
									{
										int numerotateur=Integer.parseInt(b[0])+1;
										bl=numerotateur+"/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
									}
									else
									{
										bl="1/BL/"+entreprise.getSigle()+"/"+LocalDate.now().getYear();
									}
								
								
							}
						executionoperation=venteservice.update(facture, LocalDateTime.now().toString(),"en attente",utilisateur.getId(),bl);
						if(executionoperation==true)
						{
							reductionservice.update("en attente",facture, utilisateur.getId());
							reduction2=reductionservice.getByfacture(facture);
							listevente=venteservice.getByfacture(facture);
							setch.beans.facturevente facturevente=new setch.beans.facturevente(listeentreprise.get(0), listevente);
							request.setAttribute("reductionretour",reduction);
							request.setAttribute("reductionnouveau",reduction2);
							 request.setAttribute("listevente",facturevente);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/facturevente3.jsp" ).forward( request, response );
						}
				}
			}
		}
				else if(operation.equals("etatvente"))
				{
					String dateinitiale =request.getParameter("dateinitiale")+" 00:00:00";;
					String datefinale =request.getParameter("datefinale")+" 23:59:59";
					variable2.setAttribute("date1",dateinitiale);
					variable3.setAttribute("date2",datefinale);
					listevente=venteservice.getfacture1(dateinitiale,datefinale,"en attente");
					listestatistiquevente1=statistiqueventeservice.fonction(listevente);
					 listestatistiquevente=statistiqueventeservice.fonction1(listestatistiquevente1);
					 request.setAttribute("option",operation);
					 request.setAttribute("listefacture",listestatistiquevente);
					 request.setAttribute("Titre","Vente du '"+dateinitiale+"' au '"+datefinale+"'" );
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				else if(operation.equals("etatvente1"))
				{
					typeetat=(int)variable1.getAttribute("typeetatvente");
					String type=familleservice.getByid(typeetat).getNom();
					String dateinitiale =request.getParameter("dateinitiale")+" 00:00:00";;
					String datefinale =request.getParameter("datefinale")+" 23:59:59";
					variable2.setAttribute("date1",dateinitiale);
					variable3.setAttribute("date2",datefinale);
					listevente=venteservice.getfacture1(dateinitiale,datefinale,"en attente");
					listevente1=venteservice.getfacture1(listevente, typeetat);
					listestatistiquevente1=statistiqueventeservice.fonction(listevente1);
					 listestatistiquevente=statistiqueventeservice.fonction1(listestatistiquevente1);
					 request.setAttribute("option",operation);
					 request.setAttribute("listefacture",listestatistiquevente);
					 request.setAttribute("Titre","Vente du '" + type + "'  du '"+dateinitiale+"' au '"+datefinale+"'" );
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
				else if(operation.equals("etatcommission"))
				{
					String dateinitiale =request.getParameter("dateinitiale")+" 00:00:00";;
					String datefinale =request.getParameter("datefinale")+" 23:59:59";
					listevente=venteservice.getfacture1(dateinitiale,datefinale,"en attente");
					listestatistiquevente1=statistiqueventeservice.fonction(listevente);
					 listestatistiquevente=statistiqueventeservice.fonction2(listestatistiquevente1);
					 request.setAttribute("option",operation);
					 request.setAttribute("listefacture",listestatistiquevente);
					 request.setAttribute("Titre","commissions du '"+dateinitiale+"' au '"+datefinale+"'" );
					 request.setAttribute("periode1",dateinitiale );
					 request.setAttribute("periode2",datefinale);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
					request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				}
		//---------------------pourcentage commissions-------------------
				else if(operation.equals("chargementcommission"))
				{
					File fichier =new File(request.getParameter("fichier"));
					String path=fichier.getAbsolutePath();
					databaseservice.chargementcommission(path);
					 request.setAttribute("option",option);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

				}
				else if(operation.equals("creationpourcentage"))
				{
					String date =request.getParameter("date");
					int famille = Integer.parseInt(request.getParameter("famille"));
					double pourcentage = Double.parseDouble(request.getParameter("pourcentage"));
					if(commissionservice.getByid(famille, date)==null)
					{
						commission=new setch.beans.commissions(utilisateur.getAutorisation(), famille, date, pourcentage);
						executionoperation=commissionservice.add(commission);
						if(executionoperation==true)
						{
							 listecommission=commissionservice.getAll();
							 request.setAttribute("Titre","Liste des pourcentages commissions" );
							 request.setAttribute("option","pourcentagecommission");
							 request.setAttribute("liste",listecommission);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
						}
						else
						{
							commission=new setch.beans.commissions();
							 commission.setDate("");
							 commission.setPourcentage(0);
							 commission.setFamille(0);
							 request.setAttribute("commission",commission);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("Titre","Creation pourcentage" );
							 request.setAttribute("executionoperation",executionoperation );
							 request.setAttribute("liste",Listefamille);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 
						}
					}
					else
					{
						commission=new setch.beans.commissions();
						 commission.setDate("");
						 commission.setPourcentage(0);
						 commission.setFamille(0);
						 request.setAttribute("commission",commission);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("Titre","Creation pourcentage" );
						 request.setAttribute("executionoperation",executionoperation );
						 request.setAttribute("liste",Listefamille);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 	
					}
					
				}
				else if(operation.equals("modifierpourcentage"))
				{
					int valeur= (int)variable1.getAttribute("matricule");
					commission=commissionservice.getByid(valeur);
					String date =request.getParameter("date");
					int famille = Integer.parseInt(request.getParameter("famille"));
					double pourcentage = Double.parseDouble(request.getParameter("pourcentage"));
					if(famille==commission.getFamille())
					{
						commission=new setch.beans.commissions(utilisateur.getAutorisation(), famille, date, pourcentage);
						executionoperation=commissionservice.update(valeur, commission);
						if(executionoperation==true)
						{
							 listecommission=commissionservice.getAll();
							 request.setAttribute("Titre","Liste des pourcentages commissions" );
							 request.setAttribute("option","pourcentagecommission");
							 request.setAttribute("liste",listecommission);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
						}
						else
						{
							request.setAttribute("commission",commission);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("Titre","Creation pourcentage" );
							 request.setAttribute("executionoperation",executionoperation );
							 request.setAttribute("liste",Listefamille);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 
						}
					}
					else
					{
						executionoperation=false;
						request.setAttribute("commission",commission);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("Titre","Creation pourcentage" );
						 request.setAttribute("executionoperation",executionoperation );
						 request.setAttribute("liste",Listefamille);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/pourcentage.jsp" ).forward( request, response ); 
					}
					
				}
		
		//---------------------facturation-------------------
				
				else if(operation.equals("modifierfacturation"))
				{
					
				}
				else if(operation.equals("chargementoffre"))
				{
					Part filePart=request.getPart("fichier");
					String fileName=filePart.getSubmittedFileName();
					for(Part part:request.getParts()) {
						part.write(CHEMIN_FICHIERS+fileName);
						String paths=CHEMIN_FICHIERS+fileName;
					databaseservice.chargementoffre(paths);
					}
					 request.setAttribute("option",option);
					 Listefamille=familleservice.getAll("actif");
					 request.setAttribute("listfam",Listefamille );
					 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
					 this.getServletContext().getRequestDispatcher( "/vues/dashboard.jsp" ).forward( request, response ); 

				}
				
		//---------------------inventaire

		//-------------besoin
				
				
		//------------payement credit
				else if(operation.equals("payementcredit"))
				{
					String facture =request.getParameter("numerofacturecredit");
					Double totalfacture=Double.parseDouble(request.getParameter("montantfacture"));
					Double totalversement=paymentcreditservice.totalpayment(facture);
					Double montant=Double.parseDouble(request.getParameter("versement"));
					if((totalversement+montant)<=totalfacture) {
						payementcredit=new paymentcredit(utilisateur.getId(),LocalDateTime.now().toString(),facture,montant);
						executionoperation=paymentcreditservice.add(payementcredit);
						if(executionoperation==true) {
							String date1=LocalDate.now().toString()+" 00:00:00";
							 String date2=LocalDate.now().toString()+" 23:59:59";
							 listefacture=venteservice.getfacture(date1,date2,"en attente","CN");
							 request.setAttribute("Titre","Factures de vente à credit du jour" );
							 request.setAttribute("option","ventecredit");
							 variable.setAttribute("operation","ventecredit");
							 request.setAttribute("listefacture",listefacture);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							
						}
						else {
							executionoperation=false;
							 payementcredit=new paymentcredit();
							 payementcredit.setFacture(facture);
							 request.setAttribute("executionoperation",executionoperation );
							 variable.setAttribute("operation",option);
							 request.setAttribute("option",option );
							 request.setAttribute("Titre","Reglement credit" );
							 request.setAttribute("payement",payementcredit );
							 request.setAttribute("total",totalfacture );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/paymentcredit.jsp" ).forward( request, response ); 
							
						}
					}
					else {
						executionoperation=false;
						 payementcredit=new paymentcredit();
						 payementcredit.setFacture(facture);
						 request.setAttribute("executionoperation",executionoperation );
						 variable.setAttribute("operation",option);
						 request.setAttribute("option",option );
						 request.setAttribute("Titre","Reglement credit" );
						 request.setAttribute("payement",payementcredit );
						 request.setAttribute("total",totalfacture );
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/paymentcredit.jsp" ).forward( request, response ); 
						
					}
					
				}
				else if(operation.equals("modifierpayementcredit"))
				{
					int id=Integer.parseInt(request.getParameter("idfacturecredit"));
					String facture =request.getParameter("numerofacturecredit");
					Double totalfacture=Double.parseDouble(request.getParameter("montantfacture"));
					Double totalversement=paymentcreditservice.totalpayment(facture);
					Double montant=Double.parseDouble(request.getParameter("versement"));
					if((totalversement+montant)<=totalfacture) {
						payementcredit=paymentcreditservice.getByid(id);
						payementcredit.setMontant(montant);
						executionoperation=paymentcreditservice.update(id,payementcredit);
						if(executionoperation==true) {
							String date1=LocalDate.now().toString()+" 00:00:00";
							 String date2=LocalDate.now().toString()+" 23:59:59";
							 listefacture=venteservice.getfacture(date1,date2,"en attente","CN");
							 request.setAttribute("Titre","Factures de vente à credit du jour" );
							 request.setAttribute("option","ventecredit");
							 variable.setAttribute("operation","ventecredit");
							 request.setAttribute("listefacture",listefacture);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
							
						}
						else {
							executionoperation=false;
							 payementcredit=new paymentcredit();
							 payementcredit.setFacture(facture);
							 request.setAttribute("executionoperation",executionoperation );
							 variable.setAttribute("operation",option);
							 request.setAttribute("option",option );
							 request.setAttribute("Titre","Reglement credit" );
							 request.setAttribute("payement",payementcredit );
							 request.setAttribute("total",totalfacture );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/paymentcredit.jsp" ).forward( request, response ); 
							
						}
					}
					
				}
		//----------------service
				else if(operation.equals("creationservice"))
				{
					String name =request.getParameter("name");
					String statut=request.getParameter("statut");
					service=new setch.beans.service(utilisateur.getId(),name,statut);
					if(serviceservice.getByid(name)==null)
					{
						executionoperation=serviceservice.add(service);
						if(executionoperation==true)
						{
							//------------------------
							listeservice=serviceservice.getAll();
							 request.setAttribute("Titre","Liste des services" );
							 request.setAttribute("option","service");
							 request.setAttribute("liste",listeservice);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
						}
						else
						{
							 service=new setch.beans.service();
							 service.setNom("");
							 service.setStatut("");
							 request.setAttribute("executionoperation",executionoperation );
							 variable.setAttribute("operation",option);
							 request.setAttribute("option",option );
							 request.setAttribute("Titre","Creation service" );
							 request.setAttribute("famille",service );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/service.jsp" ).forward( request, response ); 
						}
					}
					else
					{
						executionoperation=false;
						 service=new setch.beans.service();
						 service.setNom("");
						 service.setStatut("");
						 request.setAttribute("executionoperation",executionoperation );
						 variable.setAttribute("operation",option);
						 request.setAttribute("option",option );
						 request.setAttribute("Titre","Creation service" );
						 request.setAttribute("famille",service );
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/service.jsp" ).forward( request, response ); 
					}
					
				}
				else if(operation.equals("modifierservice"))
				{
					String name =request.getParameter("name");
					String statut=request.getParameter("statut");
					service=new setch.beans.service(utilisateur.getId(),name,statut);
					/*if(familleservice.getByid(name).getId()==matricule)
					{*/
						executionoperation=serviceservice.update(matricule, service);
						if(executionoperation==true)
						{
							listeservice=serviceservice.getAll();
							 request.setAttribute("Titre","Liste des services" );
							 request.setAttribute("option","service");
							 request.setAttribute("liste",listeservice);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
						}
						else
						{
							matricule = Integer.parseInt(request.getParameter("id"));
							 service=serviceservice.getByid(matricule);
							 executionoperation=true;
							 request.setAttribute("executionoperation",executionoperation );
							 variable.setAttribute("operation",option);
							 request.setAttribute("option",option );
							 request.setAttribute("famille",service );
							 request.setAttribute("Titre","Modifier service" );
							 request.setAttribute("matricule",matricule );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/service.jsp" ).forward( request, response ); 
						}
					/*}
					else
					{
						matricule = Integer.parseInt(request.getParameter("id"));
						 famille=familleservice.getByid(matricule);
						 executionoperation=true;
						 request.setAttribute("executionoperation",executionoperation );
						 variable.setAttribute("operation",option);
						 request.setAttribute("option",option );
						 request.setAttribute("famille",famille );
						 request.setAttribute("Titre","Modifier Famille" );
						 request.setAttribute("matricule",matricule );
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
						request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/famille.jsp" ).forward( request, response ); 
					}*/
					
				}
		//----------------correction stock
				 else if(operation.equals("correctionstock"))
				 {
							//------------
						 String type=request.getParameter("type");
						 String date=request.getParameter("dateinitiale");
						 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
						 Listearticle=articleservice.getAll();
						 request.setAttribute("executionoperation",true);
						 request.setAttribute("listecorrectionstock",listecorrectionstock);
						 request.setAttribute("type",type);
						 request.setAttribute("date",date);
						 variable2.setAttribute("date",date);
						 variable3.setAttribute("type",type);
						 variable.setAttribute("operation","lignecorrectionstock");
						 request.setAttribute("listearticle",Listearticle);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
				 }
				 else if(operation.equals("lignecorrectionstock"))
				 {
					 String date=(String)variable2.getAttribute("date");
					 String type=(String)variable3.getAttribute("type");
					
					 int idarticle=Integer.parseInt(request.getParameter("article"));
					 Double quantite=Double.parseDouble(request.getParameter("quantite"));
					 correctionstock=new setch.beans.correctionstock(utilisateur.getId(),date,"en attente",idarticle,quantite);
					 int a=correctionstockservice.getByid1(idarticle,"en attente",utilisateur.getId());
					 if(a==0) {
						 executionoperation=correctionstockservice.add(correctionstock);
						 if(executionoperation==true){
							 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
							 Listearticle=articleservice.getAll();
							 request.setAttribute("executionoperation",true);
							 request.setAttribute("listecorrectionstock",listecorrectionstock);
							 request.setAttribute("type",type);
							 request.setAttribute("date",date);
							 variable.setAttribute("operation","lignecorrectionstock");
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
						 }
						 else {
							 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
							 Listearticle=articleservice.getAll();
							 request.setAttribute("executionoperation",true);
							 request.setAttribute("listecorrectionstock",listecorrectionstock);
							 request.setAttribute("type",type);
							 request.setAttribute("date",date);
							 variable.setAttribute("operation","lignecorrectionstock");
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
						 }
					 }
					 else {
						 listecorrectionstock=correctionstockservice.getAll1("en attente",utilisateur.getId());
						 Listearticle=articleservice.getAll();
						 request.setAttribute("executionoperation",true);
						 request.setAttribute("listecorrectionstock",listecorrectionstock);
						 request.setAttribute("type",type);
						 request.setAttribute("date",date);
						 variable.setAttribute("operation","lignecorrectionstock");
						 request.setAttribute("listearticle",Listearticle);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/correctionstock.jsp" ).forward( request, response );
					 }
	 
				 }
		//-------livraison
				
				
		//----------------livraison vente
				 else if(operation.equals("creationlivraisonvente1")) {
					 livraisonventeservice.delete(utilisateur.getId(),"en attente");
					 request.setAttribute("print","en attente");
						//------------
					String fournisseur1=request.getParameter("client");
					String[] tab=fournisseur1.split("-");
					if(tab.length==2) {
						 int fournisseur=Integer.parseInt(tab[0]);
						 personne=personneservice.getByid(fournisseur);
						 String date=request.getParameter("datelivraisonvente");
						 listelivraisonvente=livraisonventeservice.getAll1("en attente",utilisateur.getId(),date,fournisseur,"en attente");
						 variable2.setAttribute("client",fournisseur);
						 variable4.setAttribute("date",date);
						 Listearticle=articleservice.getAll1("actif");;
						 request.setAttribute("executionoperation",executionoperation );
						 variable.setAttribute("operation","creationlivraisonvente2");
						 request.setAttribute("listelivraisonvente",listelivraisonvente);
						 request.setAttribute("client",personne.getNom());
						 request.setAttribute("date",date);
						request.setAttribute("option",option);
						 request.setAttribute("listearticle",Listearticle);
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente2.jsp" ).forward( request, response ); 
					}
					else {
						
					}
					
				 }
				 else if(operation.equals("creationlivraisonvente2"))
				 {
						 String date=(String)variable4.getAttribute("date");
						 int client=(int)variable2.getAttribute("client");
						 String clients=personneservice.getByid(client).getNom();
						 int article=Integer.parseInt(request.getParameter("article"));
						 Double quantite=Double.parseDouble(request.getParameter("quantite"));
						 int prix=prixventeservice.getAll(article).get(0).getId();
						 livraisonvente=new setch.beans.livraisonvente(utilisateur.getId(),date,"en attente",client,article,quantite,prix,"en attente");
						 int a=livraisonventeservice.getByid1(article,"en attente",utilisateur.getId());
						
						 if(a==0) {
						
							 executionoperation=livraisonventeservice.add(livraisonvente);
							 if(executionoperation==true){
								 listelivraisonvente=livraisonventeservice.getAll1("en attente",utilisateur.getId(),date,client,"en attente");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",true);
								 request.setAttribute("listelivraisonvente",listelivraisonvente);
								 request.setAttribute("date",date);
								 variable.setAttribute("operation","creationlivraisonvente2");
								 request.setAttribute("client",clients);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente2.jsp" ).forward( request, response );
							 }
							 else  {
								 listelivraisonvente=livraisonventeservice.getAll1("en attente",utilisateur.getId(),date,client,"en attente");
								 Listearticle=articleservice.getAll1("actif");
								 request.setAttribute("executionoperation",true);
								 request.setAttribute("listelivraisonvente",listelivraisonvente);
								 request.setAttribute("date",date);
								 variable.setAttribute("operation","creationlivraisonvente2");
								 request.setAttribute("client",clients);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente2.jsp" ).forward( request, response );
							 } 
						 }
						 else {
							 listelivraisonvente=livraisonventeservice.getAll1("en attente",utilisateur.getId(),date,client,"en attente");
							 Listearticle=articleservice.getAll1("actif");
							 request.setAttribute("executionoperation",true);
							 request.setAttribute("listelivraisonvente",listelivraisonvente);
							 request.setAttribute("date",date);
							 variable.setAttribute("operation","creationlivraisonvente2");
							 request.setAttribute("client",clients);
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/livraisonvente2.jsp" ).forward( request, response );
						 }	
				 }
		
		//---------transfert----
				 else if(operation.equals("transfert1"))
				 {
							//------------
						 int emetteur=Integer.parseInt(request.getParameter("emetteur"));
						 int recepteur=Integer.parseInt(request.getParameter("recepteur"));
						 String date=request.getParameter("datetransfert");
						 if(emetteur!=recepteur && date!="" ) {
							 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
							 Listearticle=articleservice.getAll();
							 request.setAttribute("executionoperation",true);
							 request.setAttribute("listetransfert",listetransfert);
							 request.setAttribute("date",date);
							 request.setAttribute("emetteur",emetteur);
							 variable.setAttribute("operation","lignetransfert");
							 request.setAttribute("recepteur",recepteur);
							 variable2.setAttribute("date",date);
							 variable3.setAttribute("emetteur",emetteur);
							 variable4.setAttribute("recepteur",recepteur);
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
						 }
						 else {
							 executionoperation=true;
							 listeservice=serviceservice.getAll("actif");
							 request.setAttribute("Listeservice",listeservice );
							 request.setAttribute("listerecaptransfert",listerecaptransfert );
							 request.setAttribute("executionoperation",executionoperation );
							 variable.setAttribute("operation",option);
							 request.setAttribute("Titre","Lieux d affectation" );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/transfert1.jsp" ).forward( request, response );
						 } 
				 }
				 else if(operation.equals("lignetransfert"))
				 {
						 String date=(String)variable2.getAttribute("date");
						 int emetteur=(int)variable3.getAttribute("emetteur");
						 int recepteur=(int)variable4.getAttribute("recepteur");
						 int idarticle=Integer.parseInt(request.getParameter("article"));
						 Double quantite=Double.parseDouble(request.getParameter("quantite"));
						 transfert=new setch.beans.transfert(utilisateur.getId(),date,"en attente",emetteur,recepteur,idarticle,quantite);
						 int a=transfertservice.getByid1(idarticle,"en attente",utilisateur.getId());
						
						 if(a==0) {
						
							 executionoperation=transfertservice.add(transfert);
							 if(executionoperation==true){
								 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
								 Listearticle=articleservice.getAll();
								 request.setAttribute("executionoperation",true);
								 request.setAttribute("listetransfert",listetransfert);
								 request.setAttribute("date",date);
								 request.setAttribute("emetteur",emetteur);
								 variable.setAttribute("operation","lignetransfert");
								 request.setAttribute("recepteur",recepteur);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
							 }
							 else  {
								 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
								 Listearticle=articleservice.getAll();
								 request.setAttribute("executionoperation",true);
								 request.setAttribute("listetransfert",listetransfert);
								 request.setAttribute("date",date);
								 request.setAttribute("emetteur",emetteur);
								 variable.setAttribute("operation","lignetransfert");
								 request.setAttribute("recepteur",recepteur);
								 request.setAttribute("listearticle",Listearticle);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
							 } 
						 }
						 else {
							 listetransfert=transfertservice.getAll1("en attente",utilisateur.getId());
							 Listearticle=articleservice.getAll();
							 request.setAttribute("executionoperation",true);
							 request.setAttribute("listetransfert",listetransfert);
							 request.setAttribute("date",date);
							 request.setAttribute("emetteur",emetteur);
							 variable.setAttribute("operation","lignetransfert");
							 request.setAttribute("recepteur",recepteur);
							 request.setAttribute("listearticle",Listearticle);
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/transfert2.jsp" ).forward( request, response );
						 }	
				 }
				 else if(operation.equals("modifiertransfert"))
				 {
							//------------
						 int emetteur=Integer.parseInt(request.getParameter("emetteur"));
						 int recepteur=Integer.parseInt(request.getParameter("recepteur"));
						 String date=request.getParameter("datetransfert");
						 String numero=request.getParameter("numero");
						 if(emetteur!=recepteur && date!="" ) {
							 transfert=new setch.beans.transfert();
							 transfert.setNumero(numero);
							 transfert.setDate(date);
							 transfert.setRecepteur(recepteur);
							 transfert.setEmetteur(emetteur);
							 executionoperation=transfertservice.update1(transfert);
							 if(executionoperation==true) {
								 transfertservice.delete1(utilisateur.getId());
									transfertservice.delete1("en attente",utilisateur.getId());
										//------------
									 listerecaptransfert=transfertservice.getAll1();
									 request.setAttribute("Titre","Liste des transferts" );
									 request.setAttribute("option","transfert");
									 request.setAttribute("liste",listerecaptransfert);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							 else {
								 transfertservice.delete1(utilisateur.getId());
									transfertservice.delete1("en attente",utilisateur.getId());
										//------------
									 listerecaptransfert=transfertservice.getAll1();
									 request.setAttribute("Titre","Liste des transferts" );
									 request.setAttribute("option","transfert");
									 request.setAttribute("liste",listerecaptransfert);
									 Listefamille=familleservice.getAll("actif");
									 request.setAttribute("listfam",Listefamille );
									 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
									request.setAttribute("listecontenuautorisation",listecontenuautorisation );
									 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
							 }
							
						 }
						 else {
							 transfertservice.delete1(utilisateur.getId());
								transfertservice.delete1("en attente",utilisateur.getId());
									//------------
								 listerecaptransfert=transfertservice.getAll1();
								 request.setAttribute("Titre","Liste des transferts" );
								 request.setAttribute("option","transfert");
								 request.setAttribute("liste",listerecaptransfert);
								 Listefamille=familleservice.getAll("actif");
								 request.setAttribute("listfam",Listefamille );
								 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
								 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response );
						 } 
				 }
				 else if(operation.equals("modifiertransfert1"))
				 {
							//------------
						 Double numeros=Double.parseDouble(request.getParameter("numero"));
						 Double quantite=Double.parseDouble(request.getParameter("quantite"));
						 transfert=transfertservice.getByid(numeros);
						 transfert.setUser(utilisateur.getId());
						 transfert.setQuantite(quantite);
						 executionoperation=transfertservice.update1(numeros, transfert);
						 String numero=transfert.getNumero();
						 listetransfert=transfertservice.getAll(numero);
						 variable.setAttribute("operation",option);
						 request.setAttribute("liste",listetransfert);
						request.setAttribute("option","visualisertransfert");
						String service1=serviceservice.getByid(listetransfert.get(0).getEmetteur()).getNom();
						String service2=serviceservice.getByid(listetransfert.get(0).getRecepteur()).getNom();
						request.setAttribute("Titre","DETAIL TRANSFERT '"+numero+"'  DE '"+service1+"' VERS  '"+service2+"' DU '"+listetransfert.get(0).getDate()+"'" );
						 Listefamille=familleservice.getAll("actif");
						 request.setAttribute("listfam",Listefamille );
						 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
							request.setAttribute("listecontenuautorisation",listecontenuautorisation );
						 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
				 }
		//------------------besoin
				 else if(option.equals("modifierbesoin"))
				 {
					 request.setAttribute("print","en attente");
					 Double id=Double.parseDouble(request.getParameter("idbesoin"));
					 double prix=Double.parseDouble(request.getParameter("quantitebesoin"));
					 String numero=request.getParameter("numerobesoin");
					
					 besoin=besoinservice.getByid(id);
					 besoin.setUser(utilisateur.getId());
					 besoin.setQuantite(prix);
					 executionoperation=besoinservice.update(id,besoin);
					 if(executionoperation==true) {
						 listebesoin=besoinservice.getAll(numero);
						 String date=besoin.getDate();
						 String famille=familleservice.getByid(articleservice.getByid(besoin.getIdarticle()).getIdfamille()).getNom();
						 variable1.setAttribute("numero",numero);
						 variable3.setAttribute("date",date);
						 variable2.setAttribute("famille",famille);
							
							 int t=besoinservice.getAll(numero, "en attente").size();
							 variable.setAttribute("operation",option);
							 request.setAttribute("liste",listebesoin);
							request.setAttribute("option","visualiserbesoin");
							request.setAttribute("t",t);
							request.setAttribute("Titre","besoin N "+numero+" du "+date+ " de type " + famille+"" );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
					 }
					 else {
						 listebesoin=besoinservice.getAll(numero);
						 String date=listebesoin.get(0).getDate();
						 String famille=familleservice.getByid(articleservice.getByid(listebesoin.get(0).getIdarticle()).getIdfamille()).getNom();
						 variable1.setAttribute("numero",numero);
						 variable3.setAttribute("date",date);
						 variable2.setAttribute("famille",famille);
							
							 int t=besoinservice.getAll(numero, "en attente").size();
							 variable.setAttribute("operation",option);
							 request.setAttribute("liste",listebesoin);
							request.setAttribute("option",option);
							request.setAttribute("t",t);
							request.setAttribute("Titre","besoin N "+numero+" du "+date+ " de type " + famille+"" );
							 Listefamille=familleservice.getAll("actif");
							 request.setAttribute("listfam",Listefamille );
							 listecontenuautorisation=contenuautorisationservice.getAll(utilisateur.getAutorisation());
								request.setAttribute("listecontenuautorisation",listecontenuautorisation );
							 this.getServletContext().getRequestDispatcher( "/vues/liste.jsp" ).forward( request, response ); 
						 
					 }
					 									 
				 }
	}

}
