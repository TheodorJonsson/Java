package se.umu.cs.apjava.maxdonalds;

import se.umu.cs.apjava.maxdonalds.burger.*;

public class BurgerOrderModel {
    private Order currentOrder=new Order();
    private boolean withMayo;
    private boolean withAioli;
    private boolean withLettuce;
    private boolean withTomato;
    private boolean withPickles;
    private boolean withKetchup;



    private boolean fav1;
    private boolean fav2;
    private boolean fav3;

    private BurgerBuilder burgerBuilder;
    private BurgerFactory burgerFactory;

    public BurgerOrderModel() {
        burgerBuilder = new BurgerBuilder();
    }

    String getOrderString() {
        StringBuilder stringBuilder=new StringBuilder();
        currentOrder.printOrder(stringBuilder);
        currentOrder=new Order();
        return stringBuilder.toString();
    }

    public void addBurgerToOrder() {

        if(withKetchup)
            burgerBuilder.addSauce("Ketchup");
        if(withMayo)
            burgerBuilder.addSauce("Mayo");
        if(withAioli)
            burgerBuilder.addSauce("Aioli");
        if(withLettuce)
            burgerBuilder.addVegetable("Lettuce");
        if(withTomato)
            burgerBuilder.addVegetable("Tomato");
        if(withPickles)
            burgerBuilder.addVegetable("Pickles");
        if(fav1){
            burgerFactory = new FavoriteOneFactory();
        }
        if(fav2){
            burgerFactory = new FavoriteTwoFactory();
        }
        if(fav3){
            burgerFactory = new FavoriteThreeFactory();
        }
        if(fav1 || fav2 || fav3){
            currentOrder.addBurger(burgerFactory.createBurger());
        }
        else{
            currentOrder.addBurger(burgerBuilder.build());
        }
        burgerBuilder = new BurgerBuilder();
        resetBurger();
    }

    private void resetBurger() {
        withMayo=false;
        withAioli=false;
        withKetchup=false;
        withLettuce=false;
        withTomato=false;
        withPickles =false;
    }

    public void setWithKetchup(boolean selected) {
        withKetchup=selected;
    }

    public void setWithMayo(boolean selected) {
        withMayo=selected;
    }

    public void setWithAioli(boolean selected) {
        withAioli=selected;
    }

    public void setWithLettuce(boolean selected) {
        withLettuce=selected;
    }

    public void setWithTomato(boolean selected) {
        withTomato=selected;
    }

    public void setWithPickles(boolean selected) {
        withPickles =selected;
    }

    public void setFav1(boolean selected) {
        this.fav1 = selected;
    }

    public void setFav2(boolean selected) {
        this.fav2 = selected;
    }

    public void setFav3(boolean selected) {
        this.fav3 = selected;
    }

    public void setType(String type) {
        burgerBuilder.setMeat(type);
    }
}
