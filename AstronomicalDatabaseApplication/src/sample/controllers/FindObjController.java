package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.anims.Shake;

public class FindObjController {
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
    private Button backButtonFind;

    @FXML
    private TextField findTextField;

    @FXML
    private Button findObjButton;

    @FXML
    private ComboBox<String> findObjComboBox;

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
    public TextField DistanceTextField;

    @FXML
    private Label DistanceLabel;

    @FXML
    private TextField RadiusTextField;

    @FXML
    private Label RadiusLabel;

    @FXML
    private TextField MassTextField;

    @FXML
    private Label MassLabel;

    @FXML
    private TextField SpectralClassTextField;

    @FXML
    private Label SpectralClassLabel;

    @FXML
    private TextField MagnitudeTextField;

    @FXML
    private Label MagnitudeLabel;

    @FXML
    private TextField SemiMajorAxisTextField;

    @FXML
    private Label SemiMajorAxisLabel;

    @FXML
    private TextField EccentricityTextField;

    @FXML
    private Label EccentricityLabel;

    @FXML
    private TextField GalaxyNameTextField;

    @FXML
    private Label GalaxyNameLabel;

    @FXML
    private TextField StarNameTextField;

    @FXML
    private Label StarNameLabel;

    @FXML
    private TextField StarSystemNameTextField;

    @FXML
    private Label StarSystemNameLabel;

    @FXML
    private TextField PlanetNameTextField;

    @FXML
    private Label PlanetNameLabel;

    @FXML
    private TextField ConstellationNameTextField;

    @FXML
    private Label ConstellationNameLabel;

    @FXML
    private TextArea ConstellationTextArea;

    @FXML
    private Label ConstellationLabel;

    @FXML
    private Label NoExistLabel;

    @FXML
    private Label successEditLabel;

    @FXML
    void initialize() {
        //делаем все поля для ввода невидимыми
        unsetVisibleAll();

        //получаем список типов объектов в выпадающем меню
        try {
            DatabaseHandler db = new DatabaseHandler();
            ResultSet rsTables = db.getTables();
            while(rsTables.next()) {
                spaceObjects.add(rsTables.getString(1));
            }
            findObjComboBox.setItems(spaceObjects);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }



        //обработка нажатия на кнопку назад
        backButtonFind.setOnAction(e -> {
            backButtonFind.getScene().getWindow().hide();
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

        //обработка нажатия на кнопку поиска объекта
        findObjButton.setOnAction(e -> {
            if (!findTextField.getText().trim().equals("")) {
                DatabaseHandler dH = new DatabaseHandler();
                ResultSet rs = dH.getObject(findObjComboBox.getValue(), findTextField.getText());
                unsetVisibleAll();
                objFindEditButton.setVisible(true);
                switch (findObjComboBox.getValue()) {
                    //в зависимости от типа объекта вставляем информацию
                    //в соответствующие поля
                    case "galaxy":
                        try {
                            ResultSet rsGalaxy = dH.getObject("galaxy",
                                    findTextField.getText().trim());
                            rsGalaxy.next();

                            if (dH.objExist("galaxy", findTextField.getText().trim())) {

                                DistanceLabel.setVisible(true);
                                DistanceTextField.setVisible(true);
                                DeclinationLabel.setVisible(true);
                                DeclinationTextField.setVisible(true);
                                RightAscensionTextField.setVisible(true);
                                RightAscensionLabel.setVisible(true);
                                TypeTextField.setVisible(true);
                                TypeLabel.setVisible(true);
                                DistanceTextField.setText(rsGalaxy.getString(5));
                                DeclinationTextField.setText(rsGalaxy.getString(2));
                                RightAscensionTextField.setText(rsGalaxy.getString(3));
                                TypeTextField.setText(rsGalaxy.getString(4));
                                objFindEditButton.setVisible(true);

                            } else {
                                objFindEditButton.setVisible(false);
                                System.out.println("НЕ НАЙДЕНО!");
                                shakeSas();
                            }
                        } catch (SQLException | InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        break;

                    case "star_system":
                        try {
                            ResultSet rsStarSystem = dH.getObject("star_system",
                                    findTextField.getText().trim());
                            rsStarSystem.next();

                            if (dH.objExist("star_system", findTextField.getText().trim())) {

                                DistanceTextField.setVisible(true);
                                DistanceLabel.setVisible(true);
                                DeclinationLabel.setVisible(true);
                                DeclinationTextField.setVisible(true);
                                RightAscensionTextField.setVisible(true);
                                RightAscensionLabel.setVisible(true);
                                GalaxyNameLabel.setVisible(true);
                                GalaxyNameTextField.setVisible(true);
                                ConstellationNameTextField.setVisible(true);
                                ConstellationNameLabel.setVisible(true);
                                DistanceTextField.setText(rsStarSystem.getString(2));
                                DeclinationTextField.setText(rsStarSystem.getString(3));
                                RightAscensionTextField.setText(rsStarSystem.getString(4));
                                GalaxyNameTextField.setText(rsStarSystem.getString(5));
                                ConstellationNameTextField.setText(rsStarSystem.getString(6));

                            } else {
                                objFindEditButton.setVisible(false);

                                shakeSas();
                            }

                        } catch (SQLException | InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        break;

                    case "star":


                        try {
                            ResultSet rsStar = dH.getObject("star", findTextField.getText().trim());
                            rsStar.next();

                            if (dH.objExist("star", findTextField.getText().trim())) {
                                MassLabel.setVisible(true);
                                MassTextField.setVisible(true);
                                RadiusTextField.setVisible(true);
                                RadiusLabel.setVisible(true);
                                SpectralClassTextField.setVisible(true);
                                SpectralClassLabel.setVisible(true);
                                MagnitudeLabel.setVisible(true);
                                MagnitudeTextField.setVisible(true);
                                StarSystemNameTextField.setVisible(true);
                                StarSystemNameLabel.setVisible(true);
                                MassTextField.setText(rsStar.getString(2));
                                RadiusTextField.setText(rsStar.getString(3));
                                SpectralClassTextField.setText(rsStar.getString(4));
                                MagnitudeTextField.setText(rsStar.getString(5));
                                StarSystemNameTextField.setText(rsStar.getString(6));
                            } else {
                                objFindEditButton.setVisible(false);

                                shakeSas();
                            }

                        } catch (SQLException | InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        break;

                    case "planet":


                        try {
                            ResultSet rsPlanet = dH.getObject("planet", findTextField.getText().trim());
                            rsPlanet.next();
                            if (dH.objExist("planet", findTextField.getText().trim())) {
                                MassLabel.setVisible(true);
                                MassTextField.setVisible(true);
                                RadiusTextField.setVisible(true);
                                RadiusLabel.setVisible(true);
                                TypeTextField.setVisible(true);
                                TypeLabel.setVisible(true);
                                SemiMajorAxisTextField.setVisible(true);
                                SemiMajorAxisLabel.setVisible(true);
                                EccentricityTextField.setVisible(true);
                                EccentricityLabel.setVisible(true);
                                StarSystemNameTextField.setVisible(true);
                                StarSystemNameLabel.setVisible(true);
                                TypeTextField.setText(rsPlanet.getString(2));
                                MassTextField.setText(rsPlanet.getString(3));
                                RadiusTextField.setText(rsPlanet.getString(4));
                                SemiMajorAxisTextField.setText(rsPlanet.getString(5));
                                EccentricityTextField.setText(rsPlanet.getString(6));
                                StarSystemNameTextField.setText(rsPlanet.getString(7));
                            } else {
                                objFindEditButton.setVisible(false);
                                shakeSas();
                            }
                        } catch (SQLException | InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        break;

                    case "satellite":




                        try {
                            ResultSet rsSatellite = dH.getObject("satellite", findTextField.getText().trim());
                            rsSatellite.next();
                            if (dH.objExist("satellite", findTextField.getText().trim())) {
                                MassLabel.setVisible(true);
                                MassTextField.setVisible(true);
                                RadiusTextField.setVisible(true);
                                RadiusLabel.setVisible(true);
                                TypeTextField.setVisible(true);
                                TypeLabel.setVisible(true);
                                SemiMajorAxisTextField.setVisible(true);
                                SemiMajorAxisLabel.setVisible(true);
                                EccentricityTextField.setVisible(true);
                                EccentricityLabel.setVisible(true);
                                PlanetNameTextField.setVisible(true);
                                PlanetNameLabel.setVisible(true);
                                TypeTextField.setText(rsSatellite.getString(2));
                                MassTextField.setText(rsSatellite.getString(3));
                                RadiusTextField.setText(rsSatellite.getString(4));
                                SemiMajorAxisTextField.setText(rsSatellite.getString(5));
                                EccentricityTextField.setText(rsSatellite.getString(6));
                                PlanetNameTextField.setText(rsSatellite.getString(7));
                            } else {
                                objFindEditButton.setVisible(false);
                                System.out.println("НЕ НАЙДЕНО!");
                                shakeSas();
                            }
                        } catch (SQLException | InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        break;

                    case "constellation":
                        String ConstellationStars = dH.getConstellationStars(findTextField.getText().trim());
                        if (dH.objExist("constellation", findTextField.getText().trim())) {
                            ConstellationTextArea.setText(ConstellationStars);
                            objFindEditButton.setVisible(false);
                            ConstellationLabel.setVisible(true);
                            ConstellationTextArea.setVisible(true);
                        } else {
                            objFindEditButton.setVisible(false);
                            System.out.println("НЕ НАЙДЕНО!");
                            try {
                                shakeSas();
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }




                }


                try {
                    System.out.println(showData(rs));
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }

            }
        });
        //метод обработки нажатия на кнопку редактирования объекта
        objFindEditButton.setOnAction(e -> {
            DatabaseHandler dH = new DatabaseHandler();
            switch (findObjComboBox.getValue()) {
                //в зависимости от типа объекта обновляем информацию
                //из соответствующих полей
                case "galaxy":

                    dH.updateGalaxy(findTextField.getText().trim(),
                            DeclinationTextField.getText(),
                            RightAscensionTextField.getText(),
                            TypeTextField.getText(),
                            DistanceTextField.getText());

                    break;

                case "star_system":

                    if (dH.objExist("galaxy", GalaxyNameTextField.getText())) {
                        if (ConstellationNameTextField.getText().equals("")) {

                            dH.updateStarSystem(findTextField.getText(),
                                    DistanceTextField.getText(),
                                    DeclinationTextField.getText(),
                                    RightAscensionTextField.getText(),
                                    GalaxyNameTextField.getText(),
                                    "");
                            unsetVisibleAll();
                            objFindEditButton.setVisible(false);
                            successEditLabel.setVisible(true);
                            backButtonFind.setVisible(true);
                        }
                        else if (dH.objExist("constellation", ConstellationNameTextField.getText())) {

                            dH.updateStarSystem(findTextField.getText(),
                                    DistanceTextField.getText(),
                                    DeclinationTextField.getText(),
                                    RightAscensionTextField.getText(),
                                    GalaxyNameTextField.getText(),
                                    ConstellationNameTextField.getText());
                            unsetVisibleAll();
                            objFindEditButton.setVisible(false);
                            successEditLabel.setVisible(true);
                            backButtonFind.setVisible(true);
                        }  else {
                        try {
                            shakeEdit();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    } else {
                        try {
                            shakeEdit();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    break;

                case "star":
                    if (dH.objExist("star_system", StarSystemNameTextField.getText().trim())) {
                        dH.updateStar(findTextField.getText(),
                                MassTextField.getText(),
                                RadiusTextField.getText(),
                                SpectralClassTextField.getText(),
                                MagnitudeTextField.getText(),
                                StarSystemNameTextField.getText());
                        unsetVisibleAll();
                        objFindEditButton.setVisible(false);
                        successEditLabel.setVisible(true);
                        backButtonFind.setVisible(true);
                    } else {
                        try {
                            shakeEdit();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    break;
                case "planet":
                    if (dH.objExist("star_system", StarSystemNameTextField.getText().trim())) {
                        dH.updatePlanet(findTextField.getText(),
                                TypeTextField.getText(),
                                MassTextField.getText(),
                                RadiusTextField.getText(),
                                SemiMajorAxisTextField.getText(),
                                EccentricityTextField.getText(),
                                StarSystemNameTextField.getText());
                        unsetVisibleAll();
                        objFindEditButton.setVisible(false);
                        successEditLabel.setVisible(true);
                        backButtonFind.setVisible(true);
                    } else {
                        try {
                            shakeEdit();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }

                    break;

                case "satellite":
                    if (dH.objExist("planet", PlanetNameTextField.getText().trim())) {
                        dH.updateSatellite(findTextField.getText(),
                                TypeTextField.getText(),
                                MassTextField.getText(),
                                RadiusTextField.getText(),
                                SemiMajorAxisTextField.getText(),
                                EccentricityTextField.getText(),
                                PlanetNameTextField.getText());
                        unsetVisibleAll();
                        objFindEditButton.setVisible(false);
                        successEditLabel.setVisible(true);
                        backButtonFind.setVisible(true);
                    } else {
                        try {
                            shakeEdit();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    break;
            }
        });


       findObjComboBox.setOnAction( e -> {
           unsetVisibleAll();
        });



    }

    public void setFindTextField(String text) {
        findTextField.setText(text);
    }


    public void shakeSas() throws InterruptedException {
        Shake findobjTextFieldAnim = new Shake(findTextField);
        findobjTextFieldAnim.playAnim();
        Shake findObjComboBoxAnim = new Shake(findObjComboBox);
        findObjComboBoxAnim.playAnim();
        NoExistLabel.setVisible(true);
        //Thread.sleep(1000);
        //NoExistLabel.setVisible(false);
    }
    public void shakeEdit() throws InterruptedException {
        Shake EbitButtonAnim = new Shake(objFindEditButton);
        EbitButtonAnim.playAnim();

    }


    //метод для сокрытия всех полей ввода и надписей для них
    public void unsetVisibleAll() {
        successEditLabel.setVisible(false);
        NoExistLabel.setVisible(false);
        objFindEditButton.setVisible(false);
        TypeTextField.setVisible(false);
        TypeLabel.setVisible(false);
        DeclinationTextField.setVisible(false);
        DeclinationLabel.setVisible(false);
        RightAscensionLabel.setVisible(false);
        RightAscensionTextField.setVisible(false);
        DistanceLabel.setVisible(false);
        DistanceTextField.setVisible(false);
        RadiusLabel.setVisible(false);
        RadiusTextField.setVisible(false);
        MassLabel.setVisible(false);
        MassTextField.setVisible(false);
        SpectralClassLabel.setVisible(false);
        SpectralClassTextField.setVisible(false);
        MagnitudeTextField.setVisible(false);
        MagnitudeLabel.setVisible(false);
        SemiMajorAxisLabel.setVisible(false);
        SemiMajorAxisTextField.setVisible(false);
        EccentricityLabel.setVisible(false);
        EccentricityTextField.setVisible(false);
        GalaxyNameLabel.setVisible(false);
        GalaxyNameTextField.setVisible(false);
        StarNameLabel.setVisible(false);
        StarNameTextField.setVisible(false);
        StarSystemNameTextField.setVisible(false);
        StarSystemNameLabel.setVisible(false);
        PlanetNameLabel.setVisible(false);
        PlanetNameTextField.setVisible(false);
        ConstellationNameLabel.setVisible(false);
        ConstellationNameTextField.setVisible(false);
        ConstellationLabel.setVisible(false);
        ConstellationTextArea.setVisible(false);
    }

    private String showData(ResultSet result) throws SQLException {
        ResultSetMetaData rsmd = result.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        String text  = "";
        for (int i = 1; i < numberOfColumns + 1; i++) { //получаем названия столбцов
            String columnName = rsmd.getColumnName(i);
            text = text + columnName + ", ";
        }
        text = text + "\n";
        while(result.next()) { //получаем данные из строк таблицы
            for(int i =0; i<numberOfColumns; i++) {
                if(i+1 == numberOfColumns) {;
                    text = text + result.getString(i + 1) + "\n";
                }
                else {
                    text += result.getString(i + 1) + ", ";
                }
            }
        }
        return text;
    }
}
