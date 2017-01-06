/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cuidebem.control.exceptions.DaoException;

/**
 *
 * @author aleci
 */
public abstract class AbstractDao<T> {

    private Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager() throws DaoException;

    public void create(T entity) throws DaoException {
    	try{
        getEntityManager().persist(entity);
    	}catch(Exception e){
    		throw new DaoException(e.getMessage(),e);
    	}
    }

    public void edit(T entity) throws DaoException {
    	try{
        getEntityManager().merge(entity);
    	}catch(Exception e){
    		throw new DaoException(e.getMessage(),e);
    	}
    }

    public void remove(T entity) throws DaoException {
    	try{
        getEntityManager().remove(getEntityManager().merge(entity));
    	}catch(Exception e){
    		throw new DaoException(e.getMessage(),e);
    	}
    }

    public T find(Object id) throws DaoException {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() throws DaoException {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) throws DaoException {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() throws DaoException {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
