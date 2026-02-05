package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.DisplayCase;
import com.example.JewelleryStoreManagementSystem.DisplayTray;
import com.example.JewelleryStoreManagementSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;

public class JewelleryController implements Initializable {
    public static JewelleryController jewelleryController;
    @FXML
    public ListView<JewelleryItem> jewelleryListView;
    @FXML
    public ChoiceBox<DisplayCase> caseChoiceBox;
    @FXML
    public ChoiceBox<DisplayTray> trayChoiceBox;
    @FXML
    public Button addJewelleryItem;
    @FXML
    public Button deleteSelectedJewelleryItem;
    @FXML
    public Button deleteAllJewelleryItems;
    @FXML
    public Button smartADD;
    @FXML
    private TextField jewelleryType, itemSerialNum, targetGender, imageURL, jewelleryPrice;
    @FXML
    private TextArea itemDescription;

    private String smartAddTrayID; //string for holding the tray to add a jewellery item to smartly.
    public String getSmartAddTrayID() {
        return smartAddTrayID;
    }
    public void setSmartAddTrayID(String smartAddTrayID) {
        this.smartAddTrayID = smartAddTrayID;
    }



    private String jewelToDelete;
    public String getJewelToDelete() {return jewelToDelete;}
    public void setJewelToDelete(String jewelToDelete) {this.jewelToDelete = jewelToDelete;}


    private String stockTotalValue; //stock value string for the overall stock value label.
    public String getStockTotalValue() {
        return stockTotalValue;
    }
    public void setStockTotalValue(String stockTotalValue) {
        this.stockTotalValue = stockTotalValue;
    }



    public void addJewelleryItem(ActionEvent actionEvent) { //adds a jewellery item to jewellery item list, choice boxes and list view.
        if((trayChoiceBox.getSelectionModel().getSelectedItem() != null) && (TrayController.trayController.caseChoiceBox.getSelectionModel().getSelectedItem() != null)) {
            JewelleryItem addJewelleryItem = new JewelleryItem(trayChoiceBox.getSelectionModel().getSelectedItem().getTrayID(), itemSerialNum.getText(), jewelleryType.getText(), targetGender.getText(), imageURL.getText(), Double.parseDouble(jewelleryPrice.getText()), itemDescription.getText());
            jewelleryListView.getItems().add(addJewelleryItem);
            CONTENT.getJewelleryItemList().addNode(addJewelleryItem);
            MaterialController.materialController.jewelleryItemChoiceBox.getItems().add(addJewelleryItem);
        }
        stockValue(); //after every item added the system calls the stock calculate method.
    }

    public boolean smartAddChecker(String inputTypeString, Double inputPrice, String inputGender){ //method checks through the entire jewellery list and checks for like items by type, price and target gender, weighted in that order by use of booleans.
        int likeItems = 0;
        boolean typeFlag = false;
        boolean priceFlag = false;
        boolean genderFlag = false;
        for (int j = 0; j < CONTENT.getTrayList().getListSize(); j++){
            DisplayTray DT = CONTENT.getTrayList().get(j).getContent();
            int likeItems2 = 0;
            for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++) {
                JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
                double lowerPrice= 0;
                double higherPrice= 0;
                if(JI.getTrayID().contains(DT.getTrayID())) {
                    lowerPrice = JI.getJewelleryPrice()+25;
                    higherPrice = JI.getJewelleryPrice()-25;
                    if (JI.getJewelleryType().contains(inputTypeString)) {
                        typeFlag = true;
                    }
                    if (inputPrice > lowerPrice && inputPrice < higherPrice) {
                        priceFlag = true;
                    }
                    if (JI.getTargetGender().contains(inputGender)) {
                        genderFlag = true;
                    }
                    if (typeFlag && priceFlag && genderFlag) {
                        likeItems2++;
                    } else if (typeFlag && priceFlag) {
                        likeItems2++;
                    } else if (typeFlag) {
                        likeItems2++;
                    }
                    if (likeItems2 > likeItems) {
                        likeItems = likeItems2;
                        setSmartAddTrayID(JI.getTrayID());
                    }
                }
            }
        }
        if (!getSmartAddTrayID().equals("")){
            return true;
        } else{
            return false;
        }
    }

    public void smartADDJewelleryItem(ActionEvent actionEvent) { // calls the smart checker to see if any like items for what has been filled out in the text fields then adds the case to the tray with the most liked items.
        if(smartAddChecker(jewelleryType.getText(), Double.parseDouble(jewelleryPrice.getText()), targetGender.getText())){
            JewelleryItem addJewelleryItem = new JewelleryItem(getSmartAddTrayID(), itemSerialNum.getText(), jewelleryType.getText(), targetGender.getText(), imageURL.getText(), Double.parseDouble(jewelleryPrice.getText()), itemDescription.getText());
            jewelleryListView.getItems().add(addJewelleryItem);
            CONTENT.getJewelleryItemList().addNode(addJewelleryItem);
            MaterialController.materialController.jewelleryItemChoiceBox.getItems().add(addJewelleryItem);
            System.out.println("Jewellery Item: "+addJewelleryItem+" added to Tray: "+getSmartAddTrayID());
            stockValue();
        } else {
            System.out.println("No like items!");
        }

    }

    public void deleteJewelleryItem(ActionEvent actionEvent){ //selects a jewellery item from the list view on button press, removes it from the list view and sends it to below method for deleting
        JewelleryItem jewelleryItemToDelete = jewelleryListView.getSelectionModel().getSelectedItem();
        jewelleryDeletion(jewelleryItemToDelete.getItemSerialNum());
        jewelleryListView.getItems().remove(jewelleryListView.getSelectionModel().getSelectedIndex());
    }

    public void jewelleryDeletion(String jewelleryInputID){ //proceeds to delete the jewellery item from the above method. recalculates the overall stock value and calls the materal cleaner method to remove any associated materials to this jewellery item to be deleted.
        setJewelToDelete(jewelleryInputID);
        for (int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++) {
            JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
            if (CONTENT.getJewelleryItemList().get(i).getContent().getItemSerialNum().contains(jewelleryInputID)) {
                System.out.println("Deleted: "+CONTENT.getJewelleryItemList().get(i).getContent().toString());
                CONTENT.getJewelleryItemList().deleteListItem(i-1);

            }
        }
        MaterialController.materialController.materialCleaner(getJewelToDelete());
        stockValue();
    }

    public void jewelleryCleaner(String trayInputID){ //method called from the tray deletion method in tray controller, deletes all trays associated with the passed tray id.
        for (int i = CONTENT.getJewelleryItemList().getListSize()-1; i >= 0; i--) {
            JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
            setJewelToDelete(JI.getTrayID());
            if (JI.getTrayID().contains(trayInputID)) {
                System.out.println("Deleted: "+CONTENT.getJewelleryItemList().get(i).getContent().toString());
                CONTENT.getJewelleryItemList().deleteListItem(i-1);
                MaterialController.materialController.materialCleaner(getJewelToDelete());
                MaterialController.materialController.jewelleryItemChoiceBox.getItems().remove(i);
            }
        }
        stockValue(); //recalculates the stock value.
    }

    public void deleteAllJewelleryItems(ActionEvent actionEvent){ //removes all jewellery items and materials. recalculates stock value.
        CONTENT.getJewelleryItemList().clearList();
        CONTENT.getMaterialList().clearList();
        JewelleryController.jewelleryController.jewelleryListView.getItems().clear();
        MaterialController.materialController.jewelleryItemChoiceBox.getItems().clear();
        MaterialController.materialController.materialListView.getItems().clear();
        SearchController.searchController.resultsList.getItems().clear();
        stockValue();
    }

    public void stockValue(){ //loops through the jewellery item list and adds up the total of item prices for an overall stock value.
        double totalValue = 0;
        for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
            totalValue = totalValue+CONTENT.getJewelleryItemList().get(i).getContent().getJewelleryPrice();
        }
        setStockTotalValue(String.valueOf(totalValue));
    }

    public void selectedCase(ActionEvent actionEvent) { //depending on the case chosen it populates the tray choice box with trays related to that case.
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

    public void selectedTray(ActionEvent actionEvent) { //depending on tray chosen it displays the associated jewellery items in the list view.
        try {
            jewelleryListView.getItems().clear();
            DisplayTray DT = trayChoiceBox.getSelectionModel().getSelectedItem();
            for(int i = CONTENT.getJewelleryItemList().getListSize() - 1; i >= 0 ; i--) {
                JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
                if (Objects.equals(DT.getTrayID(), JI.getTrayID())) {
                    jewelleryListView.getItems().add(JI);
                }
            }
        } catch (NullPointerException ignored){}
    }

    public void initialize(URL url, ResourceBundle resourceBundle){ //inititalises the jewellery controller.
        jewelleryController=this;
    }


}
