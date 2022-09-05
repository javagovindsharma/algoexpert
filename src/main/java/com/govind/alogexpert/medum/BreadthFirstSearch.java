package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();

		public Node(String name) {
			this.name = name;
		}

		public List<String> breadthFirstSearch(List<String> array) {
			Queue<Node> queue=new LinkedList<Node>();
			queue.add(this);
			while(!queue.isEmpty()) {
				Node current=queue.poll();
				array.add(current.name);
				queue.addAll(current.children);
			}
			return array;
		}

		public Node addChild(String name) {
			Node child = new Node(name);
			children.add(child);
			return this;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
