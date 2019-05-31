package chess;

/**
 * Styles of games that can be played
 * @author edurso
 */
public enum GameStyle {
	
	/**
	 * Single player game against an easy AI
	 */
	SINGLE_EASY(0),
	
	/**
	 * Single player game against a moderately difficult AI
	 */
	SINGLE_MED(1),
	
	/**
	 * Single player game against a hard AI
	 */
	SINGLE_HARD(2),
	
	/**
	 * Multiplayer game on the local device
	 */
	MULTI_LOCAL(3),
	
	/**
	 * NOT YET SUPPORTED
	 * Multiplayer game on different computers
	 */
	MULTI_ONLINE(4),
	
	/**
	 * Error, if no other game mode specified
	 */
	UNKNOWN(-1);
	
	/**
	 * integer value of mode
	 */
	private final int value;   

	/**
	 * creates style with {@code value}
	 * @param value
	 */
    GameStyle(int value) {
        this.value = value;
    }
    
}
