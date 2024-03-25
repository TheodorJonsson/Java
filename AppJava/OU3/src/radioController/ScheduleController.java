/**
 * Handles the refresh button and the timer to automatically refresh the table
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */
package radioController;

import radioGUI.ScheduleMenu;
import radioXML.Channel;
import radioXML.Episode;
import radioXML.ParseChannel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;


public class ScheduleController {
    private ScheduleMenu sMenu;
    private Channel channel;
    private Timer refreshTimer;
    private int timesUpdated = 0;

    private MouseAdapter doubleClickListener;

    public ScheduleController(ScheduleMenu sMenu, Channel channel){
        this.sMenu = sMenu;
        this.channel = channel;
        channel.createTableModel();
    }

    public void start(){
        timerToRefreshTable();
        executeParse();
    }

    /**
     * if it's the first time it's gotten the data it will also create a table for it.
     * then it will create a new table listener for it.
     *
     */
    public void done(){
        if(timesUpdated == 0){
            sMenu.createTable(channel.getTableModel());
        }
        init_TableListener();
        sMenu.updateGUI();
        timesUpdated++;
    }

    private void timerToRefreshTable() {
        int timeToRefresh = 3600000; //It's counted in ms so 3.6m ms is one hour
        init_refreshListener();

        refreshTimer = new Timer();
        refreshTimer.schedule(new TimerTask(){
            @Override
            public void run(){
                refreshTable();
            }
        },timeToRefresh, timeToRefresh);
    }

    /**
     * Adds a action listener to the refresh button so that it calls the refreshTable function
     */
    private void init_refreshListener(){
        sMenu.getRefreshButton().addActionListener(e->refreshTable());
    }


    /**
     * Will first check if a previous tablelistener exists and delete it if it does.
     * Creates a Mouselistener for the table in schedulemenu. This listener will get the
     * index of the selected index in the table then get the data of that episode then
     * send it to the schedulemenu class to open an JOptionpane with that data.
     */
    private void init_TableListener(){
        if(sMenu.getTableOfContents().getMouseListeners().length >= 1){
            sMenu.getTableOfContents().removeMouseListener(doubleClickListener);
        }
        doubleClickListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 & e.getButton() == MouseEvent.BUTTON1){
                    int index = sMenu.getTableOfContents().getSelectedRow();
                    Episode selectedEp = channel.getEpisodes().get(index);
                    sMenu.createandDisplayEpisodePopUp(selectedEp.getImage(),
                            selectedEp.getTitle(), selectedEp.getTagline());
                }
            }
        };
        sMenu.getTableOfContents().addMouseListener(doubleClickListener);
    }

    /**
     * Clears the table then creates a new SwingWorker to parse the channel.
     */
    private void refreshTable(){
        channel.clearTable();
        executeParse();
    }

    /**
     * Creates a Swinngworker to parse the channel
     */
    private void executeParse(){
        ParseChannel parseChannel = new ParseChannel(channel, this);
        parseChannel.execute();
    }

}
