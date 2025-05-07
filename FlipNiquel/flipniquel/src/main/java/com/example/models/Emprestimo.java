package com.example.models;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int equipamentoId;
    private String equipamentoNome;
    private String setorSolicitante;
    private String funcionarioCpf;
    private String funcionarioNome;
    private Date dataInicio;
    private Date dataDevolucao;
    private String status;
    private String observacoes;

    public Emprestimo(int id, int equipamentoId, String equipamentoNome, String setorSolicitante,
                      String funcionarioCpf, String funcionarioNome, Date dataInicio, 
                      Date dataDevolucao, String status, String observacoes) {
        this.id = id;
        this.equipamentoId = equipamentoId;
        this.equipamentoNome = equipamentoNome;
        this.setorSolicitante = setorSolicitante;
        this.funcionarioCpf = funcionarioCpf;
        this.funcionarioNome = funcionarioNome;
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public String getEquipamentoNome() {
        return equipamentoNome;
    }

    public void setEquipamentoNome(String equipamentoNome) {
        this.equipamentoNome = equipamentoNome;
    }

    public String getSetorSolicitante() {
        return setorSolicitante;
    }

    public void setSetorSolicitante(String setorSolicitante) {
        this.setorSolicitante = setorSolicitante;
    }

    public String getFuncionarioCpf() {
        return funcionarioCpf;
    }

    public void setFuncionarioCpf(String funcionarioCpf) {
        this.funcionarioCpf = funcionarioCpf;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * Verifica se o empréstimo está atrasado comparando com a data atual
     * @return true se estiver atrasado, false caso contrário
     */
    public boolean isAtrasado() {
        if (dataDevolucao == null || status.equals("concluído")) {
            return false;
        }
        
        return dataDevolucao.before(new Date()) && !status.equals("concluído");
    }

    @Override
    public String toString() {
        return equipamentoNome + " - " + funcionarioNome + " (" + setorSolicitante + ")";
    }
}