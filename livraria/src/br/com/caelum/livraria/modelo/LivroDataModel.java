package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro>{
	
	private static final long serialVersionUID = 1L;

	public LivroDataModel() {
		super.setRowCount(new DAO<Livro>(Livro.class).contaTodos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
	    String titulo = (String) filtros.get("titulo");

	    return new DAO<Livro>(Livro.class).listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}

}
