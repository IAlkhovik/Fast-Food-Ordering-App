package prototype2;

import OrderClassPackage.Order;
import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class PizzaCrustPage {
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

        //sets up cart button which sends customer to order more page
        Button cart = new Button("Cart");
        cart.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        cart.setPadding(Insets.EMPTY);
        cart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(cart, 26, 0, 2, 1);
        cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                OrderMorePage cartPage = new OrderMorePage();
                primaryStage.setScene(cartPage.getScene(primaryStage));
                primaryStage.show();
        }});

        //sets up account button which sends customer to account page (if logged in) or login page (if not logged in)
        Button account = new Button("Account");
        account.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        account.setPadding(Insets.EMPTY);
        account.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(account, 28, 0, 2, 1);
        account.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (HomePage.isLoggedIn()){
                    AccountInformationPage accountInfoPage = new AccountInformationPage();
                    primaryStage.setScene(accountInfoPage.getScene(primaryStage));
                    primaryStage.show();
                }
                else{
                    LoginPage loginPage = new LoginPage();
                    primaryStage.setScene(loginPage.getScene(primaryStage));
                    primaryStage.show();
                }
        }});
        
        //sets up back button which should send customer to pizza size page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 12, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaSizesPage pizzaSizesPage = new PizzaSizesPage();
                primaryStage.setScene(pizzaSizesPage.getScene(primaryStage));
                primaryStage.show();
        }}); 
        
        //sets up label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 12, 3, 1);
        
        //sets up title of page
        final Label pizzaCrustTitle = new Label("Choose your pizza's crust.");
        pizzaCrustTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pizzaCrustTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pizzaCrustTitle, 11, 1, 8, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up group so that only one crust can be chosen
        ToggleGroup crustGroup = new ToggleGroup();
        
        //sets up thin crust image
        Image crustThin = new Image(HomePage.class.getResourceAsStream("/Resources/thinCrust.png"));
        ImageView crust1 = new ImageView(crustThin);
        crust1.setFitHeight(240);
        crust1.setFitWidth(240);
        
        //sets up thin crust radio button
        RadioButton thinCrust = new RadioButton();
        thinCrust.setToggleGroup(crustGroup);
        thinCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        thinCrust.setGraphic(crust1);
        thinCrust.setPadding(Insets.EMPTY);
        thinCrust.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(thinCrust, 5, 3, 6, 6);
        
        //sets up thin crust label/ description underneath the crust selection button
        final Label thinCrustLabel = new Label("Thin Crust");
        thinCrustLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        thinCrustLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(thinCrustLabel, 5, 9, 6, 1);
        
        //sets up regular crust image
        Image crustRegular = new Image(HomePage.class.getResourceAsStream("/Resources/regularCrust.png"));
        ImageView crust2 = new ImageView(crustRegular);
        crust2.setFitHeight(240);
        crust2.setFitWidth(240);
        
        //sets up regular crust radio button (selected by default)
        RadioButton regularCrust = new RadioButton();
        regularCrust.setToggleGroup(crustGroup);
        regularCrust.setSelected(true);
        regularCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
        regularCrust.setGraphic(crust2);
        regularCrust.setPadding(Insets.EMPTY);
        regularCrust.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(regularCrust, 12, 3, 6, 6);
        
        //sets up regular crust label/ description underneath the crust selection button
        final Label regularCrustLabel = new Label("Regular Crust");
        regularCrustLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        regularCrustLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(regularCrustLabel, 12, 9, 6, 1);
        
        //sets up pan crust image
        Image crustPan = new Image(HomePage.class.getResourceAsStream("/Resources/panCrust.png"));
        ImageView crust3 = new ImageView(crustPan);
        crust3.setFitHeight(240);
        crust3.setFitWidth(240);
        
        //sets up pan crust radio button
        RadioButton panCrust = new RadioButton();
        panCrust.setToggleGroup(crustGroup);
        panCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        panCrust.setGraphic(crust3);
        panCrust.setPadding(Insets.EMPTY);
        panCrust.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(panCrust, 19, 3, 6, 6);
        
        //sets up pan crust label/ description underneath the pizza selection button
        final Label panCrustLabel = new Label("Pan Crust");
        panCrustLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        panCrustLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(panCrustLabel, 19, 9, 6, 1);
        
        //adds a yellow highlight to the thin crust and removes all others
        thinCrust.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thinCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                regularCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                panCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        }});
        
        //adds a yellow highlight to the thin crust and removes all others
        regularCrust.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thinCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                regularCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                panCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        }});
        
        //adds a yellow highlight to the thin crust and removes all others
        panCrust.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thinCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                regularCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                panCrust.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
        }});
        
        //sets up continue order button which adds the selected crust to the pizza
        //also sends customer to meat toppings page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 12, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                RadioButton selected = (RadioButton) crustGroup.getSelectedToggle();
                HomePage.getOrder(HomePage.returnOrders().size()-1).editPizzaCrust(Order.returnPizzas().size()-1, selected.getText());
                
                PizzaMeatToppingsPage pizzaMeatToppingsPage = new PizzaMeatToppingsPage();
                primaryStage.setScene(pizzaMeatToppingsPage.getScene(primaryStage));
                primaryStage.show();
        }}); 

        Scene scene = new Scene(gridpane);
        return scene;
    } 
}