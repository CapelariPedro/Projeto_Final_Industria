package com.example.models;

public class FuncionarioManutencao {
    private String cpf;
    private String nomeCompleto;
    private String departamento;
    private String cargo;
    
    public FuncionarioManutencao(String cpf, String nomeCompleto, String departamento, String cargo) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.departamento = departamento;
        this.cargo = cargo;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @Override
    public String toString() {
        return nomeCompleto + " - " + cargo;
    }
}