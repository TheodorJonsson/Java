/**
 * Handles what happens when you click on the list and is responsible for
 * creating the new initializing the frames and adding them to the main frames menubar.
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */
package radioController;

import radioGUI.*;
import radioXML.Channel;
import radioXML.ChannelList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainController {
    private MainMenu menu;
    private ChannelList channelList;
    private List<JPanel> listOfPanels;
    private int timesUpdated = 0;
    private MouseAdapter doubleClickListener;
    private ArrayList<ScheduleMenu> sMenuList = new ArrayList<>();


    public MainController(MainMenu menu) {
        this.menu = menu;
        this.channelList = new ChannelList(this);

    }

    public void start(){
        channelList.executeParsing();
        menu.getRefreshListButton().addActionListener(e->refreshChannelList());
    }

    /**
     * Creates the panels that a with the name of and image from the channel objects
     *
     * @param listChannels
     */
    public void createPanels(List<Channel> listChannels) {
        listOfPanels = new ArrayList<>();
        for (int i = 0; i < listChannels.size(); i++) {
            if (listChannels.get(i).getImage() != null) {
                ImageIcon imageIcon = getImageIcon(listChannels.get(i).getImage());
                JLabel label = new JLabel(listChannels.get(i).getName(), imageIcon, JLabel.LEFT);
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panel.add(label);
                listOfPanels.add(panel);
            }
        }
        updateJList();
    }

    private void updateJList() {
        menu.getListOfChannels().setListData(listOfPanels.toArray());
        menu.removeLoadingText();
        init_MouseListener();
        menu.updateGUI();
    }

    /**
     * Converts the byte array to an image then scales it to 60 by 60
     * @param bytes
     * @return
     */
    private ImageIcon getImageIcon(byte[] bytes){
        ImageIcon imageIcon = new ImageIcon(bytes);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }

    /**
     * Creates a mouselistener for the JList that has the all the channels
     * Which will start the SwingWorker that parses the individual channels.
     */
    private void init_MouseListener() {

        doubleClickListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // If the user double clicks with mouse 1
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int index = menu.getListOfChannels().getSelectedIndex();
                    //Creates a new JFrame for the channel if it doesn't exist already
                    if(channelList.getListOfChannels().get(index).isLoaded() == false){
                        ScheduleMenu sMenu = new ScheduleMenu(channelList.getListOfChannels().get(index));
                        sMenuList.add(index, sMenu);
                        channelList.getListOfChannels().get(index).setLoaded(true);
                        menu.addMenuItem(channelList.getListOfChannels().get(index).getName(), sMenu);
                    }
                    // if the ScheduleMenu already exists set it to visible.
                    else{
                        sMenuList.get(index).setVisible(true);
                    }
                }
            }
        };
        menu.getListOfChannels().addMouseListener(doubleClickListener);
  
    }

    /**
     * Removes the mouselistener then parses the channels in again.
     */
    private void refreshChannelList() {
        menu.getListOfChannels().removeMouseListener(doubleClickListener);
        channelList = new ChannelList(this);
        channelList.executeParsing();
    }
}

