import java.util.ArrayList;
import java.util.List;

public class EditorText implements EditorTextInterface{
    private List<EditorTextChangeListener> listener;
    private String currText;
    private int cursorPos;
    private int selectEnd;

    public EditorText(){
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
