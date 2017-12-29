package displayObjects;

//This class helps in displaying Strings onto the LED Board
public class Word {

	private char[] charArray;
	private int length;
	
	//A getter for a Word's length
	public int getLength() {
		return length;
	}

	//A Word is essentially just a String that gets converted to an array of chars
	public Word(String word){
		this.charArray = word.toCharArray();
		//The length is determined by the length of every single char's corresponding Letter plus 1 (for the space between them)
		for(int i=0; i<charArray.length; i++){
			length += Letter.convertToLetter(charArray[i]).getLength()+1;
		}
	}
	
	//This method displays a Word at a any position on (and off) the board in n RGB color
	public void displayWord(int x, int y, int red, int green, int blue){
		
		for(int i=0; i<this.charArray.length; i++){
			char letter = this.charArray[i];
			Letter.DrawLetterAt(letter, x, y, red, green, blue);
			x+=Letter.convertToLetter(letter).getLength() + 1;
		}
	}
	
	public void moveWord(int x){
		//The following two lines make the Word move one spot to the left
		this.displayWord(x+1, 0, 0, 0, 0);
		this.displayWord(x, 0, 97, 17, 2);
	}
}
