package com.kamus.gimmick;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class MainController {

    @FXML
    private ComboBox<String> languageBoxLeft;

    @FXML
    private ComboBox<String> languageBoxRight;

    @FXML
    public void initialize() {
        languageBoxLeft.getItems().addAll("Indonesia", "English");
        languageBoxRight.getItems().addAll("Indonesia", "English");
    }
}
