package br.com.yaw.ggc.client;


import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interface que define as operações assíncronas na camada de serviço web.
 * 
 * <p>Todas as operações da camada cliente com servidor são executadas de forma assíncrona.</p>
 * 
 * @see br.com.yaw.ggc.client.MercadoriaService
 * @author YaW Tecnologia
 */
public interface MercadoriaServiceAsync {

	void add(Mercadoria m, AsyncCallback<Long> callback);
	
	void remove(Mercadoria m, AsyncCallback<Boolean> callback);
	
	void getAll(AsyncCallback<Mercadoria[]> callback);
}
