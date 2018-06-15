package TextAdventure;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//Main class 
public class Main {

	//main text adventure UI elements
	JFrame window, knowledgeWindow;
	Container con, con2;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, kchoiceButtonPanel, kBackPanel;
	JLabel titleNameLabel, scoreLabel, scoreLabelNumber, weaponLabel, weaponLabelName, background;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, choice1, choice2, choice3, choice4, kChoice1, kChoice2, kChoice3, kChoice4, kChoice5, kChoice6, kReady, kBack;
	JTextArea mainTextArea;

	//game variables
	static int playerScore; 

	//internal variables
	static boolean puzzleSuccess;
	static volatile boolean scramblerSuccess;
	boolean lastPuzzle = false;
	String position, randomGame;

	//to be used for UI element background
	Color semiTransparent = new Color(0,0,0,30);
	Color transparent = new Color(0,0,0,0);

	//event handlers for title screen and user choices
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();

	//initialize puzzle games
	PuzzleGame.Main puzzleGame =  new PuzzleGame.Main();
	WordScrambler.UIMain wordScrambler = new WordScrambler.UIMain();

	public static void main(String[] args) {
		//run the default main
		new Main();
	}

	public Main(){
		//create a frame for text adventure
		window = new JFrame();

		//the frame properties
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new JLabel(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
		window.setContentPane(background);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setLayout(null);

		//con used to decorate the frame
		//JPanels containing JLabel, buttons, texts etc. will be added into con 
		con = window.getContentPane();

		//Panel for the game title and its properties
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(semiTransparent);

		//Label with the game title and its properties
		titleNameLabel = new JLabel("Science Exploration");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);	

		//add the label into the titleNamePanel
		titleNamePanel.add(titleNameLabel);

		//Panel for start button and its properties
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(transparent);

		//start button and its properties
		startButton = new JButton("Begin");
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		//add start button into the panel
		startButtonPanel.add(startButton);

		//add the panels into con
		con.add(titleNamePanel);
		con.add(startButtonPanel);

		//The display is set to true so player can see the title and begin button
		window.setVisible(true);
	}

	//set up the game screen 
	public void createGameScreen(){
		//hide the start button and game title from view
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		//this panel will be used to include a JTextArea showing
		//the story to the user as they progress through the game
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(semiTransparent);

		//a JTextArea for the story and its properties
		mainTextArea = new JTextArea("");
		mainTextArea.setHighlighter(null);
		mainTextArea.setEditable(false);
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextArea.setBackground(semiTransparent);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true);
		mainTextArea.getCaret().deinstall(mainTextArea);

		//add the JTextArea to the panel and then to con
		mainTextPanel.add(mainTextArea);
		con.add(mainTextPanel);

		//a panel and its properties for the choice buttons in the knowledge bank page
		kchoiceButtonPanel = new JPanel();
		kchoiceButtonPanel.setBounds(100, 350, 600, 250);
		kchoiceButtonPanel.setBackground(transparent);
		kchoiceButtonPanel.setLayout(new GridLayout(6,2));

		//Design button for knowledge bank -kChoice1
		kChoice1 = new JButton("Knowledge Choice 1");
		kChoice1.setFont(normalFont);
		kChoice1.setFocusPainted(false);
		kChoice1.addActionListener(choiceHandler);
		kChoice1.setActionCommand("kc1");
		kchoiceButtonPanel.add(kChoice1);

		//Design button for knowledge bank -kChoice2
		kChoice2 = new JButton("Knowledge Choice 2");
		kChoice2.setFont(normalFont);
		kChoice2.setFocusPainted(false);
		kChoice2.addActionListener(choiceHandler);
		kChoice2.setActionCommand("kc2");
		kchoiceButtonPanel.add(kChoice2);

		//Design button for knowledge bank -kChoice3
		kChoice3 = new JButton("Knowledge Choice 3");
		kChoice3.setFont(normalFont);
		kChoice3.setFocusPainted(false);
		kChoice3.addActionListener(choiceHandler);
		kChoice3.setActionCommand("kc3");
		kchoiceButtonPanel.add(kChoice3);

		//Design button for knowledge bank -kChoice4
		kChoice4 = new JButton("Knowledge Choice 4");
		kChoice4.setFont(normalFont);
		kChoice4.setFocusPainted(false);
		kChoice4.addActionListener(choiceHandler);
		kChoice4.setActionCommand("kc4");
		kchoiceButtonPanel.add(kChoice4);

		//Design button for knowledge bank -kChoice5
		kChoice5 = new JButton("Knowledge Choice 5");
		kChoice5.setFont(normalFont);
		kChoice5.setFocusPainted(false);
		kChoice5.addActionListener(choiceHandler);
		kChoice5.setActionCommand("kc5");
		kchoiceButtonPanel.add(kChoice5);

		//Design button for knowledge bank -kChoice6
		kChoice6 = new JButton("Knowledge Choice 6");
		kChoice6.setFont(normalFont);
		kChoice6.setFocusPainted(false);
		kChoice6.addActionListener(choiceHandler);
		kChoice6.setActionCommand("kc6");
		kchoiceButtonPanel.add(kChoice6);

		//Button for user to click when they are ready to play the game
		kReady = new JButton("Knowledge Choice 7");
		kReady.setFont(normalFont);
		kReady.setFocusPainted(false);
		kReady.addActionListener(choiceHandler);
		kReady.setActionCommand("kr");
		kchoiceButtonPanel.add(kReady);

		//add button panel for knowledge bank to con
		con.add(kchoiceButtonPanel);

		//panel for back button in knowledge bank page
		kBackPanel = new JPanel();
		kBackPanel.setBounds(10, 10, 80, 50);
		kBackPanel.setBackground(semiTransparent);
		kBackPanel.setLayout(new GridLayout(1,1));
		kBackPanel.setVisible(false);

		//back button
		kBack = new JButton("Back");
		kBack.setFont(normalFont);
		kBack.setFocusPainted(false);
		kBack.addActionListener(choiceHandler);
		kBack.setActionCommand("kBack");

		//add back button to panel and then to con
		kBackPanel.add(kBack); 
		con.add(kBackPanel);

		//a panel and its properties for the choice buttons when user plays the game
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 350, 300, 150);
		choiceButtonPanel.setBackground(semiTransparent);
		choiceButtonPanel.setLayout(new GridLayout(4,1));

		//add button panel for game to con
		con.add(choiceButtonPanel);

		//Design button one - Choice 1
		choice1 = new JButton("Choice 1");
		choice1.setFont(normalFont);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");
		choiceButtonPanel.add(choice1);

		//Design button two -Choice 2
		choice2 = new JButton("Choice 2");
		choice2.setFont(normalFont);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");
		choiceButtonPanel.add(choice2);

		//Design button three -Choice 3
		choice3 = new JButton("Choice 3");
		choice3.setFont(normalFont);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");
		choiceButtonPanel.add(choice3);

		/*
		//Design button four -Choice 4
		choice4 = new JButton("Choice 4");
		//choice4.setBackground(Color.black);
		//choice4.setForeground(Color.white);
		choice4.setFont(normalFont);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");
		choiceButtonPanel.add(choice4);
		 */

		//panel for user's game score
		playerPanel = new JPanel();
		playerPanel.setBounds(100, 15, 600, 50);
		playerPanel.setBackground(semiTransparent);
		playerPanel.setLayout(new GridLayout(1,4));
		con.add(playerPanel);

		//score text label to be displayed on screen 
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(normalFont);
		scoreLabel.setForeground(Color.white);

		//add label to playerPanel
		playerPanel.add(scoreLabel);

		//score digits label to be displayed on screen 
		scoreLabelNumber = new JLabel();
		scoreLabelNumber.setFont(normalFont);
		scoreLabelNumber.setForeground(Color.white);
		playerPanel.add(scoreLabelNumber);

		//go to playerSetup
		playerSetup();
	}

	//show knowledge bank page 
	public void playerSetup(){
		//initialize the player score
		playerScore = 0;
		scoreLabelNumber.setText("" + playerScore);

		//load UI elements on screen
		knowledgeBank();
	}

	public void knowledgeBank(){
		//set unwanted panels to not be visible to the user
		//and panels to be shown as true
		mainTextPanel.setVisible(true);
		choiceButtonPanel.setVisible(false);
		kchoiceButtonPanel.setVisible(true);
		playerPanel.setVisible(true);
		kBackPanel.setVisible(false);

		//set jungle background to the window
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));

		//set position so that choiceHandler know what must be shown to the user next
		position="knowledge";

		//Reset the mainTextPanel by removing everything and readding the JTextArea with new text
		mainTextPanel.removeAll();
		mainTextArea.setText("Click on any of the button to read about animals before begin the game.\n");
		mainTextPanel.add(mainTextArea);

		//set text to be displayed on the buttons
		kChoice1.setText("Tiger");
		kChoice2.setText("Fox");
		kChoice3.setText("Monkey");
		kChoice4.setText("Parrot");
		kChoice5.setText("Rabbit");
		kChoice6.setText("Snake");
		kReady.setText("PLAY");

		//usually do revalidate together with repaint
		//revalidate is used on the HIGHEST AFFECTED COMPONENT
		//this is to show changes on UI components to the window 
		con.revalidate();
		con.repaint();	
	}

	//When user clicks on kChoice1, show learning info on tigers
	public void tigerKnowledge(){
		//hide panels that are not required to be shown
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		//show back button for user to go back
		kBackPanel.setVisible(true);

		//show the learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Tiger.jpg")));

		con.revalidate();
		con.repaint();
	}

	//When user clicks on kChoice2, show learning info on foxes
	public void foxKnowledge(){
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		kBackPanel.setVisible(true);

		//show the learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Fox.jpg")));

		con.revalidate();
		con.repaint();
	}

	//When user clicks on kChoice3, show learning info on monkeys
	public void monkeyKnowledge(){
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		kBackPanel.setVisible(true);

		//show learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Monkey.jpg")));

		con.revalidate();
		con.repaint();
	}

	//When user clicks on kChoice4, show learning info on parrots
	public void parrotKnowledge(){
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		kBackPanel.setVisible(true);

		//show learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Parrot.jpg")));

		con.revalidate();
		con.repaint();
	}

	//When user clicks on kChoice5, show learning info on rabbits
	public void rabbitKnowledge(){
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		kBackPanel.setVisible(true);

		//show the learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Rabbit.jpg")));

		con.revalidate();
		con.repaint();
	}

	//When user clicks on kChoice6, show learning info on snakes
	public void snakeKnowledge(){
		kchoiceButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		choiceButtonPanel.setVisible(false);
		playerPanel.setVisible(false);

		kBackPanel.setVisible(true);

		//show learning info which is in image form
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Snake.jpg")));

		con.revalidate();
		con.repaint();
	}

	//first scenario (starting point of user in the game)
	public void wildAnimal(){
		position="wildAnimal";

		//set jungle background image
		background.setIcon(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
		choiceButtonPanel.setVisible(true);
		kchoiceButtonPanel.setVisible(false);

		//show story text
		mainTextPanel.removeAll();
		mainTextArea.setText("You have a treasure map that leads to ancient relic. \nYou are now at the entrance of amazon jungle \n\nYou heard a nearby bush rustle.\nYou panic and you start to run.\n\n Which wild animal you think it will be?");		
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		//change button texts
		choice1.setText("Snake");
		choice2.setText("Tiger");
		choice3.setText("Monkey");
	}

	//user enters this scenario if "Monkey" was chosen
	public void Monkey(){
		position="monkey";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a monkey. Solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); // puzzle console
		choice2.setText("");
		choice3.setText("");
	}

	//user enters this scenario if "Snake" was chosen
	public void Snake(){
		position= "snake";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a snake.Click next to solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");// puzzle console
		choice2.setText("");
		choice3.setText("");
	}

	//user enters this scenario if "Tiger" was chosen
	public void Tiger(){
		position="tiger";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a tiger.Click next to solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");// puzzle console
		choice2.setText("");
		choice3.setText("");
	}

	//enter crossRoad() after puzzle solved / skipped
	public void crossRoad(){
		position = "crossRoad";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/You are lost.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("You take a look behind you.\n\n Sadly, it was just a wild rabbit and you ran away like a little girl.\n\nYou are now lost.\nChoose where you want to go");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Go north");
		choice2.setText("Go east");
		choice3.setText("Go west");
	}

	//choice:Go north 
	//will come to north method
	//brings user back to wildAnimal() method
	public void north(){
		position = "north";

		mainTextPanel.removeAll();
		mainTextArea.setText("Somehow you end up at the entrance of the jungle again.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Continue on");
		choice2.setText("");
		choice3.setText("");
	}

	//choice:Go east
	//will come to east method
	//brings user back to crossRoad() method
	public void east(){
		position = "east";

		mainTextPanel.removeAll();
		mainTextArea.setText("There is nothing here. Go back");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Go back to the crossroad");
		choice2.setText("");
		choice3.setText("");
	}

	//choice:Go west
	//will come to west method
	public void west(){
		position = "west";

		mainTextPanel.removeAll();
		mainTextArea.setText("As you walk, a monkey jump to you and snatch your hat.\n\nWhat you going to do?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Just let it be");
		choice2.setText("Chase the monkey");
		choice3.setText("");
	}

	//choice: Chase the monkey
	//let user choose what they want to do
	public void chaseMonkey(){
		position ="chasemonkey";

		mainTextPanel.removeAll();
		mainTextArea.setText("The monkey ran away.\n\n You gave up chasing it.\n\n You are thinking what to do next.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Find some fruits to eat."); //Another puzzle game
		choice2.setText("Find a way out"); //Another scenario then puzzle game
		choice3.setText("Just sit and relax");// wake up mysteriously in the temple
	}

	//choice: Find some fruits to eat
	//user either chooses to play a puzzle game or walk away
	public void findFruit(){
		position="findfruit";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Fruits garden.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("After a long time, you feel so thirsty due to the hot weather. \nYou are hungry and exhausted.\n\nYou trip and fall onto a stone accidentally.\nYou found a combitnation set of numbers on the stone.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Solve the code"); //play a puzzle game
		choice2.setText("Walk away"); //will end up at the aWayOut() method
		choice3.setText("");
	}

	//choice: Find a way out (from chaseMonkey()) / Walk away (from findFruit())
	//user chooses to either play a puzzle game or walk away
	public void aWayOut(){
		position="awayout";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/guessParrot.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("A parrot sang a song.\n\nYou have to guess the title of the song in order to get the secret code.");//will discover secret code
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Accept its challenge."); //play a puzzle game
		choice2.setText("Walk away"); //will end up in the temple
		choice3.setText("");
	}

	//choice: Just let it be (from west()) / Walk away (from aWayOut())
	//brings user to discoverTemple() method
	public void ruinTemple(){
		position = "ruintemple";

		mainTextPanel.removeAll();
		mainTextArea.setText("As you are thinking a way out.\n You discover a ruin temple.\n\n So you take your chance to discover more.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Discover the temple");//will get secret code
		choice2.setText("");
		choice3.setText("");
	}

	//choice: Just sit and relax (from chaseMonkey())
	//brings user to wakeUp()
	public void sitAndRelax(){
		position="sitandrelax";//end up in temple

		mainTextPanel.removeAll();
		mainTextArea.setText("You sat and relax.\n\n Something hit you and you passed out.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");// wakeUp()
		choice2.setText("");
		choice3.setText("");
	}

	//comes from sitAndRelax()
	//brings user to discoverTemple() method
	public void wakeUp(){
		position="wakeup";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/jungletemple.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("You found yourself in a ruined temple mysteriously after you woke up.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");
		choice2.setText("");
		choice3.setText("");
	}

	//comes from ruinTemple() and wakeUp()
	//lets user choose which path to go
	public void discoverTemple(){
		position ="discovertemple";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/temple.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("You look around and there is two tunnels.Which tunnel should you go?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Left tunnel"); 
		choice2.setText("Right tunnel"); 
		choice3.setText("");
	}

	//choice: left tunnel
	//ask if user wants to change his choice
	public void leftTunnelConfirm(){
		position = "leftTunnelConfirm";

		mainTextPanel.removeAll();
		mainTextArea.setText("I hope you won't regret your choice. \nYou choose to be in the left tunnel. \n\nDo you want to change your decicion?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Yes"); //go back to discoverTemple()
		choice2.setText("No");  //proceed to leftTunnelGoodLuck()
		choice3.setText("");
	}

	//comes from leftTunnelConfirm()
	//brings user to leftTunnel()
	public void leftTunnelGoodLuck(){
		position ="leftTunnelGoodLuck";

		mainTextPanel.removeAll();
		mainTextArea.setText("Good luck, I hope you will make your way out the temple alive.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from leftTunnelGoodLuck()
	//brings user to leftTunnelTrap1()
	public void leftTunnel(){
		position = "leftTunnel";

		mainTextPanel.removeAll();
		mainTextArea.setText("You walk your way through a dark and scary temple. \nThere aren't any signs of living in the tunnel and you can barely see the path ahead.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");  
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from leftTunnel()
	//brings user to leftTunnelTrapDecision1()
	public void leftTunnelTrap1(){
		position = "leftTunnelTrap1";

		mainTextPanel.removeAll();
		mainTextArea.setText("Suddenly, you take a wrong step and you heard 'click-clark!'. Part of the floor starts to sink.\n\n You start to feel cold sweat running down your face. \nYour expression went wild when arrows flew toward your direction.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from leftTunnelTrap1()
	//let user try to save themselves
	public void leftTunnelTrapDecision1(){
		position = "leftTunnelTrapDecision1";

		mainTextPanel.removeAll();
		mainTextArea.setText("You should: ");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Bend down"); //proceed to leftTunnelTrapDecision2()
		choice2.setText("Stand still"); //you died, brings user to leftTunnelDie()
		choice3.setText("");
	}

	//comes from surviving leftTunnelTrapDecision1
	//let user try to save themselves from another trap
	public void leftTunnelTrapDecision2(){
		position = "leftTunnelTrapDecision2";

		mainTextPanel.removeAll();
		mainTextArea.setText("The arrows missed you. \nBut you already feel dead inside knowing that there will be more arrows to come. \n\nA few moments later, two more arrows flew out towards your left. \nYou should: ");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Bend to your right"); //proceed to leftTunnelHiddenSwitch() 
		choice2.setText("Bend to your left");  //you died, brings user to leftTunnelDie()
		choice3.setText("Stand still"); //you died, brings user to leftTunnelDie()
	}

	//comes from surviving leftTunnelTrapDecision2
	//let user choose what they want to do
	public void leftTunnelHiddenSwitch(){
		position = "leftTunnelHiddenSwitch";

		mainTextPanel.removeAll();
		mainTextArea.setText("The arrows missed you once again. Good job! \n\n As you turn to the path ahead, you caught a glimpse of a hidden switch between two pillars. \n\nWhat do you do?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Run to the switch"); //proceed to leftTunnelPressSwitch()
		choice2.setText("Ignore and try to escape"); //you died, brings user to leftTunnelDie()
		choice3.setText("Look for another way"); //proceed to leftTunnelAnotherWay()
	}

	//choice: Run to the switch (from leftTunnelHiddenSwitch())
	//brings user to leftTunnelDoorOpen()
	public void leftTunnelPressSwitch(){
		position = "leftTunnelPressSwitch";

		mainTextPanel.removeAll();
		mainTextArea.setText("You run towards the switch as fast as you can. \n\nWith hopes that the switch does not trigger additional traps, you pressed the switch.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//choice: Look for another way
	//let user choose what they want to do
	public void leftTunnelAnotherWay(){
		position = "leftTunnelAnotherWay";

		mainTextPanel.removeAll();
		mainTextArea.setText("You hide behind a pile of rubble and as you look around, you see a small passage across you. \n\nCrawl through the passageway?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Yes"); //proceeds to leftTunnelCrawl()
		choice2.setText("No"); //user changes his mind and runs to the switch (proceed to leftTunnelPressSwitch())
		choice3.setText("");
	}

	//comes from leftTunnelPressSwitch()
	//brings user to tunnelGenie()
	public void leftTunnelDoorOpen(){
		position = "leftTunnelDoorOpen";

		mainTextPanel.removeAll();
		mainTextArea.setText("The sound of whirring trap mechanisms stop. A door at the end of the tunnel opens.\n\n You go through the door and found yourself in a mysterious room.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from leftTunnelAnotherWay()
	//brings user to tunnelGenie()
	public void leftTunnelCrawl(){
		position = "leftTunnelCrawl";

		mainTextPanel.removeAll();
		mainTextArea.setText("You run towards the passageway and start crawling on all fours into the dark.\n\n Few minutes later, you found yourself at a mysterious room.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from leftTunnelDoorOpen(), leftTunnelCrawl() and righttunnelsafeatlast()
	//brings user to tunnelGeniePuzzle()
	public void tunnelGenie(){
		position = "tunnelGenie";

		mainTextPanel.removeAll();
		mainTextArea.setText("Feeling relieved that the trap was over, you look around the room when a mysterious genie suddenly appears and asks you to solve his question in return for a code to the ancient relic.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Accept the challenge"); //play puzzle game
		choice2.setText("It is now or never"); //play puzzle game
		choice3.setText("Try walking away"); //play puzzle game
	}

	//comes from tunnelGenie()
	//brings user to leftTunnelRelic()
	public void tunnelGeniePuzzle(){
		position = "tunnelGeniePuzzle";

		mainTextPanel.removeAll();
		mainTextArea.setText("The genie moves closer to you with great desperation in its eyes.\n\nYou could not ignore his pleas and decided to solve his question.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from tunnelGeniePuzzle()
	//brings user to ending(), user finishes the game
	public void leftTunnelRelic(){
		position = "leftTunnelRelic";

		mainTextPanel.removeAll();
		mainTextArea.setText("You make a long series of rights and lefts as instructed from the genie's code. \n\n Just as you were beginning to have doubts...");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//if user did not manage to dodge any of the traps, user ends up here
	//user will then have to start over dodging the traps from leftTunnelTrapDecision1()
	public void leftTunnelDie(){
		position = "leftTunnelDie";

		mainTextPanel.removeAll();
		mainTextArea.setText("You didn't manage to dodge the trap and died. Go back and make your choice again.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//choice: Right Tunnel (from discoverTemple())
	//brings user to rightTunnelGoodLuck()
	public void rightTunnelConfirm(){
		position = "rightTunnelConfirm";

		mainTextPanel.removeAll();
		mainTextArea.setText("I hope you won't regret your choice. \nYou choose to be in the right tunnel. \n\nDo you want to change your decicion?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Yes"); //Step on floor and arrows comes out 
		choice2.setText("No"); //To be continue 
		choice3.setText("");
	}

	//comes from rightTunnelConfirm
	//brings user to rightTunnel
	public void rightTunnelGoodLuck(){
		position ="rightTunnelGoodLuck";

		mainTextPanel.removeAll();
		mainTextArea.setText("Good luck, I hope you will make your way out the temple alive.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnelGoodLuck()
	//brings user to rightTunnelStatue()
	public void rightTunnel(){
		position ="rightTunnel";

		mainTextPanel.removeAll();
		mainTextArea.setText("You walk your way through a dark and scary temple.\n There aren't any sigs of living in the tunnel and you can barely see the path.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnel()
	//brings user to rightTunnelHitWall()
	public void rightTunnelStatue(){
		position ="righttunnelstatue";
		mainTextPanel.removeAll();
		mainTextArea.setText("You decide to take a rest on a statue and there the statue head bend to a side, the wall surrounding you start to move.\n\n You start to scream for help\nThen you realize the walls have been narrow down.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnelStatue()
	//brings user to rightTunnelKeepCool()
	public void rightTunnelHitWall(){
		position ="righttunnelhitwall";
		mainTextPanel.removeAll();
		mainTextArea.setText("You try to hit the wall and hurt your hands.\n\n Out of nowhere, the sand start to fall from above.\n Is hard to tell how the sand start to come in, but you will skip that part and try to save yourself.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnelHitWall()
	//lets user choose what they want to do
	public void rightTunnelKeepCool(){
		position ="righttunnelkeepcool";
		mainTextPanel.removeAll();
		mainTextArea.setText("You keep your cool and try to find a way out. ");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Try to feel the walls"); //brings user to rightTunnelSwitch()
		choice2.setText("Continue hit the walls"); //you died, brings user to rightTunnelDie()
		choice3.setText("Shout for help "); //brings user to rightTunnelShout()
	}

	//comes from rightTunnelKeepCool()
	//brings user to rightTunnelPressSwitch()
	public void rightTunnelSwitch(){
		position = "righttunnelswitch";

		mainTextPanel.removeAll();
		mainTextArea.setText("You feel the switch and you press the switch with all your strength.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed the story
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnelSwitch()
	//brings user to rightTunnelSafeAtLast()
	public void rightTunnelPressSwitch(){
		//The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.
		position = "righttunnelpressswitch";

		mainTextPanel.removeAll();
		mainTextArea.setText("The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue
		choice2.setText(""); 
		choice3.setText("");
	}

	//comes from rightTunnelPressSwitch()
	//brings user to tunnelGenie()
	public void rightTunnelSafeAtLast(){
		position ="righttunnelsafeatlast";

		mainTextPanel.removeAll();
		mainTextArea.setText("The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Go to genie
		choice2.setText("");  
		choice3.setText("");
	}

	//if user did not manage to dodge any of the traps, user ends up here
	//user will then have to start over dodging the traps from rightTunnelKeepCool()
	public void rightTunnelDie(){
		position = "righttunneldie";

		mainTextPanel.removeAll();
		mainTextArea.setText("Eventually that doesn't help.You die in the game. Choose your choice again.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue back to the tunnelkeepcool
		choice2.setText(""); 
		choice3.setText("");
	}

	//before showing puzzle game to user, show this
	public void askToSolve(){
		position="askToSolve";

		mainTextPanel.removeAll();
		mainTextArea.setText("Solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">");
		choice2.setText("");  
		choice3.setText("");
	}

	//when user solved/skipped a puzzle game, show this
	//for scenarios in the jungle
	public void codeSolve(){
		position="codesolve";

		mainTextPanel.removeAll();
		mainTextArea.setText("You obtained the secret numbers.\n\nRemember the number code.");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText("");  
		choice3.setText("");
	}

	//when user solved/skipped a puzzle game, show this
	//for scenarios inside the temple
	public void trapSolve(){
		position="trapsolve";
		lastPuzzle = true;

		mainTextPanel.removeAll();
		mainTextArea.setText("Congratulations, you solved the puzzle game.\n");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText(">"); //For findFruit() and awayout - Accept challenge will have different outcomes
		choice2.setText("");  
		choice3.setText("");
	}

	//story end
	public void ending(){
		position = "ending";

		mainTextPanel.removeAll();
		mainTextArea.setText("You arrive at the heart of the ruined temple. \nYou found the ancient relic.\nYay! You did it!");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("THE END");
		choice2.setText("");  
		choice3.setText("");
	}

	//after story ends, ask if user wants to play the again
	public void restart(){
		position = "restart";

		mainTextPanel.removeAll();
		mainTextArea.setText("Congratulations on completing the game. \n\n Do you want to restart?");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Restart");	//user starts the game from the beginning again (wildAnimal())
		choice2.setText("End");  //closes the program
		choice3.setText("");
	}

	//function used to randomize which puzzle game is shown to the user
	public void GameRandomizer() {
		Random rand = new Random();

		//get a random number between 0 (inclusive) and 1000
		//the mod of the random number is obtained and stored in the seed variable
		int seed = rand.nextInt(1000) % 2;

		//reset word scrambler puzzle game completion status
		//false means not solved yet
		WordScrambler.UIMain.setScramblerStatus(false);

		//determine which puzzle game is shown to the user
		switch (seed) {
		//if seed is an even number, slider puzzle is shown
		case 0:
			//store the completion status of the slider puzzle game to puzzleSuccess variable
			puzzleSuccess = PuzzleGame.Main.main(puzzleSuccess);

			//randomGame variable to set which game was chosen by the game randomizer
			randomGame = "slider";
			break;

			//if seed is an odd number, word scrambler is shown
		case 1:
			//store the completion status of the word scrambler puzzle game to scramberSuccess variable
			scramblerSuccess = WordScrambler.UIMain.main(scramblerSuccess);
			randomGame = "scrambler";
			break;
		}
	}

	//function used to check whether the puzzle game has been completed or skipped
	public boolean checkPuzzleCompletion(){
		//boolean flag to store whether the puzzle game is completed or skipped
		//false means skipped (give some penalty)
		//true means completed (reward some points)
		boolean done;

		//switch case to check which game was randomized and provide score accordingly
		switch (randomGame){
		//if the game is slider puzzle
		case "slider":
			System.out.println("slider"); //for testing purposes

			//get slider puzzle status then
			//if true, game was completed, award the user 100 points
			if (PuzzleGame.Main.getPuzzleStatus()){
				playerScore += 100;

				//update score on screen
				scoreLabelNumber.setText(String.valueOf(playerScore));

				con.revalidate();
				con.repaint();

				done = true;
			}
			//if false, game was skipped, minus 100 points
			else{
				playerScore -= 100;
				done = false;
			}
			break;

			//if the game is word scrambler
		case "scrambler":
			System.out.println("scrambler"); //for testing purposes

			//get word scrambler game completion status
			//if true, game has been completed, award the user with 100 points
			if (WordScrambler.UIMain.getScramblerStatus()){
				playerScore += 100;

				//update score on screen
				scoreLabelNumber.setText(String.valueOf(playerScore));

				con.revalidate();
				con.repaint();

				done = true;
			}
			else
				done = false;
			break;

		default:
			done = false;
			break;
		}

		return done;
	}

	//got bo??
	public void gameOver(){
		position = "gameOver";

		mainTextPanel.removeAll();
		mainTextArea.setText("GAME OVERR!! \nYour score reached below 0. \n\nBetter luck next time!");
		mainTextPanel.add(mainTextArea);

		con.revalidate();
		con.repaint();

		choice1.setText("Restart");
		choice2.setText("");  
		choice3.setText("");
	}

	//handle the screen 
	//createGameScreen method is called
	public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			createGameScreen();
		}
	}

	//the choice handler to handle the user button choices using switch case 
	public class ChoiceHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String yourChoice = event.getActionCommand();
			
			//depending on the text saved in position variable, enter different cases 
			//and then there will be another switch case depending on which button user clicks, which
			//determines what text is shown to the user / where user progress in the game
			switch(position){
			case "crossRoad":
				switch(yourChoice){
				case "c1": north(); break;
				case "c2": east();break;
				case "c3": west();break;
				}
				break;

			case "north":
				switch(yourChoice){
				case "c1": wildAnimal(); break;
				}
				break;

			case "east":
				switch(yourChoice){
				case "c1": crossRoad(); break;
				}
				break;

			case "west":
				switch(yourChoice){
				case "c1": ruinTemple(); break;
				case "c2": chaseMonkey(); break;
				}
				break;

			case "wildAnimal":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					Snake();
					break;

				case "c2":
					GameRandomizer();
					Tiger();
					break;

				case "c3":
					GameRandomizer();
					Monkey();
					break;
				}
				break;

			case "snake":
				switch(yourChoice){
				case "c1": 
					System.out.println(checkPuzzleCompletion());
					crossRoad();
					break;
				}
				break;

			case "tiger":
				switch(yourChoice){
				case "c1":
					System.out.println(checkPuzzleCompletion());
					crossRoad();
					break;
				}
				break;

			case "monkey":
				switch(yourChoice){
				case "c1":
					System.out.println(checkPuzzleCompletion());
					crossRoad();
					break;
				}
				break;

			case "chasemonkey":
				switch(yourChoice){
				case "c1": findFruit(); break;
				case "c2": aWayOut(); break;
				case "c3": sitAndRelax();break;
				}
				break; 

			case "askToSolve":
				switch(yourChoice){
				case "c1": codeSolve(); break;
				}
				break;

			case "findfruit":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					askToSolve();
					break;

				case "c2": aWayOut(); break;
				}
				break;

			case "awayout":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					askToSolve();
					break;

				case "c2": sitAndRelax(); break;
				}
				break;

			case "sitandrelax":
				switch(yourChoice){
				case "c1": wakeUp(); break;
				}
				break;	

			case "wakeup":
				switch(yourChoice){
				case "c1": discoverTemple(); break;
				}
				break;		

			case "ruintemple":
				switch(yourChoice){
				case "c1": discoverTemple(); break;
				}
				break;

			case "discovertemple":
				switch(yourChoice){
				case "c1": leftTunnelConfirm(); break;
				case "c2": rightTunnelConfirm(); break;
				}
				break;	

			case "leftTunnelConfirm":
				switch (yourChoice){
				case "c1": discoverTemple(); break;
				case "c2": leftTunnelGoodLuck(); break;
				}
				break;

			case "leftTunnelGoodLuck":
				switch (yourChoice){
				case "c1": leftTunnel(); break;
				}
				break;

			case "rightTunnelGoodLuck":
				switch (yourChoice){
				case "c1": rightTunnel(); break;
				}
				break;

			case "rightTunnelConfirm":
				switch (yourChoice){
				case "c1": discoverTemple(); break;
				case "c2": rightTunnelGoodLuck(); break;
				}
				break;

			case "leftTunnel":
				switch (yourChoice){
				case "c1": leftTunnelTrap1(); break;
				}
				break;

			case "leftTunnelTrap1":
				switch (yourChoice){
				case "c1": leftTunnelTrapDecision1(); break;
				}
				break;

			case "leftTunnelTrapDecision1":
				switch(yourChoice){
				case "c1": leftTunnelTrapDecision2(); break;
				case "c2": leftTunnelDie(); break;
				}
				break;

			case "leftTunnelDie":
				switch(yourChoice){
				case "c1": leftTunnelTrap1(); break;
				}
				break;

			case "leftTunnelTrapDecision2":
				switch(yourChoice){
				case "c1": leftTunnelHiddenSwitch(); break;
				case "c2": leftTunnelDie(); break;
				case "c3": leftTunnelDie(); break;
				}
				break;

			case "leftTunnelHiddenSwitch":
				switch(yourChoice){
				case "c1": leftTunnelPressSwitch(); break;
				case "c2": leftTunnelDie(); break;
				case "c3": leftTunnelAnotherWay(); break;
				}
				break;

			case "leftTunnelPressSwitch":
				switch(yourChoice){
				case "c1": leftTunnelDoorOpen(); break;
				}
				break;

			case "leftTunnelDoorOpen":
				switch (yourChoice){
				case "c1": tunnelGenie(); break;
				}
				break;

			case "leftTunnelAnotherWay":
				switch(yourChoice){
				case "c1": leftTunnelCrawl(); break;
				case "c2": leftTunnelPressSwitch(); break;
				}
				break;

			case "leftTunnelCrawl":
				switch(yourChoice){
				case "c1": tunnelGenie(); break;
				}
				break;

			case "tunnelGeniePuzzle":
				System.out.println(checkPuzzleCompletion());
				switch (yourChoice){
				case "c1": trapSolve(); break;
				}
				break;

			case "tunnelGenie":
				switch (yourChoice){
				case "c1":
					GameRandomizer();
					tunnelGeniePuzzle();
					break;
				case "c2":
					GameRandomizer();
					tunnelGeniePuzzle();
					break;
				case "c3":
					GameRandomizer();
					tunnelGeniePuzzle();
					break;
				}
				break;

			case "leftTunnelRelic":
				switch (yourChoice){
				case "c1": ending(); break;
				}
				break;

			case "rightTunnel":
				switch (yourChoice){
				case "c1": rightTunnelStatue(); break;
				}
				break;

			case"righttunnelstatue":
				switch (yourChoice){
				case "c1": rightTunnelHitWall(); break;
				}
				break;

			case"righttunnelhitwall":
				switch (yourChoice){
				case "c1": rightTunnelKeepCool(); break;
				}
				break;

			case"righttunnelkeepcool":
				switch (yourChoice){
				case "c1": rightTunnelSwitch(); break;
				case "c2": rightTunnelDie(); break;
				case "c3": rightTunnelDie(); break;
				}
				break;
				
			case"righttunnelswitch":
				switch(yourChoice){
				case "c1": rightTunnelPressSwitch(); break;
				}
				break;

			case"righttunnelpressswitch":
				switch(yourChoice){
				case "c1": rightTunnelSafeAtLast(); break;
				}
				break;

			case"righttunnelsafeatlast":
				switch(yourChoice){
				case "c1": tunnelGenie(); break;
				}
				break;

			case"righttunneldie":
				switch(yourChoice){
				case "c1": rightTunnelKeepCool(); break;
				}
				break;

			case "codesolve":
				System.out.println(checkPuzzleCompletion());
				switch(yourChoice){
				case "c1": crossRoad(); break;
				}
				break;

			case "trapsolve":
				switch (yourChoice){
				case "c1":
					if (lastPuzzle == true)
						ending();
					else
						rightTunnel();
					break;
				}
				break;

			case "ending":
				switch (yourChoice){
				case "c1": restart();
				}
				break;

			case "restart":
				switch (yourChoice){
				case "c1":
					con.revalidate();
					con.repaint();
					puzzleSuccess = false;
					scramblerSuccess = false;
					knowledgeBank();
					break;
				case "c2":
					//close the program
					System.exit(0);
					break;
				}

			case "knowledge":
				switch(yourChoice){
				case"kc1":
					tigerKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case "kc2":
					foxKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case "kc3":
					monkeyKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case"kc4":
					parrotKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case"kc5":
					rabbitKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case "kc6":
					snakeKnowledge();
					con.revalidate();
					con.repaint();
					break;

				case "kr":
					wildAnimal();
					break;

				case "kBack":
					knowledgeBank();
					break;
				}
			}//endSwitch
		}//endActionPerformed
	}//endChoiceHandler

	public static void setPlayerScore(int score){
		playerScore += score;
	}

	public static int getPlayerScore(){
		return playerScore;
	}

}//endMain
