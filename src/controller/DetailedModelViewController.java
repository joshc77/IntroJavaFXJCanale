/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Student;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class DetailedModelViewController implements Initializable {
    
    @FXML
    private ImageView image;
    
    @FXML
    private Label valueID;
    
    @FXML
    private Label valueEmail;
    
    @FXML
    private Label valueName;

    @FXML
    private Button backButton;

    @FXML
    private Label valuePhone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backButton.setDisable(true);
    }

    //following code is put together with the help of Professor's source code
    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (previousScene != null) {
            stage.setScene(previousScene);
        }

    }

    Student selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);
    }

    public void initData(Student model) {
        selectedModel = model;
        valueID.setText(model.getId().toString());
        valueName.setText(model.getName());
        valueEmail.setText(model.getEmail());
        valuePhone.setText(model.getPhone());

        try {
            String imagename = "/resource/" + model.getName() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
