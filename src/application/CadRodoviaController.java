package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Rodovia;
import util.Conexao;
import util.Mensagens;

public class CadRodoviaController {
	
	@FXML TextField txtNome;
	@FXML TextField txtKm;
	@FXML TextField txtFiltro;
	@FXML TableView<Rodovia> tbl;
	@FXML TableColumn<Rodovia, Number> colId;
	@FXML TableColumn<Rodovia, String> colNome;
	@FXML TableColumn<Rodovia, Number> colKm;
	
	private ArrayList<Rodovia> rodovias = new ArrayList<Rodovia>();
	private Rodovia rodoviaSelecionada = null;
	
	@FXML
	public void testeMsg() {
		
		if(Mensagens.msgOkCancel("Exclusão", "Deseja excluir?")==ButtonType.OK) {
			Mensagens.msgInformacao("Cabeçalho", "Confirmou exclusão");
		}else {
			Mensagens.msgInformacao("Cabeçalho", "Desistiu da exclusão");
		}
			
		
	}
	
	@FXML
	public void initialize() {
		colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colKm.setCellValueFactory(cellData -> cellData.getValue().kmProperty());
		buscaRodovias();
	}
	
	@FXML
	public void grava() {
		if(rodoviaSelecionada == null) {
			insereRodovia();
		}else {
			alteraRodovia();
			rodoviaSelecionada = null;
			txtNome.setText("");
			txtKm.setText("");
		}
	}
	
	@FXML
	public void selecionaRegistro() {
		if(tbl.getSelectionModel().getSelectedItem() != null) {
			rodoviaSelecionada = tbl.getSelectionModel().getSelectedItem();
			txtNome.setText(rodoviaSelecionada.getNome());
			txtKm.setText(rodoviaSelecionada.getKm()+"");
		}
	}
	
	
	private void insereRodovia() {
		Rodovia r = lerTela();
		String sql = "insert into rodovia(nome, km) values (?,?)";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getNome());
			ps.setInt(2, r.getKm());
			ps.executeUpdate();
			conn.close();
			buscaRodovias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void alteraRodovia() {
		Rodovia r = lerTela();
		String sql = "update rodovia set nome=?, km=? where id=?";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getNome());
			ps.setInt(2, r.getKm());
			ps.setInt(3, r.getId());
			ps.executeUpdate();
			conn.close();
			buscaRodovias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void excluiRodovia() {
		String sql = "delete from rodovia where id=?";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rodoviaSelecionada.getId());
			ps.executeUpdate();
			conn.close();
			buscaRodovias();
			txtNome.setText("");
			txtKm.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private Rodovia lerTela() {
		Rodovia r = new Rodovia();
		if(rodoviaSelecionada != null) {
			r.setId(rodoviaSelecionada.getId());
		}
		r.setNome(txtNome.getText());
		r.setKm(Integer.parseInt(txtKm.getText()));
		return r;
	}
	
	private void buscaRodovias() {
		rodovias = new ArrayList<Rodovia>();
		String sql = "select * from rodovia order by km";
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
			tbl.setItems(FXCollections.observableArrayList(rodovias));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void filtraRodovias() {
		rodovias = new ArrayList<Rodovia>();
		String sql = "select * from rodovia where nome like ? order by km";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, txtFiltro.getText()+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rodovia r = new Rodovia();
				r.setId(rs.getInt("id"));
				r.setNome(rs.getString("nome"));
				r.setKm(rs.getInt("km"));
				rodovias.add(r);
			}
			conn.close();
			tbl.setItems(FXCollections.observableArrayList(rodovias));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
