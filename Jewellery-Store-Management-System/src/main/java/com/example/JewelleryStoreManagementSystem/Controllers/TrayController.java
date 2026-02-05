package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class TrayController implements Initializable{
    @FXML
    public Button deleteSelectedTray;
    @FXML
    private TextField trayID;
    @FXML
    private TextField materialColor, trayWidth, trayDepth;
    @FXML
    public ListView<DisplayTray> trayListView;
    @FXML
    public ChoiceBox<DisplayCase> caseChoiceBox;
    @FXML
    public static TrayController trayController;

    public void addDisplayTray(ActionEvent actionEvent) { //adds a display tray to the tray list and to the various tray choice boxes and list view.
        if(caseChoiceBox.getSelectionModel().getSelectedItem() != null) {
            DisplayTray addTray = new DisplayTray(caseChoiceBox.getSelectionModel().getSelectedItem().getCaseID(), trayID.getText().toUpperCase(), materialColor.getText(), Utilities.parseInt(trayWidth.getText()), Utilities.parseInt(trayDepth.getText()));
            trayListView.getItems().add(addTray);
            CONTENT.getTrayList().addNode(addTray);
            MaterialController.materialController.trayChoiceBox.getItems().add(addTray);
            for (int i = CONTENT.getTrayList().getListSize()-1; i >= 0 ; i--){
                DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
                System.out.println(DT);
            }
            System.out.println();
        }
    }

    public void selectedCase(ActionEvent actionEvent) { //chooses the list of trays displayed in the tray box bases on the selected case choice box.
        try{
            DisplayCase DC = caseChoiceBox.getSelectionModel().getSelectedItem();
            trayListView.getItems().clear();
            for(int i = CONTENT.getTrayList().getListSize()-1; i >= 0 ; i--){
                DisplayTray DT=CONTENT.getTrayList().get(i).getContent();
                if(DT.getCaseID().equals(DC.getCaseID())){
                    trayListView.getItems().add(DT);
                    JewelleryController.jewelleryController.trayChoiceBox.getItems().add(DT);
                }
            }
        }catch(NullPointerException ignored){}
    }

    public void deleteTray(ActionEvent actionEvent){ //on button press method selects the chosen tray and send it to the below method for deletion while also removing it from the choice boxes and list view.
        DisplayTray trayToDelete = trayListView.getSelectionModel().getSelectedItem();
        trayDeletion(trayToDelete.getTrayID());
        trayListView.getItems().remove(trayListView.getSelectionModel().getSelectedIndex());
    }

    public void trayDeletion(String trayInputID){ //takes the selected tray from the above method and deletes it from the tray list.
        for(int i = 0; i < CONTENT.getTrayList().getListSize(); i++) {
            if (CONTENT.getTrayList().get(i).getContent().getTrayID().contains(trayInputID)) {
                System.out.println(CONTENT.getTrayList().get(i).getContent());
                CONTENT.getTrayList().deleteListItem(i-1);
                JewelleryController.jewelleryController.jewelleryCleaner(trayInputID); // calls the jewellery cleaner method in jewellery controller to remove any jewellery items associated with the chosen delete trays.
            }
        }
        JewelleryController.jewelleryController.stockValue(); //recalls the stock value calculator in case the chosen tray has any jewellery items attached to it.
    }

    public void trayCleaner(String caseInputID){ // the tray cleaner method receives a case id from the case controller method to delete any trays that may be linked to that case.
        for(int i = CONTENT.getJewelleryItemList().getListSize()-1; i >= 0; i--) {
            DisplayTray trayToDelete = CONTENT.getTrayList().get(i).getContent();
            if (CONTENT.getTrayList().get(i).getContent().getCaseID().contains(caseInputID)){
                System.out.println(CONTENT.getTrayList().get(i).getContent());
                CONTENT.getTrayList().deleteListItem(i);
                JewelleryController.jewelleryController.jewelleryCleaner(trayToDelete.getTrayID());
            }
        }
    }

    public void deleteAllTrays(ActionEvent actionEvent){ //this deletes all trays, jewellery items and materials, also removes them from all choice boxes.
        CONTENT.getTrayList().clearList();
        CONTENT.getJewelleryItemList().clearList();
        CONTENT.getMaterialList().clearList();
        TrayController.trayController.trayListView.getItems().clear();
        JewelleryController.jewelleryController.trayChoiceBox.getItems().clear();
        JewelleryController.jewelleryController.jewelleryListView.getItems().clear();
        MaterialController.materialController.trayChoiceBox.getItems().clear();
        MaterialController.materialController.jewelleryItemChoiceBox.getItems().clear();
        MaterialController.materialController.materialListView.getItems().clear();
        JewelleryController.jewelleryController.stockValue(); //recalls the stock value method to recalculate the overall stock value.
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        trayController = this;
    } //initializes the controller
}



