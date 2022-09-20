package com.govind.alogexpert.hard;

import java.util.ArrayList;

public class GenerateDivTags {
	
	public ArrayList<String> generateDivTags(int numberOfTags) {
		ArrayList<String> matchedDivTags=new ArrayList<String>();
		generateDivTagsFromPrefix(numberOfTags,numberOfTags,"",matchedDivTags);
		return matchedDivTags;
	}

	private void generateDivTagsFromPrefix(int openingTagsNeeded, int closingTagsNeeded, String prefix,
			ArrayList<String> result) {
		if(openingTagsNeeded>0) {
			String newPrefix=prefix+"<div>";
			generateDivTagsFromPrefix(openingTagsNeeded-1,closingTagsNeeded,newPrefix,result);
		}
		if(openingTagsNeeded<closingTagsNeeded) {
			String newPrefix=prefix+"</div>";
			generateDivTagsFromPrefix(openingTagsNeeded,closingTagsNeeded-1,newPrefix,result);
		}
		
		if(closingTagsNeeded==0) {
			result.add(prefix);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
