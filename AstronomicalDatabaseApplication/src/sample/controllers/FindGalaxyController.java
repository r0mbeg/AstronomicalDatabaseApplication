package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;

public class FindGalaxyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ObjFindLabel;

    @FXML
    private ImageView objFindImgView;

    @FXML
    private TextField findTextField;

    @FXML
    private Button findObjButton;

    @FXML
    private Button backButtonFind;

    @FXML
    private ComboBox<?> findObjComboBox;

    @FXML
    private Button objFindEditButton;

    @FXML
    private TextField TypeTextField;

    @FXML
    private Label TypeLabel;

    @FXML
    private TextField DeclinationTextField;

    @FXML
    private Label DeclinationLabel;

    @FXML
    private TextField RightAscensionTextField;

    @FXML
    private Label RightAscensionLabel;

    @FXML
    private TextField DistanceTextField;

    @FXML
    private Label DistanceLabel;

    @FXML
    void initialize() {

        findTextField.setText(findTextField.getText().trim());



        backButtonFind.setOnAction(e -> {
            backButtonFind.getScene().getWindow().hide();
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

    }


    public void unsetVisibleAll() {
        objFindEditButton.setVisible(false);
        TypeTextField.setVisible(false);
        TypeLabel.setVisible(false);
        DeclinationTextField.setVisible(false);
        DeclinationLabel.setVisible(false);
        RightAscensionLabel.setVisible(false);
        RightAscensionTextField.setVisible(false);
        DistanceLabel.setVisible(false);
        DistanceTextField.setVisible(false);
    }
}
