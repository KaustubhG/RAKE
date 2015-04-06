import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;



//to generate tf-idf values for stop-words and 2nd keyword generating algorithm
public class TFIDF {

	ArrayList<String> filenames;
	ArrayList<HashMap<String, Double>> documentTerms_mapList;

	public TFIDF(ArrayList<String> filenames){
		this.filenames = filenames;
		
		documentTerms_mapList = new ArrayList<>();
		for(String filename : filenames){
			documentTerms_mapList.add(genDocTermFreq(filename));
		}
	}

	//this is for a particular document
	public HashMap<String, Double> getTFIDF(String filename){
		
		HashMap<String, Double> tfidf_map = new HashMap<>();
		
		//get tfidf vals for each term from document
		HashMap<String, Double> tf_map = new HashMap<>();
		tf_map = genDocTermFreq(filename);
		for(String term : tf_map.keySet()){
			
			double tfidf_val = getIDF(term)* tf_map.get(term);
			//double tfidf_val = getIDF(term);
			tfidf_map.put(term, tfidf_val);
		}
		
		return tfidf_map;
	} 

	//get unique terms from all documents
	public HashSet<String> getUniqueTerms(){
		
		HashSet<String> uniqueTerm_set = new HashSet<>();
		for(HashMap<String, Double> map : documentTerms_mapList){
			uniqueTerm_set.addAll(map.keySet());
		}
		return uniqueTerm_set;
	}
	
	//calculate term freqs for a document
	public HashMap<String, Double> genDocTermFreq(String filename){

		HashMap<String, Double> term_freq_map = new HashMap<>();

		//parse file to String
		StringBuilder sb = new StringBuilder();
		String sCurrentLine;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))){
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine).append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//sb now has the parsed contents of filename

		//remove punctuations
		ArrayList<String> sent_list = new ArrayList<String>();
		String delimiters = "\\.\\s*|\\?\\s*|\\s*,\\s*|\\s*!\\s*";
		for(String a : sb.toString().split(delimiters)){
			sent_list.add(a);
		}

		//remove spaces to generate words
		HashSet<String> word_set = new HashSet<>();
		for(String sent : sent_list){

			for(String single_word: sent.split("\\s+|\\s*,\\s*")){	

				single_word = single_word.toLowerCase();
				//Removing numbers from generated keyword list
				if(single_word.matches("[0-9]+") || single_word.matches("\\(.*\\)")){
					continue ; 
				}
				
				//calc term-freq
				if(word_set.contains(single_word)){
					term_freq_map.put(single_word, term_freq_map.get(single_word) + 1);
				}
				else{
					term_freq_map.put(single_word, 1.0);
					word_set.add(single_word);
				}
			}
		}
		
		//normalize
		double total_val = 0;
		for(String term : term_freq_map.keySet()){
			total_val+=Math.pow(term_freq_map.get(term), 2);
		}
		total_val = Math.sqrt(total_val);
		
		for(String term : term_freq_map.keySet()){
			term_freq_map.put(term, term_freq_map.get(term)/total_val);
		}
		
		
		return term_freq_map;
	}

	//calculate idf value for each word
	public double getIDF(String word){
		
		int total_docs = filenames.size();
		int docs_containing_word = 0;
		
		for(HashMap<String, Double> map: documentTerms_mapList){
			
			if(map.containsKey(word)){
				docs_containing_word+=1;
			}
		}
		
		//calculate idf for word
		double idf_value = Math.log((double)total_docs/(1+docs_containing_word));
		return idf_value;
	}
}
