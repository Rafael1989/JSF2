package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.annotation.Log;
import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Log
	public boolean existe(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha",Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			Usuario resultado = query.getSingleResult();
		}catch(NoResultException e) {
			return false;
		}
		return true;
	}

}
