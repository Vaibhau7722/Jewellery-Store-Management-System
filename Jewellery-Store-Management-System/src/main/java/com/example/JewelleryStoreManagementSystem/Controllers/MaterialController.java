package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;


public class MaterialController implements Initializable{

    @FXML
    private TextField materialType, uniqueID, materialWeight, materialQuality;
    @FXML
    private TextArea materialDescription;
    @FXML
    public ChoiceBox<DisplayCase> caseChoiceBox;
    @FXML
    public ChoiceBox<DisplayTray> trayChoiceBox;
    @FXML
    public ChoiceBox<JewelleryItem> jewelleryItemChoiceBox;
    @FXML
    public ListView<Material> materialListView;

    public static MaterialController materialController;

    public void addMaterial(ActionEvent actionEvent){ //adds a material to the material list and list view.
        if(JewelleryController.jewelleryController.caseChoiceBox.getSelectionModel().getSelectedItem() != null && JewelleryController.jewelleryController.trayChoiceBox.getSelectionModel().getSelectedItem() != null){
            Material addMaterial = new Material(trayChoiceBox.getSelectionModel().getSelectedItem().getTrayID(), jewelleryItemChoiceBox.getSelectionModel().getSelectedItem().getItemSerialNum(), uniqueID.getText(), materialType.getText(), materialWeight.getText(), materialQuality.getText(), materialDescription.getText());
            materialListView.getItems().add(addMaterial);
            CONTENT.getMaterialList().addNode(addMaterial);
        }
    }

    public void selectedCase(ActionEvent actionEvent) { //depending on the chosen case populates the tray choice box with associated trays for selection.
        try{
            trayChoiceBox.getItems().clear();
            DisplayCase DC = caseChoiceBox.getSelectionModel().getSelectedItem();
            for(int i = CONTENT.getTrayList().getListSize() - 1; i >= 0 ; i--){
                DisplayTray DT = CONTENT.getTrayList().get(i).getContent();
                if(Objects.equals(DT.getCaseID(), DC.getCaseID())){
                    trayChoiceBox.getItems().add(DT);
                }
            }
        }catch (NullPointerException ignored){}
    }

    public void selectedTray(ActionEvent actionEvent) { //depending on the chosen tray, populates the jewellery choice box with associated jewellery items.
       try{
           jewelleryItemChoiceBox.getItems().clear();
           DisplayTray DT = trayChoiceBox.getSelectionModel().getSelectedItem();
           for(int i = CONTENT.getJewelleryItemList().getListSize() - 1; i >= 0 ; i--){
               JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
               if(Objects.equals(DT.getTrayID(), JI.getTrayID())){
                   jewelleryItemChoiceBox.getItems().add(JI);
               }
           }
       }catch (NullPointerException ignored){}
    }

    public void selectedJewelleryItem(ActionEvent actionEvent) { //Depending on the chosen jewellery item populates the list view with related materals.
        try {
            materialListView.getItems().clear();
            JewelleryItem JI = jewelleryItemChoiceBox.getSelectionModel().getSelectedItem();
            for(int i = CONTENT.getMaterialList().getListSize() - 1; i >= 0 ; i--) {
                Material M = CONTENT.getMaterialList().get(i).getContent();
                if (Objects.equals(JI.getItemSerialNum(), M.getItemSerialNum())) {
                    materialListView.getItems().add(M);
                }
            }
        } catch (NullPointerException ignored){}
    }

    public void deleteMaterialItem(ActionEvent actionEvent){ //selects a material item from the list view on button press and send it for deletion in the method below. Also removes the chose item from the material list view.
        Material materialToDelete = materialListView.getSelectionModel().getSelectedItem();
        materialDeletion(materialToDelete.getUniqueID());
        materialListView.getItems().remove(materialListView.getSelectionModel().getSelectedIndex());
    }

    public void materialDeletion(String inputMaterialID){ //takes the passed material from the above method to be deleted.
        for (int i = 0; i < CONTENT.getMaterialList().getListSize(); i++) {
            if (CONTENT.getMaterialList().get(i).getContent().getUniqueID().contains(inputMaterialID)) {
                System.out.println("Deleted: "+CONTENT.getMaterialList().get(i).getContent().toString());
                CONTENT.getMaterialList().deleteListItem(i-1);
            }
        }
    }

    public void materialCleaner(String jewelleryItemID){ //takes the passed jewellery id from the jewellery controller when a jewellery item is deleted and deletes any associated materials.
        for (int i = CONTENT.getMaterialList().getListSize()-1; i >= 0; i--) {
            Material M = CONTENT.getMaterialList().get(i).getContent();
            if (M.toString().contains(jewelleryItemID)) {
                System.out.println("Deleted: "+CONTENT.getMaterialList().get(i).getContent().toString());
                CONTENT.getMaterialList().deleteListItem(i);
            }
        }
    }

    public void deleteAllMaterials(ActionEvent actionEvent){ //deletes all material items from the list view and material list.
        CONTENT.getMaterialList().clearList();
        materialListView.getItems().clear();
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        materialController=this;
    } //initialises the material controller.


}
