package se.umu.cs.apjava.bakery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BakeryController {

    private BakeryGuiBuilder gui;
    private static BakeryViewModel bakeryViewModel;

    public BakeryController(BakeryGuiBuilder gui){
        bakeryViewModel=new BakeryViewModel();
        this.gui = gui;
        initController();
    }
    private void initController(){
        gui.getPrintButton().addActionListener(e->printOrders());
        gui.getPurchaseButton().addActionListener(e->purchaseCake());
    }
    private void purchaseCake(){
        if(gui.getCcRB().isSelected()){
            createCC();
        }
        if(gui.getVcRB().isSelected()){
            createVC();
        }
        if(gui.getSbcRB().isSelected()){
            createSBC();
        }
    }
    private void createSBC() {
        Cake newCake = new StrawberryCake();
        addExtras(newCake);
        bakeryViewModel.newCake(newCake);
    }

    private void createCC(){
        Cake newCake = new ChocolateCake();
        addExtras(newCake);
        bakeryViewModel.newCake(newCake);
    }

    private void createVC(){
        Cake newCake = new VanillaCake();
        addExtras(newCake);
        bakeryViewModel.newCake(newCake);
    }

    private void printOrders(){
        String orderString = bakeryViewModel.printOrder();
        gui.getTextArea().append(orderString);

    }
    private void addExtras(Cake newCake) {
        if(gui.getSprinkle().isSelected()){
            newCake.addSprinkles();
            gui.getSprinkle().setSelected(false);
        }
        if(gui.getExtraLarge().isSelected()){
            newCake.addExtraLarge();
            gui.getExtraLarge().setSelected(false);
        }
        if(!gui.getInputText().getText().equals("")){
            newCake.addText(gui.getInputText().getText());
            gui.getInputText().setText("");
        }
    }
}
