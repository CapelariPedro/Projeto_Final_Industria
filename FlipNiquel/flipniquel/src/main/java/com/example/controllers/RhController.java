// Define o pacote onde esta classe controllers está localizada
package com.example.controllers;

// Importa a classe Database responsável pela conexão e manipulação do banco de dados
import com.example.database.Database;

// Importa as classes modelo que representam os dados pessoais e profissionais
import com.example.models.DadoPessoal;
import com.example.models.DadoProfissional;

// Importa classes do JavaFX para manipulação de listas observáveis (listas que notificam automaticamente a interface gráfica quando seus dados são alterados)
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

// Importa anotações e controles da interface gráfica do JavaFX (FXML, Tabela, Campo de Texto, etc.)
import javafx.fxml.FXML;
import javafx.scene.control.*;

//Importa utilitário para mapear colunas da tabela aos atributos dos objetos
import javafx.scene.control.cell.PropertyValueFactory;

// Importa as classes necessárias para trabalhar com banco de dados (SQL)
import java.sql.*;




// Declaração da classe que atua como controller na arquitetura MVC (Model-View-Controller)
public class RhController {

    // Campos da interface gráfica (FXML) para o cadastro de dados pessoais do funcionário
    @FXML private TextField txtnome_completoFunc;
    @FXML private TextField txtdatanascimentoFunc;
    @FXML private ComboBox<String> comboBoxSexo;
    @FXML private ComboBox<String> comboBoxEstadoCivil;
    @FXML private TextField txtconjugeFunc;
    @FXML private TextField txtdependentesFunc;
    @FXML private ComboBox<String> comboBoxNacionalidade;
    @FXML private TextField txtnaturalidadeFunc;
    @FXML private TextField txtcpfFunc;
    @FXML private TextField txtrgFunc;
    @FXML private TextField txtenderecoFunc;
    @FXML private TextField txttelefoneFunc;
    @FXML private TextField txtemailFunc;
    @FXML private TextField txtfiliacaoFunc;
    @FXML private ComboBox<String>comboBoxtipo_sanguineoFunc;
    @FXML private TextField txtcontato_emergenciaFunc;

    // Campos da interface gráfica (FXML) para o cadastro de dados profissionais do funcionário
    @FXML private TextField txtcargo;
    @FXML private ComboBox<String> comboBoxdepartamento;
    @FXML private TextField txtfuncao;
    @FXML private TextField txtmaquinas;
    @FXML private TextField txtadmissao;
    @FXML private TextField txtsalario; 
    @FXML private TextField txtdadosbancarios;
    @FXML private TextField txtbeneficios;
    @FXML private ComboBox<String> comboBoxescolaridade;
    @FXML private TextField txtctps;
    @FXML private TextField txtpis;
    @FXML private ComboBox<String> comboBoxcontrato;
    @FXML private TextField txthorario;
    @FXML private TextField txtacidentes;
    @FXML private TextField txtadvertencias;

    // Campos da interface gráfica (FXML) para atualização de dados pessoais de um funcionário já cadastrado
    @FXML private TextField txtIdAtualizarFunc;
    @FXML private TextField txtNomeAtualizarFunc;
    @FXML private TextField txtDataNascimentoAtualizarFunc;
    @FXML private ComboBox<String>comboBoxSexoAtualizarFunc;
    @FXML private ComboBox<String>comboBoxEstadoCivilAtualizarFunc;
    @FXML private TextField txtConjugeAtualizarFunc;
    @FXML private TextField txtDependentesAtualizarFunc;
    @FXML private ComboBox<String>comboBoxNacionalidadeAtualizarFunc;
    @FXML private TextField txtNaturalidadeAtualizarFunc;
    @FXML private TextField txtCpfAtualizarFunc;
    @FXML private TextField txtRgAtualizarFunc;
    @FXML private TextField txtEnderecoAtualizarFunc;
    @FXML private TextField txtTelefoneAtualizarFunc;
    @FXML private TextField txtEmailAtualizarFunc;
    @FXML private TextField txtFiliacaoAtualizarFunc;
    @FXML private ComboBox<String>comboBoxTipoSanguineoAtualizarFunc;
    @FXML private TextField txtContatoEmergenciaAtualizarFunc;

    // Campos da interface gráfica (FXML) para atualização de dados profissionais
    @FXML private TextField txtcargoAtualizarFunc;
    @FXML private ComboBox<String> comboBoxdepartamentoAtualizarFunc;
    @FXML private TextField txtfuncaoAtualizarFunc;
    @FXML private TextField txtmaquinasAtualizarFunc;
    @FXML private TextField txtadmissaoAtualizarFunc;
    @FXML private TextField txtsalarioAtualizarFunc;
    @FXML private TextField txtdadosbancariosAtualizarFunc;
    @FXML private TextField txtbeneficiosAtualizarFunc;
    @FXML private ComboBox<String>comboBoxescolaridadeAtualizarFunc;
    @FXML private TextField txtctpsAtualizarFunc;
    @FXML private TextField txtpisAtualizarFunc;
    @FXML private ComboBox<String>comboBoxcontratoAtualizarFunc;
    @FXML private TextField txthorarioAtualizarFunc;
    @FXML private TextField txtacidentesAtualizarFunc;
    @FXML private TextField txtadvertenciasAtualizarFunc;

    // Tabelas usadas para visualizar dados pessoais e profissionais
    @FXML private TableView<DadoPessoal> tableDadoPessoal;
    @FXML private TableColumn<DadoPessoal, Integer> colIdFunc;
    @FXML private TableColumn<DadoPessoal, String> colNomeFunc;
    @FXML private TableColumn<DadoPessoal, String> colDataNascimentoFunc;
    @FXML private TableColumn<DadoPessoal, String> colSexoFunc;
    @FXML private TableColumn<DadoPessoal, String> colEstado_CivilFunc;
    @FXML private TableColumn<DadoPessoal, String> colConjugeFunc;
    @FXML private TableColumn<DadoPessoal, String> colDependentesFunc;
    @FXML private TableColumn<DadoPessoal, String> colNacionalidadeFunc;
    @FXML private TableColumn<DadoPessoal, String> colNaturalidadeFunc;
    @FXML private TableColumn<DadoPessoal, String> colCpfFunc;
    @FXML private TableColumn<DadoPessoal, String> colRgFunc;
    @FXML private TableColumn<DadoPessoal, String> colEnderecoFunc;
    @FXML private TableColumn<DadoPessoal, String> colTelefoneFunc;
    @FXML private TableColumn<DadoPessoal, String> colEmailFunc;
    @FXML private TableColumn<DadoPessoal, String> colFiliacaoFunc;
    @FXML private TableColumn<DadoPessoal, String> colTipo_SanguineoFunc;
    @FXML private TableColumn<DadoPessoal, String> colContato_EmergenciaFunc;

    @FXML private TableView<DadoProfissional> tableDadoProfissional;
    @FXML private TableColumn<DadoProfissional, Integer> colIdprof;
    @FXML private TableColumn<DadoProfissional, String> colNomeJun;
    @FXML private TableColumn<DadoProfissional, String> colCargo;
    @FXML private TableColumn<DadoProfissional, String> coldepartamento;
    @FXML private TableColumn<DadoProfissional, String> colFuncao;
    @FXML private TableColumn<DadoProfissional, String> colMaquinas;
    @FXML private TableColumn<DadoProfissional, String> colDataAdmissao;
    @FXML private TableColumn<DadoProfissional, String> colSalario;
    @FXML private TableColumn<DadoProfissional, String> colDadosBancarios;
    @FXML private TableColumn<DadoProfissional, String> colBeneficios;
    @FXML private TableColumn<DadoProfissional, String> colEscolaridade;
    @FXML private TableColumn<DadoProfissional, String> colCtps;
    @FXML private TableColumn<DadoProfissional, String> colPisPasep;
    @FXML private TableColumn<DadoProfissional, String> colContrato;
    @FXML private TableColumn<DadoProfissional, String> colHorario;
    @FXML private TableColumn<DadoProfissional, String> colAcidentes;
    @FXML private TableColumn<DadoProfissional, String> colAdvertencia;
   

    // Campos de filtro para facilitar a busca por funcionários
    @FXML private TextField filtroNomeFunc;
    @FXML private TextField filtroDataNascimentoFunc;
    @FXML private ComboBox<String>filtroSexoFunc;
    @FXML private ComboBox<String>filtroEstadoCivilFunc;
    @FXML private TextField filtroConjugeFunc;
    @FXML private TextField filtroDependentesFunc;
    @FXML private ComboBox<String>filtroNacionalidadeFunc;
    @FXML private TextField filtroNaturalidadeFunc;
    @FXML private TextField filtroCpfFunc;
    @FXML private TextField filtroRgFunc;
    @FXML private TextField filtroEnderecoFunc;
    @FXML private TextField filtroTelefoneFunc;
    @FXML private TextField filtroEmailFunc;
    @FXML private TextField filtroFiliacaoFunc;
    @FXML private ComboBox<String>filtroTipoSanguineoFunc;
    @FXML private TextField filtroContatoEmergenciaFunc;

    @FXML private TextField filtrocargo;
    @FXML private ComboBox<String>filtrodepartamento;
    @FXML private TextField filtrofuncao;
    @FXML private TextField filtromaquinas;
    @FXML private TextField filtroadmissao;
    @FXML private TextField filtrosalario;
    @FXML private TextField filtrodadosbancarios;
    @FXML private TextField filtrobeneficios;
    @FXML private ComboBox<String>filtroescolaridade;
    @FXML private TextField filtroctps;
    @FXML private TextField filtropis;
    @FXML private ComboBox<String>filtrocontrato;
    @FXML private TextField filtrohorario;
    @FXML private TextField filtroacidentes;
    @FXML private TextField filtroadvertencias;

    // Abas da interface gráfica
    @FXML private TabPane tabPaneRh;
    @FXML private Tab tabCadastro;
    @FXML private TabPane tabPaneCadastro;
    @FXML private Tab tabDadoPessoal;
    @FXML private Tab tabDadoProfissional;
    @FXML private TabPane tabPaneAtualizar;
    @FXML private Tab tabAtualizarPessoal;
    @FXML private Tab tabAtualizarProfissional;
    @FXML private TabPane tabPaneVisualizacao;
    @FXML private Tab tabAtualizacao;

    // Listas observáveis que alimentam as TableViews com os dados
    // Quando você modifica ou atualiza essa lista, a interface do usuário é automaticamente atualizada para refletir essas mudanças.
    private ObservableList<DadoPessoal> listaDadoPessoal = FXCollections.observableArrayList();
    private ObservableList<DadoProfissional> listaDadoProfissional = FXCollections.observableArrayList();

    private Integer idDadoPessoalSelecionado; 

    // Método executado automaticamente ao carregar a tela
    @FXML
    public void initialize() {

        // Mapeamento das colunas da tabela com os atributos da classe modelo DadoPessoal
        colIdFunc.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome_completo"));
        colDataNascimentoFunc.setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
        colSexoFunc.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colEstado_CivilFunc.setCellValueFactory(new PropertyValueFactory<>("estado_civil"));
        colConjugeFunc.setCellValueFactory(new PropertyValueFactory<>("conjuge"));
        colDependentesFunc.setCellValueFactory(new PropertyValueFactory<>("dependentes"));
        colNacionalidadeFunc.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        colNaturalidadeFunc.setCellValueFactory(new PropertyValueFactory<>("naturalidade"));
        colCpfFunc.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colRgFunc.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colEnderecoFunc.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colTelefoneFunc.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmailFunc.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFiliacaoFunc.setCellValueFactory(new PropertyValueFactory<>("filiacao"));
        colTipo_SanguineoFunc.setCellValueFactory(new PropertyValueFactory<>("tipo_sanguineo"));
        colContato_EmergenciaFunc.setCellValueFactory(new PropertyValueFactory<>("contato_emergencia"));

        // Inicialização das ComboBoxes com valores fixos para facilitar seleção
            ObservableList<String> estadosCivis = FXCollections.observableArrayList( 
            "Solteiro(a)",
            "Casado(a)",
            "Divorciado(a)",
            "Viúvo(a)",
            "Outro"
);
        comboBoxEstadoCivil.setItems(estadosCivis);
        comboBoxEstadoCivilAtualizarFunc.setItems(estadosCivis);
        filtroEstadoCivilFunc.setItems(estadosCivis);

        ObservableList<String> sexos = FXCollections.observableArrayList(
        "Masculino",
        "Feminino",
        "Outro"
);
        comboBoxSexo.setItems(sexos);
        comboBoxSexoAtualizarFunc.setItems(sexos);
        filtroSexoFunc.setItems(sexos);


        ObservableList<String> tiposSanguineos = FXCollections.observableArrayList(
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"
 );
         comboBoxtipo_sanguineoFunc.setItems(tiposSanguineos);
         filtroTipoSanguineoFunc.setItems(tiposSanguineos);


         ObservableList<String> nacionalidade = FXCollections.observableArrayList(
           

"Afeganistão",
"África do Sul",
"Albânia",
"Alemanha",
"Angola",
"Argentina",
"Austrália",
"Áustria",
"Bangladesh",
"Belarus",
"Bélgica",
"Bolívia",
"Brasil",
"Cabo Verde",
"Camarões",
"Canadá",
"Chile",
"China",
"Colômbia",
"Coreia do Sul",
"Costa Rica",
"Cuba",
"Dinamarca",
"Equador",
"Egito",
"Espanha",
"Estados Unidos",
"Etiópia",
"Filipinas",
"França",
"Gana",
"Guatemala",
"Haiti",
"Honduras",
"Índia",
"Indonésia",
"Inglaterra",
"Irlanda",
"Itália",
"Jamaica",
"Japão",
"Líbano",
"Líbia",
"México",
"Moçambique",
"Nigéria",
"Paquistão",
"Paraguai",
"Peru",
"Polônia",
"Portugal",
"Reino Unido",
"República Dominicana",
"Romênia",
"Rússia",
"Senegal",
"Síria",
"Ucrânia",
"Uruguai",
"Venezuela",
"Vietnã",
"Apátrida",
"Outra nacionalidade"

         );

        comboBoxNacionalidade.setItems(nacionalidade);
      

        ObservableList<String> escolaridade = FXCollections.observableArrayList(

    "Fundamental Incompleto (Até 5º ano)",
    "Fundamental Incompleto (Até 9º ano)",
    "Fundamental Completo",
    "Médio Incompleto",
    "Médio Completo",
    "Técnico Incompleto",
    "Técnico Completo",
    "Graduação Incompleta",
    "Graduação Completa (Bacharelado)",
    "Graduação Completa (Licenciatura)",
    "Graduação Completa (Tecnólogo)",
    "Pós-graduação Incompleta",
    "Especialização",
    "MBA",
    "Mestrado",
    "Doutorado",
    "Pós-doutorado",
    "Curso de Extensão",
    "Curso de Aperfeiçoamento",
    "Curso Profissionalizante",
    "Outros"

         );

         comboBoxescolaridade.setItems(escolaridade);
         comboBoxescolaridadeAtualizarFunc.setItems(escolaridade);
         filtroescolaridade.setItems(escolaridade);
                   
    
        carregarDadoPessoal();

        colIdprof.setCellValueFactory(new PropertyValueFactory<>("idprof"));
        colNomeJun.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        coldepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        colMaquinas.setCellValueFactory(new PropertyValueFactory<>("maquinas"));
        colDataAdmissao.setCellValueFactory(new PropertyValueFactory<>("admissao"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colDadosBancarios.setCellValueFactory(new PropertyValueFactory<>("dadosbancarios"));
        colBeneficios.setCellValueFactory(new PropertyValueFactory<>("beneficios"));
        colEscolaridade.setCellValueFactory(new PropertyValueFactory<>("escolaridade"));
        colCtps.setCellValueFactory(new PropertyValueFactory<>("ctps"));
        colPisPasep.setCellValueFactory(new PropertyValueFactory<>("pis"));
        colContrato.setCellValueFactory(new PropertyValueFactory<>("contrato"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colAcidentes.setCellValueFactory(new PropertyValueFactory<>("acidentes"));
        colAdvertencia.setCellValueFactory(new PropertyValueFactory<>("advertencias"));

        ObservableList<String> departamentos = FXCollections.observableArrayList(
            "Automação",
            "Estoque",
            "Financeiro",
            "Manutenção",
            "Produção",
            "Rh"
    );
    
        comboBoxdepartamento.setItems(departamentos);
        comboBoxdepartamentoAtualizarFunc.setItems(departamentos);
        filtrodepartamento.setItems(departamentos);



        ObservableList<String> contratos = FXCollections.observableArrayList(
            "Temporário",
            "CLT"
                
        );
        comboBoxcontrato.setItems(contratos);
        comboBoxcontratoAtualizarFunc.setItems(contratos);
        filtrocontrato.setItems(contratos);

           
              

        carregarDadoProfissional();

        

        // Adiciona um listener (trecho de código que "ouve" e reage quando algo acontece) para detectar quando um item da tabela de Dados Pessoais é selecionado
        tableDadoPessoal.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            
            // Verifica se um novo item foi realmente selecionado
            if (newSelection != null) {
                preencherMultiplosCampos(newSelection); // Preenche os campos com os dados selecionados
            }
        });
        // Adiciona um listener (trecho de código que "ouve" e reage quando algo acontece) para detectar quando um item da tabela de Dados Profissionais é selecionado
        tableDadoProfissional.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                // Procura o DadoPessoal correspondente pelo CPF
                DadoPessoal pessoalRelacionado = tableDadoPessoal.getItems().stream()
                    .filter(p -> p.getCpf().equals(newSelection.getDados_pessoais()))
                    .findFirst()
                    .orElse(null);
    
                if (pessoalRelacionado != null) {
                    // Preenche os campos de dados pessoais e profissionais
                    preencherCamposAtualizacaoPessoal(pessoalRelacionado);
                    preencherCamposAtualizacaoProfissional(newSelection);
                } else {
                    // Imprime mensagem de Erro se não encontrar
                    System.err.println("Dado pessoal correspondente não encontrado.");
                }
            }
        });
    }

// Método para preencher os campos de dados pessoais e profissionais quando um dado pessoal é selecionado
private void preencherMultiplosCampos(DadoPessoal dadopessoalSelecionado) {
    if (dadopessoalSelecionado == null) {
        System.out.println("Dado pessoal selecionado é nulo. Retornando.");
        return; // Se não houver seleção, não faz nada
    }

    String cpfSelecionado = dadopessoalSelecionado.getCpf();
    // Procura o dado profissional correspondente usando o CPF do dado pessoal
    DadoProfissional dadoprofissionalSelecionado = tableDadoProfissional.getItems().stream()
        .filter(p -> {
            return p.getDados_pessoais().equals(cpfSelecionado);
        })
        .findFirst()
        .orElse(null);

    if (dadoprofissionalSelecionado != null) {
        // Preenche os campos de dados pessoais e profissionais se encontrados
        preencherCamposAtualizacaoPessoal(dadopessoalSelecionado);
        preencherCamposAtualizacaoProfissional(dadoprofissionalSelecionado);  
    } else {
        System.err.println("ERRO AO CARREGAR! Nenhum dado profissional encontrado para o CPF: " + cpfSelecionado);
    }
}

// Método para salvar os dados profissionais e pessoais no banco de dados   
@FXML
    private void salvarDadosProfissional() {

        try (Connection conn = Database.getConnection(); // Estabelece a conexão com o banco
             // Preparação para inserção dos dados pessoais
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO dadospessoais (nome_completo, data_nascimento, sexo, estado_civil, conjuge, dependentes, nacionalidade, naturalidade, cpf, rg, endereco, telefone, email, filiacao, tipo_sanguineo, contato_emergencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             // Preparação para inserção dos dados profissionais
             PreparedStatement stmt_1 = conn.prepareStatement("INSERT INTO dadosprofissionais (cargo, departamento, funcao, maquinas, admissao, salario, dadosbancarios, beneficios, escolaridade, ctps, pisPasep, contrato, horario, acidentes, advertencias, dados_pessoais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Obtém o CPF digitado
            String cpfDigitado = txtcpfFunc.getText();
            // Formata o CPF
            String cpfFormatado = formatarCPF(cpfDigitado);

            // Insere dados pessoais
            stmt.setString(1, txtnome_completoFunc.getText());// Preenche com os dados do formulário
            stmt.setString(2, txtdatanascimentoFunc.getText());
            stmt.setString(3, comboBoxSexo.getValue());
            stmt.setString(4, comboBoxEstadoCivil.getValue());
            stmt.setString(5, txtconjugeFunc.getText());
            stmt.setString(6, txtdependentesFunc.getText());
            stmt.setString(7, comboBoxNacionalidade.getValue());
            stmt.setString(8, txtnaturalidadeFunc.getText());
            stmt.setString(9, cpfFormatado); // Usa o CPF formatado
            stmt.setString(10, txtrgFunc.getText());
            stmt.setString(11, txtenderecoFunc.getText());
            stmt.setString(12, txttelefoneFunc.getText());
            stmt.setString(13, txtemailFunc.getText());
            stmt.setString(14, txtfiliacaoFunc.getText());
            stmt.setString(15, comboBoxtipo_sanguineoFunc.getValue());
            stmt.setString(16, txtcontato_emergenciaFunc.getText());
            stmt.executeUpdate(); // Executa a inserção/atualização dos dados pessoais

            // Insere dados profissionais
            stmt_1.setString(1, txtcargo.getText());
            stmt_1.setString(2, comboBoxdepartamento.getValue());
            stmt_1.setString(3, txtfuncao.getText());
            stmt_1.setString(4, txtmaquinas.getText());
            stmt_1.setString(5, txtadmissao.getText());
            stmt_1.setString(6, txtsalario.getText());
            stmt_1.setString(7, txtdadosbancarios.getText());
            stmt_1.setString(8, txtbeneficios.getText());
            stmt_1.setString(9, comboBoxescolaridade.getValue());
            stmt_1.setString(10, txtctps.getText());
            stmt_1.setString(11, txtpis.getText());
            stmt_1.setString(12, comboBoxcontrato.getValue());
            stmt_1.setString(13, txthorario.getText());
            stmt_1.setString(14, txtacidentes.getText());
            stmt_1.setString(15, txtadvertencias.getText());
            stmt_1.setString(16, cpfFormatado); // Usa o CPF formatado para relacionar
            stmt_1.executeUpdate(); // Executa a inserção/atualização dos dados profissionais

            carregarDadoProfissional();
            carregarDadoPessoal();
            // Tratamento de erro ou acerto
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Funcionário salvo com sucesso!");
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao salvar funcionário: " + e.getMessage());
        }

        limparCadastrar();
    }


    @FXML
    public void limparCadastrar() {
        // Limpa todos os campos de entrada no formulário de cadastro
        txtnome_completoFunc.clear();
        txtdatanascimentoFunc.clear();
        comboBoxSexo.setValue(null);
        comboBoxEstadoCivil.setValue(null);
        txtnaturalidadeFunc.clear();
        txtcpfFunc.clear();
        txtrgFunc.clear();
        txtenderecoFunc.clear();
        comboBoxtipo_sanguineoFunc.setValue(null);
        txtconjugeFunc.clear();
        txtdependentesFunc.clear();
        comboBoxNacionalidade.setValue(null);
        txttelefoneFunc.clear();
        txtemailFunc.clear();
        txtfiliacaoFunc.clear();
        txtcontato_emergenciaFunc.clear();
    
        txtcargo.clear();
        comboBoxdepartamento.setValue(null);
        txtfuncao.clear();
        txtmaquinas.clear();
        txtadmissao.clear();
        txtsalario.clear();
        txtdadosbancarios.clear();
        txtbeneficios.clear();
        comboBoxescolaridade.setValue(null);
        txtctps.clear();
        txtpis.clear();
        comboBoxcontrato.setValue(null);
        txthorario.clear();
        txtacidentes.clear();
        txtadvertencias.clear();
        txtcpfFunc.clear();
    
    }
    

    
// Método para navegar até a aba de dados profissionais após salvar dados pessoais
    @FXML
private void salvarDadoPessoal() {
    tabPaneCadastro.getSelectionModel().select(tabDadoProfissional);
    }
            
// Método para atualizar os dados pessoais e profissionais de um funcionário
    @FXML
public void atualizarDadoProfissional() {   
    // Obtém os dados preenchidos pelo usuário nos campos de texto
    String nome_completo = txtNomeAtualizarFunc.getText();
    String data_nascimento = txtDataNascimentoAtualizarFunc.getText();
    String sexo = comboBoxSexoAtualizarFunc.getValue();
    String rg = txtRgAtualizarFunc.getText();
    String naturalidade = txtNaturalidadeAtualizarFunc.getText();
    String conjuge = txtConjugeAtualizarFunc.getText();
    String dependentes = txtDependentesAtualizarFunc.getText();
    String email = txtEmailAtualizarFunc.getText();
    String endereco = txtEnderecoAtualizarFunc.getText();
    String telefone = txtTelefoneAtualizarFunc.getText();
    String contato_emergencia = txtContatoEmergenciaAtualizarFunc.getText();
    String estadoCivil = comboBoxEstadoCivilAtualizarFunc.getValue();

    String pisPasep = txtpisAtualizarFunc.getText();
    String ctps = txtctpsAtualizarFunc.getText();
    String admissao = txtadmissaoAtualizarFunc.getText();
    String contrato = comboBoxcontratoAtualizarFunc.getValue();
    String dadosbancarios = txtdadosbancariosAtualizarFunc.getText();
    String escolaridade = comboBoxescolaridadeAtualizarFunc.getValue();
    String cargo = txtcargoAtualizarFunc.getText();
    String departamento = comboBoxdepartamentoAtualizarFunc.getValue();
    String maquinas = txtmaquinasAtualizarFunc.getText();
    String funcao = txtfuncaoAtualizarFunc.getText();
    String horario = txthorarioAtualizarFunc.getText();
    String salario = txtsalarioAtualizarFunc.getText();
    String beneficios = txtbeneficiosAtualizarFunc.getText();
    String advertencias = txtadvertenciasAtualizarFunc.getText();
    String acidentes = txtacidentesAtualizarFunc.getText();

    // Verifica se há um ID de dado pessoal selecionado para atualizar
    if (idDadoPessoalSelecionado != null) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE dadospessoais SET nome_completo = ?, data_nascimento = ?, sexo = ?, rg = ?, naturalidade = ?, conjuge = ?, dependentes = ?, email = ?, endereco = ?, telefone = ?, contato_emergencia = ?, estado_civil = ? WHERE id = ?");
             PreparedStatement statement_1 = connection.prepareStatement("UPDATE dadosprofissionais SET pisPasep = ?, ctps = ?, admissao = ?, contrato = ?, dadosbancarios = ?, escolaridade = ?, cargo = ?, departamento = ?, maquinas = ?, funcao = ?, horario = ?, salario = ?, beneficios = ?, advertencias = ?, acidentes = ? WHERE dados_pessoais = (SELECT cpf FROM dadospessoais WHERE id = ? )")) {

            // Atualiza os dados pessoais
            statement.setString(1,nome_completo);
            statement.setString(2, data_nascimento);
            statement.setString(3, sexo);
            statement.setString(4, rg);
            statement.setString(5, naturalidade);
            statement.setString(6, conjuge);
            statement.setString(7, dependentes);
            statement.setString(8, email);
            statement.setString(9, endereco);
            statement.setString(10, telefone);
            statement.setString(11, contato_emergencia);
            statement.setString(12, estadoCivil);
            statement.setInt(13, idDadoPessoalSelecionado);
            statement.executeUpdate();
            carregarDadoPessoal();

            // Atualiza os dados profissionais
            statement_1.setString(1, pisPasep);
            statement_1.setString(2, ctps);
            statement_1.setString(3, admissao);
            statement_1.setString(4, contrato);
            statement_1.setString(5, dadosbancarios);
            statement_1.setString(6, escolaridade);
            statement_1.setString(7, cargo);
            statement_1.setString(8, departamento);
            statement_1.setString(9, maquinas);
            statement_1.setString(10, funcao);
            statement_1.setString(11, horario);
            statement_1.setString(12, salario);
            statement_1.setString(13, beneficios);
            statement_1.setString(14, advertencias);
            statement_1.setString(15, acidentes);
            statement_1.setInt(16, idDadoPessoalSelecionado);
            statement_1.executeUpdate();
            carregarDadoProfissional();

            // Limpa os campos e exibe mensagem de sucesso ou erro
            limparCamposAtualizacao();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
            idDadoPessoalSelecionado = null; 
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao atualizar funcionário: " + e.getMessage());
        }
    } else {
        // Caso não tenha sido selecionado um funcionário, exibe aviso
        mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um funcionário na tabela para atualizar.");
    }
    }
    
        @FXML private void atualizarDadoPessoal() {
    tabPaneAtualizar.getSelectionModel().select(tabAtualizarProfissional);
    }

        // Método para limpar todos os campos de atualização       
        @FXML
        private void limparCamposAtualizacao() {
            txtNomeAtualizarFunc.clear();
            txtDataNascimentoAtualizarFunc.clear();
            comboBoxSexoAtualizarFunc.setValue(null);
            txtRgAtualizarFunc.clear();
            txtCpfAtualizarFunc.clear();
            txtNaturalidadeAtualizarFunc.clear();
            txtConjugeAtualizarFunc.clear();
            txtDependentesAtualizarFunc.clear();
            txtEmailAtualizarFunc.clear();
            txtEnderecoAtualizarFunc.clear();
            txtTelefoneAtualizarFunc.clear();
            txtContatoEmergenciaAtualizarFunc.clear();
            comboBoxEstadoCivilAtualizarFunc.setValue(null);

            txtpisAtualizarFunc.clear();
            txtctpsAtualizarFunc.clear();
            txtadmissaoAtualizarFunc.clear();
            comboBoxcontratoAtualizarFunc.setValue(null);
            txtdadosbancariosAtualizarFunc.clear();
            comboBoxescolaridadeAtualizarFunc.setValue(null);
            txtcargoAtualizarFunc.clear();
            comboBoxdepartamentoAtualizarFunc.setValue(null);
            txtmaquinasAtualizarFunc.clear();
            txtfuncaoAtualizarFunc.clear();
            txthorarioAtualizarFunc.clear();
            txtsalarioAtualizarFunc.clear();
            txtbeneficiosAtualizarFunc.clear();
            txtadvertenciasAtualizarFunc.clear();
            txtacidentesAtualizarFunc.clear(); 
        }
    
        // Método para preencher os campos de atualização com os dados do funcionário selecionado
        private void preencherCamposAtualizacaoPessoal(DadoPessoal pessoal) {
            if (pessoal != null) {
                idDadoPessoalSelecionado = pessoal.getId();
                txtNaturalidadeAtualizarFunc.setText(pessoal.getNaturalidade());
                txtCpfAtualizarFunc.setText(pessoal.getCpf());
                txtRgAtualizarFunc.setText(pessoal.getRg());
                comboBoxSexoAtualizarFunc.setValue(pessoal.getSexo());
                txtDataNascimentoAtualizarFunc.setText(pessoal.getData_nascimento());
                txtNomeAtualizarFunc.setText(pessoal.getNome_completo());;
                comboBoxEstadoCivilAtualizarFunc.setValue(pessoal.getEstado_civil());
                txtConjugeAtualizarFunc.setText(pessoal.getConjuge());
                txtDependentesAtualizarFunc.setText(pessoal.getDependentes());
                txtEnderecoAtualizarFunc.setText(pessoal.getEndereco());
                txtTelefoneAtualizarFunc.setText(pessoal.getTelefone());
                txtEmailAtualizarFunc.setText(pessoal.getEmail());
                txtContatoEmergenciaAtualizarFunc.setText(pessoal.getContato_emergencia());
            }
        }
        
        // Método para preencher os campos de atualização profissional com os dados do funcionário
        private void preencherCamposAtualizacaoProfissional(DadoProfissional profissional) {
            if (profissional != null) {
                txtpisAtualizarFunc.setText(profissional.getPis());
                txtcargoAtualizarFunc.setText(profissional.getCargo());
                comboBoxdepartamentoAtualizarFunc.setValue(profissional.getDepartamento());
                txtfuncaoAtualizarFunc.setText(profissional.getFuncao());
                txtmaquinasAtualizarFunc.setText(profissional.getMaquinas());
                txtsalarioAtualizarFunc.setText(profissional.getSalario());
                txtdadosbancariosAtualizarFunc.setText(profissional.getDadosbancarios());
                txtbeneficiosAtualizarFunc.setText(profissional.getBeneficios());
                comboBoxescolaridadeAtualizarFunc.setValue(profissional.getEscolaridade());
                comboBoxcontratoAtualizarFunc.setValue(profissional.getContrato());
                txthorarioAtualizarFunc.setText(profissional.getHorario());
                txtacidentesAtualizarFunc.setText(profissional.getAcidentes());
                txtadvertenciasAtualizarFunc.setText(profissional.getAdvertencias());
                txtctpsAtualizarFunc.setText(profissional.getCtps());
                txtadmissaoAtualizarFunc.setText(profissional.getAdmissao());
            }
        }
        

        private void carregarDadoPessoal() {
            listaDadoPessoal.clear();// Limpa a lista de dados pessoais antes de carregar novos dados.
            try (Connection conn = Database.getConnection(); // Estabelece a conexão com o banco de dados.
                 Statement stmt = conn.createStatement(); // Cria um statement para executar a consulta.
                 ResultSet rs = stmt.executeQuery("SELECT * FROM dadospessoais")) {// Executa a consulta SQL para selecionar todos os registros da tabela 'dadospessoais'.
                 
                // Processa cada linha retornada pela consulta e adiciona os dados na lista.
                while (rs.next()) {
                    listaDadoPessoal.add(new DadoPessoal(rs.getInt("id"), rs.getString("nome_completo"), rs.getString("data_nascimento"), rs.getString("sexo"), rs.getString("estado_civil"), rs.getString("conjuge"), rs.getString("dependentes"), rs.getString("nacionalidade"), rs.getString("naturalidade"), rs.getString("cpf"), rs.getString("rg"), rs.getString("endereco"), rs.getString("telefone"), rs.getString("email"), rs.getString("filiacao"), rs.getString("tipo_sanguineo"), rs.getString("contato_emergencia")));
                }
                // Define a lista de dados pessoais como a fonte de dados para a tabela.
                tableDadoPessoal.setItems(listaDadoPessoal);
            } catch (SQLException e) {
                // Caso ocorra algum erro na consulta ou ao carregar os dados, exibe uma mensagem de erro.
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar funcionários: " + e.getMessage());
        }
    }

        private void carregarDadoProfissional() {
                listaDadoProfissional.clear(); // Limpa a lista de dados profissionais antes de carregar novos dados.
                try (Connection conn = Database.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT dpf.idprof, dp.nome_completo,dpf.cargo, dpf.departamento, dpf.funcao, dpf.maquinas, dpf.admissao, dpf.salario, dpf.dadosbancarios, dpf.beneficios, dpf.escolaridade, dpf.ctps, dpf.pisPasep, dpf.contrato, dpf.horario, dpf.acidentes, dpf.advertencias, dpf.dados_pessoais FROM dadospessoais dp JOIN dadosprofissionais dpf ON dp.cpf = dpf.dados_pessoais")) {
  
                    // Processa cada linha retornada pela consulta e adiciona os dados na lista.
                    while (rs.next()) {
                        listaDadoProfissional.add(new DadoProfissional (rs.getInt("idprof"), 
                                                                        rs.getString("nome_completo"),
                                                                        rs.getString("cargo"), 
                                                                        rs.getString("departamento"), 
                                                                        rs.getString("funcao"), 
                                                                        rs.getString("maquinas"), 
                                                                        rs.getString("admissao"), 
                                                                        rs.getString("salario"), 
                                                                        rs.getString("dadosbancarios"), 
                                                                        rs.getString("beneficios"), 
                                                                        rs.getString("escolaridade"), 
                                                                        rs.getString("ctps"), 
                                                                        rs.getString("pisPasep"), 
                                                                        rs.getString("contrato"), 
                                                                        rs.getString("horario"), 
                                                                        rs.getString("acidentes"), 
                                                                        rs.getString("advertencias"),
                                                                        rs.getString("dados_pessoais")
                                                                        )
                                                    );
                    }
                    // Define a lista de dados profissionais como a fonte de dados para a tabela.
                    tableDadoProfissional.setItems(listaDadoProfissional); // Define a lista de dados profissionais como a fonte de dados para a tabela.
                } catch (SQLException e) {
                    // Caso ocorra algum erro na consulta ou ao carregar os dados, exibe uma mensagem de erro.
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar funcionários: " + e.getMessage());
            }
        }

        @FXML public void filtrarDadopessoal() {
        // Cria uma lista filtrada a partir da lista original de dados pessoais.   
        FilteredList<DadoPessoal> dadosFiltrados = new FilteredList<>(listaDadoPessoal, p -> true); // Cria uma lista filtrada a partir da lista original de dados pessoais.

        dadosFiltrados.setPredicate(dadopessoal -> {
            // Checa se o nome do funcionário corresponde ao valor inserido no filtro.
            if (!filtroNomeFunc.getText().isEmpty() && !dadopessoal.getNome_completo().toLowerCase().contains(filtroNomeFunc.getText().toLowerCase())) {
                return false;
            }
            if (!filtroDataNascimentoFunc.getText().isEmpty() && !dadopessoal.getData_nascimento().toLowerCase().contains(filtroDataNascimentoFunc.getText().toLowerCase())) {
                return false;
            }
            if (filtroSexoFunc.getValue() != null && !filtroSexoFunc.getValue().isEmpty() && !dadopessoal.getSexo().toLowerCase().contains(filtroSexoFunc.getValue().toLowerCase())) {
                return false;
            }
            if (filtroEstadoCivilFunc.getValue() != null && !filtroEstadoCivilFunc.getValue().isEmpty() && !dadopessoal.getEstado_civil().toLowerCase().contains(filtroEstadoCivilFunc.getValue().toLowerCase())) {
                return false;
            }
            if (filtroNaturalidadeFunc.getText() .isEmpty() && !dadopessoal.getNaturalidade().toLowerCase().contains(filtroNaturalidadeFunc.getText().toLowerCase())) {
                return false; 
            }
            if (!filtroCpfFunc.getText().isEmpty() && !dadopessoal.getCpf().toLowerCase().contains(filtroCpfFunc.getText().toLowerCase())) {
                return false;
            }
            if (!filtroRgFunc.getText().isEmpty() && !dadopessoal.getRg().toLowerCase().contains(filtroRgFunc.getText().toLowerCase())) {
                return false;
            }

            if (!filtroEnderecoFunc.getText().isEmpty() && !dadopessoal.getEndereco().toLowerCase().contains(filtroEnderecoFunc.getText().toLowerCase())) {
                return false;
            }
            if (filtroTipoSanguineoFunc.getValue() != null && !filtroTipoSanguineoFunc.getValue().isEmpty() && !dadopessoal.getTipo_sanguineo().toLowerCase().contains(filtroTipoSanguineoFunc.getValue().toLowerCase())) {
                return false;
            }
            // Outros filtros semelhantes para as demais colunas.
            return true;
        });
        // Define a lista filtrada como a fonte de dados para a tabela.
        tableDadoPessoal.setItems(dadosFiltrados);
    }

        @FXML
        public void filtrarDadoProfissional() {
            // Cria uma lista filtrada a partir da lista original de dados profissionais.
            FilteredList<DadoProfissional> dadosFiltrados = new FilteredList<>(listaDadoProfissional, p -> true);
    
            // Define o critério de filtragem com base nos campos de filtro preenchidos pelo usuário.
            dadosFiltrados.setPredicate(DadoProfissional -> {

                // Verifica se o campo de cargo está preenchido e se o cargo do item atual contém o valor filtrado.
                if (!filtrocargo.getText().isEmpty() && !DadoProfissional.getCargo().toLowerCase().contains(filtrocargo.getText().toLowerCase())) {
                    return false; // Se não contiver, o item é descartado da lista.
                }
                if (filtrodepartamento.getValue() != null && !filtrodepartamento.getValue().isEmpty() && !DadoProfissional.getDepartamento().toLowerCase().contains(filtrodepartamento.getValue().toLowerCase())) {
                    return false;
                }
                
                if (!filtroadmissao.getText().isEmpty() && !DadoProfissional.getAdmissao().toLowerCase().contains(filtroadmissao.getText().toLowerCase())) {
                    return false;
                }
                if (!filtrosalario.getText().isEmpty() && !DadoProfissional.getSalario().toLowerCase().contains(filtrosalario.getText().toLowerCase())) {
                    return false;
                }
                if (filtroescolaridade.getValue() !=null && !filtroescolaridade.getValue().isEmpty() && !DadoProfissional.getEscolaridade().toLowerCase().contains(filtroescolaridade.getValue().toLowerCase())) {
                    return false;
                }

                if (filtrocontrato.getValue() != null && !filtrocontrato.getValue().isEmpty() && !DadoProfissional.getContrato().toLowerCase().contains(filtrocontrato.getValue().toLowerCase())) {
                    return false;
                }
                                
                if (!filtrohorario.getText().isEmpty() && !DadoProfissional.getHorario().toLowerCase().contains(filtrohorario.getText().toLowerCase())) {
                    return false;
                }
                if (!filtroacidentes.getText().isEmpty() && !DadoProfissional.getAcidentes().toLowerCase().contains(filtroacidentes.getText().toLowerCase())) {
                    return false;
                }
               if (!filtrobeneficios.getText().isEmpty() && !DadoProfissional.getBeneficios().toLowerCase().contains(filtrobeneficios.getText().toLowerCase())) {

               }             
    
                // Se passou por todos os filtros, o item será incluído na tabela.
                return true;
            });
    
            // Atualiza a tabela com os dados filtrados.
            tableDadoProfissional.setItems(dadosFiltrados);
        }

    // Limpa os filtros de texto e seleção.
    @FXML
    public void limparFiltro() {
        filtroNomeFunc.clear();
        filtroDataNascimentoFunc.clear();
        filtroSexoFunc.setValue(null);
        filtroEstadoCivilFunc.setValue(null);
        filtroNaturalidadeFunc.clear();
        filtroCpfFunc.clear();
        filtroRgFunc.clear();
        filtroEnderecoFunc.clear();
        filtroTipoSanguineoFunc.setValue(null);

        tableDadoPessoal.setItems(listaDadoPessoal);

        filtrocargo.clear();
        filtrodepartamento.setValue(null);
        filtroadmissao.clear();
        filtrosalario.clear();
        filtrobeneficios.clear();
        filtroescolaridade.setValue(null);
        filtrocontrato.setValue(null);
        filtrohorario.clear();
        filtroacidentes.clear();
       

        tableDadoProfissional.setItems(listaDadoProfissional);   // Limpa os filtros para os dados profissionais também.
    }

    @FXML
public void excluirAtualizar() {
    DadoPessoal dadoPessoalSelecionado = tableDadoPessoal.getSelectionModel().getSelectedItem();
    DadoProfissional dadoProfissionalSelecionado = tableDadoProfissional.getSelectionModel().getSelectedItem();
    String excluirCpf = null; //ponte

    if (dadoPessoalSelecionado != null) {
        excluirCpf  = dadoPessoalSelecionado.getCpf(); // cpf puxa a corrente dos dados pessoais
    } else if (dadoProfissionalSelecionado != null) {
        excluirCpf  = dadoProfissionalSelecionado.getDados_pessoais(); // chave puxa corrente dos dados profissionais
    } else {
        mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um funcionário para excluir!");
        return; // Importante sair da função se nada estiver selecionado
    }

    if (excluirCpf  != null && !excluirCpf .isEmpty()) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM dadosprofissionais WHERE dados_pessoais = ?");
             PreparedStatement stmt1= conn.prepareStatement("DELETE FROM dadospessoais WHERE cpf = ?")) {

            stmt.setString(1, excluirCpf );
            stmt1.setString(1, excluirCpf );

            carregarDadoPessoal();
            carregarDadoProfissional();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Funcionário excluído com sucesso!");

        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao excluir funcionário: " + e.getMessage());
        }
    }
    limparCamposAtualizacao();
}

    public String formatarCPF(String cpf) {
        // Remover caracteres não numéricos para garantir que tenhamos apenas os dígitos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos antes de formatar
        if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." +
                   cpf.substring(3, 6) + "." +
                   cpf.substring(6, 9) + "-" +
                   cpf.substring(9, 11);
        } else {
            // Se o CPF não tiver 11 dígitos, retorna a string original ou uma mensagem de erro
            return cpf; // Ou poderia retornar null ou lançar uma exceção informando o erro
        }
    }

    // Método utilitário para exibir alertas na tela com diferentes tipos de mensagens.
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo); // Define o título da janela de alerta.
        alerta.setHeaderText(null); // Remove o cabeçalho.
        alerta.setContentText(mensagem); // Define o texto da mensagem.
        alerta.showAndWait();; // Exibe o alerta e espera o usuário fechar.
    }
}
