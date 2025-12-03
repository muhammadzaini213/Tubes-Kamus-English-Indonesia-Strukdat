package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import com.kamus.gimmick.utils.State;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class MiniDictionaryGimmick implements GimmickInterface {

    private MainController controller;

    public MiniDictionaryGimmick(MainController controller) {
        this.controller = controller;
    }

    @Override
    public Node run() {
        VBox container = new VBox(10);
        container.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 10px;");

        Label title = new Label("Mini Kamus (Gimmick)");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField inputField = new TextField();
        inputField.setPromptText("Masukkan kata...");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");

        // =============== AREA GIMMICK ===============
        StackPane gimmickArea = new StackPane();
        gimmickArea.setPrefHeight(150);
        gimmickArea.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px; -fx-border-color: gray;");

        Button translateButton = new Button("Cari");
        translateButton.setOnAction(e -> {
            String word = inputField.getText().trim().toLowerCase();
            if (word.isEmpty()) {
                resultLabel.setText("");
                gimmickArea.getChildren().clear();
                return;
            }

            // translate
            String translation;
            if (controller.getState() == State.INDONESIA_ENGLISH) {
                translation = controller.getIndonesianDict().find(word);
            } else {
                translation = controller.getEnglishDict().find(word);
            }

            if (translation == null) {
                translation = "[Kata tidak ditemukan]";
            }

            resultLabel.setText(word + " = " + translation);

            // tampilkan gimmick di area gimmick
            if (word.equals("hello")) {
                GimmickInterface gimmick = controller.getEnglishDict().getGimmick(word, controller);
                if (gimmick != null) {
                    Node gimmickNode = gimmick.run();
                    gimmickArea.getChildren().setAll(gimmickNode);
                }
            } else {
                gimmickArea.getChildren().clear();
            }
        });

        container.getChildren().addAll(title, inputField, translateButton, resultLabel, gimmickArea);
        container.setPrefSize(400, 400);

        StackPane wrapper = new StackPane(container);
        wrapper.setStyle("-fx-background-color: lightgray; -fx-padding: 10px;");
        return wrapper;
    }
}
