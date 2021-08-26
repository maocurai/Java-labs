public class MasStack {
    private char stack[];
    private int size;
    private int top;

    public MasStack(int size) {
        this.stack = new char[size];
        this.size = size;
        this.top = 0;
    }

    public void push(char ch) {
        if (top == size) {
            System.out.println("Stack is full");
        }
        stack[top]= ch;
        top++;
    }

    public char pop() {
        top --;
        return stack[top];
    }

    public char peek() {
        return stack[top-1];
    }

    public boolean isMoreVowels() {
        int countVovels = 0;
        int notLetters = 0;
        for(int i=0; i<size; i++) {
            if(Character.isLetter(stack[i])) {
                if (isVowel(stack[i])) {
                    countVovels++;
                }
            } else {
                notLetters++;
            }
        }
        return (countVovels > (top-countVovels-notLetters)) ? true : false;
    }

    public boolean isVowel(char ch) {
        return Character.toString(Character.toLowerCase(ch)).matches("[aeiou]");
    }

    public void showStack() {
        for(int i=top-1; i>=0; i--) {
            System.out.println(stack[i]);
        }
        System.out.println();
    }
}
