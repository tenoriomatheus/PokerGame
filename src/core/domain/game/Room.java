package core.domain.game;

import java.util.ArrayList;
import java.util.List;

import core.domain.game.MatchInfo;
import core.domain.game.Money;
import core.domain.game.PlayerInfo;

public class Room {

	private long _id;
	private List<PlayerInfo> _listOfPlayers;
	private MatchInfo _matchInfo;
	public final static int ROOM_CAPACITY = 6;
	
	public Room(long id, Money smallBlindValue, Money minimumBuyIn) {
		_id = id;
		_matchInfo = new MatchInfo(smallBlindValue, minimumBuyIn);
		_listOfPlayers = new ArrayList<PlayerInfo>(ROOM_CAPACITY);
	}
	
	public long getId() {
		return _id;
	}
	
	public void addPlayer(PlayerInfo player) throws Exception {
		if (_listOfPlayers.size() >= ROOM_CAPACITY)
			throw new Exception("The max capacity of the room has been reached!");
		_listOfPlayers.add(player);	
	}
	public void removePlayer(PlayerInfo player) {
		_listOfPlayers.remove(player);
	}
	public List<PlayerInfo> getPlayers() {
		return _listOfPlayers;
	}
	
	public void setMatchInfo(MatchInfo matchInfo) {
		_matchInfo = matchInfo;
	}
	public MatchInfo getMatchInfo() {
		return _matchInfo;
	}
}
