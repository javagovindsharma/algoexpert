package com.govind.alogexpert.hard;

public class InterweavingStrings {

	// solution 2
	public static boolean interweavingStrings(String one, String two, String three) {
		if(three.length()!=one.length()+two.length()) {
			return false;
		}
		Boolean[][]  cache=new Boolean[one.length()+1][two.length()+1];
		return areInterwoven(one,two,three,0,0,cache);
	}
	
	private static boolean areInterwoven(String one, String two, String three, int i, int j, Boolean[][] cache) {
	    if(cache[i][j]!=null) return cache[i][j];
	    
	    int k=i+j;
	    
	    if(k==three.length()) {
	    	return true;
	    }
	    
	    if(i<one.length() && one.charAt(i)==three.charAt(k)) {
	    	cache[i][j]=areInterwoven(one, two, three, i+1, j, cache);
	    	if(cache[i][j]) return true;
	    }
	    
	    if(j<two.length()&& two.charAt(j)==three.charAt(k)) {
	    	boolean result=areInterwoven(one, two, three, i, j+1, cache);
	    	cache[i][j]=result;
	    	return result;
	    }
	    cache[i][j]=false;
		return false;
	}

	// solution 1
	public static boolean interweavingStrings1(String one, String two, String three) {
		if(three.length()!=one.length()+two.length()) {
			return false;
		}
		return areInterwoven(one,two,three,0,0);
	}

	private static boolean areInterwoven(String one, String two, String three, int i, int j) {
		int k=i+j;
		if(k==three.length()) return true;
		
		if(i<one.length()&& one.charAt(i)==three.charAt(k)) {
			if(areInterwoven(one, two, three, i+1, j)) return true;
		}
		if(j<two.length()&& two.charAt(j)==three.charAt(k)) {
			return areInterwoven(one, two, three, i, j+1);
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
