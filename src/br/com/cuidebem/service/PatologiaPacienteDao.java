package br.com.cuidebem.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
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
	
	public List<PatologiaPaciente> findByIdPaciente(int idpaciente) throws DaoException{
		return findByNativeQuery("PatologiaPaciente.findByIdPaciente", idpaciente);
	}

}
