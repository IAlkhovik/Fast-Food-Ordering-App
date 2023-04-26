package OrderClassPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pizza {
    private int pizzaSize = 0;                                      //type of size
    private String pizzaCrust = "";                                 //pizza crust
    private ArrayList<String> meatToppings = new ArrayList<>();     //meat toppings
    private ArrayList<String> veggieToppings = new ArrayList<>();   //veggie toppings
    private double pizzaPrice = 0.00;                               //price of pizza
    
    //sets pizza's size
    public void setSize(int aSize){
        pizzaSize = aSize;
    };
    
    //sets pizza's crust
    public void setCrust(String aCrust){
        pizzaCrust = aCrust;
    };
    
    //sets pizza's meat toppings
    public void setMeatToppings(ArrayList<String> aMeatToppings){
        meatToppings = aMeatToppings;
    };
    
    //sets pizza's veggie toppings
    public void setVeggieToppings(ArrayList<String> aVeggieToppings){
        veggieToppings = aVeggieToppings;
    };
    
    //calculates the price of the pizza
    public void calculatePrice(){
        if (!meatToppings.isEmpty() && !veggieToppings.isEmpty()){
            pizzaPrice = 2 + pizzaSize*2 + (meatToppings.size()+veggieToppings.size()-1)*(.25+.25*pizzaSize);
        }
        else{
            pizzaPrice = 2 + pizzaSize*2;
        }
    };
    
    //returns size of pizza
    public String getSize(){
        String text = "Size: ";
        switch (pizzaSize) {
            case 1:
                text = text + "Small";
                return text;
            case 2:
                text = text + "Medium";
                return text;
            case 3:
                text = text + "Large";
                return text;
            case 4:
                text = text + "Extra Large";
                return text;
        }
        return text;
    };
    
    //returns crust
    public String getCrust(){
        return "Crust: " + pizzaCrust;
    };
    
    //returns meat toppings
    public String getMeatToppings(){
        String text = "Meat Toppings: ";
        if (meatToppings.isEmpty()){
            text = text + "None";
            return text;
        }
        for (int i=0; i<meatToppings.size()-1; i++){
            text = text + meatToppings.get(i) + ", ";
        }
        text = text + meatToppings.get(meatToppings.size()-1);
        return text;
    };
    
    //returns veggie toppings
    public String getVeggieToppings(){
        String text = "Veggie Toppings: ";
        if (veggieToppings.isEmpty()){
            text = text + "None";
            return text;
        }
        for (int i=0; i<veggieToppings.size()-1; i++){
            text = text + veggieToppings.get(i) + ", ";
        }
        text = text + veggieToppings.get(veggieToppings.size()-1);
        return text;
    };
    
    //returns price
    public double getPrice(){
        calculatePrice();
        return pizzaPrice;
    };
    
    //returns pizza order in summary form
    public String getPizza(){
        DecimalFormat df2 = new DecimalFormat("0.00");
        return getSize() + "\n" + getCrust() + "\n" + getMeatToppings() + "\n" + getVeggieToppings() + "\n" + "Price: " + df2.format(getPrice()) +"\n";
    };
}