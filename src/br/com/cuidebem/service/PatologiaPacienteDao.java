package br.com.cuidebem.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.control.exceptions.DaoException;
import br.com.cuidebem.model.PatologiaPaciente;

@Stateless
public class PatologiaPacienteDao extends AbstractDao<PatologiaPaciente>{
	
	@PersistenceContext(unitName = "cuidebemPU")
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() throws DaoException {
		return em;
	}
	
	public PatologiaPacienteDao(){
		super(PatologiaPaciente.class);
	}

}
