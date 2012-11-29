package br.com.yaw.ggc.client.callback;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Define um componente <code>AsyncCallback</code> com tratamento default para evento de falhas.
 * 
 * <p>Esse componente deve ser utilizado para tratar o resultado das operações com a camada servidor.</p>
 * @author YaW Tecnologia
 *
 * @param <T>
 */
public abstract class DefaultCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable t) {
		throw new RuntimeException("Erro na camada server: <br/>"+t.getMessage(), t);
	}

}
