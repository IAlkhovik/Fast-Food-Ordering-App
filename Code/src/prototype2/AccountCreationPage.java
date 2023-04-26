package prototype2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class AccountCreationPage {

    public Scene getScene(Stage primaryStage) {
        //sets up gridpane layout
        GridPane gridpane = new GridPane();
        gridpane.setStyle("-fx-background-color: white");
        
        //creates a properties object to retreive account data
        Properties prop = new Properties();

        //makes 30 rows and 15 columns in the gridpane
        for (int i = 0; i < 30; i++) {
            ColumnConstraints columnSize = new ColumnConstraints();
            columnSize.setPercentWidth(30);
            columnSize.setMinWidth(50);
            columnSize.setHgrow(Priority.SOMETIMES);
            gridpane.getColumnConstraints().add(columnSize);
        }
        for (int i = 0; i < 15; i++) {
            RowConstraints rowSize = new RowConstraints();
            rowSize.setPercentHeight(15);
            rowSize.setMinHeight(50);
            rowSize.setVgrow(Priority.SOMETIMES);
            gridpane.getRowConstraints().add(rowSize);
        }

        //makes the top row underlined to make a banner for home/account/cart/etc
        int count = 1;
        for (int i = 0; i<30; i++){
            //for (int j= 0; j<15; j++){        //can be used to make a coordinate grid for the gridpane (change new Label() to (i+","+j) and add j to gridpane add)
                final Label visual = new Label();
                visual.setAlignment(Pos.CENTER_RIGHT);
                visual.setMaxSize(Double.MAX_VALUE, 90);
                visual.setTextAlignment(TextAlignment.RIGHT);
                visual.setStyle("-fx-font-size:20; -fx-border-style:solid hidden solid hidden; -fx-border-width:1");
                gridpane.add(visual, i, 0);
                count++;
            //}
        }
        
        //sets up home button which should send customer to home page
        Button home = new Button("Home");
        home.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        home.setPadding(Insets.EMPTY);
        home.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(home, 0, 0, 2, 1);
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage homePage = new HomePage();
                primaryStage.setScene(homePage.getScene(primaryStage));
                primaryStage.show();
        }});

        //sets up logo/store title label on the middle of the banner
        final Label titleLogo = new Label("Mom and Pop's Pizzeria");
        titleLogo.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        titleLogo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(titleLogo, 12, 0, 6, 1);

        //sets up cart button which sends customer to order more page
        Button cart = new Button("Cart");
        cart.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        cart.setPadding(Insets.EMPTY);
        cart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(cart, 26, 0, 2, 1);
        cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (HomePage.returnOrders().size()>0){
                    OrderMorePage cartPage = new OrderMorePage();
                    primaryStage.setScene(cartPage.getScene(primaryStage));
                    primaryStage.show();
                }
        }});

        //sets up account button which sends customer to account page
        Button account = new Button("Account");
        account.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        account.setPadding(Insets.EMPTY);
        account.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(account, 28, 0, 2, 1);
        account.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                primaryStage.setScene(loginPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets up back button which should send customer to home page
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        cancelButton.setPadding(Insets.EMPTY);
        cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(cancelButton, 10, 12, 4, 1);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                primaryStage.setScene(loginPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up email prompt before the textfield
        final Label emailLabel = new Label("Email: ");
        emailLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        emailLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(emailLabel, 2, 3, 2, 1);
        
        //sets up email textfield
        TextField emailField = new TextField();
        emailField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        emailField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        emailField.setPromptText("Email");
        gridpane.add(emailField, 4, 3, 6, 1);
        
        //sets up password prompt before the textfield
        final Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        passwordLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(passwordLabel, 2, 5, 2, 1);
        
        //sets up username textfield
        TextField passwordField = new TextField();
        passwordField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        passwordField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        passwordField.setPromptText("Password");
        gridpane.add(passwordField, 4, 5, 6, 1);
        
        //sets up frist name prompt before the textfield
        final Label firstNameLabel = new Label("First Name: ");
        firstNameLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        firstNameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(firstNameLabel, 2, 7, 2, 1);
        
        //sets up first name textfield
        TextField firstNameField = new TextField();
        firstNameField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        firstNameField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        firstNameField.setPromptText("First Name");
        gridpane.add(firstNameField, 4, 7, 6, 1);
        
        //sets up frist name prompt before the textfield
        final Label lastNameLabel = new Label("Last Name: ");
        lastNameLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        lastNameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(lastNameLabel, 2, 9, 2, 1);
        
        //sets up first name textfield
        TextField lastNameField = new TextField();
        lastNameField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        lastNameField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lastNameField.setPromptText("Last Name");
        gridpane.add(lastNameField, 4, 9, 6, 1);
        
        //sets up phone number prompt before the textfield
        final Label phoneNumberLabel = new Label("Phone #: ");
        phoneNumberLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        phoneNumberLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(phoneNumberLabel, 11, 3, 2, 1);
        
        //sets up phone number textfield
        TextField phoneNumberField = new TextField();
        phoneNumberField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        phoneNumberField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        phoneNumberField.setPromptText("Phone Number");
        gridpane.add(phoneNumberField, 13, 3, 6, 1);
        
        //sets up address prompt before the textfield
        final Label addressLabel = new Label("Address: ");
        addressLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        addressLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(addressLabel, 11, 5, 2, 1);
        
        //sets up address textfield
        TextField addressField = new TextField();
        addressField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        addressField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addressField.setPromptText("Address");
        gridpane.add(addressField, 13, 5, 6, 1);
        
        //sets up city prompt before the textfield
        final Label cityLabel = new Label("City: ");
        cityLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        cityLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(cityLabel, 11, 7, 2, 1);
        
        //sets up city textfield
        TextField cityField = new TextField();
        cityField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        cityField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cityField.setPromptText("City");
        gridpane.add(cityField, 13, 7, 6, 1);
        
        //sets up state prompt before the textfield
        final Label stateLabel = new Label("State: ");
        stateLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        stateLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(stateLabel, 11, 9, 2, 1);
        
        //sets up state textfield
        TextField stateField = new TextField();
        stateField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        stateField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        stateField.setPromptText("State");
        gridpane.add(stateField, 13, 9, 6, 1);
        
        //sets up zip code prompt before the textfield
        final Label zipCodeLabel = new Label("Zip Code: ");
        zipCodeLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        zipCodeLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(zipCodeLabel, 20, 3, 2, 1);
        
        //sets up zip code textfield
        TextField zipCodeField = new TextField();
        zipCodeField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        zipCodeField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        zipCodeField.setPromptText("Zip Code");
        gridpane.add(zipCodeField, 22, 3, 6, 1);
        
        //sets up credit card number prompt before the textfield
        final Label ccNumber = new Label("CC #: ");
        ccNumber.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        ccNumber.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(ccNumber, 20, 5, 2, 1);
        
        //sets up credit card number textfield
        TextField ccNumberField = new TextField();
        ccNumberField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        ccNumberField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ccNumberField.setPromptText("Credit Card Number");
        gridpane.add(ccNumberField, 22, 5, 6, 1);
        
        //sets up expiration date prompt before the textfield
        final Label expirationDateLabel = new Label("Exp. Date: ");
        expirationDateLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        expirationDateLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(expirationDateLabel, 20, 7, 2, 1);
        
        //sets up expiration date textfield
        TextField expirationDateField = new TextField();
        expirationDateField.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        expirationDateField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        expirationDateField.setPromptText("Expiration Date");
        gridpane.add(expirationDateField, 22, 7, 6, 1);
        
        //sets up security code prompt before the textfield
        final Label securityCodeLabel = new Label("CCV: ");
        securityCodeLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        securityCodeLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(securityCodeLabel, 20, 9, 2, 1);
        
        //sets up security code textfield
        TextField securityCodeField = new TextField();
        securityCodeField.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1");
        securityCodeField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        securityCodeField.setPromptText("Security Code");
        gridpane.add(securityCodeField, 22, 9, 6, 1);
        
        //sets up title of page
        final Label accountInfoTitle = new Label("Create Account ");
        accountInfoTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        accountInfoTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(accountInfoTitle, 11, 1, 8, 1);
        
        //sets up save info button which saves all data to a properties file (directory is made if it doesn't exist)
        //also sends user back to login screen
        Button saveInfo = new Button("Create Account");
        saveInfo.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        saveInfo.setPadding(Insets.EMPTY);
        saveInfo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(saveInfo, 16, 12, 4, 1);
        saveInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                primaryStage.setScene(loginPage.getScene(primaryStage));
                
                File accountDir = new File("ProjectDatabase\\Accounts\\");
                if(!accountDir.exists()){
                    accountDir.mkdirs();
                }
                
                try(OutputStream output = new FileOutputStream("ProjectDatabase\\Accounts\\" + emailField.getText()+".txt")){
                    prop.setProperty("email", emailField.getText());
                    prop.setProperty("password", passwordField.getText());
                    prop.setProperty("firstName", firstNameField.getText());
                    prop.setProperty("lastName", lastNameField.getText());
                    prop.setProperty("phoneNumber", phoneNumberField.getText());
                    prop.setProperty("address", addressField.getText());
                    prop.setProperty("city", cityField.getText());
                    prop.setProperty("state", stateField.getText());
                    prop.setProperty("zipCode", zipCodeField.getText());
                    prop.setProperty("ccNumber", ccNumberField.getText());
                    prop.setProperty("expirationDate", expirationDateField.getText());
                    prop.setProperty("securityCode", securityCodeField.getText());
                    
                    prop.store(output, null);
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(AccountCreationPage.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (IOException ex) {
                    Logger.getLogger(AccountCreationPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                primaryStage.show();
        }}); 

        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}