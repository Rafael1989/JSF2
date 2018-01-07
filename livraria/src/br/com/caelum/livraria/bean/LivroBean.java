package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	public RedirectView autorForm() {
		return new RedirectView("autor");
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("O livro precisa de um autor, "
					+ "a não ser que foi escrito pelo gasparzinho"));
			return;
		}
		
		if(this.livro.getId()==null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		
		this.livro = new Livro();
	}
	
	public void remove(Livro livro) {
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void carrega(Livro livro) {
		this.livro = livro;
	}
	
	public void comecaComDigitoUm(FacesContext facesContext,UIComponent uiComponent, Object valor) {
		if(!valor.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O ISBN só pode começar com 1, vc (Faustão: -EROUUUU"));
		}
	}

}
