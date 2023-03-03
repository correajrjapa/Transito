package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Acidente;
import model.Condutor;
import model.Rodovia;
import model.Veiculo;
import util.Conexao;
import util.Mensagens;

public class AcidenteController {
	
	@FXML ComboBox<Rodovia> cbRodovias;
	@FXML ComboBox<Veiculo> cbVeiculos;
	@FXML TextField txtVitimas;
	@FXML TableView<Veiculo> tbVeiculosSelecionados;
	@FXML TableColumn<Veiculo, String> colNomeCondutor;
	@FXML TableColumn<Veiculo, Number> colAnoVeiculo;
	@FXML TableColumn<Veiculo, String> colEmbriagado;
	
	
	@FXML
	public void initialize() {
		colAnoVeiculo.setCellValueFactory(cellData -> cellData.getValue().anoProperty());
		colNomeCondutor.setCellValueFactory(cellData -> cellData.getValue().getCondutor().nomeProperty());
		colEmbriagado.setCellValueFactory(cellData -> cellData.getValue().getCondutor().embriagadoProperty());
		PreencheComboRodovias();
		PreencheComboVeiculos();
	}
	
	@FXML
	public void selecionaVeiculo() {
		Veiculo sel = cbVeiculos.getSelectionModel().getSelectedItem();
		tbVeiculosSelecionados.getItems().add(sel);
	}
	
	@FXML
	public void excluiVeiculoTabela(){
		int indice = tbVeiculosSelecionados.getSelectionModel().getSelectedIndex();
		if(indice >=0) {
			if(Mensagens.msgOkCancel("Retirar veículo", "Deseja retirar esse veículo?")==ButtonType.OK) {
				tbVeiculosSelecionados.getItems().remove(indice);
			}
		}
	}
	
	@FXML
	public void cadastraAcidente() {
		Acidente a = lerTela();
		String sql = "insert into acidente(vitimas, id_rodovia) values (?,?)";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getVitimas());
			ps.setInt(2, a.getRodovia().getId());
			ps.executeUpdate();
			conn.close();
			int codigoCadastrado = buscaUltimoAcidenteCadastrado();
			insereVeiculosAcidente(codigoCadastrado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insereVeiculosAcidente(int idAcidente) {
		String sql = "insert into veiculo_acidente(id_acidente, id_veiculo) values (?,?)";
		for(Veiculo v: tbVeiculosSelecionados.getItems()) {
			try {
				Connection conn = Conexao.conectaSqlite();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, idAcidente);
				ps.setInt(2, v.getId());
				ps.executeUpdate();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Acidente lerTela() {
		Acidente a = new Acidente();
		a.setRodovia(cbRodovias.getSelectionModel().getSelectedItem());
		a.setVitimas(Integer.parseInt(txtVitimas.getText()));
		return a;
	}
	
	private int buscaUltimoAcidenteCadastrado() {
		int codigo = 0;
		String sql = "select max(id) as codigo from acidente";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				codigo = rs.getInt("codigo");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codigo;
	}
	
	
	private void PreencheComboRodovias() {
		ArrayList<Rodovia> rodovias = new ArrayList<Rodovia>();
		String sql = "select * from rodovia order by nome";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rodovia r = new Rodovia();
				r.setId(rs.getInt("id"));
				r.setNome(rs.getString("nome"));
				r.setKm(rs.getInt("km"));
				rodovias.add(r);
			}
			conn.close();
			cbRodovias.setItems(FXCollections.observableArrayList(rodovias));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Condutor buscaCondutorPorId(int id) {
		Condutor c = new Condutor();
		String sql = "select * from condutor where id = ?";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setIdade(rs.getInt("idade"));
				c.setSexo(rs.getString("sexo"));
				c.setEmbriagado(rs.getString("embriagado"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	private void PreencheComboVeiculos() {
		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
		String sql = "select * from veiculo order by ano desc";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Veiculo v = new Veiculo();
				v.setId(rs.getInt("id"));
				v.setAno(rs.getInt("ano"));
				v.setCondutor(buscaCondutorPorId(rs.getInt("id_condutor")));
				veiculos.add(v);
			}
			conn.close();
			cbVeiculos.setItems(FXCollections.observableArrayList(veiculos));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
