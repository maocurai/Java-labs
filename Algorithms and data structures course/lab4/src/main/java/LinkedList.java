public class LinkedList {
    Node head = null;

    public LinkedList() {}

    public LinkedList(Node head) {
        this.head = head;
    }

    public void addLast(Node node) {
        if(head == null) {
            this.head = node;
        } else {
            Node last = head;
            while(last.next != null) {
                last = last.next;
            }
            last.next = node;
            node.next = null;
        }
    }

    public void addAt(Node node, int index) {
        Node current = getPtrAt(index);
        node.next = current.next;
        current.next = node;
    }

    public void addFirst(Node node) {
        node.next = head;
        head = node;
    }

    public void deleteLast() {
        Node curr = head;
        while(curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
    }

    public void deleteAt(int index) {
        if(index == 0) {
            deleteFirst();
            return;
        }
        Node current = getPtrAt(index);
        current.next = current.next.next;
    }

    public void deleteFirst() {
        head = head.next;
    }

    public void findDeleteSwap(int value) {
        Node current = head;
        int index = 0;
        while(current != null) {
            if(current.data == value) {
                deleteAt(index);
                if((current.next != null) && (current.next.next != null)) {
                    swap(index, index + 1);
                }
            }
            current = current.next;
            index++;
        }
    }

    public void showList() {
        Node last = head;
        while(last != null) {
            System.out.print(last.data + " ");
            last = last.next;
        }
        System.out.println();
    }

    public boolean findSequence(int order, int num) {
        boolean res = false;
        Node first = head;
        Node second = head.next;
        Node start = null;
        Node end = null;
        int count = 0;
        while(first.next != null) {
            boolean isSeq = false;
            if(order == 1) {
                if (first.data < second.data) {
                    isSeq = true;
                }
            }
            if(order == -1) {
                if (first.data > second.data) {
                    isSeq = true;
                }
            }
            if (isSeq) {
                start = (count == 0) ? first : start;
                count++;
                end = (count == num - 1) ? second : end;
                if (count == num - 1) {
                    res = true;
                    break;
                }
            } else {
                count = 0;
            }
            first = first.next;
            second = second.next;
        }
        if(res) {
            System.out.println("Sequence found!");
            showInterval(start, end);
        } else {
            System.out.println("No sequence found!");
        }
        return res;
    }

    public void showInterval(Node start, Node end) {
        while(start != end.next) {
            System.out.print(start.data + " ");
            start = start.next;
        }
        System.out.printf("\n\n");
    }

    public void swap(int index1, int index2) {
        if(index1 == index2) { return; }
        if(index1 == 0 || index2 ==0 ) {
            int notZero = (index1 == 0) ? index2 : index1;
            Node node = getPtrAt(notZero);
            Node temp = head;
            head = node.next;
            node.next = temp;
            temp = head.next;
            head.next = node.next.next;
            node.next.next = temp;
            return;
        }
        Node nodeInd1 = getPtrAt(index1);
        Node nodeInd2 = getPtrAt(index2);
        Node temp = nodeInd1.next;
        nodeInd1.next = nodeInd2.next;
        nodeInd2.next = temp;
        temp = nodeInd1.next.next;
        nodeInd1.next.next = nodeInd2.next.next;
        nodeInd2.next.next = temp;
    }

    public Node getPtrAt(int index) {
        Node current = head;
        int currInd = 0;
        if(index == 0) { return head; }
        while((currInd != index-1) && (current != null)) {
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