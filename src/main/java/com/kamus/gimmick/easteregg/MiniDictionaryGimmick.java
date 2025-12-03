package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import com.kamus.gimmick.utils.State;
import com.kamus.gimmick.utils.Language;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MiniDictionaryGimmick implements GimmickInterface {


private MainController controller;

public MiniDictionaryGimmick(MainController controller) {
    this.controller = controller;
}

@Override
public Node run() {
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));

    HBox topBox = new HBox(20);

    VBox leftBox = new VBox(5);
    ComboBox<Language> languageBoxLeft = new ComboBox<>();
    languageBoxLeft.getItems().addAll(Language.values());
    languageBoxLeft.getSelectionModel().select(Language.INDONESIA);

    TextArea textAreaLeft = new TextArea();
    textAreaLeft.setWrapText(true);
    textAreaLeft.setStyle("-fx-font-size: 16px;");
    VBox.setVgrow(textAreaLeft, Priority.ALWAYS);
    leftBox.getChildren().addAll(languageBoxLeft, textAreaLeft);
    HBox.setHgrow(leftBox, Priority.ALWAYS);

    VBox rightBox = new VBox(5);
    ComboBox<Language> languageBoxRight = new ComboBox<>();
    languageBoxRight.getItems().addAll(Language.values());
    languageBoxRight.getSelectionModel().select(Language.ENGLISH);

    TextArea textAreaRight = new TextArea();
    textAreaRight.setWrapText(true);
    textAreaRight.setEditable(false);
    textAreaRight.setStyle("-fx-font-size: 16px;");
    VBox.setVgrow(textAreaRight, Priority.ALWAYS);
    rightBox.getChildren().addAll(languageBoxRight, textAreaRight);
    HBox.setHgrow(rightBox, Priority.ALWAYS);

    topBox.getChildren().addAll(leftBox, rightBox);
    VBox.setVgrow(topBox, Priority.ALWAYS);

    AnchorPane gimmickPane = new AnchorPane();
    gimmickPane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: gray; -fx-border-width: 1;");
    VBox.setVgrow(gimmickPane, Priority.ALWAYS);

    root.getChildren().addAll(topBox, gimmickPane);

    textAreaLeft.textProperty().addListener((obs, oldText, newText) -> {
        gimmickPane.getChildren().clear();
        if (newText.isEmpty()) {
            textAreaRight.clear();
            return;
        }

        String word = newText.trim().toLowerCase();
        String translation;

        if (controller.getState() == State.INDONESIA_ENGLISH) {
            translation = controller.getIndonesianDict().find(word);
        } else {
            translation = controller.getEnglishDict().find(word);
        }

        if (translation == null) translation = "[Kata tidak ditemukan]";
        textAreaRight.setText(translation);

        GimmickInterface gimmick;
        if (controller.getState() == State.INDONESIA_ENGLISH) {
            gimmick = controller.getIndonesianDict().getGimmick(word, controller);
        } else {
            gimmick = controller.getEnglishDict().getGimmick(word, controller);
        }

        if (gimmick != null) {
            Node gimmickNode = gimmick.run();
            gimmickPane.getChildren().add(gimmickNode);
            AnchorPane.setTopAnchor(gimmickNode, 0.0);
            AnchorPane.setBottomAnchor(gimmickNode, 0.0);
            AnchorPane.setLeftAnchor(gimmickNode, 0.0);
            AnchorPane.setRightAnchor(gimmickNode, 0.0);
        }
    });

    Stage gimmickStage = new Stage();
    gimmickStage.initModality(Modality.APPLICATION_MODAL);
    gimmickStage.setTitle("Mini Kamus Gimmick");
    gimmickStage.setScene(new Scene(root, 1920, 1080));
    gimmickStage.setFullScreenExitHint("");
    gimmickStage.setFullScreen(true);
    gimmickStage.show();

    return new Pane();
}


}
