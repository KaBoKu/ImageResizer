package main.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.JToggleButton;

import main.gui.MarvinTest;
import main.gui.MarvinWindow;
import main.gui.MarvinWindowsPerview;
import main.utils.AllFonts;
import main.utils.DeepCopyBI;
import main.utils.ImageResize;
import main.utils.JTextFieldLimit;
import main.utils.WaterMarkImage;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

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
	private JButton previewButton;

	private JLabel nameOfFileLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;

	private JLabel widthValueLabel;
	private JLabel heightValueLabel;

	private JCheckBox boldBox;
	private JCheckBox italicBox;
	private JComboBox<String> typeOfWatermarkSpinner;
	private JComboBox<String> placeOfWatermarkSpinner;
	private JComboBox<String> colorsForComboBox;
	private JTextField textField;
	private JToggleButton watermarkDo;

	private JRadioButton verySmallWaterMarkButton;
	private JRadioButton smallWaterMarkButton;
	private JRadioButton mediumWaterMarkButton;
	private JRadioButton bigWaterMarkButton;
	private JRadioButton veryBigWaterMarkButton;
	private ButtonGroup sizeButtonGroup;

	private JRadioButton noOpaButton;
	private JRadioButton midOpaButton;
	private JRadioButton bigOpaButton;
	private JRadioButton maxOpaButton;
	private ButtonGroup opaButtonGroup;

	private JCheckBox resizeDo;
	private JFormattedTextField widthFormattedText;
	private JFormattedTextField heighFormattedText;
	private JCheckBox proportionBox;
	private JLabel widthResizeLabel;
	private JLabel heightResizeLabel;
	private JToggleButton resizeDo1;
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
	private BufferedImage sImage;
	private MarvinWindow mwMarvinWindow;
	private MarvinWindowsPerview mwMarvinWindowsPerview;

	private boolean previewDone;

	public MainWindow() {
		setLocation();
		setMenu();
		setTitle("Resize App");
		//setSize(new Dimension(250, 680));
		setSize(new Dimension(1000, 480));
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
		previewDone = false;
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
		imageInfoPanel.setPreferredSize(new Dimension(250, 70));
		imageInfoPanel.setLayout(new GridBagLayout());
		imageInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Informajce o obrazku"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		nameOfFileLabel = new JLabel(" ");

		heightLabel = new JLabel("Wysokoœæ");
		widthLabel = new JLabel("Szerokoœæ");

		heightValueLabel = new JLabel("Brak");
		widthValueLabel = new JLabel("Brak");

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		imageInfoPanel.add(heightLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		imageInfoPanel.add(heightValueLabel, c);
		c.gridx = 0;
		c.gridy = 1;
		imageInfoPanel.add(widthLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		imageInfoPanel.add(widthValueLabel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		imageInfoPanel.add(nameOfFileLabel, c);
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
		previewButton = new JButton("Podgl¹d");
		buttonsPanel.setPreferredSize(new Dimension(250, 100));

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (previewDone) {
					saveMethod();
				}
				else {
					previewMethod();
					saveMethod();
				}
			}

			private void saveMethod() {
				String tmpString = file.getAbsolutePath();
				String[] arrayString = tmpString.split(".[a-zA-Z]+$");
				// System.out.println(arrayString[0]);
				BufferedImage im = bImage;//.getBufferedImage();
				MarvinImageIO.saveImage(new MarvinImage(bImage),arrayString[0]
						+ "Edited" + ".jpg");
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
					backupImage = image.clone();
					bImage = image.getBufferedImage();
					sImage = image.getBufferedImage();
					heightValueLabel.setText(Integer.toString(image.getHeight()));
					widthValueLabel.setText(Integer.toString(image.getWidth()));
					nameOfFileLabel.setText(file.getName());
					mwMarvinWindow.MakeMarvinWindow(file.getAbsolutePath());
					previewDone = false;
				}
			}
		});
		previewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				previewMethod();
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
		buttonsPanel.add(previewButton, c);

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

		resizePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		resizePanel.setPreferredSize(new Dimension(250, 130));
		resizePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Resize"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		heightResizeLabel = new JLabel("Wysokoœæ");
		widthResizeLabel = new JLabel("Szerokoœæ");

		resizeDo = new JCheckBox("Resize");
		resizeDo1 = new JToggleButton("Resize");
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
		c.fill = c.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = c.WEST;
		resizePanel.add(heightResizeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 40;
		c.insets = new Insets(0, 10, 0, 0);
		resizePanel.add(heighFormattedText, c);
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.insets = new Insets(0, 0, 0, 0);
		resizePanel.add(widthResizeLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 40;
		c.insets = new Insets(0, 10, 0, 0);
		resizePanel.add(widthFormattedText, c);
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		resizePanel.add(proportionBox, c);
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 0;
		c.gridwidth = 2;
		resizePanel.add(resizeDo1, c);
	}

	private void setWatermark() {
		watermarkPanel = new JPanel();
		JPanel sizePanel = new JPanel(new GridLayout(0, 5));
		JPanel opaPanel = new JPanel(new GridLayout(0, 4));
		watermarkPanel.setPreferredSize(new Dimension(250, 185));
		watermarkPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		watermarkPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Znak wodny"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		boldBox = new JCheckBox("Pogrubione");
		italicBox = new JCheckBox("Kursywa");

		verySmallWaterMarkButton = new JRadioButton("Bardzo ma³e");
		smallWaterMarkButton = new JRadioButton("Ma³e");
		mediumWaterMarkButton = new JRadioButton("Œrednie");
		bigWaterMarkButton = new JRadioButton("Du¿e");
		veryBigWaterMarkButton = new JRadioButton("Bardzo du¿e");

		sizeButtonGroup = new ButtonGroup();
		sizeButtonGroup.add(verySmallWaterMarkButton);
		sizeButtonGroup.add(smallWaterMarkButton);
		sizeButtonGroup.add(mediumWaterMarkButton);
		sizeButtonGroup.add(bigWaterMarkButton);
		sizeButtonGroup.add(veryBigWaterMarkButton);

		sizePanel.add(verySmallWaterMarkButton);
		sizePanel.add(smallWaterMarkButton);
		sizePanel.add(mediumWaterMarkButton);
		sizePanel.add(bigWaterMarkButton);
		sizePanel.add(veryBigWaterMarkButton);

		noOpaButton = new JRadioButton("Bark");
		midOpaButton = new JRadioButton("Œrednia");
		bigOpaButton = new JRadioButton("Du¿e");
		maxOpaButton = new JRadioButton("Najwiêksza");

		opaButtonGroup = new ButtonGroup();
		opaButtonGroup.add(noOpaButton);
		opaButtonGroup.add(midOpaButton);
		opaButtonGroup.add(bigOpaButton);
		opaButtonGroup.add(maxOpaButton);

		opaPanel.add(noOpaButton);
		opaPanel.add(midOpaButton);
		opaPanel.add(bigOpaButton);
		opaPanel.add(maxOpaButton);

		typeOfWatermarkSpinner = new JComboBox<>(AllFonts.listOfFamilyFonts());

		colorsForComboBox = new JComboBox<String>(new String[] { "Zielony",
				"Bia³y", "Czerwony", "Czarny", "¯ó³ty" });

		placeOfWatermarkSpinner = new JComboBox<String>(new String[] {
				"Prawy górny róg", "Góra œrodek", "Lewy góry róg",
				"Œrodek lewo", "Œrodek", "Œrodek prawo", "Dolny lewy róg",
				"Dó³ œrodek", "Prawy dolny róg" });

		watermarkDo = new JToggleButton("Znak wodny");

		textField = new JTextField(10);
		textField.setDocument(new JTextFieldLimit(10));
		textField.setPreferredSize(new Dimension(180, 30));
		c.fill = c.HORIZONTAL;
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		watermarkPanel.add(typeOfWatermarkSpinner, c);
		c.gridx = 0;
		c.gridy = 1;
		watermarkPanel.add(placeOfWatermarkSpinner, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.ipadx = 180;
		watermarkPanel.add(textField, c);

		c.fill = c.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		watermarkPanel.add(watermarkDo, c);
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		watermarkPanel.add(sizePanel, c);
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1;
		watermarkPanel.add(opaPanel, c);
		c.gridx = 0;
		c.gridy = 6;
		watermarkPanel.add(colorsForComboBox, c);
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
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		//setLayout(new GridLayout(2,2));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		setJMenuBar(menuBar);
		c.gridx = 0;
		c.gridy = 0;
		add(imageInfoPanel,c);
		c.gridx = 0;
		c.gridy = 1;
		add(effectsPanel,c);
		c.gridx = 1;
		c.gridy = 0;
		add(resizePanel,c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 2;
		add(watermarkPanel,c);
		c.gridheight =1;
		c.gridx = 1;
		c.gridy = 1;
		add(buttonsPanel,c);
	}
	private void previewMethod() {
		bImage = DeepCopyBI.deepCopy(backupImage.getBufferedImage());
		System.out.println(bImage.equals(backupImage.getBufferedImage()));
		
		if (radioButton5.isSelected())
			;
		else {
			if (radioButton1.isSelected()) {
				MarvinImagePlugin imagePlugin = MarvinPluginLoader
						.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");
				MarvinImage imgTmp = new MarvinImage(bImage);
				imagePlugin.process(imgTmp, imgTmp);
				imgTmp.update();
				bImage = imgTmp.getBufferedImage();
			}
			if (radioButton2.isSelected()) {

				MarvinImagePlugin imagePlugin = MarvinPluginLoader
						.loadImagePlugin("org.marvinproject.image.color.blackAndWhite.jar");
				MarvinImage imgTmp = new MarvinImage(bImage);
				imagePlugin.process(imgTmp, imgTmp);
				imgTmp.update();
				bImage = imgTmp.getBufferedImage();
			}
			if (radioButton3.isSelected()) {

				MarvinImagePlugin imagePlugin = MarvinPluginLoader
						.loadImagePlugin("org.marvinproject.image.blur.gaussianBlur.jar");
				MarvinImage imgTmp = new MarvinImage(bImage);
				imagePlugin.process(imgTmp, imgTmp);
				imgTmp.update();
				bImage = imgTmp.getBufferedImage();
			}
			if (radioButton4.isSelected()) {

				MarvinImagePlugin imagePlugin = MarvinPluginLoader
						.loadImagePlugin("org.marvinproject.image.color.sepia.jar");
				MarvinImage imgTmp = new MarvinImage(bImage);
				imagePlugin.process(imgTmp, imgTmp);
				imgTmp.update();
				bImage = imgTmp.getBufferedImage();
			}
		}
		if (watermarkDo.isSelected()) {
			String tmpValueString = mediumWaterMarkButton.getText();
			if (verySmallWaterMarkButton.isSelected())
				tmpValueString = verySmallWaterMarkButton.getText();
			if (smallWaterMarkButton.isSelected())
				tmpValueString = smallWaterMarkButton.getText();
			if (mediumWaterMarkButton.isSelected())
				tmpValueString = mediumWaterMarkButton.getText();
			if (bigWaterMarkButton.isSelected())
				tmpValueString = bigWaterMarkButton.getText();
			if (veryBigWaterMarkButton.isSelected())
				tmpValueString = veryBigWaterMarkButton.getText();

			float tmpValue = 1f;

			if (noOpaButton.isSelected())
				tmpValue = 1f;
			if (midOpaButton.isSelected())
				tmpValue = 0.50f;
			if (bigOpaButton.isSelected())
				tmpValue = 0.35f;
			if (maxOpaButton.isSelected())
				tmpValue = 0.20f;

			String.valueOf(colorsForComboBox.getSelectedItem());

			WaterMarkImage.addWaterMark(bImage, textField.getText(),
					String.valueOf(typeOfWatermarkSpinner
							.getSelectedItem()), String
							.valueOf(placeOfWatermarkSpinner
									.getSelectedItem()), tmpValue,
					tmpValueString, String.valueOf(colorsForComboBox
							.getSelectedItem()));
		}

		if (resizeDo1.isSelected()) {
			if (heighFormattedText.getText().length() > 0
					&& widthFormattedText.getText().length() > 0) {
				int h = (bImage.getHeight() / 100)
						* Integer.parseInt(heighFormattedText.getText());
				int w = (bImage.getWidth() / 100)
						* Integer.parseInt(widthFormattedText.getText());

				bImage = ImageResize.resize(bImage, w, h);
			}
		}
		

		mwMarvinWindowsPerview.MakeWindowPerview(bImage);
		previewDone = true;
	}
}
