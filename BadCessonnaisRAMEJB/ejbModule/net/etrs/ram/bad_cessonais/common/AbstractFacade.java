package net.etrs.ram.bad_cessonais.common;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Facade abstraite générique CRUD pour les entités, paramétrée par "generics".
 *
 * @author FX
 */
public abstract class AbstractFacade <T> implements Facade<T>
{

    private Log log = LogFactory.getLog(this.getClass());
    /**
     * instance de EntityManager, injectée.
     */
    @PersistenceContext
    public EntityManager em;
    
    private PersistenceUnitUtil puu;
    
    /**
     * instance de Class représentant la classe réelle du type paramétré
     */
    @SuppressWarnings("rawtypes")
	private Class parameterizedType;

    /**
     * méthode déclenchée après construction pour déterminer la 
     * Classe réelle du type paramétré.
     */
    @SuppressWarnings("rawtypes")
    @PostConstruct
    private void init()
    {
		Class thisClass =  this.getClass();
        ParameterizedType paramType = (ParameterizedType) thisClass.getGenericSuperclass();
        parameterizedType = (Class) paramType.getActualTypeArguments()[0];      
        puu = em.getEntityManagerFactory().getPersistenceUnitUtil();
    }

    /**
     * @see Facade#newInstance()
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public T newInstance()
    {
        try
        {
            // utilisation de l'API de reflection pour instancier 
            // le bon type paramétré par le Generic.
            return (T) parameterizedType.newInstance();
            
        }
        catch (Exception ex)
        {
            if (log.isErrorEnabled())
            {
                log.error(ex);
            }
            return null;
        }
    }

    /**
     * @see Facade#getBusinessClass()
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getBusinessClass()
    {
        return this.parameterizedType;
    }

    /**
     * @see Facade#create(entity.IdentifiableEntity)
     * @param t
     */
    @Override
    public void create(T t)
    {
        em.persist(t);
        if (log.isInfoEnabled())
        {
            log.info("Ecriture en base de : " + t);
        }
    }

    /**
     * @see Facade#update(entity.IdentifiableEntity)
     * @param t
     */
    @Override
    public T update(T t)
    {
        t = em.merge(t);
        if (log.isInfoEnabled())
        {
            log.info("Mise à jour en base de : " + t);
        }
        return t;
    }

    /**
     * @see Facade#delete(entity.IdentifiableEntity)
     * @param t
     */
    @SuppressWarnings("unchecked")
	@Override
    public void delete(T t)
    {
        // t est ici détaché
        // pour le supprimé, sans influer sur la base de données
        // il faut aller le récupérer selon son ID
        // on récupère simplement la référence pour ne pas charger
        // les données du Bean.
        T attachedEntity = (T) em.getReference(parameterizedType, puu.getIdentifier(t));

        // ... et demander sa suppression
        em.remove(attachedEntity);
        
        if (log.isInfoEnabled())
        {
            log.info("Suppression en base de : " + t);
        }
    }

    /**
     * @see Facade#readAll()          
     */
    @Override
    public List<T> readAll()
    {
        return this.readAll(new String[0]);
    }
                
                
    /**
     * @see Facade#readAll(String...)
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<T> readAll(String... orderBy)
    {
        // Usage de criteria pour executer une sorte de "findAll"
        // en fonction du type paramétré de la classe.
        // on aurait pu aussi contruire une requête JPQL à la volée
        // mais je trouve cette façon plus propre.
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<T> c = qb.createQuery(parameterizedType);
        Root<T> from = c.from(parameterizedType);

        this.addSort(qb, c, from, orderBy);

        TypedQuery<T> query = em.createQuery(c);
        return query.getResultList();
    }
    


    @SuppressWarnings("unchecked")
    @Override
    public Long countAll()
    {
    	CriteriaBuilder qb = em.getCriteriaBuilder();
    	CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    	cq.select(qb.count(cq.from(parameterizedType)));
    	//cq.where(/*your stuff*/);
    	return em.createQuery(cq).getSingleResult();
    }

    /**
     * @see Facade#read(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	@Override
    public T read(Object id)
    {
        return (T) em.find(parameterizedType, id);
    }

    /**
     * @see Facade#search(java.lang.String, java.lang.Object, java.lang.String[]) 
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<T> search(String parameterName, 
                          Object parameterValue, 
                          String... orderBy)
    {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<T> c = qb.createQuery(parameterizedType);
        Root<T> from = c.from(parameterizedType);
        Predicate restriction = qb.equal(from.get(parameterName), parameterValue);
        c.where(restriction);

        this.addSort(qb, c, from, orderBy);

        TypedQuery<T> query = em.createQuery(c);
        return query.getResultList();
    }

    /**
     * Méthode privée qui ajouter un critère de tri à une requête Criteria. 
     * Cette méthode est utilisée en interne par readAll() et search().
     * 
     * @param criteriaBuilder
     * @param query
     * @param from
     * @param orderBy 
     */
    private void addSort(CriteriaBuilder criteriaBuilder, 
                         CriteriaQuery<T> query, 
                         Root<T> from, 
                         String... orderBy)
    {
        // ajout du tri, si éventuellement des paramètres de tri sont passés en paramètre
        if (orderBy!=null && orderBy.length > 0)
        {
            List<Order> orders = new ArrayList<Order>();
            for (String orderParameter : orderBy)
            {
                Order order = criteriaBuilder.asc(from.get(orderParameter));
                orders.add(order);
            }
            query.orderBy(orders);
        }
    }
    
    /**
     * Rafraichit l'instance en mémoire par rapport à la base de données.
     * 
     * @param t 
     */
    @SuppressWarnings("unchecked")
	public void refresh(T t)
    {
        t = (T) this.em.getReference(parameterizedType, puu.getIdentifier(t));
        this.em.refresh(t);
    }
    
    /**
     * retourne une instance du log de la façade.
     * @return instance de Log.
     */
    public Log getLog()
    {
        return this.log;
    }
    
   
}
