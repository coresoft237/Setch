package setch.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import setch.servelets.VenteNormale;

public class prints implements Printable {
	List<setch.beans.vente>listevente = new ArrayList<setch.beans.vente>();

	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if (pageIndex > 0) {
		      return NO_SUCH_PAGE;
		    }
/* On définit une marge pour l'impression */
		
        int marge=5;

        /* On récupère les coordonnées des bords de la page */
        int x = (int)pf.getImageableX();
        int y = (int)pf.getImageableY();
        int w = (int)pf.getImageableWidth();
        int h = (int)pf.getImageableHeight();
        System.out.println(VenteNormale.listevente1.size());
        int feuille1=10;
        int feuille2=20;
        x=10;
        /* On écrit une ligne de titre en rouge, en gras de taille 18 */
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setColor(Color.BLACK);
        g.drawString("Nom de l entreprise",x+10, y+marge+15);
        int a=10;
      //----------
        g.setFont(new Font("Arial", Font.PLAIN, 8));
        g.setColor(Color.BLACK);
        g.drawString("Date",x+10, y+marge+20+a);
         a=a+8;
         //----------
         g.setFont(new Font("Arial", Font.PLAIN, 8));
         g.setColor(Color.BLACK);
         g.drawString("Recu  N",x+10, y+marge+20+a);
          a=a+8;
        //----------
          g.setFont(new Font("Arial", Font.PLAIN, 8));
          g.setColor(Color.BLACK);
          g.drawString("Malade ",x+10, y+marge+20+a);
           a=a+8;
         //----------
           g.setFont(new Font("Arial", Font.PLAIN, 8));
           g.setColor(Color.BLACK);
           g.drawString("Code ",x+10, y+marge+20+a);
            a=a+8;
        //----------
        g.setFont(new Font("Arial", Font.PLAIN, 8));
        g.setColor(Color.BLACK);
        g.drawString("Articles         Qte Total",x+10, y+marge+20+a);
         a=a+8;
        //--------
        VenteNormale.n=(312*VenteNormale.listevente1.size())/29;
        /* On écrit une ligne en noir de taille 14 */
        for(int i=0;i<10;i++) {
      	  g.setFont(new Font("Arial", Font.PLAIN, 8));
            g.setColor(Color.BLACK);
            String plageimpression2="adong";
            String plageimpression1="boris";
         g.drawString(""+plageimpression2+"="+plageimpression1+"",x+10, y+marge+20+a);
       
            a=a+10;
      }
        return PAGE_EXISTS;
	}

}
