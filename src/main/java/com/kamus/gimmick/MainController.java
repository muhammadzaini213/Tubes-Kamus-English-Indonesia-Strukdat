package com.kamus.gimmick;

import com.kamus.gimmick.dictionary.EnglishDictionary;
import com.kamus.gimmick.dictionary.IndonesianDictionary;
import com.kamus.gimmick.easteregg.GimmickInterface;
import com.kamus.gimmick.tree.GimmickNode;
import com.kamus.gimmick.tree.RedBlackTree;
import com.kamus.gimmick.utils.Language;
import com.kamus.gimmick.utils.State;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MainController {

    private static final String dataDir = "src/main/resources/dataset/data.csv";

    private State currentState;
    private boolean updating = false;
    private RedBlackTree gimmickTree = new RedBlackTree();

    @FXML
    private TextArea textAreaLeft;
    @FXML
    private TextArea textAreaRight;
    @FXML
    private ComboBox<Language> languageBoxLeft;
    @FXML
    private ComboBox<Language> languageBoxRight;

    @FXML
    private AnchorPane gimmickPane;

    private EnglishDictionary englishDict;
    private IndonesianDictionary indonesianDict;

    // ================= TRANSLATION LOGIC =================
    private String translate(String text) {
        if (text == null || text.isEmpty())
            return "";

        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String translated;

            if (currentState == State.INDONESIA_ENGLISH) {
                translated = indonesianDict.find(word.toLowerCase());
            } else {
                translated = englishDict.find(word.toLowerCase());
            }

            if (translated == null)
                translated = "[" + word + "]";
            result.append(translated).append(" ");
        }
        return result.toString().trim();
    }

    // ================= SETUP =================
    @FXML
    public void initialize() {
        englishDict = new EnglishDictionary();
        indonesianDict = new IndonesianDictionary();

        if (!englishDict.loadAllData(dataDir))
            System.out.println("English dictionary loading failed");
        if (!indonesianDict.loadAllData(dataDir))
            System.out.println("Indonesian dictionary loading failed");

        setupComboBox();
        setupTextAreas();

        System.out.println("Dictionary Loaded: EN(" + englishDict.getSize() +
                ") | ID(" + indonesianDict.getSize() + ")");
    }

    private void setupComboBox() {
        languageBoxLeft.getItems().addAll(Language.values());
        languageBoxRight.getItems().addAll(Language.values());

        languageBoxLeft.getSelectionModel().select(Language.INDONESIA);
        languageBoxRight.getSelectionModel().select(Language.ENGLISH);
        currentState = State.INDONESIA_ENGLISH;

        languageBoxLeft.valueProperty().addListener((obs, oldVal, newVal) -> updateLanguageSelection());
        languageBoxRight.valueProperty().addListener((obs, oldVal, newVal) -> updateLanguageSelection());
    }

    private void updateLanguageSelection() {
        if (updating)
            return;
        updating = true;

        Language left = languageBoxLeft.getValue();
        languageBoxRight.getSelectionModel().select(left == Language.ENGLISH ? Language.INDONESIA : Language.ENGLISH);
        updateCurrentState();
        swapTextAreas();

        updating = false;
    }

    private void setupTextAreas() {
        textAreaRight.setEditable(false);
        textAreaLeft.textProperty().addListener((obs, oldText, newText) -> {
            if (updating)
                return;
            updating = true;

            textAreaRight.setText(translate(newText));

            String key = newText.trim().toLowerCase();
            System.out.println("Input: " + newText);
            System.out.println("Key: " + key);
            GimmickInterface gimmick;
            if (currentState == State.INDONESIA_ENGLISH) {
                gimmick = indonesianDict.getGimmick(key, this);
            } else {
                gimmick = englishDict.getGimmick(key, this);
            }
            System.out.println("Gimmick: " + gimmick);
            if (gimmick != null) {
                Node pane = gimmick.run();
                showGimmick(pane);
            }

            updating = false;
        });
    }

    // ================= COMBOBOX SWITCHER =================
    private void updateCurrentState() {
        Language left = languageBoxLeft.getValue();
        currentState = (left == Language.ENGLISH) ? State.ENGLISH_INDONESIA : State.INDONESIA_ENGLISH;
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

    // ================= GIMMICK =================
    public void showGimmick(javafx.scene.Node node) {
        gimmickPane.getChildren().setAll(node);
    }

}
