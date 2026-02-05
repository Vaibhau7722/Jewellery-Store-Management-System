package com.example.JewelleryStoreManagementSystem;

import java.io.Serializable;

public class DisplayTray implements Serializable {

    private String caseID;
    private String trayID;
    private String materialColor;
    private int trayWidth;
    private int trayDepth;

    public DisplayTray(String caseID, String trayID, String materialColor, int trayWidth, int trayDepth) {
        this.caseID = caseID;
        if(trayID.isEmpty()){
            this.trayID = Utilities.uniqueTrayIDEnforce(trayID);
        }else if (!Utilities.contains1LetterAndNumber(trayID)){
            this.trayID = Utilities.uniqueTrayIDEnforce(trayID);
        } else {
            this.trayID = trayID;
        }
        this.materialColor = materialColor;
        this.trayWidth = trayWidth;
        this.trayDepth = trayDepth;
    }

    //getters
    public String getCaseID() {return caseID;}
    public String getTrayID() {return trayID;}
    public String getMaterialColor() {return materialColor;}
    public int getTrayWidth() {return trayWidth;}
    public int getTrayDepth() {return trayDepth;}

    //setters
    public void setCaseID(String caseID) { this.caseID = caseID;}

    public void setTrayID(String trayID) {this.trayID = trayID;}

    public void setMaterialColor(String materialColor) {this.materialColor = materialColor;}

    public void setTrayWidth(int trayWidth) {this.trayWidth = trayWidth;}

    public void setTrayDepth(int trayDepth) {this.trayDepth = trayDepth;}

    @Override
    public String toString() {
        return "Tray ID: "+getTrayID()+", Inlay Material Color: "+getMaterialColor()+", Width: "+getTrayWidth()+", Depth: "+getTrayDepth()+".";

    }
}
