import ledControl.BoardController;
import ledControl.LedConfiguration;
import objects.EnemyShip;
import objects.Letter;
import objects.Projectile;
import objects.Word;

public class Main {
	
	static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);

	public static void main(String[] args) {

//		DisplayAlphabet();
		DisplayLogo(6);
//		UFO();
		
//		Letter.DrawLetterAt("F", 0, 0, 5, 123, 10);
//		controller.updateLedStripe();
//		controller.sleep(1000);
//		Letter.DrawLetterAt("E", 1, 1, 50, 83, 100);
//		controller.updateLedStripe();

//		controller.updateLedStripe();

	}

	private static void DisplayLogo(int line){
		
		Word logo = new Word("Horizon");
		
		for(int x=20; x>-28 ;x--){
			Word.displayWord(logo, x+1, line, 0, 0, 0);
			
			Word.displayWord(logo, x, line, 5, 123, 10);
			controller.updateLedStripe();
			controller.sleep(250);
		}
	}
	
	private static void DisplayAlphabet(){
		
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0; i<alphabet.length; i++){
			int red = (int) (127*Math.random()+1);
			int green = (int) (127*Math.random()+1);
			int blue = (int) (127*Math.random()+1);
			Letter.DrawLetterAt(alphabet[i], 0, 0, red, green, blue);
			controller.updateLedStripe();
			controller.sleep(1000);
			Letter.DrawLetterAt(alphabet[i], 0, 0, 0, 0, 0);
			controller.updateLedStripe();
		}
	
	}
	
	private static void UFO(){
		
		int[] topLeft = new int[]{0, 0};
		
		EnemyShip ufo = new EnemyShip(topLeft, 3, 3, 1, true);
		
		for(int x = 0; x < ufo.getLength(); x++){
			for(int y = 0; y < ufo.getHeight(); y++){
				if(x== 1 && y == 2)ufo.setColorAt(ufo, x, y, 0, 0, 0);
				else{
					if(y==0 && (x==0 || x==2))ufo.setColorAt(ufo, x, y, 0, 0, 0);
					else{
						ufo.setColorAt(ufo, x, y, 18, 18, 87);
					}
				}
			}
		}
		
		EnemyShip.spawnShip(ufo);
		while(true){
			Projectile projectile = EnemyShip.shoot(ufo);
			projectile.setY(2);
				while(projectile.getY()<20){
					
					controller.sleep(200);
					Projectile.moveProjectile(projectile, "down");
					controller.updateLedStripe();
				}
		}
	}
}
