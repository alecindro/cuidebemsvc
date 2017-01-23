/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.control.exceptions.DaoException;
import br.com.cuidebem.control.exceptions.PreexistingEntityException;
import br.com.cuidebem.model.Users;

/**
 *
 * @author aleci
 */
@Stateless
public class UsersDao extends AbstractDao<Users> {

    @PersistenceContext(unitName = "cuidebemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersDao() {
        super(Users.class);
    }
    public void confirmaAceite(String email) throws Exception{
    	Users user = find(email);
    	if(user == null){
    		throw new DaoException("Usu�rio n�o encontrado: "+email);
    	}
    	if (!user.isBlocked()){
    		throw new PreexistingEntityException("usu�rio j� foi desbloqueado");
    	}
    	user.setBlocked(false);
    	user.setActivation(new Date());
    	edit(user);
    }
    
}
