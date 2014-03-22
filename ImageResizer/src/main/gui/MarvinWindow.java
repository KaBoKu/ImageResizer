package main.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

public class MarvinWindow extends JFrame{
	MarvinImage img1;
	MarvinImagePanel imagePanel;
	
	
	public MarvinWindow(String patch) {
		// TODO Auto-generated constructor stub
		img1 = MarvinImageIO.loadImage(patch);
		imagePanel.setImage(img1);
		
		setSize(img1.getWidth(),img1.getHeight());
        setVisible(true);
        setResizable(false);
        setTitle("Podgl¹d");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		add(imagePanel);
	}
	
	@Override
	public synchronized void addWindowListener(WindowListener l) {
		// TODO Auto-generated method stub
		super.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
