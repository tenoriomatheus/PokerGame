package core.domain.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import core.net.Message;
import core.net.ServerConnection;

public class EnterRoomAction extends Action {

	private long _roomId;
	
	public EnterRoomAction() {
		super();
	}
	
	public void setRoomId(long roomId) {
		_roomId = roomId;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		_content.clear();
		_content.add(_roomId);
		
		_msg = new Message("ENTER_ROOM", _content);
		
		try {
			ServerConnection.Instance().write(_msg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
