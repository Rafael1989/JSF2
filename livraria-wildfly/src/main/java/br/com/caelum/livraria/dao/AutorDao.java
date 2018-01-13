package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Autor;

@SuppressWarnings("serial")
@Stateless //EJB
public class AutorDao implements Serializable {

	@PersistenceContext
	EntityManager em;

	private DAO<Autor> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public List<Autor> buscaPorIdLivro(Integer id) {
		TypedQuery<Autor> query = em.createQuery("select a from Autor a where id = (select l from Livro l where id = :id)",Autor.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
