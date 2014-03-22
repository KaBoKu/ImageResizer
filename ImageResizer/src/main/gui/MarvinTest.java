package main.gui;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import main.utils.AllFonts;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

public class MarvinTest extends JFrame{
	File f = new File("kl.jpg");
	
	MarvinImage img1 = MarvinImageIO.loadImage("res/Krakow2.jpg");
	MarvinImagePanel imagePanel = new MarvinImagePanel();
	Graphics2D g2d = (Graphics2D) img1.getBufferedImage().getGraphics();
	public MarvinTest(){
		imagePanel.setImage(img1);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(img1.getWidth(),img1.getHeight());
        setVisible(true);
		add(imagePanel);
		BufferedImage imgBufferedImage = img1.getBufferedImage();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.setFont(new Font("Monospaced", Font.PLAIN, 30));
		String watermark = " dewfew";
		g2d.drawString(watermark, 0, img1.getHeight()/ 2);
		//setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}
	public MarvinTest(String patch){
		img1 = MarvinImageIO.loadImage(patch);
		imagePanel.setImage(img1);
		add(imagePanel);
	}
	//public void setVisible(boolean on) {
		//setVisible(on);
	//}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new MarvinTest().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//listUsingGraphicsEnvironment();
		
		AllFonts.listOfFamilyFonts();
	}

	
	public static void listUsingGraphicsEnvironment ()
    {
        GraphicsEnvironment ge= null;
 
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
 
        String []fontNames=ge.getAvailableFontFamilyNames();
 
        for (int i = 0; i < fontNames.length; i++) {
            System.out.println(fontNames[i]);
        }
    }
	
}

