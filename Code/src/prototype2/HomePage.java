package prototype2;

import OrderClassPackage.Order;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class HomePage extends Application {
    private static boolean isLoggedIn = false;      //measures if current user is logged in or not (default: not logged in)
    private static String username = "Guest";       //gives the username of the current person logged in (for account retrieval purposes, default: guest)
    private static ArrayList<Order> orders;         //sets up array list of orders that the current run of the program has taken
    private static String customerName = "Guest";   //sets up name of the current person logged in (default: guest)
    
    public HomePage () {
    };
    
    //adds an order to the orders array list (happens on every new user)
    public static void addOrder(Order anOrder){
        orders.add(anOrder);
    };
    
    //returns the array list of orders
    public static ArrayList<Order> returnOrders(){
        return orders;
    };
    
    //gets the order at a certain position
    public static Order getOrder(int position){
        return orders.get(position);
    };
    
    //returns the isLoggedIn boolean
    public static boolean isLoggedIn(){
        return isLoggedIn;
    };
    
    //changes the isLoggedIn boolean
    public static void setLoggedIn(boolean isLogged){
        isLoggedIn = isLogged;
    };
    
    //changes username
    public static void setUsername(String aUsername){
        username = aUsername;
    };
    
    //returns the username
    public static String getUsername(){
        return username;
    };
    
    //changes the name
    public static void setCustomerName(String aName){
        customerName = aName;
    };
    
    //returns the name
    public static String getCustomerName(){
        return customerName;
    };
    
    //initializes the array list prior to launching the primary stage of the JavaFX
    private static void initVariables() {
         orders = new ArrayList<>(); 
    };
    
    public Scene getScene(Stage primaryStage){
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

        //sets up advertisement images and adds them to the grid pane
        Image homePage1 = new Image(HomePage.class.getResourceAsStream("/Resources/homePage1.png"));
        ImageView home1 = new ImageView(homePage1);
        home1.setFitHeight(200);
        home1.setFitWidth(505);
        
        Image homePage2 = new Image(HomePage.class.getResourceAsStream("/Resources/homePage2.jpg"));
        ImageView home2 = new ImageView(homePage2);
        home2.setFitHeight(200);
        home2.setFitWidth(505);
        
        Image homePage3 = new Image(HomePage.class.getResourceAsStream("/Resources/homePage3.jpg"));
        ImageView home3 = new ImageView(homePage3);
        home3.setFitHeight(200);
        home3.setFitWidth(505);
        
        Image homepage4 = new Image(HomePage.class.getResourceAsStream("/Resources/homePage4.png"));
        ImageView home4 = new ImageView(homepage4);
        home4.setFitHeight(200);
        home4.setFitWidth(505);

        gridpane.add(home1, 4, 2, 10, 4);
        gridpane.add(home2, 16, 2, 10, 4);
        gridpane.add(home3, 4, 7, 10, 4);
        gridpane.add(home4, 16, 7, 10, 4);

        //sets up start order button which sends customer to pizza size page and adds an order to the array list
        //also adds a pizza to the order 
        Button startOrder = new Button("Start Order");
        startOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        startOrder.setPadding(Insets.EMPTY);
        startOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(startOrder, 10, 12, 10, 1);
        startOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addOrder(new Order(customerName));
                HomePage.getOrder(orders.size()-1).addPizza();

                PizzaSizesPage pizzaSizesPage = new PizzaSizesPage();
                primaryStage.setScene(pizzaSizesPage.getScene(primaryStage));
                primaryStage.show();
        }});

        //sets up home button which should send customer to home page (where the customer already is)
        Button home = new Button("Home");
        home.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        home.setPadding(Insets.EMPTY);
        home.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(home, 0, 0, 2, 1);

        //sets up logo/store title label on the middle of the banner
        final Label titleLogo = new Label("Mom and Pop's Pizzeria");
        titleLogo.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        titleLogo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(titleLogo, 12, 0, 6, 1);

        //sets up cart button which sends customer to order more page (if there is an order that exists)
        Button cart = new Button("Cart");
        cart.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        cart.setPadding(Insets.EMPTY);
        cart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(cart, 26, 0, 2, 1);
        cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (orders.size()>0){
                    OrderMorePage cartPage = new OrderMorePage();
                    primaryStage.setScene(cartPage.getScene(primaryStage));
                    primaryStage.show();
                }
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
        
        Scene homePage = new Scene(gridpane);
        return homePage;
    };
    
    @Override
    public void start(Stage primaryStage) {
        //makes sure the window cannot be resized to stop problems with changing sizes    
        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(1606);
        primaryStage.setMaxHeight(800);
 
        primaryStage.setTitle("Mom and Pop's Pizzeria");  
        primaryStage.setScene(this.getScene(primaryStage)); 
        primaryStage.show();
    };

    public static void main(String[] args) {
        initVariables();
        launch(args);
    };
}