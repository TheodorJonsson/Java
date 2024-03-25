import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * JTextArea with a class implementing se.umu.cs.apjava.NoteInterface as a model backing
 * @author Johan Eliasson johane@cs.umu.se
 * @version 1
 */
public class JETextArea extends JTextArea {
    private final NoteInterface text;
    private boolean ignoreEvents=false;

    /**
     * Initializes a new se.umu.cs.apjava.JETextArea with a se.umu.cs.apjava.NoteInterface instance as its model
     * @param text the model that handles/stores the text/selection/cursor position of
     *            this TextArea
     */
    public JETextArea(NoteInterface text) {
        this.text=text;
        setText(text.getText());
        getCaret().setDot(text.getSelectionEndPosition());
        getCaret().moveDot(text.getCursorPosition());
        text.addChangeListener(e -> handleTextChange());

        addCaretListener(e -> handleCaretEvent(text, e));
        getDocument().addDocumentListener(new MyDocumentListener());
    }

    private void handleCaretEvent(NoteInterface text, CaretEvent e) {
        if(!ignoreEvents) {
            ignoreEvents=true;
            text.setCursorPosition(e.getDot(), e.getMark());
            ignoreEvents=false;
        }
    }

    private void updateText() {
        if(!ignoreEvents) {
            ignoreEvents = true;
            text.setText(getText());
            ignoreEvents = false;
        }
    }

    private void handleTextChange() {
        if(!ignoreEvents) {
            ignoreEvents = true;
            setText(text.getText());
            var caret = getCaret();
            caret.setDot(text.getSelectionEndPosition());
            caret.moveDot(text.getCursorPosition());
            ignoreEvents = false;
            this.grabFocus();
        }
    }

    private class MyDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateText();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateText();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateText();
        }
    }

    /*public void setIgnoreEvents(boolean bool){
        ignoreEvents = bool;
    }*/
}
