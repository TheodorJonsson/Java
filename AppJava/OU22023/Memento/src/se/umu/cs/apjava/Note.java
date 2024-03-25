import java.util.ArrayList;
import java.util.List;

public class Note implements NoteInterface {
    private List<EditorTextChangeListener> listener;
    private String currText;



    private int ID;
    private int cursorPos;
    private int selectEnd;

    public Note(){
        ID = 0;
        listener = new ArrayList<>();
        this.currText = "";
        this.cursorPos = 0;
        this.selectEnd = 0;
    }
    @Override
    public String getText() {
        updateAllListeners();
        return currText;
    }

    @Override
    public void setText(String text) {
        this.currText = text;
        updateAllListeners();
    }

    @Override
    public void setCursorPosition(int cursorPosition, int selectionEnd) {
        cursorPos = cursorPosition;
        selectEnd = selectionEnd;
        updateAllListeners();
    }

    @Override
    public int getCursorPosition() {
        updateAllListeners();
        return cursorPos;
    }

    @Override
    public int getSelectionEndPosition() {
        updateAllListeners();
        return selectEnd;
    }
    public int getID() {
        return ID;
    }
    public void setID(int id){
        ID = id;
    }

    @Override
    public void addChangeListener(EditorTextChangeListener listener) {
        this.listener.add(listener);
    }

    @Override
    public void removeChangeListener(EditorTextChangeListener editorTextChangeListener) {
        this.listener.remove(editorTextChangeListener);
    }

    private void updateAllListeners(){
        listener.forEach(listener -> listener.stateUpdated(this));
    }
}
