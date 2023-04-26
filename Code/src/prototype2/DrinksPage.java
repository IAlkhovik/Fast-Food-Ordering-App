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

public class DrinksPage {
    //calculates additional price (by multiplying selected quanitities by 1.00) and changes the label that shows additional price
    private static void changeAddtionalPrice(ArrayList<CheckBox> checkBoxList, ArrayList<ComboBox> comboBoxList, Label additionalPrice) {
        double price = 0.00;
        for (int i = 0; i<checkBoxList.size(); i++){
            if(checkBoxList.get(i).isSelected()){
                price = price + 1* (int) comboBoxList.get(i).getValue();
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
                OrderMorePage cartPage = new OrderMorePage();
                primaryStage.setScene(cartPage.getScene(primaryStage));
                primaryStage.show();
        }});

        //sets up account button which sends customer to account page (if logged in)
        //sends customer to login page if not logged in
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
        
        //sets up back button which should send customer to the sides page
        Button backOrder = new Button("Go Back");
        backOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1");
        backOrder.setPadding(Insets.EMPTY);
        backOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(backOrder, 10, 13, 4, 1);
        backOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SidesPage sidesPage = new SidesPage();
                primaryStage.setScene(sidesPage.getScene(primaryStage));
                primaryStage.show();
        }}); 
        
        //sets up label which shows additional price based on what is selected
        final Label additionalPrice = new Label("+$0.00");
        additionalPrice.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        additionalPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(additionalPrice, 21, 13, 3, 1);
        
        //sets up title of page
        final Label drinksTitle = new Label("Choose your drinks at $1.00 each regardless of size.");
        drinksTitle.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        drinksTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(drinksTitle, 10, 1, 10, 1);
        
        //END OF LAYOUT
        //END OF LAYOUT
        //END OF LAYOUT
        
        //lists for combo box selections (quantity and sizes)
        ObservableList<Integer> drinksQty = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        ObservableList<String> drinksSize = FXCollections.observableArrayList("S", "M", "L");
        
        //sets up image for the pepsi drink
        Image pepsiPic = new Image(HomePage.class.getResourceAsStream("/Resources/pepsi.png"));
        ImageView drink1 = new ImageView(pepsiPic);
        drink1.setFitHeight(160);
        drink1.setFitWidth(160);
        
        //sets up pepsi check box
        CheckBox pepsi = new CheckBox();
        pepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        pepsi.setGraphic(drink1);
        pepsi.setId("1");
        pepsi.setPadding(Insets.EMPTY);
        pepsi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pepsi, 5, 3, 4, 4);
        
        //sets up pepsi label underneath the pepsi button
        final Label pepsiLabel = new Label("Pepsi");
        pepsiLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pepsiLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(pepsiLabel, 5, 7, 4, 1);
        
        //sets up drop down menu for pepsi size (default: S)
        final ComboBox pepsiSize = new ComboBox(drinksSize);
        pepsiSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pepsiSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pepsiSize.setValue("S");
        gridpane.add(pepsiSize, 5, 6, 2, 1);
        
        //sets up drop down menu for pepsi quantity (default: 1)
        final ComboBox pepsiQty = new ComboBox(drinksQty);
        pepsiQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        pepsiQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pepsiQty.setValue(1);
        gridpane.add(pepsiQty, 7, 6, 2, 1);
        
        //sets up image for the orange drink
        Image orangePic = new Image(HomePage.class.getResourceAsStream("/Resources/orange.png"));
        ImageView drink2 = new ImageView(orangePic);
        drink2.setFitHeight(160);
        drink2.setFitWidth(160);
        
        //sets up check box for the orange drink
        CheckBox orange = new CheckBox();
        orange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        orange.setGraphic(drink2);
        orange.setId("2");
        orange.setPadding(Insets.EMPTY);
        orange.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(orange, 10, 3, 4, 4);
        
        //sets up orange description underneath the orange button
        final Label orangeLabel = new Label("Orange");
        orangeLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        orangeLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(orangeLabel, 10, 7, 4, 1);
        
        //sets up drop down menu for orange size (default: S)
        final ComboBox orangeSize = new ComboBox(drinksSize);
        orangeSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        orangeSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        orangeSize.setValue("S");
        gridpane.add(orangeSize, 10, 6, 2, 1);
        
        //sets up drop down menu for orange quantity (default: 1)
        final ComboBox orangeQty = new ComboBox(drinksQty);
        orangeQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        orangeQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        orangeQty.setValue(1);
        gridpane.add(orangeQty, 12, 6, 2, 1);
        
        //sets up image for the root beer drink
        Image rootBeerPic = new Image(HomePage.class.getResourceAsStream("/Resources/rootBeer.png"));
        ImageView drink3 = new ImageView(rootBeerPic);
        drink3.setFitHeight(160);
        drink3.setFitWidth(160);
        
        //sets up check box for root beer drink     
        CheckBox rootbeer = new CheckBox();
        rootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        rootbeer.setGraphic(drink3);
        rootbeer.setId("3");
        rootbeer.setPadding(Insets.EMPTY);
        rootbeer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(rootbeer, 16, 3, 4, 4);
        
        //sets up rootbeer description underneath the rootbeer button
        final Label rootbeerLabel = new Label("Root Beer");
        rootbeerLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        rootbeerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(rootbeerLabel, 16, 7, 4, 1);
        
        //sets up drop down menu for root beer size (default: S)
        final ComboBox rootbeerSize = new ComboBox(drinksSize);
        rootbeerSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        rootbeerSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        rootbeerSize.setValue("S");
        gridpane.add(rootbeerSize, 16, 6, 2, 1);
        
        //sets up drop down menu for root beer quantity (default: 1)
        final ComboBox rootbeerQty = new ComboBox(drinksQty);
        rootbeerQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        rootbeerQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        rootbeerQty.setValue(1);
        gridpane.add(rootbeerQty, 18, 6, 2, 1);
        
        //sets up image for the sierra mist drink
        Image sierraMistPic = new Image(HomePage.class.getResourceAsStream("/Resources/sierraMist.png"));
        ImageView drink4 = new ImageView(sierraMistPic);
        drink4.setFitHeight(160);
        drink4.setFitWidth(160);
        
        //sets up check box for sierra mist drink
        CheckBox sierramist = new CheckBox();
        sierramist.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        sierramist.setGraphic(drink4);
        sierramist.setId("4");
        sierramist.setPadding(Insets.EMPTY);
        sierramist.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(sierramist, 21, 3, 4, 4);

        //sets up sierramist description underneath the sierramist button
        final Label sierramistLabel = new Label("Sierra Mist");
        sierramistLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        sierramistLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(sierramistLabel, 21, 7, 4, 1);
        
        //sets up drop down menu for sierra mist size (default: S)
        final ComboBox sierraMistSize = new ComboBox(drinksSize);
        sierraMistSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        sierraMistSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sierraMistSize.setValue("S");
        gridpane.add(sierraMistSize, 21, 6, 2, 1);
        
        //sets up drop down menu for sierra mist quantity (default: 1)
        final ComboBox sierraMistQty = new ComboBox(drinksQty);
        sierraMistQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        sierraMistQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sierraMistQty.setValue(1);
        gridpane.add(sierraMistQty, 23, 6, 2, 1);
        
        //sets up image for the diet pepsi drink
        Image dietPepsiPic = new Image(HomePage.class.getResourceAsStream("/Resources/dietPepsi.png"));
        ImageView drink5 = new ImageView(dietPepsiPic);
        drink5.setFitHeight(160);
        drink5.setFitWidth(160);
        
        //sets up check box for diet pepsi drink
        CheckBox dietpepsi = new CheckBox();
        dietpepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        dietpepsi.setGraphic(drink5);
        dietpepsi.setId("5");
        dietpepsi.setPadding(Insets.EMPTY);
        dietpepsi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietpepsi, 5, 8, 4, 4);
        
        //sets up dietpepsi description underneath the dietpepsi button
        final Label dietpepsiLabel = new Label("Diet Pepsi");
        dietpepsiLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietpepsiLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietpepsiLabel, 5, 12, 4, 1);
        
        //sets up drop down menu for diet pepsi size (default: S)
        final ComboBox dietPepsiSize = new ComboBox(drinksSize);
        dietPepsiSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietPepsiSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietPepsiSize.setValue("S");
        gridpane.add(dietPepsiSize, 5, 11, 2, 1);
        
        //sets up drop down menu for diet pepsi quantity (default: 1)
        final ComboBox dietPepsiQty = new ComboBox(drinksQty);
        dietPepsiQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietPepsiQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietPepsiQty.setValue(1);
        gridpane.add(dietPepsiQty, 7, 11, 2, 1);
        
        //sets up image for the diet orange drink
        Image dietOrangePic = new Image(HomePage.class.getResourceAsStream("/Resources/dietOrange.png"));
        ImageView drink6 = new ImageView(dietOrangePic);
        drink6.setFitHeight(160);
        drink6.setFitWidth(160);
        
        //sets up check box for diet orange drink
        CheckBox dietorange = new CheckBox();
        dietorange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        dietorange.setGraphic(drink6);
        dietorange.setId("6");
        dietorange.setPadding(Insets.EMPTY);
        dietorange.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietorange, 10, 8, 4, 4);
        
        //sets up dietorange description underneath the dietorange button
        final Label dietorangeLabel = new Label("Diet Orange");
        dietorangeLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietorangeLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietorangeLabel, 10, 12, 4, 1);
        
        //sets up drop down menu for diet orange size (default: S)
        final ComboBox dietOrangeSize = new ComboBox(drinksSize);
        dietOrangeSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietOrangeSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietOrangeSize.setValue("S");
        gridpane.add(dietOrangeSize, 10, 11, 2, 1);
        
        //sets up drop down menu for diet orange quantity (default: 1)
        final ComboBox dietOrangeQty = new ComboBox(drinksQty);
        dietOrangeQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietOrangeQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietOrangeQty.setValue(1);
        gridpane.add(dietOrangeQty, 12, 11, 2, 1);
        
        //sets up image for the diet root beer drink
        Image dietRootBeerPic = new Image(HomePage.class.getResourceAsStream("/Resources/dietRootBeer.png"));
        ImageView drink7 = new ImageView(dietRootBeerPic);
        drink7.setFitHeight(160);
        drink7.setFitWidth(160);
        
        //sets up check box for diet root beer drink
        CheckBox dietrootbeer = new CheckBox();
        dietrootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        dietrootbeer.setGraphic(drink7);
        dietrootbeer.setId("7");
        dietrootbeer.setPadding(Insets.EMPTY);
        dietrootbeer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietrootbeer, 16, 8, 4, 4);
        
        //sets up dietrootbeer description underneath the dietrootbeer button
        final Label dietrootbeerLabel = new Label("Diet Root Beer");
        dietrootbeerLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietrootbeerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(dietrootbeerLabel, 16, 12, 4, 1);
        
        //sets up drop down menu for diet root beer size (default: S)
        final ComboBox dietRootBeerSize = new ComboBox(drinksSize);
        dietRootBeerSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietRootBeerSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietRootBeerSize.setValue("S");
        gridpane.add(dietRootBeerSize, 16, 11, 2, 1);
        
        //sets up drop down menu for diet root beer quantity (default: 1)
        final ComboBox dietRootBeerQty = new ComboBox(drinksQty);
        dietRootBeerQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        dietRootBeerQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dietRootBeerQty.setValue(1);
        gridpane.add(dietRootBeerQty, 18, 11, 2, 1);
        
        //sets up image for the lemonade drink
        Image lemonadePic = new Image(HomePage.class.getResourceAsStream("/Resources/lemonade.png"));
        ImageView drink8 = new ImageView(lemonadePic);
        drink8.setFitHeight(160);
        drink8.setFitWidth(160);
        
        //sets up check box for lemonade drink
        CheckBox lemonade = new CheckBox();
        lemonade.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
        lemonade.setGraphic(drink8);
        lemonade.setId("8");
        lemonade.setPadding(Insets.EMPTY);
        lemonade.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(lemonade, 21, 8, 4, 4);
        
        //sets up lemonade description underneath the lemonade button
        final Label lemonadeLabel = new Label("Lemonade");
        lemonadeLabel.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        lemonadeLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(lemonadeLabel, 21, 12, 4, 1);
        
        //sets up drop down menu for lemonade size (default: S)
        final ComboBox lemonadeSize = new ComboBox(drinksSize);
        lemonadeSize.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        lemonadeSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lemonadeSize.setValue("S");
        gridpane.add(lemonadeSize, 21, 11, 2, 1);
        
        //sets up drop down menu for lemonade quantity (default: 1)
        final ComboBox lemonadeQty = new ComboBox(drinksQty);
        lemonadeQty.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        lemonadeQty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        lemonadeQty.setValue(1);
        gridpane.add(lemonadeQty, 23, 11, 2, 1);
        
        //creates an array list of drink selections (for the changeAdditionalPrice(...) method above)
        ArrayList<CheckBox> checkBoxList = new ArrayList<>();
        checkBoxList.add(pepsi);
        checkBoxList.add(orange);
        checkBoxList.add(rootbeer);
        checkBoxList.add(sierramist);
        checkBoxList.add(dietpepsi);
        checkBoxList.add(dietorange);
        checkBoxList.add(dietrootbeer);
        checkBoxList.add(lemonade);
        
        //creates an array list of drink quantities (for the changeAdditionalPrice(...) method above)
        ArrayList<ComboBox> comboBoxList = new ArrayList<>();
        comboBoxList.add(pepsiQty);
        comboBoxList.add(orangeQty);
        comboBoxList.add(rootbeerQty);
        comboBoxList.add(sierraMistQty);
        comboBoxList.add(dietPepsiQty);
        comboBoxList.add(dietOrangeQty);
        comboBoxList.add(dietRootBeerQty);
        comboBoxList.add(lemonadeQty);
        
        //calculates all additional price of all drinks when pepsi check box is selected
        //changes outline color based on selection
        pepsi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (pepsi.isSelected()){
                    pepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    pepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when orange check box is selected
        //changes outline color based on selection
        orange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (orange.isSelected()){
                    orange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    orange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when root beer check box is selected
        //changes outline color based on selection
        rootbeer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (rootbeer.isSelected()){
                    rootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    rootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when sierra mist check box is selected
        //changes outline color based on selection
        sierramist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (sierramist.isSelected()){
                    sierramist.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    sierramist.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when diet pepsi check box is selected
        //changes color based on selection
        dietpepsi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (dietpepsi.isSelected()){
                    dietpepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    dietpepsi.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when diet orange check box is selected
        //changes color based on selection
        dietorange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (dietorange.isSelected()){
                    dietorange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    dietorange.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when diet root beer check box is selected
        //changes color based on selection
        dietrootbeer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (dietrootbeer.isSelected()){
                    dietrootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    dietrootbeer.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //calculates all additional price of all drinks when lemonade check box is selected
        //changes color based on selection
        lemonade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DrinksPage.changeAddtionalPrice(checkBoxList, comboBoxList, additionalPrice);
                if (lemonade.isSelected()){
                    lemonade.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:yellow");
                }
                else{
                    lemonade.setStyle("-fx-border-style: solid; -fx-border-width:5; -fx-border-color:white");
                }
        }});
        
        //sets up continue order button which adds the selected drinks to the order and goes to the order more page
        Button continueOrder = new Button("Continue Order");
        continueOrder.setStyle("-fx-font-size:20; -fx-border-style:solid; -fx-border-width:1; -fx-alignment:center");
        continueOrder.setPadding(Insets.EMPTY);
        continueOrder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridpane.add(continueOrder, 16, 13, 4, 1);
        continueOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (pepsi.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(pepsi.getId()), (String) pepsiSize.getValue(), (int) pepsiQty.getValue());
                }
                if (orange.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(orange.getId()), (String) orangeSize.getValue(), (int) orangeQty.getValue());
                }
                if (rootbeer.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(rootbeer.getId()), (String) rootbeerSize.getValue(), (int) rootbeerQty.getValue());
                }
                if (sierramist.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(sierramist.getId()), (String) sierraMistSize.getValue(), (int) sierraMistQty.getValue());
                }
                if (dietpepsi.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(dietpepsi.getId()), (String) dietPepsiSize.getValue(), (int) dietPepsiQty.getValue());
                }
                if (dietorange.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(dietorange.getId()), (String) dietOrangeSize.getValue(), (int) dietOrangeQty.getValue());
                }
                if (dietrootbeer.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(dietrootbeer.getId()), (String) dietRootBeerSize.getValue(), (int) dietRootBeerQty.getValue());
                }
                if (lemonade.isSelected()){
                    HomePage.getOrder(HomePage.returnOrders().size()-1).addDrink(Integer.parseInt(lemonade.getId()), (String) lemonadeSize.getValue(), (int) lemonadeQty.getValue());
                }
                
                OrderMorePage orderMorePage = new OrderMorePage();
                primaryStage.setScene(orderMorePage.getScene(primaryStage));
                primaryStage.show();
        }});
  
        Scene scene = new Scene(gridpane); 
        return scene;
    } 
}