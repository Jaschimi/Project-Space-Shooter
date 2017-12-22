package objects;

public class Word {

	char[] charArray;
	
	public Word(String word){
		this.charArray = word.toCharArray();
	}
	
	public static void displayWord(Word word, int x, int y, int red, int green, int blue){
		
		for(int i=0; i<word.charArray.length; i++){
			char letter = word.charArray[i];
			Letter.DrawLetterAt(letter, x, y, red, green, blue);
			x+=Letter.convertToLetter(letter).getLength() + 1;
		}
	}
	
	public static void moveWord(int x, int y){
		
	}
}
