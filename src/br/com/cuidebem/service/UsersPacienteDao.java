package br.com.cuidebem.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.UsersPaciente;

@Stateless
public class UsersPacienteDao extends AbstractDao<UsersPaciente>{

	public UsersPacienteDao() {
		super(UsersPaciente.class);
	}

    @PersistenceContext(unitName = "cuidebemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void save(UsersPaciente usersPaciente) throws DaoException{
    	
    	if(usersPaciente.isPrincipal()){
    		List<UsersPaciente> list = findbyUsers(usersPaciente.getUsers().getEmail());
    		if(!list.isEmpty()){
    			for(UsersPaciente up : list){
    				up.setPrincipal(false);
    				edit(up);
    			}
    		}
    	}
    	create(usersPaciente);
    }
    
    public  List<UsersPaciente> findbyUsers(String email) throws DaoException{
    	return findByNativeQuery("UsersPaciente.findByEmail", email);
    }
    
    public  UsersPaciente findbyIdPaciente(Integer idpaciente) throws DaoException{
    	UsersPaciente usersPaciente = null;
    	List<UsersPaciente> list = findByNativeQuery("UsersPaciente.findByIdPaciente", idpaciente);
    	if(!list.isEmpty()){
    		usersPaciente = list.get(0);
    	}
    	return usersPaciente;
    }
    
}
