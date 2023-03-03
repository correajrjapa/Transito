package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Condutor {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty idade = new SimpleIntegerProperty(0);
	private StringProperty sexo = new SimpleStringProperty("");
	private StringProperty embriagado = new SimpleStringProperty("");
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final IntegerProperty idadeProperty() {
		return this.idade;
	}
	
	public final int getIdade() {
		return this.idadeProperty().get();
	}
	
	public final void setIdade(final int idade) {
		this.idadeProperty().set(idade);
	}
	
	public final StringProperty sexoProperty() {
		return this.sexo;
	}
	
	public final String getSexo() {
		return this.sexoProperty().get();
	}
	
	public final void setSexo(final String sexo) {
		this.sexoProperty().set(sexo);
	}
	
	public final StringProperty embriagadoProperty() {
		return this.embriagado;
	}
	
	public final String getEmbriagado() {
		return this.embriagadoProperty().get();
	}
	
	public final void setEmbriagado(final String embriagado) {
		this.embriagadoProperty().set(embriagado);
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
