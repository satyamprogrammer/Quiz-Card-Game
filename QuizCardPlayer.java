package QuizCardGame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardPlayer {
	private JFrame frame;
	private JPanel mainpanel;
	private JTextArea textarea;
	private JButton button;
	private ArrayList<QuizCard> cardList;
	private boolean isShowAnswer;
	private QuizCard currentCard;
	private int currentCardIndex;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuizCardPlayer qcp = new QuizCardPlayer();
		qcp.go();
	}

	private void go() {
		// TODO Auto-generated method stub
		frame = new JFrame("Quiz Card Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		mainpanel = new JPanel();
		
		textarea = new JTextArea(10,20);
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		textarea.setFont(bigFont);
		
		JScrollPane scrollPane = new JScrollPane(textarea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		button = new JButton("Show Question");
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem loadCardSet = new JMenuItem("Load Card Set");
		loadCardSet.addActionListener(new lCardSet());
		menu.add(loadCardSet);
		menubar.add(menu);
		
		mainpanel.add(scrollPane);
		mainpanel.add(button);
		button.addActionListener(new ButtonActionListener());
		
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(BorderLayout.CENTER,mainpanel);;
		frame.setSize(500, 600);
		frame.setVisible(true);

	}
	
	class lCardSet implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JFileChooser filesave = new JFileChooser();
			filesave.showOpenDialog(frame);
			LoadFile(filesave.getSelectedFile());
		}
		
	}
	//Load File
	private void LoadFile(File file)
	{
		cardList = new ArrayList<QuizCard>();
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(file)); 
			String line = null;
			while((line = reader.readLine())!=null)
			{
			MakeCard(line);
			}
			reader.close();
		}
		
	catch(Exception e)
		{
		e.printStackTrace();
		}
		
	}
	private void MakeCard(String line)
	{
		String[] result = line.split("/");
		QuizCard card = new QuizCard(result[0],result[1]);
		cardList.add(card);
				
			}
	
	//Button ActionListener
	class ButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(isShowAnswer)
			{
				
				textarea.setText(currentCard.getAnswer());
				button.setText("Next Card");
				isShowAnswer = false;				
			}
			else
			{
				if(currentCardIndex < cardList.size())
				{
					showNextCard();
				}
				else
				{
					textarea.setText("No more Cards");
					button.setEnabled(false);
				}
			}
		}
		
	}
	
	private void showNextCard() {
		// TODO Auto-generated method stub
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		textarea.setText(currentCard.getQuestion());
		button.setText("Show Answer");
		isShowAnswer = true;
	}
}
