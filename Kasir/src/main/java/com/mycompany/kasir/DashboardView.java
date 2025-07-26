
package com.mycompany.kasir;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardView {
    private Stage primaryStage;
    private User user;

    public DashboardView(Stage stage, User user) {
        this.primaryStage = stage;
        this.user = user;
    }

    public VBox getView() {
        VBox root = new VBox(16);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        Label welcome = new Label("Selamat datang, " + user.getUsername());
        Button kasirBtn = new Button("Kasir Restoran");
        Button logoutBtn = new Button("Logout");

        kasirBtn.setOnAction(e -> {
            KasirView kasirView = new KasirView(primaryStage, user);
            primaryStage.setScene(new Scene(kasirView.getView(), 900, 540));
        });

        logoutBtn.setOnAction(e -> {
            LoginView loginView = new LoginView(primaryStage);
            primaryStage.setScene(new Scene(loginView.getView(), 860, 540));
        });

        root.getChildren().addAll(welcome, kasirBtn, logoutBtn);
        return root;
    }
}
