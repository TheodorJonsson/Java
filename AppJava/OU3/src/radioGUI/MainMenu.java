/**
 *
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */
package radioGUI;

import radioController.ChannelListCellRenderer;
import radioController.MainController;
import radioXML.ChannelList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class MainMenu {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;

    private JList<JPanel> listOfChannels;
    private MainController mainController;

    private JButton refreshListButton;
    private JPanel northPanel;
    private JPanel centerPanel;

    public MainMenu(){
        this.buildGUI();
    }


    public void buildGUI(){
        mainController = new MainController(this);
        buildFrame();
        buildCenterPanel();
        buildNorthPanel();
        refreshListButton = new JButton("Refresh List");
        frame.add(refreshListButton, BorderLayout.SOUTH);
        mainController.start();
        frame.pack();
        frame.setVisible(true);
    }

    public void updateGUI(){
        frame.pack();
    }

    private void buildFrame() {
        frame = new JFrame("Radio Information");
        frame.setSize(new Dimension(400,600));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
    }

    private void buildCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout());
        centerPanel.setBorder(new TitledBorder("Channels.... Loading List of Channels."));
        buildListOfChannels();

        frame.add(centerPanel, BorderLayout.CENTER);
    }

    private void buildNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buildMenuBar();
        //northPanel.add(menuBar);
        frame.setJMenuBar(menuBar);
        frame.add(northPanel, BorderLayout.NORTH);
    }


    /**
     * Creates the list which holds the channels as a ScrollPane
     * Adds this to the panel in the center
     */
    private void buildListOfChannels(){
        createListOfPanels();
        JScrollPane scroll = new JScrollPane(listOfChannels);

        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        centerPanel.add(scroll);

    }

    /**
     * Creates the list of panels that is to be used for the ScrollPane
     */
    private void createListOfPanels() {
        listOfChannels = new JList();
        listOfChannels.setCellRenderer(new ChannelListCellRenderer());
        listOfChannels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfChannels.setLayoutOrientation(JList.VERTICAL);
    }

    /**
     * builds a menu bar
     */
    private void buildMenuBar(){

        menuBar = new JMenuBar();
        menu = new JMenu("Cached Channels");
        menuBar.add(getMenu());

    }

    /**
     * Adds an item to the menu that is used to open
     * the JFrame which holds the table of times
     * This is used to be able to get the channels after they have been minimized
     * @param name, name of the Channel
     * @param subFrame, The frame which contains everything
     */
    public void addMenuItem(String name, JFrame subFrame){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(e->{
            subFrame.setVisible(true);
        });
        getMenu().add(item);
    }


    /************ All getters for the main menu *****************/
    public JButton getRefreshListButton() {return refreshListButton;}
    public JMenu getMenu() {
        return menu;
    }
    public JMenuBar getMenuBar() {
        return menuBar;
    }
    public JFrame getFrame() {
        return frame;
    }
    public JList getListOfChannels() {
        return listOfChannels;
    }
    public void removeLoadingText(){
        centerPanel.setBorder(new TitledBorder("Channels"));
    }
    /*******************************************************/
}
