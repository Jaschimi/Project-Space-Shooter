package game;
import ledControl.BoardController;
import ledControl.LedConfiguration;

public class VersucheWas {
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	public static void macheBoom(int[][][] point,int[] color,int gewünschterPunktX, int gewünschterPunktY, int gewünschteHöhe, int zufallszahl) {
		
		if(zufallszahl == 1) {
			
			for(int j = 0, z = gewünschteHöhe; j < gewünschteHöhe;z--, j++) {
				
				if(j == 0) {
					
					for(int i = 0; i < color.length; i++) {
					
					point[gewünschterPunktX][gewünschterPunktY][i] = color[i];
					
					controller.setColors(point);
					controller.sleep(70);
					controller.updateLedStripe();
							
					}
						
					}else {
						
						if(j == 1) {
							
							for(int i = 0; i < color.length; i++) {
								
								point[gewünschterPunktX][gewünschterPunktY][i] = 0;
								
								controller.setColors(point);
								controller.sleep(70);
								controller.updateLedStripe();
										
								}
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)gewünschteHöhe/2) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							for(; z >= 0; z--, j++) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - (j - 1),gewünschterPunktY - (z + 1),gewünschterPunktY + (z + 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + (j - 1),gewünschterPunktY - (z + 1),gewünschterPunktY + (z + 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - (j - 1),gewünschterPunktX - (z + 1),gewünschterPunktX + (z + 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + (j - 1),gewünschterPunktX - (z + 1),gewünschterPunktX + (z + 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - z,gewünschterPunktY + z,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - z,gewünschterPunktY + z,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - z,gewünschterPunktX + z,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - z,gewünschterPunktX + z,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
							break;
							
						}
					}
					
				}
				
			}
			
		}else if(zufallszahl == 0) {
			
            for(int j = 0; j < gewünschteHöhe; j++) {
				
				if(j == 0) {
					
					for(int i = 0; i < color.length; i++) {
					
					point[gewünschterPunktX][gewünschterPunktY][i] = color[i];
					
					controller.setColors(point);
					controller.sleep(70);
					controller.updateLedStripe();
							
					}
						
					}else {
						
						if(j == 1) {
							
							for(int i = 0; i < color.length; i++) {
								
								point[gewünschterPunktX][gewünschterPunktY][i] = 0;
								
								controller.setColors(point);
								controller.sleep(70);
								controller.updateLedStripe();
										
								}
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)gewünschteHöhe/2) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + j,gewünschterPunktY - j,gewünschterPunktY + j,point,color));
							
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + j,gewünschterPunktX - j,gewünschterPunktX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
					}
					
				}
				
			}
				
		}
        	
        	int[] farbe = {0,0,0};
        
        int j = gewünschteHöhe;
		
		controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX - (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
		controller.setColors(erzeugeStrichVonBisInYRichtung(gewünschterPunktX + (j - 1),gewünschterPunktY - (j - 1),gewünschterPunktY + (j - 1),point,farbe));
		
		controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY - (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
		controller.setColors(erzeugeStrichVonBisInXRichtung(gewünschterPunktY + (j - 1),gewünschterPunktX - (j - 1),gewünschterPunktX + (j - 1),point,farbe));
		
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
