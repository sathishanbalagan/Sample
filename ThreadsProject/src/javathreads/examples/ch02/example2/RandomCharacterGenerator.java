package javathreads.examples.ch02.example2;

import java.util.Random;

public class RandomCharacterGenerator extends Thread implements CharacterSource{

	static char[] chars;

	static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";

	static {
		chars = charArray.toCharArray( );
	}

	Random random;

	CharacterEventHandler handler;

	public RandomCharacterGenerator( ) {
		random = new Random( );
		handler = new CharacterEventHandler( );
	}

	public int getPauseTime( ) {
		return (int) (Math.max(1000, 1000 * random.nextDouble( )));
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

	@Override
	public void nextCharacter() {
		// TODO Auto-generated method stub
		handler.fireNewCharacter(this,(int) chars[random.nextInt(chars.length)]);
	}

	public void run( ) {
		for (;;) {
			nextCharacter( );
			try {
				Thread.sleep(getPauseTime( ));
			} catch (InterruptedException ie) {
				return;
			}
		}
	}

}
