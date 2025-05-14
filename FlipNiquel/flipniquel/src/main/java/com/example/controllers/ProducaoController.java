package com.example.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.database.Database;
import com.example.models.Funcionario;
import com.example.models.Maquina;
import com.example.models.Producao;
import com.example.models.Produto;
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
import javafx.util.StringConverter;

public class ProducaoController {
    @FXML private ComboBox<Funcionario> cmbFuncionario;
    @FXML private ComboBox<Maquina> cmbMaquina;
    @FXML private ComboBox<Produto> cmbProduto;
    @FXML private TextField txtQuantidade;
    @FXML private TextField filtroNome;
    @FXML private TableView<Producao> tableProducao;
    @FXML private TableColumn<Producao, Integer> colId;
    @FXML private TableColumn<Producao, String> colFuncionario;
    @FXML private TableColumn<Producao, String> colMaquina;
    @FXML private TableColumn<Producao, String> colProduto;
    @FXML private TableColumn<Producao, Integer> colQuantidade;
    @FXML private TableColumn<Producao, LocalDateTime> colDataProducao;

    private ObservableList<Producao> listaProducao = FXCollections.observableArrayList();
    private ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();
    private ObservableList<Maquina> listaMaquinas = FXCollections.observableArrayList();
    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    private boolean emEdicao = false;
    private Producao producaoEmEdicao = null;

    // Referência estática ao controlador de Produção
    private static ProducaoController instance;

    public ProducaoController() {
        instance = this; // Define a instância estática ao criar o controller
    }

    @FXML
    public void initialize() {
        configurarColunas();
        configurarComboBoxEditaveis();
        atualizarFuncionarios();
        carregarMaquinas();
        atualizarProdutos();
        carregarProducoes();
        resetarCampos();
        atualizarCampos();
    }

    private void configurarColunas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFuncionario.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        colMaquina.setCellValueFactory(new PropertyValueFactory<>("nomeMaquina"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataProducao.setCellValueFactory(new PropertyValueFactory<>("dataProducao"));
    }

    private void configurarComboBoxEditaveis() {
        // Funcionário
        cmbFuncionario.setEditable(true);
        cmbFuncionario.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            filtrarComboBox(cmbFuncionario, listaFuncionarios, newVal);
        });

        // Máquina
        cmbMaquina.setEditable(true);
        cmbMaquina.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            filtrarComboBox(cmbMaquina, listaMaquinas, newVal);
        });

        // Produto
        cmbProduto.setEditable(true);
        cmbProduto.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            filtrarComboBox(cmbProduto, listaProdutos, newVal);
        });
    }

    private <T> void filtrarComboBox(ComboBox<T> comboBox, ObservableList<T> listaOriginal, String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            comboBox.setItems(listaOriginal);
            return;
        }

        ObservableList<T> listaFiltrada = listaOriginal.filtered(item -> {
            String textoItem = item.toString().toLowerCase();
            return textoItem.contains(filtro.toLowerCase());
        });

        comboBox.setItems(listaFiltrada);
        comboBox.show();
    }

    private void carregarFuncionarios() {
        listaFuncionarios.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM funcionario")) {
            while (rs.next()) {
                listaFuncionarios.add(new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome")
                ));
            }
            cmbFuncionario.setItems(listaFuncionarios);
    
            cmbFuncionario.setConverter(new StringConverter<Funcionario>() {
                public String toString(Funcionario funcionario) {
                    return funcionario != null ? funcionario.getNome() : "";
                }
    
                public Funcionario fromString(String nomeDigitado) {
                    for (Funcionario f : listaFuncionarios) {
                        if (f.getNome().equalsIgnoreCase(nomeDigitado.trim())) {
                            return f;
                        }
                    }
                    return null; // se não encontrou
                }
            });
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar os funcionários.");
            AlertUtils.logError(e);
        }
    }
    
    private void carregarMaquinas() {
        listaMaquinas.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM equipamentos")) {
            
            while (rs.next()) {
                listaMaquinas.add(new Maquina(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("setor"),
                    rs.getString("categoria")
                ));
            }
            cmbMaquina.setItems(listaMaquinas);
    
            cmbMaquina.setConverter(new StringConverter<Maquina>() {
                @Override
                public String toString(Maquina maquina) {
                    return maquina != null ? maquina.getNome() : "";
                }
    
                @Override
                public Maquina fromString(String nomeDigitado) {
                    for (Maquina m : listaMaquinas) {
                        if (m.getNome().equalsIgnoreCase(nomeDigitado.trim())) {
                            return m;
                        }
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar as máquinas.");
            AlertUtils.logError(e);
        }
    }
    
    private void carregarProdutos() {
        listaProdutos.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM produto")) {
            
            while (rs.next()) {
                listaProdutos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("lote"),
                    rs.getString("sku")
                ));
            }
            cmbProduto.setItems(listaProdutos);

            cmbProduto.setConverter(new StringConverter<Produto>() {
                @Override
                public String toString(Produto produto) {
                    return produto != null ? produto.getNome() : "";
                }
    
                @Override
                public Produto fromString(String nomeDigitado) {
                    for (Produto p : listaProdutos) {
                        if (p.getNome().equalsIgnoreCase(nomeDigitado.trim())) {
                            return p;
                        }
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar os produtos.");
            AlertUtils.logError(e);
        }
    }
    
    @FXML
    public void registrarProducao() {
        // Obtém os valores das comboboxes e do campo de texto
        Funcionario funcionario = cmbFuncionario.getValue();
        Maquina maquina = cmbMaquina.getValue();
        Produto produto = cmbProduto.getValue();
        String quantidadeTexto = txtQuantidade.getText().trim();

        // Validação dos campos
        if (funcionario == null || maquina == null || produto == null || quantidadeTexto.isEmpty()) {
            AlertUtils.showAlert(AlertType.AVISO, "Campos Incompletos", "Preencha todos os campos obrigatórios.");
            return;
        }

        try {
            int quantidade = Integer.parseInt(quantidadeTexto);

            try (Connection conn = Database.getConnection()) {
                String sql;
                PreparedStatement stmt;

                if (emEdicao && producaoEmEdicao != null) {
                    // Atualizar produção existente
                    sql = "UPDATE producao SET funcionario = ?, maquina = ?, produto = ?, quantidade = ? WHERE id = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, funcionario.getNome());
                    stmt.setString(2, maquina.getNome());
                    stmt.setString(3, produto.getNome());
                    stmt.setInt(4, quantidade);
                    stmt.setInt(5, producaoEmEdicao.getId());

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        AlertUtils.showAlert(AlertType.SUCESSO, "Produção Atualizada", "Produção atualizada com sucesso!");
                    } else {
                        AlertUtils.showAlert(AlertType.AVISO, "Nenhuma Alteração", "Nenhuma linha foi alterada.");
                    }
                } else {
                    // Inserir nova produção
                    sql = "INSERT INTO producao (funcionario, maquina, produto, quantidade, data_producao) VALUES (?, ?, ?, ?, ?)";
                    stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, funcionario.getNome());
                    stmt.setString(2, maquina.getNome());
                    stmt.setString(3, produto.getNome());
                    stmt.setInt(4, quantidade);
                    stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        AlertUtils.showAlert(AlertType.SUCESSO, "Produção Registrada", "Produção registrada com sucesso!");
                    } else {
                        AlertUtils.showAlert(AlertType.AVISO, "Falha", "Nenhuma produção foi registrada.");
                    }
                }
                atualizarCampos();
                carregarProducoes();
                resetarCampos();
                emEdicao = false;
                producaoEmEdicao = null;

            } catch (SQLException e) {
                AlertUtils.showAlert(AlertType.ERRO, "Erro de Banco", "Não foi possível registrar ou atualizar a produção.");
                AlertUtils.logError(e);
            }

        } catch (NumberFormatException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Quantidade Inválida", "Digite uma quantidade válida (número inteiro).");
        }
    }
    
    @FXML
    public void editarProducao() {
        Producao producaoSelecionada = tableProducao.getSelectionModel().getSelectedItem();

        if (producaoSelecionada == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione uma produção para editar.");
            return;
        }

        Funcionario funcionarioSelecionado = listaFuncionarios.stream()
            .filter(f -> f.getNome().equalsIgnoreCase(producaoSelecionada.getNomeFuncionario()))
            .findFirst()
            .orElse(null);
    
        Maquina maquinaSelecionada = listaMaquinas.stream()
            .filter(m -> m.getNome().equalsIgnoreCase(producaoSelecionada.getNomeMaquina()))
            .findFirst()
            .orElse(null);
        
        Produto produtoSelecionado = listaProdutos.stream()
            .filter(p -> p.getNome().equalsIgnoreCase(producaoSelecionada.getNomeProduto()))
            .findFirst()
            .orElse(null);
        
        cmbFuncionario.setValue(funcionarioSelecionado);
        cmbMaquina.setValue(maquinaSelecionada);
        cmbProduto.setValue(produtoSelecionado);
        txtQuantidade.setText(String.valueOf(producaoSelecionada.getQuantidade()));

        this.emEdicao = true;
        this.producaoEmEdicao = producaoSelecionada;
    }

    @FXML
    public void excluirProducao() {
        Producao producaoSelecionada = tableProducao.getSelectionModel().getSelectedItem();

        if (producaoSelecionada == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione uma produção para excluir.");
            return;
        }

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM producao WHERE id = ?")) {

            stmt.setInt(1, producaoSelecionada.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                AlertUtils.showAlert(AlertType.SUCESSO, "Produção Excluída", "Produção excluída com sucesso!");
                carregarProducoes();
            }
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Exclusão", "Não foi possível excluir a produção.");
            AlertUtils.logError(e);
        }
    }

    @FXML
    public void filtrarProducoes() {
        FilteredList<Producao> dadosFiltrados = new FilteredList<>(listaProducao, p -> true);

        dadosFiltrados.setPredicate(producao -> {
            String filtroTexto = filtroNome.getText().toLowerCase();

            if (filtroTexto.isEmpty()) return true;

            return producao.getNomeFuncionario().toLowerCase().contains(filtroTexto) ||
                   producao.getNomeMaquina().toLowerCase().contains(filtroTexto) ||
                   producao.getNomeProduto().toLowerCase().contains(filtroTexto);
        });

        tableProducao.setItems(dadosFiltrados);
    }

    private void carregarProducoes() {
        listaProducao.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT p.id, p.quantidade, p.data_producao, " +
                 "p.funcionario AS funcionario_nome, " +
                 "p.maquina AS maquina_nome, " +
                 "p.produto AS produto_nome " +
                 "FROM producao p"
             )) {
            while (rs.next()) {
                listaProducao.add(new Producao(
                    rs.getInt("id"),
                    rs.getString("funcionario_nome"),
                    rs.getString("maquina_nome"),
                    rs.getString("produto_nome"),
                    rs.getInt("quantidade"),
                    rs.getTimestamp("data_producao").toLocalDateTime()
                ));
            }
            System.out.println("Total de produções carregadas: " + listaProducao.size());
            tableProducao.setItems(listaProducao);
        } catch (SQLException e) {
            AlertUtils.logError(e);
        }
    }

    private void resetarCampos() {
        
        carregarFuncionarios();
        carregarMaquinas();
        carregarProdutos();
        carregarProducoes();

        
        cmbFuncionario.setItems(listaFuncionarios);
        cmbFuncionario.setValue(null);
        cmbFuncionario.getEditor().clear();

        
        cmbMaquina.setItems(listaMaquinas);
        cmbMaquina.setValue(null);
        cmbMaquina.getEditor().clear();

        
        cmbProduto.setItems(listaProdutos);
        cmbProduto.setValue(null);
        cmbProduto.getEditor().clear();

        
        txtQuantidade.clear();

        
        filtroNome.clear();

        
        emEdicao = false;
        producaoEmEdicao = null;
    }

    private void atualizarCampos() {
        carregarFuncionarios();
        carregarMaquinas();
        carregarProdutos();
        carregarProducoes();
    }

    
    public static ProducaoController getInstance() {
        return instance;
    }

    
    public void atualizarFuncionarios() {
        carregarFuncionarios();
        cmbFuncionario.setItems(listaFuncionarios);
    }

    
    public void atualizarProdutos() {
        carregarProdutos();
        cmbProduto.setItems(listaProdutos);
    }
}