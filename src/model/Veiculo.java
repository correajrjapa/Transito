package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Veiculo {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private IntegerProperty ano = new SimpleIntegerProperty(0);
	private Condutor condutor = new Condutor();
	
	
	public final IntegerProperty anoProperty() {
		return this.ano;
	}
	
	public final int getAno() {
		return this.anoProperty().get();
	}
	
	public final void setAno(final int ano) {
		this.anoProperty().set(ano);
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	
	
	
	@Override
	public String toString() {
		return condutor.getNome()+" - ano "+getAno();
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
