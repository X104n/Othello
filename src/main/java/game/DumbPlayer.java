package game;

public class DumbPlayer implements Player {

	char symbol;
	
	public DumbPlayer(char symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public char getSymbol() {
		return symbol;
	}

}
