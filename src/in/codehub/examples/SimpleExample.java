package in.codehub.examples;

import in.codehub.document.Document;
import in.codehub.paperparser.Paper;
import in.codehub.paperparser.PaperParser;
import in.codehub.pdfreader.PdfReader;

import java.io.IOException;

public class SimpleExample
{
    public static Paper run(String filePath) throws IOException
    {
        Document document = PdfReader.getInstance().read(filePath);
        return PaperParser.getInstance().parse(document);
    }
    
    public static void main(String[] args) {
		try {
			Paper paper = run("Test2.pdf");
			System.out.println(paper.getAbstract());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
