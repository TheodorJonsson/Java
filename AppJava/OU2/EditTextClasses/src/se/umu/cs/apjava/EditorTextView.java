import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorTextView {
    private static EditorText text;

    private NoteCareTaker manager;

    private JETextArea textArea;

    private JFrame frame;
    private JPanel centerPanel;
    private JPanel northPanel;
    private JButton restore;
    private JButton save;

    public EditorTextView(){
        manager = new NoteCareTaker();

        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,600));

        buildCenterPanel();

        buildNorthPanel();

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @Method buildCenterPanel
     * Builds the center panel where the textarea is
     * located
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        text = new EditorText();
        textArea = new JETextArea(text);
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
        northPanel.add(save);
        northPanel.add(restore);
    }

    /**
     * @Method buildButtons
     * builds the buttons and adds actionlisteners to them
     */
    private void buildButtons() {
        EditorTextListener listener = new EditorTextListener(text);
        save = new JButton("Save");
        save.setActionCommand("Save");
        save.addActionListener(listener);
        restore = new JButton("Restore");
        restore.setActionCommand("Restore");
        restore.addActionListener(listener);
    }
}
