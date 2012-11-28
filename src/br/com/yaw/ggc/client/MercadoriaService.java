package br.com.yaw.ggc.client;


import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mserv")
public interface MercadoriaService extends RemoteService {

	Long add(Mercadoria m) throws RuntimeException;
	
	Boolean remove(Mercadoria m) throws RuntimeException;

	Mercadoria[] getAll() throws RuntimeException;
	
}
