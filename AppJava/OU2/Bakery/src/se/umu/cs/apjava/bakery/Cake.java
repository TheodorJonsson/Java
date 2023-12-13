package se.umu.cs.apjava.bakery;

/**
 * Cake bake class.
 */
public abstract class Cake {
    private int CAKE_COST;
    private String description;

    protected void addCost(int cost){
        CAKE_COST += cost;
    }
    public int getCost() {
        return CAKE_COST;
    }

    public String getDescription(){return description;}

    public void setDescription(String name){
        this.description = name;
    }

    public void addSprinkles(){
        addCost(2);
        description = description + " with sprinkles";
    }

    public void addExtraLarge(){
        addCost(5);
        description = description + " extra large";
    }

    public void addText(String input){
        addCost(1);
        description = description + " with text " + input;
    }
}
