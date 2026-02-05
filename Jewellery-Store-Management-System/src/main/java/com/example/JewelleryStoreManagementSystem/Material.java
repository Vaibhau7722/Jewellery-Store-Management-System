package com.example.JewelleryStoreManagementSystem;

import java.io.Serializable;

public class Material implements Serializable {

    private String trayID;
    private String itemSerialNum;
    private String uniqueID;
    private String materialType;
    private String materialWeight;
    private String materialQuality;
    private String materialDescription;
    public Material(String trayID, String itemSerialNum, String uniqueID, String materialType, String materialWeight, String materialQuality, String materialDescription){
        this.trayID = trayID;
        this.itemSerialNum = itemSerialNum;
        this.uniqueID = Utilities.uniqueMaterialIDEnforce(uniqueID);
        this.materialType = materialType;
        this.materialWeight = materialWeight;
        this.materialQuality = materialQuality;
        this.materialDescription = materialDescription;
    }

    //getter
    public String getUniqueID(){return uniqueID;}
    public String getTrayID(){return trayID;}
    public String getItemSerialNum(){return itemSerialNum;}
    public String getMaterialType() {
        return materialType;
    }
    public String getMaterialWeight() {
        return materialWeight;
    }
    public String getMaterialQuality() {
        return materialQuality;
    }
    public String getMaterialDescription() {
        return materialDescription;
    }

    //setter
    public void setUniqueID(String uniqueID){this.uniqueID = uniqueID;}

    public void setTrayID(String trayID){this.trayID = trayID;}

    public void setItemSerialNum(String itemSerialNum){this.itemSerialNum = itemSerialNum;}

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setMaterialWeight(String materialWeight) {
        this.materialWeight = materialWeight;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String toString() {
        return "Tray ID: "+getTrayID()+", Jewellery Item Serial Number: "+getItemSerialNum()+", Type: "+getMaterialType()+", Weight: "+getMaterialWeight()+", Quality: "+getMaterialQuality()+".\nDescription: "+getMaterialDescription()+".";
    }
}
