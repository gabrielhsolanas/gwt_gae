package br.com.yaw.ggc.client.controller;

import java.util.Date;
import java.util.Iterator;

import br.com.yaw.ggc.client.MercadoriaServiceAsync;
import br.com.yaw.ggc.client.callback.DefaultCallback;
import br.com.yaw.ggc.client.model.Mercadoria;
import br.com.yaw.ggc.client.ui.IncluirMercadoriaDialog;
import br.com.yaw.ggc.client.ui.MercadoriaTable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MercadoriaController extends AbstractController {

	private MercadoriaServiceAsync service;
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private MercadoriaTable mercadoriasTable = new MercadoriaTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Label lastUpdatedLabel = new Label();
	
	public MercadoriaController(MercadoriaServiceAsync s) {
		this.service = s;
		
		final IncluirMercadoriaDialog incluirDialog = new IncluirMercadoriaDialog();
		addHandler(incluirDialog.getbSalvar(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Mercadoria m = incluirDialog.getMercadoria();
				if (m == null) return;
				
				service.add(m, new DefaultCallback<Long>() {
					@Override
					public void onSuccess(Long result) {
						m.setId(result);
						
						mercadoriasTable.add(m);
						incluirDialog.hide();
					}
					
				});
			}
		});
		addHandler(incluirDialog.getbCancelar(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				incluirDialog.hide();
			}
		});
		
		buttonPanel.addStyleName("buttonPanel");
		
		Button bNova = new Button("Nova");
		addHandler(bNova, new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				incluirDialog.center();
				incluirDialog.show();
			}
		});
		buttonPanel.add(bNova);
		
		Button bEditar = new Button("Editar");
		addHandler(bEditar, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Mercadoria m = mercadoriasTable.getMercadoriaSelected();
				if (m == null) return;
				
				incluirDialog.setMercadoria(m);
				incluirDialog.center();
				incluirDialog.show();
			}
		});
		buttonPanel.add(bEditar);
		
		Button bExcluir = new Button("Excluir");
		bExcluir.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final Mercadoria m = mercadoriasTable.getMercadoriaSelected();
				if (m == null) return;
				
				service.remove(m, new DefaultCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						mercadoriasTable.remove(m);
					}
				});
			}
		});
		buttonPanel.add(bExcluir);
		
		Button bAtualizar = new Button("Atualizar");
		bAtualizar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				fillTable();
			}
		});
		buttonPanel.add(bAtualizar);
		
		mainPanel.add(mercadoriasTable);
		mainPanel.add(buttonPanel);
		mainPanel.add(lastUpdatedLabel);

		RootPanel.get("mercadoriaList").add(mainPanel);
		
		fillTable();
	}
	
	private void updateStateButtons(final boolean enabled) {
		Iterator<Widget> it = buttonPanel.iterator();
		while (it.hasNext()) {
			Widget w = it.next();
			if (w instanceof Button) {
				((Button) w).setEnabled(enabled);
			}
		}
	}
	
	private void fillTable() {
		updateStateButtons(false);
		service.getAll(new DefaultCallback<Mercadoria[]>() {
			
			@Override
			public void onSuccess(Mercadoria[] mercadorias) {
				mercadoriasTable.clear();
				fillTable(mercadorias);
				updateStateButtons(true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				updateStateButtons(true);
				super.onFailure(caught);
			}
		});
	}
	
	private void fillTable(Mercadoria[] mercadorias) {
		mercadoriasTable.fillTable(mercadorias);
	    lastUpdatedLabel.setText("Última consulta: "+ DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(new Date()));
	}
	
}
