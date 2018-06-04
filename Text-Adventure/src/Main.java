import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

	//main text adventure
	JFrame window, knowledgeWindow;
	Container con, con2;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
	JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, background, knowledgeBackground;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, choice1, choice2, choice3, choice4;
	JTextArea mainTextArea;

	int playerHP, monsterHP, silverRing;
	volatile 
	boolean puzzleSuccess = false;
	volatile boolean scramblerSuccess;
	boolean lastPuzzle = false;

	String weapon, position, prevPosition, knowledgeDisplayTxt;
	Color semiTransparent = new Color(0,0,0,30);

	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();

	PuzzleGame.Main puzzleGame =  new PuzzleGame.Main();
	WordScrambler.UIMain wordScrambler = new WordScrambler.UIMain();

	public static void main(String[] args) {
		//run the default main
		new Main();
	}

	public Main(){

		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		background = new JLabel(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
		window.setContentPane(background);

		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		//window.getContentPane().setBackground(Color.black);
		//window.setLayout(null);

		con = window.getContentPane();

		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(semiTransparent);
		titleNameLabel = new JLabel("Science Exploration");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);	

		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(new Color(0,0,0,0));

		startButton = new JButton("Begin");
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);

		con.add(titleNamePanel);
		con.add(startButtonPanel);

		//The display is set to true so player can see the title and begin button
		window.setVisible(true);
	}

	//set up the game screen 
	public void createGameScreen(){

		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(semiTransparent);
		con.add(mainTextPanel);

		mainTextArea = new JTextArea("This is the main text are. This game is going to be great. I'm sure of it!!!!!!!");
		mainTextArea.setHighlighter(null);
		mainTextArea.setEditable(false);
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextArea.setBackground(semiTransparent);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setWrapStyleWord(true);
		mainTextArea.getCaret().deinstall(mainTextArea);

		mainTextPanel.add(mainTextArea);

		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 350, 300, 150);
		choiceButtonPanel.setBackground(semiTransparent);
		choiceButtonPanel.setLayout(new GridLayout(4,1));
		con.add(choiceButtonPanel);

		//Design button one - Choice 1
		choice1 = new JButton("Choice 1");
		//choice1.setBackground(Color.black);
		//choice1.setForeground(Color.white);
		choice1.setFont(normalFont);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");
		choiceButtonPanel.add(choice1);

		//Design button two -Choice 2
		choice2 = new JButton("Choice 2");
		//choice2.setBackground(Color.black);
		//choice2.setForeground(Color.white);
		choice2.setFont(normalFont);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");
		choiceButtonPanel.add(choice2);

		//Design button three -Choice 3
		choice3 = new JButton("Choice 3");
		//choice3.setBackground(Color.black);
		//choice3.setForeground(Color.white);
		choice3.setFont(normalFont);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");
		choiceButtonPanel.add(choice3);

		//Design button four -Choice 4
		choice4 = new JButton("Choice 4");
		//choice4.setBackground(Color.black);
		//choice4.setForeground(Color.white);
		choice4.setFont(normalFont);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");
		choiceButtonPanel.add(choice4);

		//Design the screen background
		playerPanel = new JPanel();
		playerPanel.setBounds(100, 15, 600, 50);
		playerPanel.setBackground(semiTransparent);
		playerPanel.setLayout(new GridLayout(1,4));
		con.add(playerPanel);

		//Design the Power label display on screen 
		hpLabel = new JLabel("HP:");
		hpLabel.setFont(normalFont);
		hpLabel.setForeground(Color.white);
		playerPanel.add(hpLabel);

		//Design the Power count display on screen 
		hpLabelNumber = new JLabel();
		hpLabelNumber.setFont(normalFont);
		hpLabelNumber.setForeground(Color.white);
		playerPanel.add(hpLabelNumber);

		//Design the Weapon label display on screen
		weaponLabel = new JLabel("Weapon:");
		weaponLabel.setFont(normalFont);
		weaponLabel.setForeground(Color.white);
		playerPanel.add(weaponLabel);

		//Design the Weapon type display on screen
		weaponLabelName = new JLabel();
		weaponLabelName.setFont(normalFont);
		weaponLabelName.setForeground(Color.white);
		playerPanel.add(weaponLabelName);

		//go to playerSetup
		playerSetup();

	}

	public void playerSetup(){

		//initialize variables
		playerHP = 15;
		monsterHP = 20;
		weapon = "Knife";
		weaponLabelName.setText(weapon);
		hpLabelNumber.setText("" + playerHP);

		//go to townGate
		//itemDamage();
		wildAnimal();
	}

	public void itemDamage(){
		position="itemDamage";

		mainTextPanel.removeAll();
		mainTextArea.setText("You have a treasure map that leads to ancient relic. \nYou are now at the entrance of amazon jungle \n\nUnfortunately your bag damage.");		
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();
	}
	
	public void knowledgeBank(){
		//position = "knowledge";
		
		knowledgeWindow = new JFrame("Science Exploration Knowledge Bank");
		knowledgeWindow.setSize(800, 600);
		//Dimension preferSize = new Dimension(800, 600);
				
		//knowledgeBackground = new JLabel(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
		//knowledgeWindow.setContentPane(knowledgeBackground);
		
		//knowledgeWindow.setPreferredSize(preferSize);
		knowledgeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		knowledgeWindow.setResizable(true);
		knowledgeWindow.setLocationRelativeTo(null);
		
		JPanel mainPanel = new KnowledgeBank(position);
		knowledgeWindow.getContentPane().add(mainPanel);
		
		System.out.println("AHHHHHH");
		
		knowledgeWindow.revalidate();
		knowledgeWindow.repaint();
		knowledgeWindow.pack();
		
		System.out.println("ROCK AND ROLL");
		
		knowledgeWindow.setVisible(true);		
	}

	public void wildAnimal(){
		position="wildAnimal";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You have a treasure map that leads to ancient relic. \nYou are now at the entrance of amazon jungle \n\nYou heard a nearby bush rustle.\nYou panic and you start to run.\n\n Which wild animal you think it will be?");		
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Snake");
		choice2.setText("Tiger");
		choice3.setText("Monkey");
		choice4.setText("*Knowledge Bank*");
	}

	public void Monkey(){
		position="monkey";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a monkey. Solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); // puzzle console
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public void Snake(){
		position= "snake";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a snake.Click next to solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">");// puzzle console
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public void Tiger(){
		position="tiger";

		mainTextPanel.removeAll();
		mainTextArea.setText("Your guess is a tiger.Click next to solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">");// puzzle console
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}


	public void crossRoad(){
		position = "crossRoad";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/You are lost.jpg")));
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You take a look behind you.\n\n Sadly, it was just a wild rabbit and you ran away like a little girl.\n\nYou are now lost.\nChoose where you want to go");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Go north");
		choice2.setText("Go east");
		choice3.setText("Go west");
	}

	//choice:Go north 
	//will come to north method
	//
	public void north(){
		position = "north";

		mainTextPanel.removeAll();
		mainTextArea.setText("Somehow you end up at the entrance of the jungle again.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Continue on");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	//choice:Go east
	//will come to east method
	public void east(){
		position = "east";

		mainTextPanel.removeAll();
		mainTextArea.setText("There is nothing here. Go back");
		mainTextPanel.add(mainTextArea);
		/*
		weapon = "Long Sword";
		weaponLabelName.setText(weapon);
		 */

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Go back to the crossroad");
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");

	}

	//choice:Go west
	//will come to west method
	public void west(){
		position = "west";

		mainTextPanel.removeAll();
		mainTextArea.setText("As you walk, a monkey jump to you and snatch your hat.\n\nWhat you going to do?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Just let it be");
		choice2.setText("Chase the monkey");
		choice3.setText("");
		choice4.setText("");
	}


	public void chaseMonkey(){
		position ="chasemonkey";

		mainTextPanel.removeAll();
		mainTextArea.setText("The monkey ran away.\n\n You gave up chasing it.\n\n You are thinking what to do next.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Find some fruits to eat."); //Another puzzle game
		choice2.setText("Find a way out"); //discover secret code that may helpful later on
		choice3.setText("Just sit and relax");// wake up and player is in the temple
		choice4.setText("");
	}

	public void ruinTemple(){
		position = "ruintemple";

		mainTextPanel.removeAll();
		mainTextArea.setText("As you are thinking a way out.\n You discover a ruin temple.\n\n So you take your chance to discover more.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Discover the temple");//will get secret code
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public void findFruit(){
		position="findfruit";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Fruits garden.jpg")));
		
		mainTextPanel.removeAll();
		mainTextArea.setText("After a long time, you feel so thirsty due to the hot weather. \nYou are hungry and exhausted.\n\nYou trip and fall onto a stone accidentally.\nYou found a combitnation set of numbers on the stone.");//will discover secret code
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Solve the code");//will get secret code// here is the scramble puzzle take place
		choice2.setText("Walk away");// will end up aWayOut
		choice3.setText("");
		choice4.setText("");

	}

	public void aWayOut(){
		position="awayout";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Parrot.jpg")));
		
		mainTextPanel.removeAll();
		mainTextArea.setText("A parrot sang a song.\n\nYou have to guess the title of the song in order to get the secret code.");//will discover secret code
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Accept its challenge.");//will get secret code// here is the scramble puzzle take place
		choice2.setText("Walk away");// will end up in the temple
		choice3.setText("");
		choice4.setText("");
	}

	public void sitAndRelax(){
		position="sitandrelax";//end up in temple

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/Sit down and rest.jpg")));
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You sat and relax.\n\n Something hit you and you passed out.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">");// wakeUp()
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public void wakeUp(){
		position="wakeup";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/jungletemple.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("You found yourself in a ruined temple mysteriously after you woke up.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">");// discovertemple()
		choice2.setText("");
		choice3.setText("");
		choice4.setText("");
	}

	public void discoverTemple(){
		position ="discovertemple";

		background.setIcon(new ImageIcon(Main.class.getResource("/Images/temple.jpg")));

		mainTextPanel.removeAll();
		mainTextArea.setText("You look around and there is two tunnels.Which tunnel should you go?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Left tunnel"); //Step on floor and arrows comes out 
		choice2.setText("Right tunnel"); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	//left tunnel scenarios
	public void leftTunnelConfirm(){
		position = "leftTunnelConfirm";

		mainTextPanel.removeAll();
		mainTextArea.setText("I hope you won't regret your choice. \nYou choose to be in the left tunnel. \n\nDo you want to change your decicion?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Yes"); //Step on floor and arrows comes out 
		choice2.setText("No"); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	public void leftTunnelGoodLuck(){
		//Step on the floor and arrows come out
		//Left and Right point counts (maybe later)
		position ="leftTunnelGoodLuck";

		mainTextPanel.removeAll();
		mainTextArea.setText("Good luck, I hope you will make your way out the temple alive.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Step on floor and arrows comes out 
		choice2.setText(""); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	public void leftTunnel(){
		position = "leftTunnel";

		mainTextPanel.removeAll();
		mainTextArea.setText("You walk your way through a dark and scary temple. \nThere aren't any signs of living in the tunnel and you can barely see the path ahead.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	public void leftTunnelTrap1(){
		position = "leftTunnelTrap1";

		mainTextPanel.removeAll();
		mainTextArea.setText("Suddenly, you take a wrong step and you heard 'click-clark!'. Part of the floor starts to sink.\n\n You start to feel cold sweat running down your face. \nYour expression went wild when arrows flew toward your direction.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	public void leftTunnelTrapDecision1(){
		position = "leftTunnelTrapDecision1";

		mainTextPanel.removeAll();
		mainTextArea.setText("You should: ");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Bend down"); 
		choice2.setText("Stand still"); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelTrapDecision2(){
		position = "leftTunnelTrapDecision2";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("The arrows missed you. \nBut you already feel dead inside knowing that there will be more arrows to come. \n\nA few moments later, two more arrows flew out towards your left. \nYou should: ");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Bend to your right"); 
		choice2.setText("Bend to your left"); 
		choice3.setText("Stand still");
		choice4.setText("");
	}
	
	public void leftTunnelHiddenSwitch(){
		position = "leftTunnelHiddenSwitch";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("The arrows missed you once again. Good job! \n\n As you turn to the path ahead, you caught a glimpse of a hidden switch between two pillars. \n\nWhat do you do?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Run to the switch"); 
		choice2.setText("Ignore and try to escape"); 
		choice3.setText("Look for another way");
		choice4.setText("");
	}

	public void leftTunnelPressSwitch(){
		position = "leftTunnelPressSwitch";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You run towards the switch as fast as you can. \n\nWith hopes that the switch does not trigger additional traps, you pressed the switch.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelDoorOpen(){
		position = "leftTunnelDoorOpen";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("The sound of whirring trap mechanisms stop. A door at the end of the tunnel opens.\n\n You go through the door and found yourself in a mysterious room.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelAnotherWay(){
		position = "leftTunnelAnotherWay";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You hide behind a pile of rubble and as you look around, you see a small passage across you. \n\nCrawl through the passageway?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Yes"); 
		choice2.setText("No"); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelCrawl(){
		position = "leftTunnelCrawl";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You run towards the passageway and start crawling on all fours into the dark.\n\n Few minutes later, you found yourself at a mysterious room.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelGenie(){
		position = "leftTunnelGenie";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("Feeling relieved that the trap was over, you look around the room when a mysterious genie suddenly appears and asks you to solve his question in return for a code to the ancient relic.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Accept the challenge"); 
		choice2.setText("It is now or never"); 
		choice3.setText("Try walking away");
		choice4.setText("");
	}
	
	public void leftTunnelGeniePuzzle(){
		position = "leftTunnelGeniePuzzle";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("The genie moves closer to you with great desperation in its eyes.\n\nYou could not ignore his pleas and decided to solve his question.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelRelic(){
		position = "leftTunnelRelic";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("You make a long series of rights and lefts as instructed from the genie's code. \n\n Just as you were beginning to have doubts...");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void leftTunnelDie(){
		position = "leftTunnelDie";

		mainTextPanel.removeAll();
		mainTextArea.setText("You didn't manage to dodge the trap and died. Go back and make your choice again.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); 
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void rightTunnel(){
		position ="rightTunnel";

		mainTextPanel.removeAll();
		mainTextArea.setText("You walk your way through a dark and scary temple.\n There aren't any livings in the tunnel and you can't barely see the path.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void rightTunnelStatue(){
		/**/
		
		position ="righttunnelstatue";
		mainTextPanel.removeAll();
		mainTextArea.setText("You decide to take a rest on a statue and there the statue head bend to a side, the wall surrounding you start to move.\n\n You start to scream for help\nThen you realize the walls have been narrow down.");
		mainTextPanel.add(mainTextArea);
		
		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");	
		
	}
	
	public void rightTunnelHitWall(){
		
		position ="righttunnelhitwall";
		mainTextPanel.removeAll();
		mainTextArea.setText("You try to hit the wall and hurt your hands.\n\n Out of nowhere, the sand start to fall from above.\n Is hard to tell how the sand start to come in, but you will skip that part and try to save yourself.");
		mainTextPanel.add(mainTextArea);
		
		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Proceed story
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
		
	}
	
	public void rightTunnelKeepCool(){
		//righttunnelkeepcool
		
		position ="righttunnelkeepcool";
		mainTextPanel.removeAll();
		mainTextArea.setText("You keep your cool and try to find a way out. ");
		mainTextPanel.add(mainTextArea);
		
		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();
		
		choice1.setText("Try to feel the walls to see whether is there any switch"); //rightTunnelSwitch()
		choice2.setText("Continue hit the walls"); //rightTunnelCHitWall()
		choice3.setText("Shout for help ");//rightTunnelShout()
		choice4.setText("");
	}
	
	public void rightTunnelSwitch(){
		
		//You feel the switch and you press the switch with all your strength.
		
		position = "righttunnelswitch";

		mainTextPanel.removeAll();
		mainTextArea.setText("You feel the switch and you press the switch with all your strength.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void rightTunnelPressSwitch(){
		//The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.
		position = "righttunnelpressswitch";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
		
	}
	
	public void rightTunnelCHitWall(){
		position = "righttunnelchitwall";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("Eventually that doesn't help.You die in the game. Choose your choice again.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue back to the tunnelkeepcool
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	public void rightTunnelShout(){
		position = "righttunnelshout";
		
		mainTextPanel.removeAll();
		mainTextArea.setText("Eventually that doesn't help.You die in the game. Choose your choice again.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Continue back to the tunnelkeepcool
		choice2.setText(""); 
		choice3.setText("");
		choice4.setText("");
	}
	
	
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
		choice4.setText("");
	}
	
	public void rightTunnelGoodLuck(){
		//Step on the floor and arrows come out
		//Left and Right point counts (maybe later)
		position ="rightTunnelGoodLuck";

		mainTextPanel.removeAll();
		mainTextArea.setText("Good luck, I hope you will make your way out the temple alive.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //
		choice2.setText(""); //To be continue 
		choice3.setText("");
		choice4.setText("");
	}

	public void rightTunnelSafeAtLast(){
		position ="righttunnelsafeatlast";

		mainTextPanel.removeAll();
		mainTextArea.setText("The walls goes back to normal and the sand stop coming down from nowhere.\n\n You cough and try to catch your breath before you continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //Go to genie
		choice2.setText("");  
		choice3.setText("");
		choice4.setText("");
	}
	
	public void codeSolve(){
		position="codesolve";

		mainTextPanel.removeAll();
		mainTextArea.setText("You obtained the secret numbers.\n\nRemember the number code.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //For findFruit() and awayout - Accept challenge will have different outcomes
		choice2.setText("");  
		choice3.setText("");
		choice4.setText("");
	}
	
	public void askToSolve(){
		prevPosition = position;
		position="askToSolve";

		mainTextPanel.removeAll();
		mainTextArea.setText("Solve a puzzle to continue your journey.");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">");
		choice2.setText("");  
		choice3.setText("");
		choice4.setText("");
	}

	public void trapSolve(){
		position="trapsolve";
		lastPuzzle = true;
		
		mainTextPanel.removeAll();
		mainTextArea.setText("Congratulations, you solved the puzzle game.\n");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText(">"); //For findFruit() and awayout - Accept challenge will have different outcomes
		choice2.setText("");  
		choice3.setText("");
		choice4.setText("");
	}

	//story end
	public void ending(){
		position = "ending";

		mainTextPanel.removeAll();
		mainTextArea.setText("You arrive at the heart of the ruined temple. \nYou found the ancient relic.\nYay! You did it!");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("THE END");
		choice2.setText("");  
		choice3.setText("");
		choice4.setText("");
	}

	public void restart(){
		position = "restart";

		mainTextPanel.removeAll();
		mainTextArea.setText("Congratulations on completing the game. \n\n Do you want to restart?");
		mainTextPanel.add(mainTextArea);

		//usually do revalidate together with repaint
		//when there is a change in UI components
		//revalidate used on the HIGHEST AFFECTED COMPONENT
		con.revalidate();
		con.repaint();

		choice1.setText("Restart");
		choice2.setText("End");  
		choice3.setText("");
		choice4.setText("");
	}

	public void GameRandomizer() {
		puzzleSuccess = false;
		scramblerSuccess = false;
		
		Random rand = new Random();
		int seed = rand.nextInt(2);
		//int seed = 1;
		
		switch (seed) {
		case 0:
			puzzleSuccess = PuzzleGame.Main.main(puzzleSuccess);
			break;
		case 1:
			scramblerSuccess = WordScrambler.UIMain.main(scramblerSuccess);
			break;
		}
	}

	//handle the screen 
	//createGameScreen method is called
	public class TitleScreenHandler implements ActionListener{

		public void actionPerformed(ActionEvent event){

			createGameScreen();
		}
	}

	//the choice handle handle the choice using use case 

	public class ChoiceHandler implements ActionListener{
		String userProgress = "";

		public void actionPerformed(ActionEvent event){

			String yourChoice = event.getActionCommand();

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
					Snake();
					//switch(yourChoice){case "c1":townGate();}
					break;

				case "c2":
					Tiger();
					//switch(yourChoice){case "c1":townGate();}
					break;

				case "c3":
					Monkey();
					//switch(yourChoice){case "c1":townGate();}
					break;

				case "c4":
					knowledgeBank();
					break;
				}
				break;
				
			case "snake":
				switch(yourChoice){
				case "c1": 
					GameRandomizer();
					//window.setVisible(false);
					crossRoad();
					/*
					if (puzzleSuccess == true || scramblerSuccess == true){
						puzzleSuccess = false;
						scramblerSuccess = false;
						crossRoad(); 
					}
					else {
						System.out.println("You failed!");
						crossRoad();	//change
					}*/
					
					break;
				}
				break;

			case "tiger":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					crossRoad();
					/*
					if (puzzleSuccess == true || scramblerSuccess == true){
						puzzleSuccess = false;
						scramblerSuccess = false;
						crossRoad(); 
					}
					else {
						System.out.println("You failed!");
						crossRoad();	//change
					}*/ 
					
					break;
				}
				break;

			case "monkey":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					crossRoad();
					
					/*
					if (puzzleSuccess == true || scramblerSuccess == true){
						puzzleSuccess = false;
						scramblerSuccess = false;
						crossRoad(); 
					}
					else {
						System.out.println("You failed!");
						crossRoad();	//change
					}*/
					
					break;
				}
				break;

			case "chasemonkey":
				switch(yourChoice){
				case "c1":
					findFruit();
					break;

				case "c2":
					aWayOut();
					break;

				case "c3":
					sitAndRelax();
					break;
				}
				break; 

			case "askToSolve":
				switch(yourChoice){
				case "c1":
					System.out.println(puzzleSuccess);
					System.out.println(scramblerSuccess);
					
					if (puzzleSuccess || scramblerSuccess){
						//reset puzzle state
						puzzleSuccess = false;
						scramblerSuccess = false;
						
						codeSolve();
					}
					break;
				}
				break;
				
			case "findfruit":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					askToSolve();
					break;

				case "c2":
					aWayOut();
					break;

				}

				break;

			case "awayout":
				switch(yourChoice){
				case "c1":
					GameRandomizer();
					askToSolve();
					codeSolve();
					break;

				case "c2":
					sitAndRelax();
					break;

				}

				break;

			case "sitandrelax":
				switch(yourChoice){
				case "c1":
					wakeUp();
					break;

				}

				break;	


			case "wakeup":
				switch(yourChoice){
				case "c1":
					discoverTemple();
					break;
				}

				break;	
				//here	

			case "ruintemple":
				switch(yourChoice){
				case "c1":
					discoverTemple();
					break;
				}

				break;

			case "discovertemple":
				switch(yourChoice){
				case "c1":
					leftTunnelConfirm();
					break;

				case "c2":
					rightTunnelConfirm();
					break;
				}

				break;	

			case "leftTunnelConfirm":
				switch (yourChoice){
				case "c1":
					discoverTemple();
					break;

				case "c2":
					leftTunnelGoodLuck();
					break;
				}
				break;

			case "leftTunnelGoodLuck":
				switch (yourChoice){
				case "c1":
					leftTunnel();
					break;
				}
				break;

			case "rightTunnelGoodLuck":
				switch (yourChoice){
				case "c1":
					rightTunnel();
					break;
				}
				break;

			case "rightTunnelConfirm":
				switch (yourChoice){
				case "c1":
					discoverTemple();
					break;

				case "c2":
					rightTunnelGoodLuck();
					break;
				}
				break;

			case "leftTunnel":
				switch (yourChoice){
				case "c1":
					leftTunnelTrap1();
					break;
				}
				break;
				
			case "leftTunnelTrap1":
				switch (yourChoice){
				case "c1":
					leftTunnelTrapDecision1();
					break;
				}
				break;
				
			case "leftTunnelTrapDecision1":
				switch(yourChoice){
				case "c1":
					leftTunnelTrapDecision2();
					break;
				case "c2":
					leftTunnelDie();
					break;
				}
				break;
				
			case "leftTunnelDie":
				switch(yourChoice){
				case "c1":
					leftTunnelTrap1();
					break;
				}
				break;
				
			case "leftTunnelTrapDecision2":
				switch(yourChoice){
				case "c1":
					leftTunnelHiddenSwitch();
					break;
				case "c2":
					leftTunnelDie();
					break;
				case "c3":
					leftTunnelDie();
					break;
				}
				break;
				
			case "leftTunnelHiddenSwitch":
				switch(yourChoice){
				case "c1":
					leftTunnelPressSwitch();
					break;
				case "c2":
					leftTunnelDie();
					break;
				case "c3":
					leftTunnelAnotherWay();
					break;
				}
				break;
				
			case "leftTunnelPressSwitch":
				switch(yourChoice){
				case "c1":
					leftTunnelDoorOpen();
					break;
				}
				break;
				
			case "leftTunnelDoorOpen":
				switch (yourChoice){
				case "c1":
					leftTunnelGenie();
					break;
				}
				break;
				
			case "leftTunnelAnotherWay":
				switch(yourChoice){
				case "c1":
					leftTunnelGenie();
					break;
				case "c2":
					leftTunnelPressSwitch();
					break;
				}
				break;
				
			case "leftTunnelGeniePuzzle":
				switch (yourChoice){
				case "c1":
					trapSolve();
					break;
				}
				break;
				
			case "leftTunnelGenie":
				switch (yourChoice){
				case "c1":
					GameRandomizer();
					leftTunnelGeniePuzzle();
					break;
				case "c2":
					GameRandomizer();
					leftTunnelGeniePuzzle();
					break;
				case "c3":
					GameRandomizer();
					leftTunnelGeniePuzzle();
					break;
				}
				break;
				
			case "leftTunnelRelic":
				switch (yourChoice){
				case "c1":
					ending();
					break;
				}
				break;
				
			case "rightTunnel":
				switch (yourChoice){
				case "c1":
					rightTunnelStatue();
					break;
				}
				break;
				
			case"righttunnelstatue":
				switch (yourChoice){
				case "c1":
					rightTunnelHitWall();
					break;
				}
				break;
				
			//righttunnelhitwall
				
			case"righttunnelhitwall":
				switch (yourChoice){
				case "c1":
					rightTunnelKeepCool();
					break;
				}
				break;
				
			case"righttunnelkeepcool":
				switch (yourChoice){
				case "c1":
					rightTunnelSwitch();
					break;
				
				case "c2":
					rightTunnelCHitWall();
					break;
					
				case "c3":
					rightTunnelShout();
					break;
					
				}
				break;
			case"righttunnelswitch":
				
				switch(yourChoice){
				case "c1":
					rightTunnelPressSwitch();
					break;
				}
				
				break;
				
			case"righttunnelpressswitch":
				
				switch(yourChoice){
				case "c1":
					rightTunnelSafeAtLast();
					break;
				}
				break;
			case"righttunnelsafeatlast":
				
				switch(yourChoice){
				case "c1":
					leftTunnelGenie();
					break;
				}
				break;
				
				
			case"righttunnelchitwall":
				switch(yourChoice){
				case "c1":
					rightTunnelKeepCool();
					break;
				}
				
				break;
				
			case"righttunnelshout":
				
				switch(yourChoice){
				case "c1":
					rightTunnelKeepCool();
					break;
				}
				
				break;

			case "codesolve":
				switch(yourChoice){
				case "c1":
					switch(prevPosition){
					case "findfruit":
						crossRoad();
						break;
					case "":
					}
					
					break;
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
				case "c1":
					restart();
				}
				break;

			case "restart":
				switch (yourChoice){
				case "c1":
					con.revalidate();
					con.repaint();
					puzzleSuccess = false;
					scramblerSuccess = false;
					wildAnimal();
					break;
				case "c2":
					//close the program
					System.exit(0);
					break;
				}
			}//endSwitch
		}//endActionPerformed
	}//endChoiceHandler
}//endMain
