package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendaDao vendaDao;
	
	public List<Venda> getVendas(){
		return this.vendaDao.getVendas();
	}
	
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setLegendPosition("ne");
		model.setTitle("Vendas");
		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setLabel("Título");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		model.setAnimate(true);
		
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");
		
		List<Venda> vendas = getVendas();
		
		for(Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);
		
		return model;
	}

}
