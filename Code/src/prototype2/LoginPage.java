package prototype2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class LoginPage {
    
    public Scene getScene(Stage primaryStage) {   
        //sets up gridpane layout
        GridPane gridpane = new GridPane();
        gridpane.setStyle("-fx-background-color: white");

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
        
        //sets up home button which sends customer to home page
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

        //sets up cart button which sends customer to order more page (if an order exists)
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

        //sets up account button which sends customer to account page (which the customer is already on when not logged in)
        Button account = new Button("Account");
        account.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        account.setPadding(Insets.EMPTY);
        account.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(account, 28, 0, 2, 1);
        
        //sets up back button which should send customer to home page
        Button backHome = new Button("Back Home");
        backHome.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backHome.setPadding(Insets.EMPTY);
        backHome.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backHome, 10, 12, 4, 1);
        backHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage homePage = new HomePage();
                primaryStage.setScene(homePage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up username prompt before the textfield
        final Label usernameLabel = new Label("Email: ");
        usernameLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        usernameLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(usernameLabel, 9, 4, 2, 1);
        
        //sets up username textfield
        TextField emailField = new TextField();
        emailField.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        emailField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        emailField.setPromptText("Email");
        gridpane.add(emailField, 11, 4, 10, 1);
        
        //sets up password prompt before the textfield
        final Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        passwordLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(passwordLabel, 9, 6, 2, 1);
        
        //sets up username textfield
        TextField passwordField = new TextField();
        passwordField.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        passwordField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        passwordField.setPromptText("Password");
        gridpane.add(passwordField, 11, 6, 10, 1);
        
        //sets up a button which a customer can use to create an account
        Button createAccount = new Button("Create an Account");
        createAccount.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        createAccount.setPadding(Insets.EMPTY);
        createAccount.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(createAccount, 17, 7, 4, 1);
        createAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AccountCreationPage accountCreatePage = new AccountCreationPage();
                primaryStage.setScene(accountCreatePage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets up incorrect username/password warning (which shows up if the username or password are incorrect
        final Label incorrectCredentialsLabel = new Label("Incorrect email or password.");
        incorrectCredentialsLabel.setStyle("-fx-font-size:20; -fx-text-fill:white; -fx-alignment:center");
        incorrectCredentialsLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(incorrectCredentialsLabel, 11, 9, 8, 1);
        
        //sets up title of page
        final Label loginTitle = new Label("Login with Your Username and Password.");
        loginTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        loginTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(loginTitle, 11, 1, 8, 1);
        
        //sets up login button which should send customer to account page (on correct email and password)
        //or draws the incorrectCredentialsLabel when email or password are incorrect
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        loginButton.setPadding(Insets.EMPTY);
        loginButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(loginButton, 16, 12, 4, 1);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try(FileInputStream input = new FileInputStream("ProjectDatabase\\Accounts\\" + emailField.getText()+".txt")){
                    Properties prop = new Properties();
                    prop.load(input);
                    
                    if (prop.getProperty("password").equals(passwordField.getText())){
                        HomePage.setLoggedIn(true);
                        HomePage.setUsername(emailField.getText());
                        HomePage.setCustomerName(prop.getProperty("firstName") + " " + prop.getProperty("lastName"));
                        
                        AccountInformationPage accountInfoPage = new AccountInformationPage();
                        primaryStage.setScene(accountInfoPage.getScene(primaryStage));
                        primaryStage.show();
                    }
                    else{
                        incorrectCredentialsLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-text-fill:red; -fx-alignment:center");
                    }
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (IOException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    incorrectCredentialsLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-text-fill:red; -fx-alignment:center");
                }
        }});

        Scene scene = new Scene(gridpane); 
        return scene;
    }
}