package com.example.models;

import java.time.LocalDate;

public class DadoProfissional {
    private Integer idprof;
    private String cargo;
    private String departamento;
    private String funcao;
    private String maquinas;
    private LocalDate admissao;
    private String salario;
    private String dadosbancarios;
    private String beneficios;
    private String escolaridade;
    private String ctps;
    private String pisPasep;
    private String contrato;
    private String horario;
    private String acidentes;
    private String advertencias;
    private String nomeCompleto;
    private String dados_pessoais;

    public DadoProfissional(Integer idprof,String nomeCompleto, String cargo, String departamento, String funcao,  String maquinas, LocalDate admissao, String salario, String dadosbancarios, String beneficios,
    String escolaridade, String ctps, String pisPasep, String contrato, String horario, String acidentes, String advertencias, String dados_pessoais) {
        this.idprof = idprof;
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.departamento = departamento;
        this.funcao = funcao;
        this.maquinas = maquinas;
        this.admissao = admissao;
        this.salario = salario;
        this.dadosbancarios = dadosbancarios;
        this.beneficios = beneficios;
        this.escolaridade = escolaridade;
        this.ctps = ctps;
        this.pisPasep = pisPasep;
        this.contrato = contrato;
        this.horario = horario;
        this.acidentes = acidentes;
        this.advertencias = advertencias;
        this.dados_pessoais = dados_pessoais;

}

public String getDados_pessoais() {
    return dados_pessoais;
}

public void setdados_pessoais(String dados_pessoais){
    this.dados_pessoais = dados_pessoais;
}

public String getNomeCompleto() {
    return nomeCompleto;
}
public void setNomeCompleto(String nomeCompleto){
    this.nomeCompleto = nomeCompleto;
}

public int getIdprof() {
    return idprof;
}
public void setIdprof(int idprof){
    this.idprof = idprof;
}

public String getCargo() {
    return cargo;
}

public void setCargo(String cargo){
    this.cargo = cargo;
}

public String getDepartamento() {
    return departamento;
}
public void setDepartamento(String departamento){
    this.departamento = departamento;
}

public String getFuncao() {
    return funcao;
}
public void setFuncao(String funcao){
    this.funcao = funcao;
}

public String getMaquinas() {
    return maquinas;
}
public void setMaquinas(String maquinas){
    this.maquinas = maquinas;
}

public LocalDate getAdmissao(){
    return admissao;
}
public void setAdmissao(LocalDate admissao){
    this.admissao = LocalDate.parse(admissao); // Converte String para LocalDate
}

public String getSalario() {
    return salario;
}
public void setSalario(String salario){
    this.salario = salario;
}

public String getDadosbancarios() {
    return dadosbancarios;
}
public void setDadosbancarios(String dadosbancarios){
    this.dadosbancarios = dadosbancarios;
}

public String getBeneficios() {
    return beneficios;
}
public void setBeneficios(String beneficios){
    this.beneficios = beneficios;
}

public String getEscolaridade() {
    return escolaridade;
}
public void setEscolaridade(String escolaridade){
    this.escolaridade = escolaridade;
}

public String getCtps() {
    return ctps;
}
public void setCtps(String ctps){
    this.ctps = ctps;
}

public String getPis() {
    return pisPasep;
}
public void setPis(String pisPasep){
    this.pisPasep = pisPasep;
}

public String getContrato() {
    return contrato;
}
public void setContrato(String contrato){
    this.contrato = contrato;
}

public String getHorario() {
    return horario;
}
public void setHorario(String horario){
    this.horario = horario;
}

public String getAcidentes() {
    return acidentes;
}
public void setAcidentes(String acidentes){
    this.acidentes = acidentes;
}

public String getAdvertencias() {
    return advertencias;
}
public void setAdvertencias(String advertencias){
    this.advertencias = advertencias;
}

}
