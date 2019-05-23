package gestion;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class loaderService
 */
@Stateless
@LocalBean
public class loaderService implements loaderServiceRemote {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public loaderService() {
        // TODO Auto-generated constructor stub
    	
    }

	@Override
	public void addLoader(loader l) {
		em.persist(l);
		
	}

	@Override
	public void updateLoder(loader l) {
		// TODO Auto-generated method stub
		em.merge(l);
	}

	@Override
	public void removeLoder(loader l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public loader findLoder(int id) {
		return em.find(loader.class,id);
	}

}
