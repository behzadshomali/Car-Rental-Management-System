package management;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Motor;
import vehicle.Truck;
import vehicle.Vehicle;

public final class Gui {
    Stage window;
    
    public Gui(Stage window){
        this.window = window;
        window.setResizable(false);
        BorderPane mainLayout = new BorderPane();
        ImageView homeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/homeIcon.png")));
        ImageView storesIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/storesIcon.png")));
        ImageView employeesIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/employeesIcon.png")));
        ImageView addEmployeeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/addIcon.png")));
        ImageView removeEmployeeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/removeIcon.png")));
        ImageView addStoreIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/addIcon.png")));
        ImageView removeStoreIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/removeIcon.png")));
        
        storesIcon.setPreserveRatio(true);
        storesIcon.setFitWidth(180);
        
        employeesIcon.setPreserveRatio(true);
        employeesIcon.setFitWidth(180);
        
        addEmployeeIcon.setPreserveRatio(true);
        addEmployeeIcon.setFitWidth(75);
        
        removeEmployeeIcon.setPreserveRatio(true);
        removeEmployeeIcon.setFitWidth(75);
        
        addStoreIcon.setVisible(false);
        removeStoreIcon.setVisible(false);
        
        addStoreIcon.setPreserveRatio(true);
        addStoreIcon.setFitWidth(75);
        
        removeStoreIcon.setPreserveRatio(true);
        removeStoreIcon.setFitWidth(75);
        
        addEmployeeIcon.setVisible(false);
        removeEmployeeIcon.setVisible(false);
        
        homeIcon.setOnMouseEntered(e -> {
            ScaleTransition scale = new ScaleTransition();
            scale.setNode(homeIcon);
            scale.setFromX(1);
            scale.setToX(1.2);
            scale.setFromY(1);
            scale.setToY(1.2);
            scale.setDuration(new Duration(200));
            scale.play();
        });
        
        homeIcon.setOnMouseExited(e -> {
            ScaleTransition scale = new ScaleTransition();
            scale.setNode(homeIcon);
            scale.setFromX(1.2);
            scale.setToX(1);
            scale.setFromY(1.2);
            scale.setToY(1);
            scale.setDuration(new Duration(200));
            scale.play();
        });
        
        Label inRentLabel = new Label("In-Rent List");
        Label vehiclesLabel = new Label("Vehicles List");
        Label salesLabel = new Label("Sales");
        
        
        
        HBox topPane = new HBox(30);
        AnchorPane centerPane = new AnchorPane();
        AnchorPane.setLeftAnchor(storesIcon, 120d);
        AnchorPane.setTopAnchor(storesIcon, 160d);
        AnchorPane.setRightAnchor(employeesIcon, 150d);
        AnchorPane.setTopAnchor(employeesIcon, 160d);
        centerPane.getChildren().addAll(addEmployeeIcon, addStoreIcon, removeEmployeeIcon, removeStoreIcon, storesIcon, employeesIcon);
        
        homeIcon.setPreserveRatio(true);
        homeIcon.setFitWidth(50);
        inRentLabel.setPadding(new Insets(15, 0, 0, 0));
        vehiclesLabel.setPadding(new Insets(15, 0, 0, 0));
        salesLabel.setPadding(new Insets(15, 0, 0, 0));
        topPane.getChildren().addAll(homeIcon, inRentLabel, vehiclesLabel, salesLabel);
        mainLayout.setTop(topPane);
        mainLayout.setCenter(centerPane);
        
        ListView<String> carsList = new ListView<>();
            ListView<String> busesList = new ListView<>();
            ListView<String> trucksList = new ListView<>();
            ListView<String> motorsList = new ListView<>();

        boolean[] salesLabelClicked = new boolean[1];
        salesLabelClicked[0] = false;
        salesLabel.setOnMouseClicked(e -> {
            if(!salesLabelClicked[0]){
                ImageView carView = new ImageView(new Image(getClass().getResourceAsStream("/images/carIcon.png")));
                ImageView busView = new ImageView(new Image(getClass().getResourceAsStream("/images/busIcon.png")));
                ImageView truckView = new ImageView(new Image(getClass().getResourceAsStream("/images/truckIcon.png")));
                ImageView motorView = new ImageView(new Image(getClass().getResourceAsStream("/images/motorIcon.png")));
                
                Label carSales = new Label();
                Label busSales = new Label();
                Label truckSales = new Label();
                Label motorSales = new Label();
                Label totalSale = new Label();
                
                double eachSum;
                double sum; 
                eachSum = 0;
                sum = 0;
                for(int i = 0; i < ManagementSystem.carIncomes.size(); i++)
                    eachSum += ManagementSystem.carIncomes.get(i);
                sum += eachSum;
                carSales.setText("Car-Income: " + eachSum + "$");
                
                eachSum = 0;
                for(int i = 0; i < ManagementSystem.busIncomes.size(); i++)
                    eachSum += ManagementSystem.busIncomes.get(i);
                sum += eachSum;
                busSales.setText("Bus-Income: " + eachSum + "$");
                
                eachSum = 0;
                for(int i = 0; i < ManagementSystem.truckIncomes.size(); i++)
                    eachSum += ManagementSystem.truckIncomes.get(i);
                sum += eachSum;
                truckSales.setText("Truck-Income: " + eachSum + "$");
                
                eachSum = 0;
                for(int i = 0; i < ManagementSystem.motorIncomes.size(); i++)
                    eachSum += ManagementSystem.motorIncomes.get(i);
                sum += eachSum;
                motorSales.setText("Motor-Income: " + eachSum + "$");
                
                totalSale.setText("Total income: " + sum + "$");
                
                VBox salesLayout = new VBox(25, carSales, busSales, truckSales, motorSales, totalSale);
                Scene salesScene = new Scene(salesLayout, 400, 300);
                Stage salesWindow = new Stage();
                salesWindow.setResizable(false);
                
//                totalSale.setPadding(new Insets(10, 0, 0, 70));
                salesLayout.setPadding(new Insets(10, 0, 0, 60));
                salesWindow.setScene(salesScene);
                salesWindow.show();
                
                salesLayout.getStylesheets().add("/css/sales.css");
                salesLabelClicked[0] = true;
                
                salesWindow.setOnCloseRequest(e2 -> salesLabelClicked[0] = false);
            }
        });
            
        vehiclesLabel.setOnMouseClicked(e -> {
            ImageView carView = new ImageView(new Image(getClass().getResourceAsStream("/images/carIcon.png")));
            ImageView busView = new ImageView(new Image(getClass().getResourceAsStream("/images/busIcon.png")));
            ImageView truckView = new ImageView(new Image(getClass().getResourceAsStream("/images/truckIcon.png")));
            ImageView motorView = new ImageView(new Image(getClass().getResourceAsStream("/images/motorIcon.png")));
            
            carView.setPreserveRatio(true);
            carView.setFitWidth(75);
            hoverScaleHandle(carView);
            
            busView.setPreserveRatio(true);
            busView.setFitWidth(75);
            hoverScaleHandle(busView);
            
            truckView.setPreserveRatio(true);
            truckView.setFitWidth(75);
            hoverScaleHandle(truckView);
            
            motorView.setPreserveRatio(true);
            motorView.setFitWidth(75);
            hoverScaleHandle(motorView);
            

            carsList.setOpacity(.9);
            busesList.setOpacity(.9);
            trucksList.setOpacity(.9);
            motorsList.setOpacity(.9);            
            
            VBox carLayout = new VBox(-20);
            carLayout.getChildren().addAll(carsList, carView);
 
            HBox listsLayout = new HBox(50);
            listsLayout.getChildren().addAll(carsList, busesList, trucksList, motorsList);
            
            HBox imagesLayout = new HBox(175);
            imagesLayout.getChildren().addAll(carView, busView, truckView, motorView);
            imagesLayout.setPadding(new Insets(0, 0, 0, 70));
            
            VBox listsimagesLayout = new VBox(-25);
            listsimagesLayout.getChildren().addAll(listsLayout, imagesLayout);
            listsimagesLayout.setPadding(new Insets(20, 20, 0, 20));
 
            BorderPane vehiclesMainLayout = new BorderPane();
            vehiclesMainLayout.setCenter(listsimagesLayout);
            vehiclesMainLayout.setTop(topPane);
            topPane.setPadding(new Insets(5, 0, 0, 5));

            vehiclesMainLayout.getStylesheets().add("/css/vehicles.css");
            
            Scene vehiclesScene = new Scene(vehiclesMainLayout, 1000, 600);
            window.setScene(vehiclesScene);
            
            boolean[] carClicked = new boolean[1];
            boolean[] busClicked = new boolean[1];
            boolean[] truckClicked = new boolean[1];
            boolean[] motorClicked = new boolean[1];
            carClicked[0] = false;
            busClicked[0] = false;
            truckClicked[0] = false;
            motorClicked[0] = false;

            ManagementSystem.listViewRemoveAbility(carsList);
            carsList.getItems().clear();
            for(int i = 0; i < ManagementSystem.vehicles.size(); i++){
                if(ManagementSystem.vehicles.get(i) instanceof Car)
                    carsList.getItems().add(ManagementSystem.vehicles.get(i).getModelName());
            }
            ManagementSystem.listViewRemoveAbility(busesList);
            busesList.getItems().clear();
            for(int i = 0; i < ManagementSystem.vehicles.size(); i++){
                if(ManagementSystem.vehicles.get(i) instanceof Bus)
                    busesList.getItems().add(ManagementSystem.vehicles.get(i).getModelName());
            }
            
            ManagementSystem.listViewRemoveAbility(trucksList);
            trucksList.getItems().clear();
            for(int i = 0; i < ManagementSystem.vehicles.size(); i++){
                if(ManagementSystem.vehicles.get(i) instanceof Truck)
                    trucksList.getItems().add(ManagementSystem.vehicles.get(i).getModelName());
            }
            ManagementSystem.listViewRemoveAbility(motorsList);
            motorsList.getItems().clear();
            for(int i = 0; i < ManagementSystem.vehicles.size(); i++){
                if(ManagementSystem.vehicles.get(i) instanceof Motor)
                    motorsList.getItems().add(ManagementSystem.vehicles.get(i).getModelName());
            }
            
            carView.setOnMouseClicked(e2 -> {
                if(!carClicked[0] && !e2.isControlDown()){
                    addVehicle("Car", carsList, carClicked);
                    carClicked[0] = true;
                }
                else if(!carClicked[0] && e2.isControlDown() && carsList.getSelectionModel().getSelectedItem() != null){
                    editVehicle("Car", carsList, carClicked);
                    carClicked[0] = true;
                }
            });
            busView.setOnMouseClicked(e2 -> {
                if(!busClicked[0] && !e2.isControlDown()){
                    addVehicle("Bus", busesList, busClicked);
                    busClicked[0] = true;
                }
                else if(!busClicked[0] && e2.isControlDown() && busesList.getSelectionModel().getSelectedItem() != null){
                    editVehicle("Bus", busesList, busClicked);
                    busClicked[0] = true;
                }
            });
            truckView.setOnMouseClicked(e2 -> {
                if(!truckClicked[0] && !e2.isControlDown()){
                    addVehicle("Truck", trucksList, truckClicked);
                    truckClicked[0] = true;
                }
                else if(!truckClicked[0] && e2.isControlDown() && trucksList.getSelectionModel().getSelectedItem() != null){
                    editVehicle("Truck", trucksList, truckClicked);
                    truckClicked[0] = true;
                }
            });
            motorView.setOnMouseClicked(e2 -> {
                if(!motorClicked[0] && !e2.isControlDown()){
                    addVehicle("Motor", motorsList, motorClicked);
                    motorClicked[0] = true;
                }
                else if(!motorClicked[0] && e2.isControlDown() && motorsList.getSelectionModel().getSelectedItem() != null){
                    editVehicle("Motor", motorsList, motorClicked);
                    motorClicked[0] = true;
                }
            });
        });
        
        boolean[] rentLabelClicked = new boolean[1];
        rentLabelClicked[0] = false;
        ListView<HBox> salesList = new ListView<>();
        
        if(ManagementSystem.carsInRent.isEmpty())
            inRentLabel.setDisable(true);
        
        inRentLabel.setOnMouseClicked(e -> {
            if(!rentLabelClicked[0]){
                salesList.getItems().clear();
                for(int i = 0; i < ManagementSystem.carsInRent.size(); i++){
                    Label nameLabel = new Label();
                    Label startDateLabel = new Label();
                    Label endDateLabel = new Label();
                    nameLabel.setMinWidth(150);
                    startDateLabel.setMinWidth(300);
//                    endDateLabel.setMinWidth(400);
                    nameLabel.setText("Name: " + ManagementSystem.carsInRent.get(i).getRenter());
                    startDateLabel.setText("Start: " + ManagementSystem.carsInRent.get(i).getStartDate().toString());
                    endDateLabel.setText("End: " + ManagementSystem.carsInRent.get(i).getEndDate().toString());
                    salesList.getItems().add(new HBox(20, nameLabel, startDateLabel, endDateLabel));
                }
                BorderPane inRentLayout = new BorderPane();
                inRentLayout.setCenter(salesList);

                Scene inRentScene = new Scene(inRentLayout, 750, 500);
                Stage inRentWindow = new Stage();
                inRentWindow.setResizable(false);
                inRentWindow.setScene(inRentScene);
                inRentWindow.setAlwaysOnTop(true);
                inRentWindow.show();
                inRentWindow.setOnCloseRequest(e2 -> rentLabelClicked[0] = false);
                rentLabelClicked[0] = true;
            }
            
        });
        
        boolean[] storeClicked = new boolean[1];
        boolean[] employeeClicked = new boolean[1];
        boolean[] addStoreClicked = new boolean[1];
        boolean[] removeEmployeeClicked = new boolean[1];
        
        storeClicked[0] = false;
        employeeClicked[0] = false;
        addStoreClicked[0] = false;
        removeEmployeeClicked[0] = false;
                
        boolean[] addEmployeeClicked = new boolean[1];
        addEmployeeClicked[0] = false;
        storesIcon.setOnMouseClicked(e -> {
            if(employeeClicked[0]){
                transitRotate(addEmployeeIcon, new Line(580, 170, 490, 250), 360);
                transitRotate(removeEmployeeIcon, new Line(580, 330, 490, 250), 0);
                employeeClicked[0] = false;
            }
            if(!e.isControlDown()){
                if(!storeClicked[0]){
                    addStoreIcon.setVisible(true);
                    removeStoreIcon.setVisible(true);
                    transitRotate(addStoreIcon, new Line(170, 250, 80, 170), 0);
                    transitRotate(removeStoreIcon, new Line(170, 250, 80, 330), 360);
                    storeClicked[0] = true;
                }
                else{
                    transitRotate(addStoreIcon, new Line(80, 170, 170, 250), 360);
                    transitRotate(removeStoreIcon, new Line(80, 330, 170, 250), 0);
                    storeClicked[0] = false;
                }
            }
            else{
                ListView<String> storesName = new ListView<>();
                ImageView garageView = new ImageView(new Image(getClass().getResourceAsStream("/images/garageIcon.png")));
                hoverScaleHandle(garageView);
                garageView.setPreserveRatio(true);
                garageView.setFitWidth(75);
                for(int i = 0; i < ManagementSystem.stores.size(); i++)
                    storesName.getItems().add(ManagementSystem.stores.get(i).getStoreName());
                
                VBox labelsLayout = new VBox(10);
                Label carsCountLabel = new Label("Available Cars: ----");
                Label codeLabel = new Label("Store Code: ----");
                Label garagescountLabel = new Label("Available Garages: ----");
                labelsLayout.getChildren().addAll(codeLabel, carsCountLabel, garagescountLabel);
                
                HBox storeCenterPane = new HBox(10);
                storeCenterPane.getChildren().addAll(storesName, labelsLayout);
                
                BorderPane storesLayout = new BorderPane();
                HBox bottomPane = new HBox(garageView);
                bottomPane.setPadding(new Insets(0, 0, 0, 620));
                storesLayout.setTop(topPane);
                storesLayout.setCenter(storeCenterPane);
                storesLayout.setBottom(bottomPane);
                Scene storeScene = new Scene(storesLayout, 700, 600);
                inRentLabel.setPadding(new Insets(15, 0, 0, 0));
                storesLayout.setPadding(new Insets(5, 0, 0, 5));
                storeCenterPane.setPadding(new Insets(30, 0, 45, 30));
                
                storesName.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
                    Store store = ManagementSystem.findStoreByName(newValue);
                    codeLabel.setText("Store Code: " + store.getStoreCode());
                    int count = 0;
                    
                    for(int i = 0; i < store.getGarages().size(); i++)
                        count += store.getGarages().get(i).getVehicles().size();
                    carsCountLabel.setText("Available Cars: " + count);
                    garagescountLabel.setText("Available Garages: " + store.getGarages().size());
                });
                
                storesName.setOnKeyPressed(e2 -> {
                    if(e2.getCode() == KeyCode.ENTER){
                        Store store = ManagementSystem.findStoreByName(storesName.getSelectionModel().getSelectedItem());
                        
                        ListView<String> vehiclenames = new ListView<>();
                        for(int i = 0; i < store.getGarages().size(); i++)
                            for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++)
                                vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                        
                        ToggleGroup group = new ToggleGroup();
                        
                        RadioButton allRB = new RadioButton("All");
                        allRB.setToggleGroup(group);
                        allRB.setSelected(true);
                        
                        RadioButton carRB = new RadioButton("Cars");
                        carRB.setToggleGroup(group);
                        
                        RadioButton busRB = new RadioButton("Buses");
                        busRB.setToggleGroup(group);
                        
                        RadioButton truckRB = new RadioButton("Trucks");
                        truckRB.setToggleGroup(group);
                        
                        RadioButton motorRB = new RadioButton("Motors");
                        motorRB.setToggleGroup(group);
                        
                        TextField nameField = new TextField();
                        TextField companyField = new TextField();
                        TextField priceField = new TextField();
                        TextField yearField = new TextField();
                        TextField passengersField = new TextField();
                        TextArea infoField = new TextArea();
                        
                        Label nameLabel = new Label("Name:");
                        Label companyLabel = new Label("Company:");
                        Label priceLabel = new Label("Base_Price:");
                        Label yearLabel = new Label("Year:");
                        Label passengersLabel = new Label("People:");
                        Label infoLabel = new Label("Info:");
                        
                        nameField.setOpacity(.8);
                        companyField.setOpacity(.8);
                        priceField.setOpacity(.8);
                        yearField.setOpacity(.8);
                        passengersField.setOpacity(.8);
                        infoField.setOpacity(.8);
                        
                        nameField.setEditable(false);
                        companyField.setEditable(false);
                        priceField.setEditable(false);
                        yearField.setEditable(false);
                        passengersField.setEditable(false);
                        infoField.setEditable(false);
                        
                        HBox listLayout = new HBox(vehiclenames);
                        vehiclenames.setMinWidth(400);
                        listLayout.setOpacity(.8);

                        VBox nameLayout = new VBox(5, nameLabel, nameField);
                        VBox companyLayout = new VBox(5, companyLabel, companyField);
                        VBox priceLayout = new VBox(5, priceLabel, priceField);
                        VBox yearLayout = new VBox(5, yearLabel, yearField);
                        VBox passengersLayout = new VBox(5, passengersLabel, passengersField);
                        VBox infoLayout = new VBox(5, infoLabel, infoField);
                        
                        HBox radioButtons = new HBox(20, allRB, carRB, busRB, truckRB, motorRB);
                        HBox nameCompanyPrice = new HBox(33, nameLayout, companyLayout, priceLayout);
                        VBox yearPassengers = new VBox(15, yearLayout, passengersLayout);
                        HBox yearPassengersInfo = new HBox(25, yearPassengers, infoLayout);
                        
                        VBox fieldsListLayout = new VBox(20, listLayout, radioButtons, nameCompanyPrice, yearPassengersInfo);
                        fieldsListLayout.setPadding(new Insets(15, 35, 20, 35));
                        listLayout.setPadding(new Insets(0, 0, 0, 115));
                        radioButtons.setPadding(new Insets(0, 0, 0, 115));
                        topPane.setPadding(new Insets(5, 0, 0, 5));
                        
                        BorderPane rentLayout = new BorderPane();
                        rentLayout.setTop(topPane);
                        rentLayout.setCenter(fieldsListLayout);
                        rentLayout.getStylesheets().add("/css/rent.css");
                        
                        Scene rentScene = new Scene(rentLayout, 700, 600);
                        window.setScene(rentScene);
                        
                        allRB.setOnAction(e3 -> {
                            vehiclenames.getItems().clear();
                            nameField.setText("");
                            companyField.setText("");
                            priceField.setText("");
                            yearField.setText("");
                            passengersField.setText("");
                            infoField.setText("");
                            for(int i = 0; i < store.getGarages().size(); i++){
                                for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++){
                                    vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                                }
                            }
                        });
                        carRB.setOnAction(e3 -> {
                            vehiclenames.getItems().clear();
                            nameField.setText("");
                            companyField.setText("");
                            priceField.setText("");
                            yearField.setText("");
                            passengersField.setText("");
                            infoField.setText("");
                            for(int i = 0; i < store.getGarages().size(); i++){
                                for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++){
                                    if(store.getGarages().get(i).getVehicles().get(j) instanceof Car)
                                        vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                                }
                            }
                        });
                        busRB.setOnAction(e3 -> {
                            vehiclenames.getItems().clear();
                            nameField.setText("");
                            companyField.setText("");
                            priceField.setText("");
                            yearField.setText("");
                            passengersField.setText("");
                            infoField.setText("");
                            for(int i = 0; i < store.getGarages().size(); i++){
                                for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++){
                                    if(store.getGarages().get(i).getVehicles().get(j) instanceof Bus)
                                        vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                                }
                            }
                        });
                        truckRB.setOnAction(e3 -> {
                            vehiclenames.getItems().clear();
                            nameField.setText("");
                            companyField.setText("");
                            priceField.setText("");
                            yearField.setText("");
                            passengersField.setText("");
                            infoField.setText("");
                            for(int i = 0; i < store.getGarages().size(); i++){
                                for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++){
                                    if(store.getGarages().get(i).getVehicles().get(j) instanceof Truck)
                                        vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                                }
                            }
                        });
                        motorRB.setOnAction(e3 -> {
                            vehiclenames.getItems().clear();
                            nameField.setText("");
                            companyField.setText("");
                            priceField.setText("");
                            yearField.setText("");
                            passengersField.setText("");
                            infoField.setText("");
                            for(int i = 0; i < store.getGarages().size(); i++){
                                for(int j = 0; j < store.getGarages().get(i).getVehicles().size(); j++){
                                    if(store.getGarages().get(i).getVehicles().get(j) instanceof Motor)
                                        vehiclenames.getItems().add(store.getGarages().get(i).getVehicles().get(j).getModelName());
                                }
                            }
                        });
                        
                        vehiclenames.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
                            if(newValue != null){
                                
                                Vehicle vehicle = ManagementSystem.findVehicleByName(newValue);
                                nameField.setText(vehicle.getModelName());
                                companyField.setText(vehicle.getManufacturer());
                                priceField.setText(Double.toString(vehicle.getPrice()));
                                yearField.setText(Integer.toString(vehicle.getManufactureYear()));
                                passengersField.setText(Integer.toString(vehicle.getPassengersCount()));
                                infoField.setText(vehicle.getInformation());
                                if(vehicle instanceof Car)
                                    infoField.setText(infoField.getText() + "\nMode: " + ((Car) vehicle).getMode());        
                            }
                        });
                        vehiclenames.setOnKeyPressed(e3 -> {
                            
                            if(e3.getCode() == KeyCode.ENTER){
                                Vehicle vehicle = ManagementSystem.findVehicleByName(vehiclenames.getSelectionModel().getSelectedItem());
                                
                                
                                TextField userField = new TextField();
                                TextField totalPriceField = new TextField();
                                DatePicker startDatePicker = new DatePicker();
                                
                                
                                TextField endDateField = new TextField();
                                
                                ImageView timeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/timeIcon.png")));
                                ImageView rentIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/rentIcon.png")));
                                ToggleGroup timeGroup = new ToggleGroup();
                                
                                RadioButton hourlyRB = new RadioButton("Hourly");
                                RadioButton dailyRB = new RadioButton("Daily");
                                RadioButton weeklyRB = new RadioButton("Weekly");
                                RadioButton monthlyRB = new RadioButton("Monthly (10% OFF)");
                                RadioButton yearlyRB = new RadioButton("Yearly (20% OFF)");
                                
                                hourlyRB.setToggleGroup(timeGroup);
                                dailyRB.setToggleGroup(timeGroup);
                                weeklyRB.setToggleGroup(timeGroup);
                                monthlyRB.setToggleGroup(timeGroup);
                                yearlyRB.setToggleGroup(timeGroup);
                                
                                dailyRB.setSelected(true);
                                timeIcon.setPreserveRatio(true);
                                timeIcon.setFitWidth(125);
                                hoverScaleHandle(timeIcon);
                                
                                rentIcon.setPreserveRatio(true);
                                rentIcon.setFitWidth(75);
                                
                                Thread shakingThread = new Thread(() -> {
                                    RotateTransition rotate = new RotateTransition(new Duration(1500), rentIcon);
                                    rotate.setFromAngle(-4);
                                    rotate.setToAngle(4);
                                    rotate.setAutoReverse(true);
                                    rotate.setCycleCount(2);
                                    while (window.isShowing())
                                        rotate.play();
                                });
                                shakingThread.start();

                                userField.setMaxWidth(400);
                                startDatePicker.setMaxWidth(200);
                                endDateField.setMaxWidth(200);
                                totalPriceField.setMaxWidth(200);
                                
                                startDatePicker.setEditable(false);
                                
                                Label userLabel = new Label("Renter full-Name:");
                                Label startLabel = new Label("Start Date:");
                                Label endLabel = new Label("How many ");
                                Label totalPriceLabel = new Label("Total Price:");
                                
                                VBox userLayout = new VBox(5, userLabel, userField);
                                VBox startLayout = new VBox(5, startLabel, startDatePicker);
                                VBox endLayout = new VBox(5, endLabel, endDateField);
                                VBox totalPriceLayout = new VBox(5, totalPriceLabel, totalPriceField);
                                VBox startEndLayout = new VBox(15, startLayout, endLayout, totalPriceLayout);
                                HBox timeIconLayout = new HBox(timeIcon);
                                HBox rentIconLayout = new HBox(rentIcon);
                                
                                VBox buttonsLayout = new VBox(5, hourlyRB, dailyRB, weeklyRB, monthlyRB, yearlyRB);
                                HBox datesTimeLayout = new HBox(20, startEndLayout, timeIconLayout);
                                VBox filedsLayout = new VBox(20, userLayout, datesTimeLayout);
                                BorderPane userRentLayout = new BorderPane();
                                userRentLayout.getStylesheets().add("css/userRent.css");
                                userRentLayout.setTop(topPane);
                                userRentLayout.setCenter(filedsLayout);
                                userRentLayout.setBottom(rentIconLayout);
                                filedsLayout.setPadding(new Insets(15, 0, 0, 30));
                                timeIconLayout.setPadding(new Insets(15, 0, 0, 20));
                                buttonsLayout.setPadding(new Insets(15, 0, 0, -120));
                                rentIconLayout.setPadding(new Insets(-70, 0, 0, 320));
                                
                                Scene userRentScene = new Scene(userRentLayout, 500, 400);
                                window.setScene(userRentScene);
                                
                                
                                timeIcon.setOnMouseClicked(e4 -> {
                                    FadeTransition fade = new FadeTransition(new Duration(500), timeIcon);
                                    fade.setFromValue(1);
                                    fade.setToValue(.1);
                                    fade.play();
                                    datesTimeLayout.getChildren().add(buttonsLayout);
                                    timeIcon.setDisable(true);
                                });
                                
                                rentIcon.setOnMouseClicked(e4 -> {
                                    inRentLabel.setDisable(false);
                                    Date endDate;
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                    
                                    Date getDate = java.sql.Date.valueOf(startDatePicker.getValue());
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(getDate);
                                    
                                    if(hourlyRB.isSelected()){
                                        String[] hourMinute = endDateField.getText().split(" ");
                                        if(hourMinute.length == 2){
                                            if(hourMinute[0].substring(2, 3).equals(":")){
                                                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourMinute[0].substring(0, 2)));
                                                calendar.set(Calendar.MINUTE, Integer.parseInt(hourMinute[0].substring(3, 5)));
                                            }
                                            else{
                                                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt("0" + hourMinute[0].substring(0, 1)));
                                                calendar.set(Calendar.MINUTE, Integer.parseInt(hourMinute[0].substring(2, 4)));
                                            }
                                            getDate = calendar.getTime();
                                            calendar.add(Calendar.HOUR, Integer.parseInt(hourMinute[1]));
                                            
                                        }
                                        else{
                                            calendar.set(Calendar.HOUR_OF_DAY, LocalDateTime.now().getHour());
                                            calendar.set(Calendar.MINUTE, LocalDateTime.now().getMinute());
                                            getDate = calendar.getTime();
                                            calendar.add(Calendar.HOUR, Integer.parseInt(hourMinute[0]));
                                        }
                                        
                                        endDate = calendar.getTime();
                                        
                                    }
                                    else if(dailyRB.isSelected()){
                                        calendar.add(Calendar.DATE, Integer.parseInt(endDateField.getText()));
                                        endDate = calendar.getTime();
                                    }
                                    else if(weeklyRB.isSelected()){
                                        calendar.add(Calendar.DATE, 7 * Integer.parseInt(endDateField.getText()));
                                        endDate = calendar.getTime();
                                    }
                                    else if(monthlyRB.isSelected()){
                                        calendar.add(Calendar.MONTH, Integer.parseInt(endDateField.getText()));
                                        endDate = calendar.getTime();
                                    }
                                    else{
                                        calendar.add(Calendar.YEAR, Integer.parseInt(endDateField.getText()));
                                        endDate = calendar.getTime();
                                    }
                                    int x;
                                    for(x = 0; x < ManagementSystem.carsInRent.size(); x++){
                                        if(ManagementSystem.carsInRent.get(x).getVehicle().equals(vehicle)){
                                            if(getDate.compareTo(ManagementSystem.carsInRent.get(x).getStartDate()) < 0 && endDate.compareTo(ManagementSystem.carsInRent.get(x).getStartDate()) < 0){
                                                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(vehicle, Double.parseDouble(totalPriceField.getText().split(" ")[0]),userField.getText(),getDate, endDate);
                                                ManagementSystem.carsInRent.add(borrowedVehicle);
                                                window.setScene(rentScene);
                                                break;
                                            }
                                            else if(getDate.compareTo(ManagementSystem.carsInRent.get(x).getEndDate()) > 0 && endDate.compareTo(ManagementSystem.carsInRent.get(x).getEndDate()) > 0){
                                                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(vehicle, Double.parseDouble(totalPriceField.getText().split(" ")[0]), userField.getText(),getDate, endDate);
                                                ManagementSystem.carsInRent.add(borrowedVehicle);
                                                window.setScene(rentScene);
                                                break;
                                            }
                                        }
                                    }
                                    if(x == ManagementSystem.carsInRent.size()){
                                        BorrowedVehicles borrowedVehicle;
                                        borrowedVehicle = new BorrowedVehicles(vehicle, Double.parseDouble(totalPriceField.getText().split(" ")[0]), userField.getText(), getDate, endDate);
                                        ManagementSystem.carsInRent.add(borrowedVehicle);
                                        window.setScene(rentScene);   
                                    }
                                    
                                    rentLayout.setTop(topPane);
                                    
                                });
                                
                                hourlyRB.setOnAction(e4 -> {
                                    endDateField.setPromptText("eg: 02:45 2");
                                    try{
                                        String[] hourMinute = endDateField.getText().split(" ");
                                        Calendar temp = Calendar.getInstance();
                                        temp.setTime(java.sql.Date.valueOf(startDatePicker.getValue()));
                                        double seasonOff = 1;
                                        if(temp.get(Calendar.MONTH) < 04 && vehicle instanceof Motor)
                                            seasonOff = 0.85;
                                        
                                        if(hourMinute.length == 2)
                                            totalPriceField.setText(Double.toString(vehicle.getPrice() * 0.05 * seasonOff * Integer.parseInt(hourMinute[1])) + " $");
                                        else
                                            totalPriceField.setText(Double.toString(vehicle.getPrice() * 0.05 * seasonOff * Integer.parseInt(hourMinute[0])) + " $");
                                    }catch(NumberFormatException e5){}
                                });
                                dailyRB.setOnAction(e4 -> {
                                    endLabel.setText("How many days?"); endDateField.setPromptText("");
                                    try{
                                        Calendar temp = Calendar.getInstance();
                                        temp.setTime(java.sql.Date.valueOf(startDatePicker.getValue()));
                                        
                                        double seasonOff = 1;
                                        if(temp.get(Calendar.MONTH) < 04 && vehicle instanceof Motor)
                                            seasonOff = 0.85;
                                        totalPriceField.setText(Double.toString(vehicle.getPrice() * seasonOff * Integer.parseInt(endDateField.getText())) + " $");
                                    }catch(NumberFormatException e5){}
                                });
                                weeklyRB.setOnAction(e4 -> {
                                    endLabel.setText("How many weeks?"); endDateField.setPromptText("");
                                    try{
                                        Calendar temp = Calendar.getInstance();
                                        temp.setTime(java.sql.Date.valueOf(startDatePicker.getValue()));
                                        
                                        double seasonOff = 1;
                                        if(temp.get(Calendar.MONTH) < 04 && vehicle instanceof Motor)
                                            seasonOff = 0.85;
                                        totalPriceField.setText(Double.toString(vehicle.getPrice() * 7 * seasonOff * Integer.parseInt(endDateField.getText())) + " $");
                                    }catch(NumberFormatException e5){}
                                });
                                monthlyRB.setOnAction(e4 -> {
                                    endLabel.setText("How many months?"); endDateField.setPromptText("");
                                    try{
                                        Calendar temp = Calendar.getInstance();
                                        temp.setTime(java.sql.Date.valueOf(startDatePicker.getValue()));
                                        
                                        double seasonOff = 1;
                                        if(temp.get(Calendar.MONTH) < 04 && vehicle instanceof Motor)
                                            seasonOff = 0.85;
                                        totalPriceField.setText(Double.toString(vehicle.getPrice() * 30 * 0.9 * seasonOff * Integer.parseInt(endDateField.getText())) + " $");
                                    }catch(NumberFormatException e5){}
                                });
                                yearlyRB.setOnAction(e4 -> {
                                    endLabel.setText("How many years?"); endDateField.setPromptText("");
                                    try{
                                        Calendar temp = Calendar.getInstance();
                                        temp.setTime(java.sql.Date.valueOf(startDatePicker.getValue()));
                                        
                                        double seasonOff = 1;
                                        if(temp.get(Calendar.MONTH) < 04 && vehicle instanceof Motor)
                                            seasonOff = 0.85;
                                        totalPriceField.setText(Double.toString(vehicle.getPrice() * 30 * 0.85 * seasonOff * Integer.parseInt(endDateField.getText())) + " $");
                                    }catch(NumberFormatException e5){}
                                });
                            }
                            
                        });
                    }
                });
                
                Stage garageWindow = new Stage();
                garageWindow.setResizable(false);
                garageView.setOnMouseClicked(e2 -> {
                    
                    ImageView addGarageView = new ImageView(new Image(getClass().getResourceAsStream("/images/addGarage.png")));
                    hoverScaleHandle(addGarageView);
                    addGarageView.setPreserveRatio(true);
                    addGarageView.setFitWidth(75);
                    
                    ImageView removeGarageView = new ImageView(new Image(getClass().getResourceAsStream("/images/removeGarage.png")));
                    hoverScaleHandle(removeGarageView);
                    removeGarageView.setPreserveRatio(true);
                    removeGarageView.setFitWidth(75);
                    
                    Label message = new Label("Enter the code of garage you \n     want to add or remove");
                    message.setPadding(new Insets(0, 0, 0, 10));
                    TextField codeField = new TextField();
                    
                    VBox fieldLabel = new VBox(5, message, codeField);
                    HBox buttonsLayout = new HBox(5, addGarageView, removeGarageView);
                    buttonsLayout.setPadding(new Insets(0, 0, 0, 35));
                    
                    BorderPane garageLayout = new BorderPane();
                    garageLayout.setCenter(fieldLabel);
                    garageLayout.setBottom(buttonsLayout);
                    garageLayout.setPadding(new Insets(20, 40, -1, 40));
                    garageLayout.getStylesheets().add("/css/garage.css");
                    
                    Scene garageScene = new Scene(garageLayout, 300, 200);
                    
                    garageWindow.setScene(garageScene);
                    garageWindow.setAlwaysOnTop(true);
                    garageWindow.show();
                    
                    
                    Label errorLabel = new Label();
                    errorLabel.setVisible(false);
                    errorLabel.setPadding(new Insets(10, 0, 0, 20));
                    FadeTransition fade = new FadeTransition(new Duration(1500), errorLabel);
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setAutoReverse(true);
                    fade.setCycleCount(2);
                    fieldLabel.getChildren().add(errorLabel);
                    
                    addGarageView.setOnMouseClicked(e3 -> {
                        try{
                            ManagementSystem.garages.add(new Garage(Integer.parseInt(codeField.getText())));
                            System.out.println("management.Gui.<init>()");
                            for(int i = 0; i < ManagementSystem.garages.size(); i++)
                                System.out.println(ManagementSystem.garages.get(i).getGarageCode());
                            garageWindow.close();
                        }catch(NumberFormatException e4){
                            codeField.setText("");
                            errorLabel.setVisible(true);
                            errorLabel.setText("Fill out the blank with a code");
                            fade.play();
                        }
                    });
                    removeGarageView.setOnMouseClicked(e3 -> {
                        try{
                            Garage garage = ManagementSystem.findGarageByCode(Integer.parseInt(codeField.getText()));
                            if(garage != null){
                                ManagementSystem.garages.remove(garage);
                                
                                for(int i = 0; i < garage.getVehicles().size(); i++)
                                    ManagementSystem.vehicles.remove(garage.getVehicles().get(i));
                                
                                List<Store> stores = ManagementSystem.findStoresByGarageCode(garage.getGarageCode());
                                for(int i = 0; i < stores.size(); i++){
                                    stores.get(i).getGarages().remove(garage);
                                    ManagementSystem.stores.remove(stores.get(i));
                                    ManagementSystem.stores.add(stores.get(i));
                                }
                                garageWindow.close();
                            }
                            else{
                                codeField.setText("");
                                errorLabel.setVisible(true);
                                errorLabel.setText("The garage does not exist:/");
                                fade.play();
                            }
                        }catch(NumberFormatException e4){
                            codeField.setText("");
                            errorLabel.setVisible(true);
                            errorLabel.setText("Fill out the blank with a code");
                            fade.play();
                        }
                    });
                });
                
                storeScene.getStylesheets().add("/css/main.css");
                window.setScene(storeScene);
            }
        });
        
        addStoreIcon.setOnMouseClicked(e -> {
            TextField nameField = new TextField();
            TextField codeField = new TextField();
            TextField countField = new TextField();
            
            Label nameLabel = new Label("Store Name:");
            Label garagesLabel = new Label("Garages Codes:");
            Label codeLabel = new Label("Store Code:");
            
            if(!addStoreClicked[0]){
                nameField.requestFocus();
                addEmployeeIcon.setVisible(false);
                removeEmployeeIcon.setVisible(false);
                VBox fieldsLayout = new VBox(0);

                nameField.setMinWidth(160);
                countField.setMinWidth(140);
                codeField.setMinWidth(120);
                
                nameLabel.setMinWidth(150);
                garagesLabel.setMinWidth(150);
                codeLabel.setMinWidth(150);
                
//                VBox.setMargin(countField, new Insets(0, 0, 0, 25));
//                VBox.setMargin(codeField, new Insets(0, 0, 0, 50));
                fieldsLayout.getChildren().addAll(nameLabel, nameField, garagesLabel, countField, codeLabel, codeField);

                mainLayout.getChildren().add(fieldsLayout);
                PathTransition employeeTransit = new PathTransition();
                employeeTransit.setNode(employeesIcon);
                employeeTransit.setPath(new Line(90, 100, 90, 500));
                employeeTransit.setDuration(new Duration(700));


                PathTransition fieldsTransit = new PathTransition();
                fieldsTransit.setNode(fieldsLayout);
                fieldsTransit.setPath(new Line(410, -200, 410, 200));
                fieldsTransit.setDuration(new Duration(700));

                SequentialTransition sequence = new SequentialTransition();
                sequence.getChildren().addAll(employeeTransit, fieldsTransit);
                sequence.play();

                transitRotate(addStoreIcon, new Line(80, 170, 170, 250), 360);
                transitRotate(removeStoreIcon, new Line(80, 330, 170, 250), 0);
                storeClicked[0] = false;
//                int[] count = new int[1];
                codeField.setOnKeyPressed(e2 -> {
                    if(e2.getCode() == KeyCode.ENTER){
                        
                        employeeTransit.setPath(new Line(90, 500, 90, 100));
                        fieldsTransit.setPath(new Line(410, 200, 410, -200));
                        sequence.getChildren().clear();
                        sequence.getChildren().addAll(fieldsTransit, employeeTransit);
                        sequence.play();
                        Store store = new Store(nameField.getText(), Integer.parseInt(codeField.getText()));
                        String[] gargaesCodes = countField.getText().split(" ");
//                        System.out.println("garags " + gargaesCodes[0]);
                        for(int  i = 0; i < gargaesCodes.length; i++)
                            store.getGarages().add(ManagementSystem.findGarageByCode(Integer.parseInt(gargaesCodes[i])));
                        ManagementSystem.stores.add(store);
                        nameField.setText("");
                        codeField.setText("");
                        addStoreClicked[0] = false;
                    }
                });

                nameField.setOnKeyPressed(e2 -> {
                    if(e2.getCode() == KeyCode.ESCAPE && addStoreClicked[0]){
                        employeeTransit.setPath(new Line(90, -350, 90, 100));
                        fieldsTransit.setPath(new Line(410, 200, 410, 600));
                        sequence.getChildren().clear();
                        sequence.getChildren().addAll(fieldsTransit, employeeTransit);
                        sequence.play();
                        nameField.setText("");
                        codeField.setText("");
                        addStoreClicked[0] = false;
                    }
                });
            }
            addStoreClicked[0] = true;
        });
        boolean[] removeStoreClicked = new boolean[1];
        removeStoreClicked[0] = false;
        
        removeStoreIcon.setOnMouseClicked(e2 -> {
            if(!removeStoreClicked[0] && !addStoreClicked[0]){ 
                transitRotate(addStoreIcon, new Line(80, 170, 170, 250), 360);
                transitRotate(removeStoreIcon, new Line(80, 330, 170, 250), 0);
                storeClicked[0] = false;
                TextField codeField = new TextField();
                codeField.setMaxWidth(190);
                Label message = new Label("Enter the store code");
                VBox fieldLabel = new VBox(5, message, codeField);

                centerPane.getChildren().add(fieldLabel);

                removeEmployeeIcon.setVisible(false);
                addEmployeeIcon.setVisible(false);
                PathTransition storeTransit = new PathTransition();
                storeTransit.setNode(employeesIcon);
                storeTransit.setPath(new Line(90, 90, 90, 500));
                storeTransit.setDuration(new Duration(700));


                PathTransition codeFieldTransit = new PathTransition();
                codeFieldTransit.setNode(fieldLabel);
                codeFieldTransit.setPath(new Line(490, -150, 490, 220));
                codeFieldTransit.setDuration(new Duration(700));


                SequentialTransition sequencential = new SequentialTransition();
                sequencential.getChildren().addAll(storeTransit, codeFieldTransit);

                sequencential.play();

                
                removeStoreClicked[0] = true;
                codeField.setOnKeyPressed(e -> { 
                    if(e.getCode() == KeyCode.ESCAPE){
                        codeFieldTransit.setPath(new Line(490, 220, 490, 600));
                        storeTransit.setPath(new Line(90, -350, 90, 90));
                        sequencential.getChildren().clear();
                        sequencential.getChildren().addAll(codeFieldTransit, storeTransit);
                        sequencential.play();
                        
                        removeStoreClicked[0] = false;
                        codeField.setText("");
                        storesIcon.requestFocus();
                    }
                    else if(e.getCode() == KeyCode.ENTER){
                        Store store = ManagementSystem.findStoreByCode(Integer.parseInt(codeField.getText()));
                        if(store != null){
                            ManagementSystem.stores.remove(store);
                            Label okMessage = new Label("The intended store has been\n\t removed succesfully");
                            centerPane.getChildren().add(okMessage);

                            PathTransition okMessageTransit = new PathTransition();
                            okMessageTransit.setNode(okMessage);
                            okMessageTransit.setPath(new Line(490, -150, 490, 600));
                            okMessageTransit.setDuration(new Duration(3000));

                            codeFieldTransit.setPath(new Line(490, 220, 490, 600));
                            storeTransit.setPath(new Line(90, -320, 90, 90));
                            storeTransit.setDuration(new Duration(1000));

                            sequencential.getChildren().clear();
                            sequencential.getChildren().addAll(codeFieldTransit, okMessageTransit, storeTransit);
                            sequencential.play();
                        }
                        removeStoreClicked[0] = false;
                    }
                });
            }
            else
            {
                transitRotate(addStoreIcon, new Line(80, 170, 170, 250), 0);
                transitRotate(removeStoreIcon, new Line(80, 330, 170, 250), 360);
            }
            storeClicked[0] = false;
        });
        employeesIcon.setOnMouseClicked(e -> {
            if(storeClicked[0]){
                transitRotate(addStoreIcon, new Line(80, 170, 170, 250), 360);
                transitRotate(removeStoreIcon, new Line(80, 330, 170, 250), 0);
                storeClicked[0] = false;
            }
            
            if(!employeeClicked[0]){
                addEmployeeIcon.setVisible(true);
                removeEmployeeIcon.setVisible(true);
                
                transitRotate(addEmployeeIcon, new Line(490, 250, 580, 170), 0);
                transitRotate(removeEmployeeIcon, new Line(490, 250, 580, 330), 360);
                employeeClicked[0] = true;
            }
            else{
                transitRotate(addEmployeeIcon, new Line(580, 170, 490, 250), 360);
                transitRotate(removeEmployeeIcon, new Line(580, 330, 490, 250), 0);
                employeeClicked[0] = false;
            }
        });
        
        hoverScaleHandle(employeesIcon);
        hoverScaleHandle(storesIcon);
        hoverScaleHandle(addEmployeeIcon);
        hoverScaleHandle(removeEmployeeIcon);
        hoverScaleHandle(addStoreIcon);
        hoverScaleHandle(removeStoreIcon);
        
        mainLayout.setPadding(new Insets(5, 0, 0, 5));
        Scene mainScene = new Scene(mainLayout, 700, 600);
        mainLayout.getStylesheets().add("css/main.css");


//        logIn(mainScene);
        window.setScene(mainScene);
        window.show();
        
        homeIcon.setOnMouseClicked(e -> {
            mainLayout.setTop(topPane);
            window.setScene(mainScene);
        });
        
        Label headerLabel = new Label("Enter the employee full name");
        headerLabel.getStyleClass().clear();
        headerLabel.setStyle("-fx-font-size: 20;\n" +
"    -fx-font-weight: bold;");
        TextField employeeNameField = new TextField();
        headerLabel.setPadding(new Insets(0, 0, 0, -65));

        employeeNameField.setMaxWidth(150);
        VBox fieldLabel = new VBox(5);
        fieldLabel.getChildren().addAll(headerLabel, employeeNameField);
        fieldLabel.setVisible(false);
        centerPane.getChildren().add(fieldLabel);
        
        removeEmployeeIcon.setOnMouseClicked(e -> {
            if(!removeEmployeeClicked[0]){
                removeStoreIcon.setVisible(false);
                addStoreIcon.setVisible(false);
                fieldLabel.setVisible(true);
                PathTransition storeTransit = new PathTransition();
                storeTransit.setNode(storesIcon);
                storeTransit.setPath(new Line(90, 90, 90, 500));
                storeTransit.setDuration(new Duration(700));


                PathTransition nameFieldTransit = new PathTransition();
                nameFieldTransit.setNode(fieldLabel);
                nameFieldTransit.setPath(new Line(210, -100, 210, 220));
                nameFieldTransit.setDuration(new Duration(700));
                

                SequentialTransition sequence = new SequentialTransition();
                sequence.getChildren().addAll(storeTransit, nameFieldTransit);
                sequence.play();
               
                employeeNameField.requestFocus();
                
                removeEmployeeClicked[0] = true;
            }
        });
        
        addEmployeeIcon.setOnMouseClicked(e -> {
            if(!addEmployeeClicked[0]){
                ToggleGroup group = new ToggleGroup();

                TextField nameField= new TextField();
                Label nameLabel = new Label("Name: ");

                TextField lastnameField = new TextField();
                Label lastnameLabel = new Label("Lastname: ");

                TextField ageField = new TextField();
                Label ageLabel = new Label("Age: ");

                RadioButton maleRB = new RadioButton("Male");
                RadioButton femaleRB = new RadioButton("Female");
                maleRB.setToggleGroup(group);
                femaleRB.setToggleGroup(group);
                maleRB.setSelected(true);

                ImageView submit = new ImageView(new Image(getClass().getResourceAsStream("/images/submitButton.png")));
                submit.setPreserveRatio(true);
                submit.setFitWidth(150);
                hoverScaleHandle(submit);
                HBox temp = new HBox(submit);
                GridPane addEmployeeLayout = new GridPane();
                GridPane.setConstraints(nameLabel, 0, 0);
                GridPane.setConstraints(nameField, 1, 0);
                GridPane.setConstraints(lastnameLabel, 0, 1);
                GridPane.setConstraints(lastnameField, 1, 1);
                GridPane.setConstraints(ageLabel, 0, 2);
                GridPane.setConstraints(ageField, 1, 2);
                GridPane.setConstraints(maleRB, 0, 3);
                GridPane.setConstraints(femaleRB, 1, 3);
                GridPane.setConstraints(temp, 1, 4);
                addEmployeeLayout.getChildren().addAll(nameLabel, nameField, lastnameLabel, lastnameField, ageLabel, ageField, maleRB, femaleRB, temp);
                addEmployeeLayout.setVgap(10);
                addEmployeeLayout.setHgap(5);
                addEmployeeLayout.setPadding(new Insets(40, 70, 0, 70));
                addEmployeeLayout.getStylesheets().add("/css/garage.css");
                maleRB.setPadding(new Insets(0, 0, 0, 40));
                femaleRB.setPadding(new Insets(0, 0, 0, 70));
                temp.setPadding(new Insets(76, 0, 0, -30));
                
                Scene addEmployeeScene = new Scene(addEmployeeLayout, 395, 300);
                
                
                Stage addEmployeeWindow = new Stage();
                addEmployeeWindow.setResizable(false);
            
                addEmployeeWindow.setScene(addEmployeeScene);
                addEmployeeWindow.show();
                addEmployeeWindow.setAlwaysOnTop(true);
                
                submit.setOnMouseClicked(e2 -> {
                    boolean gender;
                    gender = group.getSelectedToggle().equals(femaleRB);
                    ManagementSystem.employees.add(new Employee(nameField.getText(), lastnameField.getText(), Integer.parseInt(ageField.getText()), gender));
                    addEmployeeWindow.close();
                    addEmployeeClicked[0] = false;
                });
                addEmployeeWindow.setOnCloseRequest(e2 -> addEmployeeClicked[0] = false);
            }
            
            addEmployeeClicked[0] = true;
        });
        
        employeeNameField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE && removeEmployeeClicked[0]){
                PathTransition storeTransit = new PathTransition();
                storeTransit.setNode(storesIcon);
                storeTransit.setPath(new Line(90, -350, 90, 90));
                storeTransit.setDuration(new Duration(700));

                PathTransition nameFieldTransit = new PathTransition();
                nameFieldTransit.setNode(fieldLabel);
                nameFieldTransit.setPath(new Line(210, 220, 210, 600));
                nameFieldTransit.setDuration(new Duration(700));

                SequentialTransition sequence = new SequentialTransition();
                sequence.getChildren().addAll(nameFieldTransit, storeTransit);
                sequence.play();
                
                employeeNameField.setText("");
                headerLabel.setText("Enter the employee full name");
                removeEmployeeClicked[0] = false;
            }
            else if(e.getCode() == KeyCode.ENTER){
                if(!ManagementSystem.removeEmployee(employeeNameField.getText()))
                    headerLabel.setText("Please check the name again...");
                else{
                    headerLabel.setText("Enter the employee full name");
                    Label message = new Label();
                    message.setText("The employee removed \n\t   succesfully!");
                    centerPane.getChildren().add(message);
                    
                    PathTransition nameFieldTransit = new PathTransition();
                    nameFieldTransit.setNode(fieldLabel);
                    nameFieldTransit.setPath(new Line(210, 220, 210, 600));
                    nameFieldTransit.setDuration(new Duration(700));
                    
                    PathTransition messageTransition = new PathTransition();
                    messageTransition.setNode(message);
                    messageTransition.setPath(new Line(190, -110, 190, 600));
                    messageTransition.setDuration(new Duration(3000));
                    
                    PathTransition storeTransit = new PathTransition();
                    storeTransit.setNode(storesIcon);
                    storeTransit.setPath(new Line(90, -350, 90, 90));
                    storeTransit.setDuration(new Duration(1500));
                    
                    SequentialTransition sequence = new SequentialTransition();
                    sequence.getChildren().addAll(nameFieldTransit, messageTransition, storeTransit);
                    sequence.play();
                }
            }
        });   
        window.setOnCloseRequest(e -> {
            Label message = new Label("Do you want to save your work?");
            Button saveButton = new Button("Save");
            Button discardButton = new Button("Discard");
            Button cancelButton = new Button("Cancel");
            
            HBox buttonsLayout = new HBox(5, saveButton, discardButton, cancelButton);
            VBox closeLayout = new VBox(10, message, buttonsLayout);
            
            saveButton.setMinWidth(120);
            discardButton.setMinWidth(120);
            cancelButton.setMinWidth(120);
            
            buttonsLayout.setPadding(new Insets(0, 0, 0, 15));
            message.setPadding(new Insets(10, 0, 0, 15));
            
            Scene closeScene = new Scene(closeLayout, 410, 105);
            Stage closeWindow = new Stage();
            closeWindow.setResizable(false);
            closeWindow.setScene(closeScene);
            closeWindow.setAlwaysOnTop(true);
            e.consume();
            closeWindow.initStyle(StageStyle.UTILITY);
            closeLayout.getStylesheets().add("/css/close.css");
            closeWindow.show();
            
            cancelButton.setOnAction(e2 -> closeWindow.close());
            discardButton.setOnAction(e2 -> {
                window.close();
                closeWindow.close();
            });
            saveButton.setOnAction(e2 -> {
                try {
                    WriteJSON write = new WriteJSON();
                    write.writeBorrwed("src/sales.JSON");
                    write.writeGaragesStores("src/test.JSON");
                    write.writeIncomes("src/incomes.JSON");
                    write.writeEmployees("src/employees.JSON");
                    
                    window.close();
                    closeWindow.close();
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
    }
    
    
    
    public void logIn(Scene mainScene){
        GridPane fields = new GridPane();
        TextField userField = new TextField();
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        ScaleTransition userScale = new ScaleTransition();
        ScaleTransition passScale = new ScaleTransition();
        ScaleTransition buttonScale = new ScaleTransition();
        
        GridPane.setConstraints(userField, 0, 0);
        GridPane.setConstraints(passField, 0, 1);
        GridPane.setConstraints(loginButton, 0, 2);
        GridPane.setRowSpan(loginButton, 30);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        fields.setVgap(3);
        fields.setPadding(new Insets(200, 75, 0, 0));
        fields.setAlignment(Pos.BASELINE_RIGHT);
        fields.getChildren().addAll(userField, passField, loginButton);
        
        loginButton.setPrefWidth(100);
        userScale.setNode(userField);
        userScale.setFromX(0);
        userScale.setFromY(0);
        userScale.setToX(1);
        userScale.setToY(1);
        userScale.setDelay(new Duration(250));
        userScale.setDuration(new Duration(1000));
        userScale.play();
        
        passScale.setNode(passField);
        passScale.setFromX(0);
        passScale.setFromY(0);
        passScale.setToX(1);
        passScale.setToY(1);
        passScale.setDelay(new Duration(250));
        passScale.setDuration(new Duration(1000));
        
        buttonScale.setNode(loginButton);
        buttonScale.setFromX(0);
        buttonScale.setFromY(0);
        buttonScale.setToX(1);
        buttonScale.setToY(1);
        buttonScale.setDuration(new Duration(750));
        
        SequentialTransition sequence = new SequentialTransition();
        sequence.getChildren().addAll(passScale, buttonScale);
        sequence.play();
        
        Scene loginScene = new Scene(fields, 1280, 650);
        window.setScene(loginScene);
        loginScene.getStylesheets().add("css/login.css");
        window.show();
        
        passField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER)
                if(ManagementSystem.loggingIn(userField.getText(), passField.getText()))
                    window.setScene(mainScene);
        });
        
        loginButton.setOnAction(e -> {
            if(ManagementSystem.loggingIn(userField.getText(), passField.getText()))
                window.setScene(mainScene);
        });
    }
    
    public void hoverScaleHandle(Node myNode){
        myNode.setOnMouseEntered(e -> {
            ScaleTransition scale = new ScaleTransition();
            scale.setNode(myNode);
            scale.setFromX(1);
            scale.setFromY(1);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setDuration(new Duration(200));
            scale.play();
        });
        
        myNode.setOnMouseExited(e -> {
            ScaleTransition scale = new ScaleTransition();
            scale.setNode(myNode);
            scale.setFromX(1.1);
            scale.setFromY(1.1);
            scale.setToX(1);
            scale.setToY(1);
            scale.setDuration(new Duration(200));
            scale.play();
        });
    }
    
    public void transitRotate(Node myNode, Line path, int fromAngle){
        PathTransition transit = new PathTransition();
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(myNode);
        rotate.setFromAngle(fromAngle);
        rotate.setToAngle(360 - fromAngle);
        rotate.setDuration(new Duration(500));
        rotate.play();
        
        transit.setNode(myNode);
        transit.setPath(path);
        transit.setDuration(new Duration(500));
        transit.play();
    }
    
    public void addVehicle(String vehicleCategory, ListView<String> list, boolean[] clicked){
        
        TextField nameField = new TextField();
        Label nameLabel = new Label("Name: ");
        nameField.setMinWidth(210);

        TextField companyField = new TextField();
        Label companyLabel = new Label("Company: ");

        TextField yearField = new TextField();
        yearField.setMaxWidth(60);
        Label yearLabel = new Label("Year: ");

        TextArea infoField = new TextArea();
        Label infoLabel = new Label("Info: ");
        infoField.setMinHeight(100);
        infoLabel.setMinWidth(60);


        TextField passengersField = new TextField();
        passengersField.setMaxWidth(60);
        Label passengerLabel = new Label("People: ");

        TextField priceField = new TextField();
        priceField.setMaxWidth(60);
        Label priceLabel = new Label("Price: ");
        
        TextField garageField = new TextField();
        garageField.setMaxWidth(60);
        Label garageLabel = new Label("Garage: ");

        ToggleGroup modes = new ToggleGroup();
        Label modeLabel = new Label("Mode: ");
        RadioButton sportRB = new RadioButton("Sport");
        RadioButton economicRB = new RadioButton("Economic");
        RadioButton luxuryRB = new RadioButton("Luxury");

        hoverScaleHandle(sportRB);
        hoverScaleHandle(economicRB);
        hoverScaleHandle(luxuryRB);
        sportRB.setDisable(true);
        economicRB.setDisable(true);
        luxuryRB.setDisable(true);
        sportRB.setToggleGroup(modes);
        sportRB.setSelected(true);
        economicRB.setToggleGroup(modes);
        luxuryRB.setToggleGroup(modes);

        HBox nameLayout = new HBox(10, nameLabel, nameField);
        HBox companyLayout = new HBox(10, companyLabel, companyField);
        HBox priceYearPassengerGarageLayout = new HBox(3, priceLabel, priceField, yearLabel, yearField, passengerLabel, passengersField, garageLabel, garageField);
        HBox modeLayout = new HBox(10, modeLabel, sportRB, economicRB, luxuryRB); 
        HBox infoLayout = new HBox(infoLabel, infoField);

        ImageView submitButton = new ImageView(new Image(getClass().getResourceAsStream("/images/submitButton.png")));
        submitButton.setPreserveRatio(true);
        submitButton.setFitWidth(120);
        hoverScaleHandle(submitButton);
        HBox buttonLayout = new HBox(submitButton);

        VBox addVehicleLayout = new VBox(15, nameLayout, companyLayout, priceYearPassengerGarageLayout, modeLayout, infoLayout, buttonLayout);
        addVehicleLayout.setPadding(new Insets(20, 20, 40, 20));
        addVehicleLayout.getStylesheets().add("/css/addVehicle.css");
        priceLabel.setPadding(new Insets(5, 0, 0, 0));
        yearLabel.setPadding(new Insets(5, 0, 0, 10));
        passengerLabel.setPadding(new Insets(5, 0, 0, 10));
        garageLabel.setPadding(new Insets(5, 0, 0, 10));
        buttonLayout.setPadding(new Insets(32, 0, 0, 180));

        Scene addVehicleScene = new Scene(addVehicleLayout, 525, 355);
        Stage addVehicleWindow = new Stage();
        addVehicleWindow.setResizable(false);
        addVehicleWindow.setScene(addVehicleScene);
        addVehicleWindow.show();
        addVehicleWindow.setAlwaysOnTop(true);
        
            switch(vehicleCategory){
                case "Car":
                    sportRB.setDisable(false);
                    economicRB.setDisable(false);
                    luxuryRB.setDisable(false);
                    submitButton.setOnMouseClicked(e -> {
                        Car car = new Car(companyField.getText(), nameField.getText(), Integer.parseInt(yearField.getText())
                                ,Integer.parseInt(passengersField.getText()), infoField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(garageField.getText()));
                        if(sportRB.isSelected())
                            car.setMode("Sport");
                        else if(economicRB.isSelected())
                            car.setMode("Economic");
                        else
                            car.setMode("Luxury");
                        ManagementSystem.vehicles.add((Vehicle)car);
                        Garage garage = ManagementSystem.findGarageByCode(Integer.parseInt(garageField.getText()));
                        garage.getVehicles().add(car);
                        list.getItems().add(car.getModelName());
                        addVehicleWindow.close();
                        clicked[0] = false;
                    });
                    break;
                case "Bus":
                    submitButton.setOnMouseClicked(e -> {
                        Bus bus = new Bus(companyField.getText(), nameField.getText(), Integer.parseInt(yearField.getText())
                                ,Integer.parseInt(passengersField.getText()), infoField.getText(), Double.parseDouble(priceField.getText()),  Integer.parseInt(garageField.getText()));
                        ManagementSystem.vehicles.add((Vehicle)bus);
                        Garage garage = ManagementSystem.findGarageByCode(Integer.parseInt(garageField.getText()));
                        garage.getVehicles().add(bus);
                        list.getItems().add(bus.getModelName());
                        addVehicleWindow.close();
                        clicked[0] = false;
                    });
                    break;
                case "Truck":
                    submitButton.setOnMouseClicked(e -> {
                        Truck truck = new Truck(companyField.getText(), nameField.getText(), Integer.parseInt(yearField.getText())
                                ,Integer.parseInt(passengersField.getText()), infoField.getText(), Double.parseDouble(priceField.getText()),  Integer.parseInt(garageField.getText()));
                        ManagementSystem.vehicles.add((Vehicle)truck);
                        Garage garage = ManagementSystem.findGarageByCode(Integer.parseInt(garageField.getText()));
                        garage.getVehicles().add(truck);
                        list.getItems().add(truck.getModelName());
                        addVehicleWindow.close();
                        clicked[0] = false;
                    });
                    break;
                default:
                    submitButton.setOnMouseClicked(e -> {
                        Motor motor = new Motor(companyField.getText(), nameField.getText(), Integer.parseInt(yearField.getText())
                                ,Integer.parseInt(passengersField.getText()), infoField.getText(), Double.parseDouble(priceField.getText()),  Integer.parseInt(garageField.getText()));
                        ManagementSystem.vehicles.add((Vehicle)motor);
                        Garage garage = ManagementSystem.findGarageByCode(Integer.parseInt(garageField.getText()));
                        garage.getVehicles().add(motor);
                        list.getItems().add(motor.getModelName());
                        addVehicleWindow.close();
                        clicked[0] = false;
                    });
            }
        addVehicleWindow.setOnCloseRequest(e -> clicked[0] = false);
    }
    
    public void editVehicle(String vehicleCategory, ListView<String> list, boolean[] clicked){
        
        TextField nameField = new TextField();
        Label nameLabel = new Label("Name: ");
        nameField.setMinWidth(210);

        TextField companyField = new TextField();
        Label companyLabel = new Label("Company: ");

        TextField yearField = new TextField();
        yearField.setMaxWidth(60);
        Label yearLabel = new Label("Year: ");

        TextArea infoField = new TextArea();
        Label infoLabel = new Label("Info: ");
        infoField.setMinHeight(100);
        infoLabel.setMinWidth(60);


        TextField passengersField = new TextField();
        passengersField.setMaxWidth(60);
        Label passengerLabel = new Label("Passengers: ");

        TextField priceField = new TextField();
        priceField.setMaxWidth(60);
        Label priceLabel = new Label("Price: ");

        ToggleGroup modes = new ToggleGroup();
        Label modeLabel = new Label("Mode: ");
        RadioButton sportRB = new RadioButton("Sport");
        RadioButton economicRB = new RadioButton("Economic");
        RadioButton luxuryRB = new RadioButton("Luxury");

        hoverScaleHandle(sportRB);
        hoverScaleHandle(economicRB);
        hoverScaleHandle(luxuryRB);
        sportRB.setDisable(true);
        economicRB.setDisable(true);
        luxuryRB.setDisable(true);
        sportRB.setToggleGroup(modes);
        sportRB.setSelected(true);
        economicRB.setToggleGroup(modes);
        luxuryRB.setToggleGroup(modes);

        HBox nameLayout = new HBox(10, nameLabel, nameField);
        HBox companyLayout = new HBox(10, companyLabel, companyField);
        HBox priceYearPassengerLayout = new HBox(5, priceLabel, priceField, yearLabel, yearField, passengerLabel, passengersField);
        HBox modeLayout = new HBox(10, modeLabel, sportRB, economicRB, luxuryRB); 
        HBox infoLayout = new HBox(infoLabel, infoField);

        ImageView submitButton = new ImageView(new Image(getClass().getResourceAsStream("/images/submitButton.png")));
        submitButton.setPreserveRatio(true);
        submitButton.setFitWidth(120);
        hoverScaleHandle(submitButton);
        HBox buttonLayout = new HBox(submitButton);

        VBox addVehicleLayout = new VBox(15, nameLayout, companyLayout, priceYearPassengerLayout, modeLayout, infoLayout, buttonLayout);
        addVehicleLayout.setPadding(new Insets(20, 25, 40, 20));
        priceLabel.setPadding(new Insets(5, 0, 0, 0));
        yearLabel.setPadding(new Insets(5, 0, 0, 20));
        passengerLabel.setPadding(new Insets(5, 0, 0, 20));
        buttonLayout.setPadding(new Insets(32, 0, 0, 170));

        Scene addVehicleScene = new Scene(addVehicleLayout, 500, 355);
        Stage addVehicleWindow = new Stage();
        addVehicleWindow.setResizable(false);
        addVehicleWindow.setScene(addVehicleScene);
        addVehicleWindow.show();
        addVehicleWindow.setAlwaysOnTop(true);
        
        Vehicle vehicle = ManagementSystem.findVehicleByName(list.getSelectionModel().getSelectedItem());

        nameField.setText(vehicle.getModelName());
        companyField.setText(vehicle.getManufacturer());
        yearField.setText(Integer.toString(vehicle.getManufactureYear()));
        passengersField.setText(Integer.toString(vehicle.getPassengersCount()));
        priceField.setText(Double.toString(vehicle.getPrice()));
        infoField.setText(vehicle.getInformation());

        if(vehicleCategory.equalsIgnoreCase("Car")){
            sportRB.setDisable(false);
            economicRB.setDisable(false);
            luxuryRB.setDisable(false);
            switch(((Car)vehicle).getMode()){
                case "Sport":
                    sportRB.setSelected(true);
                    break;
                case "Economic":
                    economicRB.setSelected(true);
                    break;
                default:
                    luxuryRB.setSelected(true);
            }
        }
        submitButton.setOnMouseClicked(e -> {
            vehicle.setModelName(nameField.getText());
            vehicle.setManufacturer(companyField.getText());
            vehicle.setManufactureYear(Integer.parseInt(yearField.getText()));
            vehicle.setPassengersCount(Integer.parseInt(passengersField.getText()));
            vehicle.setBasePrice(Double.parseDouble(priceField.getText()));
            if(vehicleCategory.equalsIgnoreCase("Car")){
                if(sportRB.isSelected())
                    ((Car)vehicle).setMode("Sport");
                else if(economicRB.isSelected())
                    ((Car)vehicle).setMode("Economic");
                else
                    ((Car)vehicle).setMode("Luxury");
            }
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
            list.getItems().add(vehicle.getModelName());
            addVehicleWindow.close();
            clicked[0] = false;
        });
        addVehicleWindow.setOnCloseRequest(e -> clicked[0] = false);
    }
}


//1397/04/11
//6:18 AM