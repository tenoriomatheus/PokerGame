package core.domain.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import core.net.Message;
import core.net.ServerConnection;
import core.service.Range;

public class GetRoomsAction extends Action {

	private Range _range;
	
	public GetRoomsAction(Range range) {
		super();
		_range = range;
	}
	
	public Range getRange() {
		return _range;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		_content.clear();
		_content.add(_range);
		
		_msg = new Message("GET_ROOMS", _content);
		
		try {
			ServerConnection.Instance().write(_msg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
