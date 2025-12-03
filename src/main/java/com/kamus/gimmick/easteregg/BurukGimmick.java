package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.util.Objects;

public class BurukGimmick implements GimmickInterface {


private MainController controller;

public BurukGimmick(MainController controller) {
    this.controller = controller;
}

@Override
public Node run() {
    Stage gimmickStage = new Stage();
    gimmickStage.initModality(Modality.APPLICATION_MODAL);
    gimmickStage.setTitle("Buruk Gimmick");
    gimmickStage.setFullScreenExitHint("");
    gimmickStage.setFullScreen(true);  

    StackPane pane = new StackPane();

    Image backgroundImage = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/buruk.jpeg")));
    BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1080, 1080, true, true, true, true));
    pane.setBackground(new Background(background));

    gimmickStage.setScene(new Scene(pane, 1080, 1080));
    gimmickStage.show();

    return new Pane();
}


}
