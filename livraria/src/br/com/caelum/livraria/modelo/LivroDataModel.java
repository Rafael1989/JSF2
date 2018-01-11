package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

public class LivroDataModel extends LazyDataModel<Livro>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	public LivroDataModel() {
		super.setRowCount(this.livroDao.quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String titulo = (String) filtros.get("titulo");
		return this.livroDao.listaTodosPaginada(inicio, quantidade,"titulo",titulo);
	}
}
