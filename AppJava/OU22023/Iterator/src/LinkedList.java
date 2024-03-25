import java.util.Iterator;

/**
 * Implementation of a  linked list
 * @param  <T> The type of the elements in the list
 * @author Johan Eliasson johane@cs.umu.se
 * @version 1
 */
public class LinkedList<T> implements Iterable<T>{

    public interface Position {
    }

    private Cell<T> head;

    public LinkedList() {
        head=new Cell<T>();
    }

    /**
     * Returns the position of the first value in the list.
     */
    public Position first() {
        return head;
    }

    /**Check if position p is the position after the last */
    public boolean isEnd(Position p) {
        return ((Cell<T>)p).next==null;
    }


    /**
     * Returns the following position in the list.
     * @param p the position who's following position you want to find.
     */
    @SuppressWarnings("unchecked")
    public Position next(Position p) {
        return ((Cell<T>)p).next;
    }

    /**
     * Returns the value in position p in the list.
     * @param p the position in the list to inspect
     */
    @SuppressWarnings("unchecked")
    public T inspect(Position p) {
        return ((Cell<T>)p).next.data;
    }

    /**
     * Removes the Position p from the list.
     * @param p
     * @return the position of the element that was previously after the removed element
     */
    @SuppressWarnings("unchecked")
    public Position remove(Position p) {
        Cell c=(Cell<T>) p;
        c.next=c.next.next;
        return c;
    }

    /**
     * Inserts val before the position p in the list.
     * @param data the value to insert
     * @param p the position which the value should be inserted before
     * @return The position of the inserted element
     */
    public Position insert(Position p,T data) {
        Cell<T> c=(Cell<T>) p;
        Cell<T> newPos=new Cell<T>(c.next,data);
        c.next=newPos;
        return newPos;
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, else false
     */
    public boolean isEmpty() {
        return head.next==null;
    }

    private class Cell <Data> implements Position {

        private Cell<Data> next;
        private Data data;

        public Cell(){

        }

        public Cell(Cell<Data> next,Data data) {
            this.data=data;
            this.next=next;
        }

        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            Cell<Data> c=(Cell<Data>) o;
            return c.next==next;
        }
    }
    @Override
    public Iterator iterator() {
        return new LinkedList.LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        LinkedList<T> list = LinkedList.this;
        LinkedList.Position pos = list.first();

        @Override
        public boolean hasNext() {
            //DoubleLinkedList.Position checkEnd = list.end();
            if(list.isEnd(pos)){
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T object = list.inspect(pos);
            pos = list.next(pos);
            return object;
        }
    }
}
