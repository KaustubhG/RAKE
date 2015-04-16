import in.codehub.document.Document;
import in.codehub.paperparser.Paper;
import in.codehub.paperparser.PaperParser;
import in.codehub.pdfreader.PdfReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

		long start = System.currentTimeMillis();
		
		final File folder = new File("/home/kaustubhg/NLPProject/temp");
		
		StringBuilder stringBuilder = new StringBuilder();

		for(String abs_filepath :listFilesForFolder(folder) ){

			if(!isPDF(abs_filepath)){
				continue;
			}

			String paper_abstract = ""; //this will contain the abstract
			try {
				Paper paper = run(abs_filepath);
				paper_abstract = paper.getAbstract();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			//keyword extraction algo
			GenerateCandidateKeywords genKey = new GenerateCandidateKeywords(paper_abstract);
			ArrayList<ArrayList<String>> candKeyWords = genKey.generate();

			GenerateWordScores wordScores = new GenerateWordScores(candKeyWords);

			//print top n keyWords
			int n = 10;
			stringBuilder.append("-----------------" + "file : " + abs_filepath + "-------------------").append("\n");
			
			List<Entry<ArrayList<String>, Double>> greatest = findGreatest(wordScores.getWordScore(), n);
			for (Entry<ArrayList<String>, Double> entry : greatest){
				stringBuilder.append(entry).append("\n");
				//System.out.println(entry);
			}
			
			stringBuilder.append("---------------------------------------").append("\n");
			//System.out.println("---------------------------------------");
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);

		System.out.println(stringBuilder.toString());
		//Write results file with built string
		/*
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("result.txt")));
			bufferedWriter.write(stringBuilder.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
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

	public static Paper run(String filePath) throws IOException{
		Document document = PdfReader.getInstance().read(filePath);
		return PaperParser.getInstance().parse(document);
	}

	public static ArrayList<String> listFilesForFolder(final File folder) {

		ArrayList<String> list = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {

			if (fileEntry.isDirectory()) {
				list.addAll(listFilesForFolder(fileEntry));
			} else {
				String abs_filepath = fileEntry.getAbsolutePath();
				list.add(abs_filepath);
			}
		}
		return list;
	}

	public static boolean isPDF(String path){

		if(!path.substring(path.length()-1-2, path.length()).equals("pdf")){
			return false;
		}
		else return true;
	}
}
