package PuzzleGame;
import javax.swing.JLabel;

//for timer purposes
public class stopWatch {
	static int milliseconds = 0;
	static int seconds = 0;
	static int minutes = 0;
	static int hours = 0;
	
	//toggle stop watch start or pause
	//and also for checking whether watch is paused
	//paused means false, continuing means true
	static boolean state = true;
	
	//reset clock back to 0
	public void resetClock(){
		milliseconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
	}
	
	//start the timer
	public void start(JLabel lblMilli, JLabel lblSeconds, JLabel lblMinutes, JLabel lblHours){
		state = true;
		
		Thread t = new Thread(){
			public void run(){		
				for (;;){
					if (state == true){
						try{
							sleep(1);
							
							//after milliseconds is more than 1000
							//reset milliseconds back to 0 and increment seconds by 1
							if (milliseconds > 1000){
								milliseconds = 0;
								seconds++;
							}
							
							//after seconds is more than 59
							//reset seconds and milliseconds to 0 and increment minutes by 1
							if (seconds > 59){
								milliseconds = 0;
								seconds = 0;
								minutes++;
							}
							
							//after minutes is more than 59
							//reset milliseconds, seconds and minutes to 0 and increment hours by one
							if (minutes > 59){
								milliseconds = 0;
								seconds = 0;
								minutes = 0;
								hours++;
							}
							
							//show time using the labels on the window
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
	
	//get the value for minutes (for checking whether 2 minutes has passed)
	public int getMinutes(){
		return minutes;
	}
}
