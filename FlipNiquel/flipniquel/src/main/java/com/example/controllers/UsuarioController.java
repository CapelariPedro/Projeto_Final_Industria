package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

import com.example.database.Database;

public class UsuarioController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField passSenha;
    @FXML private ComboBox<String> cmbSetorLogin;


    @FXML
    public void initialize() {
        
        cmbSetorLogin.getItems().addAll("RH", "Financeiro", "Produção", "Estoque", "Controle de Qualidade", "Automação", "Maquinário");
    }

    @FXML
    public void Logar(){
        System.out.println("Usuario: " + txtUsuario.getText());
        System.out.println("Senha: " + passSenha.getText());
        System.out.println("Setor: " + cmbSetorLogin.getValue());

        String usuario = txtUsuario.getText();
        String senha = passSenha.getText();
        String setorSelecionado = cmbSetorLogin.getValue();



        if (usuario.isEmpty() || senha.isEmpty() || setorSelecionado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        if (setorSelecionado == null || setorSelecionado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
        }

        if (setorSelecionado == "RH") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioRH WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");

                    //Encaminha para a tela de RH
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/rh.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Financeiro") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioFinanceiro WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Financeiro.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Produção") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioProducao WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/TelaProducao.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Estoque") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioEstoque WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Almoxarifado.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Controle de Qualidade") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioQA WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ControleQualidade.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Automação") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioAutomacao WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/automacao.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        if (setorSelecionado == "Maquinário") {
            try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarioMaquinario WHERE usuario = ? AND senha = ?")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (setorSelecionado == null || setorSelecionado.isEmpty()) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Setor não selecionado", "Por favor, selecione um setor.");
                } else {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Login bem-sucedido", "Bem-vindo, " + usuario + "!");
                    // Aqui você pode redirecionar para outra tela
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/MonitoramentoView.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) txtUsuario.getScene().getWindow();
                    stage.setTitle("Flip Niquel");
                    Image image = new Image(this.getClass().getResource("/com/example/logoFabrica.png").toExternalForm());
                    stage.getIcons().add(image);
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", "Usuário ou senha inválidos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        }

        
    }



    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
