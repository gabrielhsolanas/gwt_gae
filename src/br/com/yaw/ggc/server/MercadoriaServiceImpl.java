package br.com.yaw.ggc.server;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.yaw.ggc.client.MercadoriaService;
import br.com.yaw.ggc.client.model.Mercadoria;
import br.com.yaw.ggc.server.dao.MercadoriaDAO;
import br.com.yaw.ggc.server.dao.MercadoriaDAOJDO;
import br.com.yaw.ggc.server.model.MercadoriaEntity;
import br.com.yaw.ggc.server.util.ApplicationProperties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Classe que implementa as operações definidas pelo serviço web (<i>camada servidor</i>).
 * 
 * @author YaW Tecnologia
 */
public class MercadoriaServiceImpl extends RemoteServiceServlet implements MercadoriaService {
	
	private static Logger log = Logger.getLogger(MercadoriaServiceImpl.class);
	
	private MercadoriaDAO dao = new MercadoriaDAOJDO();
	
	@Override
	public Long add(Mercadoria m) {
		try {
			MercadoriaEntity me = dao.save(new MercadoriaEntity(m));
			log.debug("Mercadoria "+me.getId()+" foi persistida");
			return me.getId();
		} catch (RuntimeException e){
			log.error("Nao foi possivel persistir mercadoria.", e);
			throw e;
		}
	}
	
	@Override
	public Boolean remove(Mercadoria m) {
		try {
			dao.remove(m.getId());
			log.debug("Mercadoria "+m.getId()+" foi removida");
			return true;
		} catch (RuntimeException e){
			log.error("Nao foi possivel remover mercadoria.", e);
			throw e;
		}
	}
	
	@Override
	public Mercadoria[] getAll() {
		try {
	    	List<MercadoriaEntity> mercadorias = dao.getAll();
	    	log.debug("Consulta de todas as mercadorias: "+mercadorias.size());
	    	return MercadoriaEntity.toMercadoriaArray(mercadorias);
	    } catch (RuntimeException e){
	    	log.error("Nao foi possivel consultar mercadoria(s).", e);
	    	throw e;
		}
	}
	
	@Override
	public Map<String, String> getApplicationProperties() {
		return ApplicationProperties.getAll();
	}
	
}
