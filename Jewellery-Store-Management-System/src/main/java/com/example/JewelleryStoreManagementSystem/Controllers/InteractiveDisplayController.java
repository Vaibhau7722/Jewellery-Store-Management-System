package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class InteractiveDisplayController implements Initializable {
    @FXML
    public static InteractiveDisplayController interactiveController;
    @FXML
    public ListView<String> displayListView;
    @FXML
    public Label caseDisplay, chosenCaseValue, chosenTrayValueLabel, chosenTrayValue, chosenCaseDisplay, trayDisplay, chosenTrayDisplay, jewelleryItemDisplay, chosenJewelleryItemDisplay, backLabel, enterLabel, displayLabel;
    @FXML
    public Button back, enter, showJewelleryImage;
    @FXML
    public Label chosenCaseDisplayValueLabel;

    private String caseLabel;
    private String trayLabel;
    private String jewelleryItemLabel;

    public String getCaseLabel() {
        return caseLabel;
    }

    public void setCaseLabel(String caseLabel) {
        this.caseLabel = caseLabel;
    }

    public String getTrayLabel() {
        return trayLabel;
    }

    public void setTrayLabel(String trayLabel) {
        this.trayLabel = trayLabel;
    }

    public String getJewelleryItemLabel() {
        return jewelleryItemLabel;
    }

    public void setJewelleryItemLabel(String jewelleryItemLabel) {
        this.jewelleryItemLabel = jewelleryItemLabel;
    }

    int traversalID = 1;
    public int getTraversalID(){return traversalID;}
    public void setTraversalID(int traversalID){this.traversalID = traversalID;}

    public void displayTraversal() {
       if(getTraversalID() >= 1 && getTraversalID() <= 4){
           if(getTraversalID() == 1){
               displayLabel.setText("Cases");
               populateCasesListView();
               back.visibleProperty().set(false);
               backLabel.visibleProperty().set(false);
               caseDisplay.visibleProperty().set(false);
               chosenCaseDisplay.visibleProperty().set(false);
               chosenCaseDisplayValueLabel.visibleProperty().set(false);
               chosenCaseValue.visibleProperty().set(false);
               trayDisplay.visibleProperty().set(false);
               chosenTrayDisplay.visibleProperty().set(false);
               chosenTrayValue.visibleProperty().set(false);
               chosenTrayValueLabel.visibleProperty().set(false);
               jewelleryItemDisplay.visibleProperty().set(false);
               chosenJewelleryItemDisplay.visibleProperty().set(false);
           } else if(getTraversalID() == 2){
               displayLabel.setText("Trays");
               populateTrayListView();
               back.visibleProperty().set(true);
               backLabel.visibleProperty().set(true);
               caseDisplay.visibleProperty().set(true);
               chosenCaseDisplay.setText(getCaseLabel());
               chosenCaseDisplay.visibleProperty().set(true);
               chosenCaseDisplayValueLabel.visibleProperty().set(true);
               chosenCaseValue.setText("€"+specificCaseStockValue(getCaseLabel()));
               chosenCaseValue.visibleProperty().set(true);
               trayDisplay.visibleProperty().set(false);
               chosenTrayDisplay.visibleProperty().set(false);
               chosenTrayValueLabel.visibleProperty().set(false);
               chosenTrayValue.visibleProperty().set(false);
               jewelleryItemDisplay.visibleProperty().set(false);
               chosenJewelleryItemDisplay.visibleProperty().set(false);
               showJewelleryImage.visibleProperty().set(false);
           } else if(getTraversalID() == 3){
               displayLabel.setText("Jewellery Items");
               populateJewelleryListView();
               caseDisplay.visibleProperty().set(true);
               chosenCaseDisplay.visibleProperty().set(true);
               chosenCaseDisplayValueLabel.visibleProperty().set(true);
               chosenCaseValue.setText("€"+specificCaseStockValue(getCaseLabel()));
               chosenCaseValue.visibleProperty().set(true);
               trayDisplay.visibleProperty().set(true);
               chosenTrayDisplay.setText(getTrayLabel());
               chosenTrayDisplay.visibleProperty().set(true);
               chosenTrayValueLabel.visibleProperty().set(true);
               chosenTrayValue.setText("€"+specificTrayStockValue(getTrayLabel()));
               chosenTrayValue.visibleProperty().set(true);
               jewelleryItemDisplay.visibleProperty().set(false);
               chosenJewelleryItemDisplay.visibleProperty().set(false);
               enter.visibleProperty().set(true);
               enterLabel.visibleProperty().set(true);
               showJewelleryImage.visibleProperty().set(true);
           } else if(getTraversalID() == 4){
               displayLabel.setText("Materials");
               populateMaterialListView();
               enter.visibleProperty().set(false);
               enterLabel.visibleProperty().set(false);
               caseDisplay.visibleProperty().set(true);
               chosenCaseDisplay.setText(getCaseLabel());
               chosenCaseDisplay.visibleProperty().set(true);
               chosenCaseDisplayValueLabel.visibleProperty().set(true);
               chosenCaseValue.setText("€"+specificCaseStockValue(getCaseLabel()));
               chosenCaseValue.visibleProperty().set(true);
               trayDisplay.visibleProperty().set(true);
               chosenTrayDisplay.visibleProperty().set(true);
               chosenTrayValueLabel.visibleProperty().set(true);
               chosenTrayValue.setText("€"+specificTrayStockValue(getTrayLabel()));
               chosenTrayValue.visibleProperty().set(true);
               jewelleryItemDisplay.visibleProperty().set(true);
               chosenJewelleryItemDisplay.setText(getJewelleryItemLabel());
               chosenJewelleryItemDisplay.visibleProperty().set(true);
               showJewelleryImage.visibleProperty().set(false);
           }
       } else if(getTraversalID() < 1){
           setTraversalID(1);
       } else if(getTraversalID() > 4){
           setTraversalID(4);
       }
    }

    public String specificCaseStockValue(String inputString){
        double totalValue = 0;
        for(int i = 0; i < CONTENT.getCaseList().getListSize(); i++) {
            DisplayCase DC = CONTENT.getCaseList().get(i).getContent();
            if (DC.getCaseID().contains(inputString)) {
                for (int j = 0; j < CONTENT.getTrayList().getListSize(); j++) {
                    DisplayTray DT = CONTENT.getTrayList().get(j).getContent();
                    if (DT.getCaseID().contains(DC.getCaseID())) {
                        for (int m = 0; m < CONTENT.getJewelleryItemList().getListSize(); m++) {
                            JewelleryItem JI = CONTENT.getJewelleryItemList().get(m).getContent();
                            if (JI.getTrayID().contains(DT.getTrayID())) {
                                totalValue = totalValue+CONTENT.getJewelleryItemList().get(m).getContent().getJewelleryPrice();
                            }
                        }
                    }
                }
            }
        }
        return String.valueOf(totalValue);
    }

    public String specificTrayStockValue(String inputString){
        double totalValue = 0;
        for(int i = 0; i < CONTENT.getTrayList().getListSize(); i++){
            DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
            if(DT.getTrayID().contains(inputString)) {
                for (int j = 0; j < CONTENT.getJewelleryItemList().getListSize(); j++) {
                    JewelleryItem JI = CONTENT.getJewelleryItemList().get(j).getContent();
                    if (JI.getTrayID().contains(DT.getTrayID())) {
                        totalValue = totalValue + CONTENT.getJewelleryItemList().get(j).getContent().getJewelleryPrice();
                    }
                }
            }
        }
        return String.valueOf(totalValue);
    }

    public void populateCasesListView(){
        displayListView.getItems().clear();
        for(int i = CONTENT.getCaseList().getListSize()-1; i >= 0; i--){
            DisplayCase DC = CONTENT.getCaseList().get(i).getContent();
            displayListView.getItems().add(String.valueOf(DC));
        }
    }

    public void populateTrayListView(){
        Object DC = displayListView.getSelectionModel().getSelectedItem();
        displayListView.getItems().clear();
        if (DC != null){
            for(int i = CONTENT.getTrayList().getListSize()-1; i >= 0; i--){
                String caseMatch = DC.toString();
                DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
                if(caseMatch.contains(DT.getCaseID())){
                    displayListView.getItems().add(String.valueOf(DT));
                }
            }
        } else{
            String caseMatch;
            for(int i = CONTENT.getTrayList().getListSize()-1; i >= 0; i--){
                caseMatch = getCaseLabel();
                DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
                if(caseMatch.contains(DT.getCaseID())){
                    displayListView.getItems().add(String.valueOf(DT));
                }
            }
        } try{
            for(int i = 0; i < CONTENT.getCaseList().getListSize(); i++){
                String caseMatch = DC.toString();
                DisplayCase displayCase = CONTENT.getCaseList().get(i).getContent();
                if(caseMatch.contains(displayCase.getCaseID())){
                    setCaseLabel(displayCase.getCaseID());
                }
            }
        }catch(NullPointerException ignored){}
    }

    public void populateJewelleryListView(){
        Object DT = displayListView.getSelectionModel().getSelectedItem();
        displayListView.getItems().clear();
        if(DT != null){
            for(int i = CONTENT.getJewelleryItemList().getListSize()-1; i >= 0 ; i--){
                String trayMatch = DT.toString();
                JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
                if(trayMatch.contains(JI.getTrayID())){
                    displayListView.getItems().add(String.valueOf(JI));
                }
            }
        } else {
            String trayMatch;
            for(int i = CONTENT.getJewelleryItemList().getListSize()-1; i >= 0 ; i--){
                trayMatch = getTrayLabel();
                JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
                if(trayMatch.contains(JI.getTrayID())){
                    displayListView.getItems().add(String.valueOf(JI));
                }
            }
        }
        try{
            for(int i = 0; i < CONTENT.getTrayList().getListSize(); i++){
                String trayMatch = DT.toString();
                DisplayTray displayTray = CONTENT.getTrayList().get(i).getContent();
                if(trayMatch.contains(displayTray.getTrayID())){
                    setTrayLabel(displayTray.getTrayID());
                }
            }
        }catch(NullPointerException ignored){}

    }



    public void populateMaterialListView(){
        Object JI = displayListView.getSelectionModel().getSelectedItem();
        displayListView.getItems().clear();
        for(int i = CONTENT.getMaterialList().getListSize()-1; i >= 0 ; i--){
            String itemMatch = JI.toString();
            Material M = CONTENT.getMaterialList().get(i).getContent();
            if(itemMatch.contains(M.getItemSerialNum())){
                displayListView.getItems().add(String.valueOf(M));
            }
        }
        for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
            String jewelleryMatch = JI.toString();
            JewelleryItem jewelleryItem = CONTENT.getJewelleryItemList().get(i).getContent();
            if(jewelleryMatch.contains(jewelleryItem.getItemSerialNum())){
                setJewelleryItemLabel(jewelleryItem.getItemSerialNum());
            }
        }
    }

    public String getURL(){
        if(traversalID==3){
            Object JI = displayListView.getSelectionModel().getSelectedItem();
            for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
                String jewelleryMatch = JI.toString();
                JewelleryItem jewelleryItem = CONTENT.getJewelleryItemList().get(i).getContent();
                if(jewelleryMatch.contains(jewelleryItem.getItemSerialNum())){
                    return jewelleryItem.getImageURL();
                }
            }
        }
        return "null";
    }

    public String getJewelleryItemSerialNumber(){
        if(traversalID==3){
            Object JI = displayListView.getSelectionModel().getSelectedItem();
            for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
                String jewelleryMatch = JI.toString();
                JewelleryItem jewelleryItem = CONTENT.getJewelleryItemList().get(i).getContent();
                if(jewelleryMatch.contains(jewelleryItem.getItemSerialNum())){
                    return jewelleryItem.getItemSerialNum();
                }
            }
        }
        return "null";
    }

    public void showImage(ActionEvent actionEvent) {
    }



    public void back(ActionEvent actionEvent) {
       try{
           setTraversalID(getTraversalID()-1);
           displayTraversal();
       }catch(NullPointerException ignored){}
    }

    public void enter(ActionEvent actionEvent) {
        if (displayListView.getSelectionModel().getSelectedItem() != null) {
            setTraversalID(getTraversalID() + 1);
            displayTraversal();
        }
    }

    public void handleMouseDoubleClicked(MouseEvent click) {
        if(click.getClickCount() == 2){
            if (displayListView.getSelectionModel().getSelectedItem() != null) {
                setTraversalID(getTraversalID() + 1);
                displayTraversal();
            }
        } else if ((click.getClickCount() == 1 && displayListView.getSelectionModel().getSelectedItem() == null)) {
            setTraversalID(getTraversalID() - 1);
            displayTraversal();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        interactiveController = this;
    }



}
