package br.com.yaw.ggc.client;

import br.com.yaw.ggc.client.controller.MercadoriaController;
import br.com.yaw.ggc.client.ui.MessageDialog;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;

/**
 * Entry point da aplicação. O método <code>onModuleLoad()</code> é acionado para carregar os componentes do front-end.
 * 
 * Veja a configuração no <code>Gwt_gae.gwt.xml</code>.
 * 
 * @author YaW Tecnologia
 */
public class GwtGaeJDO implements EntryPoint {

	private final MercadoriaServiceAsync mercadoriaService = GWT.create(MercadoriaService.class);

	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				Throwable ue = unwrap(e);
				MessageDialog msgDialog = new MessageDialog(ue, "Erro durante execução");
				msgDialog.center();
				msgDialog.show();
			}
		});
		
		new MercadoriaController(mercadoriaService);
	}

	/**
	 * @param e Recebe uma exceção
	 * @return Procura pela exceção original caso o GWT use um wrapper.
	 * @see UmbrellaException
	 */
	private Throwable unwrap(Throwable e) {
		if (e instanceof UmbrellaException) {
			UmbrellaException ue = (UmbrellaException) e;
			if (ue.getCauses().size() == 1) {
				return unwrap(ue.getCauses().iterator().next());
			}
		}
		return e;
	}

}
