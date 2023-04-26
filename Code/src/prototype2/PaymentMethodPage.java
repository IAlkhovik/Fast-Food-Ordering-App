package prototype2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class PaymentMethodPage {
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
        final Label paymentMethodTitle = new Label("Choose your payment method.");
        paymentMethodTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        paymentMethodTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(paymentMethodTitle, 10, 1, 10, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //sets up toggle group for the delivery method so that only one method can be selected
        ToggleGroup deliveryGroup = new ToggleGroup();
        
        //sets up carry out radio button (set as default for the deliveryGroup ToggleGroup)
        RadioButton carryOut = new RadioButton("Carryout");
        carryOut.setToggleGroup(deliveryGroup);
        carryOut.setId("1");
        carryOut.setSelected(true);
        carryOut.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        carryOut.setPadding(Insets.EMPTY);
        carryOut.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(carryOut, 19, 5, 4, 1);
        
        //sets up delivery radio button
        RadioButton delivery = new RadioButton("Delivery");
        delivery.setToggleGroup(deliveryGroup);
        delivery.setId("2");
        delivery.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        delivery.setPadding(Insets.EMPTY);
        delivery.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(delivery, 23, 5, 4, 1);
        
        //sets pay with cash button which sets the payment method and the delivery method to the order
        //also saves the order to a .txt file (directory is created if it doesn't exist)
        //also sends the customer to the Thank you page
        Button payWithCash = new Button("Pay with Cash");
        payWithCash.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        payWithCash.setPadding(Insets.EMPTY);
        payWithCash.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(payWithCash, 17, 7, 11, 1);
        payWithCash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage.getOrder(HomePage.returnOrders().size()-1).setPaymentMethod("Cash");
                RadioButton selected = (RadioButton) deliveryGroup.getSelectedToggle();
                HomePage.getOrder(HomePage.returnOrders().size()-1).setDeliveryMethod(selected.getText());
                
                File orderFile = new File("ProjectDatabase\\Orders\\");
                if(!orderFile.exists()){
                    orderFile.mkdirs();
                }
                
                try(PrintWriter output = new PrintWriter("ProjectDatabase\\Orders\\order"+ HomePage.returnOrders().size() +".txt")){
                    output.write(HomePage.getOrder(HomePage.returnOrders().size()-1).getOrder());
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(PaymentMethodPage.class.getName()).log(Level.SEVERE, null, ex);
                }

                ThankYouPage thankYouPage = new ThankYouPage();
                primaryStage.setScene(thankYouPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets pay with check button which sets the payment method and the delivery method to the order
        //also saves the order to a .txt file (directory is created if it doesn't exist)
        //also sends the customer to the Thank you page
        Button payWithCheck = new Button("Pay with Check");
        payWithCheck.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        payWithCheck.setPadding(Insets.EMPTY);
        payWithCheck.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(payWithCheck, 17, 9, 11, 1);
        payWithCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage.getOrder(HomePage.returnOrders().size()-1).setPaymentMethod("Check");
                RadioButton selected = (RadioButton) deliveryGroup.getSelectedToggle();
                HomePage.getOrder(HomePage.returnOrders().size()-1).setDeliveryMethod(selected.getText());
                
                File orderFile = new File("ProjectDatabase\\Orders\\");
                if(!orderFile.exists()){
                    orderFile.mkdirs();
                }
                
                try(PrintWriter output = new PrintWriter("ProjectDatabase\\Orders\\order"+ HomePage.returnOrders().size() +".txt")){
                    output.write(HomePage.getOrder(HomePage.returnOrders().size()-1).getOrder());
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(PaymentMethodPage.class.getName()).log(Level.SEVERE, null, ex);
                }

                ThankYouPage thankYouPage = new ThankYouPage();
                primaryStage.setScene(thankYouPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets pay with credit card button which sets the payment method and the delivery method to the order
        //also saves the order to a .txt file (directory is created if it doesn't exist)
        //also sends the customer to the Thank you page
        Button payWithCC = new Button("Pay with Credit Card");
        payWithCC.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        payWithCC.setPadding(Insets.EMPTY);
        payWithCC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(payWithCC, 17, 11, 11, 1);
        payWithCC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                HomePage.getOrder(HomePage.returnOrders().size()-1).setPaymentMethod("Credit Card");
                RadioButton selected = (RadioButton) deliveryGroup.getSelectedToggle();
                HomePage.getOrder(HomePage.returnOrders().size()-1).setDeliveryMethod(selected.getText());
                
                File orderFile = new File("ProjectDatabase\\Orders\\");
                if(!orderFile.exists()){
                    orderFile.mkdirs();
                }
                
                try(PrintWriter output = new PrintWriter("ProjectDatabase\\Orders\\order"+ HomePage.returnOrders().size() +".txt")){
                    output.write(HomePage.getOrder(HomePage.returnOrders().size()-1).getOrder());
                } 
                catch (FileNotFoundException ex) {
                    Logger.getLogger(PaymentMethodPage.class.getName()).log(Level.SEVERE, null, ex);
                }

                ThankYouPage thankYouPage = new ThankYouPage();
                primaryStage.setScene(thankYouPage.getScene(primaryStage));
                primaryStage.show();
        }});
        
        //sets up title label for the order summary
        final Label yourOrderTitle = new Label("Your Order:");
        yourOrderTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        yourOrderTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(yourOrderTitle, 2, 2, 3, 1);
        
        //sets up scroll pane in case large order summaries cause the text to spill off the screen
        ScrollPane orderSlipsScroll = new ScrollPane();
        
        //sets up order summary label
        final Label orderSlip = new Label(HomePage.getOrder(HomePage.returnOrders().size()-1).getOrder());
        orderSlip.setStyle("-fx-font-size:20");
        orderSlip.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        //places order summary label into the scroll pane
        orderSlipsScroll.setContent(orderSlip);
        orderSlipsScroll.setMaxHeight(530);
        
        //adds scroll pane into gridpane
        gridpane.add(orderSlipsScroll, 2, 3, 13, 11);
  
        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}