package com.example.JewelleryStoreManagementSystem;

import java.io.Serializable;

public class DisplayCase implements Serializable {

    private String caseID;
    private String type;
    private String lighting;

    public DisplayCase( String caseID, String type, String lighting) {
        this.caseID = Utilities.uniqueCaseIDEnforce(caseID);
        this.type = type;
        this.lighting = lighting;
    }

    //Getters
    public String getCaseID() {
        return caseID;
    }

    public String getType() {
        return type;
    }

    public String getLighting() {
        return lighting;
    }

    //Setters
    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public String toString() {
        return "Case ID: "+getCaseID()+ ", Type: "+getType()+", Lighting: "+getLighting()+".";
    }



}
