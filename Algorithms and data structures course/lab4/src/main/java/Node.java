public class Node {
    int data;
    Node next = null;
    Node prev = null;

    public Node() {}

    public Node(int data) {
        this.data = data;
    }

    public Node(Node node) {
        this.data = node.data;
        this.next = node.next;
        this.prev = node.prev;
    }

    public int getData() { return data; }
}
