package com.govind.alogexpert.medum;

import java.util.ArrayList;

public class ValidIPAddres {

	public ArrayList<String> validIPAddresses(String string) {
		ArrayList<String> ipAddressFound = new ArrayList<String>();
		for (int i = 1; i < Math.min((int) string.length(), 4); i++) {
			String[] currentIPAddressParts = new String[] { "", "", "", "" };
			currentIPAddressParts[0] = string.substring(0, i);

			if (!isValidParts(currentIPAddressParts[0])) {
				continue;
			}

			for (int j = i + 1; j < i + Math.min((int) string.length() - i, 4); j++) {
				currentIPAddressParts[1] = string.substring(i, j);
				if (!isValidParts(currentIPAddressParts[1])) {
					continue;
				}
				for (int k = j + 1; k < j + Math.min((int) string.length() - j, 4); k++) {
					currentIPAddressParts[2] = string.substring(j, k);
					currentIPAddressParts[3] = string.substring(k);
					if (isValidParts(currentIPAddressParts[2]) && isValidParts(currentIPAddressParts[3])) {
						ipAddressFound.add(join(currentIPAddressParts));
					}
				}

			}
		}
		return ipAddressFound;
	}

	public  boolean isValidParts(String str) {
		int stringInt = Integer.parseInt(str);
		if (stringInt > 255)
			return false;

		return str.length() == Integer.toString(stringInt).length();
	}

	
	 public  String join(String[] str) {
		 StringBuilder sb=new StringBuilder();
		for(int l=0;l<str.length;l++) {
			sb.append(str[l]);
			if(l<str.length-1) {
				sb.append(".");
			}
		}
		 return sb.toString();
	 }
	public static void main(String[] args) {
		 String input = "1921680";
		 System.out.println(new ValidIPAddres().validIPAddresses(input));
	}
}
