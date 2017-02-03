/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.exceptions.PreexistingEntityException;
import br.com.cuidebem.model.Users;
import br.com.security.quali.password.UtilPassword;

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
    
    public void updatePassword(String email, String newPassword) throws DaoException{
    	Users user = find(email);
    	if(user != null){
    		String password = UtilPassword.genPassword(newPassword);
    		user.setPassword(password);
    		edit(user);
    	}
    }
    
    public List<Users> findColaboradoresByPaciente(Integer idpaciente) throws DaoException{
    	 return findByNativeQuery("Users.findCuidadorByPaciente", idpaciente);
    }
    
    public List<Users> findColaboradoresByNome(String nome) throws DaoException{
    	return findWithNamedQuery("Users.likeCuidadorByNome", QueryParameter.init("nome", "%"+nome+"%"), 0);
    }
    public List<Users> findColaboradoresByEmail(String email) throws DaoException{
    	return findWithNamedQuery("Users.likeCuidadorByEmail", QueryParameter.init("nome", "%"+email+"%"), 0);
    }
}
