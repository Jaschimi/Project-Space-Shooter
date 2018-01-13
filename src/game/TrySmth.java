package game;
import ledControl.BoardController;
import ledControl.LedConfiguration;

public class TrySmth {
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	public static void makeBoom(int[][][] point,int[] color,int wantedPointX, int wantedPointY, int wantedHeight, int random) {
		
		if(random == 1) {
			
			for(int j = 0, z = wantedHeight; j < wantedHeight;z--, j++) {
				
				if(j == 0) {
					
					for(int i = 0; i < color.length; i++) {
					
					point[wantedPointX][wantedPointY][i] = color[i];
					
					controller.setColors(point);
					controller.sleep(70);
					controller.updateLedStripe();
							
					}
						
					}else {
						
						if(j == 1) {
							
							for(int i = 0; i < color.length; i++) {
								
								point[wantedPointX][wantedPointY][i] = 0;
								
								controller.setColors(point);
								controller.sleep(70);
								controller.updateLedStripe();
										
								}
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)wantedHeight/2) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							for(; z >= 0; z--, j++) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - (j - 1),wantedPointY - (z + 1),wantedPointY + (z + 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + (j - 1),wantedPointY - (z + 1),wantedPointY + (z + 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - (j - 1),wantedPointX - (z + 1),wantedPointX + (z + 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + (j - 1),wantedPointX - (z + 1),wantedPointX + (z + 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - z,wantedPointY + z,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - z,wantedPointY + z,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - z,wantedPointX + z,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - z,wantedPointX + z,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
							break;
							
						}
					}
					
				}
				
			}
			
		}else if(random == 0) {
			
            for(int j = 0; j < wantedHeight; j++) {
				
				if(j == 0) {
					
					for(int i = 0; i < color.length; i++) {
					
					point[wantedPointX][wantedPointY][i] = color[i];
					
					controller.setColors(point);
					controller.sleep(70);
					controller.updateLedStripe();
							
					}
						
					}else {
						
						if(j == 1) {
							
							for(int i = 0; i < color.length; i++) {
								
								point[wantedPointX][wantedPointY][i] = 0;
								
								controller.setColors(point);
								controller.sleep(70);
								controller.updateLedStripe();
										
								}
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)wantedHeight/2) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
					}
					
				}
				
			}
				
		}
        	
        	int[] farbe = {0,0,0};
        
        int j = wantedHeight;
		
		controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
		controller.setColors(erzeugeStrichVonBisInYRichtung(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
		
		controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
		controller.setColors(erzeugeStrichVonBisInXRichtung(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
		
		controller.sleep(50);
		controller.updateLedStripe();
		
	}
	
	private static int[][][] erzeugeStrichVonBisInYRichtung(int woAufXAchse,int von, int bis, int[][][] point, int[] color){
		
		int anfang  = von;
		
		for(int i = 0; i < color.length; i++) {
		
		for(von = anfang; von <= bis; von++) {
				
				point[woAufXAchse][von][i] = color[i];
				
		}
		
		}
		
		return point;
		
	}
	
    private static int[][][] erzeugeStrichVonBisInXRichtung(int woAufYAchse,int von, int bis, int[][][] point, int[] color){
    	
    	    int anfang  = von;
    	
        for(von = anfang; von <= bis; von++) {
			
			for(int i = 0; i < color.length; i++) {
				
				point[von][woAufYAchse][i] = color[i];
				
			}
			
		}
		
		return point;
		
	}

}
