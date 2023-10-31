//import required tools/packages
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.io.*;

public class Assignment9 {

    //Generate array of random integers (0-100)(USING GENERICS)
    public static <T extends Comparable<T>> ArrayList<T> spawnRandomArrayList(int arrayLength, Class<T> clazz) {
        ArrayList<T> randomArrayList = new ArrayList<>();
        Random rand = new Random(); //random function


        try{
            for (int i = 0; i < arrayLength; i++) {
                T element = clazz.getDeclaredConstructor(int.class).newInstance(rand.nextInt(101));
                randomArrayList.add(element); 

            }
        
        }catch (Exception e) {
            e.printStackTrace();
        }
        return randomArrayList; //returns random array

    }

    //write int array to file one at a time
    public static void arrayToFile(int[] array, String arrayfile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arrayfile))) {
            for (int number : array) {
                writer.println(number);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

            
    }

    //read integers from file
    public static int[] readFileArray(String arrayfile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arrayfile))){
        String line;
        int[] array = new int[0];
        int index = 0;

        while ((line = reader.readLine()) != null) {
            int number = Integer.parseInt(line);

            if (index >= array.length) {
                int[] newArray = new int[array.length +1];
                System.arraycopy(array, 0, newArray, 0, array.length);
                array = newArray;
            }

            array[index] = number; //store read integer in array
            index++;
            }
        
            return array;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    
    }
    
    
    //bubble sort (USING GENERICS)
    public static <T extends Comparable<T>> void bubbleSort(ArrayList<Integer> list) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i< n;i++) {
                if (list.get(i-1).compareTo(list.get(i)) > 0) {

                    //swap array[i-1] and array[i]
                    Collections.swap(list, i-1, i);
                    swapped = true;
                }
            }
            n--;
        }while (swapped);
    }

    //MAIN FUNCTION
    public static void main(String[] args){
        int arrayLength = 100;

        //random array list
        ArrayList<Integer> randArrayList = spawnRandomArrayList(arrayLength, Integer.class);

        //bubble sort copy
        ArrayList<Integer> bubArrayList = new ArrayList<>(randArrayList);

        //execution time (bubble)
        long bubbleSortStartTime = System.currentTimeMillis();
        bubbleSort(bubArrayList);
        long bubbleSortEndTime = System.currentTimeMillis();
        long bubbleSortExecuteTime = bubbleSortEndTime - bubbleSortStartTime;

        //print results for bubble
        System.out.println("Sorted ArrayList using BubbleSort: ");
        for (int number : bubArrayList){
            System.out.println(number + " ");
        }
        System.out.println();
        System.out.println("BubbleSort Execution time: " + bubbleSortExecuteTime);
        System.out.println();

        //create a merge sort copy
        ArrayList<Integer> mergSoArrayList = new ArrayList<>(randArrayList);

        //execution time (merge)
        long mergeSortStartTime = System.currentTimeMillis();
        MergeSort.mergesort(mergSoArrayList);
        long mergeSortEndTime = System.currentTimeMillis();
        long mergeSortExecuteTime = mergeSortEndTime - mergeSortStartTime;


        //print results for merge
        System.out.println("Sorted ArrayList using MergeSort: ");
        for (int number : mergSoArrayList){
            System.out.println(number + " ");
        }
        System.out.println();
        System.out.println("MergeSort Execution time: " + mergeSortExecuteTime);

    

}
}

//MERGE SORT FUNCTION (GENERICS)
class MergeSort {
    public static <T extends Comparable<T>> void mergesort(ArrayList<Integer> list){
        if (list.size() > 1) {
            int mid = list.size()/2;
            List<Integer> left = list.subList(0,mid);
            List<Integer> right = list.subList(mid, list.size());


            mergesort(new ArrayList<>(left));
            mergesort(new ArrayList<>(right));

            MergeArrayBack(list, left, right);
        }
        
    }

    public static void MergeArrayBack(ArrayList<Integer> list, List<Integer> left, List<Integer> right){
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                list.set(k++, left.get(i++));
            }
            else {
                list.set(k++, right.get(i++));
            }

        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }

    }
}