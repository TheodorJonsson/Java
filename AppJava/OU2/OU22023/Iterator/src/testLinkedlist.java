public class testLinkedlist {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        LinkedList.Position pos = list.first();
        for(int i = 0; i < 100; i++){
            pos = list.insert(pos, i);
            //list.next(pos);
        }


        for(Integer i : list){
            System.out.println(i);
        }
            
    }
}
