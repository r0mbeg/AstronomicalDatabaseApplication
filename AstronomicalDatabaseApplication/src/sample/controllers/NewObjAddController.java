package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sample.anims.Shake;

public class NewObjAddController {
    //массив для списка типов объектов в выпадающем меню
    ObservableList<String> spaceObjects = FXCollections.observableArrayList();


    //добавление объектов - надписей, кнопок, полей для ввода и т.д.
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
    private Button backButtonAdd;

    @FXML
    private ComboBox<String> addComboBox;

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
    private TextField RadiusTextField;

    @FXML
    private TextField MassTextField;

    @FXML
    private TextField SpectralClassTextField;

    @FXML
    private TextField MagnitudeTextField;

    @FXML
    private TextField SemiMajorAxisTextField;

    @FXML
    private TextField EccentricityTextField;

    @FXML
    private TextField GalaxyNameTextField;

    @FXML
    private TextField StarNameTextField;

    @FXML
    private TextField StarSystemNameTextField;

    @FXML
    private TextField PlanetNameTextField;

    @FXML
    private TextField ConstellationNameTextField;

    @FXML
    private Label mandatoryLabel;

    @FXML
    private Label SuccessAddLabel;

    @FXML
    private Label ErrorLabel;

    @FXML
    void initialize() {
        ErrorLabel.setText("An object with this name already exists");
        //делаем все поля для ввода невидимыми
        unsetVisibleAll();


        //получаем список типов объектов в выпадающем меню
        try {
            DatabaseHandler db = new DatabaseHandler();
            ResultSet rsTables = db.getTables();
            while(rsTables.next()) {
                spaceObjects.add(rsTables.getString(1));
            }
            addComboBox.setItems(spaceObjects);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        //обработка нажатия выпадающий список объектов
        addComboBox.setOnAction(e -> {
            unsetVisibleAll();
            AddButton.setVisible(true);
            mandatoryLabel.setVisible(true);
            switch (addComboBox.getValue()) {
            //в зависимости от типа объекта
                //отображаем соответствующие поля для ввода
                case "galaxy":

                    addComboBox.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/GalaxyAdd.fxml"));

                    try {
                        loader.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    break;
                case "star_system":
                    addComboBox.getScene().getWindow().hide();
                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("/sample/view/StarSystemAdd.fxml"));

                    try {
                        loader2.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Parent root2 = loader2.getRoot();
                    Stage stage2 = new Stage();
                    stage2.setScene(new Scene(root2));
                    stage2.show();
                    break;
                case "star":
                    addComboBox.getScene().getWindow().hide();
                    FXMLLoader loader3 = new FXMLLoader();
                    loader3.setLocation(getClass().getResource("/sample/view/StarAdd.fxml"));

                    try {
                        loader3.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Parent root3 = loader3.getRoot();
                    Stage stage3 = new Stage();
                    stage3.setScene(new Scene(root3));
                    stage3.show();
                    break;


                case "planet":
                    addComboBox.getScene().getWindow().hide();
                    FXMLLoader loader4 = new FXMLLoader();
                    loader4.setLocation(getClass().getResource("/sample/view/PlanetAdd.fxml"));

                    try {
                        loader4.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Parent root4 = loader4.getRoot();
                    Stage stage4 = new Stage();
                    stage4.setScene(new Scene(root4));
                    stage4.show();
                    break;

                case "satellite":
                    addComboBox.getScene().getWindow().hide();
                    FXMLLoader loader5 = new FXMLLoader();
                    loader5.setLocation(getClass().getResource("/sample/view/SatelliteAdd.fxml"));

                    try {
                        loader5.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Parent root5 = loader5.getRoot();
                    Stage stage5 = new Stage();
                    stage5.setScene(new Scene(root5));
                    stage5.show();
                    break;

                case "constellation":

                    break;
            }
        });


        //в зависимости от типа объекта вставляем информацию
        //из соответствующих полей
        AddButton.setOnAction(e -> {
            DatabaseHandler dH = new DatabaseHandler();
            if (!addTextField.getText().trim().equals("")) {

                switch (addComboBox.getValue()){


                    case "constellation":
                        if (dH.objExist("constellation", addTextField.getText()) == false) {
                            dH.insertConstellation(addTextField.getText());
                            unsetVisibleAll();
                            SuccessAddLabel.setVisible(true);
                        } else {
                            unsetVisibleAll();
                            shakeSas();
                            ErrorLabel.setVisible(true);
                        }
                        break;


                }
            }
        });
        //обработка нажатия на кнопку назад
        backButtonAdd.setOnAction(e -> {
            backButtonAdd.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/mainMenu.fxml"));

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

    //метод для сокрытия всех полей ввода и надписей для них
    public void unsetVisibleAll() {

        TypeTextField.setVisible(false);
        DeclinationTextField.setVisible(false);
        RightAscensionTextField.setVisible(false);
        DistanceTextField.setVisible(false);
        RadiusTextField.setVisible(false);
        MassTextField.setVisible(false);
        SpectralClassTextField.setVisible(false);
        MagnitudeTextField.setVisible(false);
        SemiMajorAxisTextField.setVisible(false);
        EccentricityTextField.setVisible(false);
        GalaxyNameTextField.setVisible(false);
        StarNameTextField.setVisible(false);
        StarSystemNameTextField.setVisible(false);
        PlanetNameTextField.setVisible(false);
        ConstellationNameTextField.setVisible(false);
        AddButton.setVisible(false);
        mandatoryLabel.setVisible(false);
        SuccessAddLabel.setVisible(false);
        ErrorLabel.setVisible(false);

    }
}

