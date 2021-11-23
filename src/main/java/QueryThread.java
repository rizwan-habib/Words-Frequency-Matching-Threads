import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class QueryThread extends Thread {
	private String fName,inputWord;
	private Vector<String> input=new Vector<String>();
	private ArrayList<Word> matchedwords=new ArrayList<Word>();

	public QueryThread(String name,String inp) {
		// TODO Auto-generated constructor stub
		fName=name;
		inputWord=inp;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			File myObj = new File(fName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String[] arrOfStr = data.split(" ");
		        for (int i = 0; i < arrOfStr.length; i++) {
		        	input.add(arrOfStr[i]);
				}
			}
			myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("File Not Found.");
	      e.printStackTrace();
	    }
		String[] arrOfStr = inputWord.split(" ");
        
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < arrOfStr.length; j++) {
				boolean flag=true;
				if (arrOfStr[j].equals(input.get(i))) {
					for (int k = 0; k < matchedwords.size(); k++) {
						if (matchedwords.get(k).getWord().equals( input.get(i) ) ) {
							matchedwords.get(k).incFrequency();
							flag=false;
						}	
					}	
					if (flag) {
						Word w1=new Word(input.get(i), 1);
						matchedwords.add(w1);
					}
				}
				
			}
		}
		
	}
	public ArrayList<Word> getResult(){
		return matchedwords;
	}
	public Vector<String> getVectorResult(){

		return input;
	}

}
