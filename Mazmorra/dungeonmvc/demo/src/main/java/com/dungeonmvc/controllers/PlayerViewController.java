package com.dungeonmvc.controllers;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerViewController implements Observer{
    @FXML
    ImageView portrait;
    @FXML
    Label nameTag;
    @FXML
    Label maxHealthTag;
    @FXML 
    Label currentHealthTag;
    @FXML
    Label strenghtTag;
    @FXML
    Label defenseTag;
    @FXML
    ImageView leftWeaponImg;
    @FXML
    ImageView rightWeaponImg;

    Player player;
    

    @FXML
    private void initialize() {
        System.out.println("Main Character controller loaded");

        player = GameManager.getInstance().getPlayer();
        player.subscribe(this);
        portrait.setImage(new Image(App.class.getResource("images/"+player.getPortrait()+".png").toExternalForm(),portrait.getFitWidth(),portrait.getFitHeight(),true,false));
        leftWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getLeftHand()+".png").toExternalForm(),leftWeaponImg.getFitWidth(),leftWeaponImg.getFitHeight(),true,false));
        rightWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getRightHand()+".png").toExternalForm(),rightWeaponImg.getFitWidth(),rightWeaponImg.getFitHeight(),true,false));

        
        onChange();
    }


    @Override
    public void onChange() {
        nameTag.setText(player.getName());
        leftWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getLeftHand()+".png").toExternalForm(),leftWeaponImg.getFitWidth(),leftWeaponImg.getFitHeight(),true,false));
        rightWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getRightHand()+".png").toExternalForm(),rightWeaponImg.getFitWidth(),rightWeaponImg.getFitHeight(),true,false));

    }

    @Override
    public void onChange(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
}