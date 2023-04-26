package OrderClassPackage;

public class Drinks {
    private int drinkType = 0;          //type of drink
    private String drinkSize = "";      //size of drinks
    private int drinkQty = 0;           //quantity of drink
    private double drinkPrice = 0.00;   //price of drink
    
    //creates a drink
    public Drinks(int type, String size, int quantity){
        drinkType = type;
        drinkSize = size;
        drinkQty = quantity;
        this.calculateDrinkPrice();
    };
    
    //sets drink type
    public void setDrinkType(int aDrinkType){
        drinkType = aDrinkType;
    };
    
    //sets drink size
    public void setDrinkSize(String aDrinkSize){
        switch (aDrinkSize){
            case "S":
                drinkSize = "Small";
                return;
            case "M":
                drinkSize = "Medium";
                return;
            case "L":
                drinkSize = "Large";
                return;
            default:
                drinkSize = "";
                return;
        }
    };
    
    //sets drink quantity
    public void setDrinkQty(int aDrinkQty){
        drinkQty = aDrinkQty;
    };
    
    //calculates drink price
    public void calculateDrinkPrice(){
        drinkPrice = drinkQty *1.00;
    };
    
    //returns drink type based on the drinkType int
    public String getDrinkType(){
        String text = "Drink: ";
        switch (drinkType){
            case 1:
                text = text + "Pepsi";
                return text;
            case 2:
                text = text + "Orange";
                return text;
            case 3:
                text = text + "Root Beer";
                return text;
            case 4:
                text = text + "Sierra Mist";
                return text;
            case 5: 
                text = text + "Diet Pepsi";
                return text;
            case 6:
                text = text + "Diet Orange";
                return text;
            case 7:
                text = text + "Diet Root Beer";
                return text;
            case 8:
                text = text + "Lemonade";
                return text;
            default:
                text = text + "None";
                return text;
        }
    };
    
    //returns drink size
    public String getDrinkSize(){
        return "Size: " + drinkSize;
    };
    
    //returns drink quantity
    public int getDrinkQty(){
        return drinkQty;
    };
    
    //returns drinks price
    public double getDrinkPrice(){
        return drinkPrice;
    };
    
    //returns all of the drink info in a summary
    public String getDrink(){
        calculateDrinkPrice();
        return getDrinkType() + "\n" + getDrinkSize() + "\n" + "Quantity: " + getDrinkQty() + "\n" + "Price: " + getDrinkPrice() +"\n";
    };
}