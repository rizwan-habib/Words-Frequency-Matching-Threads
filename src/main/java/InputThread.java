import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class InputThread extends Thread {

	private String fname;
	private Vector<String> input=new Vector<String>();
	private BSTree bst;
	private ArrayList<Word> matchedwords=new ArrayList<Word>();
	public InputThread(String name,BSTree bs) {
		// TODO Auto-generated constructor stub
		fname=name;
		bst=bs;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			File myObj = new File(fname);
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
		for (int i = 0; i < input.size(); i++) {
			boolean flag=true;
			if (bst.search(input.get(i))) {
				for (int j = 0; j < matchedwords.size(); j++) {
					if (matchedwords.get(j).getWord().equals( input.get(i) ) ) {
						matchedwords.get(j).incFrequency();
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
	public ArrayList<Word> getResult(){
		return matchedwords;
	}
	public void threadCheck() throws ThreadNotComplete {
		if (input.size()<0) {
			throw new ThreadNotComplete("Thread not complete");
		}
	}
	public Vector<String> getVectorResult(){

		return input;
	}


}
