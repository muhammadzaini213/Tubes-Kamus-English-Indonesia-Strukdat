package com.kamus.gimmick;

import com.kamus.gimmick.dictionary.EnglishDictionary;
import com.kamus.gimmick.dictionary.IndonesianDictionary;

import java.util.Scanner;

public class MainCLI {
    private static final String dataDir = "src/main/resources/dataset/data.csv";
    private static EnglishDictionary englishDict;
    private static IndonesianDictionary indonesianDict;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        System.out.println("=============================================");
        System.out.println("   Dictionary CLI - English <-> Indonesian");
        System.out.println("=============================================\n");

        // Load both dictionaries at startup
        loadDictionaries();

        // Main menu loop
        boolean running = true;
        while (running) {
            running = showMainMenu();
        }

        scanner.close();
        System.out.println("\nBye.");
    }

    private static void loadDictionaries() {
        System.out.println("Loading dictionaries from: " + dataDir);

        try {
            long startTime = System.nanoTime();

            englishDict = new EnglishDictionary();
            long englishStart = System.nanoTime();
            boolean englishLoaded = englishDict.loadAllData(dataDir);
            long englishEnd = System.nanoTime();

            indonesianDict = new IndonesianDictionary();
            long indonesianStart = System.nanoTime();
            boolean indonesianLoaded = indonesianDict.loadAllData(dataDir);
            long indonesianEnd = System.nanoTime();

            long totalTime = System.nanoTime() - startTime;

            if (englishLoaded && indonesianLoaded) {
                System.out.println("✓ English dictionary loaded: " + englishDict.getSize() + " entries " +
                        "(" + formatTime(englishEnd - englishStart) + ")");
                System.out.println("✓ Indonesian dictionary loaded: " + indonesianDict.getSize() + " entries " +
                        "(" + formatTime(indonesianEnd - indonesianStart) + ")");
                System.out.println("Total loading time: " + formatTime(totalTime));
                System.out.println();
            } else {
                System.out.println("⚠ Warning: Some dictionaries failed to load properly\n");
            }
        } catch (Exception e) {
            System.err.println("Error loading dictionaries: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean showMainMenu() {
        try {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Search for a word");
            System.out.println("2. Display tree structure");
            System.out.println("3. Display all words (in-order)");
            System.out.println("4. Show dictionary statistics");
            System.out.println("0. Exit");
            System.out.print("\nChoose an option (0-4): ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    searchWord();
                    break;
                case 2:
                    displayTreeStructure();
                    break;
                case 3:
                    displayInOrder();
                    break;
                case 4:
                    showStatistics();
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private static void searchWord() {
        try {
            System.out.println("\n========== SEARCH ==========");
            System.out.println("1. English → Indonesian");
            System.out.println("2. Indonesian → English");
            System.out.print("\nChoose dictionary (1-2): ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter word to search: ");
            String word = scanner.nextLine().trim();

            if (word.isEmpty()) {
                System.out.println("⚠ Word cannot be empty");
                return;
            }

            String result;
            String direction;

            long startTime = System.nanoTime();

            if (choice == 1) {
                result = englishDict.find(word);
                direction = "English → Indonesian";
            } else if (choice == 2) {
                result = indonesianDict.find(word);
                direction = "Indonesian → English";
            } else {
                System.out.println("Invalid dictionary choice");
                return;
            }

            long endTime = System.nanoTime();

            System.out.println("\n--- Result (" + direction + ") ---");
            if (result != null) {
                System.out.println("\"" + word + "\" = \"" + result + "\"");
            } else {
                System.out.println("✗ Word \"" + word + "\" not found");
            }
            System.out.println("Search time: " + formatTime(endTime - startTime));

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error during search: " + e.getMessage());
        }
    }

    private static void displayTreeStructure() {
        try {
            System.out.println("\n========== DISPLAY TREE STRUCTURE ==========");
            System.out.println("1. English dictionary tree");
            System.out.println("2. Indonesian dictionary tree");
            System.out.print("\nChoose dictionary (1-2): ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            long startTime = System.nanoTime();

            System.out.println();
            if (choice == 1) {
                System.out.println("--- English Dictionary Tree ---");
                System.out.println("(Red = Red nodes, White = Black nodes)\n");
                englishDict.printTree();
            } else if (choice == 2) {
                System.out.println("--- Indonesian Dictionary Tree ---");
                System.out.println("(Red = Red nodes, White = Black nodes)\n");
                indonesianDict.printTree();
            } else {
                System.out.println("Invalid dictionary choice");
                return;
            }

            long endTime = System.nanoTime();
            System.out.println("\nPrint time: " + formatTime(endTime - startTime));

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error displaying tree: " + e.getMessage());
        }
    }

    private static void displayInOrder() {
        try {
            System.out.println("\n========== DISPLAY IN-ORDER ==========");
            System.out.println("1. English dictionary");
            System.out.println("2. Indonesian dictionary");
            System.out.print("\nChoose dictionary (1-2): ");

            int dictChoice = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("\n--- Display Options ---");
            System.out.println("1. Show keys only");
            System.out.println("2. Show keys with values");
            System.out.print("\nChoose option (1-2): ");

            int displayChoice = Integer.parseInt(scanner.nextLine().trim());
            boolean showValues = (displayChoice == 2);

            long startTime = System.nanoTime();

            System.out.println();
            if (dictChoice == 1) {
                System.out.println("--- English Dictionary (In-Order) ---");
                englishDict.printInOrder(englishDict.getRoot(), showValues);
            } else if (dictChoice == 2) {
                System.out.println("--- Indonesian Dictionary (In-Order) ---");
                indonesianDict.printInOrder(indonesianDict.getRoot(), showValues);
            } else {
                System.out.println("Invalid dictionary choice");
                return;
            }

            long endTime = System.nanoTime();
            System.out.println("\nPrint time: " + formatTime(endTime - startTime));

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error displaying in-order: " + e.getMessage());
        }
    }

    private static void showStatistics() {
        try {
            System.out.println("\n========== DICTIONARY STATISTICS ==========");
            System.out.println("English Dictionary:");
            System.out.println("  - Total entries: " + englishDict.getSize());
            System.out.println("  - Root node: " + (englishDict.getRoot() != null ? englishDict.getRoot().getKey() : "null"));

            System.out.println("\nIndonesian Dictionary:");
            System.out.println("  - Total entries: " + indonesianDict.getSize());
            System.out.println("  - Root node: " + (indonesianDict.getRoot() != null ? indonesianDict.getRoot().getKey() : "null"));

        } catch (Exception e) {
            System.out.println("Error showing statistics: " + e.getMessage());
        }
    }

    private static String formatTime(long nanoTime) {
        if (nanoTime < 1_000) {
            return nanoTime + " ns";
        } else if (nanoTime < 1_000_000) {
            return String.format("%.2f μs", nanoTime / 1_000.0);
        } else if (nanoTime < 1_000_000_000) {
            return String.format("%.2f ms", nanoTime / 1_000_000.0);
        } else {
            return String.format("%.2f s", nanoTime / 1_000_000_000.0);
        }
    }
}
