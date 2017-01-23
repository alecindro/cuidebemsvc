package br.com.cuidebem.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.model.UsersPaciente;

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

}
