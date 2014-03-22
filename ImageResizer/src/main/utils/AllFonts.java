package main.utils;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class AllFonts {
	public static String[] listUsingGraphicsEnvironment ()
    {
        GraphicsEnvironment ge= null;
 
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
 
        String []fontNames=ge.getAvailableFontFamilyNames();
 
        for (int i = 0; i < fontNames.length; i++) {
            System.out.println(fontNames[i]);
        }
		return fontNames;
    }
	
	public static String[] listOfFamilyFonts(){
		String[] families = { "Serif", // "TimesRoman" in Java 1.0
			      "SansSerif", // "Helvetica" in Java 1.0
			      "Monospaced" }; // "Courier" in Java 1.0
			
			  // The available font styles and names for each one
			  int[] styles = { Font.PLAIN, Font.ITALIC, Font.BOLD,
			      Font.ITALIC + Font.BOLD };

			  String[] stylenames = { "Plain", "Italic", "Bold", "Bold Italic" };
			  
			  String[] resultStrings = new String [families.length*styles.length] ;
			  int x=0;
			    for (int f = 0; f < families.length; f++) { // for each family
			      for (int s = 0; s < styles.length; s++) { // for each style
			        Font font = new Font(families[f], styles[s], 18); // create font
			        
			        String name = families[f] + " " + stylenames[s]; // create name
			        System.out.println(name);
			        
			        resultStrings[x]=name;
			        //System.out.println(x+" "+resultStrings[f+s]);
			        x++;
			      }
			    
			  }
			    /*for(String s: resultStrings){
			    	System.out.println(s);
			    }*/
				return resultStrings;
	}
}
