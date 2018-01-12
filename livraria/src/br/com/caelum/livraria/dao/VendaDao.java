package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

public class VendaDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	private DAO<Venda> dao;
	
	@PostConstruct
	public void init() {
		this.dao = new DAO<Venda>(Venda.class,em);
	}
	
	public List<Venda> getVendas(){
		return dao.listaTodos();
	}
	
	
}
