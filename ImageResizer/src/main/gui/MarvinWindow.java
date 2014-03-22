package main.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

public class MarvinWindow extends JFrame {
	MarvinImage img1;
	MarvinImagePanel imagePanel;
	
	public MarvinWindow(){
		imagePanel = new MarvinImagePanel();
		setVisible(false);
	}
	
	public MarvinWindow(String patch) {

		imagePanel = new MarvinImagePanel();
		img1 = MarvinImageIO.loadImage(patch);
		System.out.println(patch);
		imagePanel.setImage(img1);

		setSize(img1.getWidth(), img1.getHeight());
		setVisible(true);
		setResizable(false);
		setTitle("Obrazek przed edycj¹");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(imagePanel);

		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize());

		Dimension windowSize = new Dimension(getPreferredSize());

		int wdwLeft = screenSize.width / 10;
		int wdwTop = screenSize.height / 6;
		System.out.println(screenSize.width);
		System.out.println(screenSize.height);
		setLocation(wdwLeft, wdwTop);
	}
	
	public void MakeMarvinWindow(String patch){
		img1 = MarvinImageIO.loadImage(patch);
		System.out.println(patch);
		imagePanel.setImage(img1);

		setSize(img1.getWidth(), img1.getHeight());
		setVisible(true);
		setResizable(false);
		setTitle("Obrazek przed edycj¹");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(imagePanel);

		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize());

		Dimension windowSize = new Dimension(getPreferredSize());

		int wdwLeft = screenSize.width / 10;
		int wdwTop = screenSize.height / 6;
		System.out.println(screenSize.width);
		System.out.println(screenSize.height);
		setLocation(wdwLeft, wdwTop);
	}
}
