package chess;
public enum GameStyle {
	
	//SINGLEPLAYER
	SINGLE_EASY(0),
	SINGLE_MED(1),
	SINGLE_HARD(2),
	
	//MULTIPLAYER
	MULTI_LOCAL(3),
	MULTI_ONLINE(4), //TODO - implement???
	
	UNKNOWN(-1);
	
	private final int value;   

    GameStyle(int value) {
        this.value = value;
    }
    
}
