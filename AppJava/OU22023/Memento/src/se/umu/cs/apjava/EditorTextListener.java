import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditorTextListener implements ActionListener{
    private static Note text;
    private EditorTextView view;
    private DefaultListModel list;
    private int IDreal = 0;
    private int IDshown = 0;
    private NoteCareTaker careTaker;

    public EditorTextListener(Note text, EditorTextView view){
        this.view = view;
        this.text = text;
        careTaker = new NoteCareTaker();
        list = new DefaultListModel<String>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New Note")){
            list.add(IDreal, "Note " + IDshown);

            view.addToList(list);
            careTaker.createNewNote(text, IDreal);
            text.setID(IDreal);
            text.setText("");
            IDshown++;
            IDreal++;
            //System.out.println(list);

        }
        if(e.getActionCommand().equals("Save")){
            careTaker.saveMemento(text, text.getID());
        }
        if(e.getActionCommand().equals("Back")){
            if(careTaker.hasMemento(text.getID())){
                NoteMemento mem = careTaker.getMemento(text.getID());
                mem.setNewState(text);
            }
        }
        if(e.getActionCommand().equals("Select")){
            if(careTaker.hasNote()){
                int selectedID = view.getSelectedIndex();
                if(selectedID >= 0) {
                    //System.out.println("Finns memento");
                    NoteMemento mem = careTaker.getNote(selectedID);
                    mem.setNewState(text);
                }
            }
        }
        if(e.getActionCommand().equals("Remove")){
            if(careTaker.hasNote()){
                int selectedID = view.getSelectedIndex();
                if(selectedID >= 0){
                    list.remove(selectedID);
                    IDreal--;
                }
            }
        }

        if(e.getActionCommand().equals("Show all")){
            if(careTaker.hasMemento(text.getID())){
                List<NoteMemento> mements = careTaker.getMementos(text.getID());
                String text = new String();
                for(int i = 0; i < mements.size(); i++){
                    text = text + mements.get(i).getCurrText() + "\n";
                    System.out.println(text);
                }
                view.openMementoPane(text);
            }
        }
    }

}
