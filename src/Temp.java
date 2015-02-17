import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;


public class Temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = " , hey, this is, kg";
		String delimiter = "\\s*,\\s*|\\s+";
		for(String a : test.split(delimiter)){
			System.out.println(a);
		}
		
		/*
		StringBuilder build = new StringBuilder();
		String sCurrentLine;
		try (BufferedReader br = new BufferedReader(new FileReader("string.txt")))
		{		
			while ((sCurrentLine = br.readLine()) != null) {
				build.append(sCurrentLine).append(" ");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

		GenerateCandidateKeywords genKey = new GenerateCandidateKeywords(build.toString());
		ArrayList<ArrayList<String>> candKeyWords = genKey.generate();
		for(ArrayList<String> list : candKeyWords){
			System.out.println(list);
		}

		GenerateWordScores wordScores = new GenerateWordScores(candKeyWords);
		System.out.println(wordScores.getWordScore());
		*/


	}
}
