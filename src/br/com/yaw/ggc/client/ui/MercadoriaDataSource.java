package br.com.yaw.ggc.client.ui;


import java.util.ArrayList;
import java.util.List;

import br.com.yaw.ggc.client.model.Mercadoria;

public class MercadoriaDataSource implements DataSource<Mercadoria> {	
	
    private List<Mercadoria> data = new ArrayList<Mercadoria>();
    
    public MercadoriaDataSource(Mercadoria[] mercadorias) {
    	if (mercadorias != null) {
    		for (Mercadoria m: mercadorias) {
            	data.add(m);
            }
    	}
    }

    public int getRowCount() {
        return data.size();
    }

    public Mercadoria getRow(int i) {
        return data.get(i);
    }
    
    public void add(Mercadoria m) {
    	if (m == null) return;
    	
    	if (data.contains(m)) {
    		int i = data.indexOf(m);
    		data.set(i, m);
    	} else {
    		data.add(m);
    	}
    }
    
    public void remove(Mercadoria m) {
    	if (m == null || m.getId() == null) return;
    	
    	for (int i = 0; i < data.size(); i++) {
    		if (m.getId().equals(data.get(i).getId())) {
    			data.remove(i);
    			break;
    		}
    	}
    }
	
}
