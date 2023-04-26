package prototype2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class SidesPage {
    //calculates additional price and changes the label
    //calculated by seeing which checkbox is selected and multiplying the quantitiy by the price based on that
    private static void changeAddtionalPrice(ArrayList<CheckBox> checkBoxList, ArrayList<ComboBox> comboBoxList, Label additionalPrice) {
        double price = 0.00;
        for (int i = 0; i<checkBoxList.size(); i++){
            if(checkBoxList.get(i).isSelected()){
                if (Integer.parseInt(checkBoxList.get(i).getId())==1 || Integer.parseInt(checkBoxList.get(i).getId())==2){
                    price = price + 4* (int) comboBoxList.get(i).getValue();
                }
                else if (Integer.parseInt(checkBoxList.get(i).getId())==3){
                    price = price + 2* (int) comboBoxList.get(i).getValue();
                }
            }
        }
        
        DecimalFormat df2 = new DecimalFormat("0.00");
        additionalPrice.setText("+$" + df2.format(price));
    };
    
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
        
        //sets up back button which should send customer to the veggie toppings page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 12, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage pizzaVeggieToppingsPage = new PizzaVeggieToppingsPage();
                primaryStage.setScene(pizzaVeggieToppingsPage.getScene(primaryStage));
                primaryStage.show();
        }}); 
        
        //sets up label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 12, 3, 1);
        
        //sets up title of page
        final Label sidesTitle = new Label("Choose your sides.");
        sidesTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        sidesTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(sidesTitle, 13, 1, 4, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up image for the chocolate cookie side
        Image ccCookiePic = new Image(HomePage.class.getResourceAsStream("/Resources/ccCookie.png"));
        ImageView side1 = new ImageView(ccCookiePic);
        side1.setFitHeight(240);
        side1.setFitWidth(240);
        
        //sets up checkbox for the chocolate chip cookie
        CheckBox chocolateCookie = new CheckBox();
        chocolateCookie.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        chocolateCookie.setGraphic(side1);
        chocolateCookie.setId("1");
        chocolateCookie.setPadding(Insets.EMPTY);
        chocolateCookie.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(chocolateCookie, 5, 3, 6, 6);
        
        //sets up list for side quantities
        ObservableList<Integer> sidesQty = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        
        //sets up drop down menu for chocolate chip cookie quantities
        final ComboBox chocolateCookieQty = new ComboBox(sidesQty);
        chocolateCookieQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        chocolateCookieQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chocolateCookieQty.setValue(1);
        gridpane.add(chocolateCookieQty, 9, 8, 2, 1);
        
        //sets up chocolate chip cookie description underneath the cookie button
        final Label chocolateCookieLabel = new Label("Chocolate Chip Cookie: $4.00");
        chocolateCookieLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        chocolateCookieLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(chocolateCookieLabel, 5, 9, 6, 1);
        
        //sets up image for the breadsticks side
        Image breadstickPic = new Image(HomePage.class.getResourceAsStream("/Resources/breadsticks.png"));
        ImageView side2 = new ImageView(breadstickPic);
        side2.setFitHeight(240);
        side2.setFitWidth(240);
        
        //sets up checkbox for breadsticks
        CheckBox breadsticks = new CheckBox();
        breadsticks.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        breadsticks.setGraphic(side2);
        breadsticks.setId("2");
        breadsticks.setPadding(Insets.EMPTY);
        breadsticks.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(breadsticks, 12, 3, 6, 6);
        
        //sets up drop down menu for the breadstick quantity
        final ComboBox breadsticksQty = new ComboBox(sidesQty);
        breadsticksQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        breadsticksQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        breadsticksQty.setValue(1);
        gridpane.add(breadsticksQty, 16, 8, 2, 1);
        
        //sets up breadsticks description underneath the breadsticks button
        final Label breadsticksLabel = new Label("Breadsticks: $4.00");
        breadsticksLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        breadsticksLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(breadsticksLabel, 12, 9, 6, 1);
        
        //sets up image for the breadstick bites side
        Image breadstickBitesPic = new Image(HomePage.class.getResourceAsStream("/Resources/breadstickBites.png"));
        ImageView side3 = new ImageView(breadstickBitesPic);
        side3.setFitHeight(240);
        side3.setFitWidth(240);
        
        //sets up checkbox for breadstick bites
        CheckBox breadstickBites = new CheckBox();
        breadstickBites.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        breadstickBites.setGraphic(side3);
        breadstickBites.setId("3");
        breadstickBites.setPadding(Insets.EMPTY);
        breadstickBites.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(breadstickBites, 19, 3, 6, 6);
        
        //sets up combobox for breadstick bite quantities
        final ComboBox breadstickBitesQty = new ComboBox(sidesQty);
        breadstickBitesQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        breadstickBitesQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        breadstickBitesQty.setValue(1);
        gridpane.add(breadstickBitesQty, 23, 8, 2, 1);
        
        //sets up breadstick bites description underneath the breadstick bites button
        final Label breadstickBitesLabel = new Label("Breadstick Bites: $2.00");
        breadstickBitesLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        breadstickBitesLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(breadstickBitesLabel, 19, 9, 6, 1);
        
        //sets up list for checkboxes for use in the changeAdditionalPrice(...) method
        ArrayList<CheckBox> checkBoxList = new ArrayList<>();
        checkBoxList.add(chocolateCookie);
        checkBoxList.add(breadsticks);
        checkBoxList.add(breadstickBites);
        
        //sets up list for comboboxes for use in the changeAdditionalPrice(...) method
        ArrayList<ComboBox> comboBoxList = new ArrayList<>();
        comboBoxList.add(chocolateCookieQty);
        comboBoxList.add(breadsticksQty);
        comboBoxList.add(breadstickBitesQty);
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        chocolateCookie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SidesPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (chocolateCookie.isSelected()){
                    chocolateCookie.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    chocolateCookie.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        breadsticks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SidesPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (breadsticks.isSelected()){
                    breadsticks.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    breadsticks.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }}); 
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        breadstickBites.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SidesPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (breadstickBites.isSelected()){
                    breadstickBites.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    breadstickBites.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }}); 
        
        //sets up continue order button which adds sides to the order and sends the customer to the drink page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 12, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {               
                if (chocolateCookie.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addSide(Integer.parseInt(chocolateCookie.getId()), (int) chocolateCookieQty.getValue());
                }
                if (breadsticks.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addSide(Integer.parseInt(breadsticks.getId()), (int) breadsticksQty.getValue());
                }
                if (breadstickBites.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addSide(Integer.parseInt(breadstickBites.getId()), (int) breadstickBitesQty.getValue());
                }
                
                DrinksPage drinksPage = new DrinksPage();
                primaryStage.setScene(drinksPage.getScene(primaryStage));
                primaryStage.show();
        }});

        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}