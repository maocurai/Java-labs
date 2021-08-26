public class Node {
    char data;
    Node next = null;

    public Node() {}

    public Node(char data) {
        this.data = data;
    }

    public Node(Node node) {
        this.data = node.data;
        this.next = node.next;
    }
}