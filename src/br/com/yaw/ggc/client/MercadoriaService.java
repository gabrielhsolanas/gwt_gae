package br.com.yaw.ggc.client;


import java.util.Map;

import br.com.yaw.ggc.client.model.Mercadoria;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interface que expõe os serviços na camada web (servidor).
 * 
 * <p>Os métodos definidos nessa interface lançam <code>RuntimeException</code> para manter o <i>tipo</i> da exceção original na camada cliente.</p>
 * 
 * @author YaW Tecnologia
 */
@RemoteServiceRelativePath("mserv")
public interface MercadoriaService extends RemoteService {

	/**
	 * Faz a inserção ou atualização da mercadoria no mecanismo de persistencia.
	 * @param m
	 * @return o <code>ID</code> o da mercadoria persistida.
	 * @throws RuntimeException caso algum erro durante a operação aconteça.
	 */
	Long add(Mercadoria m) throws RuntimeException;
	
	/**
	 * Exclui o registro da mercadoria no mecanismo de persistencia.
	 * @param m
	 * @return <code>true</code>
	 * @throws RuntimeException caso algum erro durante a operação aconteça.
	 */
	Boolean remove(Mercadoria m) throws RuntimeException;

	/**
	 * @return Lista com todas as mercadorias cadastradas no mecanismo de persistencia.
	 * @throws RuntimeException
	 */
	Mercadoria[] getAll() throws RuntimeException;
	
	/**
	 * @return Recupera informações da aplicação no arquivo <code>META-INF/MANIFEST.MF</code>.
	 */
	Map<String, String> getApplicationProperties();

}
 