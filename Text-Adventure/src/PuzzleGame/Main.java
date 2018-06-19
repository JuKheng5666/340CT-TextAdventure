package PuzzleGame;
/*Libraries used
 * *java.awt.event.* : Event and Event listener 
 * java.util.Random : Generator of random number
 * javax.swing.* : Respond to an event generate by user 
 * 
 * Main as public class.
 * Main class consist of JFrame which is extend of swing.*.
 * 
 * 
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Main {

	//JFrame is used to control the created frame
	static JFrame frame;

	//Puzzle class is used to define a puzzle characteristic and behavior
	static Puzzle puzzle;
	static stopWatch watch;
	static boolean puzzleComplete;
	static int n;
	static JLabel lblTimer, lblMilliseconds, lblSeconds, lblMinutes, lblHours;

	//function call falls here
	public static boolean main(boolean puzzleSuccess) {
		puzzleComplete = false;

		//a new JFrame have created with JFrame properties
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Slider Puzzle");
		frame.setUndecorated(true);

		//the frame size has been fixed
		frame.setSize(1050,580);

		//setLocationRelativeTo method is null to allocate the frame on the center of the system
		frame.setLocationRelativeTo(null);

		//do not allow the user to Resize the frame 
		frame.setResizable(false);

		//Purpose of the random number is allow the system to randomly generate the image puzzle
		Random rand = new Random();

		//the 'n' is based on how many images available and choose the image within the numbers
		n = rand.nextInt(4) + 1;

		//will contain every JComponent, contentPanel will be added into the frame later
		JPanel contentPanel = new JPanel(new BorderLayout());

		//layout containing 3 rows and one column
		//labels containing the title and instruction will be added into 
		//each row of the layout
		JPanel titlePanel = new JPanel(new GridLayout(3,1));

		//Label for title of the game
		//createEmptyBorder to put padding around the label
		JLabel lbl = new JLabel("Slider Puzzle");
		lbl.setFont(new Font("Verdana", 1, 20));
		lbl.setHorizontalAlignment(JLabel.CENTER);
		Border border = BorderFactory.createEmptyBorder(5,5,5,5);
		lbl.setBorder(border);
		titlePanel.add(lbl);

		//label for game instruction
		lbl = new JLabel("Arrange the image below to pass this mission. " + 
				"\nYou are allowed to skip after 2 minutes.");
		lbl.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(lbl);

		/*puzzle variable function as storing the image 
		 * ImageIcon is to create an un-initialize image 
		 * */
		JPanel puzzlePanel = new JPanel(new BorderLayout());
		puzzle = new Puzzle(new ImageIcon(Main.class.getResource("/Images/images"+n+".jpg")).getImage());
		setMouselistenerToPuzzle(puzzle);
		
		//set padding to move to center.. right and left padding 180
		Border puzzleBorder = BorderFactory.createEmptyBorder(0, 180, 0, 180);
		puzzlePanel.setBorder(puzzleBorder);

		//purpose of the add method is to show the item in the frame later on
		puzzlePanel.add(puzzle);

		//panel to show actual image of the puzzle
		JPanel actualPicPanel = new JPanel(new GridLayout(2,0));

		//use label to show the image
		JLabel lblImage = new JLabel();
		ImageIcon actualImg = new ImageIcon(Main.class.getResource("/Images/images"+n+".jpg"));
		Image img = actualImg.getImage();
		img = img.getScaledInstance(180, 200, Image.SCALE_SMOOTH);
		actualImg = new ImageIcon(img);
		lblImage.setIcon(actualImg);
		lblImage.setText("(Actual image)");

		//label for writing game instruction
		JTextArea jTxtInfo = new JTextArea();
		jTxtInfo.setSize(230, 800);
		jTxtInfo.setLineWrap(true);
		jTxtInfo.setWrapStyleWord(true);
		jTxtInfo.setBackground(new Color(0,0,0,0));
		jTxtInfo.getCaret().deinstall(jTxtInfo);
		jTxtInfo.setHighlighter(null);
		jTxtInfo.setEditable(false);
		
		jTxtInfo.setText("Game instructions: \n1. Click on the puzzle to start scrambling. \n2. Click again to stop scrambling. \n3. Start playing by moving a puzzle piece to the white space repeatedly to rearrange the image. Refer to the actual image for clues. \n4. Click on 'Reset Puzzle' if you want to start from the beginning again. \n5. You can move one puzzle piece at a time. Good Luck! \n6. Click 'Finish' button after completion.");

		actualPicPanel.add(lblImage);
		actualPicPanel.add(jTxtInfo);

		puzzlePanel.add(actualPicPanel, BorderLayout.EAST);
		
		//panel containing finish and skip buttons
		JPanel buttonPanel = new JPanel(new FlowLayout());

		//button to skip the puzzle
		JButton btnSkip = new JButton("Skip");
		btnSkip.setVisible(false);
		//close the whole frame when skip button is clicked
		btnSkip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//close frame
				frame.dispose();
				TextAdventure.Main.showWindow();
			}
		});

		//button to reset the puzzle
		JButton btnReset = new JButton("Reset Puzzle");

		//reset the puzzle when button is clicked
		btnReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				//remove the puzzle inside and then re-initialize the puzzle
				puzzlePanel.removeAll();
				puzzle = null;
				puzzle = new Puzzle(new ImageIcon(Main.class.getResource("/Images/images"+n+".jpg")).getImage());
				setMouselistenerToPuzzle(puzzle);
				puzzlePanel.add(puzzle);
				puzzlePanel.add(actualPicPanel, BorderLayout.EAST);
				puzzlePanel.repaint();	
			}
		});

		buttonPanel.add(btnReset);
		buttonPanel.add(btnSkip);

		//panel containing timer 
		JPanel timerPanel = new JPanel (new FlowLayout());
		lblTimer = new JLabel("TIMER: ");
		lblMilliseconds = new JLabel("0 : ");
		lblMilliseconds.setVisible(false);
		lblSeconds = new JLabel("0  ");
		lblMinutes = new JLabel("0 : ");
		lblHours = new JLabel("0: ");

		//call stop watch to start counting time
		watch = new stopWatch();
		watch.resetClock();

		timerPanel.add(lblTimer);
		timerPanel.add(lblHours);
		timerPanel.add(lblMinutes);
		timerPanel.add(lblSeconds);
		timerPanel.add(lblMilliseconds);

		//button to pause stop watch
		JButton btnPauseResume = new JButton("Pause ");
		btnPauseResume.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (btnPauseResume.getText() == "Pause "){
					watch.pause();
					btnPauseResume.setText("Resume");

					//do not allow reset when puzzle is paused
					btnReset.setVisible(false);

					//false to pause the puzzle, user cannot move the segments
					puzzle.setState(false);

				} 
				else if (btnPauseResume.getText() == "Resume"){
					watch.start(lblMilliseconds, lblSeconds, lblMinutes, lblHours);
					btnPauseResume.setText("Pause ");

					//only allow reset when puzzle is not paused
					btnReset.setVisible(true);

					//true to resume puzzle, segments can be moved again
					puzzle.setState(true);
				}
			}
		});

		timerPanel.add(btnPauseResume);
		titlePanel.add(timerPanel);

		//NORTH, CENTER, SOUTH is the position of the panels inside the frame
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		contentPanel.add(puzzlePanel, BorderLayout.CENTER);
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);

		//add the contentPanel into the frame
		frame.setContentPane(contentPanel);

		//setVisible is to allow the frame to be view by public
		frame.setVisible(true);

		//thread for checking whether the puzzle has been completed
		//also used for checking whether the timer has passed 2 minutes
		Thread t = new Thread(){
			public void run(){
				for (;;){

					try{
						sleep(10);

						//if slider puzzle has been completed
						//change button text to finish and stop the timer
						if (puzzle.isComplete() == true){
							puzzleComplete = true;
							btnSkip.setText("Finish");
							btnSkip.setVisible(true);
							watch.pause();
							btnReset.setVisible(false);
							btnPauseResume.setVisible(false);
						}

						//allow skip after 2 minutes
						if (watch.getMinutes() >= 2){
							btnSkip.setVisible(true);
						}
					}catch(Exception e){
						System.out.println("Error in thread 2");
					}
				}
			}
		};

		t.start();

		//kill program on closing the frame
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		return puzzleComplete;
	}
	
	//for checking whether puzzle has been completed
	public static boolean getPuzzleStatus(){
		if (puzzleComplete)
			return true;
		else
			return false;
	}

	public static String getAnimal(){
		String animal = "";
		
		switch (n){
		case 1:
			animal = "a rabbit";
			break;
		case 2:
			animal = "an orang utan";
			break;
		case 3:
			animal = "a fox";
			break;
		case 4:
			animal = "a monkey";
			break;
		default:
			animal = "a rabbit";
			break;
		}
		
		return animal;
	}
	
	public static void setMouselistenerToPuzzle(Puzzle puzzle){
		/*
		 * addMouseListener is to enable the functionalities once it is clicked
		 * if addMouseListener is null, no action will be take 
		 * if the puzzle have not start it will be switch to start
		 * else if the puzzle mixing is true then set to false
		 * else will call the puzzle.onClick
		 * */
		puzzle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!puzzle.started){
					puzzle.start();
					watch.start(lblMilliseconds, lblSeconds, lblMinutes, lblHours);
				}
				else if (puzzle.mixing){
					puzzle.mixing = false;
				}
				else puzzle.onClick(e);
			}
		});
	}
}
