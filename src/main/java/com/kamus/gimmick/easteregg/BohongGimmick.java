/*
    CARA TAMBAHIN CLASS INI KE CSV
    - Ke resources/dataset/data.csv
    - Tambah com.kamus.gimmick.easteregg.HelloGimmick
    - Selesai
*/


package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

// ================= WAJIB ADA CONSTRUCTOR DAN INTERFACE =================
public class BohongGimmick implements GimmickInterface {

    private MainController controller;

    public BohongGimmick(MainController controller) {
        this.controller = controller;
    }


    // ================= TAMPILAN GIMMICK =================
    @Override
    public Node run() {
        Label label = new Label("Mencium aroma kebohongan");
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");

        StackPane pane = new StackPane(label);
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bohong.jpg")));
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        pane.setBackground(new Background(background));
        pane.setPrefSize(400, 200);

        return pane;
    }

}
