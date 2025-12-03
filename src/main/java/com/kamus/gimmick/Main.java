package com.kamus.gimmick;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Kamus JavaFX");

        stage.show();

        Platform.runLater(() -> stage.setFullScreen(true));
    }

    public static void main(String[] args) {
        launch();
    }

}
