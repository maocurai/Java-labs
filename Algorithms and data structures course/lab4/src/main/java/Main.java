public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for(int i=0; i<10; i++) {
            list.addLast(new Node(i));
        }
        list.addFirst(new Node(6));
        list.addFirst(new Node(7));
        list.addFirst(new Node(8));
        list.addFirst(new Node(9));
        list.addFirst(new Node(10));
        list.addLast(new Node(6));
        list.showList();
        list.findDeleteSwap(6);
        list.showList();
        list.findSequence(1, 5);
        list.findSequence(-1, 5);

        DoublyLinkedList list1 = new DoublyLinkedList();
        for(int i=0; i<10; i++) {
            list1.addLast(new Node(i));
        }
        list1.addFirst(new Node(4));
        list1.addLast(new Node(4));
        list1.showList();
        list1.findAndDelete(4);
        list1.showList();
    }
}
