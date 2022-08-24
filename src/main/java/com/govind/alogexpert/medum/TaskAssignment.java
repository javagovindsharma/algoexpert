package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TaskAssignment {

	public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
		ArrayList<ArrayList<Integer>> pairedTasks= new ArrayList<ArrayList<Integer>>();
		HashMap<Integer,ArrayList<Integer>> taskDurationsToIndices=getTaskDurationToIndices(tasks);
		
		ArrayList<Integer> sortedTask=tasks;
		Collections.sort(sortedTask);
		
		for(int idx=0;idx<k;idx++) {
			int task1Duration =sortedTask.get(idx);
			ArrayList<Integer> indicesWithTask1Duration=taskDurationsToIndices.get(task1Duration);
			int task1Index=indicesWithTask1Duration.remove(indicesWithTask1Duration.size()-1);
			
			int task2SortedIndex = tasks.size()-1 -idx;
			int task2Duration =sortedTask.get(task2SortedIndex);
			ArrayList<Integer> indicesWithTask2Duration=taskDurationsToIndices.get(task2Duration);
			int task2Index = indicesWithTask2Duration.remove(indicesWithTask2Duration.size()-1);
			ArrayList<Integer> pairedTask=new ArrayList<Integer>();
			pairedTask.add(task1Index);
			pairedTask.add(task2Index);
			pairedTasks.add(pairedTask);
		}
		return pairedTasks;
	}

	private HashMap<Integer, ArrayList<Integer>> getTaskDurationToIndices(ArrayList<Integer> tasks) {
		HashMap<Integer, ArrayList<Integer>> taskDurationToIndices=new HashMap<Integer, ArrayList<Integer>>();
		for(int idx=0; idx < tasks.size(); idx++) {
			int taskDuration=tasks.get(idx);
			if(taskDurationToIndices.containsKey(taskDuration)) {
				taskDurationToIndices.get(taskDuration).add(idx);
			}else {
				ArrayList<Integer> temp=new ArrayList<Integer>();
				temp.add(idx);
				taskDurationToIndices.put(taskDuration, temp);
			}
		}
		return taskDurationToIndices;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
