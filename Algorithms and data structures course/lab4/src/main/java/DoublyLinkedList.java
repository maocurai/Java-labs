public class DoublyLinkedList {
    private Node head = null;
    private Node tail = null;

    public DoublyLinkedList() {}

    public DoublyLinkedList(Node node) {
        this.head = head;
        this.tail = head;
    }

    public Node getTail() {
        return tail;
    }

    public void findAndDelete(int value) {
        Node current = head;
        while(current != null) {
            if(current.data == value) {
                if(current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if(current.next != null) { current.next.prev = current.prev; }
            }
            current = current.next;
        }
    }

    public void addLast(Node node) {
        if(head == null) {
            this.head = node;
            this.tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void addFirst(Node node) {
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void addAt(Node node, int index) {
        Node current = getNodeAt(index);
        node.next = current;
        node.prev = current.prev;
        current.prev = node;
        node.prev.next = node;
    }

    public void showList() {
        Node last = head;
        while(last != null) {
            System.out.print(last.data + " ");
            last = last.next;
        }
        System.out.println();
    }

    public void deleteAt(int index) {
        if(index == 0) {
            deleteFirst();
            return;
        }
        Node current = getNodeAt(index);
        if(current.equals(tail)) {
            tail = current.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    public void swap(int index1, int index2) {
        if(index1 == index2) { return; }
        Node temp1 = new Node(getNodeAt(index1));
        Node temp2 = new Node(getNodeAt(index2));
        deleteAt(index1);
        addAt(temp2, index1);
        deleteAt(index2);
        addAt(temp1, index2);
    }

    public void deleteFirst() {
        head = head.next;
        head.next.prev = null;
    }

    public Node getNodeAt(int index) {
        Node current = head;
        int currInd = 0;
        while((currInd != index) && (current != null)) {
            current = current.next;
            if(current == null) {
                System.out.println("no such element!");
                return null;
            }
            currInd++;
        }
        return current;
    }
}
