package br.com.cuidebem.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.Eventos;
import br.com.cuidebem.model.Paciente;

@Stateless
public class EventosDAO extends AbstractDao<Eventos> {

	public EventosDAO() {
		super(Eventos.class);
	}


	@PersistenceContext(unitName = "cuidebemPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() throws DaoException {
		return em;
	}
	public List<Eventos> findEnabledByPaciente(Paciente paciente) throws DaoException {
		Calendar calendarInitial = Calendar.getInstance();
		calendarInitial.set(Calendar.HOUR_OF_DAY, 0);
		calendarInitial.set(Calendar.MINUTE, 0);
		calendarInitial.set(Calendar.SECOND, 0);
		Calendar calendarFinal = Calendar.getInstance();
		calendarFinal.set(Calendar.HOUR_OF_DAY, 23);
		calendarFinal.set(Calendar.MINUTE, 59);
		calendarFinal.set(Calendar.SECOND, 59);
		return findByNativeQuery("Eventos.findEnableByPaciente", paciente.getIdpaciente(),calendarInitial.getTime(),calendarFinal.getTime());
	}
	
	public List<Eventos> findByPaciente(Integer idpaciente,Calendar calendar) throws DaoException {
		Calendar calendarInitial = Calendar.getInstance();
		calendarInitial.setTime(calendar.getTime());
		calendarInitial.set(Calendar.HOUR_OF_DAY, 0);
		calendarInitial.set(Calendar.MINUTE, 0);
		calendarInitial.set(Calendar.SECOND, 0);
		Calendar calendarFinal = Calendar.getInstance();
		calendarFinal.setTime(calendar.getTime());
		calendarFinal.set(Calendar.HOUR_OF_DAY, 23);
		calendarFinal.set(Calendar.MINUTE, 59);
		calendarFinal.set(Calendar.SECOND, 59);
		return findByNativeQuery("Eventos.findByPaciente", idpaciente,calendarInitial.getTime(),calendarFinal.getTime());
	}
	
	public void delete(Eventos evento) throws DaoException{
		evento.setEnabled(false);
		edit(evento);
	}

}
