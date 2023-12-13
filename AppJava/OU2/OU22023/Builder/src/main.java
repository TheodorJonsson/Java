import se.umu.cs.appjava.maxdonalds.burger.*;

public class main {
    public static void main(String[] args){
        BurgerBuilder builder = new BurgerBuilder();
        builder.setMeat("Chicken");
        builder.addVegetable("Tomato");
        builder.addVegetable("Lettuce");
        builder.addSauce("Ketchup");
        builder.addSauce("Mayo");
        Burger burger = builder.build();
        System.out.println(burger.getDescription());
    }
}
