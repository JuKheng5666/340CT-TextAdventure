package PuzzleGame;
import javax.swing.JLabel;

public class stopWatch {
	static int milliseconds = 0;
	static int seconds = 0;
	static int minutes = 0;
	static int hours = 0;
	
	//toggle stop watch start or pause
	static boolean state = true;
	
	public void start(JLabel lblMilli, JLabel lblSeconds, JLabel lblMinutes, JLabel lblHours){
		state = true;
		
		Thread t = new Thread(){
			public void run(){
				
				for (;;){
					if (state == true){
						try{
							sleep(1);
							
							if (milliseconds > 1000){
								milliseconds = 0;
								seconds++;
							}
							if (seconds > 59){
								milliseconds = 0;
								seconds = 0;
								minutes++;
							}
							if (minutes > 59){
								milliseconds = 0;
								seconds = 0;
								minutes = 0;
								hours++;
							}
							
							//show time using the labels on frame
							lblMilli.setText(" : " + milliseconds);
							milliseconds++;
							lblSeconds.setText(" : " + seconds);
							lblMinutes.setText(" : " + minutes);
							lblHours.setText("" + hours);
							
						}catch(Exception e){
							System.out.println("Stop watch error!");
						}
					}
					else
						break;
				}
			}
		};
		
		//start the thread
		t.start();
	}
	
	//pause the timer
	public void pause(){
		state = false;
	}
	
	public int getMinutes(){
		return minutes;
	}
}
