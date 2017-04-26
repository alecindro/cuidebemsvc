package br.com.cuidebem.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.PacientePhoto;

@Stateless
public class PacientePhotoDao  extends AbstractDao<PacientePhoto> {

	@PersistenceContext(unitName = "cuidebemPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public PacientePhotoDao(){
		super(PacientePhoto.class);
	}
	
	public PacientePhoto findByPaciente(Integer idpaciente) throws DaoException {
		QueryParameter parameters = QueryParameter.init("idpaciente", idpaciente);
		List<PacientePhoto> ups = findWithNamedQuery("PacientePhoto.findByIdpaciente", parameters, 0);
		PacientePhoto up = null;
		if (!ups.isEmpty()) {
			up = ups.get(0);
		}
		return up;
	}

	public byte[] findPacientePhoto(Integer idpaciente) throws DaoException {
		PacientePhoto pacientePhoto = findByPaciente(idpaciente);
		byte[] photo = null;
		if(pacientePhoto != null){
			photo = pacientePhoto.getPhoto();
		}
		return photo;
	}
	
	public PacientePhoto savePhoto(Paciente paciente, InputStream photo) throws DaoException{
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			PacientePhoto pacientePhoto = new PacientePhoto();
			pacientePhoto.setIdpaciente(paciente.getIdpaciente());
			pacientePhoto.setPaciente(paciente);
			pacientePhoto.setPhoto(imageInByte);
			PacientePhoto result =  savePhoto(pacientePhoto);
			baos.close();
			return result;
		} catch (IOException e) {
			throw new  DaoException(e.getMessage());
		}
	}

	public PacientePhoto savePhoto(PacientePhoto pacientePhoto) throws DaoException {
		PacientePhoto old = findByPaciente(pacientePhoto.getIdpaciente());
		if (old  != null) {
			old.setPhoto(pacientePhoto.getPhoto());
			edit(old);
			return old;
		} else {
			return create(pacientePhoto);
		}
	}

}
