package featuresExtraction;

import java.net.*; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class featuresExtraction {

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



	@SuppressWarnings("resource")
	public static void main(String[] args) throws URISyntaxException, IOException {
		
		//URL counter, testing
		//int count1 = 0;
		int count2 = 0;

		//read input dataset csv file
		Scanner dataset = new Scanner(new FileReader("finalDataset.csv"));
		dataset.useDelimiter(",");

		//write the output dataset, features, labels, with urls
		FileWriter outDataseturl = new FileWriter("outputUrl.csv");
		//write the output dataset, features, labels, without urls
		FileWriter outDataset = new FileWriter("output.csv");
		//URLs that cause exceptions
		FileWriter exceptions = new FileWriter("exception");

		//write table header in output file
		outDataseturl.write("URL" + "," + "urlLen" + "," + "pathLength" + "," + "Querylength" +
				"," + "URL_Letter_Count" + "," + "URL_DigitCount" + "," +"host_letter_count" + 
				"," + "host_DigitCount" + "," + "symbolCount_URL" + "," + "SpcharUrl" +
				"," + "Query_Digit_Count" + "," + "pathurIRatio" + "," + "label\n");

		outDataset.write("urlLen" + "," + "pathLength" + "," + "Querylength" +
				"," + "URL_Letter_Count" + "," + "URL_DigitCount" + "," +"host_letter_count" + 
				"," + "host_DigitCount" + "," + "symbolCount_URL" + "," + "SpcharUrl" +
				"," + "Query_Digit_Count" + "," + "pathurIRatio" + "," + "label\n");

		//skip dataset table header
		dataset.nextLine();


		URI uri = new URI("");

		//dataset loop
		while(dataset.hasNext()) {
			try{
				String allRow = dataset.nextLine().trim();
				String url = allRow.substring(0,allRow.length()-2);
				String label = allRow.substring(allRow.length()-2);
				uri = new URI(url); 

				//testing
				//System.out.println(++count1); 
				//System.out.println(uri);

				//write URL, features, label in output file
				outDataseturl.write(uri +"," + getUrlLength(uri) + "," + getPathLength(uri) + "," +getQueryLength(uri) +  
						"," + countUrlLetters(uri) + "," + countUrlDigits(uri) + "," + countHostLetters(uri) +
						"," + countHostDigits(uri) + "," + countUrlSymbols(uri) + "," + countSpecialChars(uri) + 
						"," + countQueryDigits(uri) + "," + getPathURLRatio(uri) + label + "\n");

				outDataset.write(getUrlLength(uri) + "," + getPathLength(uri) + "," +getQueryLength(uri) +  
						"," + countUrlLetters(uri) + "," + countUrlDigits(uri) + "," + countHostLetters(uri) +
						"," + countHostDigits(uri) + "," + countUrlSymbols(uri) + "," + countSpecialChars(uri) + 
						"," + countQueryDigits(uri) + "," + getPathURLRatio(uri) + label + "\n");
			}
			//some URLs cause exception, save it in exception file
			catch(Exception e) {
				exceptions.write((++count2)+","+uri+","+e+"\n");
			} 
		}
		outDataseturl.close();
		outDataset.close();
		System.out.println("Features Exraction Is DONE!!");

	}

}

