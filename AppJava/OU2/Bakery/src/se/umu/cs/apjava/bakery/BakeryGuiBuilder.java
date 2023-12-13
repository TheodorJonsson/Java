package se.umu.cs.apjava.bakery;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BakeryGuiBuilder {

    private JFrame frame;

    private JPanel printPanel;
    private JSplitPane sidePane;

    private JCheckBox sprinkle;
    private JCheckBox extraLarge;

    private JTextArea textArea;
    private JScrollPane textPane;
    private JTextField inputText;


    private JRadioButton sbcRB;
    private JRadioButton ccRB;
    private JRadioButton vcRB;
    private ButtonGroup cakeButtons;



    private JButton purchaseButton;
    private JButton printButton;



    private BakeryController listener;

    void buildGui() {
        frame=new JFrame("Bakery");

        buildTextArea();
        buildPrintPanel();
        buildSidePanel();



        frame.add(sidePane, BorderLayout.EAST);
        frame.add(printPanel,BorderLayout.SOUTH);
        textPane = new JScrollPane(textArea);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listener = new BakeryController(this);
        frame.pack();
        frame.setVisible(true);
    }

    private void buildTextArea() {
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600,400));
    }

    private void buildPrintPanel() {
        printPanel = new JPanel();
        printButton = new JButton("Print Order");
        printPanel.add(printButton);
    }

    private void buildSidePanel() {
        JPanel topPanel = new JPanel();
        sprinkle = new JCheckBox("Add sprinkles");
        extraLarge = new JCheckBox("Extra large");
        topPanel.add(sprinkle);
        topPanel.add(extraLarge);

        JPanel middlePanel = new JPanel();

        middlePanel.setBorder(new TitledBorder("Add text to cake"));
        inputText = new JTextField();
        inputText.setPreferredSize(new Dimension(150,20));
        middlePanel.add(inputText);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4,1));
        addCakeRadioButtons(bottomPanel);

        JSplitPane botmidPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, middlePanel, bottomPanel);
        sidePane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, botmidPane);
        sidePane.setBorder(new TitledBorder("Add extras to cake"));
    }

    private void addCakeRadioButtons(JPanel bottomPanel) {
        ccRB = new JRadioButton("Chocolate Cake");
        vcRB = new JRadioButton("Vanilla Cake");
        sbcRB = new JRadioButton("Strawberry Cake");
        cakeButtons = new ButtonGroup();
        cakeButtons.add(ccRB);
        cakeButtons.add(vcRB);
        cakeButtons.add(sbcRB);
        purchaseButton = new JButton("Purchase");
        bottomPanel.add(ccRB);
        bottomPanel.add(vcRB);
        bottomPanel.add(sbcRB);
        bottomPanel.add(purchaseButton);
    }




    public JCheckBox getSprinkle(){
        return sprinkle;
    }
    public JCheckBox getExtraLarge() {
        return extraLarge;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JScrollPane getTextPane() {
        return textPane;
    }

    public JTextField getInputText() {
        return inputText;
    }

    public JButton getPrintButton() {
        return printButton;
    }

    public JRadioButton getSbcRB() {
        return sbcRB;
    }

    public JRadioButton getCcRB() {
        return ccRB;
    }

    public JRadioButton getVcRB() {
        return vcRB;
    }

    public ButtonGroup getCakeButtons() {
        return cakeButtons;
    }

    public JButton getPurchaseButton() {
        return purchaseButton;
    }

}
