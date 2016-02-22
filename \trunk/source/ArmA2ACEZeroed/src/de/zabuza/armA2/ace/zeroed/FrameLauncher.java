package de.zabuza.armA2.ace.zeroed;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.zabuza.armA2.ace.zeroed.gui.controller.MainFrameController;
import de.zabuza.armA2.ace.zeroed.gui.view.MainFrameView;

/**
 * Starts the tool in a frame.
 * 
 * @author Zabuza
 * 
 */
public final class FrameLauncher {

	/**
	 * Launch the view.
	 * 
	 * @param args
	 *            Not supported
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setResizable(false);
					frame.setTitle("Zeroed - ArmaA2 ACE");
					frame.setBounds(100, 100, 549, 452);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					Dimension screenSize =
							Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((screenSize.width - frame.getWidth()) / 2,
							(screenSize.height - frame.getHeight()) / 2);
					
					MainFrameView window =
							new MainFrameView(frame.getContentPane());
					
					try {
						MainFrameController controller =
								new MainFrameController(window);
						controller.initialize();
					} catch (Exception e) {
						window.logError("Ein unbekannter Fehler trat auf.");
						if (e.getMessage() != null) {
							window.logError(e.getMessage());
						}
					} finally {
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Utility class. No implementation.
	 */
	private FrameLauncher() {

	}

}
