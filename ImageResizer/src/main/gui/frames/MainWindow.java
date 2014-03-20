package main.gui.frames;

import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	// Panele
	private JPanel effectsPanel;
	private JPanel watermarkPanel;
	private JPanel buttonsPanel;
	private JPanel imageInfoPanel;
	private JPanel resizePanel;

	// Elements
	private JButton chooseFileButton;
	private JButton saveButton;
	private JButton pervieButton;

	private JLabel nameOfFileLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;

	private JLabel widthValueLabel;
	private JLabel heightValueLabel;

	private JSpinner typeOfWatermarkSpinner;
	private JSpinner placeOfWatermarkSpinner;
	private JTextField textField;
	
	private ButtonGroup radoiGroup;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	
	// Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	public MainWindow() {
		setMenu();
		setTitle("Resize App");
		setSize(new Dimension(250, 580));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setImageInfo();
		setButtons();
		setEffects();
		setResize();
		setWatermark();
		addElemewntsToFrame();
	}

	private void setMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Info");
		menu.setMnemonic(KeyEvent.VK_I);
		menuBar.add(this.menu);
	}

	private void setImageInfo() {
		imageInfoPanel = new JPanel();
		imageInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Informajce o obrazku"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		nameOfFileLabel = new JLabel("Brak");

		heightLabel = new JLabel("Wysokoœæ");
		widthLabel = new JLabel("Szerokoœæ");

		heightValueLabel = new JLabel("Brak");
		widthValueLabel = new JLabel("Brak");

		imageInfoPanel.add(nameOfFileLabel);
		imageInfoPanel.add(heightLabel);
		imageInfoPanel.add(heightValueLabel);
		imageInfoPanel.add(widthLabel);
		imageInfoPanel.add(widthValueLabel);
	}

	private void setButtons() {
		buttonsPanel = new JPanel();
		
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Zapis/Podgl¹d/Wybór"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		chooseFileButton = new JButton("Wybierz plik");
		saveButton = new JButton("Zapisz");
		pervieButton = new JButton("Podgl¹d");
		
		buttonsPanel.add(chooseFileButton);
		buttonsPanel.add(saveButton);
		buttonsPanel.add(pervieButton);
	}
	
	private void setEffects(){
		effectsPanel = new JPanel();
		
		effectsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Wybór efektu"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		radioButton1 = new JRadioButton("Efekt 1");
		radioButton2 = new JRadioButton("Efekt 2");
		radioButton3 = new JRadioButton("Efekt 3");
		radioButton4 = new JRadioButton("Efekt 4");
		effectsPanel.add(radioButton1);
		effectsPanel.add(radioButton2);
		effectsPanel.add(radioButton3);
		effectsPanel.add(radioButton4);
	}
	
	private void setResize(){
		resizePanel = new JPanel();
		
	}
	private void setWatermark(){
		watermarkPanel = new JPanel();
		
		watermarkPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Znak wodny"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		typeOfWatermarkSpinner = new JSpinner();
		placeOfWatermarkSpinner = new JSpinner();
		textField = new JTextField();
		
		
		
		
		watermarkPanel.add(typeOfWatermarkSpinner);
		watermarkPanel.add(placeOfWatermarkSpinner);
		watermarkPanel.add(textField);
	}
	private void addElemewntsToFrame() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setJMenuBar(menuBar);
		add(imageInfoPanel);
		add(effectsPanel);
		add(watermarkPanel);
		add(buttonsPanel);
	}
}
