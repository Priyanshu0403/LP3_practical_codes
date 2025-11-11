import java.util.Random;

public class MiniProject {

    // Normal Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Multithreaded Merge Sort
    public static void parallelMergeSort(int[] arr, int left, int right, int depth) {
        if (left < right) {
            int mid = (left + right) / 2;

            if (depth <= 3) { // Limit recursion depth to avoid excessive threads
                Thread leftThread = new Thread(() -> parallelMergeSort(arr, left, mid, depth + 1));
                Thread rightThread = new Thread(() -> parallelMergeSort(arr, mid + 1, right, depth + 1));

                leftThread.start();
                rightThread.start();

                try {
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // beyond depth threshold, run sequentially
                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);
            }

            merge(arr, left, mid, right);
        }
    }

    // Merge two halves
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Generate random array
    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(100000);
        return arr;
    }

    // Copy array
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }

    public static void main(String[] args) {
        int n = 1000000; // 1 million elements
        int[] arr1 = generateArray(n);
        int[] arr2 = copyArray(arr1);

        System.out.println("Sorting " + n + " elements...");

        // Measure normal merge sort
        long start1 = System.currentTimeMillis();
        mergeSort(arr1, 0, arr1.length - 1);
        long end1 = System.currentTimeMillis();

        System.out.println("Normal Merge Sort Time: " + (end1 - start1) + " ms");

        // Measure parallel merge sort
        long start2 = System.currentTimeMillis();
        parallelMergeSort(arr2, 0, arr2.length - 1, 1);
        long end2 = System.currentTimeMillis();

        System.out.println("Multithreaded Merge Sort Time: " + (end2 - start2) + " ms");
    }
}
