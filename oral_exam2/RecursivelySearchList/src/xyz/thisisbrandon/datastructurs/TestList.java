package xyz.thisisbrandon.datastructurs;

import xyz.thisisbrandon.datastructurs.List;

import java.util.Random;
import java.util.Scanner;

/**
 * Main class designed for users to test the List class' search method.
 */
public class TestList {

    /**
     * Main method, creates console interface for testing list class search method. Prompts user
     * for a list size, then generates this random list and the user can input numbers to search
     * for. Results of search are outputted.
     * @param args  inputted args on run.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Select list size: ");
        int size = input.nextInt();
        List<Integer> linkedList = generateRandomList(size, 100);
        linkedList.print();

        while (true) {
            System.out.print("Input a number to search for: ");
            int searchVal = input.nextInt();

            Integer result = linkedList.search(searchVal);

            if (result != null) {
                System.out.println("result: " + result.toString());
            } else {
                System.out.println("result: null");
            }
        }
    }

    /**
     * Generates a random integer list of size where each number is no larger than
     * upperbound.
     * @param size          Size of the linked list generated
     * @param upperBound    Max number randomly generated
     * @return              List object containing specified number of elements.
     */
    public static List<Integer> generateRandomList(int size, int upperBound) {
        Random rand = new Random();
        List<Integer> randomList = new List<Integer>();

        for (int i = 0; i<size; i++) {
            int nextNumber = rand.nextInt(upperBound);
            while (randomList.search(nextNumber) != null) {
                nextNumber = rand.nextInt(upperBound);
            }
            randomList.insertAtBack(nextNumber);
        }

        return randomList;
    }

}
