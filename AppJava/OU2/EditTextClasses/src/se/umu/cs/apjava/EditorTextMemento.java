public class EditorTextMemento {
    private final String currText;
    private final int cursorPos;
    private final int selectEnd;



    public EditorTextMemento(EditorText editorText){
        currText = editorText.getText();
        cursorPos = editorText.getCursorPosition();
        selectEnd = editorText.getSelectionEndPosition();
    }

    public void setNewState(EditorText editorText) {
        editorText.setText(currText);
        editorText.setCursorPosition(cursorPos, selectEnd);
    }

}
