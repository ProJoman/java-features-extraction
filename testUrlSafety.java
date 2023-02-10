package feturesExtraction2;

import java.net.URI;
import java.net.URISyntaxException;

public class testUrlSafety {
	//1st feature, URL Length
	public static int getUrlLength(URI uri) {

		return uri.toString().length();
	}

	//2nd, path length
	public static int getPathLength(URI uri) {

		String urlPath = uri.getPath();

		//Some URLs dosen't have path, and null will causing error in length() method
		if (urlPath == null) {
			urlPath = "";
		}

		return urlPath.length();
	}

	//3rd feature method, Query length
	public static int getQueryLength(URI uri) {

		String urlQuery = uri.getQuery();

		//Some URLs dosen't have Query, and null will causing error in length() method
		if (urlQuery == null) {
			urlQuery = "";
		}

		return urlQuery.length();
	}

	//4th feature, URL Letter Count
	public static int countUrlLetters(URI uri) {

		int UrlLetterCount = 0;

		//url content loop
		for (int k = 0; k < getUrlLength(uri); k++) {
			if (Character.isLetter(uri.toString().charAt(k))) {
				UrlLetterCount++;
			}
		}

		return UrlLetterCount;
	}

	//5th feature, URL Digit Count
	public static int countUrlDigits(URI uri) {

		//uri array loop
		int UrlDigitCount = 0;

		//url content loop
		for (int k = 0; k < getUrlLength(uri); k++) {
			if (Character.isDigit(uri.toString().charAt(k))) {
				UrlDigitCount++;
			}
		}
		return UrlDigitCount;
	}

	//6th feature, HOST_Letter_Count
	public static int countHostLetters(URI uri) {

		int HostLetterCount = 0;

		//url content loop
		for (int k = 0; k < getUrlLength(uri); k++) {
			if (Character.isLetter(uri.toString().charAt(k))) {
				HostLetterCount++;
			}
		}
		return HostLetterCount;
	}

	//7th feature, HOST_Digit_Count
	public static int countHostDigits(URI uri) {

		int hostDigitCount = 0;

		//url content loop
		for (int k = 0; k < getUrlLength(uri); k++) {
			if (Character.isDigit(uri.toString().charAt(k))) {
				hostDigitCount++;
			}
		}
		return hostDigitCount;
	}

	//8th symbolCount_URL
	public static int countUrlSymbols(URI uri) {

		String specialChars = ";,/?:@&=+$-_.!~*'( )#";
		int delimeter_C = 0;

		//url content loop
		for (int i = 0; i < getUrlLength(uri); i++) {
			if (specialChars.contains(String.valueOf(uri.toString().charAt(i)))) {
				delimeter_C++;
			}
		}
		return delimeter_C;

	}

	//9th, SpcharUrl
	public static int countSpecialChars(URI uri) {

		String specialChars = ",|#?!$";
		int count = 0;

		for (int i = 0; i < getUrlLength(uri); i++) {
			if (specialChars.contains(String.valueOf(uri.toString().charAt(i)))) {
				count++;
			}
		}
		return count;
	}

	//10th feature, Query_Digit_Count
	public static int countQueryDigits(URI uri) {

		int count_Q = 0;
		String urlQuery = uri.getQuery();

		//Some URLs dosen't have Query, and null will causing error in length() method
		if (urlQuery == null) 
			urlQuery = "";


		//url content loop
		for (int k = 0; k < urlQuery.length(); k++) {
			if (Character.isDigit(urlQuery.toString().charAt(k))) {
				count_Q++;
			}
		}
		return count_Q++;
	}

	//11th Path_URL_Ratio
	public static double getPathURLRatio(URI uri) {

		return (uri.getPath().length()) / getUrlLength(uri);
	}
		
	public static boolean isUrlSafe(String url) {
		double features[] = null;
		
		 try {
			 
			URI uri = new URI(url);
			features = extract_features(uri);
			//double[] results = mode.score(features);
			         
		} catch (URISyntaxException e) {
			System.out.println(e);
		}

		return features[11] == 0 ;
	}
	
	public static double[] extract_features(URI uri){
		
        double[] features = new double[12];
        
        features[0] = getQueryLength(uri);
        features[1] = getPathLength(uri);
        features[2] = getQueryLength(uri);
        features[3] = countUrlLetters(uri);
        features[4] = countUrlDigits(uri);
        features[5] = countHostLetters(uri);
        features[6] = countHostDigits(uri);
        features[7] = countUrlSymbols(uri);
        features[8] = countSpecialChars(uri);
        features[9] = countQueryDigits(uri);
        features[10] = getPathURLRatio(uri);
        features[11] = 1;

       // System.out.println(features[11]);
       return features;
   }
	
	public static void main(String[] args) {
	String url = "http//1337x.to/torrent/1048648/American-Sniper-2014-MD-iTALiAN-DVDSCR-X264-BST-MT/";
	System.out.println(isUrlSafe(url));
	
	}

}
