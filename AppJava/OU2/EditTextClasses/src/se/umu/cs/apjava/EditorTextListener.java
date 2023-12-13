import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorTextListener implements ActionListener {
    private static EditorText text;

    private NoteCareTaker manager;

    public EditorTextListener(EditorText text){
        this.text = text;
        manager = new NoteCareTaker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Save")){
            EditorTextMemento mem = new EditorTextMemento(text);
            manager.save(mem);
        }
        if(e.getActionCommand().equals("Restore")){
            if(manager.hasMemento()){
                System.out.println("Finns memento");
                EditorTextMemento mem = manager.restore();
                mem.setNewState(text);
            }
        }
    }
}
