package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
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
import br.com.caelum.livraria.modelo.LivroDataModel;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	
	private LivroDataModel livroDataModel = new LivroDataModel();
	
	private Integer autorId;

	private List<Livro> livros;
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<String> getGeneros() {
	    return generos;
	}
	
	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}
	
	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}
	
	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
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
		if(this.livros == null) {
			this.livros = new DAO<Livro>(Livro.class).listaTodos();
		}
		return this.livros;
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
			this.livros = new DAO<Livro>(Livro.class).listaTodos();
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
	
	public void carregaPeloId() {
		Integer id = this.livro.getId();
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(id);
		if(this.livro == null) {
			this.livro = new Livro();
		}
	}
	
	public void comecaComDigitoUm(FacesContext facesContext,UIComponent uiComponent, Object valor) {
		if(!valor.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O ISBN só pode começar com 1, vc (Faustão: -EROUUUU"));
		}
	}
	
}
