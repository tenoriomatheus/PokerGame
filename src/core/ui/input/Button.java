package core.ui.input;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import core.ui.UI_Element;

public class Button implements UI_Element {
	
	private JButton _button;
	private boolean _enable;
	
	public Button(Rectangle bounds, String text, Color backgroundColor, Color textColor, ActionListener actionListener) {
		_button = new JButton(text);
		
		_button.setLocation(bounds.x, bounds.y);
		_button.setSize(bounds.width, bounds.height);
		_button.setForeground(textColor);
		_button.setBackground(backgroundColor);
		_button.addActionListener(actionListener);
		
		_enable = true;
	}

	public void setLocation(Point location) {
		_button.setLocation(location);
	}
	
	public void resize(Dimension size) {
		_button.setSize(size);
	}
	
	public void setTextColor(Color textColor) {
		_button.setForeground(textColor);
	}

	public void setText(String text) {
		_button.setText(text);
	}

	public void setBackgroundColor(Color backgroundColor) {
		_button.setBackground(backgroundColor);
	}
	
	public void setActionListener(ActionListener actionListener) {
		_button.addActionListener(actionListener);
	}

	public void changeEnable() {
		_enable = !_enable;
		_button.setEnabled(_enable);
	}
	
	@Override
	public JComponent getComponent() {
		return _button;
	}
}
