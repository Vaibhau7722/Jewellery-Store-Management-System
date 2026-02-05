package com.example.JewelleryStoreManagementSystem;

import java.io.Serializable;

public class JewelleryItem implements Serializable {
    private String trayID;
    private String itemSerialNum;
    private String jewelleryType;
    private String targetGender;
    private String imageURL;
    private double jewelleryPrice;
    private String itemDescription;

    public JewelleryItem(String trayID, String itemSerialNum, String jewelleryType, String targetGender, String imageURL, double jewelleryPrice, String itemDescription){
        this.trayID = trayID;
        this.itemSerialNum = Utilities.uniqueSerialNumEnforce(itemSerialNum);
        this.jewelleryType = jewelleryType;
        this.targetGender = targetGender;
        this.imageURL = imageURL;
        this.jewelleryPrice = jewelleryPrice;
        this.itemDescription = itemDescription;
    }

    //getters
    public String getTrayID(){ return trayID;}
    public String getItemSerialNum(){ return itemSerialNum;}
    public String getJewelleryType() {
        return jewelleryType;
    }
    public String getTargetGender() {
        return targetGender;
    }
    public String getImageURL() {
        return imageURL;
    }
    public double getJewelleryPrice() {
        return jewelleryPrice;
    }
    public String getItemDescription() {
        return itemDescription;
    }

    //setters
    public void setItemSerialNum(String itemSerialNum){this.itemSerialNum=itemSerialNum;}

    public void setTrayID(String trayID){this.trayID=trayID;}

    public void setJewelleryType(String jewelleryType) {
        this.jewelleryType = jewelleryType;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setJewelleryPrice(double jewelleryPrice) {
        this.jewelleryPrice = jewelleryPrice;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String toString(){
        return "Jewellery: "+getJewelleryType()+", Serial Number: "+getItemSerialNum()+", Target Gender: "+getTargetGender()+", Image URL: "+getImageURL()+", Price: "+getJewelleryPrice()+".\nDescription: "+getItemDescription()+".";
    }
}
