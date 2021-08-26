//7. Організувати програмно хешування 100 записів (вузлів) в таблицю,
//що складається з 20 посилань на однозв'язні лінійні списки (стеки),
//спочатку порожні. Записи мають невід'ємні цілі ключі k<50, що формуються випадковим чином.
//Хеш-функцію організувати шляхом множення як окремий метод.

public class Main {
    public static void main(String[] args) {
        HashTable ob = new HashTable(20);
//        System.out.println(ob.getHash(9));
//        System.out.println(ob.getHash(26));
        ob.add(101, 99);
        System.out.println(ob.get(101));
        System.out.println(ob.getHash(101));
        for(int i=0; i<100; i++) {
            ob.add(i, i+10);
        }
        System.out.println(ob.get(101));
        System.out.println(ob.getHash(101));
        ob.showTable();
    }
}
