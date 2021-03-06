package core.ui.graphic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import core.domain.action.EnterRoomAction;
import core.domain.game.Money;
import core.domain.game.PlayerInfo;
import core.domain.game.PlayerStats;
import core.domain.game.Room;
import core.net.ServerConnection;
import core.ui.graphic.basics.Label;
import core.ui.graphic.basics.PopUp;
import core.ui.graphic.basics.TextStyle;
import core.ui.graphic.screen.MatchScreen;
import core.ui.input.MoneyInput;

public class EnterRoomPopUp extends PopUp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 824213959521958735L;
	
	private Label _message;
	private MoneyInput _moneyInput;
	private Room _room;
	private MessagePopUp _messagePopUp;
	private EnterRoomAction _enterRoomAction;
	
	public EnterRoomPopUp(JFrame owner, String title, PlayerInfo playerInfo) {
		super(owner, title);
		
		_message = new Label(new Point(0, 0), "Enter with the BuyIn value", new TextStyle(Color.BLACK, "Arial", 12, false, false));
		_moneyInput = new MoneyInput(new Point(0, 20));
		
		_enterRoomAction = new EnterRoomAction();
		if (_room != null) 
			_enterRoomAction.setRoomId(_room.getId());
		
		addContent(_message.getComponent());
		addContent(_moneyInput.getComponent());
		
		setSize(260, 150);
		
		addActionToConfirmButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Money buyIn = _moneyInput.getValue();
				
				if (buyIn.parseToLong() < _room.getMatchInfo().getMinimumBuyIn().parseToLong()) {
					_messagePopUp = new MessagePopUp(owner, "BuyIn wrong!", "The minimun BuyIn is " + _room.getMatchInfo().getMinimumBuyIn().toString());
					_messagePopUp.setVisible(true);
				}
				else if (buyIn.parseToLong() > PlayerStats.Instance().getMoney().parseToLong()) {
					_messagePopUp = new MessagePopUp(owner, "BuyIn wrong!", "The most money that you have is " + PlayerStats.Instance().getMoney().toString());
					_messagePopUp.setVisible(true);
				}
				else {
					setVisible(false);
					owner.setVisible(false);
					
					PlayerStats.Instance().getMoney().removeMoney(buyIn);
					playerInfo.setMoneyPlayer(buyIn);
					
					_enterRoomAction.actionPerformed(null);
					try {
						_room.addPlayer(playerInfo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					ServerConnection.Instance().getMessageHandler().setMatchScreen(new MatchScreen(owner, _room, playerInfo));
				}
			}
			
		});
	}
	
	public void setRoom(Room room) {
		_room = room;
		_enterRoomAction.setRoomId(_room.getId());
		setTitle("Do you enter in the Room" + room.getId() + " ?");
	}
}
