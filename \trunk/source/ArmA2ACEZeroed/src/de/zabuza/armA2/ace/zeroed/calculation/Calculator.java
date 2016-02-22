package de.zabuza.armA2.ace.zeroed.calculation;

import de.zabuza.armA2.ace.zeroed.data.DataEntry;

/**
 * Utility class. Can calculate elevation, wind and air routes.
 * 
 * @author Zabuza
 * 
 */
public final class Calculator {

	/**
	 * Standard value of the wind for the range tables.
	 */
	public static final int WIND_VALUE = 4;
	/**
	 * Factor which shifts a value in the decimal system for 2 positions.
	 */
	public static final double TWO_DECIMALS = 100.0;

	/**
	 * Calculates the air route to a target using the direct distance and the
	 * altitude difference.
	 * 
	 * @param directDistance
	 *            direct distance to the target
	 * @param altitudeDifference
	 *            altitude difference
	 * @return air route to a target in meter
	 */
	public static int calculateAirRoute(final int directDistance,
			final int altitudeDifference) {
		if (directDistance < 0 || altitudeDifference < 0) {
			throw new IllegalArgumentException("Arguments must not be "
					+ "negative.");
		}
		return (int) Math.round(Math.sqrt(Math.abs(Math.pow(directDistance, 2)
				- Math.pow(altitudeDifference, 2))));
	}

	/**
	 * Calculates the elevation in mil for a given distance using two
	 * surrounding entries.
	 * 
	 * @param distance
	 *            distance to the target
	 * @param closerEntry
	 *            data entry with a closer distance
	 * @param furtherEntry
	 *            data entry with a further distance
	 * @return elevation in mil for the given distance
	 */
	public static double calculateElevation(final int distance,
			final DataEntry closerEntry, final DataEntry furtherEntry) {
		if (closerEntry == null || furtherEntry == null
				|| distance < closerEntry.getDistance()
				|| distance > furtherEntry.getDistance()) {
			throw new IllegalArgumentException(
					"Arguments must not be null "
							+ "and distance must be between distances "
							+ "given from entries.");
		}
		if (closerEntry.getDistance() == distance) {
			return round2Dec(closerEntry.getElevation());
		} else if (furtherEntry.getDistance() == distance) {
			return round2Dec(furtherEntry.getElevation());
		}

		int absDistance = furtherEntry.getDistance()
				- closerEntry.getDistance();
		double absElevation = furtherEntry.getElevation()
				- closerEntry.getElevation();
		int absLeftDistance = distance - closerEntry.getDistance();
		double ratio = (absLeftDistance + 0.0) / absDistance;
		return round2Dec(closerEntry.getElevation() + ratio * absElevation);
	}

	/**
	 * Calculates the wind correction in mil for a given distance and crosswind
	 * using two surrounding entries.
	 * 
	 * @param distance
	 *            distance to the target
	 * @param crosswind
	 *            current crosswind
	 * @param closerEntry
	 *            data entry with a closer distance
	 * @param furtherEntry
	 *            data entry with a further distance
	 * @return wind correction in mil for the given distance
	 */
	public static double calculateWind(final int distance,
			final double crosswind, final DataEntry closerEntry,
			final DataEntry furtherEntry) {
		if (closerEntry == null || furtherEntry == null
				|| distance < closerEntry.getDistance()
				|| distance > furtherEntry.getDistance() || crosswind < 0) {
			throw new IllegalArgumentException(
					"Arguments must not be null"
							+ ", distance must be between distances given "
							+ "from entries and crossWind must not be "
							+ "negative.");
		}
		if (closerEntry.getDistance() == distance) {
			return round2Dec(closerEntry.getWind() * (crosswind / WIND_VALUE));
		} else if (furtherEntry.getDistance() == distance) {
			return round2Dec(furtherEntry.getWind() * (crosswind / WIND_VALUE));
		}

		int absDistance = furtherEntry.getDistance()
				- closerEntry.getDistance();
		double absWind = furtherEntry.getWind() - closerEntry.getWind();
		int absLeftDistance = distance - closerEntry.getDistance();
		double ratio = (absLeftDistance + 0.0) / absDistance;
		double wind = closerEntry.getWind() + ratio * absWind;
		return round2Dec(wind * (crosswind / WIND_VALUE));
	}

	/**
	 * Rounds a number on 2 decimal places after the dot.
	 * 
	 * @param number
	 *            number to round
	 * @return on 2 decimal places after the dot rounded number
	 */
	private static double round2Dec(final double number) {
		return Math.round(number * TWO_DECIMALS) / TWO_DECIMALS;
	}

	/**
	 * Utility class. No implementation.
	 */
	private Calculator() {

	}
}
