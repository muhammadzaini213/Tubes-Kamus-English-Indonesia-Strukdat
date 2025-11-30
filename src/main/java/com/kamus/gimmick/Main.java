package com.kamus.gimmick;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // =============== ABAIKAN KEGILAAN INI ===============
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Kamus JavaFX");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
