import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;


public class TFIDFMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> list = new ArrayList<String>();
		list.add("test1.txt");
		list.add("test2.txt");
		list.add("test3.txt");
		list.add("test4.txt");
		list.add("test5.txt");
		list.add("test6.txt");
		TFIDF tfidf = new TFIDF(list);
		//System.out.println(tfidf.genDocTermFreq("string.txt"));
		//System.out.println(tfidf.getUniqueTerms());
		
		
		/*System.out.println(tfidf.getTFIDF("test2.txt"));
		System.out.println(tfidf.getTFIDF("test3.txt"));
		System.out.println(tfidf.getTFIDF("test4.txt"));*/
		
		HashSet<String> unique = tfidf.getUniqueTerms();
		HashMap<String, Double> unique_idfs = new HashMap<>();
		
		for(String term: unique)
		{
			//System.out.println("Term: " + term + "  IDF:  " + tfidf.getIDF(term));
			unique_idfs.put(term, tfidf.getIDF(term));
		}
		
		//get first n terms from each doc
		int n = 20;
		List<Entry<String, Double>> greatest = Main.findGreatest(tfidf.getTFIDF("test6.txt"), n);
		//System.out.println("Top "+n+" entries:");
		for (Entry<String, Double> entry : greatest){
			System.out.println(entry);
		}

		/*
		System.out.println("---------------------------------------------------------------");
		List<Entry<String, Double>> greatest1 = Main.findLowest(unique_idfs, unique.size());
		//System.out.println("Top "+n+" entries:");
		for (Entry<String, Double> entry : greatest1){
			System.out.println(entry);
		}
		*/
		
	}

}
