package setch.service;

import java.io.BufferedWriter;
import java.io.FileWriter;

import setch.servelets.login;

public class erreurservice {
	public static void remplir(String date,String poste,String utilisateur,String message )
	{
		try {
			String lineToAppend="\r\n"+date+":"+poste+":"+utilisateur+":"+message;
			 String filePath =login.CHEMIN_FICHIERS1+""+"erreurs.txt";
	            FileWriter fw = new FileWriter(filePath, true);    
	            fw.write(lineToAppend);
	            fw.close();
		    } 
		catch (Exception e) 
		{
		      e.printStackTrace();
		    }   
	}
	public static void remplir1(String date,String poste,String message )
	{
		try {
			String lineToAppend="\r\n"+date+":"+poste+":"+message;
			 String filePath =login.CHEMIN_FICHIERS1+""+"erreurs.txt";
	            FileWriter fw = new FileWriter(filePath, true);    
	            fw.write(lineToAppend);
	            fw.close();
		    } 
		catch (Exception e) 
		{
		      e.printStackTrace();
		    }   
	}

}
