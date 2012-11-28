package br.com.yaw.ggc.server;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import br.com.yaw.ggc.client.MercadoriaService;
import br.com.yaw.ggc.client.model.Mercadoria;
import br.com.yaw.ggc.server.model.MercadoriaEntity;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MercadoriaServiceImpl extends RemoteServiceServlet implements MercadoriaService{
	
	//TODO logger
	
	private static final PersistenceManagerFactory factory = JDOHelper.getPersistenceManagerFactory("ggc-pm");
	
	@Override
	public Long add(Mercadoria m) {
		PersistenceManager pm = getPersistenceManager();
		try {
			MercadoriaEntity n = pm.makePersistent(new MercadoriaEntity(m));
			return n.getId();
		} catch (Exception e){
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
			remove = true;
		} catch (Exception e){
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
	    	return MercadoriaEntity.toMercadoriaArray(mercadorias);
	    } catch (Exception e){
			throw new RuntimeException("Nao foi possivel consultar mercadoria(s): "+e.getMessage());
		} finally {
	    	pm.close();
	    }
	}
	
	private PersistenceManager getPersistenceManager() {
	    return factory.getPersistenceManager();
	}
	
}
