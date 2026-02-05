package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class CaseController implements Initializable {
    @FXML
    public static CaseController caseController;
    @FXML
    public Button deleteCase;
    @FXML
    private TextField caseID;
    @FXML
    private CheckBox type, lighting;
    @FXML
    public ListView<DisplayCase> caseListView;

    public void addDisplayCase(ActionEvent actionEvent){ // Adds a display case to the case list and listview and to the various choice boxes.
        DisplayCase addCase = new DisplayCase(caseID.getText(), wallmountedIsSelected(actionEvent), isBacklitSelected(actionEvent));
        caseListView.getItems().add(addCase);
        CONTENT.getCaseList().addNode(addCase);
        TrayController.trayController.caseChoiceBox.getItems().add(addCase);
        JewelleryController.jewelleryController.caseChoiceBox.getItems().add(addCase);
        MaterialController.materialController.caseChoiceBox.getItems().add(addCase);
        InteractiveDisplayController.interactiveController.populateCasesListView();
    }

    public void deleteSelectedCase(ActionEvent actionEvent){ //gets a case and removes it from listviews and choice boxes and sends the case id to below method for list deletion.
        DisplayCase caseToDelete = caseListView.getSelectionModel().getSelectedItem();
        int caseToDel = caseListView.getSelectionModel().getSelectedIndex();
        caseListView.getItems().remove(caseListView.getSelectionModel().getSelectedIndex());
        caseDeletion(caseToDelete.getCaseID());
        TrayController.trayController.caseChoiceBox.getItems().remove(caseToDelete);
        JewelleryController.jewelleryController.caseChoiceBox.getItems().remove(caseToDelete);
        MaterialController.materialController.caseChoiceBox.getItems().remove(caseToDelete);
        InteractiveDisplayController.interactiveController.displayListView.getItems().remove(caseToDel);
    }

    public void caseDeletion(String caseInput){ // deletes a case by looping through and matching the chosen case from the above method.
        for (int i = 0; i < CONTENT.getCaseList().getListSize(); i++){
            DisplayCase caseToDelete = CONTENT.getCaseList().get(i).getContent();
            if (CONTENT.getCaseList().get(i).getContent().getCaseID().contains(caseInput)){
                System.out.println("Deleted: "+CONTENT.getCaseList().get(i).getContent().toString());
                CONTENT.getCaseList().deleteListItem(i-1);

            }
        }
        TrayController.trayController.trayCleaner(caseInput); //calls the tray cleaner method to remove any trays associated with this case.
    }


    public void deleteAllCases(ActionEvent actionEvent){ //Removes all cases from choice boxes and listviews and clears the case list itself along with all.
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

    public String wallmountedIsSelected(ActionEvent actionEvent) {
        if(type.isSelected()){
            return "Wall-Mounted";
        }else{
            return "Non-Mounted";
        }
    }

    public String isBacklitSelected(ActionEvent actionEvent) {
        if (lighting.isSelected()){
            return "Lit";
        }else {
            return "Unlit";
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        caseController = this;
    }

    public void save() throws Exception{
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(fileName())));
        out.writeObject(CONTENT);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File((fileName()))));
        CONTENT = (Main.Content) in.readObject();
        in.close();
        populateContent();
    }

    public void populateContent(){
        for(int i = CONTENT.getCaseList().getListSize() - 1; i >= 0 ; i--){
            DisplayCase DC = CONTENT.getCaseList().get(i).getContent();
            caseListView.getItems().add(DC);
            TrayController.trayController.caseChoiceBox.getItems().add(DC);
            JewelleryController.jewelleryController.caseChoiceBox.getItems().add(DC);
            MaterialController.materialController.caseChoiceBox.getItems().add(DC);
        }
        for(int i = CONTENT.getTrayList().getListSize() - 1; i >= 0 ; i--){
            DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
            JewelleryController.jewelleryController.trayChoiceBox.getItems().add(DT);
            MaterialController.materialController.trayChoiceBox.getItems().add(DT);
        }
        for(int i = CONTENT.getJewelleryItemList().getListSize() - 1; i >= 0 ; i--){
            JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
            MaterialController.materialController.jewelleryItemChoiceBox.getItems().add(JI);
        }
        JewelleryController.jewelleryController.stockValue();
        InteractiveDisplayController.interactiveController.populateCasesListView();
        SearchController.searchController.populateJewelleryItems();
    }

    public String fileName(){return "cases.dat";}
}