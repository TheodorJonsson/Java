/**
 * Handles the parsing of individual channels to get the
 * program for that channel. Stores all the data in a 2D array to be
 * sent to a table model.
 * Uses a SwingWorker to avoid locking the GUI
 *
 * Written and Compiled in Java 17
 *
 * @Author Theodor Jonsson
 * @Date 2023-01-09
 */

package radioXML;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import radioController.ScheduleController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.Object.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



public class ParseChannel extends SwingWorker<ArrayList<Episode>, ArrayList<String[]>> {

    private Channel channel;
    private ScheduleController scheduleController;

    public ParseChannel(Channel channel, ScheduleController sController){
        this.scheduleController = sController;
        this.channel = channel;
    }
    @Override
    protected ArrayList<Episode> doInBackground() {
        ArrayList<Episode> episodeArrayList = new ArrayList<>();
        try {
            NodeList rootList = getNodeList();

            for(int i = 0; i < rootList.getLength(); i++){
                Node allEpisodes = rootList.item(i);
                if(allEpisodes.getNodeType() == Node.ELEMENT_NODE){
                    NodeList scheduleList = allEpisodes.getChildNodes();
                    addEpisodesToStringArray(episodeArrayList, scheduleList);
                }
            }
        } catch (ParserConfigurationException e) {
            episodeArrayList.clear();
        } catch (SAXException e) {
            episodeArrayList.clear();
        } catch (IOException e) {
            episodeArrayList.clear();
        }

        return episodeArrayList;
    }

    /**
     * Goes through the nodelist of the XML document to find the title, starttime and endtime
     * and adds these strings to a string array when all three values have been found and added
     * add the string array to a 2D string
     * @param episodeArrayList, the 2D String array
     * @param scheduleList, This is the child nodelist of a node
     */
    private void addEpisodesToStringArray(ArrayList<Episode> episodeArrayList, NodeList scheduleList) throws IOException {
        for(int j = 0; j < scheduleList.getLength(); j++){
            Node scheduledEpisode = scheduleList.item(j);
            if(scheduledEpisode.getNodeType() == Node.ELEMENT_NODE){
                Episode episode = new Episode();
                NodeList scheduledEpisodeList = scheduledEpisode.getChildNodes();
                Boolean hasBeenPublished = false;
                int isDone = 0;
                for(int k = 0; k < scheduledEpisodeList.getLength(); k++){
                    Node episodeXML = scheduledEpisodeList.item(k);
                    if(episodeXML.getNodeType() == Node.ELEMENT_NODE){
                        if(episodeXML.getNodeName().equals("title")){
                            episode.setTitle(episodeXML.getTextContent());
                            if(episode.getTitle() != null)
                            {
                                isDone++;
                            }
                        }
                        if(episodeXML.getNodeName().equals("starttimeutc")){
                            episode.setStartTime(getTimeInLocal(episodeXML.getTextContent(), true));
                            if(episode.getStartTime() != null){
                                isDone++;
                            }
                        }
                        if(episodeXML.getNodeName().equals("endtimeutc")){
                            episode.setEndTime(getTimeInLocal(episodeXML.getTextContent(), false));
                            if(episode.getEndTime() != null){
                                isDone++;
                            }
                        }
                        if(episodeXML.getNodeName().equals("imageurl")){
                            episode.setImage(loadImageToByte(episodeXML.getTextContent()));
                            if(episode.getImage() != null){
                                isDone++;
                            }
                        }
                        if(episodeXML.getNodeName().equals("description")){
                            episode.setTagline(episodeXML.getTextContent());
                            if(episode.getTagline() != null){
                                isDone++;
                            }
                        }
                        if(isDone == 5 && hasBeenPublished == false){
                            hasBeenPublished = true;
                            episodeArrayList.add(episode);
                        }
                    }
                }
            }
        }
    }

    /**
     * Loads an image using BufferedImage then converts it to an byte array to be stored.
     * returns null if an IO exception happens and will return an image of an error sign if
     * a url malfunctio
     * @param imageName
     * @return imageBytes which is the image in an byte array
     */
    private byte[] loadImageToByte(String imageName){
        URL url = null;
        byte[] imageBytes = null;
        InputStream is = null;
        try {
            url = new URL(imageName);
            BufferedImage bImage = ImageIO.read(url);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            imageBytes = bos.toByteArray();
        } catch (MalformedURLException e) {
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(new File("/resources/errorSign.png"));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", bos);
                imageBytes = bos.toByteArray();
            } catch (IOException ex) {
                return null;
            }

        } catch (IOException e) {
            return null;
        }
        return imageBytes;
    }



    /**
     * Gets a nodelist of the XML document
     * @return rootlist
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private NodeList getNodeList() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String url = "http://api.sr.se/api/v2/scheduledepisodes?channelid="+channel.getId()+"&pagination=false";
        Document doc = builder.parse(new URL(url).openStream());
        Element rootElement = doc.getDocumentElement();
        NodeList rootList = doc.getElementsByTagName("schedule");
        return rootList;
    }

    /**
     * Sends the data gathered to the channel to be added to its table model.
     */
    @Override
    protected void done() {
        try {
            if(!get().isEmpty()){

                channel.setEpisodes(get());
                channel.addToTable(get());
                scheduleController.done();
            }
            else{
                JOptionPane.showMessageDialog(null, "Could not find any episodes", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExecutionException e) {
            JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * Checks if the time is within bounds of 6h before and 12 hours after the current time.
     * If it is it will return a string of the episode time in local time zone.
     * If not it will return null showing that it wasn't in bounds.
     *
     * This method handles checking both start time and end time
     * @param episodeTime
     * @return timeOfEpisode, in format HH:MM:SS
     */
    public String getTimeInLocal(String episodeTime, boolean isStartTime){
        //Gets the current time in UTC so it becomes easier to compare and parses input
        //String to a date format
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime epTime = OffsetDateTime.parse(episodeTime);

        //Gets the time between current time and episode time
        Duration pastDuration = Duration.between(epTime, now);
        Duration futureDuration = Duration.between(now, epTime);

        //Checks if the time is within bounds
        /*if(pastDuration.toHours() >= 6 || futureDuration.toHours() >= 12){
            return null;
        }*/
        if(isStartTime == true && futureDuration.toHours() >= 12){
            return null;
        }
        else if(isStartTime == false && pastDuration.toHours() >= 6){
            return null;
        }

        //Translates the time to local time zone of the current system
        ZonedDateTime zoned = epTime.atZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime localZoneEpTime = zoned.toLocalDateTime();
        String timeOfEpisode = localZoneEpTime.toLocalTime().toString();
        return timeOfEpisode;

    }



}
