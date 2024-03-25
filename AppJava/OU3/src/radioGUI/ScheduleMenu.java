/**
 * The JFrame for the individual channels and their programs
 * if closed can be opened from the main frames menubar.
 *
 *
 * Written and compiled in Java 17
 *
 * @Author Theodor Jonsson (ens18trn)
 * @date 2023-01-09
 */
package radioGUI;

import radioController.ScheduleController;
import radioXML.Channel;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;


public class ScheduleMenu extends JFrame{
    private JPanel centerPanel;
    private JSplitPane north;
    private Channel channel;
    private ScheduleController scheduleController;



    private JTable tableOfContents;


    private JButton refreshButton;

    public ScheduleMenu(Channel channel){
        scheduleController = new ScheduleController(this, channel);
        setTitle(channel.getName());
        this.channel = channel;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //the centerPanel will contain the table for the channel.
        centerPanel = new JPanel(new GridLayout());
        createNorthPanel(channel);


        add(centerPanel, BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);
        scheduleController.start();
        setVisible(true);
        pack();
    }

    public void updateGUI(){
        pack();
    }

    public void createTable(DefaultTableModel tModel){
        tableOfContents = new JTable(tModel);
        tableOfContents.getTableHeader().setReorderingAllowed(false);
        centerPanel.add(new JScrollPane(tableOfContents), BorderLayout.CENTER);


    }

    /**
     * Creates the north panel which contains the name of the channel,
     * the image of the channel, the tagline of the channel and a refresh button.
     * @param channel, it's channel
     */
    private void createNorthPanel(Channel channel) {
        JPanel northEast = new JPanel();
        ImageIcon image = getImageIcon(channel.getImage());
        JLabel label = new JLabel( image, JLabel.LEFT);
        label.setPreferredSize(new Dimension(60,60));
        northEast.add(label);

        JTextArea tagLine = createTextAreaForTagLine(channel);
        northEast.add(tagLine);
        northEast.setBorder(new LineBorder(Color.BLACK));

        JPanel northWest = new JPanel();
        northWest.setLayout(new GridLayout(2,1));
        refreshButton = new JButton("refresh");


        JLabel titleLabel = new JLabel(channel.getName(), JLabel.CENTER);
        northWest.add(titleLabel);
        northWest.add(refreshButton);

        north = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, northWest, northEast);

    }


    /**
     * Gets the tagline for the channel and creates a textarea for that tagline
     * @param channel
     * @return tagLine, which is a textArea with the tagLine
     */
    private JTextArea createTextAreaForTagLine(Channel channel) {
        JTextArea tagLine = new JTextArea();
        tagLine.setLineWrap(true);
        tagLine.setText(channel.getTagline());
        tagLine.setLayout(new FlowLayout());
        tagLine.setPreferredSize(new Dimension(720,60));
        tagLine.setEditable(false);
        return tagLine;
    }

    /**
     * Converts the byte array to an image then scales it to 60 by 60
     * @param bytes
     * @return
     */
    private ImageIcon getImageIcon(byte[] bytes)  {
        ImageIcon imageIcon = new ImageIcon(bytes);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }

    public void createandDisplayEpisodePopUp(byte[] bytes, String title, String tagline){
        ImageIcon epImage = getImageIcon(bytes);
        JOptionPane.showMessageDialog(
                this,
                tagline,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                epImage
                );

    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JTable getTableOfContents() {
        return tableOfContents;
    }
}
