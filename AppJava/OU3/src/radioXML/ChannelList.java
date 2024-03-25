/**
 * Holds the channels for the JList with all channels and starts parseXMLAllChannels to
 * gather all the channels and put them in the JList
 *
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */
package radioXML;

import java.util.*;

import radioController.MainController;


public class ChannelList{

    private ArrayList<Channel> listOfChannels = new ArrayList<>();
    //private List<JPanel> listOfPanels = new ArrayList<>();
    private MainController controller;
    private parseXMLAllChannels parse;
    private boolean parsed = false;


    /**
     * Creates the ChannelList and starts the SwingWorker.
     */
    public ChannelList(MainController controller) {
        this.controller = controller;
        parse = new parseXMLAllChannels(this);
    }

    /**
     * Gets the data once again from the XML document
     */
    public void refreshChannelList(){
        //listOfPanels.clear();
        parse = new parseXMLAllChannels(this);
        parse.execute();
    }

    public void executeParsing(){
        parse.execute();
    }

    public boolean isParsed() {
        return parsed;
    }


    /**
     * if parsed is true it will call the addToList method to add
     * all the panels to the JList
     * @param parsed
     */
    public void isDoneParsing(boolean parsed) {
        this.parsed = parsed;
        if (isParsed() == true) {
            controller.createPanels(listOfChannels);
        }
        this.parsed = false;
    }

    /**
     * Adds the panels to the JList
     *
    public void addToJList() {
        menu.getListOfChannels().setListData(listOfPanels.toArray());
        menu.removeLoadingText();
        menu.getFrame().pack();
    }*/



    public ArrayList<Channel> getListOfChannels() {
        return listOfChannels;
    }

}

