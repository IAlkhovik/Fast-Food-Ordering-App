package prototype2;

import javafx.scene.Scene; 
import javafx.scene.control.Button;  
import javafx.stage.Stage; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class ThankYouPage {
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
        
        //sets up home button which sends customer to the home page
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

        //sets up account button which sends customer to account page (if logged in) or login page (if logged out)
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
        
        //sets up title of page
        final Label orderMoreTitle = new Label("Thank you for your purchase.");
        orderMoreTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        orderMoreTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(orderMoreTitle, 20, 8, 6, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up title for order summary
        final Label yourOrderTitle = new Label("Your Order:");
        yourOrderTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        yourOrderTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(yourOrderTitle, 2, 2, 3, 1);
        
        //sets up scroll pane in case long orders spill over application size
        ScrollPane orderSlipsScroll = new ScrollPane();
        
        //sets up summary of order
        final Label orderSlip = new Label(HomePage.getOrder(HomePage.returnOrders().size()-1).getOrder());
        orderSlip.setStyle("-fx-font-size:20");
        orderSlip.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        //adds order summary into scroll pane
        orderSlipsScroll.setContent(orderSlip);
        orderSlipsScroll.setMaxHeight(530);
        
        //adds scroll pane into gridpane
        gridpane.add(orderSlipsScroll, 2, 3, 13, 11);
  
        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}