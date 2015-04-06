import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		/*String build = ReadDataset.pdftoText("test2.pdf") ; 
		System.out.println(build);*/

		StringBuilder build = new StringBuilder();
		String sCurrentLine;
		try (BufferedReader br = new BufferedReader(new FileReader("test6.txt"))){

			while ((sCurrentLine = br.readLine()) != null) {
				build.append(sCurrentLine).append(" ");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

		GenerateCandidateKeywords genKey = new GenerateCandidateKeywords(build.toString());
		ArrayList<ArrayList<String>> candKeyWords = genKey.generate();

		GenerateWordScores wordScores = new GenerateWordScores(candKeyWords);
		
		//print top n keyWords
		int n = 20;
		List<Entry<ArrayList<String>, Double>> greatest = findGreatest(wordScores.getWordScore(), n);
		for (Entry<ArrayList<String>, Double> entry : greatest){
			System.out.println(entry);
		}
		
	}

	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
	findGreatest(Map<K, V> map, int n){

		Comparator<? super Entry<K, V>> comparator = 
				new Comparator<Entry<K, V>>(){
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1){
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v0.compareTo(v1);
			}
		};
		PriorityQueue<Entry<K, V>> highest = 
				new PriorityQueue<Entry<K,V>>(n, comparator);
		for (Entry<K, V> entry : map.entrySet()){
			highest.offer(entry);
			while (highest.size() > n){
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
		while (highest.size() > 0){
			result.add(highest.poll());
		}
		return result;
	}
	
	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
	findLowest(Map<K, V> map, int n){

		Comparator<? super Entry<K, V>> comparator = 
				new Comparator<Entry<K, V>>(){
			@Override
			public int compare(Entry<K, V> e0, Entry<K, V> e1){
				V v0 = e0.getValue();
				V v1 = e1.getValue();
				return v1.compareTo(v0);
			}
		};
		
		PriorityQueue<Entry<K, V>> highest = 
				new PriorityQueue<Entry<K,V>>(n, comparator);
		for (Entry<K, V> entry : map.entrySet()){
			highest.offer(entry);
			while (highest.size() > n){
				highest.poll();
			}
		}

		List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
		while (highest.size() > 0){
			result.add(highest.poll());
		}
		return result;
	}
}
