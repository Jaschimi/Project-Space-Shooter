package game;
import ledControl.BoardController;
import ledControl.LedConfiguration;

public class Misc{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	public static void makeBoom(int[][][] point,int[] color,int wantedPointX, int wantedPointY, int wantedHeight, int difficulty) {
		
		if(difficulty == 1) {
			
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
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)wantedHeight/2) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							for(; z >= 0; z--, j++) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (z + 1),wantedPointY + (z + 1),point,farbe));
							controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (z + 1),wantedPointY + (z + 1),point,farbe));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (z + 1),wantedPointX + (z + 1),point,farbe));
							controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (z + 1),wantedPointX + (z + 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - z,wantedPointY + z,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - z,wantedPointY + z,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - z,wantedPointX + z,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - z,wantedPointX + z,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
							break;
							
						}
					}
					
				}
				
			}
			
		}
		if(difficulty == 3) {
			
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
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							if(j <= (int)wantedHeight/2) {
							
							int[] black = {0,0,0};
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,black));
							controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,black));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,black));
							controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,black));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}else {
							
							int[] black = {0,0,0};
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,black));
							controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,black));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,black));
							controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,black));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - j,wantedPointY + j,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - j,wantedPointY + j,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - j,wantedPointX + j,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - j,wantedPointX + j,point,color));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
						}
					}
					
				}
				
			}
				
		}
        	
        	int[] farbe = {0,0,0};
        
        int j = wantedHeight;
		
		controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
		controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (j - 1),wantedPointY + (j - 1),point,farbe));
		
		controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
		controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (j - 1),wantedPointX + (j - 1),point,farbe));
		
		controller.sleep(50);
		controller.updateLedStripe();
		
	}
	
	private static int[][][] createLineFromToInYDirection(int whereOnXAchsis,int from, int to, int[][][] point, int[] color){
		
		for(int i=0; i<3; i++){
			for(int position=from; position<=to; position++){
				point[whereOnXAchsis][position][i] = color[i];	
			}
		}
		
		return point;
	}
	
    private static int[][][] createLineFromToInXDirection(int whereOnYAchsis,int from, int to, int[][][] point, int[] color){
    	
        for(int i=0; i<3; i++){
			for(int position=from; position<=to; position++){
				point[position][whereOnYAchsis][i] = color[i];
			}
		}
		
		return point;
	}
    
}