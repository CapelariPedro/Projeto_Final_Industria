package com.example.controllers;

import com.example.database.Database;
import com.example.models.Emprestimo;
import com.example.models.Equipamento;
import com.example.models.FuncionarioManutencao;
import com.example.models.Manutencao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public class EquipamentoController {
    // Campos da aba de cadastro
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNome;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private TextField txtModelo;
    @FXML private TextField txtNumeroSerie;
    @FXML private ComboBox<String> cmbSetor;
    @FXML private DatePicker dtAquisicao;
    @FXML private TextField txtValorAquisicao;
    @FXML private CheckBox chkManutencaoPeriodica;

    // Campos da aba de manutenção
    @FXML private ComboBox<Equipamento> cmbEquipamentoManutencao;
    @FXML private ComboBox<String> cmbTipoManutencao;
    @FXML private DatePicker dtInicioManutencao;
    @FXML private DatePicker dtConclusaoManutencao;
    @FXML private TextArea txtDescricaoServico;
    @FXML private TableView<Manutencao> tblManutencoes;
    @FXML private TableColumn<Manutencao, String> colManutencaoEquipamento;
    @FXML private TableColumn<Manutencao, String> colManutencaoTipo;
    @FXML private TableColumn<Manutencao, Date> colManutencaoDataInicio;
    @FXML private TableColumn<Manutencao, Date> colManutencaoDataConclusao;
    @FXML private TableColumn<Manutencao, String> colManutencaoStatus;
    @FXML private TableColumn<Manutencao, String> colManutencaoObservacoes;

    // Campos da aba de empréstimo
    @FXML private ComboBox<Equipamento> cmbEquipamentoEmprestimo;
    @FXML private ComboBox<String> cmbSetorSolicitante;
    @FXML private ComboBox<FuncionarioManutencao> cmbFuncionario;
    @FXML private DatePicker dtInicioEmprestimo;
    @FXML private DatePicker dtDevolucaoEmprestimo;
    @FXML private TextArea txtObservacoesEmprestimo;
    @FXML private TableView<Emprestimo> tblEmprestimos;
    @FXML private TableColumn<Emprestimo, String> colEmprestimoEquipamento;
    @FXML private TableColumn<Emprestimo, String> colEmprestimoSetor;
    @FXML private TableColumn<Emprestimo, String> colEmprestimoFuncionario;
    @FXML private TableColumn<Emprestimo, Date> colEmprestimoDataInicio;
    @FXML private TableColumn<Emprestimo, Date> colEmprestimoDataDevolucao;
    @FXML private TableColumn<Emprestimo, String> colEmprestimoStatus;
    @FXML private TableColumn<Emprestimo, String> colEmprestimoObservacoes;
   
    // Campos da aba de listagem
    @FXML private TableView<Equipamento> tblEquipamentos;
    @FXML private TableColumn<Equipamento, String> colCodigo;
    @FXML private TableColumn<Equipamento, String> colNome;
    @FXML private TableColumn<Equipamento, String> colCategoria;
    @FXML private TableColumn<Equipamento, String> colModelo;
    @FXML private TableColumn<Equipamento, String> colNumeroSerie;
    @FXML private TableColumn<Equipamento, String> colSetor;
    @FXML private TableColumn<Equipamento, Date> colDataAquisicao;
    @FXML private TableColumn<Equipamento, Double> colValorAquisicao;
    @FXML private TableColumn<Equipamento, String> colStatus;
    @FXML private TableColumn<Equipamento, Boolean> colManutencaoPeriodica;
    @FXML private TableColumn<Equipamento, String> colObservacoes;
    @FXML private ComboBox<String> cmbFiltro;
    @FXML private TextField txtFiltro;
    @FXML private Button btnEditarSelecionado;

    private ObservableList<Equipamento> listaEquipamentos = FXCollections.observableArrayList();
    private ObservableList<Manutencao> listaManutencoes = FXCollections.observableArrayList();
    private ObservableList<Emprestimo> listaEmprestimos = FXCollections.observableArrayList();
    private FilteredList<Equipamento> listaFiltrada;

    @FXML
    public void initialize() {
        // Configuração da tabela de equipamentos
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colNumeroSerie.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
        colSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
        colDataAquisicao.setCellValueFactory(new PropertyValueFactory<>("dataAquisicao"));
        colValorAquisicao.setCellValueFactory(new PropertyValueFactory<>("valorAquisicao"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colManutencaoPeriodica.setCellValueFactory(new PropertyValueFactory<>("manutencaoPeriodica"));
        colObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));

        // Configurar coluna de manutenção periódica para exibir "Sim" ou "Não"
        colManutencaoPeriodica.setCellFactory(column -> new TableCell<Equipamento, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Sim" : "Não");
                }
            }
        });

        // Configuração da tabela de manutenções
        colManutencaoEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamentoNome"));
        colManutencaoTipo.setCellValueFactory(new PropertyValueFactory<>("tipoManutencao"));
        colManutencaoDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colManutencaoDataConclusao.setCellValueFactory(new PropertyValueFactory<>("dataConclusao"));
        colManutencaoStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colManutencaoObservacoes.setCellValueFactory(new PropertyValueFactory<>("descricaoServico"));

        // Configuração da tabela de empréstimos
        colEmprestimoEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamentoNome"));
        colEmprestimoSetor.setCellValueFactory(new PropertyValueFactory<>("setorSolicitante"));
        colEmprestimoFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionarioNome"));
        colEmprestimoDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colEmprestimoDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        colEmprestimoStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEmprestimoObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));

        // Desativar edição direta na tabela
        tblEquipamentos.setEditable(false);

        // Configuração dos comboboxes
        cmbCategoria.getItems().addAll("Acabamento", "Eletrônica", "Ferramenta de Conformação", "Ferramenta de Corte","Ferramenta de Medição","Ferramenta Manual", "Furadeira","Instrumentação","Montagem","Pintura Industrial","Pneumático","Solda", "Outros");
        cmbFiltro.getItems().addAll("Código", "Nome", "Modelo", "Setor", "Categoria");
        cmbTipoManutencao.getItems().addAll("Conserto");
        
        // Configurar lista de setores para ComboBox
        cmbSetor.getItems().addAll("Acabamento", "Almoxarifado", "Automação", "Controle de Qualidade", 
                                 "Financeiro", "Manutenção","Montagem","Pintura", "Produção", "RH");

        // Agora carregue os setores do RH apenas para o combobox de empréstimos
        carregarSetoresDoRH();

        // Adicionar listener para quando o setor for selecionado
        cmbSetorSolicitante.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                carregarFuncionariosPorSetor(newVal);
            }
        });

        // Configurar políticas de redimensionamento
        tblEquipamentos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblManutencoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblEmprestimos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Configurar formatadores de data para todos os DatePickers
        configurarDatePickers();

        // Carregar dados
        carregarEquipamentos();
        carregarEquipamentosDisponiveis();
        carregarManutencoes();
        carregarEmprestimos();

        // Configuração do filtro
        listaFiltrada = new FilteredList<>(listaEquipamentos, p -> true);
        tblEquipamentos.setItems(listaFiltrada);
        
        // Adicionar listeners para seleção de equipamentos
        cmbEquipamentoManutencao.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                onEquipamentoSelecionadoManutencao();
            }
        });

        // Configurar estilo para destacar manutenções atrasadas
        tblManutencoes.setRowFactory(tv -> new TableRow<Manutencao>() {
            @Override
            protected void updateItem(Manutencao manutencao, boolean empty) {
                super.updateItem(manutencao, empty);
                
                if (manutencao == null || empty) {
                    setStyle("");
                } else if (manutencao.getStatus().equals("Atrasada")) {
                    setStyle("-fx-background-color: #ffcccc;"); // Fundo vermelho claro para itens atrasados
                } else {
                    setStyle("");
                }
            }
        });

        // Configurar estilo para destacar empréstimos atrasados
        tblEmprestimos.setRowFactory(tv -> new TableRow<Emprestimo>() {
            @Override
            protected void updateItem(Emprestimo emprestimo, boolean empty) {
                super.updateItem(emprestimo, empty);
                
                if (emprestimo == null || empty) {
                    setStyle("");
                } else if (emprestimo.getStatus().equals("atrasado")) {
                    setStyle("-fx-background-color: #ffcccc;"); // Fundo vermelho claro para itens atrasados
                } else {
                    setStyle("");
                }
            }
        });

        // Verificar atrasos ao inicializar
        verificarAtrasosManutencaoEEmprestimo();
        
        // Configurar uma tarefa agendada para verificar atrasos periodicamente (a cada 5 minutos)
        Timeline verificadorAtrasos = new Timeline(
            new KeyFrame(Duration.minutes(5), e -> verificarAtrasosManutencaoEEmprestimo())
        );
        verificadorAtrasos.setCycleCount(Timeline.INDEFINITE);
        verificadorAtrasos.play();
    }

    /**
 * Carrega os setores/departamentos disponíveis no banco de RH
 */
    private void carregarSetoresDoRH() {
        try (Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT DISTINCT departamento FROM dadosprofissionais ORDER BY departamento")) {
            
            ObservableList<String> setores = FXCollections.observableArrayList();
            while (rs.next()) {
                setores.add(rs.getString("departamento"));
            }
            
            // Alimentar o ComboBox de setores na aba de empréstimos
            cmbSetorSolicitante.setItems(setores);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar setores do RH: " + e.getMessage());
        }
    }


    /**
     * Carrega os funcionários do departamento selecionado
     * @param departamento O departamento selecionado
     */
    private void carregarFuncionariosPorSetor(String departamento) {
        cmbFuncionario.getItems().clear();
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT dp.cpf, dp.nome_completo, dprof.departamento, dprof.cargo " +
                 "FROM dadospessoais dp " +
                 "JOIN dadosprofissionais dprof ON dp.cpf = dprof.dados_pessoais " +
                 "WHERE dprof.departamento = ? " +
                 "ORDER BY dp.nome_completo")) {
            
            stmt.setString(1, departamento);
            ResultSet rs = stmt.executeQuery();
            
            ObservableList<FuncionarioManutencao> funcionarios = FXCollections.observableArrayList();
            while (rs.next()) {
                FuncionarioManutencao funcionario = new FuncionarioManutencao(
                    rs.getString("cpf"),
                    rs.getString("nome_completo"),
                    rs.getString("departamento"),
                    rs.getString("cargo")
                );
                funcionarios.add(funcionario);
            }
            
            cmbFuncionario.setItems(funcionarios);
            cmbFuncionario.setConverter(new StringConverter<FuncionarioManutencao>() {
                @Override
                public String toString(FuncionarioManutencao funcionario) {
                    if (funcionario == null) return "";
                    return funcionario.getNomeCompleto() + " - " + funcionario.getCargo();
                }
                
                @Override
                public FuncionarioManutencao fromString(String string) {
                    return null; // Não necessário para este caso
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    /**
     * Verifica e atualiza o status de manutenções e empréstimos em atraso no banco de dados
     */
    private void verificarAtrasosManutencaoEEmprestimo() {
        try (Connection conn = Database.getConnection()) {
            // Atualizar status de manutenções atrasadas
            String sqlManutencao = "UPDATE manutencoes SET status = 'Atrasada' WHERE " +
                                  "data_conclusao < CURRENT_DATE() AND status = 'Em Manutenção'";
            
            try (PreparedStatement stmt = conn.prepareStatement(sqlManutencao)) {
                int registrosAtualizados = stmt.executeUpdate();
                if (registrosAtualizados > 0) {
                    System.out.println(registrosAtualizados + " manutenções marcadas como atrasadas");
                }
            }
            
            // Atualizar status de empréstimos atrasados
            String sqlEmprestimo = "UPDATE emprestimos SET status = 'atrasado' WHERE " +
                                   "data_devolucao < CURRENT_DATE() AND status = 'no prazo'";
            
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprestimo)) {
                int registrosAtualizados = stmt.executeUpdate();
                if (registrosAtualizados > 0) {
                    System.out.println(registrosAtualizados + " empréstimos marcados como atrasados");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Falha ao verificar atrasos: " + e.getMessage());
        }
    }

    private void configurarDatePickers() {
        // Define um conversor mais robusto para datas no formato brasileiro
        StringConverter<LocalDate> dateConverter = new StringConverter<>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    // Verificar padrão brasileiro DD/MM/AAAA
                    if (string.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                        try {
                            String[] parts = string.split("/");
                            int day = Integer.parseInt(parts[0]);
                            int month = Integer.parseInt(parts[1]);
                            int year = Integer.parseInt(parts[2]);
                            
                            // Validar valores de dia, mês e ano
                            if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                                return LocalDate.of(year, month, day);
                            } else {
                                mostrarAlerta(AlertType.ERROR, "Erro de Data", "Data inválida: verifique dia e mês.");
                                return null;
                            }
                        } catch (Exception e) {
                            mostrarAlerta(AlertType.ERROR, "Erro de Data", "Formato de data inválido. Use dd/mm/aaaa.");
                            return null;
                        }
                    } else {
                        // Tenta usar o parser padrão como fallback
                        try {
                            return LocalDate.parse(string, dateFormatter);
                        } catch (Exception e) {
                            mostrarAlerta(AlertType.ERROR, "Erro de Data", "Formato de data inválido. Use dd/mm/aaaa.");
                            return null;
                        }
                    }
                }
                return null;
            }
        };

        // Aplicar o conversor a todos DatePickers
        dtAquisicao.setConverter(dateConverter);
        dtInicioManutencao.setConverter(dateConverter);
        dtConclusaoManutencao.setConverter(dateConverter);
        dtInicioEmprestimo.setConverter(dateConverter);
        dtDevolucaoEmprestimo.setConverter(dateConverter);
        
        // Define o padrão de exibição
        dtAquisicao.setPromptText("dd/mm/aaaa");
        dtInicioManutencao.setPromptText("dd/mm/aaaa");
        dtConclusaoManutencao.setPromptText("dd/mm/aaaa");
        dtInicioEmprestimo.setPromptText("dd/mm/aaaa");
        dtDevolucaoEmprestimo.setPromptText("dd/mm/aaaa");

        // Adicionar listeners para formatação imediata após entrada manual
        dtAquisicao.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // quando perde o foco
                try {
                    LocalDate date = dtAquisicao.getConverter().fromString(dtAquisicao.getEditor().getText());
                    dtAquisicao.setValue(date);
                } catch (Exception e) {
                    // Tratamento de erro já feito no conversor
                }
            }
        });
        
        // Aplicar o mesmo listener para os outros DatePickers
        dtInicioManutencao.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    LocalDate date = dtInicioManutencao.getConverter().fromString(dtInicioManutencao.getEditor().getText());
                    dtInicioManutencao.setValue(date);
                } catch (Exception e) {}
            }
        });
        
        dtConclusaoManutencao.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    LocalDate date = dtConclusaoManutencao.getConverter().fromString(dtConclusaoManutencao.getEditor().getText());
                    dtConclusaoManutencao.setValue(date);
                } catch (Exception e) {}
            }
        });
        
        dtInicioEmprestimo.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    LocalDate date = dtInicioEmprestimo.getConverter().fromString(dtInicioEmprestimo.getEditor().getText());
                    dtInicioEmprestimo.setValue(date);
                } catch (Exception e) {}
            }
        });
        
        dtDevolucaoEmprestimo.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    LocalDate date = dtDevolucaoEmprestimo.getConverter().fromString(dtDevolucaoEmprestimo.getEditor().getText());
                    dtDevolucaoEmprestimo.setValue(date);
                } catch (Exception e) {}
            }
        });
    }

    // Métodos para a aba de Cadastro de Equipamento
    
    @FXML
    public void salvarEquipamento() {
        // Validação dos campos
        if (txtCodigo.getText().isEmpty() || txtNome.getText().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Código e Nome são campos obrigatórios!");
            return;
        }
        
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String categoria = cmbCategoria.getValue();
        String modelo = txtModelo.getText();
        String numeroSerie = txtNumeroSerie.getText();
        String setor = cmbSetor.getValue();
        LocalDate dataAquisicao = dtAquisicao.getValue();
        double valorAquisicao;
        try {
            String valorStr = txtValorAquisicao.getText().replace(',', '.');
            valorAquisicao = Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            mostrarAlerta(AlertType.ERROR, "Erro", "Valor de aquisição inválido.");
            return;
        }
        boolean manutencaoPeriodica = chkManutencaoPeriodica.isSelected();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO equipamentos (codigo, nome, categoria, modelo, numero_serie, setor, data_aquisicao, valor_aquisicao, status, manutencao_periodica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, codigo);
            stmt.setString(2, nome);
            stmt.setString(3, categoria);
            stmt.setString(4, modelo);
            stmt.setString(5, numeroSerie);
            stmt.setString(6, setor);
            stmt.setDate(7, dataAquisicao != null ? java.sql.Date.valueOf(dataAquisicao) : null);
            stmt.setDouble(8, valorAquisicao);
            stmt.setString(9, "disponível");
            stmt.setBoolean(10, manutencaoPeriodica);
            stmt.executeUpdate();

            carregarEquipamentos();
            carregarEquipamentosDisponiveis();
            mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Equipamento salvo com sucesso!");
            limparCampos();
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao salvar o equipamento: " + e.getMessage());
        }
    }

    @FXML
    public void limparCampos() {
        txtCodigo.clear();
        txtNome.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        txtModelo.clear();
        txtNumeroSerie.clear();
        cmbSetor.getSelectionModel().clearSelection();
        dtAquisicao.setValue(null);
        txtValorAquisicao.clear();
        chkManutencaoPeriodica.setSelected(false);
    }

    // Métodos para a aba de Manutenção
    
    private void carregarEquipamentosDisponiveis() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, codigo, nome, manutencao_periodica FROM equipamentos WHERE status = 'disponível'")) {
            
            ObservableList<Equipamento> equipamentosDisponiveis = FXCollections.observableArrayList();
            while (rs.next()) {
                Equipamento equip = new Equipamento(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nome"),
                    "", "", "", "", null, 0,
                    "disponível",
                    rs.getBoolean("manutencao_periodica")
                );
                equipamentosDisponiveis.add(equip);
            }
            
            cmbEquipamentoManutencao.setItems(equipamentosDisponiveis);
            cmbEquipamentoManutencao.setConverter(new StringConverter<Equipamento>() {
                @Override
                public String toString(Equipamento equip) {
                    return equip != null ? equip.getCodigo() + " - " + equip.getNome() : "";
                }

                @Override
                public Equipamento fromString(String string) {
                    return null; // Não necessário para este caso
                }
            });
            
            cmbEquipamentoEmprestimo.setItems(equipamentosDisponiveis);
            cmbEquipamentoEmprestimo.setConverter(new StringConverter<Equipamento>() {
                @Override
                public String toString(Equipamento equip) {
                    return equip != null ? equip.getCodigo() + " - " + equip.getNome() : "";
                }

                @Override
                public Equipamento fromString(String string) {
                    return null; // Não necessário para este caso
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar equipamentos disponíveis: " + e.getMessage());
        }
    }

    private void carregarManutencoes() {
        // Verificar atrasos antes de carregar
        verificarAtrasosManutencaoEEmprestimo();
        
        listaManutencoes.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT m.*, e.nome as equipamento_nome " +
                 "FROM manutencoes m " +
                 "JOIN equipamentos e ON m.equipamento_id = e.id " +
                 "ORDER BY m.data_inicio DESC")) {
            
            while (rs.next()) {
                Manutencao manutencao = new Manutencao(
                    rs.getInt("id"),
                    rs.getInt("equipamento_id"),
                    rs.getString("equipamento_nome"),
                    rs.getString("tipo_manutencao"),
                    rs.getDate("data_inicio"),
                    rs.getDate("data_conclusao"),
                    rs.getString("status"),
                    rs.getString("descricao_servico")
                );
                listaManutencoes.add(manutencao);
            }
            tblManutencoes.setItems(listaManutencoes);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar manutenções: " + e.getMessage());
        }
    }

    @FXML
    public void onEquipamentoSelecionadoManutencao() {
        Equipamento equipSelecionado = cmbEquipamentoManutencao.getValue();
        if (equipSelecionado != null) {
            // Limpar combobox de tipos de manutenção
            cmbTipoManutencao.getItems().clear();
            
            // Sempre adicionar opção de Conserto (para todos os equipamentos)
            cmbTipoManutencao.getItems().add("Conserto");
            cmbTipoManutencao.setValue("Conserto"); // Define um valor padrão
            
            // Adicionar Manutenção Periódica apenas se o equipamento requer
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT manutencao_periodica FROM equipamentos WHERE id = ?")) {
                
                stmt.setInt(1, equipSelecionado.getId());
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next() && rs.getBoolean("manutencao_periodica")) {
                    cmbTipoManutencao.getItems().add("Manutenção Periódica");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao verificar tipo de manutenção: " + e.getMessage());
            }
        }
    }

    @FXML
    public void registrarManutencao() {
        Equipamento equipamentoSelecionado = cmbEquipamentoManutencao.getValue();
        String tipoManutencao = cmbTipoManutencao.getValue();
        LocalDate dataInicio = dtInicioManutencao.getValue();
        LocalDate dataConclusao = dtConclusaoManutencao.getValue();
        String descricaoServico = txtDescricaoServico.getText();

        // Validação
        if (equipamentoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um equipamento.");
            return;
        }
        
        if (tipoManutencao == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione o tipo de manutenção.");
            return;
        }
        
        if (dataInicio == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, informe a data de início.");
            return;
        }
        
        if (descricaoServico == null || descricaoServico.trim().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, preencha a descrição do serviço.");
            return;
        }
        
        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);
            
            try {
                // Determinar se a manutenção estará atrasada desde o início
                String status = "Em Manutenção";
                if (dataConclusao != null && dataConclusao.isBefore(LocalDate.now())) {
                    status = "Atrasada";
                }
                
                // Inserir registro de manutenção
                try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO manutencoes (equipamento_id, tipo_manutencao, data_inicio, data_conclusao, status, descricao_servico) VALUES (?, ?, ?, ?, ?, ?)")) {
                    
                    stmt.setInt(1, equipamentoSelecionado.getId());
                    stmt.setString(2, tipoManutencao);
                    stmt.setDate(3, java.sql.Date.valueOf(dataInicio));
                    stmt.setDate(4, dataConclusao != null ? java.sql.Date.valueOf(dataConclusao) : null);
                    stmt.setString(5, status);
                    stmt.setString(6, descricaoServico);
                    stmt.executeUpdate();
                }
                
                // Atualizar o status do equipamento
                try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE equipamentos SET status = ? WHERE id = ?")) {
                    updateStmt.setString(1, "manutenção");
                    updateStmt.setInt(2, equipamentoSelecionado.getId());
                    updateStmt.executeUpdate();
                }
                
                conn.commit();
                mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Manutenção registrada com sucesso!");
                limparCamposManutencao();
                carregarEquipamentos();
                carregarEquipamentosDisponiveis();
                carregarManutencoes();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao registrar manutenção: " + e.getMessage());
        }
    } 

    @FXML
    public void liberarManutencao() {
        Manutencao manutencaoSelecionada = tblManutencoes.getSelectionModel().getSelectedItem();
        if (manutencaoSelecionada == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione uma manutenção para liberar.");
            return;
        }
        
        if (!manutencaoSelecionada.getStatus().equals("Em Manutenção") && !manutencaoSelecionada.getStatus().equals("Atrasada")) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Apenas equipamentos em manutenção podem ser liberados.");
            return;
        }
        
        Alert confirmacao = new Alert(AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Liberação");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja liberar o equipamento " + manutencaoSelecionada.getEquipamentoNome() + " da manutenção?");
        
        if (confirmacao.showAndWait().get() == ButtonType.OK) {
            Connection conn = null;
            
            try {
                conn = Database.getConnection();
                conn.setAutoCommit(false);
                
                try {
                    // Atualizar o status do equipamento
                    try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE equipamentos SET status = ? WHERE id = ?")) {
                        
                        stmt.setString(1, "disponível");
                        stmt.setInt(2, manutencaoSelecionada.getEquipamentoId());
                        stmt.executeUpdate();
                    }
                    
                    // Atualizar o status da manutenção e a data de conclusão
                    try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE manutencoes SET status = ?, data_conclusao = ? WHERE id = ?")) {
                        
                        stmt.setString(1, "Concluída");
                        stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                        stmt.setInt(3, manutencaoSelecionada.getId());
                        stmt.executeUpdate();
                    }
                    
                    conn.commit();
                    mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Equipamento liberado com sucesso!");
                    carregarManutencoes();
                    carregarEquipamentos();
                    carregarEquipamentosDisponiveis();
                } catch (SQLException e) {
                    if (conn != null) {
                        conn.rollback();
                    }
                    throw e;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao liberar equipamento: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void limparCamposManutencao() {
        cmbEquipamentoManutencao.setValue(null);
        cmbTipoManutencao.setValue(null);
        dtInicioManutencao.setValue(null);
        dtConclusaoManutencao.setValue(null);
        txtDescricaoServico.clear();
    }

    // Métodos para a aba de Empréstimo
    
    private void carregarEmprestimos() {
        // Verificar atrasos antes de carregar
        verificarAtrasosManutencaoEEmprestimo();
        
        listaEmprestimos.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT emp.*, e.nome as equipamento_nome " +
                 "FROM emprestimos emp " +
                 "JOIN equipamentos e ON emp.equipamento_id = e.id " +
                 "ORDER BY emp.data_inicio DESC")) {
            
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                    rs.getInt("id"),
                    rs.getInt("equipamento_id"),
                    rs.getString("equipamento_nome"),
                    rs.getString("setor_solicitante"),
                    rs.getString("funcionario_cpf"),
                    rs.getString("funcionario_nome"),
                    rs.getDate("data_inicio"),
                    rs.getDate("data_devolucao"),
                    rs.getString("status"),
                    rs.getString("observacoes")
                );
                listaEmprestimos.add(emprestimo);
            }
            tblEmprestimos.setItems(listaEmprestimos);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar empréstimos: " + e.getMessage());
        }
    }

    @FXML
    public void registrarEmprestimo() {
        Equipamento equipamentoSelecionado = cmbEquipamentoEmprestimo.getValue();
        String setorSolicitante = cmbSetorSolicitante.getValue();
        FuncionarioManutencao funcionarioSelecionado = cmbFuncionario.getValue();
        LocalDate dataInicio = dtInicioEmprestimo.getValue();
        LocalDate dataDevolucao = dtDevolucaoEmprestimo.getValue();
        String observacoes = txtObservacoesEmprestimo.getText();

        // Validação
        if (equipamentoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um equipamento.");
            return;
        }
        
        if (setorSolicitante == null || setorSolicitante.trim().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione o setor solicitante.");
            return;
        }
        
        if (funcionarioSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione o funcionário.");
            return;
        }
        
        if (dataInicio == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, informe a data de início.");
            return;
        }
        
        if (dataDevolucao == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, informe a data de devolução.");
            return;
        }
        
        if (observacoes == null || observacoes.trim().isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "O campo de observações é obrigatório.");
            return;
        }
        
        try (Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);
            
            try {
                // Verificar se o equipamento está disponível
                try (PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT status FROM equipamentos WHERE id = ?")) {
                    
                    checkStmt.setInt(1, equipamentoSelecionado.getId());
                    ResultSet rs = checkStmt.executeQuery();
                    
                    if (rs.next() && !rs.getString("status").equals("disponível")) {
                        mostrarAlerta(AlertType.WARNING, "Aviso", "Este equipamento não está disponível.");
                        return;
                    }
                }
                
                // Determinar se o empréstimo estará atrasado desde o início
                String status = "no prazo";
                if (dataDevolucao.isBefore(LocalDate.now())) {
                    status = "atrasado";
                }
                
                // Registrar empréstimo
                try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO emprestimos (equipamento_id, setor_solicitante, funcionario_cpf, funcionario_nome, data_inicio, data_devolucao, status, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                    
                    stmt.setInt(1, equipamentoSelecionado.getId());
                    stmt.setString(2, setorSolicitante);
                    stmt.setString(3, funcionarioSelecionado.getCpf());
                    stmt.setString(4, funcionarioSelecionado.getNomeCompleto());
                    stmt.setDate(5, java.sql.Date.valueOf(dataInicio));
                    stmt.setDate(6, java.sql.Date.valueOf(dataDevolucao));
                    stmt.setString(7, status);
                    stmt.setString(8, observacoes);
                    stmt.executeUpdate();
                }
                
                // Atualizar o status do equipamento
                try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE equipamentos SET status = ? WHERE id = ?")) {
                    updateStmt.setString(1, "emprestado");
                    updateStmt.setInt(2, equipamentoSelecionado.getId());
                    updateStmt.executeUpdate();
                }
                
                conn.commit();
                mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Empréstimo registrado com sucesso!");
                limparCamposEmprestimo();
                carregarEquipamentos();
                carregarEquipamentosDisponiveis();
                carregarEmprestimos();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao registrar empréstimo: " + e.getMessage());
        }
    }
    @FXML
    public void liberarEmprestimo() {
        Emprestimo emprestimoSelecionado = tblEmprestimos.getSelectionModel().getSelectedItem();
        if (emprestimoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um empréstimo para liberar.");
            return;
        }
        
        if (emprestimoSelecionado.getStatus().equals("concluído")) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Este empréstimo já foi concluído.");
            return;
        }
        
        Alert confirmacao = new Alert(AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Liberação");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja liberar o equipamento " + emprestimoSelecionado.getEquipamentoNome() + " do empréstimo?");
        
        if (confirmacao.showAndWait().get() == ButtonType.OK) {
            Connection conn = null;
            
            try {
                conn = Database.getConnection();
                conn.setAutoCommit(false);
                
                try {
                    // Atualizar o status do equipamento
                    try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE equipamentos SET status = ? WHERE id = ?")) {
                        
                        stmt.setString(1, "disponível");
                        stmt.setInt(2, emprestimoSelecionado.getEquipamentoId());
                        stmt.executeUpdate();
                    }
                    
                    // Atualizar o status do empréstimo
                    try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE emprestimos SET status = ? WHERE id = ?")) {
                        
                        stmt.setString(1, "concluído");
                        stmt.setInt(2, emprestimoSelecionado.getId());
                        stmt.executeUpdate();
                    }
                    
                    conn.commit();
                    mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Equipamento liberado com sucesso!");
                    carregarEmprestimos();
                    carregarEquipamentos();
                    carregarEquipamentosDisponiveis();
                } catch (SQLException e) {
                    if (conn != null) {
                        conn.rollback();
                    }
                    throw e;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao liberar equipamento: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void limparCamposEmprestimo() {
        cmbEquipamentoEmprestimo.setValue(null);
        cmbSetorSolicitante.setValue(null);
        cmbFuncionario.setValue(null);
        dtInicioEmprestimo.setValue(null);
        dtDevolucaoEmprestimo.setValue(null);
        txtObservacoesEmprestimo.clear();
    }

    // Métodos para a aba de Listagem de Equipamentos
    
    private void carregarEquipamentos() {
        listaEquipamentos.clear();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT e.*, " +
                 "(CASE " +
                 "    WHEN e.status = 'manutenção' THEN (" +
                 "        SELECT m.descricao_servico FROM manutencoes m " +
                 "        WHERE m.equipamento_id = e.id AND (m.status = 'Em Manutenção' OR m.status = 'Atrasada') " +
                 "        ORDER BY m.data_inicio DESC LIMIT 1" +
                 "    ) " +
                 "    WHEN e.status = 'emprestado' THEN (" +
                 "        SELECT CONCAT(emp.funcionario_nome, ' (', emp.setor_solicitante, ')') FROM emprestimos emp " +
                 "        WHERE emp.equipamento_id = e.id AND (emp.status = 'no prazo' OR emp.status = 'atrasado') " +
                 "        ORDER BY emp.data_inicio DESC LIMIT 1" +
                 "    ) " +
                 "    ELSE NULL " +
                 "END) as observacoes " +
                 "FROM equipamentos e")) {
            
            while (rs.next()) {
                Equipamento equip = new Equipamento(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nome"),
                    rs.getString("categoria"),
                    rs.getString("modelo"),
                    rs.getString("numero_serie"),
                    rs.getString("setor"),
                    rs.getDate("data_aquisicao"),
                    rs.getDouble("valor_aquisicao"),
                    rs.getString("status"),
                    rs.getBoolean("manutencao_periodica")
                );
                
                // Definir observações
                equip.setObservacoes(rs.getString("observacoes"));
                
                listaEquipamentos.add(equip);
            }
            
            if (listaFiltrada != null) {
                listaFiltrada.setPredicate(p -> true);  // Reset filter
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao carregar equipamentos: " + e.getMessage());
        }
    }

    @FXML
    public void aplicarFiltro() {
        String filtro = txtFiltro.getText().toLowerCase();
        String tipoDeFiltro = cmbFiltro.getValue();

        if (filtro == null || filtro.isEmpty()) {
            listaFiltrada.setPredicate(null);
        } else {
            listaFiltrada.setPredicate(criarPredicadoFiltro(tipoDeFiltro, filtro));
        }
    }

    private Predicate<Equipamento> criarPredicadoFiltro(String tipoDeFiltro, String filtro) {
        return equipamento -> {
            if (tipoDeFiltro == null) return true;
            switch (tipoDeFiltro) {
                case "Código":
                    return equipamento.getCodigo().toLowerCase().contains(filtro);
                case "Nome":
                    return equipamento.getNome().toLowerCase().contains(filtro);
                case "Modelo":
                    return equipamento.getModelo().toLowerCase().contains(filtro);
                case "Setor":
                    return equipamento.getSetor().toLowerCase().contains(filtro);
                case "Categoria":
                    return equipamento.getCategoria().toLowerCase().contains(filtro);
                default:
                    return true;
            }
        };
    }

    @FXML
    public void excluirEquipamento() {
        Equipamento equipamentoSelecionado = tblEquipamentos.getSelectionModel().getSelectedItem();
        if (equipamentoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um equipamento para excluir.");
            return;
        }
        
        // Não permitir exclusão de equipamentos em manutenção ou emprestados
        if (!equipamentoSelecionado.getStatus().equals("disponível")) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Não é possível excluir um equipamento que está em manutenção ou emprestado.");
            return;
        }

        // Confirmar exclusão
        Alert confirmacao = new Alert(AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar Exclusão");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Tem certeza que deseja excluir o equipamento " + equipamentoSelecionado.getNome() + "?");
        
        if (confirmacao.showAndWait().get() == ButtonType.OK) {
            try (Connection conn = Database.getConnection()) {
                // Verificar se tem manutenções ou empréstimos
                try (PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT " +
                    "(SELECT COUNT(*) FROM manutencoes WHERE equipamento_id = ?) as manutencoes, " +
                    "(SELECT COUNT(*) FROM emprestimos WHERE equipamento_id = ?) as emprestimos")) {
                    
                    checkStmt.setInt(1, equipamentoSelecionado.getId());
                    checkStmt.setInt(2, equipamentoSelecionado.getId());
                    ResultSet rs = checkStmt.executeQuery();
                    
                    if (rs.next()) {
                        int manutencoes = rs.getInt("manutencoes");
                        int emprestimos = rs.getInt("emprestimos");
                        
                        if (manutencoes > 0 || emprestimos > 0) {
                            mostrarAlerta(AlertType.WARNING, "Aviso", 
                                "Este equipamento possui histórico de manutenção ou empréstimo e não pode ser excluído.");
                            return;
                        }
                    }
                }
                
                // Proceder com a exclusão
                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM equipamentos WHERE id = ?")) {
                    stmt.setInt(1, equipamentoSelecionado.getId());
                    stmt.executeUpdate();

                    carregarEquipamentos();
                    carregarEquipamentosDisponiveis();
                    
                    mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Equipamento excluído com sucesso!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao excluir o equipamento: " + e.getMessage());
            }
        }
    }

    @FXML
    public void editarEquipamentoSelecionado() {
        Equipamento equipamentoSelecionado = tblEquipamentos.getSelectionModel().getSelectedItem();
        if (equipamentoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um equipamento para editar.");
            return;
        }
        
        // Destaca a linha selecionada
        tblEquipamentos.getSelectionModel().select(equipamentoSelecionado);
        
        // Criar um diálogo personalizado para edição
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Editar Equipamento");
        dialog.setHeaderText("Editar informações do equipamento: " + equipamentoSelecionado.getNome());
        
        // Adicionar botões
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        // Criar grid para campos
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        // Criar campos de edição
        TextField txtEditCodigo = new TextField(equipamentoSelecionado.getCodigo());
        TextField txtEditNome = new TextField(equipamentoSelecionado.getNome());
        ComboBox<String> cmbEditCategoria = new ComboBox<>();
        cmbEditCategoria.getItems().addAll("Acabamento", "Eletrônica", "Ferramenta de Conformação", "Ferramenta de Corte","Ferramenta de Medição","Ferramenta Manual", "Furadeira","Instrumentação","Montagem","Pintura Industrial","Pneumático","Solda", "Outros");
        cmbEditCategoria.setValue(equipamentoSelecionado.getCategoria());
        TextField txtEditModelo = new TextField(equipamentoSelecionado.getModelo());
        TextField txtEditNumeroSerie = new TextField(equipamentoSelecionado.getNumeroSerie());
        ComboBox<String> cmbEditSetor = new ComboBox<>();
        cmbEditSetor.getItems().addAll("Acabamento", "Almoxarifado", "Automação", "Controle de Qualidade", 
                                  "Financeiro", "Manutenção", "Produção", "RH", "Pintura", 
                                  "Montagem", "Teste", "Qualidade");
        cmbEditSetor.setValue(equipamentoSelecionado.getSetor());
        DatePicker dtEditAquisicao = new DatePicker();

        // CORREÇÃO: Converter java.util.Date para LocalDate de forma segura
        if (equipamentoSelecionado.getDataAquisicao() != null) {
            // Converter via Instant e ZoneId (funciona para java.util.Date)
            Date utilDate = equipamentoSelecionado.getDataAquisicao();
            Instant instant = new Date(utilDate.getTime()).toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            dtEditAquisicao.setValue(localDate);
        }
        
        TextField txtEditValorAquisicao = new TextField(String.valueOf(equipamentoSelecionado.getValorAquisicao()).replace('.', ','));
        CheckBox chkEditManutencaoPeriodica = new CheckBox();
        chkEditManutencaoPeriodica.setSelected(equipamentoSelecionado.isManutencaoPeriodica());
        
        // Aplicar o mesmo conversor de data - CORRIGIDO PARA USAR dd/MM/yyyy
        StringConverter<LocalDate> dateConverter = new StringConverter<>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    // Verificar padrão brasileiro DD/MM/AAAA
                    if (string.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                        try {
                            String[] parts = string.split("/");
                            int day = Integer.parseInt(parts[0]);
                            int month = Integer.parseInt(parts[1]);
                            int year = Integer.parseInt(parts[2]);
                            
                            // Validar valores de dia, mês e ano
                            if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                                return LocalDate.of(year, month, day);
                            } else {
                                mostrarAlerta(AlertType.ERROR, "Erro de Data", "Data inválida: verifique dia e mês.");
                                return null;
                            }
                        } catch (Exception e) {
                            mostrarAlerta(AlertType.ERROR, "Erro de Data", "Formato de data inválido. Use dd/mm/aaaa.");
                            return null;
                        }
                    } else {
                        // Tenta usar o parser padrão como fallback
                        try {
                            return LocalDate.parse(string, dateFormatter);
                        } catch (Exception e) {
                            mostrarAlerta(AlertType.ERROR, "Erro de Data", "Formato de data inválido. Use dd/mm/aaaa.");
                            return null;
                        }
                    }
                }
                return null;
            }
        };
        dtEditAquisicao.setConverter(dateConverter);
        dtEditAquisicao.setPromptText("dd/mm/aaaa");
        
        // Adicionar listener para formatação da data ao perder foco
        dtEditAquisicao.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // quando perde o foco
                try {
                    LocalDate date = dtEditAquisicao.getConverter().fromString(dtEditAquisicao.getEditor().getText());
                    dtEditAquisicao.setValue(date);
                } catch (Exception e) {
                    // Tratamento de erro já feito no conversor
                }
            }
        });
        
        // Adicionar campos ao grid
        grid.add(new Label("Código:"), 0, 0);
        grid.add(txtEditCodigo, 1, 0);
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(txtEditNome, 1, 1);
        grid.add(new Label("Categoria:"), 0, 2);
        grid.add(cmbEditCategoria, 1, 2);
        grid.add(new Label("Modelo:"), 0, 3);
        grid.add(txtEditModelo, 1, 3);
        grid.add(new Label("Número de Série:"), 0, 4);
        grid.add(txtEditNumeroSerie, 1, 4);
        grid.add(new Label("Setor:"), 0, 5);
        grid.add(cmbEditSetor, 1, 5);
        grid.add(new Label("Data de Aquisição:"), 0, 6);
        grid.add(dtEditAquisicao, 1, 6);
        grid.add(new Label("Valor de Aquisição (R$):"), 0, 7);
        grid.add(txtEditValorAquisicao, 1, 7);
        grid.add(new Label("Requer Manutenção Periódica:"), 0, 8);
        grid.add(chkEditManutencaoPeriodica, 1, 8);
        
        dialog.getDialogPane().setContent(grid);
        // Processar o resultado
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE equipamentos SET codigo = ?, nome = ?, categoria = ?, modelo = ?, numero_serie = ?, " +
                     "setor = ?, data_aquisicao = ?, valor_aquisicao = ?, manutencao_periodica = ? WHERE id = ?")) {
                
                stmt.setString(1, txtEditCodigo.getText());
                stmt.setString(2, txtEditNome.getText());
                stmt.setString(3, cmbEditCategoria.getValue());
                stmt.setString(4, txtEditModelo.getText());
                stmt.setString(5, txtEditNumeroSerie.getText());
                stmt.setString(6, cmbEditSetor.getValue());
                stmt.setDate(7, dtEditAquisicao.getValue() != null ? 
                             java.sql.Date.valueOf(dtEditAquisicao.getValue()) : null);
                
                double valorAquisicao;
                try {
                    String valorStr = txtEditValorAquisicao.getText().replace(',', '.');
                    valorAquisicao = Double.parseDouble(valorStr);
                } catch (NumberFormatException e) {
                    mostrarAlerta(AlertType.ERROR, "Erro", "Valor de aquisição deve ser numérico.");
                    return;
                }
                stmt.setDouble(8, valorAquisicao);
                stmt.setBoolean(9, chkEditManutencaoPeriodica.isSelected());
                stmt.setInt(10, equipamentoSelecionado.getId());
                
                stmt.executeUpdate();
                
                carregarEquipamentos(); // Atualizar a tabela
                mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Equipamento atualizado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao atualizar equipamento: " + e.getMessage());
            }
        }
    }
    @FXML
    public void mostrarDetalhes() {
        Equipamento equipamentoSelecionado = tblEquipamentos.getSelectionModel().getSelectedItem();
        if (equipamentoSelecionado == null) {
            mostrarAlerta(AlertType.WARNING, "Aviso", "Por favor, selecione um equipamento para ver os detalhes.");
            return;
        }
        
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Código: ").append(equipamentoSelecionado.getCodigo()).append("\n");
        detalhes.append("Nome: ").append(equipamentoSelecionado.getNome()).append("\n");
        detalhes.append("Categoria: ").append(equipamentoSelecionado.getCategoria()).append("\n");
        detalhes.append("Modelo: ").append(equipamentoSelecionado.getModelo()).append("\n");
        detalhes.append("Número de Série: ").append(equipamentoSelecionado.getNumeroSerie()).append("\n");
        detalhes.append("Setor: ").append(equipamentoSelecionado.getSetor()).append("\n");
        detalhes.append("Data de Aquisição: ").append(equipamentoSelecionado.getDataAquisicao()).append("\n");
        detalhes.append("Valor de Aquisição: R$ ").append(String.format("%.2f", equipamentoSelecionado.getValorAquisicao()).replace('.', ',')).append("\n");
        detalhes.append("Status: ").append(equipamentoSelecionado.getStatus()).append("\n");
        detalhes.append("Requer Manutenção Periódica: ").append(equipamentoSelecionado.isManutencaoPeriodica() ? "Sim" : "Não").append("\n");
        
        if (equipamentoSelecionado.getObservacoes() != null && !equipamentoSelecionado.getObservacoes().isEmpty()) {
            detalhes.append("\nObservações: ").append(equipamentoSelecionado.getObservacoes()).append("\n");
        }
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Detalhes do Equipamento");
        alert.setHeaderText(equipamentoSelecionado.getNome());
        alert.setContentText(detalhes.toString());
        alert.showAndWait();
    }
    @FXML
    public void gerarRelatorio() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT COUNT(*) as total, " +
                 "SUM(CASE WHEN status = 'disponível' THEN 1 ELSE 0 END) as disponiveis, " +
                 "SUM(CASE WHEN status = 'manutenção' THEN 1 ELSE 0 END) as em_manutencao, " +
                 "SUM(CASE WHEN status = 'emprestado' THEN 1 ELSE 0 END) as emprestados, " +
                 "SUM(valor_aquisicao) as valor_total " +
                 "FROM equipamentos")) {
            
            if (rs.next()) {
                int total = rs.getInt("total");
                int disponiveis = rs.getInt("disponiveis");
                int emManutencao = rs.getInt("em_manutencao");
                int emprestados = rs.getInt("emprestados");
                double valorTotal = rs.getDouble("valor_total");
                
                StringBuilder relatorio = new StringBuilder();
                relatorio.append("RELATÓRIO DE EQUIPAMENTOS\n\n");
                relatorio.append("Total de Equipamentos: ").append(total).append("\n");
                relatorio.append("Equipamentos Disponíveis: ").append(disponiveis).append("\n");
                relatorio.append("Equipamentos em Manutenção: ").append(emManutencao).append("\n");
                relatorio.append("Equipamentos Emprestados: ").append(emprestados).append("\n");
                relatorio.append("Valor Total dos Equipamentos: R$ ").append(String.format("%.2f", valorTotal).replace('.', ',')).append("\n\n");
                
                // Adicionar informações de manutenções
                try (Statement stmtManutencao = conn.createStatement();
                     ResultSet rsManutencao = stmtManutencao.executeQuery(
                         "SELECT COUNT(*) as total_manutencoes, " +
                         "SUM(CASE WHEN status = 'Em Manutenção' THEN 1 ELSE 0 END) as em_andamento, " +
                         "SUM(CASE WHEN status = 'Atrasada' THEN 1 ELSE 0 END) as atrasadas, " +
                         "SUM(CASE WHEN status = 'Concluída' THEN 1 ELSE 0 END) as concluidas " +
                         "FROM manutencoes")) {
                    
                    if (rsManutencao.next()) {
                        int totalManutencoes = rsManutencao.getInt("total_manutencoes");
                        int emAndamento = rsManutencao.getInt("em_andamento");
                        int atrasadas = rsManutencao.getInt("atrasadas");
                        int concluidas = rsManutencao.getInt("concluidas");
                        
                        relatorio.append("MANUTENÇÕES\n");
                        relatorio.append("Total de Manutenções: ").append(totalManutencoes).append("\n");
                        relatorio.append("Manutenções em Andamento: ").append(emAndamento).append("\n");
                        relatorio.append("Manutenções Atrasadas: ").append(atrasadas).append("\n");
                        relatorio.append("Manutenções Concluídas: ").append(concluidas).append("\n\n");
                    }
                }
                // Adicionar informações de empréstimos
                try (Statement stmtEmprestimo = conn.createStatement();
                     ResultSet rsEmprestimo = stmtEmprestimo.executeQuery(
                         "SELECT COUNT(*) as total_emprestimos, " +
                         "SUM(CASE WHEN status = 'no prazo' THEN 1 ELSE 0 END) as no_prazo, " +
                         "SUM(CASE WHEN status = 'atrasado' THEN 1 ELSE 0 END) as atrasados, " +
                         "SUM(CASE WHEN status = 'concluído' THEN 1 ELSE 0 END) as concluidos " +
                         "FROM emprestimos")) {
                    
                    if (rsEmprestimo.next()) {
                        int totalEmprestimos = rsEmprestimo.getInt("total_emprestimos");
                        int noPrazo = rsEmprestimo.getInt("no_prazo");
                        int atrasados = rsEmprestimo.getInt("atrasados");
                        int concluidos = rsEmprestimo.getInt("concluidos");
                        
                        relatorio.append("EMPRÉSTIMOS\n");
                        relatorio.append("Total de Empréstimos: ").append(totalEmprestimos).append("\n");
                        relatorio.append("Empréstimos no Prazo: ").append(noPrazo).append("\n");
                        relatorio.append("Empréstimos Atrasados: ").append(atrasados).append("\n");
                        relatorio.append("Empréstimos Concluídos: ").append(concluidos).append("\n");
                    }
                }
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Relatório");
                alert.setHeaderText("Relatório de Equipamentos");
                
                TextArea textArea = new TextArea(relatorio.toString());
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setPrefHeight(400);
                textArea.setPrefWidth(500);
                
                Button btnImprimir = new Button("Imprimir");
                btnImprimir.setOnAction(e -> imprimirRelatorio(relatorio.toString()));
                
                HBox hbox = new HBox(10);
                hbox.getChildren().add(btnImprimir);
                hbox.setAlignment(Pos.CENTER_RIGHT);
                
                VBox vbox = new VBox(10);
                vbox.getChildren().addAll(textArea, hbox);
                
                alert.getDialogPane().setContent(vbox);
                alert.showAndWait();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao gerar relatório: " + e.getMessage());
        }
    }
    private void imprimirRelatorio(String conteudo) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            mostrarAlerta(AlertType.ERROR, "Erro", "Não foi possível encontrar uma impressora.");
            return;
        }
        
        boolean proceedWithPrinting = job.showPrintDialog(null);
        
        if (proceedWithPrinting) {
            // Criar texto para impressão
            Text text = new Text(conteudo);
            text.setWrappingWidth(500);
            
            // Configurar página de impressão
            PageLayout pageLayout = job.getJobSettings().getPageLayout();
            
            // Criar nó para impressão
            StackPane root = new StackPane(text);
            root.setAlignment(Pos.TOP_LEFT);
            
            // Imprimir
            boolean printed = job.printPage(pageLayout, root);
            
            if (printed) {
                job.endJob();
                mostrarAlerta(AlertType.INFORMATION, "Impressão", "Relatório enviado para impressão com sucesso!");
            } else {
                mostrarAlerta(AlertType.ERROR, "Erro", "Falha ao imprimir relatório.");
            }
        }
    }
    private void mostrarAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}





