package br.com.cuidebem.service;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.control.exceptions.DaoException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.PatologiaPaciente;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.UsersPaciente;

@Stateless
public class PacienteDao extends AbstractDao<Paciente>{
	
	 public PacienteDao() {
		super(Paciente.class);
	}
	 
	 @Inject
	 private PatologiaPacienteDao patologiaPacienteDao;
	 
	 @Inject
	 private UsersPacienteDao usersPacienteDao;
	 
	 @Inject
	 private UsersDao usersDao;

	@PersistenceContext(unitName = "cuidebemPU")
	    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() throws DaoException {
		return em;
	}
	
	public void save(Paciente paciente, String email, List<String> patologias, boolean principal) throws DaoException{
		paciente = create(paciente);
		Users user = usersDao.find(email);
		UsersPaciente usersPaciente = new UsersPaciente();
		usersPaciente.setEmail(user);
		usersPaciente.setIdpaciente(paciente);
		usersPaciente.setPrincipal(principal);
		usersPacienteDao.create(usersPaciente);
		for (String patologia : patologias){
			PatologiaPaciente patolociaPaciente = new PatologiaPaciente(patologia,paciente);
			try{
				patologiaPacienteDao.create(patolociaPaciente);
			}catch(Exception e){
				getLogger().log(Level.INFO,"Erro ao salvar patologia do paciente",e);
			}
		}
	}
	
	
	

	
	
}
