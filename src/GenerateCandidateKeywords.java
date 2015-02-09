import java.util.ArrayList;
import java.util.HashSet;


public class GenerateCandidateKeywords {
	
	String text ;
	private HashSet<String> disWords = new HashSet<String>(); 
	
	public HashSet<String> getdistinctWords()
	{
		if(disWords.isEmpty())
		{
			throw new IllegalStateException("Call generate before calling this method");
		}
		else
			return disWords; 
	}
	
	public GenerateCandidateKeywords(String text) {
		// TODO Auto-generated constructor stub
		this.text = text ; 
	}
	
	public ArrayList<ArrayList<String>> generate()
	{
		
		ArrayList<ArrayList<String>> candidate = new ArrayList<ArrayList<String>>();
		HashSet<String> stopWords = getStopWordsfromFile("");
		
		ArrayList<String> temp = new ArrayList<String>(); 
		for(String a: this.text.split(" "))
		{
			
			if(stopWords.contains(a.toLowerCase()))
			{
				if(temp.isEmpty())
				{
					continue; 
				}
				
				else
				{
					candidate.add(new ArrayList<String>(temp));
					//System.out.println(temp);
					temp.clear();
				}
			}
			
			else
			{
				if(temp.isEmpty())
				{
					temp.clear();;
				}
				//System.out.println(checkLastChar(a));
				temp.add(checkLastChar(a));
				disWords.add(checkLastChar(a));
			}
		}

		if(!temp.isEmpty())
		{
			candidate.add(temp);
		}
		return candidate ; 
	}
	
	public HashSet<String> getStopWordsfromFile(String filename)
	{
		
		HashSet<String> stopWords = new HashSet<>();
		stopWords.add(".");stopWords.add(",");
		stopWords.add("the");stopWords.add("or");stopWords.add("a");
		stopWords.add("over");stopWords.add("of");
		return stopWords; 
	}
	
	public static String checkLastChar(String word)
	{
		HashSet<String> trimmer = new HashSet<String>();
		trimmer.add("s");trimmer.add("?");trimmer.add(".");
		//add while loop for multiple last char checking
		//see how substirng ke index works
		if(trimmer.contains(word.substring(word.length()-1, word.length())))
		{
			return word.substring(0, word.length() - 1);
		}
		else
			return word ; 
		
	}
}
