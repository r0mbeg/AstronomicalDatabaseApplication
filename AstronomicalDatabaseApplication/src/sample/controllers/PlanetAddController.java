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

public class PlanetAddController {

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
    private TextField MassTextField;

    @FXML
    private TextField RadiusTextField;

    @FXML
    private TextField TypeTextField;

    @FXML
    private Label mandatoryLabel;

    @FXML
    private Label SuccessAddLabel;

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField StarSystemNameTextField;

    @FXML
    private TextField SemiMajorAxisTextField;

    @FXML
    private TextField EccentricityTextField;

    @FXML
    void initialize() {
        ErrorLabel.setText("An object with this name already exists" + "\n or you entered the wrong star system");
        unsetVisibleAll();
        TypeTextField.setVisible(true);
        RadiusTextField.setVisible(true);
        MassTextField.setVisible(true);
        SemiMajorAxisTextField.setVisible(true);
        EccentricityTextField.setVisible(true);
        StarSystemNameTextField.setVisible(true);
        AddButton.setVisible(true);

        AddButton.setOnAction(e ->{
            DatabaseHandler dH = new DatabaseHandler();
            if (dH.objExist("star_system", StarSystemNameTextField.getText().trim()) == true &&
                    dH.objExist("planet", addTextField.getText().trim()) == false) {
                dH.insertPlanet(addTextField.getText(),
                        TypeTextField.getText(),
                        MassTextField.getText(),
                        RadiusTextField.getText(),
                        SemiMajorAxisTextField.getText(),
                        EccentricityTextField.getText(),
                        StarSystemNameTextField.getText());
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

        TypeTextField.setVisible(false);
        RadiusTextField.setVisible(false);
        MassTextField.setVisible(false);
        SemiMajorAxisTextField.setVisible(false);
        EccentricityTextField.setVisible(false);
        StarSystemNameTextField.setVisible(false);
        AddButton.setVisible(false);
        mandatoryLabel.setVisible(false);
        SuccessAddLabel.setVisible(false);
        ErrorLabel.setVisible(false);

    }
}
