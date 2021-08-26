public class SelectionSort {
    public static void sort(int [] arr) {
        int minInd = 0;       //індекс мінімального елемента в невідсортованій частині масиву
        int sortInd = 0;      //індекс першого не відсортованого елемента
        for (int j = 0; j < arr.length; j++) {
            /* пошук мінімального елемента в невідсортований частині масиву
              цикл починається з першого невідсортованого елемента і продовжується до кінця масиву */
            for (int i = sortInd; i < arr.length; i++) {
                if (arr[i] < arr[minInd]) {
                    minInd = i;
                }
            }
            //мінімальний елемент міняється місцями з першим не відсортованим елементом
            swap(arr, sortInd, minInd);
            sortInd++;     //індекс до якого відсортовані елемети збільшується
            minInd = sortInd;    //перший невідсортований елемент стає новим мінімумом на новій частині масиву
        }
    }

    //метод для перестановки місцями елементів по їх індексам
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}