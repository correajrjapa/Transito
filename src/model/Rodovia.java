package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rodovia {
	
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty km = new SimpleIntegerProperty(0);
	
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final IntegerProperty kmProperty() {
		return this.km;
	}
	
	public final int getKm() {
		return this.kmProperty().get();
	}
	
	public final void setKm(final int km) {
		this.kmProperty().set(km);
	}
	
	@Override
	public String toString() {
		return getNome()+": "+getKm()+" km";
	}
	
	
}
