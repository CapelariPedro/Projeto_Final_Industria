package com.example.models;

import java.time.LocalDateTime;

public class Producao {
    private int id;
    private int quantidade;
    private LocalDateTime dataProducao;
    private String nomeFuncionario;
    private String nomeMaquina;
    private String nomeProduto;

    public Producao(int id, String nomeFuncionario, String nomeMaquina, String nomeProduto,
    int quantidade, LocalDateTime dataProducao) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeMaquina = nomeMaquina;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.dataProducao = dataProducao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataProducao() { return dataProducao; }
    public void setDataProducao(LocalDateTime dataProducao) { this.dataProducao = dataProducao; }

    public String getNomeFuncionario() { return nomeFuncionario; }
    public void setNomeFuncionario(String nomeFuncionario) { this.nomeFuncionario = nomeFuncionario; }

    public String getNomeMaquina() { return nomeMaquina; }
    public void setNomeMaquina(String nomeMaquina) { this.nomeMaquina = nomeMaquina; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    @Override
    public String toString() {
        return String.format("Funcionário: %s | Máquina: %s | Produto: %s",
                nomeFuncionario != null ? nomeFuncionario : "N/A",
                nomeMaquina != null ? nomeMaquina : "N/A",
                nomeProduto != null ? nomeProduto : "N/A");
    }
}