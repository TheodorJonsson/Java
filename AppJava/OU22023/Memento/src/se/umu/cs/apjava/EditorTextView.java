import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorTextView {
    private static Note note;
    private NoteCareTaker manager;
    private JList mementoList;
    private JScrollPane mementoPane;
    private JETextArea textArea;
    private JFrame frame;
    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JButton select;
    private JButton newNote;
    private JButton save;
    private JButton remove;
    private JButton back;
    private JButton showAll;

    public EditorTextView(){
        manager = new NoteCareTaker();

        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 600));
        //frame.setPreferredSize(new Dimension(400,600));
        //buildWestPanel();

        buildCenterPanel();
        buildNorthPanel();

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        //frame.add(westPanel, BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     *
     */
    private void buildWestPanel(){

    }

    /**
     * @Method buildCenterPanel
     * Builds the center panel where the textarea is
     * located
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        note = new Note();
        textArea = new JETextArea(note);
        textArea.setLineWrap(true);
        textArea.setBorder(new LineBorder(Color.BLACK));

        centerPanel.setBorder(new LineBorder(Color.BLACK));
        centerPanel.add(textArea);
    }

    /**
     * @Method buildNorthPanel
     * builds the north panel which is the panel for
     * the buttons
     */
    private void buildNorthPanel() {
        northPanel = new JPanel();
        northPanel.setBorder(new LineBorder(Color.BLACK));
        buildButtons();
        northPanel.add(newNote);
        northPanel.add(save);
        northPanel.add(back);
        northPanel.add(select);
        northPanel.add(remove);
        northPanel.add(showAll);
    }

    /**
     * @Method buildButtons
     * builds the buttons and adds actionlisteners to them
     */
    private void buildButtons() {
        EditorTextListener listener = new EditorTextListener(note, this);
        newNote = new JButton("New Note");
        newNote.setActionCommand("New Note");
        newNote.addActionListener(listener);
        save = new JButton("Save");
        save.setActionCommand("Save");
        save.addActionListener(listener);
        back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(listener);
        select = new JButton("Select");
        select.setActionCommand("Select");
        select.addActionListener(listener);
        remove = new JButton("Remove");
        remove.setActionCommand("Remove");
        remove.addActionListener(listener);
        showAll = new JButton("Show all");
        showAll.setActionCommand("Show all");
        showAll.addActionListener(listener);
    }

    public void addToList(ListModel<String> list){
        if(westPanel == null){
            westPanel = new JPanel();
            westPanel.setLayout(new BorderLayout());
            mementoList = new JList<>(list);
            mementoList.setCellRenderer(new DefaultListCellRenderer());
            mementoList.setVisible(true);
            mementoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            mementoPane = new JScrollPane(mementoList);
            westPanel.add(mementoList);
            westPanel.setPreferredSize(new Dimension(100, 100));
            frame.add(westPanel, BorderLayout.WEST);
            frame.pack();
        }
        else{

        }
    }

    public void openMementoPane(String text){
        System.out.println("tar sig hit?");
        //JFrame popUpFrame = new JFrame();
        JOptionPane.showMessageDialog(frame, text, "All saved mementos for this note", JOptionPane.INFORMATION_MESSAGE);
        //popUpFrame.pack();
        //popUpFrame.setVisible(true);
    }

    public int getSelectedIndex(){
        if(mementoList != null){
            return mementoList.getSelectedIndex();
        }
        return 0;
    }
}
