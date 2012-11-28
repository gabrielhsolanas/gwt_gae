package br.com.yaw.ggc.client;


import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MercadoriaServiceAsync {

	void add(Mercadoria m, AsyncCallback<Long> callback);
	
	void remove(Mercadoria m, AsyncCallback<Boolean> callback);
	
	void getAll(AsyncCallback<Mercadoria[]> callback);
}
