package com.example.JewelleryStoreManagementSystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class MainController implements Initializable {

    public static MainController mainController;
    @FXML
    public Label totalValueLabel;
    @FXML
    public Button load;
    @FXML
    public Button clear;

    @FXML
    private void saveAllData(ActionEvent actionEvent) {
        try {
            CaseController.caseController.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    @FXML
    private void loadAllData(ActionEvent actionEvent) {
        try {
            CaseController.caseController.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    @FXML
    public void clearAllData(ActionEvent actionEvent) {
        CONTENT.getCaseList().clearList();
        CONTENT.getTrayList().clearList();
        CONTENT.getJewelleryItemList().clearList();
        CONTENT.getMaterialList().clearList();
        CaseController.caseController.caseListView.getItems().clear();
        TrayController.trayController.caseChoiceBox.getItems().clear();
        TrayController.trayController.trayListView.getItems().clear();
        JewelleryController.jewelleryController.caseChoiceBox.getItems().clear();
        JewelleryController.jewelleryController.trayChoiceBox.getItems().clear();
        JewelleryController.jewelleryController.jewelleryListView.getItems().clear();
        MaterialController.materialController.caseChoiceBox.getItems().clear();
        MaterialController.materialController.trayChoiceBox.getItems().clear();
        MaterialController.materialController.jewelleryItemChoiceBox.getItems().clear();
        MaterialController.materialController.materialListView.getItems().clear();
        InteractiveDisplayController.interactiveController.displayListView.getItems().clear();
        SearchController.searchController.resultsList.getItems().clear();
        JewelleryController.jewelleryController.stockValue();
    }
}
