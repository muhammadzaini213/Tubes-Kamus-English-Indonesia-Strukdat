package com.kamus.gimmick.easteregg;

import com.kamus.gimmick.MainController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class ThankYouGimmick implements GimmickInterface {


private MainController controller;

public ThankYouGimmick(MainController controller) {
    this.controller = controller;
}

@Override
public Node run() {
    File videoFile = new File("src/main/resources/videos/thankyou.mp4");
    if (!videoFile.exists()) {
        System.err.println("Video not found!");
        return null;
    }

    try {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        if (os.contains("win")) {
            pb= new ProcessBuilder("cmd", "/c", "start", "\"\"", videoFile.getAbsolutePath());
        } else if (os.contains("mac")) {
            pb = new ProcessBuilder("open", videoFile.getAbsolutePath());
        } else { // Linux / Unix
            pb = new ProcessBuilder("xdg-open", videoFile.getAbsolutePath());
        }

        pb.start();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return new Pane();
}


}
