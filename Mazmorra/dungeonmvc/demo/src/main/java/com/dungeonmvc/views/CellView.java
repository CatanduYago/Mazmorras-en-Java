package com.dungeonmvc.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane {
    private ImageView imageView;

    public CellView(Image image) {
        imageView = new ImageView(image);
        getChildren().add(imageView);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }
}
