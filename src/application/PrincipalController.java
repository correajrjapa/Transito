package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PrincipalController {

	@FXML
	TabPane pane;
	
	private void abreTela(String titulo, String Arquivofxml) {
		try {
			boolean aberta = false;
			for (Tab tb : pane.getTabs()) {
				if (tb.getText().equals(titulo)) {
					aberta = true;
					pane.getSelectionModel().select(tb);
				}
			}
			
			if (!aberta) {
				Tab tab = new Tab(titulo);
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(Arquivofxml)));
				pane.getSelectionModel().select(tab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void abreCadastroRodovia() {
		abreTela("Cadastro de Rodovias", "CadRodovia.fxml");
	}
	
	@FXML
	public void abreCadastroAcidente() {
		abreTela("Cadastro de Acidentes", "Acidente.fxml");
	}

}
