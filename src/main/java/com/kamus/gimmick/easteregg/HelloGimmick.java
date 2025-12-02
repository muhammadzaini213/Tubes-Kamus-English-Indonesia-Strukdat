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
        pane.setStyle("-fx-background-color: lightgray;");
        pane.setPrefSize(400, 200);

        return pane;
    }

}
