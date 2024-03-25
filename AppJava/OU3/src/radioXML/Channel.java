/**
 * Holds the data for the individual channel. Creates the JTable
 * and gets the data gathered from parseChannel and adding it to the JTable
 *
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */

package radioXML;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Channel {
    private String name;
    private String id;
    private byte[] image;
    private String tagline;



    private boolean loaded = false;

    private DefaultTableModel tableModel;




    private ArrayList<Episode> episodes = new ArrayList<>();


    public Channel() {
    }


    /**
     * Creates the table model and adds the title columns for the table
     * then creates the JTable with the table model
     */
    public void createTableModel(){
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Program name");
        tableModel.addColumn("Starttime");
        tableModel.addColumn("Endtime");
    }

    /**
     * Adds the data to the table model
     * @param datalist
     */
    public void addToTable(ArrayList<Episode> datalist){
        for(int i = 0; i < datalist.size(); i++){
            tableModel.addRow(new Object[]{datalist.get(i).getTitle(),
                    datalist.get(i).getStartTime(), datalist.get(i).getEndTime()});
        }
    }

    /**
     * empties the tablemodel
     */
    public void clearTable(){
        tableModel.setRowCount(0);
    }

    /**
     * @return the name of the channel
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the id of the channel
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the image string of the channel
     */
    public byte[] getImage() {
        return image;
    }

    /**
     *
     * @return the tagline of the channel
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * sets the name of the channel
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the id of the channel
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * sets the string to the url of the image for the channel
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * sets the tagline to the channel
     * @param tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @Override
    public String toString(){
        return name + " " + id + " " + tagline + " " + image;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

}
