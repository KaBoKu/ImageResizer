package main.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
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

import main.gui.MarvinTest;
import main.gui.MarvinWindow;
import main.gui.MarvinWindowsPerview;
import main.utils.AllFonts;
import main.utils.ImageResize;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

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

	private JCheckBox boldBox;
	private JCheckBox italicBox;
	private JComboBox<String> typeOfWatermarkSpinner;
	private JComboBox<String> placeOfWatermarkSpinner;
	private JTextField textField;

	private JButton resizeDo;
	private JFormattedTextField widthFormattedText;
	private JFormattedTextField heighFormattedText;
	private JCheckBox proportionBox;
	private JLabel widthResizeLabel;
	private JLabel heightResizeLabel;
	private JLabel proportionLabel;

	private ButtonGroup radioGroup;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;

	// MenuF
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	private File file;

	private Dimension screenSize;
	private Dimension windowSize;

	private MarvinImage image;
	private MarvinImage backupImage;
	private BufferedImage bImage;
	private MarvinWindow mwMarvinWindow;
	private MarvinWindowsPerview mwMarvinWindowsPerview;

	public MainWindow() {
		setLocation();
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
		// new MarvinWindow("D:\\WatermarkedImage.jpg");
		// new MarvinWindowsPerview("D:\\WatermarkedImage.jpg");
		mwMarvinWindow = new MarvinWindow();
		mwMarvinWindowsPerview = new MarvinWindowsPerview();
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
		imageInfoPanel.setLayout(new GridLayout(0, 2));
		imageInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Informajce o obrazku"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		nameOfFileLabel = new JLabel(" ");

		heightLabel = new JLabel("Wysokoœæ");
		widthLabel = new JLabel("Szerokoœæ");

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
		buttonsPanel.setSize(250, 100);
		buttonsPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Zapis/Podgl¹d/Wybór"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		chooseFileButton = new JButton("Wybierz plik");
		saveButton = new JButton("Zapisz");
		pervieButton = new JButton("Podgl¹d");
		buttonsPanel.setPreferredSize(new Dimension(250, 100));

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tmpString = file.getAbsolutePath();
				String[] arrayString = tmpString.split(".[a-zA-Z]+$");
				// System.out.println(arrayString[0]);
				BufferedImage im = image.getBufferedImage();
				try {
					ImageIO.write(im, "jpg", new File(arrayString[0] + "Edited"
							+ ".jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		chooseFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ExtensionFileFilter extensionFileFilter = new ExtensionFileFilter(
						"Pliki", new String[] { "jpg", "bmp", "gif" });
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(extensionFileFilter);
				int returnValue = fc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					System.out.println(file.getName());
					System.out.println(file.getAbsolutePath());
					image = MarvinImageIO.loadImage(file.getAbsolutePath());
					bImage = image.getBufferedImage();
					heightValueLabel.setText(Integer.toString(image.getHeight()));
					widthValueLabel.setText(Integer.toString(image.getWidth()));
					nameOfFileLabel.setText(file.getName());
					mwMarvinWindow.MakeMarvinWindow(file.getAbsolutePath());

				}
			}
		});
		pervieButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//mwMarvinWindowsPerview.MakeWindowPerview(file.getAbsolutePath());
				if(heighFormattedText.getText().length()>0 && widthFormattedText.getText().length()>0){
					int h=(bImage.getHeight()/100)*Integer.parseInt(heighFormattedText.getText());
					int w=(bImage.getWidth()/100)*Integer.parseInt(widthFormattedText.getText());
					
					bImage = ImageResize.resize(bImage,w,h);
					mwMarvinWindowsPerview.MakeWindowPerview(bImage);
				}
				
			}
		});
		c.ipadx = 0;
		c.ipady = 0;
		c.fill = 0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		// chooseFileButton.setBounds(10, 10, 10, 10);
		c.fill = GridBagConstraints.BOTH;
		buttonsPanel.add(chooseFileButton, c);
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		buttonsPanel.add(saveButton, c);
		c.gridx = 1;
		c.gridy = 1;
		buttonsPanel.add(pervieButton, c);

	}

	private void setEffects() {
		effectsPanel = new JPanel();
		effectsPanel.setLayout(new GridLayout(0, 2));
		effectsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Wybór efektu"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		radioButton1 = new JRadioButton("Efekt 1");
		radioButton2 = new JRadioButton("Efekt 2");
		radioButton3 = new JRadioButton("Efekt 3");
		radioButton4 = new JRadioButton("Efekt 4");
		radioButton5 = new JRadioButton("Brak Efektów");

		radioGroup = new ButtonGroup();
		radioGroup.add(radioButton1);
		radioGroup.add(radioButton2);
		radioGroup.add(radioButton3);
		radioGroup.add(radioButton4);
		radioGroup.add(radioButton5);

		radioButton5.setSelected(true);

		effectsPanel.add(radioButton1);
		effectsPanel.add(radioButton2);
		effectsPanel.add(radioButton3);
		effectsPanel.add(radioButton4);
		effectsPanel.add(radioButton5);
	}

	private void setResize() {
		resizePanel = new JPanel();
		// resizePanel.setLayout(new GridLayout(0,2));
		resizePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		resizePanel.setPreferredSize(new Dimension(250, 130));
		resizePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Resize"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		heightResizeLabel = new JLabel("Wysokoœæ");
		widthResizeLabel = new JLabel("Szerokoœæ");

		proportionBox = new JCheckBox("Zachowaj proporcje");
		NumberFormat percentDisplayFormat = NumberFormat.getIntegerInstance();
		percentDisplayFormat.setMaximumIntegerDigits(2);
		percentDisplayFormat.setMinimumFractionDigits(0);
		heighFormattedText = new JFormattedTextField(percentDisplayFormat);
		widthFormattedText = new JFormattedTextField(percentDisplayFormat);

		heighFormattedText.setPreferredSize(new Dimension(100, 150));

		heighFormattedText.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (proportionBox.isSelected()) {
					widthFormattedText.setText(heighFormattedText.getText());
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		widthFormattedText.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (proportionBox.isSelected())
					heighFormattedText.setText(widthFormattedText.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		proportionBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (proportionBox.isSelected()) {
					widthFormattedText.setText(heighFormattedText.getText());
				}
			}
		});

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = c.WEST;
		resizePanel.add(heightResizeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 30;
		resizePanel.add(heighFormattedText, c);
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		resizePanel.add(widthResizeLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 30;
		resizePanel.add(widthFormattedText, c);
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		resizePanel.add(proportionBox, c);
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 0;
		resizePanel.add(proportionBox, c);
	}

	private void setWatermark() {
		watermarkPanel = new JPanel();
		watermarkPanel.setPreferredSize(new Dimension(250, 100));
		// watermarkPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		watermarkPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Znak wodny"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		boldBox = new JCheckBox("Pogrubione");
		italicBox = new JCheckBox("Kursywa");

		typeOfWatermarkSpinner = new JComboBox<>(AllFonts.listOfFamilyFonts());

		placeOfWatermarkSpinner = new JComboBox<String>(new String[] {
				"Prawy górny róg", "Góra œrodek", "Lewy góry róg",
				"Œrodek lewo", "Œrodek", "Œrodek prawo", "Dolny lewy róg",
				"Dó³ œrodek", "Prawy dolny róg" });

		textField = new JTextField();

		watermarkPanel.add(typeOfWatermarkSpinner);
		watermarkPanel.add(placeOfWatermarkSpinner);
		watermarkPanel.add(textField);
	}

	private void setLocation() {
		screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

		windowSize = new Dimension(getPreferredSize());

		int wdwLeft = 300 + screenSize.width / 2 - windowSize.width / 2;
		int wdwTop = screenSize.height / 6 - windowSize.height;

		setLocation(wdwLeft, wdwTop);
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

				int option = JOptionPane.showOptionDialog(getContentPane(),
						"Czy chcesz wyjœæ", "Koniec dzia³ania", 0,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
				System.out.println("Options " + option);
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
