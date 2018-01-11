package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.annotation.Log;
import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	public void init() {
		this.dao = new DAO<Livro>(Livro.class,em);
	}

	@Log
	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	@Log
	public void remove(Livro t) {
		dao.remove(t);
	}

	@Log
	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	@Log
	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	@Log
	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	@Log
	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}

	@Log
	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}
	
	

}
