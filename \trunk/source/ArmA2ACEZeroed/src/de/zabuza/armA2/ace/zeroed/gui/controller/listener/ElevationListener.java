package de.zabuza.armA2.ace.zeroed.gui.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.zabuza.armA2.ace.zeroed.calculation.Calculator;
import de.zabuza.armA2.ace.zeroed.data.DataEntry;
import de.zabuza.armA2.ace.zeroed.gui.view.MainFrameView;

/**
 * Listener of the elevation button.
 * 
 * @author Zabuza
 * 
 */
public final class ElevationListener implements ActionListener {

	/**
	 * The view of the main frame.
	 */
	private final MainFrameView view;

	/**
	 * Creates a new listener of the elevation button.
	 * 
	 * @param thatView
	 *            view of the main frame
	 */
	public ElevationListener(final MainFrameView thatView) {
		view = thatView;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		String airrouteText = view.getAirrouteFieldText();
		if (airrouteText.equals("")) {
			view.logError(MainFrameView.EMPTY + "Luftlinie");
		} else {
			int airroute = -1;
			try {
				airroute = Integer.parseInt(airrouteText);
			} catch (NumberFormatException ex) {
				view.logError(MainFrameView.WRONG_FORMAT + "Luftlinie");
			}
			if (airroute != -1) {
				try {
					DataEntry closerEntry = view.getSelectedDataOfCaliberBox()
							.getCloserEntry(airroute);
					DataEntry furtherEntry = view.getSelectedDataOfCaliberBox()
							.getFurtherEntry(airroute);
					view.setElevationFieldText(Calculator.calculateElevation(
							airroute, closerEntry, furtherEntry) + "");
					// view.log("Ausrichtung berechnet");
				} catch (IllegalArgumentException ex) {
					view.logError(MainFrameView.CALIBER_OUT_OF_DISTANCE);
				}
			}
		}
	}
}
