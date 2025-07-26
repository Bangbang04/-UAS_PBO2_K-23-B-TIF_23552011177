package com.mycompany.kasir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene; // Simpan scene global untuk setRoot()

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Kasir Restoran");

        // Jika ingin memulai dari LoginView (custom Java view)
        LoginView loginView = new LoginView(primaryStage);
        scene = new Scene(loginView.getView(), 860, 540);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Tambahkan method setRoot seperti yang dibutuhkan controller
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Method bantu untuk load FXML
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
