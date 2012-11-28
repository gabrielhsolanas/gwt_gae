package br.com.yaw.ggc.client.ui;

import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;

public class MercadoriaTable extends FlexTable {
	
    private MercadoriaDataSource source;
    
    private int selectedRow;

	public MercadoriaTable() {
		setText(0, 0, "ID");
		setText(0, 1, "Nome");
		setText(0, 2, "Descrição");
		setText(0, 3, "Quantidade");
		setText(0, 4, "Preço");
		setCellPadding(6);
		
		getRowFormatter().addStyleName(0, "listHeader");
		addStyleName("list");
		getCellFormatter().addStyleName(0, 1, "listTextColumn");
		getCellFormatter().addStyleName(0, 2, "listTextColumn");
		getCellFormatter().addStyleName(0, 3, "listNumericColumn");
		getCellFormatter().addStyleName(0, 4, "listNumericColumn");
		
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Cell td = getCellForEvent(event);
				if (td == null) return;
		        
		        boolean sameRow = selectedRow == td.getRowIndex();
		        changeRow(selectedRow, false);
		        selectedRow = td.getRowIndex();
		        changeRow(selectedRow, !sameRow);
		       	if (sameRow) selectedRow = 0;
			}
		});
	}	
	
	public void fillTable(Mercadoria[] mercadorias) {
		source = new MercadoriaDataSource(mercadorias);
		updateAll();
	}
	
	private void updateAll() {
		for (int i = this.getRowCount()-1; i > 0; i--){
            this.removeRow(1);
        }
		
		int rows = source.getRowCount();
        for(int i = 0 ; i < rows; i++ ){
            Mercadoria m = source.getRow(i);
            insertRow(i+1, m);
        }
        this.selectedRow = 0;
	}

	private void insertRow(final int row, Mercadoria m) {
		setText(row, 0, m.getId().toString());
		setText(row, 1, m.getNome());
		setText(row, 2, m.getDescricao());
		setText(row, 3, m.getQuantidade().toString());
		setText(row, 4, Mercadoria.convertPrecoToString(m.getPreco()));
		
		getCellFormatter().addStyleName(row, 3, "listNumericColumn");
		getCellFormatter().addStyleName(row, 4, "listNumericColumn");
	}
	
	private void changeRow(int row, boolean selected) {
		if (row > 0) {
			Element tr = getRowFormatter().getElement(row);
			if (tr != null)
				DOM.setStyleAttribute(tr, "backgroundColor", selected ? "#ffce00" : "#ffffff");
		}
	}
	
	public Mercadoria getMercadoriaSelected() {
		if (source == null || selectedRow == 0)
			return null;
		
		return source.getRow(selectedRow-1);
	}
	
	public void add(Mercadoria m) {
		source.add(m);
		updateAll();
	}
	
	public void remove(Mercadoria m) {
		source.remove(m);
		updateAll();
	}
}
