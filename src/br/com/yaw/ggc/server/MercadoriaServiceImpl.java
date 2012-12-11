package br.com.yaw.ggc.server;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import br.com.yaw.ggc.client.MercadoriaService;
import br.com.yaw.ggc.client.model.Mercadoria;
import br.com.yaw.ggc.server.model.MercadoriaEntity;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Classe que implementa as operações definidas pelo serviço web (<i>camada servidor</i>).
 * 
 * <p>Utiliza o mecanismo de persistência para realizar as operações de cadastro de mercadorias.</p>
 * 
 * @author YaW Tecnologia
 */
public class MercadoriaServiceImpl extends RemoteServiceServlet implements MercadoriaService {
	
	private static Logger log = Logger.getLogger(MercadoriaServiceImpl.class);
	
	private static final PersistenceManagerFactory factory = JDOHelper.getPersistenceManagerFactory("ggc-pm");
	
	@Override
	public Long add(Mercadoria m) {
		PersistenceManager pm = getPersistenceManager();
		try {
			MercadoriaEntity me = pm.makePersistent(new MercadoriaEntity(m));
			log.debug("Mercadoria "+me.getId()+" foi persistida");
			return me.getId();
		} catch (Exception e){
			log.error("Nao foi possivel persistir mercadoria.", e);
			throw new RuntimeException("Nao foi possivel persistir mercadoria: "+e.getMessage());
		} finally {
			pm.close();
		}
	}
	
	@Override
	public Boolean remove(Mercadoria m) {
		boolean remove = false;
		PersistenceManager pm = getPersistenceManager();
		try {
			MercadoriaEntity me = pm.getObjectById(MercadoriaEntity.class, m.getId());
			pm.deletePersistent(me);
			log.debug("Mercadoria "+me.getId()+" foi removida");
			remove = true;
		} catch (Exception e){
			log.error("Nao foi possivel remover mercadoria.", e);
			throw new RuntimeException("Nao foi possivel remover mercadoria: "+e.getMessage());
		} finally {
			pm.close();
		}
		return remove;
	}
	
	@Override
	public Mercadoria[] getAll() {
		PersistenceManager pm = getPersistenceManager();
	    try {
	    	Query q = pm.newQuery(MercadoriaEntity.class);
	    	List<MercadoriaEntity> mercadorias = (List<MercadoriaEntity>) q.execute();
	    	log.debug("Consulta de todas as mercadorias: "+mercadorias.size());
	    	return MercadoriaEntity.toMercadoriaArray(mercadorias);
	    } catch (Exception e){
	    	log.error("Nao foi possivel consultar mercadoria(s).", e);
			throw new RuntimeException("Nao foi possivel consultar mercadoria(s): "+e.getMessage());
		} finally {
	    	pm.close();
	    }
	}

	/**
	 * @return <code>PersistenceManager</code> componente JDO responsável por executar as operações de persistência.
	 */
	private PersistenceManager getPersistenceManager() {
	    return factory.getPersistenceManager();
	}
	
}
