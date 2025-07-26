
package com.mycompany.kasir;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegisterView {
    private Stage primaryStage;
    private UserOperations userOps = new UserOperations();

    public RegisterView(Stage stage) { this.primaryStage = stage; }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label title = new Label("Registrasi Pengguna");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nama Pengguna");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Kata Sandi");
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Konfirmasi Kata Sandi");
        Button registerBtn = new Button("Daftar");
        Hyperlink linkLogin = new Hyperlink("Sudah punya akun? Login di sini");

        registerBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirm = confirmField.getText();
            if (!password.equals(confirm)) {
                showError("Konfirmasi password tidak cocok.");
                return;
            }
            boolean success = userOps.registerUser(username, password, "kasir");
            if (success) {
                showInfo("Registrasi berhasil!");
                LoginView loginView = new LoginView(primaryStage);
                primaryStage.setScene(new Scene(loginView.getView(), 860, 540));
            } else {
                showError("Registrasi gagal. Username sudah digunakan?");
            }
        });

        linkLogin.setOnAction(e -> {
            LoginView loginView = new LoginView(primaryStage);
            primaryStage.setScene(new Scene(loginView.getView(), 860, 540));
        });

        root.getChildren().addAll(title, usernameField, passwordField, confirmField, registerBtn, linkLogin);
        return root;
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
