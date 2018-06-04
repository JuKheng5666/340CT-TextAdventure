import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class KnowledgeBank extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JPanel knowledgeTextPanel, knowledgeBtnPanel;
	JButton knowledgeBackBtn;
	JTextArea knowledgeText;
	String knowledgeDisplayTxt = "";
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	String userPosition = "";
	JLabel knowledgeBackground;
	
	public KnowledgeBank(String text){
		this.userPosition = text;
		//this.setLayout(null);
		knowledgeBackground = new JLabel();
		
		setDisplayText(text);
		knowledgeText = new JTextArea();
		knowledgeText.setLineWrap(true);
		knowledgeText.setWrapStyleWord(true);
		knowledgeText.setHighlighter(null);
		knowledgeText.setEditable(false);
		knowledgeText.setBounds(100, 100, 600, 250);
		knowledgeText.setBackground(new Color(0,0,0,30));
		knowledgeText.setForeground(Color.white);
		knowledgeText.setFont(normalFont);
		knowledgeText.getCaret().deinstall(knowledgeText);
		knowledgeText.setText(knowledgeDisplayTxt);
		
		knowledgeTextPanel = new JPanel();
		knowledgeTextPanel.setBounds(100, 100, 600, 250);
		knowledgeTextPanel.setBackground(new Color(0,0,0,0));
		knowledgeTextPanel.add(knowledgeText);
		
		knowledgeBackBtn = new JButton("Back");
		knowledgeBackBtn.setFont(normalFont);
		knowledgeBackBtn.setFocusPainted(false);
		knowledgeBackBtn.setActionCommand("c4");
		
		//have to change 
		knowledgeBackBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("CLOSE FRAMEEE");
			}
		});
		
		knowledgeBtnPanel = new JPanel();
		knowledgeBtnPanel.setBounds(250, 350, 300, 150);
		knowledgeBtnPanel.setBackground(new Color(0,0,0,30));
		knowledgeBtnPanel.add(knowledgeBackBtn);
	
		this.add(knowledgeBackground);
		//this.add(knowledgeTextPanel, BorderLayout.CENTER);
		//this.add(knowledgeBtnPanel, BorderLayout.SOUTH);
	
		System.out.println("Added elements");
	}
	
	public void setDisplayText(String userPosition){
		switch (userPosition){
		case "wildAnimal":
			knowledgeDisplayTxt = "wild animals";
			knowledgeBackground.setIcon(new ImageIcon(Main.class.getResource("/Images/jungle.jpg")));
			break;
		}
	}
}
