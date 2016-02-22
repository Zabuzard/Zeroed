package de.zabuza.armA2.ace.zeroed.data;

/**
 * Data entry which has values for elevation and wind for a given distance.
 * 
 * @author Zabuza
 * 
 */
public final class DataEntry {

	/**
	 * The distance of the entry.
	 */
	private final int distance;
	/**
	 * The elevation of the entry.
	 */
	private final double elevation;
	/**
	 * The wind of the entry.
	 */
	private final double wind;

	/**
	 * Creates a new data entry with a value for distance, elevation and wind.
	 * 
	 * @param thatDistance
	 *            distance of the entry
	 * @param thatElevation
	 *            elevation of the entry
	 * @param thatWind
	 *            wind of the entry
	 */
	public DataEntry(final int thatDistance, final double thatElevation,
			final double thatWind) {
		distance = thatDistance;
		elevation = thatElevation;
		wind = thatWind;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the elevation
	 */
	public double getElevation() {
		return elevation;
	}

	/**
	 * @return the wind
	 */
	public double getWind() {
		return wind;
	}

	@Override
	public String toString() {
		return distance + "\t" + elevation + "\t" + wind;
	}

}
