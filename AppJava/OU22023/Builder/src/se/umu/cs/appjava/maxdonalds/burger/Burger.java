package se.umu.cs.appjava.maxdonalds.burger;

import java.util.List;

public abstract class Burger {
    protected List<String> vegetables;
    protected List<String> sauces;
    public abstract int getCost();
    public abstract String getDescription();

}
