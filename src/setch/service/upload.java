package setch.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class upload {
	public void ecrireFichier(Part part,String nomFichier,String chemin,int TAILLE_TAMPON)throws IOException{
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
}
