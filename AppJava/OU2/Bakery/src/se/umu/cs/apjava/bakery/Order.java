package se.umu.cs.apjava.bakery;

import java.util.ArrayList;
import java.util.List;

/**
 * Store a collection of cakes being ordered by a customer
 */
public class Order {
    private final List<Cake> cakes = new ArrayList<>();

    public void addCake(Cake cake) {
        cakes.add(cake);
    }

    public void printOrder(StringBuilder stringBuilder) {
        int totalPrice=0;
        for (Cake cake : cakes) {
            totalPrice+=cake.getCost();
            stringBuilder.append(String.format("   %2d  %s\n", cake.getCost(), cake.getDescription()));
        }
        stringBuilder.append("Total cost: "+totalPrice+"\n");
        stringBuilder.append("------------------------------------------\n");
    }
}
