package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		boolean existe = this.usuarioDao.existe(this.usuario);
		if(existe) {
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "livro?faces-redirect=true";
		}
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage("Dados inválidos, favor comer omega 3 para melhorar a memória..."));
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		facesContext.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

}
