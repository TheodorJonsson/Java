import java.util.Iterator;

public class testDoublelinkedlist {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        DoubleLinkedList.Position pos = list.first();
        for(int i = 0; i < 100; i++){
            list.insert(i, pos);
            list.next(pos);
        }


        for(Integer i : list){
            System.out.println(i);
        }
            
    }
}
