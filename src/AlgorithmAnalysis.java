import java.util.Arrays;
import java.util.Random;

public class AlgorithmAnalysis {

    // Bubble Sort
    public void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // Selection Sort
    public void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Merge Sort
    public void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = array[left + i];
        for (int j = 0; j < n2; j++) R[j] = array[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) array[k++] = L[i++];
            else array[k++] = R[j++];
        }
        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
    }

    // Linear Search
    public int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search
    public int binarySearch(int[] array, int key) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) return mid;
            if (array[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Method to measure the time for sorting and searching
    public void measureTime(int[] array, String algorithm) {
        int[] copy = Arrays.copyOf(array, array.length);  // Create a copy to avoid modifying original array
        long startTime, endTime, duration;

        switch (algorithm) {
            case "BubbleSort":
                startTime = System.nanoTime();
                bubbleSort(copy);
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Bubble Sort Time: " + duration + " ns");
                break;
            case "InsertionSort":
                startTime = System.nanoTime();
                insertionSort(copy);
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Insertion Sort Time: " + duration + " ns");
                break;
            case "SelectionSort":
                startTime = System.nanoTime();
                selectionSort(copy);
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Selection Sort Time: " + duration + " ns");
                break;
            case "MergeSort":
                startTime = System.nanoTime();
                mergeSort(copy, 0, copy.length - 1);
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Merge Sort Time: " + duration + " ns");
                break;
            case "LinearSearch":
                startTime = System.nanoTime();
                linearSearch(copy, -1);  // Searching for -1, which won't be found, for worst case
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Linear Search Time: " + duration + " ns");
                break;
            case "BinarySearch":
                Arrays.sort(copy);  // Binary Search requires sorted array
                startTime = System.nanoTime();
                binarySearch(copy, -1);  // Searching for -1, which won't be found, for worst case
                endTime = System.nanoTime();
                duration = endTime - startTime;
                System.out.println("Binary Search Time: " + duration + " ns");
                break;
        }
    }

    // Generate random array of given size
    public int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size);
        }
        return array;
    }

    public static void main(String[] args) {
        AlgorithmAnalysis analysis = new AlgorithmAnalysis();
        int[] sizes = {100, 500, 1000, 10000, 100000};  // Array sizes for testing
        String[] algorithms = {"BubbleSort", "InsertionSort", "SelectionSort", "MergeSort", "LinearSearch", "BinarySearch"};

        for (int size : sizes) {
            System.out.println("\nArray Size: " + size);
            int[] array = analysis.generateRandomArray(size);

            for (String algorithm : algorithms) {
                System.out.println("\nRunning " + algorithm + "...");
                analysis.measureTime(array, algorithm);
            }
        }
    }
}
