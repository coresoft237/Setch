package setch.servelets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;


@WebServlet("/uploadfile")
@MultipartConfig

public class uploadfile extends HttpServlet {
	setch.service.databaseservice databaseservice=new setch.service.databaseservice();
	private static final int TAILLE_TAMPON=10240;
	public static final String CHEMIN_FICHIERS="../Documents/upload";
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			Part part=request.getPart("fichier");
			String filename=part.getSubmittedFileName();
			System.out.println("gg");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 response.setContentType("text/html;charset=UTF-8");
		  try {
			Part part=request.getPart("fichier");
			String filename=part.getSubmittedFileName();
			System.out.println(filename);
			//File f = new File(CHEMIN_FICHIERS);
			//-----
			/*try{
			    if(f.mkdir()) { 
			        System.out.println("Directory Created");
			        ecrireFichier(part,filename,CHEMIN_FICHIERS);
			        System.out.println(CHEMIN_FICHIERS); 
			    } else {
			        System.out.println("Directory is not created");
			        ecrireFichier(part,filename,CHEMIN_FICHIERS);
			    }
			    String path=""+CHEMIN_FICHIERS+"/"+filename+"";
			    System.out.println(path); 
			    databaseservice.chargementarticle(path);
			} 
			
			catch(Exception e){
			    e.printStackTrace();
			} */
			
			//----
			String path=getServletContext().getRealPath("/"+"file"+File.separator+filename);
			InputStream is=part.getInputStream();
			boolean succes=uploadfile(is,path);
			if(succes) {
				
				System.out.println(path);
				FileInputStream file = new FileInputStream(new File("C:/BORIS DE DIEU/Desktop/PlanComptableGeneral.xlsx"));
				Workbook workbook = new XSSFWorkbook(file);
				System.out.println("ok:");
			}
			else {
				System.out.println("false");
			}
			
			
		} catch (ServletException e) {
			
			e.printStackTrace();
		}
	}
	public boolean uploadfile(InputStream is,String path) {
		boolean test=false;
		try {
			byte[]byt=new byte[(is.available())];
			is.read();
			FileOutputStream fops=new FileOutputStream(path);
			fops.write(byt);
			fops.flush();
			fops.close();
			test=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return test;
	}
	private void ecrireFichier(Part part,String nomFichier,String chemin)throws IOException{
		BufferedInputStream entree=null;
		BufferedOutputStream sortie=null;
		try {
			entree=new BufferedInputStream(part.getInputStream(),TAILLE_TAMPON);
			sortie=new BufferedOutputStream(new FileOutputStream(new File(chemin,nomFichier)),TAILLE_TAMPON);
			byte[] tampon=new byte[TAILLE_TAMPON];
			int longueur;
			while((longueur=entree.read(tampon))>0) {
				sortie.write(tampon, 0, longueur);
				
			}
			
		}
		finally {
			entree.close();
			sortie.close();
		}
	}

}