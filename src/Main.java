

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "The compatibility of linear constants over a set of natural numbers linear constants";
		GenerateCandidateKeywords gen = new GenerateCandidateKeywords(input);
		
		GenerateWordScores gen_word_score = new GenerateWordScores(gen.generate());
		System.out.println(gen_word_score.getWordScore());

		
	}

}
