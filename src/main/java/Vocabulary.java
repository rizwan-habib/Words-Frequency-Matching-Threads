import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
//import java.util.Vector;

public class Vocabulary extends Thread {

	private String fname;
	BSTree bst=new BSTree();
	public Vocabulary(String name) {
		// TODO Auto-generated constructor stub
		fname=name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
			try {
				File myObj = new File(fname);
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        String[] arrOfStr = data.split(" ");
			        for (int i = 0; i < arrOfStr.length; i++) {
						bst.insert(arrOfStr[i]);
					}
				}
				myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("File Not Found.");
		      e.printStackTrace();
		    }
			this.notify();
		}
		
	}
	
	public BSTree getResult(){
//		bst.inorder();
		return bst;
	}


}
