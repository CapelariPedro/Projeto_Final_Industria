package com.example.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.database.Database;
import com.example.models.Funcionario;
import com.example.utils.AlertType;
import com.example.utils.AlertUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FuncionarioController {

    @FXML private ComboBox<Funcionario> cmbFuncionario;
    @FXML private ComboBox<String> cmbFuncao;
    @FXML private TextField filtroNome;
    @FXML private TableView<Funcionario> tableFuncionarios;
    @FXML private TableColumn<Funcionario, Integer> colFuncionarioId;
    @FXML private TableColumn<Funcionario, String> colFuncionarioNome;
    @FXML private TableColumn<Funcionario, String> colFuncionarioSetor;

    private ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();
    private boolean emEdicao = false;
    private Funcionario funcionarioEmEdicao = null;

    @FXML
    public void initialize() {
        cmbFuncionario.setEditable(true);
        configurarComboBoxFuncionario();

        cmbFuncao.setItems(FXCollections.observableArrayList(
            "Montador de Gabinetes",
            "Eletricista Montador",
            "Técnico em Eletrônica",
            "Operador de CNC",
            "Montador de Estruturas Metálicas"
            
        ));

        configurarColunas();
        carregarFuncionarios();
        carregarFuncionariosTable();
    }

    private void configurarColunas() {
        colFuncionarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFuncionarioSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
    }

    private void configurarComboBoxFuncionario() {
        cmbFuncionario.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            filtrarComboBox(cmbFuncionario, listaFuncionarios, newVal);
        });
    }

    private void filtrarComboBox(ComboBox<Funcionario> comboBox, ObservableList<Funcionario> listaOriginal, String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            comboBox.setItems(listaOriginal);
            return;
        }

        ObservableList<Funcionario> listaFiltrada = listaOriginal.filtered(item -> 
            item.getNome().toLowerCase().contains(filtro.toLowerCase())
        );

        comboBox.setItems(listaFiltrada);
        comboBox.show();
    }

    @FXML
    public void salvarFuncionario() {
        String nome = cmbFuncionario.getEditor().getText().trim();
        String setor = cmbFuncao.getValue(); 

        if (nome.isEmpty() || setor == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Campos Incompletos", "Preencha todos os campos.");
            return;
        }

        try (Connection conn = Database.getConnection()) {
            String sql;
            PreparedStatement stmt;

            if (emEdicao && funcionarioEmEdicao != null) {
                
                sql = "UPDATE funcionario SET nome = ?, setor = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, setor);
                stmt.setInt(3, funcionarioEmEdicao.getId());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    AlertUtils.showAlert(AlertType.SUCESSO, "Sucesso", "Funcionário atualizado com sucesso!");
                }

            } else {
               
                sql = "INSERT INTO funcionario (nome, setor) VALUES (?, ?)";
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, nome);
                stmt.setString(2, setor);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    AlertUtils.showAlert(AlertType.SUCESSO, "Sucesso", "Funcionário cadastrado com sucesso!");
                }
            }

           
            carregarFuncionariosTable();
            carregarFuncionarios();
            limparCampos();

            
            ProducaoController.getInstance().atualizarFuncionarios();

        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Banco", "Não foi possível salvar/atualizar o funcionário.");
            AlertUtils.logError(e);
        }
    }

    @FXML
    public void editarFuncionario() {
        Funcionario funcionarioSelecionado = tableFuncionarios.getSelectionModel().getSelectedItem();
        
        if (funcionarioSelecionado == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione um funcionário para editar.");
            return;
        }
        
        cmbFuncionario.setValue(funcionarioSelecionado);
        cmbFuncao.setValue(funcionarioSelecionado.getSetor());
        
        emEdicao = true;
        funcionarioEmEdicao = funcionarioSelecionado;
        carregarFuncionariosTable();
    }

    @FXML
    public void excluirFuncionario() {
        Funcionario funcionarioSelecionado = tableFuncionarios.getSelectionModel().getSelectedItem();
        
        if (funcionarioSelecionado == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione um funcionário para excluir.");
            return;
        }

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM funcionario WHERE id = ?"
             )) {
            stmt.setInt(1, funcionarioSelecionado.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                AlertUtils.showAlert(AlertType.SUCESSO, "Funcionário Excluído", "Funcionário removido com sucesso!");
                carregarFuncionarios();
            } else {
                AlertUtils.showAlert(AlertType.AVISO, "Erro na Exclusão", "Não foi possível excluir o funcionário.");
            }
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Exclusão", "Não foi possível remover o funcionário.");
            AlertUtils.logError(e);
        }
        carregarFuncionariosTable();
    }

    @FXML
    public void filtrarFuncionarios() {
        FilteredList<Funcionario> dadosFiltrados = new FilteredList<>(listaFuncionarios, p -> true);

        dadosFiltrados.setPredicate(funcionario -> {
            String filtroTexto = filtroNome.getText().toLowerCase();

            if (filtroTexto.isEmpty()) return true;

            return funcionario.getNome().toLowerCase().contains(filtroTexto) ||
                   funcionario.getSetor().toLowerCase().contains(filtroTexto);
        });

        tableFuncionarios.setItems(dadosFiltrados);
    }

    @FXML
    public void limparFiltro() {
        filtroNome.clear();
        tableFuncionarios.setItems(listaFuncionarios);
    }

    private void carregarFuncionariosTable() {
        ObservableList<Funcionario> listaTabela = FXCollections.observableArrayList();
        try (Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM funcionario")) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("setor")
                );
                listaTabela.add(funcionario);
            }

            tableFuncionarios.setItems(listaTabela);

        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar os funcionários.");
            AlertUtils.logError(e);
        }
    }

    private void carregarFuncionarios() {
        listaFuncionarios.clear();
        
        String sql = "SELECT f.id_funcionarios, f.nome " +
                     "FROM funcionarios f " +
                     "INNER JOIN funcionario_setor fs ON f.id_funcionarios = fs.fk_funcionario " +
                     "WHERE fs.fk_setor = 3";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                    rs.getInt("id_funcionarios"),
                    rs.getString("nome")
                );
                listaFuncionarios.add(funcionario);
            }

            cmbFuncionario.setItems(listaFuncionarios);

        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar os funcionários.");
            AlertUtils.logError(e);
        }
    }

    private void limparCampos() {
        cmbFuncionario.setValue(null);
        cmbFuncionario.getEditor().clear();
        cmbFuncao.setValue(null);
        emEdicao = false;
        funcionarioEmEdicao = null;
    }
}