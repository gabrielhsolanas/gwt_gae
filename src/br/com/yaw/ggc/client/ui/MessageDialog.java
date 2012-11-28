package br.com.yaw.ggc.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MessageDialog extends DialogBox {

	public MessageDialog(Throwable ex, String title) {
		this(ex.getMessage(), title);
	}

	public MessageDialog(String msg, String title) {
		final Button bFechar = new Button("Fechar");
		bFechar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<h4>" + title + "</h4>"));
		dialogVPanel.add(new HTML("<p class='labelError'>" + msg + "</p>"));

		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_JUSTIFY);
		dialogVPanel.add(bFechar);
		add(dialogVPanel);
	}
}
