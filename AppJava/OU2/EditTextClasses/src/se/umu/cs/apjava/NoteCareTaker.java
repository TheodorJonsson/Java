import java.util.Stack;

public class NoteCareTaker {
    private Stack<EditorTextMemento> mementos;

    public NoteCareTaker(){
        mementos = new Stack<>();
    }

    public void save(EditorTextMemento memento){
        mementos.push(memento);
    }

    public EditorTextMemento restore(){
        if(hasMemento()){
            return mementos.pop();
        }
        return null;
    }

    public boolean hasMemento(){
        if(mementos.isEmpty()){
            return false;
        }
        return true;
    }
}
