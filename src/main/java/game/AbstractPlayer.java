package game;

public abstract class AbstractPlayer implements Player {

	private char symbol;
	private String name;
	
	public AbstractPlayer(char symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}
	
	@Override
	public char getSymbol() {
		return symbol;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbstractPlayer) {
			AbstractPlayer player = (AbstractPlayer) obj;
			return this.symbol == player.symbol;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}
}
