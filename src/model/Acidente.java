package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Acidente {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private Rodovia rodovia = new Rodovia();
	private IntegerProperty vitimas = new SimpleIntegerProperty(0);
	private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	
	public final IntegerProperty vitimasProperty() {
		return this.vitimas;
	}
	
	public final int getVitimas() {
		return this.vitimasProperty().get();
	}
	
	public final void setVitimas(final int vitimas) {
		this.vitimasProperty().set(vitimas);
	}

	public Rodovia getRodovia() {
		return rodovia;
	}

	public void setRodovia(Rodovia rodovia) {
		this.rodovia = rodovia;
	}

	public ArrayList<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(ArrayList<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	
}
