//Завдання
//Реалізувати стек на основі масиву, виконати завдання.
//Потім реалізувати стек на основі зв'язаного списку і теж виконати завдання.
//Варіант 17
//Створити стек символів. Якщо у стеці більше голосних літер - новий стек заповнити 10 одиницями,
//якщо ж більше голосних - новий стек заповнити двійками.


public class Main {
    public static void main(String[] args) {
        MasStack ob = new MasStack(10);
        ob.push('o');
        ob.push('h');
        ob.push('a');
        ob.push('y');
        ob.push('o');
        ob.showStack();
        MasStack ob1 = new MasStack(10);
        if(ob.isMoreVowels()) {
            for(int i=0; i<10; i++) {
                ob1.push('1');
            }
        } else {
            for(int i=0; i<10; i++) {
                ob1.push('2');
            }
        }
        ob1.showStack();

        ListStack ob2 = new ListStack();
        ob2.push(new Node('h'));
        ob2.push(new Node('a'));
        ob2.push(new Node('l'));
        ob2.push(new Node('l'));
        ob2.push(new Node('o'));
        ob2.showStack();
        ListStack ob3 = new ListStack();
        if(ob2.isMoreVowels()) {
            for(int i=0; i<10; i++) {
                ob3.push(new Node('1'));
            }
        } else {
            for(int i=0; i<10; i++) {
                ob3.push(new Node('2'));
            }
        }
        ob3.showStack();
    }
}
