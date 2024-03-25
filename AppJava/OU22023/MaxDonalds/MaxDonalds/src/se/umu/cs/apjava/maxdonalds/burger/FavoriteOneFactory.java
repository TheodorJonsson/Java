package se.umu.cs.apjava.maxdonalds.burger;

public class FavoriteOneFactory implements BurgerFactory{

    public FavoriteOneFactory() {

    }

    @Override
    public Burger createBurger() {
        BurgerBuilder builder = new BurgerBuilder();
        builder.setMeat("Chicken");
        builder.addVegetable("Tomato");
        builder.addVegetable("Lettuce");
        builder.addSauce("Ketchup");
        builder.addSauce("Mayo");

        return builder.build();
    }
}
