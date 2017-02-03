package br.com.cuidebem.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.exceptions.DaoException;
import br.com.cuidebem.model.UsersPhoto;

@Stateless
public class UsersPhotoDao extends AbstractDao<UsersPhoto> {
	
	


	public UsersPhotoDao() {
		super(UsersPhoto.class);
	}

	@PersistenceContext(unitName = "cuidebemPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsersPhoto findByUser(String email) throws DaoException {
		QueryParameter parameters = QueryParameter.init("email", email);
		List<UsersPhoto> ups = findWithNamedQuery("UsersPhoto.findByEmail", parameters, 0);
		UsersPhoto up = null;
		if (!ups.isEmpty()) {
			up = ups.get(0);
		}
		return up;
	}

	public byte[] findUserPhoto(String email) throws DaoException {
		UsersPhoto usersPhoto = findByUser(email);
		byte[] photo = null;
		if(usersPhoto != null){
			photo = usersPhoto.getPhoto();
		}
		return photo;
	}
	
	public UsersPhoto savePhoto(String email, InputStream photo) throws DaoException{
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			UsersPhoto usersPhoto = new UsersPhoto();
			usersPhoto.setEmail(email);
			usersPhoto.setPhoto(imageInByte);
			UsersPhoto result =  savePhoto(usersPhoto);
			baos.close();
			return result;
		} catch (IOException e) {
			throw new  DaoException(e.getMessage());
		}
	}

	public UsersPhoto savePhoto(UsersPhoto usersPhoto) throws DaoException {
		UsersPhoto old = findByUser(usersPhoto.getEmail());
		if (old  != null) {
			old.setPhoto(usersPhoto.getPhoto());
			edit(old);
			return old;
		} else {
			return create(usersPhoto);
		}
	}



}
