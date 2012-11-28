package br.com.yaw.ggc.client.callback;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DefaultCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable t) {
		throw new RuntimeException("Erro na camada server: <br/>"+t.getMessage(), t);
	}

}
