package br.com.yaw.ggc.client.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import br.com.yaw.ggc.client.MercadoriaServiceAsync;
import br.com.yaw.ggc.client.callback.DefaultCallback;
import br.com.yaw.ggc.client.model.Mercadoria;
import br.com.yaw.ggc.client.ui.IncluirMercadoriaDialog;
import br.com.yaw.ggc.client.ui.MercadoriaTable;
import br.com.yaw.ggc.client.ui.MessageDialog;

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

/**
 * Define o controlador para operações com o cadastro <code>Mercadoria</code>.
 * 
 * <p>Esse componente é responsável por instanciar objetos GUI, além de vincular as operações com o serviço web.</code>
 * 
 * <p>As operações com a camada servidor são acessadas pela interface <code>MercadoriaServiceAsync</code>.</p>
 * 
 * @author YaW Tecnologia
 */
public class MercadoriaController extends AbstractController {

	private MercadoriaServiceAsync service;
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private MercadoriaTable mercadoriasTable = new MercadoriaTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Label lastUpdatedLabel = new Label();
	
	public MercadoriaController(MercadoriaServiceAsync s) {
		this.service = s;
		
		final IncluirMercadoriaDialog incluirDialog = new IncluirMercadoriaDialog();
		registerHandler(incluirDialog.getbSalvar(), new ClickHandler() {
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
		registerHandler(incluirDialog.getbCancelar(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				incluirDialog.hide();
			}
		});
		
		Button bNova = new Button("Nova");
		registerHandler(bNova, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				incluirDialog.center();
				incluirDialog.show();
			}
		});
		
		Button bEditar = new Button("Editar");
		registerHandler(bEditar, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Mercadoria m = mercadoriasTable.getMercadoriaSelected();
				if (m == null) return;
				
				incluirDialog.setMercadoria(m);
				incluirDialog.center();
				incluirDialog.show();
			}
		});
		
		Button bExcluir = new Button("Excluir");
		registerHandler(bExcluir, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Mercadoria m = mercadoriasTable.getMercadoriaSelected();
				if (m == null) return;
				
				service.remove(m, new DefaultCallback<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							mercadoriasTable.remove(m);
						}
					}
				});
			}
		});
		
		Button bAtualizar = new Button("Atualizar");
		registerHandler(bAtualizar, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fillTable();
			}
		});
		
		Button bSobre = new Button("Sobre");
		registerHandler(bSobre, new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				service.getApplicationProperties(new DefaultCallback<Map<String,String>>() {
					public void onSuccess(Map<String,String> result) {
						StringBuilder sb = new StringBuilder();
						for (String k: result.keySet()) {
							sb.append("<strong>").append(k).append(":</strong> ")
								.append(result.get(k)).append("<br/>");
						}
						MessageDialog msgDialog = new MessageDialog(sb.toString(),"Informações da Aplicação");
						msgDialog.show();
						msgDialog.center();
					};
				});
			}
		});
		
		buttonPanel.addStyleName("buttonPanel");
		buttonPanel.add(bNova);
		buttonPanel.add(bEditar);
		buttonPanel.add(bExcluir);
		buttonPanel.add(bAtualizar);
		buttonPanel.add(bSobre);
		
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
