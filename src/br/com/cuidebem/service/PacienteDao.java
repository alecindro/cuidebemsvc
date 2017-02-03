package br.com.cuidebem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.PatologiaPaciente;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.UsersPaciente;

@Stateless
public class PacienteDao extends AbstractDao<Paciente> {

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

	public void save(Paciente paciente, String email, List<String> patologias, boolean principal) throws DaoException {
		if (paciente.getIdpaciente() == null) {
			paciente = create(paciente);

			Users user = usersDao.find(email);
			UsersPaciente usersPaciente = new UsersPaciente();
			usersPaciente.setUsers(user);
			usersPaciente.setIdpaciente(paciente);
			usersPaciente.setPrincipal(principal);
			usersPacienteDao.save(usersPaciente);
			// TODO melhorar esse insert
			for (String patologia : patologias) {
				PatologiaPaciente patolociaPaciente = new PatologiaPaciente(patologia, paciente);
				try {
					patologiaPacienteDao.create(patolociaPaciente);
				} catch (Exception e) {
					getLogger().log(Level.INFO, "Erro ao salvar patologia do paciente", e);
				}
			}
		} else {
			editPaciente(paciente, email, patologias, principal);
		}
	}

	public void editPaciente(Paciente paciente, String email, List<String> patologias, boolean principal)
			throws DaoException {
		edit(paciente);
		UsersPaciente usersPaciente = usersPacienteDao.findbyIdPaciente(paciente.getIdpaciente());
		if (usersPaciente.isPrincipal() != principal) {
			usersPaciente.setPrincipal(principal);
			usersPacienteDao.edit(usersPaciente);
		}
		List<String> patologiasOld = new ArrayList<String>();
		List<PatologiaPaciente> list = patologiaPacienteDao.findByIdPaciente(paciente.getIdpaciente());
		for (PatologiaPaciente pp : list) {
			if (!patologias.contains(pp.getPatologia())) {
				patologiaPacienteDao.remove(pp);
			} else {
				patologiasOld.add(pp.getPatologia());
			}
		}

		List<String> patologiasNew = patologias;
		if (patologiasNew.removeAll(patologiasOld)) {
			for (String pt : patologiasNew) {
				PatologiaPaciente patolociaPaciente = new PatologiaPaciente(pt, paciente);
				patologiaPacienteDao.create(patolociaPaciente);
			}
		}

	}

	public List<Paciente> findByUser(String email) throws DaoException {

		return findByNativeQuery("Paciente.findByUser", email);
	}

	@Override
	public void remove(Paciente entity) throws DaoException {
		entity.setEnabled(false);
		super.edit(entity);
	}

	@Override
	public List<Paciente> findAll() throws DaoException {
		return findByNativeQuery("Paciente.findAll", QueryParameter.parameterEnabled());
	}

}
