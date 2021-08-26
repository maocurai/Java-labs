public class ListStack {
    Node top = null;

    public ListStack() {}

    public ListStack(Node node) {
        this.top = node;
    }

    public void push(Node node) {
        if (top == null) {
            this.top = node;
        } else {
            Node last = top;
            while(last.next != null) {
                last = last.next;
            }
            last.next = node;
            node.next = null;
        }
    }

    public char pop() {
        Node prev = top;
        Node res = top;
        while(res.next != null) {
            prev = res;
            res = res.next;
        }
        prev.next = null;
        return res.data;
    }

    public char peek() {
        Node res = top;
        while(res.next != null) {
            res = res.next;
        }
        return res.data;
    }

    public int getSize() {
        int res = 0;
        Node temp = top;
        while(temp != null) {
            res++;
            temp = temp.next;
        }
        return res;
    }

    public boolean isMoreVowels() {
        int countVovels = 0;
        int notLetters = 0;
        Node temp = top;
        while(temp != null) {
            if(Character.isLetter(temp.data)) {
                if (isVowel(temp.data)) {
                    countVovels++;
                }
            } else {
                notLetters++;
            }
            temp = temp.next;
        }
        return (countVovels > (getSize()-countVovels-notLetters)) ? true : false;
    }

    public boolean isVowel(char ch) {
        return Character.toString(Character.toLowerCase(ch)).matches("[aeiou]");
    }

    public void showStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }
}

