package com.example.models;

public class Produto {
    private int id;
    private String nome;
    private String lote;
    private String sku;

    public Produto(int id, String nome, String lote, String sku) {
        this.id = id;
        this.nome = nome;
        this.lote = lote;
        this.sku = sku;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    @Override
    public String toString() {
        return nome;
    }
}