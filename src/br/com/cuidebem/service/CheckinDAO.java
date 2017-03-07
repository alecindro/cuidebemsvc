package br.com.cuidebem.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.Checkin;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Users;

@Stateless
public class CheckinDAO extends AbstractDao<Checkin>{

	
	@PersistenceContext(unitName = "cuidebemPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public CheckinDAO(){
		super(Checkin.class);
	}
	
	public Checkin findCheckinbyUser(String email) throws DaoException{
		QueryParameter parameters = QueryParameter.init("email", email);
		List<Checkin> list =  findWithNamedQuery("Checkin.findCheckinOpenByUser", parameters, 1);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	
	public Checkin findCheckinbyUser(Users user) throws DaoException{
		return findCheckinbyUser(user.getEmail());
	}
	
	public Checkin checkIN(Date dateCheckin, Users user, Paciente paciente) throws DaoException{
		Checkin checkin = new Checkin();
		checkin.setDatecheckin(new Date());
		checkin.setPaciente(paciente);
		checkin.setUser(user);
		return create(checkin);		
	}
	
	public void checkout(Checkin checkin) throws DaoException{
		checkin.setDatecheckout(new Date());
		edit(checkin);
	}
}
