package se.umu.cs.apjava.maxdonalds.burger;

public class FavoriteTwoFactory implements BurgerFactory{

    public FavoriteTwoFactory() {

    }

    @Override
    public Burger createBurger() {
        BurgerBuilder builder = new BurgerBuilder();
        builder.setMeat("Chicken");
        builder.addVegetable("Lettuce");
        builder.addVegetable("Lettuce");
        builder.addSauce("Ketchup");

        return builder.build();
    }
}
