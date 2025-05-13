package com.example.models;

import java.time.LocalDate;

import javafx.scene.control.Alert;

public class DadoPessoal {
    private int id;
    private String nome_completo;
    private LocalDate data_nascimento;
    private String sexo;
    private String estado_civil;
    private String conjuge;
    private LocalDate data_conjugue;
    private String dependentes;
    private String nacionalidade;
    private String naturalidade;
    private String cpf;
    private String rg;
    private String endereco;
    private String telefone;
    private String email;
    private String filiacao;
    private String tipo_sanguineo;
    private String contato_emergencia;


      
    // Método para validar o CPF
    public boolean validarCPF(String cpf) {
        // Remove qualquer formatação do CPF
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se o CPF é uma sequência repetida (exemplo: 111.111.111.11)
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
            cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
            cpf.equals("99999999999")) {
            return false;
        }

        // Calcula os dígitos verificadores
        int soma = 0, peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * peso--;
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * peso--;
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }

        // Verifica se os dois dígitos verificadores estão corretos
        return cpf.charAt(9) == (char) (digito1 + '0') && cpf.charAt(10) == (char) (digito2 + '0');
    }

    // Método para salvar os dados
    public void salvarDados() {
        String cpf = this.cpf; // Acessa o valor do campo cpf da classe


        // Valida o CPF antes de prosseguir com o salvamento
        if (!validarCPF(cpf)) {
            // Exibe uma mensagem de erro, por exemplo
            exibirMensagemErro("CPF inválido");
            return;  // Não prossegue se o CPF for inválido
        }

        // Prosseguir com o processo de salvar os dados
        // Lógica de salvar o CPF e outros dados
    }

    // Método para exibir uma mensagem de erro
    private void exibirMensagemErro(String mensagem) {
        // Exibindo erro, pode ser um alerta JavaFX
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}


    public DadoPessoal(int id, String nome_completo, LocalDate data_nascimento, String sexo,  String estado_civil, String conjuge, LocalDate data_conjugue, String dependentes, String nacionalidade, String naturalidade,
    String cpf, String rg, String endereco, String telefone, String email, String filiacao, String tipo_sanguineo, String contato_emergencia) {
        this.id = id;
        this.nome_completo = nome_completo;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.estado_civil = estado_civil;
        this.conjuge = conjuge;
        this.data_conjugue = data_conjugue;
        this.dependentes = dependentes;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.filiacao = filiacao;
        this.tipo_sanguineo = tipo_sanguineo;
        this.contato_emergencia = contato_emergencia;
}

public int getId() {
    return id;
}
public void setId(int id){
    this.id = id;
}

public String getNome_completo() {
    return nome_completo;
}
public void setNome_completo(String nome_completo){
    this.nome_completo = nome_completo;
}

public LocalDate getData_nascimento() {
    return data_nascimento;
}
public void setData_nascimento(LocalDate data_nascimento){
    this.data_nascimento = data_nascimento;
}

public String getSexo() {
    return sexo;
}
public void setSexo(String sexo){
    this.sexo = sexo;
}

public String getEstado_civil() {
    return estado_civil;
}
public void setEstado_civil(String estado_civil){
    this.estado_civil = estado_civil;
}

public String getConjuge() {
    return conjuge;
}
public void setConjuge(String conjuge){
    this.conjuge = conjuge;
}

public LocalDate getdata_conjuge() {
    return data_conjugue;
}
public void setdata_conjuge(LocalDate data_conjuge){
    this.data_conjugue = data_conjuge;
}

public String getDependentes(){
    return dependentes;
}
public void setDependentes(String dependentes){
    this.dependentes = dependentes;
}

public String getNacionalidade() {
    return nacionalidade;
}
public void setNacionalidade(String nacionalidade){
    this.nacionalidade = nacionalidade;
}

public String getNaturalidade() {
    return naturalidade;
}
public void setNaturalidade(String naturalidade){
    this.naturalidade = naturalidade;
}

public String getCpf(){
    return cpf;
}
public void setCpf(String cpf){
    this.cpf = cpf;
}

public String getRg() {
    return rg;
}
public void setRg(String rg){
    this.rg = rg;
}

public String getEndereco() {
    return endereco;
}
public void setEndereco(String endereco){
    this.endereco = endereco;
}

public String getTelefone(){
    return telefone;
}
public void setTelefone(String telefone){
    this.telefone = telefone;
}

public String getEmail(){
    return email;
}
public void setEmail(String email){
    this.email = email;
}

public String getFiliacao() {
    return filiacao;
}
public void setFiliacao(String filiacao){
    this.filiacao = filiacao;
}

public String getTipo_sanguineo(){
    return tipo_sanguineo;
}
public void setTipo_sanguineo(String tipo_sanguineo){
    this.tipo_sanguineo = tipo_sanguineo;
}

public String getContato_emergencia() {
    return contato_emergencia;
}
public void setContato_emergencia(String contato_emergencia){
    this.contato_emergencia = contato_emergencia;
}

}