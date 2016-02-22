package de.zabuza.armA2.ace.zeroed.data;

/**
 * Data for a caliber. Has several {@link DataEntry}s and can get the two
 * nearest entries to a given distance.
 * 
 * @author Zabuza
 * 
 */
public final class Data {

	/**
	 * Constant for a none valid distance.
	 */
	private static final int NO_DISTANCE = -1;

	/**
	 * Constant for a none valid index.
	 */
	public static final int NO_INDEX = -1;

	/**
	 * Gets data for a given caliber out of an array with data.
	 * 
	 * @param caliber
	 *            caliber for the data
	 * @param allData
	 *            data array to get data from
	 * @return data for given caliber or null if not found
	 */
	public static Data getData(final String caliber, final Data[] allData) {
		for (Data data : allData) {
			if (data.getCaliber().toLowerCase().equals(caliber.toLowerCase())) {
				return data;
			}
		}
		return null;
	}

	/**
	 * Caliber for the data.
	 */
	private final String caliber;

	/**
	 * Data entries.
	 */
	private final DataEntry[] entries;

	/**
	 * Creates a new data for a caliber with several entries.
	 * 
	 * @param thatCaliber
	 *            caliber for the data
	 * @param thatEntries
	 *            entries for the data
	 */
	public Data(final String thatCaliber, final DataEntry[] thatEntries) {
		caliber = thatCaliber;
		entries = thatEntries;
	}

	/**
	 * @return the caliber
	 */
	public String getCaliber() {
		return caliber;
	}

	/**
	 * Gets the nearest entry to the given distance which is closer to the
	 * shooter or null if no further entry was found.
	 * 
	 * @param distance
	 *            the distance to get the entry
	 * @return nearest entry to the given distance which is closer to the
	 *         shooter or null of no further entry was found
	 */
	public DataEntry getCloserEntry(final int distance) {
		int furtherIndex = getFurtherIndex(distance);
		// There is no further index
		if (furtherIndex == NO_INDEX) {
			// Try using the latest entry
			if (entries[entries.length - 1].getDistance() <= distance) {
				return entries[entries.length - 1];
			} else {
				return null;
			}
		} else if (entries[furtherIndex].getDistance() == distance) {
			return entries[furtherIndex];
		} else if (furtherIndex - 1 >= 0) {
			return entries[furtherIndex - 1];
		} else {
			return null;
		}
	}

	/**
	 * Gets the nearest entry to the given distance which is further from the
	 * shooter or null if no further entry was found.
	 * 
	 * @param distance
	 *            the distance to get the entry
	 * @return nearest entry to the given distance which is further from the
	 *         shooter or null of no further entry was found
	 */
	public DataEntry getFurtherEntry(final int distance) {
		int furtherIndex = getFurtherIndex(distance);
		if (furtherIndex == NO_INDEX) {
			return null;
		} else {
			return entries[furtherIndex];
		}
	}

	@Override
	public String toString() {
		return caliber;
	}

	/**
	 * Gets the nearest index to the given distance which is further from the
	 * shooter or {@link NO_INDEX} if no further index was found.
	 * 
	 * @param distance
	 *            the distance to get the entry
	 * @return nearest index to the given distance which is further from the
	 *         shooter or {@link NO_INDEX} of no further index was found
	 */
	private int getFurtherIndex(final int distance) {
		int i = 0;
		int currentDistance = NO_DISTANCE;
		do {
			currentDistance = entries[i].getDistance();
			i++;
		} while (i < entries.length && currentDistance < distance);

		if (currentDistance == NO_DISTANCE || currentDistance < distance) {
			return NO_INDEX;
		} else {
			return i - 1;
		}
	}

}
