package com.example.controllers;

import com.example.database.Database;
import com.example.models.Produto;
import com.example.utils.AlertUtils;
import com.example.utils.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.Optional;

public class ProdutoController {
    @FXML private TextField txtProdutoNome;
    @FXML private TextField txtProdutoLote;
    @FXML private TextField txtProdutoSku;
    @FXML private TextField filtroNome;
    @FXML private TableView<Produto> tableProdutos;
    @FXML private TableColumn<Produto, Integer> colProdutoId;
    @FXML private TableColumn<Produto, String> colProdutoNome;
    @FXML private TableColumn<Produto, String> colProdutoLote;
    @FXML private TableColumn<Produto, String> colProdutoSku;

    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    
    // Variáveis de controle de edição
    private boolean emEdicao = false;
    private Produto produtoEmEdicao = null;

    @FXML
    public void initialize() {
        configurarColunas();
        carregarProdutos();
    }

    private void configurarColunas() {
        colProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colProdutoLote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        colProdutoSku.setCellValueFactory(new PropertyValueFactory<>("sku"));
    }

    @FXML
    public void salvarProduto() {
        String nome = txtProdutoNome.getText().trim();
        String lote = txtProdutoLote.getText().trim();
        String sku = txtProdutoSku.getText().trim();

        if (nome.isEmpty() || lote.isEmpty() || sku.isEmpty()) {
            AlertUtils.showAlert(AlertType.AVISO, "Campos Incompletos", "Preencha todos os campos obrigatórios.");
            return;
        }

        try (Connection conn = Database.getConnection()) {
            String sql;
            PreparedStatement stmt;

            if (emEdicao && produtoEmEdicao != null) {
                // Lógica de ATUALIZAÇÃO
                sql = "UPDATE produto SET nome = ?, lote = ?, sku = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, lote);
                stmt.setString(3, sku);
                stmt.setInt(4, produtoEmEdicao.getId());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    AlertUtils.showAlert(AlertType.SUCESSO, "Sucesso", "Produto atualizado com sucesso!");
                }
            } else {
                // Lógica de INSERÇÃO
                sql = "INSERT INTO produto (nome, lote, sku) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, nome);
                stmt.setString(2, lote);
                stmt.setString(3, sku);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    AlertUtils.showAlert(AlertType.SUCESSO, "Sucesso", "Produto cadastrado com sucesso!");
                }
            }

            // Recarregar a lista de produtos
            carregarProdutos();

            // Limpar campos
            limparCampos();

            // Notifica o ProducaoController para recarregar a combobox de produtos
            ProducaoController.getInstance().atualizarProdutos();

        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Banco", "Não foi possível salvar/atualizar o produto.");
            AlertUtils.logError(e);
        }
    }

    @FXML
    public void editarProduto() {
        Produto produtoSelecionado = tableProdutos.getSelectionModel().getSelectedItem();

        if (produtoSelecionado == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione um produto para editar.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação de Edição");
        confirmacao.setHeaderText("Deseja editar o produto selecionado?");
        confirmacao.setContentText("Nome: " + produtoSelecionado.getNome());

        Optional<ButtonType> resultado = confirmacao.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Preenche os campos com os dados do produto selecionado
            txtProdutoNome.setText(produtoSelecionado.getNome());
            txtProdutoLote.setText(produtoSelecionado.getLote());
            txtProdutoSku.setText(produtoSelecionado.getSku());

            // Configura modo de edição
            this.emEdicao = true;
            this.produtoEmEdicao = produtoSelecionado;
        }
    }

    @FXML
    public void excluirProduto() {
        Produto produtoSelecionado = tableProdutos.getSelectionModel().getSelectedItem();
        
        if (produtoSelecionado == null) {
            AlertUtils.showAlert(AlertType.AVISO, "Seleção Necessária", "Selecione um produto para excluir.");
            return;
        }
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM produto WHERE id = ?")) {
            stmt.setInt(1, produtoSelecionado.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                AlertUtils.showAlert(AlertType.SUCESSO, "Produto Excluído", "Produto excluído com sucesso!");
                carregarProdutos();
            }
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Exclusão", "Não foi possível excluir o produto.");
            AlertUtils.logError(e);
        }
    }

    @FXML
    public void filtrarProdutos() {
        FilteredList<Produto> dadosFiltrados = new FilteredList<>(listaProdutos, p -> true);
        
        dadosFiltrados.setPredicate(produto -> {
            String filtroTexto = filtroNome.getText().toLowerCase();
            
            if (filtroTexto.isEmpty()) return true;
            
            return produto.getNome().toLowerCase().contains(filtroTexto) ||
                   produto.getLote().toLowerCase().contains(filtroTexto) ||
                   produto.getSku().toLowerCase().contains(filtroTexto);
        });
        
        tableProdutos.setItems(dadosFiltrados);
    }

    @FXML
    public void limparFiltro() {
        filtroNome.clear();
        tableProdutos.setItems(listaProdutos);
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
            
            tableProdutos.setItems(listaProdutos);
        } catch (SQLException e) {
            AlertUtils.showAlert(AlertType.ERRO, "Erro de Carregamento", "Não foi possível carregar os produtos.");
            AlertUtils.logError(e);
        }
    }

    private void limparCampos() {
        txtProdutoNome.clear();
        txtProdutoLote.clear();
        txtProdutoSku.clear();
        emEdicao = false;
        produtoEmEdicao = null;
    }
}