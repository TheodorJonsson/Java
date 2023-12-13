public class NoteMemento {


    private final String currText;
    private final int ID;
    private final int cursorPos;
    private final int selectEnd;



    public NoteMemento(Note note){
        this.ID = note.getID();
        currText = note.getText();
        cursorPos = note.getCursorPosition();
        selectEnd = note.getSelectionEndPosition();
    }

    public void setNewState(Note note) {
        note.setText(currText);
        note.setCursorPosition(cursorPos, selectEnd);
    }

    public String getCurrText() {
        return currText;
    }
}
