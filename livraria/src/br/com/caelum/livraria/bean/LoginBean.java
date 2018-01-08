package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		boolean existe = new UsuarioDao().existe(this.usuario);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(existe) {
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "livro?faces-redirect=true";
		}
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage("Dados inválidos, favor comer omega 3 para melhorar a memória..."));
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

}
