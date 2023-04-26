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

public class PizzaVeggieToppingsPage{

    //calculates additional price and changes the label
    //reads if any meat toppings are selected
    //if a meat topping is selected, sets initial price to .50 and adds (number of toppings -1) multiplied by multiplier (which depends on pizza size)
    //if a meat topping is not selected, sets initial price to 0 and adds (number of toppings -1) multiplied by multiplier (which depends on pizza size)
    private static void changeAddtionalPrice(ArrayList<CheckBox> checkBoxList, Label additionalPrice) {
        int toppingCount = 0;
        double price = 0.00;
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
        
        if (HomePage.getOrder(HomePage.returnOrders().size()-1).getPizza(Order.returnPizzas().size()-1).getMeatToppings().equals("Meat Toppings: None")){
            price = 0.00;
        }
        else{
            price = multiplier;
        }
        for (int i = 0; i<checkBoxList.size(); i++){
            if(checkBoxList.get(i).isSelected()){
                toppingCount++;
            }
        }
        
        DecimalFormat df2 = new DecimalFormat("0.00");
        
        if (toppingCount == 0){
            additionalPrice.setText("+$0.00");
        }
        else{
            price = price + (toppingCount-1)*multiplier;
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
        
        //sets up back button which sends customer to meat toppings page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 13, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaMeatToppingsPage pizzaMeatToppingsPage = new PizzaMeatToppingsPage();
                primaryStage.setScene(pizzaMeatToppingsPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets up label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 13, 3, 1);
        
        //sets up title of page
        final Label pizzaCrustTitle = new Label("Choose your pizza's toppings.");
        pizzaCrustTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pizzaCrustTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pizzaCrustTitle, 11, 1, 8, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up image for the mushroom topping
        Image mushroomPic = new Image(HomePage.class.getResourceAsStream("/Resources/mushroom.png"));
        ImageView veggieTop1 = new ImageView(mushroomPic);
        veggieTop1.setFitHeight(160);
        veggieTop1.setFitWidth(160);
        
        //sets up checkbox for mushroom topping
        CheckBox mushroomTopping = new CheckBox();
        mushroomTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        mushroomTopping.setGraphic(veggieTop1);
        mushroomTopping.setPadding(Insets.EMPTY);
        mushroomTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(mushroomTopping, 8, 3, 4, 4);
        
        //sets up image for the onion topping
        Image onionPic = new Image(HomePage.class.getResourceAsStream("/Resources/onion.png"));
        ImageView veggieTop2 = new ImageView(onionPic);
        veggieTop2.setFitHeight(160);
        veggieTop2.setFitWidth(160);
        
        //sets up checkbox for onion topping
        CheckBox onionTopping = new CheckBox();
        onionTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        onionTopping.setGraphic(veggieTop2);
        onionTopping.setPadding(Insets.EMPTY);
        onionTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(onionTopping, 13, 3, 4, 4);
        
        //sets up image for the toamto topping
        Image tomatoPic = new Image(HomePage.class.getResourceAsStream("/Resources/tomato.png"));
        ImageView veggieTop3 = new ImageView(tomatoPic);
        veggieTop3.setFitHeight(160);
        veggieTop3.setFitWidth(160);
        
        //sets up checkbox for tomato topping
        CheckBox tomatoTopping = new CheckBox();
        tomatoTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        tomatoTopping.setGraphic(veggieTop3);
        tomatoTopping.setPadding(Insets.EMPTY);
        tomatoTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(tomatoTopping, 10, 8, 4, 4);
        
        //sets up image for the pineapple topping
        Image pineapplePic = new Image(HomePage.class.getResourceAsStream("/Resources/pineapple.png"));
        ImageView veggieTop4 = new ImageView(pineapplePic);
        veggieTop4.setFitHeight(160);
        veggieTop4.setFitWidth(160);
        
        //sets up checkbox for pineapple topping
        CheckBox pineappleTopping = new CheckBox();
        pineappleTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        pineappleTopping.setGraphic(veggieTop4);
        pineappleTopping.setPadding(Insets.EMPTY);
        pineappleTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pineappleTopping, 16, 8, 4, 4);
        
        //sets up image for the pepper topping
        Image pepperPic = new Image(HomePage.class.getResourceAsStream("/Resources/pepper.png"));
        ImageView veggieTop5 = new ImageView(pepperPic);
        veggieTop5.setFitHeight(160);
        veggieTop5.setFitWidth(160);
        
        //sets up checkbox for pepper topping
        CheckBox pepperTopping = new CheckBox();
        pepperTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        pepperTopping.setGraphic(veggieTop5);
        pepperTopping.setPadding(Insets.EMPTY);
        pepperTopping.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pepperTopping, 18, 3, 4, 4);

        //adds all toppings into an array list for the changeAdditionalPrice(...) method above
        ArrayList<CheckBox> list = new ArrayList<>();
        list.add(mushroomTopping);
        list.add(onionTopping);
        list.add(tomatoTopping);
        list.add(pineappleTopping);
        list.add(pepperTopping);
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        mushroomTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage.changeAddtionalPrice(list, additionalPrice);
                if (mushroomTopping.isSelected()){
                    mushroomTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    mushroomTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }}); 
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        onionTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage.changeAddtionalPrice(list, additionalPrice);
                if (onionTopping.isSelected()){
                    onionTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    onionTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        tomatoTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage.changeAddtionalPrice(list, additionalPrice);
                if (tomatoTopping.isSelected()){
                    tomatoTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    tomatoTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        pineappleTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage.changeAddtionalPrice(list, additionalPrice);
                if (pineappleTopping.isSelected()){
                    pineappleTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    pineappleTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calls changeAdditionalPrice(...) method when checkbox is changed
        //changes outline color based on selection
        pepperTopping.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                PizzaVeggieToppingsPage.changeAddtionalPrice(list, additionalPrice);
                if (pepperTopping.isSelected()){
                    pepperTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    pepperTopping.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }}); 
        
        //sets up topping info underneath the selections
        final Label toppingInfoLabel = new Label("First topping is free. Each additional topping (including the vegetable toppings) is an extra $0.50(small), $0.75(medium), $1.00(large), or $1.25(extra large).");
        toppingInfoLabel.setStyle("-fx-font-size:18; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        toppingInfoLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(toppingInfoLabel, 2, 12, 26, 1);
        
        //sets up continue order button which adds toppings to the pizza and moves the user to the sides page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 13, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ArrayList<String> toppings = new ArrayList<String>();
                
                if (mushroomTopping.isSelected()){
                    toppings.add(mushroomTopping.getText());
                }
                if (onionTopping.isSelected()){
                    toppings.add(onionTopping.getText());;
                }
                if (tomatoTopping.isSelected()){
                    toppings.add(tomatoTopping.getText());
                }
                if (pineappleTopping.isSelected()){
                    toppings.add(pineappleTopping.getText());
                }
                if (pepperTopping.isSelected()){
                    toppings.add(pepperTopping.getText());
                }
                
                HomePage.getOrder(HomePage.returnOrders().size()-1).editPizzaVeggieToppings(Order.returnPizzas().size()-1, toppings);
                
                SidesPage sidesPage = new SidesPage();
                primaryStage.setScene(sidesPage.getScene(primaryStage));
                primaryStage.show();
        }}); 

        Scene scene = new Scene(gridpane);
        return scene;
    } 
}