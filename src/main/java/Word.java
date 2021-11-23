
public class Word {
	private String word;
	private int frequency;
	public Word() {
		// TODO Auto-generated constructor stub
		word="";
		frequency=0;
	}
	
	public Word(String word, int frequency) {
		super();
		this.word = word;
		this.frequency = frequency;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public void incFrequency() {
		this.frequency++;
	}
	
}
