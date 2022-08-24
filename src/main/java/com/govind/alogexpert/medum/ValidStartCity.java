package com.govind.alogexpert.medum;

public class ValidStartCity {

	public int validStartingCity(int[] distances, int[] fuel, int mpg) {
		int numberOfCity = distances.length;
		int milesRemaining = 0;

		int indexOfStartingCityCandidate = 0;
		int milesRemainingAtStartingCityCandidate = 0;

		for (int cityIdx = 1; cityIdx < numberOfCity; cityIdx++) {
			int distanceFromPreviousCity = distances[cityIdx - 1];
			int fuelFromPreviousCity = fuel[cityIdx - 1];
			milesRemaining += fuelFromPreviousCity * mpg - distanceFromPreviousCity;

			if (milesRemaining < milesRemainingAtStartingCityCandidate) {
				milesRemainingAtStartingCityCandidate = milesRemaining;
				indexOfStartingCityCandidate = cityIdx;
			}
		}
		return indexOfStartingCityCandidate;
	}

	// solution 1

	public int validStartingCity1(int[] distances, int[] fuel, int mpg) {
		int numberOfCity = distances.length;
		for (int startCityIdx = 0; startCityIdx < numberOfCity; startCityIdx++) {
			int milesRemaining = 0;
			for (int currentCityIdx = startCityIdx; currentCityIdx < startCityIdx + numberOfCity; currentCityIdx++) {
				if (milesRemaining < 0) {
					continue;
				}
				int currentCityIdxRotated = currentCityIdx % numberOfCity;
				int fuelFromCurrentCity = fuel[currentCityIdxRotated];
				int distanceToNextCity = distances[currentCityIdxRotated];
				milesRemaining += fuelFromCurrentCity * mpg - distanceToNextCity;
			}
			if (milesRemaining >= 0) {
				return startCityIdx;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] distances = new int[] { 5, 25, 15, 10, 15 };
		int[] fuel = new int[] { 1, 2, 1, 0, 3 };
		int mpg = 10;
		int expected = 4;
		Object actual = new ValidStartCity().validStartingCity(distances, fuel, mpg);
		System.out.println(expected + " == " + actual);

	}

}
