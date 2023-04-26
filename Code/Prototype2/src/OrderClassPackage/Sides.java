package OrderClassPackage;

public class Sides {
    private int sideType = 0;           //type of side
    private int sideQty = 0;            //quantity of side
    private double sidePrice = 0.00;    //price of side
    
    //creates a side
    public Sides(int type, int quantity){
        sideType = type;
        sideQty = quantity;
        this.calculateSidePrice();
    };
    
    //sets side type
    public void setSideType(int aSideType){
        sideType = aSideType;
    };
    
    //sets side quantity
    public void setSideQty(int aSideQty){
        sideQty = aSideQty;
    };
    
    //calculates price of side
    public void calculateSidePrice(){
        switch (sideType){
            case 1:
                sidePrice = 4.00;
                sidePrice = sidePrice*sideQty;
                return;
            case 2:
                sidePrice = 4.00;
                sidePrice = sidePrice*sideQty;
                return;
            case 3:
                sidePrice = 2.00;
                sidePrice = sidePrice*sideQty;
                return;
        }
    };
    
    //gets side type
    public String getSideType() {
        String text = "Side: ";
        switch (sideType) {
            case 1:
                text = text + "Big Chocolate Chip Cookie";
                return text;
            case 2:
                text = text + "Breadsticks";
                return text;
            case 3:
                text = text + "Breadstick Bites";
                return text;
            default:
                text = text + "None";
                return text;
        }
    };
    
    //gets side quantity
    public int getSideQty(){
        return sideQty;
    };
    
    //gets side price
    public double getSidePrice(){
        return sidePrice;
    };
    
    //returns side in summary format
    public String getSide(){
        calculateSidePrice();
        return getSideType() + "\n" + "Quantity: " + getSideQty() + "\n" + "Price: " + getSidePrice() +"\n";
    };
}