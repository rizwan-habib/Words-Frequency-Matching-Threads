import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class DriverClass {

	public DriverClass() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Number of Files are: "+ args.length);
		for (int i = 0; i < args.length; i++) {
			System.out.println("File Name: "+args[i]);
			
		}
		Vocabulary v1=new Vocabulary(args[0]);
		v1.setName(args[0]);
		v1.start();
		synchronized (v1) {
			try {
				v1.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		InputThread inp[]=new InputThread[args.length-1];
		for (int i = 0; i < args.length-1; i++) {
			inp[i]=new InputThread(args[i+1],v1.getResult());
			inp[i].start();
		}
		for (int i = 0; i < args.length-1; i++) {
			try {
				inp[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < inp.length; i++) {
			try {
				inp[i].threadCheck();
			} catch (ThreadNotComplete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int opt=0;
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		
		do {
			System.out.println("1. Display BST build from Vocabulary File.");
			System.out.println("2. Display Vectors build from Input files.");
			System.out.println("3. Display Match words and its frequency");
			System.out.println("4) Searching a query.");
			System.out.println("5. Exit.");
			opt=input.nextInt();
			input.nextLine();
			
			
			if (opt==1) {
				System.out.println("\n\nBST: \n\n");
				v1.getResult().inorder();
			}
			else if (opt==2) {
				for (int i = 0; i < inp.length; i++) {
					System.out.println("\n\nVector of File: "+args[i+1]);
					Vector<String> res=inp[i].getVectorResult();
					for (int j = 0; j < res.size(); j++) {
						System.out.println(res.get(j));
						
					}
				}
			}
			else if (opt==3) {
				for (int i = 0; i < inp.length; i++) {
					System.out.println("Results of File: "+args[i+1]);
					ArrayList<Word> res=inp[i].getResult();
					for (int j = 0; j < res.size(); j++) {
						System.out.print("Word: "+res.get(j).getWord());
						System.out.print("    Freq: "+res.get(j).getFrequency());
						System.out.println();
					}
				}
			}
			else if (opt==4) {
				System.out.print("Enter Word: ");
				String word=input.nextLine();
				QueryThread[] query=new QueryThread[args.length-1];
				for (int i = 0; i < query.length; i++) {
					 query[i]=new QueryThread(args[i+1], word);
					 query[i].start();
				}
				for (int i = 0; i < query.length; i++) {
					try {
						query[i].join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < query.length; i++) {
					System.out.println("Results of File: "+args[i+1]);
					ArrayList<Word> res=query[i].getResult();
					for (int j = 0; j < res.size(); j++) {
						System.out.print("Word: "+res.get(j).getWord());
						System.out.print("    Freq: "+res.get(j).getFrequency());
						System.out.println();
					}
				}
				
			}
			else if (opt==5) {
				return;
			}
		} while (true);
	}

}
