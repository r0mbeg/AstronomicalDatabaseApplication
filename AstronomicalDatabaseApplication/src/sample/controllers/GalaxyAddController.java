package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.anims.Shake;

public class GalaxyAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ObjFindLabel;

    @FXML
    private ImageView objFindImgView;

    @FXML
    private TextField addTextField;

    @FXML
    private Button backButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextField TypeTextField;

    @FXML
    private TextField DeclinationTextField;

    @FXML
    private TextField RightAscensionTextField;

    @FXML
    private TextField DistanceTextField;

    @FXML
    private Label mandatoryLabel;

    @FXML
    private Label SuccessAddLabel;

    @FXML
    private Label ErrorLabel;

    @FXML
    void initialize() {

        unsetVisibleAll();
        DeclinationTextField.setVisible(true);
        RightAscensionTextField.setVisible(true);
        TypeTextField.setVisible(true);
        DistanceTextField.setVisible(true);
        AddButton.setVisible(true);

        AddButton.setOnAction(e -> {
            DatabaseHandler dH = new DatabaseHandler();
                if (!addTextField.getText().trim().equals("")) {
                    if (!dH.objExist("galaxy", addTextField.getText().trim())) {
                        dH.insertGalaxy(addTextField.getText(),
                                DistanceTextField.getText(),
                                DeclinationTextField.getText(),
                                RightAscensionTextField.getText(),
                                TypeTextField.getText());
                        unsetVisibleAll();
                        SuccessAddLabel.setVisible(true);
                        AddButton.setVisible(false);
                        addTextField.setVisible(false);
                        backButton.setVisible(true);
                    } else {
                        unsetVisibleAll();
                        shakeSas();
                    }
                }



                });


        backButton.setOnAction(e -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/newObjAdd.fxml"));

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

    private void shakeSas() {
        Shake addTextFieldAnim = new Shake(addTextField);
        addTextFieldAnim.playAnim();
        ErrorLabel.setVisible(true);
    }

    public void unsetVisibleAll() {
        TypeTextField.setVisible(false);
        DeclinationTextField.setVisible(false);
        RightAscensionTextField.setVisible(false);
        DistanceTextField.setVisible(false);
        AddButton.setVisible(false);
        mandatoryLabel.setVisible(false);
        SuccessAddLabel.setVisible(false);
        ErrorLabel.setVisible(false);


    }
}
