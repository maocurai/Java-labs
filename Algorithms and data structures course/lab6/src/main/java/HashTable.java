import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Double.NaN;

public class HashTable {
    int count = 0;
    int size;
    private ArrayList<Stack<HashNode>> table;

    public HashTable(int size) {
        this.size = size;
        this.table = new ArrayList<Stack<HashNode>>(size);
        for(int i=0; i<size; i++) {
            this.table.add(new Stack<HashNode>());
        }
    }

    public void setTable(ArrayList<Stack<HashNode>> table) {
        this.table = table;
    }

    public ArrayList<Stack<HashNode>> getTable() {
        return table;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHash(double key) {
        double Fk = (Math.sqrt(5)-1)*key;
        return Math.abs((int)(size*(Fk - (int)Fk)));
    }

    public int calculateOccupancyRate() {
        return (int)(count/(size));
    }

    public boolean isKey(double key) {
        for(Stack<HashNode> st : this.table) {
            for(HashNode hn : st) {
                if(hn.getKey() == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(double key, double value) {
        if(calculateOccupancyRate() < 4) {
            if(!isKey(key)) {
                table.get(getHash(key)).add(new HashNode(key, value));
                count++;
            } else {
                System.out.println("Key already in the table");
            }
        } else {
            this.resize(size*2);
            add(key, value);
        }
    }

    public double get(double key) {
        if(isKey(key)) {
            for(HashNode hn : table.get(getHash(key))) {
                if(hn.getKey() == key) {
                    return hn.getValue();
                }
            }
        }
        System.out.println("No such key!");
        return NaN;
    }

    public boolean delete(double key) {
        if(isKey(key)) {
            int ind = 0;
            int hash = getHash(key);
            for(HashNode hn : table.get(hash)) {
                if(hn.getKey() == key) {
                    table.get(hash).remove(ind);
                    return true;
                }
                ind++;
            }
        }
        System.out.println("No such key!");
        return false;
    }

    public void showTable() {
        int ind = 0;
        for(Stack<HashNode> st : this.table) {
            System.out.println("Hash: " + ind);
            for(HashNode hn : st) {
                System.out.println("Key: " + hn.getKey() + " value: " + hn.getValue());
            }
            ind++;
        }
    }

    public void resize(int newSize) {
        System.out.println("resizing! count: " + count);
        HashTable newTable = new HashTable(newSize);
        for(Stack<HashNode> st : this.table) {
            for(HashNode hn : st) {
                newTable.add(hn.getKey(), hn.getValue());
            }
        }
        this.setTable(newTable.getTable());
        this.setSize(newSize);
    }

}