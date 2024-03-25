package se.umu.cs.apjava.maxdonalds.burger;

import java.util.ArrayList;
import java.util.List;

public class BurgerBuilder {
    private String meatType;
    private int cost;
    private List<String> veg;
    private List<String> sauce;
    public BurgerBuilder(){
        this.meatType = "Beef";
        veg = new ArrayList<>();
        sauce = new ArrayList<>();
    }
    public void setMeat(String meat){
        this.meatType = meat;
    }
    public void addVegetable(String veg){
        this.veg.add(veg);
        cost += 3;
    }
    public void addSauce(String sauce){
        this.sauce.add(sauce);
        cost += 2;
    }
    public Burger build(){
        Burger burger = null;
        if(meatType == "Beef"){
            cost += 11;
            burger = new BeefBurger(cost, veg, sauce);
        }
        if(meatType == "Fish" || meatType == "fish"){
            cost += 12;
            burger = new FishBurger(cost, veg, sauce);
        }
        if(meatType == "Chicken" || meatType == "chicken"){
            cost += 10;
            burger = new ChickenBurger(cost, veg, sauce);
        }
        return burger;
    }
}
