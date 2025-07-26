
package com.mycompany.kasir;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginView {
    private Stage primaryStage;
    private UserOperations userOps = new UserOperations();

    public LoginView(Stage stage) { this.primaryStage = stage; }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label title = new Label("Login Kasir Restoran");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nama Pengguna");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Kata Sandi");
        Button loginBtn = new Button("Login");
        Hyperlink linkRegister = new Hyperlink("Belum punya akun? Daftar di sini");

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = userOps.loginUser(username, password);
            if (user != null) {
                DashboardView dashboard = new DashboardView(primaryStage, user);
                primaryStage.setScene(new Scene(dashboard.getView(), 860, 540));
            } else {
                showError("Login gagal. Cek username/password.");
            }
        });

        linkRegister.setOnAction(e -> {
            RegisterView registerView = new RegisterView(primaryStage);
            primaryStage.setScene(new Scene(registerView.getView(), 860, 540));
        });

        root.getChildren().addAll(title, usernameField, passwordField, loginBtn, linkRegister);
        return root;
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
