package se.umu.cs.apjava.bakery;

import javax.swing.SwingUtilities;

/**
 * Create an order and print it
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new BakeryGuiBuilder().buildGui());
    }
}
