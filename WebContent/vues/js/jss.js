
	

//autorisations
$(function ex(){
	  $('input[class="checkbox1"]').click(function(){
		  var id=$(this).attr("id");
		  var nom=$(this).attr("name");
		  var idauto=$(this).attr("idauto");
	    if($(this).prop("checked") == true){
	    	
			window.location.href="./ContenuAutorisation?option=UpdateContenuAutorisation&id2=true&id="+id+"&nom="+nom+"&idauto="+idauto+""
	    }
	    else if($(this).prop("checked") == false){
			window.location.href="./ContenuAutorisation?option=UpdateContenuAutorisation&id2=false&id="+id+"&nom="+nom+"&idauto="+idauto+""
	    }
	  });
	});

$(function(){


	$(".datepicker").datepicker({
		todayBtn:"linked",
		language:"it",
		autoclose:true,
		todayHighlight:true,
		format:'yyyy/mm/dd'
		
		});
	});

function imprimer(b){

	window.print();
	
		if(b=="creationvente")
		{
				window.onafterprint=prints;
		}
	else if(b=="creationventespecifique")
		{
		window.onafterprint=prin;
		}
	
	
	
}
function imprimeroffre(){

	window.print();
	window.onafterprint=printoffre;
}

function imprimer1(){

	window.print();
	window.onafterprint=prints1;
	
	
}
function prints(){
	
	window.location.href="./login?option=creationvente"
		
}
function printoffre(){
	
	window.location.href="./login?option=offre"
		
}
function prin(){
	
	window.location.href="./login?option=creationventespecifique"
		
}
function prints1(){
	
	window.location.href="./login?option=retourventenormale"
		
}
function modalshow(b)
{
	var nom=$('#listepatient').val();
	if(nom!="")
	{	
	var tab=b.split("/");
	var i=-1;
	do
		{
		i+=1;
		}
	while(i<tab.length && tab[i]!=nom)
		if(i>=tab.length)
			{
			$('input[name=name1]').attr('value', nom);
			$('#myModal1').modal('show');
			}
	}	
}
function modalshow1(b)
{
	var nom=$('#listefournisseur').val();
	if(nom!="")
	{	
	var tab=b.split("/");
	var i=-1;
	do
		{
		i+=1;
		}
	while(i<tab.length && tab[i]!=nom)
		if(i>=tab.length)
			{
			$('input[name=name2]').attr('value', nom);
			$('#myModal2').modal('show');
			}
	}
	
}
function modalshow2(b)
{
	var nom=$('#listelivreur').val();
	if(nom!="")
	{
		var tab1=nom.split("-");
		if(tab1.length==2){
			var nom1=tab1[1];
			var tab=b.split("/");
			var i=-1;
			do
				{
				i+=1;
				}
			while(i<tab.length && tab[i]!=nom1)
				if(i>=tab.length)
					{
					$('input[name=name2]').attr('value', nom1);
					$('#myModal2').modal('show');
					}
			}
		else if(tab1.length==1){
			var nom1=tab1[0];
			var tab=b.split("/");
			var i=-1;
			do
				{
				i+=1;
				}
			while(i<tab.length && tab[i]!=nom1)
				if(i>=tab.length)
					{
					$('input[name=name2]').attr('value', nom1);
					$('#myModal2').modal('show');
					}
				else{
					window.location.href="./login?option=creationlivraison1";
				}
			
			}
		}
	
}
function modalshowlivvente(b)
{

	var nom=$('#listelivreurvente').val();
	if(nom!="")
	{
		var tab1=nom.split("-");
		if(tab1.length==2){
			var nom1=tab1[1];
			var tab=b.split("/");
			var i=-1;
			do
				{
				i+=1;
				}
			while(i<tab.length && tab[i]!=nom1)
				if(i>=tab.length)
					{
					$('input[name=name2]').attr('value', nom1);
					$('#myModal3').modal('show');
					}
			}
		else if(tab1.length==1){
			var nom1=tab1[0];
			var tab=b.split("/");
			var i=-1;
			do
				{
				i+=1;
				}
			while(i<tab.length && tab[i]!=nom1)
				if(i>=tab.length)
					{
					$('input[name=name2]').attr('value', nom1);
					$('#myModal3').modal('show');
					}
				else{
					window.location.href="./login?option=creationlivraisonvente1";
				}
			
			}
		}
	
}
function hidemodal1()
{
	$('#myModal1').modal('hide');
}

function hidemodal2()
{
	alert("ok")
	$('#myModal2').modal('hide');
}
function hidemodal2bis()
{
	$('#myModal3').modal('hide');
}

function hidemodal()
{
	$('#myModal').modal('hide');
}
var requete;
function creationpatient()
{
	var nom=document.getElementById("name1");
	var telephone=document.getElementById("telephone1");
	var titre=document.getElementById("titre1");
	var statut=document.getElementById("statut1");
	var url = "login?option=creationpatient&nom="+nom.value+"&telephone="+telephone.value+"&titre="+titre.value+"&statut="+statut.value+"";
	if (window.XMLHttpRequest) {
	requete = new XMLHttpRequest();
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM;
	requete.send(null);
	} else if (window.ActiveXObject) {
	requete = new ActiveXObject("Microsoft.XMLHTTP");
	if (requete) {
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM;
	requete.send();
	}
	} else {
	alert("Le navigateur ne supporte pas la technologie AJAX");
	}
}
function creationfournisseur()
{
	var nom=document.getElementById("name2");
	var telephone=document.getElementById("telephone2");
	var titre=document.getElementById("titre2");
	var statut=document.getElementById("statut2");
	var url = "login?option=creationpatient&nom="+nom.value+"&telephone="+telephone.value+"&titre="+titre.value+"&statut="+statut.value+"";
	if (window.XMLHttpRequest) {
	requete = new XMLHttpRequest();
	requete.open("GET", url, true);
	requete.onreadystatechange = majIH;
	requete.send(null);
	} else if (window.ActiveXObject) {
	requete = new ActiveXObject("Microsoft.XMLHTTP");
	if (requete) {
	requete.open("GET", url, true);
	alert("debut");
	requete.onreadystatechange = majIH;
	requete.send();
	}
	} else {
	alert("Le navigateur ne supporte pas la technologie AJAX");
	}
}
function creationclient()
{
	var nom=document.getElementById("namelivvente2");
	var telephone=document.getElementById("telephonelivvente2");
	var titre=document.getElementById("titrelivvente2");
	var statut=document.getElementById("statutlivvente2");
	var url = "login?option=creationpatient&nom="+nom.value+"&telephone="+telephone.value+"&titre="+titre.value+"&statut="+statut.value+"";
	if (window.XMLHttpRequest) {
	requete = new XMLHttpRequest();
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM2bis;
	requete.send(null);
	} else if (window.ActiveXObject) {
	requete = new ActiveXObject("Microsoft.XMLHTTP");
	if (requete) {
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM2bis;
	requete.send();
	}
	} else {
	alert("Le navigateur ne supporte pas la technologie AJAX");
	}
}
function modificationpwd()
{
	var ancienpwd=document.getElementById("ancienPwd");
	var nouveaupwd=document.getElementById("nouveauPwd");
	var url = "login?option=modificationpwd&ancienpwd="+ancienpwd.value+"&nouveaupwd="+nouveaupwd.value+"";
	if (window.XMLHttpRequest) {
	requete = new XMLHttpRequest();
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM1;
	requete.send(null);
	} else if (window.ActiveXObject) {
	requete = new ActiveXObject("Microsoft.XMLHTTP");
	if (requete) {
	requete.open("GET", url, true);
	requete.onreadystatechange = majIHM1;
	requete.send();
	}
	} else {
	alert("Le navigateur ne supporte pas la technologie AJAX");
	}
}
function majIHM() {
	var message = "";
	if (requete.readyState == 4) {
	if (requete.status == 200) {
	// exploitation des données de la réponse
		hidemodal1();
	// ...
	} else {
	alert('Une erreur est survenue lors de la mise à jour de la page');
	}
	}
	}
function majIHM2() {
	var message = "";
	if (requete.readyState == 4) {
	if (requete.status == 200) {
	// exploitation des données de la réponse
		hidemodal2();
		window.location.href="./login?option=creationlivraison1";
	// ...
	} else {
	alert('Une erreur est survenue lors de la mise à jour de la page');
	}
	}
	}
function majIH() {
	var message = "";
	if (requete.readyState == 4) {
	if (requete.status == 200) {
	// exploitation des données de la réponse
		hidemodal2();
		
	// ...
	} else {
	alert('Une erreur est survenue lors de la mise à jour de la page');
	}
	}
	}
function majIHM2bis() {
	var message = "";
	if (requete.readyState == 4) {
	if (requete.status == 200) {
	// exploitation des données de la réponse
		hidemodal2bis();
		window.location.href="./login?option=creationlivraisonvente1";
	// ...
	} else {
	alert('Une erreur est survenue lors de la mise à jour de la page');
	}
	}
	}
function majIHM1() {
	var message = "";
	if (requete.readyState == 4) {
	if (requete.status == 200) {
	// exploitation des données de la réponse
		hidemodal();
	// ...
	} else {
	alert('Une erreur est survenue lors de la mise à jour de la page');
	}
	}
	}

function choixfacture(a,b)
{
	var tab=a.split(":");
	  var valeur = prompt("Saisissez le numero de facture dont les elements doivent etre retournees");
		if(valeur!=""){
			var ta=valeur.split("/");
			if(ta[1]=="VN"){
				var j=-1
				do
				{
				j+=1;
				}
			while(j<tab.length && tab[j]!=valeur)
				if(j>=tab.length)
					{
					alert("Le numero de facture " + valeur + " n existe pas.Revoyez le numero de facture");
					
					}
				else
					{
				window.location.href="./login?option="+b+"&facture="+valeur+"";
					}
			}
			else{
				alert("Les numeros de facture doivent etre celle des ventes normales")
			}

		}
		else{
			
			alert("Aucun numero de facture renseigne");
		}
}
function controlretour(b,c,d,e)
{
	var tab=b.split("/");
	var tab1=d.split("/");
	var tab2=e.split("/")
	var qte=document.getElementById("qteretour");
	var nom=document.getElementById("articleretour");
	var type="creationretourventespecifiquebis";
	var quantiteretour=0;
	var j=-1;
	do
		{
		j+=1;
		}
	while(j<tab1.length && tab1[j]!=nom.value)
		if(j<tab1.length)
			{
			alert("l article "+ nom.value +" figure deja sur le recu en cours");
			
			}
		else
			{
				var i1=-1;
				var i2=0
				//qtite facturee
				do
					{
					i1+=1;
					}
				while(i1<tab.length && tab[i1]!=nom.value);
					//retour
				if(tab2.length>1)
				{
					for(var i=1;i<tab2.length;i=i+2)
						{
						if(tab2[i]==nom.value)
							{
							quantiteretour=parseInt(quantiteretour)+parseInt(tab2[i+1]);
							}
						}
					const val=parseInt(tab[i1+1])+parseInt(quantiteretour)-parseInt(qte.value);
							if(val>=0)
								{
								
								
								window.location.href="./login?option="+type+"&facture="+c+"&article="+tab[i1]+"&quantite="+qte.value+"";
								}
							else
								{
								alert("Les Quantites retournes sont superieures aux quantites du recu " + c + " .Revoyez les quantites")
								}
				}
				else
					{
					const val=parseInt(tab[i1+1])-parseInt(qte.value);
					if(val>=0)
					{
					
					
					window.location.href="./login?option="+type+"&facture="+c+"&article="+tab[i1]+"&quantite="+qte.value+"";
					}
				else
					{
					alert("Les Quantites retournes sont superieures aux quantites du recu " + c + " .Revoyez les quantites")
					}
					}	
			}
}
function controlretour1(b,c,d,e)
{
	var tab=b.split("/");
	var tab1=d.split("/");
	var tab2=e.split("/")
	var qte=document.getElementById("qteretour");
	var nom=document.getElementById("articleretour");
	var type="creationretourventespecifiquebissss";
	var quantiteretour=0;
	var j=-1;
	do
		{
		j+=1;
		}
	while(j<tab1.length && tab1[j]!=nom.value)
		if(j<tab1.length)
			{
			alert("l article "+ nom.value +" figure deja sur le recu en cours");
			
			}
		else
			{
				var i1=-1;
				var i2=0
				//qtite facturee
				do
					{
					i1+=1;
					}
				while(i1<tab.length && tab[i1]!=nom.value);
					//retour
				if(tab2.length>1)
				{
					for(var i=1;i<tab2.length;i=i+2)
						{
						if(tab2[i]==nom.value)
							{
							quantiteretour=parseInt(quantiteretour)+parseInt(tab2[i+1]);
							}
						}
					const val=parseInt(tab[i1+1])+parseInt(quantiteretour)-parseInt(qte.value);
							if(val>=0)
								{
								
								
								window.location.href="./login?option="+type+"&facture="+c+"&article="+tab[i1]+"&quantite="+qte.value+"";
								}
							else
								{
								alert("Les Quantites retournes sont superieures aux quantites du recu " + c + " .Revoyez les quantites")
								}
				}
				else
					{
					const val=parseInt(tab[i1+1])-parseInt(qte.value);
					if(val>=0)
					{
					
					
					window.location.href="./login?option="+type+"&facture="+c+"&article="+tab[i1]+"&quantite="+qte.value+"";
					}
				else
					{
					alert("Les Quantites retournes sont superieures aux quantites du recu " + c + " .Revoyez les quantites")
					}
					}
					
			}
	
	
}
function controlfacturationretour(c,d)
{

	var tab1=d.split("/");
	var qte=document.getElementById("quantiteprise");
	var prescripteur=document.getElementById("prescripteur");
	var nom=document.getElementById("soins");
	var type="creationretourventespecifiquebiss";
	var j=-1;
	do
		{
		j+=1;
		}
	while(j<tab1.length && tab1[j]!=nom.value)
		if(j<tab1.length)
			{
			alert("l article "+ nom.value +" figure deja sur le recu en cours");
			
			}
		else
			{
			window.location.href="./login?option="+type+"&facture="+c+"&article="+nom.value+"&quantite="+qte.value+"&prescripteur="+prescripteur.value+"";
						
			}
}
function controlfacturationretour1(c,d)
{

	var tab1=d.split("/");
	var qte=document.getElementById("quantiteprise");
	var prescripteur=document.getElementById("prescripteur");
	var nom=document.getElementById("soins");
	var reduction=document.getElementById("reduction");
	var type="creationretourventespecifiquebisss";
	var j=-1;
	do
		{
		j+=1;
		}
	while(j<tab1.length && tab1[j]!=nom.value)
		if(j<tab1.length)
			{
			alert("l article "+ nom.value +" figure deja sur le recu en cours");
			
			}
		else
			{
			window.location.href="./login?option="+type+"&facture="+c+"&article="+nom.value+"&quantite="+qte.value+"&prescripteur="+prescripteur.value+"&reduction="+reduction.value+"";
						
			}	
}

function offre2(){
	window.location.href="./OffrePrixAchat?option=SaveOffre"
}
function besoin2(){
	window.location.href="./Besoin?option=SaveBesoin"
}
function Livraison2(){
	window.location.href="./Livraison?option=SaveLivraison"
}
function inventaire(){
	window.location.href="./inventaire?option=SaveInventaire"
	
}
function offre3(){
	 var article=$('#articleoffre').val();
	 var prix=$('#prixoffre').val();
	 window.location.href="./login?option=creationoffre3&article="+article+"&prix="+prix+""
}
function showoffre(a,b,c,d,e)
{
	$('input[id=idoffre]').attr('value', a);
	$('input[id=idarticleoffre]').attr('value', b);
	$('input[id=prixoffree]').attr('value', c);
	$('input[id=numerooffre]').attr('value', e);
	var option="";
	tab=new Array("nonactif","actif");
	for(var i=0;i<tab.length;i++){
		if(d==tab[i]){
			option +="<option value= + tab[i]+ selected >" + tab[i] + "</option>";
		}
		else{
			option +="<option value= + tab[i]+ >" + tab[i] + "</option>";
		}
	}
	$("#statutoffree").append(option);	
	jQuery("#myModifoffre").modal("show");
			
}
function showbesoin(a,b,c,d,e)
{
	$('input[id=idbesoin]').attr('value', a);
	$('input[id=idarticlebesoin]').attr('value', b);
	$('input[id=quantitebesoin]').attr('value', c);
	$('input[id=numerobesoin]').attr('value', e);
	jQuery("#myModifbesoin").modal("show");	
}
function showcorrectionstock(a,b,c,d)
{
	$('input[id=idcorrection]').attr('value', a);
	$('input[id=nc]').attr('value', d);
	$('input[id=idarticlecorrection]').attr('value', b);
	$('input[id=quantitecorrection]').attr('value', c);
	jQuery("#myModifcorrectionstock").modal("show");	
}
function showcorrectionstock1(a,b,c,d)
{
	$('input[id=idcorrection1]').attr('value', a);
	$('input[id=nc1]').attr('value', d);
	$('input[id=idarticlecorrection1]').attr('value', b);
	$('input[id=quantitecorrection1]').attr('value', c);
	jQuery("#myModifcorrectionstock1").modal("show");	
}
function showlivraisonvente(a,b,c)
{
	$('input[id=idlivraisonvente]').attr('value', a);
	$('input[id=idarticlelivraisonvente]').attr('value', b);
	$('input[id=quantitelivraisonvente]').attr('value', c);
	jQuery("#myModiflivraisonvente").modal("show");
			
}
function showlivraison(a,b,c,e)
{
	$('input[id=idlivraison]').attr('value', a);
	$('input[id=idarticlelivraison]').attr('value', b);
	$('input[id=quantitelivraison]').attr('value', c);
	$('input[id=pulivraison]').attr('value', e);
	jQuery("#myModiflivraison").modal("show");
			
}
$("#modifoffre").click(function(){
	id=$("#idoffre").val();
	prix=$("#prixoffree").val();
	numero=$("#numerooffre").val();
	statut=$('#statutoffree option:selected').text();
	window.location.href="./login?option=modifieroffre&id="+id+"&prix="+prix+"&statut="+statut+"&numero="+numero;
});
$("#modifbesoin").click(function(){
	id=$("#idbesoin").val();
	prix=$("#quantitebesoin").val();
	numero=$("#numerobesoin").val();
	window.location.href="./login?option=modifierbesoin&id="+id+"&prix="+prix+"&numero="+numero;
});
$("#modifcorrection").click(function(){
	id=$("#idcorrection").val();
	quantite=$("#quantitecorrection").val();
	nc=$("#nc").val();
	if(quantite>=0){
		window.location.href="./login?option=modifiercorrectionstock&id="+id+"&quantite="+quantite+"&nc="+nc;
	}
	else{
		alert("Nous sommes dans le cadre d une entree .La quantite doit etre superieure ou egale a 0");
	}
});
$("#modifcorrection1").click(function(){
	id=$("#idcorrection1").val();
	quantite=$("#quantitecorrection1").val();
	nc=$("#nc1").val();
	if(quantite <=0){
		window.location.href="./login?option=modifiercorrectionstock&id="+id+"&quantite="+quantite+"&nc="+nc;
	}
	else{
		alert("Nous sommes dans le cadre d une sortie .La quantite doit etre inferieure ou egale a 0");
	}
	
});
$("#modiflivraison").click(function(){
	id=$("#idlivraison").val();
	quantite=$("#quantitelivraison").val();
	pu=$("#pulivraison").val();
	window.location.href="./login?option=modifierlivraison&id="+id+"&pu="+pu+"&quantite="+quantite;
});
$("#modiflivraisonvente").click(function(){
	id=$("#idlivraisonvente").val();
	quantite=$("#quantitelivraisonvente").val();
	window.location.href="./login?option=modifierlivraisonvente&id="+id+"&quantite="+quantite;
});
$("#buttoncorrection").click(function(){
	window.location.href="./login?option=imprimercorrectionstock";
});
$("#buttontransfert").click(function(){
	window.location.href="./login?option=imprimertransfert";
});
$("#buttoncorrectionstock").click(function(){
	alert("bjr");
	window.location.href="./login?option=imprimercorrectionstock";
});

function inventaire2(){
	 var famille=$('#famillecommande option:selected').val();
	window.location.href="./login?option=creationinventaire2&famille="+famille+""
}
function PrintCommande(){
	 var choix=$('#choix option:selected').val();
	 window.open('./Besoin?option=PrintCommande2&choix='+choix,'_blank');
	//window.location.href="./Besoin?option=PrintCommande2&choix="+choix+""
}
function besoin3(){
	 var article=$('#articlebesoin').val();
	 var quantite=$('#quantitebesoin').val();
	 window.location.href="./login?option=creationbesoin3&article="+article+"&quantite="+quantite+""
}
function inventaire3(){
	 var article=$('#articlebesoin').val();
	 var quantite=$('#quantitebesoin').val();
	 window.location.href="./login?option=creationinventaire3&article="+article+"&quantite="+quantite+""
}
function livraison3(){
	 var article=$('#articlelivraison option:selected').val();
	 var quantite=$('#quantitelivraison').val();
	 var prix=$('#prixlivraison').val();
	 window.location.href="./login?option=creationlivraison3&article="+article+"&quantite="+quantite+"&prix="+prix+""
}
function besoin3bis(){
	 var article=$('#articlebesoinbis').val();
	 var quantite=$('#quantitebesoinbis').val();
	 window.location.href="./login?option=creationbesoin3bis&article="+article+"&quantite="+quantite+""
}
function livraison2(){
	 var fournisseur=$('#listelivreur').val();
	 var numerobc=$('#numerobc option:selected').val();
	 var numerobl=$('#numerolivraison').val();
	 var date=$('#datelivraison').val();
	window.location.href="./login?option=creationlivraison2&fournisseur="+fournisseur+"&numerobc="+numerobc+"&numerobl="+numerobl+"&date="+date+""
}
function marge(){

	var date1=($('#dateinitialemarge').val());
	var date2=($('#datefinalemarge').val());
	var heure1=($('#heureinitialemarge').val());
	var heure2=($('#heurefinalemarge').val());
	if(date2>=date1){
	window.location.href="./login?option=etatmarge1&date1="+date1+"&date2="+date2+"&heure1="+heure1+"&heure2="+heure2+"";			
	}
	else{
		alert("La date de fin est anterieure à  la date de debut");
	}
}
function commande2(){
	var commande=$('#numerocommande option:selected').val();
	window.location.href="./login?option=creationcommande2&commande="+commande+""
}
//-------factures
function val(){
	window.open("./login?option=printfacturevente");

}
function val1(){

	window.location.href="./VenteNormale?option=PrintVenteNormale"
}
function rechercheventenormale(){
var date1=($('#datepicker').val());
	window.location.href="./VenteNormale?option=RechercheVenteNormale&date1="+date1+"";
}

function marge1(){
	var date1=($('#dateinitialestock').val());
	var date2=($('#datefinalestock').val());
	if(date2>=date1){
	window.location.href="./login?option=etatstock1&date1="+date1+"&date2="+date2+"";			
	}
	else{
		alert("La date de fin est anterieure à  la date de debut");
	}
}






