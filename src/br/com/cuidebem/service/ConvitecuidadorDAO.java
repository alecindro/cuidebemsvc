package br.com.cuidebem.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.Convitecuidador;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.UsersPaciente;

@Stateless
public class ConvitecuidadorDAO extends AbstractDao<Convitecuidador>{

	
	@EJB
	private UsersPacienteDao usersPacienteDao;
	
	@PersistenceContext(unitName = "cuidebemPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ConvitecuidadorDAO(){
		super(Convitecuidador.class);
	}
	
	public List<Convitecuidador> findConviteEnabled(String emailCuidador) throws DaoException{
		QueryParameter parameters = QueryParameter.init("emailcuidador", emailCuidador); 
		return findWithNamedQuery("Convitecuidador.findEnableByEmailcuidador", parameters,0);
	}
	
	public void aceitarConvite(Users cuidador, Convitecuidador convitecuidador) throws DaoException{
		convitecuidador.setPendente(false);
		edit(convitecuidador);
		UsersPaciente up = new UsersPaciente();
		up.setIdpaciente(convitecuidador.getIdpaciente());
		up.setUsers(cuidador);
		up.setEnabled(true);
		up.setPrincipal(false);
		usersPacienteDao.create(up);
		
	}
	
}
