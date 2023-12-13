package se.umu.cs.apjava.bakery;

public class BakeryViewModel {
    private Order currentOrder=new Order();
    private Cake currentCake;

    String printOrder() {
        finishOrder();
        StringBuilder stringBuilder=new StringBuilder();
        currentOrder.printOrder(stringBuilder);
        currentOrder=new Order();
        return stringBuilder.toString();
    }

    private void finishOrder() {
        if (currentCake!=null) {
            currentOrder.addCake(currentCake);
        }
        currentCake=null;
    }

    public void newCake(Cake cake) {
        if (currentCake!=null) {
            currentOrder.addCake(currentCake);
        }
        currentCake=cake;
    }
}
