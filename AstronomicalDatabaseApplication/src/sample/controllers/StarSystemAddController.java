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

public class StarSystemAddController {

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
    private TextField GalaxyNameTextField;

    @FXML
    private TextField ConstellationNameTextField;

    @FXML
    void initialize() {
        ErrorLabel.setText("An object with this name already exists" + "\n or you entered the wrong galaxy");
        unsetVisibleAll();
        DeclinationTextField.setVisible(true);
        RightAscensionTextField.setVisible(true);
        DistanceTextField.setVisible(true);
        GalaxyNameTextField.setVisible(true);
        ConstellationNameTextField.setVisible(true);
        AddButton.setVisible(true);


        AddButton.setOnAction(e -> {

            DatabaseHandler dH = new DatabaseHandler();
            if (dH.objExist("galaxy", GalaxyNameTextField.getText().trim()) == true &&
                    dH.objExist("star_system", addTextField.getText().trim()) == false) {
            if (!ConstellationNameTextField.getText().trim().isEmpty()) {
                dH.insertStarSystem(addTextField.getText(),
                        DistanceTextField.getText(),
                        DeclinationTextField.getText(),
                        RightAscensionTextField.getText(),
                        GalaxyNameTextField.getText(),
                        ConstellationNameTextField.getText());
            } else {
                dH.insertStarSystem(addTextField.getText(),
                        DistanceTextField.getText(),
                        DeclinationTextField.getText(),
                        RightAscensionTextField.getText(),
                        GalaxyNameTextField.getText(),
                        "");
                }
                unsetVisibleAll();
                SuccessAddLabel.setVisible(true);
                AddButton.setVisible(false);
                addTextField.setVisible(false);
                backButton.setVisible(true);
            } else {
                unsetVisibleAll();

                shakeSas();

                ErrorLabel.setVisible(true);
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


        DeclinationTextField.setVisible(false);
        RightAscensionTextField.setVisible(false);
        DistanceTextField.setVisible(false);
        GalaxyNameTextField.setVisible(false);
        ConstellationNameTextField.setVisible(false);
        AddButton.setVisible(false);
        mandatoryLabel.setVisible(false);
        SuccessAddLabel.setVisible(false);
        ErrorLabel.setVisible(false);

    }
}
