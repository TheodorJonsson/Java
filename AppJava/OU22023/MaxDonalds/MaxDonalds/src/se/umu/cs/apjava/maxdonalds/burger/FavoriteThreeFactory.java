package se.umu.cs.apjava.maxdonalds.burger;

public class FavoriteThreeFactory implements BurgerFactory{

    public FavoriteThreeFactory() {

    }

    @Override
    public Burger createBurger() {
        BurgerBuilder builder = new BurgerBuilder();
        builder.setMeat("Fish");
        builder.addVegetable("Pickles");
        builder.addVegetable("Lettuce");
        builder.addSauce("Mayo");

        return builder.build();
    }
}
