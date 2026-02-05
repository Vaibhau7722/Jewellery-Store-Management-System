package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.JewelleryItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;
public class SearchController implements Initializable {


    public static SearchController searchController;

    @FXML
    public TextField searchTerm;
    @FXML
    public ListView<JewelleryItem> resultsList;
    @FXML
    public Button searchButton;
    @FXML
    public Button viewPhoto;

    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void search(ActionEvent actionEvent) {
        setSearchString(searchTerm.getText());
        searchFunction(getSearchString());
    }

    public void searchFunction(String searchInput){
        if(searchInput.equals("")){
            resultsList.getItems().clear();
            populateJewelleryItems();
        } else {
            resultsList.getItems().clear();
            for(int i = 0; i < CONTENT.getJewelleryItemList().getListSize(); i++){
                JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
                if(JI.toString().toLowerCase().contains(searchInput.toLowerCase())){
                    resultsList.getItems().add(JI);
                }
            }
        }
    }

    public void populateJewelleryItems(){
        for (int i = CONTENT.getJewelleryItemList().getListSize()-1; i >= 0; i--){
            JewelleryItem JI = CONTENT.getJewelleryItemList().get(i).getContent();
            resultsList.getItems().add(JI);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        searchController = this;
    }
}

