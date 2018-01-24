package game;
import displayObjects.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;

public abstract class Misc{
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
	
	public static void makeBoom(int[][][] point,int[] color,int wantedPointX, int wantedPointY, int wantedHeight, int difficulty) {
		
		if(difficulty == 1) {
			
			for(int j = 0, y = wantedHeight; j < wantedHeight;y--, j++) {
				
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
							
							for(; y >= 0; y--, j++) {
							
							int[] farbe = {0,0,0};
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - (j - 1),wantedPointY - (y + 1),wantedPointY + (y + 1),point,farbe));
							controller.setColors(createLineFromToInYDirection(wantedPointX + (j - 1),wantedPointY - (y + 1),wantedPointY + (y + 1),point,farbe));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - (j - 1),wantedPointX - (y + 1),wantedPointX + (y + 1),point,farbe));
							controller.setColors(createLineFromToInXDirection(wantedPointY + (j - 1),wantedPointX - (y + 1),wantedPointX + (y + 1),point,farbe));
							
							controller.sleep(70);
							controller.updateLedStripe();
							
							
							
							controller.setColors(createLineFromToInYDirection(wantedPointX - j,wantedPointY - y,wantedPointY + y,point,color));
							controller.setColors(createLineFromToInYDirection(wantedPointX + j,wantedPointY - y,wantedPointY + y,point,color));
							
							controller.setColors(createLineFromToInXDirection(wantedPointY - j,wantedPointX - y,wantedPointX + y,point,color));
							controller.setColors(createLineFromToInXDirection(wantedPointY + j,wantedPointX - y,wantedPointX + y,point,color));
							
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
//    	starts from the point "from" on x-axis and makes a colored line until the point "to".
//    	You can choose the point on the y-axis where to generate a horizontal line of the chosen color. 
		for(int i=0; i<3; i++){
			for(int position=from; position<=to; position++){
				point[whereOnXAchsis][position][i] = color[i];	
			}
		}
		
		return point;
	}
	
    private static int[][][] createLineFromToInXDirection(int whereOnYAchsis,int from, int to, int[][][] point, int[] color){
//    	starts from the point "from" on y-axis and makes a colored line until the point "to".
//    	You can choose the point on the x-axis where to generate a vertical line of the chosen color. 
        for(int i=0; i<3; i++){
			for(int position=from; position<=to; position++){
				point[position][whereOnYAchsis][i] = color[i];
			}
		}
		
		return point;
	}
	
	private static int whereOnX = 9;
	private static int ground = 19;

	private static void goUp(int[][][] point, int[] color) {

		//Special case. Only one colored point has to be generated on the ground, because there are no 
		//colored points on the LED-Board.
		for(int i = 0; i < color.length; i++) {
			
		    point[whereOnX][ground][i] = color[i];
		
		    controller.setColors(point);
		    controller.updateLedStripe();
		
	    }
		for(ground = 17; ground > 4; ground-=2) {
		    //I go up!
    	    for(int i = 0; i < color.length; i++) {
			
			point[whereOnX][ground][i] = color[i];
			point[whereOnX][ground + 2][i] = 0;
			
			controller.setColors(point);
			controller.updateLedStripe();

    	    }
		}
		
		ground = 3;
    	//Here the last point that was generated in the loop is set to black
        for(int i = 0; i < color.length; i++) {
			
			point[whereOnX][ground + 2][i] = 0;
			
			controller.setColors(point);
			controller.updateLedStripe();
			
		}
        
	}
	
	static void makeMegaBoom(int[][][] point, int[] color) {
		
		ground = ground + 2;
		
		for(int counter = 0; counter < 8; counter++) {
			
				final Word bDay = new Word("1598");
	    		bDay.displayWordAt(2, 15, 9, 9, 0);
	    		point = controller.getColors();
	    		
			if(counter < 6) {

				//If counter%2 == 0, this method generates colored points in diagonal, vertical and horizontal direction.
				//But before this method creates new points, the old ones have to be colored black.
				if(counter%2 == 0) {
		            for(int i = 0; i < color.length; i++) {
		            	
		              	point[whereOnX + (counter - 1)][ground][i] = 0;
			            point[whereOnX - (counter - 1)][ground][i] = 0;
			            point[whereOnX][ground + (counter - 1)][i] = 0;
			            point[whereOnX][ground - (counter - 1)][i] = 0;
			            
			            point[whereOnX + ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = 0;
			            point[whereOnX - ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = 0;
			            point[whereOnX + ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = 0;
			            point[whereOnX - ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = 0;
		            	
//----------------------------------------------------------------------------------------------------
			
			            point[whereOnX + counter][ground][i] = color[i];
			            point[whereOnX - counter][ground][i] = color[i];
			            point[whereOnX][ground + counter][i] = color[i];
			            point[whereOnX][ground - counter][i] = color[i];
			            
			            point[whereOnX + (counter)/2][ground - (counter)/2][i] = color[i];
			            point[whereOnX - (counter)/2][ground - (counter)/2][i] = color[i];
			            point[whereOnX + (counter)/2][ground + (counter)/2][i] = color[i];
			            point[whereOnX - (counter)/2][ground + (counter)/2][i] = color[i];
			            
			            controller.setColors(point);
			            controller.updateLedStripe();
			
		            }
		        
				}
				else {
					//If counter%2 == 1, this method generates colored points in vertical and horizontal direction.
					//But before this method creates new points the old ones have to be deleted.
		            for(int i = 0; i < color.length; i++) {
		            	
		              	point[whereOnX + (counter - 1)][ground][i] = 0;
			            point[whereOnX - (counter - 1)][ground][i] = 0;
			            point[whereOnX][ground + (counter - 1)][i] = 0;
			            point[whereOnX][ground - (counter - 1)][i] = 0;
			            
			            point[whereOnX + ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = 0;
			            point[whereOnX - ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = 0;
			            point[whereOnX + ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = 0;
			            point[whereOnX - ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = 0;
		            	
//------------------------------------------------------------------------------------------------		            	
		    			
			            point[whereOnX + counter][ground][i] = color[i];
			            point[whereOnX - counter][ground][i] = color[i];
			            point[whereOnX][ground + counter][i] = color[i];
			            point[whereOnX][ground - counter][i] = color[i];
			            
			            controller.setColors(point);
			            controller.updateLedStripe();
		    			
		            }
					
				}
		
		    }
		}
		controller.sleep(275);
	}
	
	public static void makeAll(int[][][] point, int[] color) {
		
		goUp(point, color);
		makeMegaBoom(point, color);
		
	}
	
}