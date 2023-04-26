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

public class PizzaSizesPage {
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

        //sets up account button which sends customer to account page (if logged in) and login page (if not logged in)
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
        
        //sets up back button which sends customer to home page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 12, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage homePage = new HomePage();
                primaryStage.setScene(homePage.getScene(primaryStage));
                primaryStage.show();
        }}); 
        
        //sets up changeable label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 12, 3, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up group so that one size can be selected at a time
        ToggleGroup sizeGroup = new ToggleGroup();
        
        //creates image for small pizza
        Image sizeSmall = new Image(HomePage.class.getResourceAsStream("/Resources/small.png"));
        ImageView size1 = new ImageView(sizeSmall);
        size1.setFitHeight(200);
        size1.setFitWidth(200);

        //sets up radio button for a small pizza
        RadioButton smallSize = new RadioButton();
        smallSize.setToggleGroup(sizeGroup);
        smallSize.setId("1");
        smallSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        smallSize.setGraphic(size1);
        smallSize.setPadding(Insets.EMPTY);
        smallSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(smallSize, 2, 3, 5, 5);
        
        //sets up small pizza price underneath the pizza selection button
        final Label smallPrice = new Label("Small Pizza: $4.00");
        smallPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        smallPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(smallPrice, 2, 8, 5, 1);
        
        //creates image for medium pizza
        Image sizeMedium = new Image(HomePage.class.getResourceAsStream("/Resources/medium.png"));
        ImageView size2 = new ImageView(sizeMedium);
        size2.setFitHeight(200);
        size2.setFitWidth(200);
        
        //sets up radio button for a medium pizza
        RadioButton mediumSize = new RadioButton();
        mediumSize.setToggleGroup(sizeGroup);
        mediumSize.setId("2");
        mediumSize.setGraphic(size2);
        mediumSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        mediumSize.setPadding(Insets.EMPTY);
        mediumSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(mediumSize, 9, 3, 5, 5);
        
        //sets up medium price underneath the pizza selection button
        final Label mediumPrice = new Label("Medium Pizza: $6.00");
        mediumPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        mediumPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(mediumPrice, 9, 8, 5, 1);
        
        //creates image for large pizza
        Image sizeLarge = new Image(HomePage.class.getResourceAsStream("/Resources/large.png"));
        ImageView size3 = new ImageView(sizeLarge);
        size3.setFitHeight(200);
        size3.setFitWidth(200);
        
        //sets up radio button for a large pizza
        RadioButton largeSize = new RadioButton();
        largeSize.setToggleGroup(sizeGroup);
        largeSize.setId("3");
        largeSize.setGraphic(size3);
        largeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        largeSize.setPadding(Insets.EMPTY);
        largeSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(largeSize, 16, 3, 5, 5);
        
        //sets up large price underneath the pizza selection button
        final Label largePrice = new Label("Large Pizza: $8.00");
        largePrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        largePrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(largePrice, 16, 8, 5, 1);
        
        //creates image for extra large pizza
        Image sizeExtraLarge = new Image(HomePage.class.getResourceAsStream("/Resources/extraLarge.png"));
        ImageView size4 = new ImageView(sizeExtraLarge);
        size4.setFitHeight(200);
        size4.setFitWidth(200);
        
        //sets up radio button for extra large pizza
        RadioButton extraLargeSize = new RadioButton();
        extraLargeSize.setToggleGroup(sizeGroup);
        extraLargeSize.setId("4");
        extraLargeSize.setGraphic(size4);
        extraLargeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        extraLargeSize.setPadding(Insets.EMPTY);
        extraLargeSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(extraLargeSize, 23, 3, 5, 5);
        
        //sets up extra large price underneath the pizza selection button
        final Label extraLargePrice = new Label("Extra Large Pizza: $10.00");
        extraLargePrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        extraLargePrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(extraLargePrice, 23, 8, 5, 1);
        
        //sets up text stating that all pizzas have tomato-based sauce
        final Label pizzaSauceText = new Label("All pizzas contain regular tomato-based sauce.");
        pizzaSauceText.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pizzaSauceText.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pizzaSauceText, 9, 10, 12, 1);
        
        //sets up title of page
        final Label pizzaSizeTitle = new Label("Choose your pizza's size.");
        pizzaSizeTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pizzaSizeTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pizzaSizeTitle, 11, 1, 8, 1);
        
        //sets additional price to $4 when small pizza is selected
        //adds a yellow highlight to the small pizza and removes all others
        smallSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                additionalPrice.setText("+$4.00");
                smallSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                mediumSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                largeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                extraLargeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        }});
        
        //sets additional price to $4 when medium pizza is selected
        //adds a yellow highlight to the medium pizza and removes all others
        mediumSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                additionalPrice.setText("+$6.00");
                smallSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                mediumSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                largeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                extraLargeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        }}); 
        
        //sets additional price to $4 when large pizza is selected
        //adds a yellow highlight to the large pizza and removes all others
        largeSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                additionalPrice.setText("+$8.00");
                smallSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                mediumSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                largeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                extraLargeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        }}); 
        
        //sets additional price to $4 when extra large pizza is selected
        //adds a yellow highlight to the extra large pizza and removes all others
        extraLargeSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                additionalPrice.setText("+$10.00");
                smallSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                mediumSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                largeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                extraLargeSize.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
        }}); 
        
        //sets up continue order button which adds the crust to the pizza order and sends customer to pizza crust page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 12, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                RadioButton selected = (RadioButton) sizeGroup.getSelectedToggle();
                HomePage.getOrder(HomePage.returnOrders().size()-1).editPizzaSize(Order.returnPizzas().size()-1, Integer.parseInt(selected.getId()));
                
                PizzaCrustPage pizzaCrustPage = new PizzaCrustPage();
                primaryStage.setScene(pizzaCrustPage.getScene(primaryStage));
                primaryStage.show();
        }});

        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}