/*
    CARA TAMBAHIN CLASS INI KE CSV
    - Ke resources/dataset/data.csv
    - Tambah com.kamus.gimmick.easteregg.HelloGimmick
    - Selesai
*/


package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import com.kamus.gimmick.tree.GimmickNode;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.util.Objects;

// ================= WAJIB ADA CONSTRUCTOR DAN INTERFACE =================
public class HelloGimmick implements GimmickInterface {

    private MainController controller;

    public HelloGimmick(MainController controller) {
        this.controller = controller;
    }


    // ================= TAMPILAN GIMMICK =================
    @Override
    public Node run() {
        Label label = new Label("Hello Gimmick!");
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");

        StackPane pane = new StackPane(label);
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/abu.jpg")));
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        pane.setBackground(new Background(background));
        // pane.setPrefSize(400, 200);

        return pane;
    }

}
