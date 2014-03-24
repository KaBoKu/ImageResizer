package main.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JRadioButton;

public class WaterMarkImage {
	public static void addWaterMark(BufferedImage img, String text,
			String type, String place, float opa, String size,String color) {
		
		int fontSize = 30;
		System.out.println(size);
		if (size.equals("Bardzo ma³e"))
			fontSize = 10;
		if (size.equals("Ma³a"))
			fontSize = 15;
		if (size.equals("Œrednie"))
			fontSize = 25;
		if (size.equals("Du¿e"))
			fontSize = 45;
		if (size.equals("Bardzo du¿e"))
			fontSize = 65;
		
		
		
		
		String[] arrayTypeStrings = type.split(" ");
		for (String string : arrayTypeStrings)
			System.out.println(string);
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opa));
		if (arrayTypeStrings[1].equals("Plain"))
			g2d.setFont(new Font(arrayTypeStrings[0], Font.PLAIN, fontSize));
		if (arrayTypeStrings[1].equals("Bold"))
			g2d.setFont(new Font(arrayTypeStrings[0], Font.BOLD,fontSize));
		if (arrayTypeStrings[1].equals("Italic"))
			g2d.setFont(new Font(arrayTypeStrings[0], Font.ITALIC, fontSize));

		if (arrayTypeStrings[1].equals("Bold Italic"))
			g2d.setFont(new Font(arrayTypeStrings[0], Font.BOLD | Font.ITALIC,
					50));
		
		String      message = new String("Hello, StackOverflow!");
		Font        defaultFont = new Font("Helvetica", Font.PLAIN, 12);
		//FontMetrics fontMetrics = new FontMetrics(defaultFont);
		//...
		//int widtha = fontMetrics.stringWidth(message);
		
		int height = img.getHeight();
		int width = img.getWidth();
		if(place.equals("Lewy góry róg")){
			height = img.getHeight()/20;
			width = img.getWidth()/20;
		};
		if(place.equals("Œrodek lewo")){
			height = img.getHeight()/2;
			width = img.getWidth()/20;
		};
		if(place.equals("Dolny lewy róg")){
			height = img.getHeight()-img.getHeight()/20;;
			width = img.getWidth()/20;
		}
		
		FontMetrics fM = g2d.getFontMetrics();
		fM.stringWidth(text);
		if(place.equals("Góra œrodek")){
			height = img.getHeight()/20;
			width = img.getWidth()/2-fM.stringWidth(text)/2;
		}
		if(place.equals("Œrodek")){
			height = img.getHeight()/2;
			width = img.getWidth()/2-fM.stringWidth(text)/2;
		}
		if(place.equals("Œrodek dó³")){
			height = img.getHeight()-img.getHeight()/20;
			width = img.getWidth()/2-fM.stringWidth(text)/2;
		}
		if(place.equals("Prawy górny róg")){
			height = img.getHeight()/20;
			width = img.getWidth()-img.getWidth()/20-fM.stringWidth(text);
		}
		if(place.equals("Œrodek prawo")){
			height = img.getHeight()/2;
			width = img.getWidth()-img.getWidth()/20-fM.stringWidth(text);
		}
		if(place.equals("Prawy dolny róg")){
			height = img.getHeight()-img.getHeight()/20;
			width = img.getWidth()-img.getWidth()/20-fM.stringWidth(text);
		}
		System.out.println("Height: "+height+"\n Width: "+width);
		if(color.equals("Bia³y"))g2d.setColor(Color.WHITE);
		if(color.equals("¯ó³ty"))g2d.setColor(Color.YELLOW);
		if(color.equals("Czerwony"))g2d.setColor(Color.RED);
		if(color.equals("Zielony"))g2d.setColor(Color.GREEN);
		if(color.equals("Czarny"))g2d.setColor(Color.BLACK);
		
		String watermark = text;
		g2d.drawString(watermark, width, height);

	}
}
