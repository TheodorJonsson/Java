/**
 * @Class parseXMLAllChannels
 * Handles the parsing of the XML document that has all the channels.
 * The class also handles the creation of the JPanels with the image and name
 * of the channel. Sends this data to the ChannelList object to be displayed
 * Uses a SwingWorker to avoid locking the GUI
 *
 *
 * Written and Compiled in Java 17
 *
 * @Author Theodor Jonsson
 * @Date 2023-01-09
 */

package radioXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;




public class parseXMLAllChannels extends SwingWorker<List<Channel>, List<Channel>> {
    private ChannelList list;
    //private List<Channel> listChannels = new ArrayList<>();
    private List<JPanel> listOfPanels = new ArrayList<>();
    public parseXMLAllChannels(ChannelList list){
        this.list = list;
    }
    @Override
    protected List<Channel> doInBackground(){
        List<Channel> listChannels = new ArrayList<>();
        try {
            NodeList channelList = parsingXMLDOM();
            addChannels(listChannels, channelList);

        }catch (MalformedURLException e) {
            listChannels.clear();
        } catch (IOException e) {
            listChannels.clear();
        } catch (SAXException e) {
            listChannels.clear();
        } catch (ParserConfigurationException e) {
            listChannels.clear();
        }

        return listChannels;
    }

    /**
     * Parses the XML document and returns a nodelist of all nodes with the tag "channel"
     * @return channelList, a NodeList.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private NodeList parsingXMLDOM() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new URL("http://api.sr.se/api/v2/channels?pagination=false").openStream());
        Element rootElement = doc.getDocumentElement();
        NodeList channelList = doc.getElementsByTagName("channel");
        return channelList;
    }

    /**
     * retrieves the attributes and nodes of each channel and creates a channel object
     * to place these in to later place in the list.
     * @param channels
     * @param channelList
     * @return
     */
    private void addChannels(List<Channel> channels, NodeList channelList) {

        for(int i = 0; i < channelList.getLength(); i++){
            Node channelNode = channelList.item(i);
            if(channelNode.getNodeType() == Node.ELEMENT_NODE) {
                Channel channel = new Channel();
                Element channelEle = (Element) channelNode;
                channel.setName(channelEle.getAttribute("name"));
                channel.setId(channelEle.getAttribute("id"));

                NodeList childChannelNodeList = channelNode.getChildNodes();
                getChildAttributes(channel, childChannelNodeList);
                channels.add(channel);
            }
        }

    }

    /**
     * Gets the contents of the child nodes of the channel
     * @param newChannel
     * @param childChannelNodeList
     */
    private void getChildAttributes(Channel newChannel, NodeList childChannelNodeList) {
        for(int j = 0; j < childChannelNodeList.getLength(); j++){
            Node childNode = childChannelNodeList.item(j);
            if(childNode.getNodeType() == Node.ELEMENT_NODE){
                if(childNode.getNodeName().equals("image")){
                    newChannel.setImage(loadImageToByte(childNode.getTextContent()));
                }
                if(childNode.getNodeName().equals("tagline")){
                    newChannel.setTagline(childNode.getTextContent());
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
     * Runs after doInBackground is completed and sends all the panels and channels
     * to the list of channels.
     */
    @Override
    protected void done(){
        try {
            List<Channel> channels = get();

            if(!channels.isEmpty()){
                for(int i = 0; i < channels.size(); i++){
                    list.getListOfChannels().add(channels.get(i));
                }
                //Checks that all is done and all information can be added to the JList.
                list.isDoneParsing(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Could not find any Channels.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExecutionException e) {
            JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
