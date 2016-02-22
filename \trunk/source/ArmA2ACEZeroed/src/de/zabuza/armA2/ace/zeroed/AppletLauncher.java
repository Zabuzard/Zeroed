package de.zabuza.armA2.ace.zeroed;

import java.awt.Container;

import javax.swing.JApplet;

import de.zabuza.armA2.ace.zeroed.gui.controller.MainFrameController;
import de.zabuza.armA2.ace.zeroed.gui.view.MainFrameView;

/**
 * Starts the tool in an applet.
 * 
 * @author Zabuza
 * 
 */
public final class AppletLauncher extends JApplet {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5872079495272280073L;

	/**
	 * True if tool has started.
	 */
	private boolean started = false;

	@Override
	public void init() {

	}

	@Override
	public void start() {
		if (!started) {
			started = true;
			try {
				Container container = new Container();
				MainFrameView window = new MainFrameView(container);
				try {
					MainFrameController controller = new MainFrameController(
							window);
					controller.initialize();
				} catch (Exception e) {
					window.logError("Ein unbekannter Fehler trat auf.");
					if (e.getMessage() != null) {
						window.logError(e.getMessage());
					}
				} finally {
					setContentPane(container);
					setFocusable(false);
					container.setFocusCycleRoot(true);
					container.setVisible(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		if (started) {
			started = false;
		}
	}

}
