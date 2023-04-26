package prototype2;

import OrderClassPackage.Order;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class PizzaMeatToppingsPage {

    //calculates additional price and changes the additional price label
    //counts numbers of toppings, subtracts 1, and multiplies by a multiplier determined by getting the pizza's size
    private static void changeAddtionalPrice(ArrayList<CheckBox> checkBoxList, Label additionalPrice) {
        int toppingCount = 0;
        double price = 0.00;
        for (int i = 0; i<checkBoxList.size(); i++){
            if(checkBoxList.get(i).isSelected()){
                toppingCount++;
            }
        }
        double multiplier = 0.50;
        if (HomePage.getOrder(HomePage.returnOrders().size()-1).getPizza(Order.returnPizzas().size()-1).getSize().equals("Size: Medium")){
            multiplier = 0.75;
        }
        else if (HomePage.getOrder(HomePage.returnOrders().size()-1).getPizza(Order.returnPizzas().size()-1).getSize().equals("Size: Large")){
            multiplier = 1.00;
        }
        else if (HomePage.getOrder(HomePage.returnOrders().size()-1).getPizza(Order.returnPizzas().size()-1).getSize().equals("Size: Extra Large")){
            multiplier = 1.25;
        }
        
        DecimalFormat df2 = new DecimalFormat("0.00");
        
        if (toppingCount == 0){
            additionalPrice.setText("+$0.00");
        }
        else{
            price = (toppingCount-1)*multiplier;
            additionalPrice.setText("+$"+df2.format(price));
        }
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

        //sets up account button which sends customer to account page (if logged in) or login page(if not logged in)
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
        
        //sets up back button which should send customer to pizza crust page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 12, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaCrustPage pizzaCrustPage = new PizzaCrustPage();
                primaryStage.setScene(pizzaCrustPage.getScene(primaryStage));
                primaryStage.show();
        }}); 
        
        //sets up label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 12, 3, 1);
        
        //sets up title of page
        final Label pizzaCrustTitle = new Label("Choose your pizza's toppings.");
        pizzaCrustTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pizzaCrustTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pizzaCrustTitle, 11, 1, 8, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up image for the pepperoni topping
        Image pepperoniPic = new Image(HomePage.class.getResourceAsStream("/Resources/pepperoni.png"));
        ImageView meatTop1 = new ImageView(pepperoniPic);
        meatTop1.setFitHeight(240);
        meatTop1.setFitWidth(240);
        
        //sets up checkbox for the pepperoni topping
        CheckBox pepperoniTopping = new CheckBox();
        pepperoniTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        pepperoniTopping.setGraphic(meatTop1);
        pepperoniTopping.setPadding(Insets.EMPTY);
        pepperoniTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pepperoniTopping, 5, 3, 6, 6);
        
        //sets up image for the sausage topping
        Image sausagePic = new Image(HomePage.class.getResourceAsStream("/Resources/sausage.png"));
        ImageView meatTop2 = new ImageView(sausagePic);
        meatTop2.setFitHeight(240);
        meatTop2.setFitWidth(240);
        
        //sets up checkbox for the sausage topping
        CheckBox sausageTopping = new CheckBox();
        sausageTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        sausageTopping.setGraphic(meatTop2);
        sausageTopping.setPadding(Insets.EMPTY);
        sausageTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(sausageTopping, 12, 3, 6, 6);
        
        //sets up image for the ham topping
        Image hamPic = new Image(HomePage.class.getResourceAsStream("/Resources/ham.png"));
        ImageView meatTop3 = new ImageView(hamPic);
        meatTop3.setFitHeight(240);
        meatTop3.setFitWidth(240);
        
        //sets up checkbox for the ham topping
        CheckBox hamTopping = new CheckBox();
        hamTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        hamTopping.setGraphic(meatTop3);
        hamTopping.setPadding(Insets.EMPTY);
        hamTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(hamTopping, 19, 3, 6, 6);
        
        //sets up an array list of checkboxes for use in the changeAdditionalPrice(...) method up above
        ArrayList<CheckBox> checkBoxList = new ArrayList<>();
        checkBoxList.add(pepperoniTopping);
        checkBoxList.add(sausageTopping);
        checkBoxList.add(hamTopping);
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        pepperoniTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaMeatToppingsPage.changeAddtionalPrice(checkBoxList, additionalPrice);
                if (pepperoniTopping.isSelected()){
                    pepperoniTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    pepperoniTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        sausageTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaMeatToppingsPage.changeAddtionalPrice(checkBoxList, additionalPrice);
                if (sausageTopping.isSelected()){
                    sausageTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    sausageTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        hamTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaMeatToppingsPage.changeAddtionalPrice(checkBoxList, additionalPrice);
                if (hamTopping.isSelected()){
                    hamTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    hamTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //sets up topping information underneath the toppings 
        final Label toppingInfoLabel = new Label("First topping is free. Each additional topping (including the vegetable toppings) is an extra $0.50(small), $0.75(medium), $1.00(large), or $1.25(extra large).");
        toppingInfoLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        toppingInfoLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(toppingInfoLabel, 2, 10, 26, 1);
        
        //sets up continue order button which adds the toppings to the current pizza and moves to the veggie toppings page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 12, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ArrayList<String> toppings = new ArrayList<String>();
                
                if (pepperoniTopping.isSelected()){
                    toppings.add(pepperoniTopping.getText());
                }
                if (sausageTopping.isSelected()){
                    toppings.add(sausageTopping.getText());
                }
                if (hamTopping.isSelected()){
                    toppings.add(hamTopping.getText());
                }
                
                HomePage.getOrder(HomePage.returnOrders().size()-1).editPizzaMeatToppings(Order.returnPizzas().size()-1, toppings);
                
                PizzaVeggieToppingsPage pizzaVeggieToppingsPage = new PizzaVeggieToppingsPage();
                primaryStage.setScene(pizzaVeggieToppingsPage.getScene(primaryStage));
                primaryStage.show();
        }});

        Scene scene = new Scene(gridpane);
        return scene;
    };
}