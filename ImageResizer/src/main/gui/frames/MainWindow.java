package main.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

	private JComboBox<String> typeOfWatermarkSpinner;
	private JComboBox<String> placeOfWatermarkSpinner;
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
		setResize();
		addElemewntsToFrame();
		addClosingDialog();
	}

	private void setMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Info");
		menu.setMnemonic(KeyEvent.VK_I);
		menuItem = new JMenuItem("O programie", KeyEvent.VK_A);
		menuBar.add(this.menu);
	}

	private void setImageInfo() {
		imageInfoPanel = new JPanel();
		imageInfoPanel.setPreferredSize(new Dimension(250, 100));
		imageInfoPanel.setLayout(new GridLayout(0,2));
		imageInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Informajce o obrazku"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		nameOfFileLabel = new JLabel("Brak");

		heightLabel = new JLabel("Wysoko��");
		widthLabel = new JLabel("Szeroko��");

		heightValueLabel = new JLabel("Brak");
		widthValueLabel = new JLabel("Brak");

		
		imageInfoPanel.add(heightLabel);
		imageInfoPanel.add(heightValueLabel);
		imageInfoPanel.add(widthLabel);
		imageInfoPanel.add(widthValueLabel);
		imageInfoPanel.add(nameOfFileLabel);
	}

	private void setButtons() {
		buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(250, 100));
		GridBagLayout gBL = new GridBagLayout();
		//gBL.
		//buttonsPanel.setBackground(Color.darkGray);
	    buttonsPanel.setSize(250,100);
		//gBL.columnWeights = new double [] {2.0,2.0};
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Zapis/Podgl�d/Wyb�r"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		chooseFileButton = new JButton("Wybierz plik");
		saveButton = new JButton("Zapisz");
		pervieButton = new JButton("Podgl�d");
		buttonsPanel.setPreferredSize(new Dimension(250, 100));
		//c.fill = GridBagConstraints.VERTICAL;
		c.ipadx = 0;
		c.ipady = 0;
		c.fill = 0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		//chooseFileButton.setBounds(10, 10, 10, 10);
		c.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(chooseFileButton,c);
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		buttonsPanel.add(saveButton,c);
		c.gridx = 1;
		c.gridy = 1;
		buttonsPanel.add(pervieButton,c);
		
	}

	private void setEffects() {
		effectsPanel = new JPanel();
		effectsPanel.setLayout(new GridLayout(0,2));
		effectsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Wyb�r efektu"),
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

	private void setResize() {
		resizePanel = new JPanel();
		resizePanel.setPreferredSize(new Dimension(250,100));
		resizePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Resize"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	private void setWatermark() {
		watermarkPanel = new JPanel();
		watermarkPanel.setPreferredSize(new Dimension(250, 100));
		watermarkPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Znak wodny"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		typeOfWatermarkSpinner = new JComboBox<>();
		placeOfWatermarkSpinner = new JComboBox<String>();
		textField = new JTextField();

		watermarkPanel.add(typeOfWatermarkSpinner);
		watermarkPanel.add(placeOfWatermarkSpinner);
		watermarkPanel.add(textField);
	}

	private void addClosingDialog() {

		addWindowListener(new WindowListener() {

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
				String[] options = new String[2];
				options[0] = new String("Tak");
				options[1] = new String("Nie");

				int option = JOptionPane.showOptionDialog(getContentPane(), "Czy chcesz wyj��",
						"Koniec dzia�ania", 0, JOptionPane.INFORMATION_MESSAGE, null,
						options, null);
				System.out.println("Options "+option);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (option == JOptionPane.NO_OPTION) {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
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

	private void addElemewntsToFrame() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setJMenuBar(menuBar);
		add(imageInfoPanel);
		add(effectsPanel);
		add(resizePanel);
		add(watermarkPanel);
		add(buttonsPanel);
	}
}
