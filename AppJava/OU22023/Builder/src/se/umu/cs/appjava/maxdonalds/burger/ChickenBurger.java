package se.umu.cs.appjava.maxdonalds.burger;

import java.util.List;

class ChickenBurger extends Burger{
    private int cost;
    private String meatType;
    public ChickenBurger(int cost, List<String> veg, List<String> sauce){
        meatType = "Chicken";
        this.cost = cost;
        this.vegetables = veg;
        this.sauces = sauce;
    }
    public int getCost(){
        return cost;
    }
    public String getDescription(){
        String toString = new String();
        if(meatType != null){
            toString = meatType + " " + "burger";
        }
        if(vegetables != null){
            toString = toString + " with " + vegetables;
        }
        if(sauces != null){
            toString = toString + " and " + sauces;
        }
        toString = toString + " costing " + cost;

        return toString;
    }
}
