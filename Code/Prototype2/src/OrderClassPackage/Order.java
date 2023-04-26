package OrderClassPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private static ArrayList<Pizza> pizzaOrder = new ArrayList<>();         //creates array list of pizzas
    private static ArrayList<Sides> sidesOrder = new ArrayList<>();         //creates array list of sides
    private static ArrayList<Drinks> drinksOrder = new ArrayList<>();       //creates array list of drinks
    private String name;                                                    //name of customer
    private String paymentMethod = "";                                      //payment method of order
    private String deliveryMethod = "";                                     //delivery method of order
    private double totalPrice = 0.00;                                       //total price of order
    
    //on new order, clears all array lists and puts in the customers name
    public Order(String aName){
        pizzaOrder.clear();
        sidesOrder.clear();
        drinksOrder.clear();
        name = aName;
    };
    
    //adds a pizza to an order
    public void addPizza(){
        pizzaOrder.add(new Pizza());
    };
    
    //gets a pizza from an order
    public Pizza getPizza(int i){
        return pizzaOrder.get(i);
    };
    
    //edits a pizza's size
    public void editPizzaSize(int position, int size){
        pizzaOrder.get(position).setSize(size);
    };
    
    //edits a pizza's crust
    public void editPizzaCrust(int position, String crust){
        pizzaOrder.get(position).setCrust(crust);
    };
    
    //edits a pizza's meat toppings
    public void editPizzaMeatToppings(int position, ArrayList<String> meatToppings){
        pizzaOrder.get(position).setMeatToppings(meatToppings);
    };
    
    //edits a pizza's veggie toppings
    public void editPizzaVeggieToppings(int position, ArrayList<String> veggieToppings){
        pizzaOrder.get(position).setVeggieToppings(veggieToppings);
    };
    
    //adds a side to the order
    public void addSide(int type, int quantity){
        sidesOrder.add(new Sides(type, quantity));
    };
    
    //edits the side type 
    public void editSideType(int position, int type){
        sidesOrder.get(position).setSideType(type);
    };
    
    //edits the side quantity
    public void editSideQty(int position, int quantity){
        sidesOrder.get(position).setSideQty(quantity);
    };
    
    //adds a drink to the order
    public void addDrink(int type, String size, int quantity){
        drinksOrder.add(new Drinks(type, size, quantity));
    };
    
    //edits a drink's type
    public void editDrinkType(int position, int type){
        drinksOrder.get(position).setDrinkType(type);
    };
    
    //edits a drink's size
    public void editDrinkSize(int position, String size){
        drinksOrder.get(position).setDrinkSize(size);
    };
    
    //edits a drink's quantity
    public void editDrinkQty(int position, int quantity){
        drinksOrder.get(position).setDrinkQty(quantity);
    };
    
    //sets a payment method
    public void setPaymentMethod(String method){
        paymentMethod = method;
    };
    
    //sets a delivery method
    public void setDeliveryMethod(String method){
        deliveryMethod = method;
    };
    
    //calculates the total price
    public void calculateTotalPrice(){
        totalPrice = 0;
        for (int i = 0; i<pizzaOrder.size(); i++){
            totalPrice = totalPrice + pizzaOrder.get(i).getPrice();
        }
        for (int i = 0; i<sidesOrder.size(); i++){
            totalPrice = totalPrice + sidesOrder.get(i).getSidePrice();
        }
        for (int i = 0; i<drinksOrder.size(); i++){
            totalPrice = totalPrice + drinksOrder.get(i).getDrinkPrice();
        }
    };
    
    //returns the pizza array list
    public static ArrayList<Pizza> returnPizzas(){
        return pizzaOrder;
    };
    
    //returns the side array list
    public static ArrayList<Sides> returnSides(){
        return sidesOrder;
    };
    
    //returns the drinks array list
    public static ArrayList<Drinks> returnDrinks(){
        return drinksOrder;
    };
    
    //returns total price
    public double getTotalPrice(){
        return totalPrice;
    };
    
    //returns payment method
    public String getPaymentMethod(){
        return paymentMethod;
    };
    
    //returns delivery method
    public String getDeliveryMethod(){
        return deliveryMethod;
    };
    
    //returns full order in summary format
    public String getOrder(){
        DecimalFormat df2 = new DecimalFormat("0.00");
        String order = "Name: " + name + "\n";
        for (int i = 0; i<pizzaOrder.size(); i++){
            order = order + pizzaOrder.get(i).getPizza() + "\n";
        }
        for (int i = 0; i<sidesOrder.size(); i++){
            order = order + sidesOrder.get(i).getSide() + "\n";
        }
        for (int i = 0; i<drinksOrder.size(); i++){
            order = order + drinksOrder.get(i).getDrink() + "\n";
        }
        order = order + "Total Price: " + df2.format(totalPrice) + "\n";
        order = order + "Payment Method: " + paymentMethod + "\n";
        order = order + "Delivery Method: " + deliveryMethod;
        return order;
    };
}