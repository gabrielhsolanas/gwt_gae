package br.com.yaw.ggc.client.ui;

import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IncluirMercadoriaDialog extends DialogBox {

	private TextBox tbNome;
	private TextBox tbDescricao;
	private TextBox tbQuantidade;
	private TextBox tbPreco;
	private TextBox tbId;
	
	private Button bSalvar;
	private Button bCancelar;
	
	public IncluirMercadoriaDialog() {
		tbNome = new TextBox();
		tbDescricao = new TextBox();
		tbQuantidade = new TextBox();
		tbPreco = new TextBox();
		tbId = new TextBox();
		tbId.setEnabled(false);
		
		this.add(montaPanelEditarMercadoria());
	}

	private VerticalPanel montaPanelEditarMercadoria() {
		VerticalPanel fPanel = new VerticalPanel();
		fPanel.add(new Label("Nome:"));
		fPanel.add(tbNome);
		
		fPanel.add(new Label("Descricao:"));
		fPanel.add(tbDescricao);
		
		fPanel.add(new Label("Quantidade:"));
		fPanel.add(tbQuantidade);
		
		fPanel.add(new Label("Preco:"));
		fPanel.add(tbPreco);
		
		fPanel.add(new Label("Id:"));
		fPanel.add(tbId);
		
		fPanel.add(montaPanelBotoesEditar());
		return fPanel;
	}
	
	private HorizontalPanel montaPanelBotoesEditar() {
		HorizontalPanel bPanel = new HorizontalPanel();
		bSalvar = new Button("Salvar");
		bCancelar = new Button("Cancelar");
		bPanel.add(bSalvar);
		bPanel.add(bCancelar);
		return bPanel;
	}
	
	private void resetForm(){
		tbId.setValue(null);
		tbNome.setText("");
		tbDescricao.setText("");
		tbPreco.setText("");
		tbQuantidade.setText(new Integer(1).toString());
	}
	
	private void populaTextFields(Mercadoria m){
		tbId.setValue(m.getId().toString());
		tbNome.setText(m.getNome());
		tbDescricao.setText(m.getDescricao());
		tbQuantidade.setValue(m.getQuantidade().toString());
		tbPreco.setText(Mercadoria.convertPrecoToString(m.getPreco()));
	}
	
	private String validador() {
		StringBuilder sb = new StringBuilder();
		sb.append(tbNome.getText() == null || "".equals(tbNome.getText().trim()) ? "Nome, " : "");
		sb.append(tbPreco.getText() == null || "".equals(tbPreco.getText().trim()) ? "Preco, " : "");
		sb.append(tbQuantidade.getText() == null || "".equals(tbQuantidade.getText().trim()) ? "Quantidade, " : "");
		
		if (!sb.toString().isEmpty()) {
			sb.delete(sb.toString().length()-2, sb.toString().length());
		}
		return sb.toString();
	}
	
	public Mercadoria getMercadoria() {
		String msg = validador();
		if (!msg.isEmpty()) {
			MessageDialog msgDialog = new MessageDialog("Preencha o(s) campo(s): "+msg, "Validação dos campos");
			msgDialog.center();
			msgDialog.show();
			
			return null;
		}

		String nome = tbNome.getText();
		String descricao = tbDescricao.getText();

		Integer quantidade = null;
		try {
			quantidade = Integer.valueOf(tbQuantidade.getText());
		} catch (NumberFormatException nex) {
			throw new RuntimeException("Campo quantidade com conteúdo inválido!");
		}

		Double preco = null;
		try {
			preco = Mercadoria.formatStringToPreco(tbPreco.getText());
		} catch (Exception nex) {
			throw new RuntimeException("Campo preco com conteudo invalido!");
		}

		Long id = null;
		try {
			id = Long.parseLong(tbId.getText());
		} catch (Exception nex) {}

		return new Mercadoria(id, nome, descricao, quantidade, preco);
	}
	
	@Override
	public void hide() {
		resetForm();
		super.hide();
	}
	
	public void setMercadoria(Mercadoria m) {
		if (m != null) {
			populaTextFields(m);
		}
	}
	
	public Button getbSalvar() {
		return bSalvar;
	}
	
	public Button getbCancelar() {
		return bCancelar;
	}
	
}
