package com.example.JewelleryStoreManagementSystem;

import com.example.JewelleryStoreManagementSystem.Controllers.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {

    public static class Content implements Serializable {
        public MyList<DisplayCase> caseList = new MyList<>();

        public MyList<DisplayCase> getCaseList() {
            return caseList;
        }

        public MyList<DisplayTray> trayList = new MyList<>();

        public MyList<DisplayTray> getTrayList() {
            return trayList;
        }

        public MyList<JewelleryItem> jewelleryItemList = new MyList<>();

        public MyList<JewelleryItem> getJewelleryItemList() {
            return jewelleryItemList;
        }

        public MyList<Material> materialList = new MyList<>();

        public MyList<Material> getMaterialList() {
            return materialList;
        }

    }

    public static Main.Content CONTENT;

    @Override
    public void start(Stage stage) throws IOException {
        CONTENT = new Content();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 722);
        stage.setTitle("Jewellery Store Management System");
        stage.setScene(scene);
        stage.show();
        try {
            EventHandler<MouseEvent> update = update1 -> { //event handler for updating the stock value when ever any of the below buttons are pressed.
                if (update1.getSource() == JewelleryController.jewelleryController.addJewelleryItem || update1.getSource() == JewelleryController.jewelleryController.deleteSelectedJewelleryItem || update1.getSource() == JewelleryController.jewelleryController.deleteAllJewelleryItems || update1.getSource() == MainController.mainController.load || update1.getSource() == MainController.mainController.clear || update1.getSource() == CaseController.caseController.deleteCase || update1.getSource() == TrayController.trayController.deleteSelectedTray) {
                    MainController.mainController.totalValueLabel.setText("€"+JewelleryController.jewelleryController.getStockTotalValue());
                }
                update1.consume();
            };
            CaseController.caseController.deleteCase.setOnMouseClicked(update); //handles what a button does when clicked.
            TrayController.trayController.deleteSelectedTray.setOnMouseClicked(update);
            JewelleryController.jewelleryController.addJewelleryItem.setOnMouseClicked(update);
            JewelleryController.jewelleryController.deleteSelectedJewelleryItem.setOnMouseClicked(update);
            JewelleryController.jewelleryController.deleteAllJewelleryItems.setOnMouseClicked(update);
            MainController.mainController.load.setOnMouseClicked(update);
            MainController.mainController.clear.setOnMouseClicked(update);

        } catch (NullPointerException ignored) {}

        EventHandler<MouseEvent> newWindow = imageWindow -> { //event handler for whenever the show jewellery image button is clicked it, creates a new image view window to display the given url of the chosen jewellery URL
            if(imageWindow.getSource() == InteractiveDisplayController.interactiveController.showJewelleryImage || imageWindow.getSource() == SearchController.searchController.viewPhoto) {

                Image image = new Image(InteractiveDisplayController.interactiveController.getURL());

                ImageView imageView = new ImageView(image);
                imageView.setImage(image);

                imageView.setX(50);
                imageView.setY(50);

                if (image.getHeight() >= 600 || image.getWidth() >= 600){
                    imageView.setFitHeight(image.getHeight()/2);
                    imageView.setFitWidth(image.getWidth()/2);
                }

                imageView.setPreserveRatio(false);

                Group root = new Group(imageView);
                Scene secondScene = new Scene(root, imageView.getFitWidth()+100, imageView.getFitHeight()+100);

                Stage window = new Stage();

                window.setTitle("Image of Jewellery Item: "+InteractiveDisplayController.interactiveController.getJewelleryItemSerialNumber());
                window.setScene(secondScene);


                window.setX(secondScene.getX()+100);
                window.setY(secondScene.getY()+100);

                window.show();
            }
            imageWindow.consume();
        };
        InteractiveDisplayController.interactiveController.showJewelleryImage.setOnMouseClicked(newWindow);
        SearchController.searchController.viewPhoto.setOnMouseClicked(newWindow);
    }

    public static void main(String[] args) {
        launch(args);
    }

}


