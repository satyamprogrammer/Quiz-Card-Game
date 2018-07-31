package QuizCardGame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UIManager;

import QuizCardGame.QuizCard;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardBuilder {
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	private ArrayList<QuizCard> cardList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuizCardBuilder qcb = new QuizCardBuilder();
		
		qcb.go();
	}

	private void go() {
		// TODO Auto-generated method stub
		frame = new JFrame("Create Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);

		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);

		JScrollPane qpane = new JScrollPane(question);
		qpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);

		JScrollPane apane = new JScrollPane(answer);
		apane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		apane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel quest = new JLabel("Question:");
		JLabel ans = new JLabel("Answer:");
		JButton next = new JButton("Next");
		
		cardList = new ArrayList<QuizCard>();

		mainpanel.add(quest);
		mainpanel.add(qpane);
		mainpanel.add(ans);
		mainpanel.add(apane);
		mainpanel.add(next);
		
		next.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem New = new JMenuItem("New");
		JMenuItem Save = new JMenuItem("Save");
		New.addActionListener(new NewListener());
		Save.addActionListener(new SaveListener());
		menu.add(New);
		menu.add(Save);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		

		frame.getContentPane().add(BorderLayout.CENTER, mainpanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}

	class NewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			// TODO Auto-generated method stub
			cardList.clear();
			clearCard();

		}

	}

	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);

			JFileChooser filesave = new JFileChooser();
			filesave.showSaveDialog(frame);
			saveFile(filesave.getSelectedFile());

		}

	}

	class NextCardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}

	}

	void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}

	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("couldn't write the cardList");
			e.printStackTrace();
		}
	}

}
