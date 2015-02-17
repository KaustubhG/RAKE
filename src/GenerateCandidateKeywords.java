import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;


public class GenerateCandidateKeywords {

	String text ;

	public GenerateCandidateKeywords(String text) {
		// TODO Auto-generated constructor stub
		this.text = text ; 
	}

	//Gives a list of list of candidate key-words
	public ArrayList<ArrayList<String>> generate()
	{

		ArrayList<ArrayList<String>> candidate = new ArrayList<ArrayList<String>>();
		HashSet<String> stopWords = getStopWordsfromFile("SmartStoplist.txt");

		ArrayList<String> sentences = genSent();
		ArrayList<String> temp = new ArrayList<String>(); //temp list for storing candkey words
		for(String sent : sentences){


			for(String single_word: sent.split("\\s+|\\s*,\\s*"))
			{
				//System.out.println(single_word);
				single_word = single_word.toLowerCase();
				if(stopWords.contains(single_word)){

					if(temp.isEmpty()) continue;
					else{
						candidate.add(new ArrayList<String>(temp));
						temp.clear();
					}
				}
				else{
					//temp.add(checkLastChar(single_word));
					temp.add(single_word);
				}
			}
			//for the last words in temp
			if(!temp.isEmpty()){
				candidate.add(new ArrayList<String>(temp));
			}
			temp.clear();

		}
		return candidate ;
	}

	//generates sentences from given text
	public ArrayList<String> genSent(){

		ArrayList<String> sent_list = new ArrayList<String>();
		String delimiters = "\\.\\s*|\\?\\s*|\\s*,\\s*";

		for(String a : this.text.split(delimiters)){

			sent_list.add(a);
		}

		return sent_list;


	}

	public HashSet<String> getStopWordsfromFile(String filename)
	{

		return StopWords.getStopWordsFromFile(filename); 
	}

	public static String checkLastChar(String word)
	{
		HashSet<String> trimmer = new HashSet<String>();
		trimmer.add("?");
		trimmer.add(".");
		trimmer.add(",");
		//add while loop for multiple last char checking
		//see how substring index works
		if(trimmer.contains(word.substring(word.length()-1, word.length())))
		{
			return word.substring(0, word.length() - 1);
		}
		else
			return word ; 



	}
}
