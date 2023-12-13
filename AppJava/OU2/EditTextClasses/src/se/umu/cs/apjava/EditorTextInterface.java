/**
 * Interface for objects that can act as a text model for se.umu.cs.apjava.JETextArea
 */
public interface EditorTextInterface {

    /**
     * Get the text of this se.umu.cs.apjava.EditorText as a String
     * @return the text
     */
    String getText();

    /**
     * Update the text in this se.umu.cs.apjava.EditorText
     * @param text the new text
     */
    void setText(String text);

    /**
     * Update the cursor position and if text is selected. Position indexes start with 0 being before the first char
     * @param cursorPosition the position where next text input should be done
     * @param selectionEnd
     *              position of ending of selected text. Should be the same as cursorPosition
     *              if no text should be selected. Selections can go in either direction from cursor position so
     *              might be less than or greater than cursorPosition
     */
    void setCursorPosition(int cursorPosition, int selectionEnd);


    /**
     * Get the cursor position. This is the position were input should occur. 0 is before first char.
     * @return the position where text input should occur
     */
    int getCursorPosition();

    /**
     * Returns end position of selected text. If no text is selected it will return
     * the same value as getCursorPosition. The text between the cursor position
     * and the value returned by this function should be considered selected
     * @return the end position of the selected part of the text
     */
    int getSelectionEndPosition();

    /**
     * Add a listener that should be notified when the state (text, selection or cursor position in this object changes)
     * @param listener the listener to add.
     */
    void addChangeListener(EditorTextChangeListener listener);

    /**
     * Remove a previously added listener. The listener will no longer be notified in changes in the state of this object
     * @param editorTextChangeListener the listener to remove
     */
    void removeChangeListener(EditorTextChangeListener editorTextChangeListener);
}
