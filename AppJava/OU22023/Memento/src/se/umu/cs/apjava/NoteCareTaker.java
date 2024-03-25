import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NoteCareTaker {
    private ArrayList<NoteMemento> notes;
    private ArrayList<Stack<NoteMemento>> memListStack;

    public NoteCareTaker(){
        notes = new ArrayList<>();
        memListStack = new ArrayList<>();
    }

    public void createNewNote(Note memento, int noteid){
        NoteMemento mem = new NoteMemento(memento);
        Stack memStack = new Stack<NoteMemento>();
        memListStack.add(noteid, memStack);
        notes.add(mem);

    }
    public void saveMemento(Note memento, int noteid){
        NoteMemento mem = new NoteMemento(memento);
        if(memListStack.isEmpty()){
            memListStack = new ArrayList<Stack<NoteMemento>>();
            Stack memStack = new Stack<NoteMemento>();
            memListStack.add(noteid, memStack);
        }
        memListStack.get(noteid).push(mem);
    }

    public NoteMemento getMemento(int noteíd){
        if(hasMemento(noteíd)){
            return memListStack.get(noteíd).pop();
        }
        return null;
    }
    public List<NoteMemento> getMementos(int noteid){
        return memListStack.get(noteid).stream().toList();
    }
    public NoteMemento getNote(int noteid){
        if(hasNote()){
            return notes.get(noteid);
        }
        return null;
    }

    public boolean hasNote(){
        if(notes.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean hasMemento(int noteid){
        if(memListStack.isEmpty() || memListStack.get(noteid).isEmpty()){
            return false;
        }
        return true;
    }
}
