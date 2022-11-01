package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AirportConnections {

	public static int airportConnections(List<String> airports, List<List<String>> routes, String startingAirport) {
		Map<String,AirportNode> airportGraph=createAirportGraph(airports,routes);
		List<AirportNode> unreachableAirportNode=getUnreachableAirportNodes(airportGraph,airports,startingAirport);
		markUnreachableConnections(airportGraph,unreachableAirportNode);
		return getMinNumberOfNewConnections(airportGraph,unreachableAirportNode);
	}

	
	private static Map<String, AirportNode> createAirportGraph(List<String> airports, List<List<String>> routes) {
		Map<String,AirportNode> airportGraph=new HashMap<String, AirportNode>();
		
		for(String airport:airports) {
			airportGraph.put(airport, new AirportNode(airport));
		}
		
		for(List<String> route:routes) {
			String airport=route.get(0);
			String connection=route.get(1);
			airportGraph.get(airport).connections.add(connection);
		}
		return airportGraph;
	}


	private static List<AirportNode> getUnreachableAirportNodes(Map<String, AirportNode> airportGraph,
			List<String> airports, String startingAirport) {
		Set<String> visitedAirport=new HashSet<String>();
		depthFirstTraversalAirports(airportGraph,startingAirport,visitedAirport);
		List<AirportNode> unreachableAirportNodes=new ArrayList<AirportNode>();
		for(String airport:airports) {
			if(visitedAirport.contains(airport)) continue;
			AirportNode airportNode=airportGraph.get(airport);
			airportNode.isReachable=false;
			unreachableAirportNodes.add(airportNode);
		}
		return unreachableAirportNodes;
	}


	private static void depthFirstTraversalAirports(Map<String, AirportNode> airportGraph, String airport,
			Set<String> visitedAirport) {
		if(visitedAirport.contains(airport)) return;
		visitedAirport.add(airport);
		List<String> connections=airportGraph.get(airport).connections;
		for(String connection:connections) {
			depthFirstTraversalAirports(airportGraph, connection, visitedAirport);
		}
		
	}


	private static void markUnreachableConnections(Map<String, AirportNode> airportGraph,
			List<AirportNode> unreachableAirportNode) {
		
		for(AirportNode airportNode:unreachableAirportNode) {
			String airport=airportNode.airport;
			List<String> unreachableConnections=new ArrayList<String>();
			Set<String> visitedAirport=new HashSet<String>();
			depthFirstAddUnreachableConnections(airportGraph, airport, unreachableConnections,visitedAirport);
			airportNode.unreachableConnections=unreachableConnections;
		}
		
	}


	private static void depthFirstAddUnreachableConnections(Map<String, AirportNode> airportGraph, String airport,
			List<String> unreachableConnection, Set<String> visitedAirport) {
		
		if(airportGraph.get(airport).isReachable) return;
		if(visitedAirport.contains(airport)) return;
		
		visitedAirport.add(airport);
		unreachableConnection.add(airport);
		List<String> connections=airportGraph.get(airport).connections;
		for(String connection:connections) {
			depthFirstAddUnreachableConnections(airportGraph, connection, unreachableConnection, visitedAirport);
		}
		
	}


	private static int getMinNumberOfNewConnections(Map<String, AirportNode> airportGraph,
			List<AirportNode> unreachableAirportNode) {
		
		unreachableAirportNode.sort((a1,a2)->a2.unreachableConnections.size()-a1.unreachableConnections.size());
		
		int numberOfNewConnections=0;
		for(AirportNode airportNode:unreachableAirportNode) {
			if(airportNode.isReachable) continue;
			numberOfNewConnections++;
			for(String connection:airportNode.unreachableConnections) {
				airportGraph.get(connection).isReachable=true;
			}
		}
		return numberOfNewConnections;
	}


	static class AirportNode{
		String airport;
		List<String> connections;
		boolean isReachable;
		List<String> unreachableConnections;
		public AirportNode(String airport) {
			this.airport = airport;
			this.connections = new ArrayList<String>();
			this.isReachable = true;
			this.unreachableConnections =  new ArrayList<String>();
		}
	}
	// Tester

	static List<String> AIRPORTS = new ArrayList<String>(Arrays.asList("BGI", "CDG", "DEL", "DOH", "DSM", "EWR", "EYW",
			"HND", "ICN", "JFK", "LGA", "LHR", "ORD", "SAN", "SFO", "SIN", "TLV", "BUD"));

	static String STARTING_AIRPORT = "LGA";

	public static void main(String[] args) {
		List<List<String>> routes = new ArrayList<List<String>>();
		routes.add(new ArrayList<String>(Arrays.asList("DSM", "ORD")));
		routes.add(new ArrayList<String>(Arrays.asList("ORD", "BGI")));
		routes.add(new ArrayList<String>(Arrays.asList("BGI", "LGA")));
		routes.add(new ArrayList<String>(Arrays.asList("SIN", "CDG")));
		routes.add(new ArrayList<String>(Arrays.asList("CDG", "SIN")));
		routes.add(new ArrayList<String>(Arrays.asList("CDG", "BUD")));
		routes.add(new ArrayList<String>(Arrays.asList("DEL", "DOH")));
		routes.add(new ArrayList<String>(Arrays.asList("DEL", "CDG")));
		routes.add(new ArrayList<String>(Arrays.asList("TLV", "DEL")));
		routes.add(new ArrayList<String>(Arrays.asList("EWR", "HND")));
		routes.add(new ArrayList<String>(Arrays.asList("HND", "ICN")));
		routes.add(new ArrayList<String>(Arrays.asList("HND", "JFK")));
		routes.add(new ArrayList<String>(Arrays.asList("ICN", "JFK")));
		routes.add(new ArrayList<String>(Arrays.asList("JFK", "LGA")));
		routes.add(new ArrayList<String>(Arrays.asList("EYW", "LHR")));
		routes.add(new ArrayList<String>(Arrays.asList("LHR", "SFO")));
		routes.add(new ArrayList<String>(Arrays.asList("SFO", "SAN")));
		routes.add(new ArrayList<String>(Arrays.asList("SFO", "DSM")));
		routes.add(new ArrayList<String>(Arrays.asList("SAN", "EYW")));
		assertTrue(AirportConnections.airportConnections(AIRPORTS, routes, STARTING_AIRPORT) == 3);

	}

}
