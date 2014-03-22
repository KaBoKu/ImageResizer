package main.gui.frames;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.gui.MarvinTest;

public class MainWindowTEst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(laf.getName())){
                        try {
                            UIManager.setLookAndFeel(laf.getClassName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
				MainWindow mw = new MainWindow();
				//new MarvinTest();
			}
		});
	}
}
