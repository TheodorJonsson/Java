package se.umu.cs.apjava.maxdonalds;

import se.umu.cs.apjava.maxdonalds.burger.Burger;

import java.util.ArrayList;
import java.util.List;

/**
 * Store a collection of burgers being ordered by a customer
 */
public class Order {
    private final List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        burgers.add(burger);
    }

    public void printOrder(StringBuilder stringBuilder) {
        int totalPrice=0;
        for (Burger burger : burgers) {
            totalPrice+=burger.getCost();
            stringBuilder.append(String.format("   %2d  %s\n", burger.getCost(), burger.getDescription()));
        }
        stringBuilder.append("Total cost: ");
        stringBuilder.append(totalPrice);
        stringBuilder.append("\n");
        stringBuilder.append("------------------------------------------\n");
    }
}
