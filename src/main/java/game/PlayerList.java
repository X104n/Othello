package game;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerList implements Iterable<Player> {

	ArrayList<Player> players;
	int nextIndex;
	
	public PlayerList() {
		players = new ArrayList<Player>();
		nextIndex = 0;
	}
	
	public void add(Player p) {
		if(players.contains(p))
			throw new IllegalArgumentException("Can not have 2 players with same symbol in the same list.");
		players.add(p);
	}
	
	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}

	public Player getNextPlayer() {
		Player next = players.get(nextIndex);
		nextIndex++;
		if(nextIndex>=players.size())
			nextIndex=0;
		return next;
	}

}
