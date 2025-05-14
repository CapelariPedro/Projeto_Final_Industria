package com.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty setor = new SimpleStringProperty();

    public Funcionario(int id, String nome) {
        this.id.set(id);
        this.nome.set(nome);
        this.setor.set("");
    }

    public Funcionario(int id, String nome, String setor) {
        this.id.set(id);
        this.nome.set(nome);
        this.setor.set(setor);
    }

    // Métodos Property
    @SuppressWarnings("exports")
    public IntegerProperty idProperty() { return id; }
    @SuppressWarnings("exports")
    public StringProperty nomeProperty() { return nome; }
    @SuppressWarnings("exports")
    public StringProperty setorProperty() { return setor; }

    // Getters e Setters tradicionais
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    
    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }
    
    public String getSetor() { return setor.get(); }
    public void setSetor(String setor) { this.setor.set(setor); }

    @Override
    public String toString() {
        return nome.get();
    }
}