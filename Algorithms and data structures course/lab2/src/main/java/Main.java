import java.util.Arrays;

public class Main {

    public static int [] generateMass(int size) {
        int [] mas = new int[size];
        for(int i=0; i<size; i++) {
            mas[i] = (int) (-100 + Math.random()*200);
        }
        return mas;
    }

    public static void reverseArray(int [] arr) {
        for(int i = 0; i < arr.length / 2 ; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
    }

    public static void main(String[] args) {
        int size = 20;
        int [] randMas = generateMass(size);
        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("HeapSort " + size + " elements random time : ");
        long time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("HeapSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        reverseArray(randMas);
        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("HeapSort " + size + " elements descending order time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        size = 5000;
        randMas = generateMass(size);
        System.out.print("HeapSort " + size + " elements random time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("HeapSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("HeapSort " + size + " elements descending order time : ");
        reverseArray(randMas);
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        size = 50000;
        randMas = generateMass(size);
        System.out.print("HeapSort " + size + " elements random time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("HeapSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("HeapSort " + size + " elements descending order time : ");
        reverseArray(randMas);
        time  = System.nanoTime();
        HeapSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        size = 20;
        randMas = generateMass(size);
        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("SelectionSort " + size + " elements random time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("SelectionSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        reverseArray(randMas);
        System.out.println("Start array: " + Arrays.toString(randMas));
        System.out.print("SelectionSort " + size + " elements descending order time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
        System.out.println("Sorted: " + Arrays.toString(randMas) + "\n");

        size = 5000;
        randMas = generateMass(size);
        System.out.print("SelectionSort " + size + " elements random time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("SelectionSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("SelectionSort " + size + " elements descending order time : ");
        reverseArray(randMas);
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        size = 50000;
        randMas = generateMass(size);
        System.out.print("SelectionSort " + size + " elements random time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("SelectionSort " + size + " elements ascending order time : ");
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);

        System.out.print("SelectionSort " + size + " elements descending order time : ");
        reverseArray(randMas);
        time = System.nanoTime();
        SelectionSort.sort(randMas);
        System.out.println((System.nanoTime() - time)/ 1000000.0);
    }
}