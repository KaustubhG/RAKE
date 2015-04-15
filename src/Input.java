
public class Input {
	
	String filename ; 
	
	public Input(String filename) {
		// TODO Auto-generated constructor stub
		this.filename = filename ; 
	}
	
	public String getAbstract()
	{
		
		String plain_text = ReadDataset.pdftoText(filename).toLowerCase();
		
		
		
		
		return null ; 
	}
}
