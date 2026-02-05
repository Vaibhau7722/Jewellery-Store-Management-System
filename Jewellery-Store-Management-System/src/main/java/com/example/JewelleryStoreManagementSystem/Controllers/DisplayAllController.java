package com.example.JewelleryStoreManagementSystem.Controllers;

import com.example.JewelleryStoreManagementSystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.JewelleryStoreManagementSystem.Main.CONTENT;


public class DisplayAllController implements Initializable {

    public static DisplayAllController displayAllController;
    public Button refresh;
    public ListView<String> stockList;

    public String getDC() {
        return DC;
    }

    public void setDC(String DC) {
        this.DC = DC;
    }

    public String getDT() {
        return DT;
    }

    public void setDT(String DT) {
        this.DT = DT;
    }

    public String getJI() {
        return JI;
    }

    public void setJI(String JI) {
        this.JI = JI;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    private String DC;
    private String DT;
    private String JI;
    private String M;


    public void initialize(URL url, ResourceBundle resourceBundle){
        DisplayAllController displayAllController = this;
    }

    public void refresh(ActionEvent actionEvent) {
        stockList.getItems().clear();
        for (int i = 0; i < CONTENT.getCaseList().getListSize(); i++){
            DisplayCase DC = CONTENT.getCaseList().get(i).getContent();
            stockList.getItems().add(DC.toString());
            for (int j = 0; j < CONTENT.getTrayList().getListSize(); j++){
                DisplayTray DT = CONTENT.getTrayList().get(j).getContent();
                if(DT.getCaseID().contains(DC.getCaseID())){
                    stockList.getItems().add(DT.toString());
                    for(int n = 0; n < CONTENT.getJewelleryItemList().getListSize(); n++){
                        JewelleryItem JI = CONTENT.getJewelleryItemList().get(n).getContent();
                        if (JI.getTrayID().contains(DT.getTrayID())){
                            stockList.getItems().add(JI.toString());
                            for (int m = 0; m < CONTENT.getMaterialList().getListSize(); m++){
                                Material M = CONTENT.getMaterialList().get(m).getContent();
                                if (M.getItemSerialNum().contains(JI.getItemSerialNum())){
                                    stockList.getItems().add(M.toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
