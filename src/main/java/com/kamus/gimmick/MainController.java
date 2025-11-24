package com.kamus.gimmick;

import com.kamus.gimmick.dictionary.EnglishDictionary;
import com.kamus.gimmick.layoutUtils.Language;
import com.kamus.gimmick.layoutUtils.State;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MainController {

    private State currentState; // State ini cek aja di State.java di folder Utils
    private boolean updating = false;

    @FXML
    private TextArea textAreaLeft;
    @FXML
    private TextArea textAreaRight;
    @FXML
    private ComboBox<Language> languageBoxLeft;
    @FXML
    private ComboBox<Language> languageBoxRight;

    private EnglishDictionary tree;

    // ================= LOGIC =================
    private String translate(String text) {
        if (text == null || text.isEmpty())
            return "";

        switch (currentState) {
            case INDONESIA_ENGLISH:
                return "[TRANSLASI ID → EN]: " + text;
            case ENGLISH_INDONESIA:
                return "[TRANSLASI EN → ID]: " + text;
            default:
                return text;
        }
    }

    // ================= SETUP =================
    @FXML
    public void initialize() {
        tree = new EnglishDictionary();
        setupComboBox();
        setupTextAreas();
    }

    private void setupComboBox() {
        languageBoxLeft.getItems().addAll(Language.values());
        languageBoxRight.getItems().addAll(Language.values());

        languageBoxLeft.getSelectionModel().select(Language.INDONESIA);
        languageBoxRight.getSelectionModel().select(Language.ENGLISH);
        currentState = State.INDONESIA_ENGLISH;

        languageBoxLeft.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (updating || newVal == null)
                return;
            updating = true;

            if (newVal == Language.ENGLISH) {
                languageBoxRight.getSelectionModel().select(Language.INDONESIA);
            } else {
                languageBoxRight.getSelectionModel().select(Language.ENGLISH);
            }
            swapTextAreas();

            updateCurrentState();
            updating = false;
        });

        languageBoxRight.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (updating || newVal == null)
                return;
            updating = true;

            if (newVal == Language.ENGLISH) {
                languageBoxLeft.getSelectionModel().select(Language.INDONESIA);
            } else {
                languageBoxLeft.getSelectionModel().select(Language.ENGLISH);
            }
            swapTextAreas();

            updateCurrentState();
            updating = false;
        });
    }

    private void setupTextAreas() {
        textAreaRight.setEditable(false);
        textAreaLeft.textProperty().addListener((obs, oldText, newText) -> {
            if (updating)
                return;
            updating = true;

            String result = translate(newText);
            textAreaRight.setText(result);

            updating = false;
        });
    }

    // ================= COMBOBOX SWITCHER =================
    private void updateCurrentState() {
        Language left = languageBoxLeft.getValue();
        Language right = languageBoxRight.getValue();

        if (left == Language.ENGLISH && right == Language.INDONESIA) {
            currentState = State.ENGLISH_INDONESIA;
        } else if (left == Language.INDONESIA && right == Language.ENGLISH) {
            currentState = State.INDONESIA_ENGLISH;
        } else {
            currentState = null;
        }

        System.out.println("Current state: " + currentState);
    }

    private void swapTextAreas() {
        String temp = textAreaLeft.getText();
        textAreaLeft.setText(textAreaRight.getText());
        textAreaRight.setText(temp);
    }

    // ================= GETTER =================
    public String getLeftText() {
        return textAreaLeft.getText().toLowerCase();
    }

    public String getRightText() {
        return textAreaRight.getText().toLowerCase();
    }

    public State getState() {
        return currentState;
    }

    // ================= SETTER =================
    public void setLeftText(String text) {
        textAreaLeft.setText(text);
    }

    public void setRightText(String text) {
        textAreaRight.setText(text);
    }

}
