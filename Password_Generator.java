package org.jsp.passworddemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Password_Generator {
    public static void main(String[] args) {

        String selection = "";
        boolean running = true;

        Scanner in = new Scanner(System.in);

        System.out.printf("Hello, and Welcome : \n\n");

        System.out.println("\nWould you like a complex or simple  password?");
        System.out.println("Please type \"complex\" for a complex password, or \"simple\" for a simple password, or press \"Q\" to quit.");

        while (running) {
            if (in.hasNext()) {
                selection = in.next();
                if (selection.equalsIgnoreCase("complex")) {
                    System.out.println("Complex password selected.");
                    genComplex();
                } else if (selection.equalsIgnoreCase("simple")) {
                    System.out.println("Simple password selected");
                    genSimple();
                } else if (selection.equalsIgnoreCase("q")) {
                    System.out.println("Exiting Program.");
                    running = false;
                } else {
                    System.out.println("Unknown Input. Please put in a correct password type, either \"complex\" or \"simple\". ");
                }
            }
        }
    }

    private static void genComplex() {
 
        StringBuilder finalComplexPassword = new StringBuilder();
        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        int charSelector; 
        int numSelector; 
        int specialSelector; 
        int upperOrLower; 
        int alphabetSelector;

        final String[] CHARS_UPPER = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }; // 0 -> 25
        final String[] CHARS_LOWER = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }; // 0 -> 25
        final String[] NUMBERS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" }; // 0 -> 19
        final String[] SPECIAL_CHARS = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+" }; // 0 -> 25

        for (int i = 0; i < 12; i++) {
            charSelector = 1 + rand.nextInt(3);
            numSelector  = rand.nextInt(20);
            specialSelector = rand.nextInt(26);
            upperOrLower = rand.nextInt(2); 
            alphabetSelector = rand.nextInt(26); 
            if (charSelector == 1) {
                if (upperOrLower == 0) {
                    finalComplexPassword.append(CHARS_LOWER[alphabetSelector]);
                } else {
                    finalComplexPassword.append(CHARS_UPPER[alphabetSelector]);
                }
            } else if (charSelector == 2) {
                finalComplexPassword.append(NUMBERS[numSelector]);
            } else if (charSelector == 3) {
                finalComplexPassword.append(SPECIAL_CHARS[specialSelector]);
            }
        }

        System.out.println(finalComplexPassword);
    }
    private static void genSimple() {

        StringBuilder finalSimplePassword = new StringBuilder();
        String separator = " ";
        boolean running = true;
        boolean running2 = true;
        int dictSize;
        int wordNum;
        String selection;
        int numOfWords = 0;

        Random rand = new Random();
        Scanner foot = new Scanner(System.in);
        ArrayList<String> dict = readDict();

        if (dict == null) {
            System.err.print("Could not find wordlist, exiting.");
            System.exit(0);
        }

        dictSize = dict.size(); 

        System.out.println("Would you like a separated password, or all together.");
        System.out.println("Please enter \"separated\" for separated, or \"together\" for all together.");
        System.out.println("Pressing \"Q\" will default to 3 words and separated.");

        while (running) {
            if (foot.hasNext()) {
                selection = foot.next();
                if (selection.equalsIgnoreCase("separated")) {
                    System.out.println("How many words? Lower limit is \"2\" and upper limit is \"5\"");
                    while (running2) {
                        if (foot.hasNext()) {
                            String result = foot.next();
                            if (result.equalsIgnoreCase("q")) {
                                running2 = false;
                                running = false;
                                System.out.println("Quitting, returning to main menu.");
                                break;
                            }
                            numOfWords = Integer.valueOf(result);
                            if (numOfWords <= 1 || numOfWords >= 6) {
                                System.out.println("Too few or too many words. Please enter a number between 2 and 5.");
                            } else if (numOfWords >= 3 || numOfWords <= 7) {
                                for (int i = 0; i < numOfWords; i++) {
                                    wordNum = rand.nextInt(dictSize);
                                    finalSimplePassword.append(dict.get(wordNum) + separator);
                                }
                                System.out.println(finalSimplePassword);
                                finalSimplePassword = new StringBuilder();
                            } else {
                                System.out.println("Please enter a valid response.");
                            }
                        }
                    }
                } else if (selection.equalsIgnoreCase("together")) {
                    System.out.println("How many words? Lower limit is \"2\" and upper limit is \"5\"");
                    while (running2) {
                        if (foot.hasNext()) {
                            String result = foot.next();
                            if (result.equalsIgnoreCase("q")) {
                                running2 = false;
                                running = false;
                                System.out.println("Quitting, returning to main menu.");
                                break;
                            }
                            numOfWords = Integer.valueOf(result);
                            if (numOfWords <= 1 || numOfWords >= 6) {

                                System.out.println("Too few or too many words. Please enter a number between 2 and 5.");
                            } else if (numOfWords >= 3 || numOfWords <= 7) {
                                for (int i = 0; i < numOfWords; i++) {
                                    wordNum = rand.nextInt(dictSize);
                                    finalSimplePassword.append(dict.get(wordNum));
                                }
                                System.out.println(finalSimplePassword);
                                finalSimplePassword = new StringBuilder();
                            } else {
                                System.out.println("Please enter a valid response.");
                            }
                        }
                    }
                } else if (selection.equalsIgnoreCase("q")) {
                    for (int i = 0; i < 3; i++) {
                        wordNum = rand.nextInt(dictSize);
                        finalSimplePassword.append(dict.get(wordNum) + separator);
                    }
                    System.out.println(finalSimplePassword);
                    System.out.println("Returning to main menu");
                    running = false;
                    running2 = false;
                } else {
                    System.out.println("Please enter a valid response.");
                }

            }
        }
    }
    private static ArrayList<String> readDict() {
        ArrayList<String> dict = new ArrayList();

        try {
            Scanner reader = new Scanner(new File("wordlist.txt"));

            while (reader.hasNext()) {
                dict.add(reader.nextLine());
            }

            return dict;
        } catch (Exception e) {
            System.out.printf("Could not find file\n");
        }
        return null;
    }
}
