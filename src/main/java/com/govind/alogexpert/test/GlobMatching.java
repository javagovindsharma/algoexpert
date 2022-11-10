package com.govind.alogexpert.test;

public class GlobMatching {
	
	public static final String convertGlobToRegex(String pattern) {
	    StringBuilder sb = new StringBuilder(pattern.length());
	    int inGroup = 0;
	    int inClass = 0;
	    int firstIndexInClass = -1;
	    char[] arr = pattern.toCharArray();
	    for (int i = 0; i < arr.length; i++) {
	        char ch = arr[i];
	        switch (ch) {
	            case '\\':
	                if (++i >= arr.length) {
	                    sb.append('\\');
	                } else {
	                    char next = arr[i];
	                    switch (next) {
	                        case ',':
	                            // escape not needed
	                            break;
	                        case 'Q':
	                        case 'E':
	                            // extra escape needed
	                            sb.append('\\');
	                        default:
	                            sb.append('\\');
	                    }
	                    sb.append(next);
	                }
	                break;
	            case '*':
	                if (inClass == 0)
	                    sb.append(".*");
	                else
	                    sb.append('*');
	                break;
	            case '?':
	                if (inClass == 0)
	                    sb.append('.');
	                else
	                    sb.append('?');
	                break;
	            case '[':
	                inClass++;
	                firstIndexInClass = i+1;
	                sb.append('[');
	                break;
	            case ']':
	                inClass--;
	                sb.append(']');
	                break;
	            case '.':
	            case '(':
	            case ')':
	            case '+':
	            case '|':
	            case '^':
	            case '$':
	            case '@':
	            case '%':
	                if (inClass == 0 || (firstIndexInClass == i && ch == '^'))
	                    sb.append('\\');
	                sb.append(ch);
	                break;
	            case '!':
	                if (firstIndexInClass == i)
	                    sb.append('^');
	                else
	                    sb.append('!');
	                break;
	            case '{':
	                inGroup++;
	                sb.append('(');
	                break;
	            case '}':
	                inGroup--;
	                sb.append(')');
	                break;
	            case ',':
	                if (inGroup > 0)
	                    sb.append('|');
	                else
	                    sb.append(',');
	                break;
	            default:
	                sb.append(ch);
	        }
	    }
	    return sb.toString();
	}

	public static boolean globMatching(String fileName, String pattern) {
		String ptrn=convertGlobToRegex(pattern);
		return fileName.matches(ptrn);
	}

	public static void main(String[] args) {
		String fileName = "abcdefg";
		String pattern = "abc??efg";//"a*e?g";
		fileName.length();
	   // pattern =pattern.replace("*",".*");
	 //   pattern =pattern.replace("?",".?");
		System.out.println(GlobMatching.globMatching(fileName, pattern));
		//assertTrue(GlobMatching.globMatching(fileName, pattern) == true);

	}

}
