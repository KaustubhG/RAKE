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

		String test = "12 vandan 14 kg 21 hello (abcd) (efgh)";
		
		GenerateCandidateKeywords genKey = new GenerateCandidateKeywords(test.toString());
		ArrayList<ArrayList<String>> candKeyWords = genKey.generate();


		GenerateWordScores wordScores = new GenerateWordScores(candKeyWords);
		System.out.println(wordScores.getWordScore());

/*		//print top n keyWords
		int n = 7;
		List<Entry<ArrayList<String>, Double>> greatest = Main.findGreatest(wordScores.getWordScore(), n);
		//System.out.println("Top "+n+" entries:");
		for (Entry<ArrayList<String>, Double> entry : greatest){
			System.out.println(entry);
		}*/
	}
}
