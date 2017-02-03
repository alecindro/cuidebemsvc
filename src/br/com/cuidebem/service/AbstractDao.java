/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;

import br.com.cuidebem.exceptions.DaoException;

/**
 *
 * @author aleci
 */
public abstract class AbstractDao<T> {

	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Logger getLogger() {
		return Logger.getLogger(entityClass.getName());
	}

	protected abstract EntityManager getEntityManager() throws DaoException;

	public T create(T entity) throws DaoException {
		try {
			getLogger().log(Level.INFO, "Salvando " + entity.toString());
			getEntityManager().persist(entity);
			getEntityManager().flush();
			getLogger().log(Level.INFO, "Salvou " + entity.toString());
			return entity;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void edit(T entity) throws DaoException {
		try {
			getLogger().log(Level.FINE, "Atualizando " + entity.toString());
			getEntityManager().merge(entity);
			getLogger().log(Level.FINE, "Atualizou " + entity.toString());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void remove(T entity) throws DaoException {
		try {
			getLogger().log(Level.FINE, "Excluindo " + entity.toString());
			getEntityManager().remove(getEntityManager().merge(entity));
			getLogger().log(Level.FINE, "Excluiu " + entity.toString());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public T find(Object id) throws DaoException {
		try {
			getLogger().log(Level.FINE, "Pesquisando " + id);
			return getEntityManager().find(entityClass, id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	public List<T> findWithNamedQuery(String namedQueryName,
			QueryParameter parameters, int resultLimit) throws DaoException {
		try {
		EntityManager em = getEntityManager();
		Set<Entry<String, Object>> rawParameters = parameters.getParameters()
				.entrySet();
		Query query = em.createNamedQuery(namedQueryName);

		if (resultLimit > 0)
			query.setMaxResults(resultLimit);
		for (Entry entry : rawParameters) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
		return query.getResultList();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	public List<T> findByNativeQuery(String namedQuery,Object... parameters) throws DaoException{
		try{
		javax.persistence.Query query = getEntityManager().createNamedQuery(namedQuery);
		if(parameters != null){
			int i = 1;
			for(Object parameter : parameters){
				query.setParameter(i, parameter);
				i = i+1;
			}
		}
		
			return query.getResultList();
		}catch(Exception e){
			throw new DaoException(e.getMessage(), e);
		}
		
	}

	public List<T> findAll() throws DaoException {
		try {
			getLogger().log(Level.FINE, "Listando todos objetos: " + entityClass.getName());
			javax.persistence.criteria.CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder()
					.createQuery();
			cq.select(cq.from(entityClass));
			return getEntityManager().createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<T> findRange(int[] range) throws DaoException {
		try {
			getLogger().log(Level.FINE, "Listando range objetos: " + entityClass.getName());
			javax.persistence.criteria.CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder()
					.createQuery();
			cq.select(cq.from(entityClass));
			javax.persistence.Query q = getEntityManager().createQuery(cq);
			q.setMaxResults(range[1] - range[0] + 1);
			q.setFirstResult(range[0]);
			return q.getResultList();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public int count() throws DaoException {
		try {
			getLogger().log(Level.FINE, "Contando objetos: " + entityClass.getName());
			javax.persistence.criteria.CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder()
					.createQuery();
			javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
			cq.select((Selection<? extends T>) getEntityManager().getCriteriaBuilder().count(rt));
			javax.persistence.Query q = getEntityManager().createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

}
