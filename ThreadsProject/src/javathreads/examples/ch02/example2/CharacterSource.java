package javathreads.examples.ch02.example2;

public interface CharacterSource {

	public void addCharacterListener(CharacterListener cl);
	
	public void removeCharacterListener(CharacterListener cl);
	
	public void nextCharacter( );

}