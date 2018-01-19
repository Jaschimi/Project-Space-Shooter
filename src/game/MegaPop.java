package game;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class MegaPop {
	
	private static int whereOnX = 9;
	private static int ground = 19;
	
	private static BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);

	public static void goUp(int[][][] point, int[] color) {
		
		for(ground = 19; ground > 3; ground--) {
			
			if(ground == 19) {
			
			    for(int i = 0; i < color.length; i++) {
				
				    point[whereOnX][ground][i] = color[i];
				
				    controller.setColors(point);
				    controller.updateLedStripe();
				
			    }
			
		    }else if(ground == 4){
		    	
		        for(int i = 0; i < color.length; i++) {
					
					point[whereOnX][ground + 1][i] = 0;
					
					controller.setColors(point);
					controller.sleep(150);
					controller.updateLedStripe();
					
				}
		    	
		    }else {
		    	 
		    	    for(int i = 0; i < color.length; i++) {
					
					point[whereOnX][ground][i] = color[i];
                     
					point[whereOnX][ground + 1][i] = 0;
					
					controller.setColors(point);
					controller.updateLedStripe();

				}
		    	
		    }
			
		}
		
	}
	
	public static void makeMegaBoom(int[][][] point, int[] color) {
		
		ground = ground + 2;
		
		for(int counter = 0; counter < 8; counter++) {
			
			if(counter < 6) {
				
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
		        
				}else {
					
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
		
		    }else if(counter == 0) {
		    	
		      	for(int i = 0; i < color.length; i++) {
					
		            point[whereOnX][ground][i] = color[i];
		            
		            controller.setColors(point);
		            controller.updateLedStripe();
		
	            }
		      	
		    }else if(counter == 6) {
		    	
		    	    for(int i = 0; i < color.length; i++) {
		    	
		      	    point[whereOnX + ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = color[i];
	                point[whereOnX - ((counter)/2 - 1)][ground - ((counter)/2 - 1)][i] = color[i];
	                point[whereOnX + ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = color[i];
	                point[whereOnX - ((counter)/2 - 1)][ground + ((counter)/2 - 1)][i] = color[i];
	                
	                controller.setColors(point);
	                controller.updateLedStripe();
	            
		    	    }
		    	
		    }else {
		    	
		    	   for(int i = 0; i < color.length; i++) {
		    			
		    			point = new int[20][20][3];
		    			
		    			controller.setColors(point);
			        controller.updateLedStripe();
		    			
		        }
		    	
		    }
		
		}
		
	}
	
	public static void makeAll(int[][][] point, int[] color) {
		
		goUp(point, color);
		makeMegaBoom(point, color);
		
	}
	
}
