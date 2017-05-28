package javathreads.examples.ch02.example2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingTypeTester extends JFrame implements CharacterSource{

	protected RandomCharacterGenerator producer;

	private CharacterDisplayCanvas displayCanvas;

	private CharacterDisplayCanvas feedbackCanvas;

	private JButton quitButton;

	private JButton startButton;

	private CharacterEventHandler handler;

	public SwingTypeTester( ) {
		initComponents( );
	}

	private void initComponents( ) {

		handler = new CharacterEventHandler( );

		displayCanvas = new CharacterDisplayCanvas( );

		feedbackCanvas = new CharacterDisplayCanvas(this);

		quitButton = new JButton( );

		startButton = new JButton( );

		add(displayCanvas, BorderLayout.NORTH);

		add(feedbackCanvas, BorderLayout.CENTER);

		JPanel p = new JPanel( );

		startButton.setLabel("Start");

		quitButton.setLabel("Quit");

		p.add(startButton);

		p.add(quitButton);

		add(p, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter( ) {
			public void windowClosing(WindowEvent evt) {
				quit();
			}
		});

		feedbackCanvas.addKeyListener(new KeyAdapter( ) {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar( );
				if (c != KeyEvent.CHAR_UNDEFINED)
					newCharacter((int) c);
			}
		});

		startButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent evt) {
				producer = new RandomCharacterGenerator( );
				displayCanvas.setCharacterSource(producer);
				producer.start( );
				startButton.setEnabled(false);
				feedbackCanvas.setEnabled(true);
				feedbackCanvas.requestFocus( );
			}
		});
		quitButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent evt) {
				quit( );
			}
		});
		pack( );
	}
	private void quit( ) {
		System.exit(0);
	}
	@Override
	public void addCharacterListener(CharacterListener cl) {
		// TODO Auto-generated method stub
		handler.addCharacterListener(cl);
	}

	@Override
	public void removeCharacterListener(CharacterListener cl) {
		// TODO Auto-generated method stub
		handler.removeCharacterListener(cl);
	}

	public void newCharacter(int c) {
		handler.fireNewCharacter(this, c);
	}
	@Override
	public void nextCharacter() {
		// TODO Auto-generated method stub
		throw new IllegalStateException("We don't produce on demand");
	}

	public static void main(String args[]) {
		new SwingTypeTester().show();
	}
}
