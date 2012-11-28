package br.com.yaw.ggc.client.controller;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractController implements ClickHandler {

	private HashMap<String, ClickHandler> handlers;

	public AbstractController() {
		handlers = new HashMap<String, ClickHandler>();
	}
	
	
	@Override
	public void onClick(ClickEvent event) {
		Widget w = (Widget) event.getSource();
		if (w == null) {
			return;
		}
		String text = w.getElement().getInnerText();
		ClickHandler handler = handlers.get(text);
		if (handler != null) {
			handler.onClick(event);
		}
	}
	
	public void addHandler(Widget w, ClickHandler h) {
		if (w == null || h == null) {
			return;
		}
		w.addDomHandler(h, ClickEvent.getType());
		String text = w.getElement().getInnerText();
		handlers.put(text, h);
	}
}
