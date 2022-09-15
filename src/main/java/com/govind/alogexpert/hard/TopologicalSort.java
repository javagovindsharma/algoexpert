package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort {

	// solution 1
	/*
	 * public static List<Integer> topologicalSort(List<Integer> jobs,
	 * List<Integer[]> deps) { JobGraph jobGraph = createJobGraph(jobs, deps);
	 * return getOrderedJobs(jobGraph); }
	 * 
	 * private static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]>
	 * deps) { JobGraph graph = new JobGraph(jobs); for (Integer[] dep : deps) {
	 * graph.addPrereq(dep[1], dep[0]); } return graph; }
	 * 
	 * private static List<Integer> getOrderedJobs(JobGraph graph) { List<Integer>
	 * orderedJobs = new ArrayList<Integer>(); List<JobNode> nodes = new
	 * ArrayList<JobNode>(graph.nodes); while (nodes.size() > 0) { JobNode node =
	 * nodes.get(nodes.size() - 1); nodes.remove(nodes.size() - 1); boolean
	 * containsCycle = depthFirstTraverse(node, orderedJobs); if (containsCycle)
	 * return new ArrayList<Integer>(); } return orderedJobs; }
	 * 
	 * private static boolean depthFirstTraverse(JobNode node, List<Integer>
	 * orderedJobs) { if (node.visited) return false; if (node.visiting) return
	 * true; node.visiting = true; for (JobNode prereqNode : node.prereqs) { boolean
	 * containsCycle = depthFirstTraverse(prereqNode, orderedJobs); if
	 * (containsCycle) return true; } node.visited = true; node.visiting = false;
	 * orderedJobs.add(node.job); return false; }
	 * 
	 * static class JobGraph { public List<JobNode> nodes; public Map<Integer,
	 * JobNode> graph;
	 * 
	 * public JobGraph(List<Integer> jobs) { nodes = new ArrayList<JobNode>(); graph
	 * = new HashMap<Integer, TopologicalSort.JobNode>(); for (Integer job : jobs) {
	 * addNode(job); } }
	 * 
	 * public void addPrereq(Integer job, Integer prereq) { JobNode jobNode =
	 * getNode(job); JobNode prereqNode = getNode(prereq);
	 * jobNode.prereqs.add(prereqNode); }
	 * 
	 * public void addNode(Integer job) { graph.put(job, new JobNode(job));
	 * nodes.add(graph.get(job)); }
	 * 
	 * public JobNode getNode(Integer job) { if (!graph.containsKey(job))
	 * addNode(job); return graph.get(job); } }
	 * 
	 * static class JobNode { public Integer job; public List<JobNode> prereqs;
	 * public boolean visited; public boolean visiting;
	 * 
	 * public JobNode(Integer job) { this.job = job; prereqs = new
	 * ArrayList<JobNode>(); visited = false; visiting = false; }
	 * 
	 * }
	 */

	// solution 2
	public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph jobGraph = createJobGraph(jobs, deps);
		return getOrderedJobs(jobGraph);
	}

	private static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph graph = new JobGraph(jobs);
		for (Integer[] dep : deps) {
			graph.addDep(dep[0], dep[1]);
		}
		return graph;
	}

	private static List<Integer> getOrderedJobs(JobGraph graph) {
		List<Integer> orderedJobs = new ArrayList<Integer>();
		List<JobNode> nodesWithNoPrereqs = new ArrayList<JobNode>(graph.nodes);

		for (JobNode node : graph.nodes) {
			if (node.numOfPrereqs == 0) {
				nodesWithNoPrereqs.add(node);
			}
		}
		while (nodesWithNoPrereqs.size() > 0) {
			JobNode node = nodesWithNoPrereqs.get(nodesWithNoPrereqs.size() - 1);
			nodesWithNoPrereqs.remove(nodesWithNoPrereqs.size() - 1);
			orderedJobs.add(node.job);
			removeDeps(node, nodesWithNoPrereqs);
		}

		boolean graphHasdges = false;
		for (JobNode node : graph.nodes) {
			if (node.numOfPrereqs > 0) {
				graphHasdges = true;
			}
		}
		return graphHasdges ? new ArrayList<Integer>() : orderedJobs;
	}

	private static void removeDeps(JobNode node, List<JobNode> nodesWithNoPrereqs) {
		while (node.deps.size() > 0) {
			JobNode dep = node.deps.get(node.deps.size() - 1);
			node.deps.remove(node.deps.size() - 1);
			dep.numOfPrereqs--;
			if (dep.numOfPrereqs == 0)
				nodesWithNoPrereqs.add(dep);
		}
	}

	static class JobGraph {
		public List<JobNode> nodes;
		public Map<Integer, JobNode> graph;

		public JobGraph(List<Integer> jobs) {
			nodes = new ArrayList<JobNode>();
			graph = new HashMap<Integer, TopologicalSort.JobNode>();
			for (Integer job : jobs) {
				addNode(job);
			}
		}

		public void addDep(Integer job, Integer dep) {
			JobNode jobNode = getNode(job);
			JobNode depNode = getNode(dep);
			jobNode.deps.add(depNode);
			depNode.numOfPrereqs++;
		}

		public void addNode(Integer job) {
			graph.put(job, new JobNode(job));
			nodes.add(graph.get(job));
		}

		public JobNode getNode(Integer job) {
			if (!graph.containsKey(job))
				addNode(job);
			return graph.get(job);
		}
	}

	static class JobNode {
		public Integer job;
		public List<JobNode> deps;
		public Integer numOfPrereqs;

		public JobNode(Integer job) {
			this.job = job;
			deps = new ArrayList<JobNode>();
			numOfPrereqs = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
