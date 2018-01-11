package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.annotation.Transaction;
import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();

	@Inject
	private AutorDao dao;

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Transaction
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId()==null) {
			this.dao.adiciona(this.autor);
		}else {
			this.dao.atualiza(this.autor);
		}
		
		
		return new RedirectView("livro");
	}
	
	public List<Autor> getAutores(){
		return this.dao.listaTodos();
	}
	
	@Transaction
	public void remove(Autor autor) {
		this.dao.remove(autor);
	}
	
	public void carregaPeloId() {
		Integer id = this.autor.getId();
		this.autor = this.dao.buscaPorId(id);
		if(this.autor == null) {
			this.autor = new Autor();
		}
	}
	
}
