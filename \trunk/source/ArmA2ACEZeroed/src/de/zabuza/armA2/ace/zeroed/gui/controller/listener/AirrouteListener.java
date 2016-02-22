package de.zabuza.armA2.ace.zeroed.gui.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.zabuza.armA2.ace.zeroed.calculation.Calculator;
import de.zabuza.armA2.ace.zeroed.gui.view.MainFrameView;

/**
 * Listener of the air route button.
 * 
 * @author Zabuza
 * 
 */
public final class AirrouteListener implements ActionListener {

	/**
	 * The view of the main frame.
	 */
	private final MainFrameView view;

	/**
	 * Creates a new listener of the air route button.
	 * 
	 * @param thatView
	 *            view of the main frame
	 */
	public AirrouteListener(final MainFrameView thatView) {
		view = thatView;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		String directText = view.getDirectFieldText();
		String altitudeText = view.getAltitudeFieldText();
		boolean oneEmpty = false;
		if (directText.equals("")) {
			view.logError(MainFrameView.EMPTY + "Direkt");
			oneEmpty = true;
		}
		if (altitudeText.equals("")) {
			view.logError(MainFrameView.EMPTY + "Höhenunterschied");
			oneEmpty = true;
		}
		if (!oneEmpty) {
			int direct = -1;
			int altitude = -1;
			try {
				direct = Integer.parseInt(directText);
			} catch (NumberFormatException ex) {
				view.logError(MainFrameView.WRONG_FORMAT + "Direkt");
			}
			try {
				altitude = Integer.parseInt(altitudeText);
			} catch (NumberFormatException ex) {
				view.logError(MainFrameView.WRONG_FORMAT + "Höhenunterschied");
			}
			if (direct != -1 && altitude != -1) {
				view.setAirrouteFieldText(Calculator.calculateAirRoute(direct,
						altitude) + "");
				// view.log("Luftlinie berechnet");
			}
		}
	}

}
