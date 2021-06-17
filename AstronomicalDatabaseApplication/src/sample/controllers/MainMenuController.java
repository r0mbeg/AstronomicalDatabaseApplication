package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
    //добавление объектов - надписей, кнопок, полей для ввода и т.д.
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addObjectButton;

    @FXML
    private Button findObjectButton;



    @FXML
    void initialize() {
        //обработка нажатия на кнопку перехода к поиску объектов
        findObjectButton.setOnAction(e -> {
            findObjectButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/FindObj.fxml"));

            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        //обработка нажатия на кнопку перехода к добавлению объектов
        addObjectButton.setOnAction(e -> {
            addObjectButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/NewObjAdd.fxml"));

            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }
}

