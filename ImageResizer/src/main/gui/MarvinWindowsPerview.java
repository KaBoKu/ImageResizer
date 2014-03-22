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

public class MarvinWindowsPerview extends JFrame {
	MarvinImage img1;
	MarvinImagePanel imagePanel;

	public MarvinWindowsPerview(String patch) {

		imagePanel = new MarvinImagePanel();
		img1 = MarvinImageIO.loadImage(patch);
		System.out.println(patch);
		imagePanel.setImage(img1);

		setSize(img1.getWidth(), img1.getHeight());
		setVisible(true);
		setResizable(false);
		setTitle("Podgl¹d");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
