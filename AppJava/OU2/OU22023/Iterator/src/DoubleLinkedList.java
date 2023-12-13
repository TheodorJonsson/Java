import java.util.Iterator;

/**
 * Implementation of a double linked list
 * @param The type of the elements in the list
 * @author Johan Eliasson johane@cs.umu.se
 * @version 1
 */
public class DoubleLinkedList<T> implements Iterable<T>{

    private final Cell head;

    /**
     * Creates an empty DoubleLinkedList
     */
    public DoubleLinkedList() {
        head = new Cell(null, null, null);
        head.nextel = head;
        head.prevel = head;
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, else false
     */
    public boolean isEmpty() {
        return (head.nextel == head);
    }

    /**
     * Inserts val before the position p in the list.
     * @param val the value to insert
     * @param p the position which the value should be inserted before
     * @return The position of the inserted element
     */
    public Position insert(T val, Position p) {
        Cell el;
        if (isEmpty()) {
            el = new Cell(val, head, head);
            head.nextel = el;
            head.prevel = el;
        } else {
            var cell=(Cell) p;
            el = new Cell(val, cell.prevel, cell);
            cell.prevel = el;
            el.prevel.nextel = el;
        }
        return el;
    }

    /**
     * Returns the value in position p in the list.
     * @param p the position in the list to inspect
     */
    public T inspect(Position p) {
        return ((Cell) p).value;
    }

    /**
     * Returns the position of the first value in the list.
     */
    public Position first() {
        return head.nextel;
    }

    /**
     * Returns the position after the last value in the list.
     */
    public Position end() {
        return head;
    }

    /**
     * Returnerar positionen på position p efterföljare i listan.
     * @param p
     */
    public Position next(Position p) {
        return ((Cell) p).nextel;
    }

    /**
     * Returnerar positionen på position p föregående i listan.
     * @param p
     */
    public Position previous(Position p) {
        return ((Cell) p).prevel;
    }

    /**
     * Removes the Position p from the list.
     * @param p
     * @return the position of the element that was previously after the removed element
     */
    public Position remove(Position p) {
        Cell cell=(Cell) p;
        cell.prevel.nextel = cell.nextel;
        cell.nextel.prevel = cell.prevel;
        return cell.nextel;
    }

    public interface Position {
    }

    private class Cell implements Position {
        protected T value;
        protected Cell nextel;
        protected Cell prevel;

        /**
         * creates a Cell with value värde v samt pekare prevel och nextel
         * @param v the value in the cell
         * @param prevel the cell that should be before this
         * @param nextel the cell that should be after this
         */
        public Cell(T v, Cell prevel, Cell nextel) {
            value = v;
            this.nextel = nextel;
            this.prevel = prevel;
        }
    }

    @Override
    public Iterator iterator() {
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements Iterator<T> {
        DoubleLinkedList list = DoubleLinkedList.this;
        Position pos = list.first();

        @Override
        public boolean hasNext() {
            Position checkEnd = list.end();
            if(list.next(pos).equals(checkEnd)){
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            pos = list.next(pos);
            return (T) list.inspect(pos);
        }
    }
}
