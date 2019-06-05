package chess;

/**
 * Styles of games that can be played
 * @author edurso
 */
public enum GameStyle {
	
	/**
	 * Single player game against an easy AI
	 */
	SINGLE(0),

	/**
	 * Multiplayer game on the local device
	 */
	MULTI_LOCAL(1),
	
	/**
	 * NOT YET SUPPORTED
	 * Multiplayer game on different computers
	 */
	MULTI_ONLINE(2),
	
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
	 * pre: none
	 * post: style created
	 * @param value
	 */
    GameStyle(int value) {
        this.value = value;
    }
    
}
