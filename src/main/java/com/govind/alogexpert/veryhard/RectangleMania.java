package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RectangleMania {

	static String UP = "up";
	static String RIGHT = "right";
	static String DOWN = "down";
	static String LEFT = "left";

	// solution 1
	public static int rectangleMania1(List<Integer[]> coords) {
		Map<String, Map<String, List<Integer[]>>> coordstable = getCoordsTable1(coords);
		return getRectangeCount1(coords, coordstable);
	}

	private static Map<String, Map<String, List<Integer[]>>> getCoordsTable1(List<Integer[]> coords) {
		Map<String, Map<String, List<Integer[]>>> coordsTable = new HashMap<String, Map<String, List<Integer[]>>>();

		for (Integer[] coords1 : coords) {
			Map<String, List<Integer[]>> coords1Direction = new HashMap<String, List<Integer[]>>();
			coords1Direction.put(UP, new ArrayList<Integer[]>());
			coords1Direction.put(RIGHT, new ArrayList<Integer[]>());
			coords1Direction.put(DOWN, new ArrayList<Integer[]>());
			coords1Direction.put(LEFT, new ArrayList<Integer[]>());
			for (Integer[] coords2 : coords) {
				String coords2Direction = getCoordDirection(coords1, coords2);
				if (coords1Direction.containsKey(coords2Direction)) {
					coords1Direction.get(coords2Direction).add(coords2);
				}
			}
			String coord1String = coordToString(coords1);
			coordsTable.put(coord1String, coords1Direction);
		}

		return coordsTable;
	}

	private static String getCoordDirection(Integer[] coords1, Integer[] coords2) {
		if (coords2[1] == coords1[1]) {
			if (coords2[0] > coords1[0]) {
				return RIGHT;
			} else if (coords2[0] < coords1[0]) {
				return LEFT;
			}
		} else if (coords2[0] == coords1[0]) {
			if (coords2[1] > coords1[1]) {
				return UP;
			} else if (coords2[1] < coords1[1]) {
				return DOWN;
			}
		}
		return "";
	}

	private static int getRectangeCount1(List<Integer[]> coords,
			Map<String, Map<String, List<Integer[]>>> coordsTable) {
		int rectangleCount = 0;
		for (Integer[] coord : coords) {
			rectangleCount += clockwiseCountRectangles(coord, coordsTable, UP, coord);
		}
		return rectangleCount;
	}

	private static int clockwiseCountRectangles(Integer[] coord, Map<String, Map<String, List<Integer[]>>> coordsTable,
			String direction, Integer[] origin) {
		String coordString = coordToString(coord);
		if (direction == LEFT) {
			boolean rectangleFound = coordsTable.get(coordString).get(LEFT).contains(origin);
			return rectangleFound ? 1 : 0;

		} else {
			int rectangleCount = 0;
			String nextDirection = getNextClockwiseDirection(direction);
			for (Integer[] nextCoord : coordsTable.get(coordString).get(direction)) {
				rectangleCount += clockwiseCountRectangles(nextCoord, coordsTable, nextDirection, origin);
			}
			return rectangleCount;
		}

	}

	private static String getNextClockwiseDirection(String direction) {
		if (direction == UP)
			return RIGHT;
		if (direction == RIGHT)
			return DOWN;
		if (direction == DOWN)
			return LEFT;
		return "";
	}

	static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static String coordToString(Integer[] coords) {
		return Integer.toString(coords[0]) + "-" + Integer.toString(coords[1]);
	}

	// solution 2
	public static int rectangleMania2(List<Integer[]> coords) {
		Map<String, Map<Integer, List<Integer[]>>> coordstable = getCoordsTable2(coords);
		return getRectangeCount(coords, coordstable);
	}

	private static Map<String, Map<Integer, List<Integer[]>>> getCoordsTable2(List<Integer[]> coords) {
		Map<String, Map<Integer, List<Integer[]>>> coordsTable = new HashMap<String, Map<Integer, List<Integer[]>>>();
		coordsTable.put("x", new HashMap<Integer, List<Integer[]>>());
		coordsTable.put("y", new HashMap<Integer, List<Integer[]>>());
		for (Integer[] coord : coords) {
			if (!coordsTable.get("x").containsKey(coord[0])) {
				coordsTable.get("x").put(coord[0], new ArrayList<Integer[]>());
			}
			if (!coordsTable.get("y").containsKey(coord[1])) {
				coordsTable.get("y").put(coord[1], new ArrayList<Integer[]>());
			}

			coordsTable.get("x").get(coord[0]).add(coord);
			coordsTable.get("y").get(coord[1]).add(coord);
		}

		return coordsTable;
	}

	private static int getRectangeCount(List<Integer[]> coords,
			Map<String, Map<Integer, List<Integer[]>>> coordsTable) {
		int rectangleCount = 0;
		for (Integer[] coord : coords) {
			int lowerLefty = coord[1];
			rectangleCount += clockwiseCountRectangles(coord, coordsTable, UP, lowerLefty);
		}
		return rectangleCount;
	}

	private static int clockwiseCountRectangles(Integer[] coord1,
			Map<String, Map<Integer, List<Integer[]>>> coordsTable, String direction, int lowerLefty) {
		if (direction == DOWN) {
			List<Integer[]> relevantCoords = coordsTable.get("x").get(coord1[0]);
			for (Integer[] coord2 : relevantCoords) {
				int lowerRightY = coord2[1];
				if (lowerRightY == lowerLefty)
					return 1;
			}
			return 0;
		} else {
			int rectangleCount = 0;
			if (direction == UP) {
				List<Integer[]> relevantCoords = coordsTable.get("x").get(coord1[0]);
				for (Integer[] coord2 : relevantCoords) {
					boolean isAbove = coord2[1] > coord1[1];
					if (isAbove)
						rectangleCount += clockwiseCountRectangles(coord2, coordsTable, RIGHT, lowerLefty);
				}
			} else if (direction == RIGHT) {
				List<Integer[]> relevantCoords = coordsTable.get("y").get(coord1[1]);
				for (Integer[] coord2 : relevantCoords) {
					boolean isRight = coord2[0] > coord1[0];
					if (isRight)
						rectangleCount += clockwiseCountRectangles(coord2, coordsTable, DOWN, lowerLefty);
				}
			}
			return rectangleCount;
		}
	}

	// solution 3
	public static int rectangleMania(List<Integer[]> coords) {
		Set<String> coordstable = getCoordsTable(coords);
		return getRectangeCount(coords, coordstable);
	}

	
	private static Set<String> getCoordsTable(List<Integer[]> coords) {
		Set<String> coordsTable=new HashSet<String>();
		for(Integer[] coord:coords) {
			String coordString=coordToString(coord);
			coordsTable.add(coordString);
		}
		return coordsTable;
	}
	
	private static int getRectangeCount(List<Integer[]> coords, Set<String> coordstable) {
		int rectangleCount = 0;
		for(Integer[] coords1:coords) {
			for(Integer[] coords2:coords) {
				if(!isInUpperRight(coords1,coords2)) continue;
				String upperCoordString=coordToString(new Integer[] {coords1[0],coords2[1]});
				String rightCoordString=coordToString(new Integer[] {coords2[0],coords1[1]});
				if(coordstable.contains(upperCoordString) && coordstable.contains(rightCoordString))
					rectangleCount++;
			}
		}
		return rectangleCount;
	}


	private static boolean isInUpperRight(Integer[] coords1, Integer[] coords2) {
		return coords2[0]>coords1[0] && coords2[1]>coords1[1] ;
	}

	public static void main(String[] args) {
		List<Integer[]> coords = new ArrayList<Integer[]>(Arrays.asList(new Integer[] { 0, 0 }, new Integer[] { 0, 1 },
				new Integer[] { 1, 1 }, new Integer[] { 1, 0 }, new Integer[] { 2, 1 }, new Integer[] { 2, 0 },
				new Integer[] { 3, 1 }, new Integer[] { 3, 0 }));
		assertTrue(RectangleMania.rectangleMania(coords) == 6);

	}

}
