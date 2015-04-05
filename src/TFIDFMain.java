import java.util.ArrayList;


public class TFIDFMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> list = new ArrayList<String>();
		list.add("string.txt");
		list.add("string2.txt");
		TFIDF tfidf = new TFIDF(list);
		//System.out.println(tfidf.genDocTermFreq("string.txt"));
		//System.out.println(tfidf.getUniqueTerms());
		
		
		System.out.println(tfidf.getTFIDF("string.txt"));
		System.out.println(tfidf.getTFIDF("string2.txt"));
	}

}
