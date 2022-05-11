package Parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class StoryParser {
    private static ArrayList<String> act1 = parseDatabase().get(0);
    private static ArrayList<String> act2 = parseDatabase().get(1);
    private static ArrayList<String> act3 = parseDatabase().get(2);


    public static ArrayList<ArrayList<String>> parseDatabase() {
        ArrayList<ArrayList<String>> allActs = new ArrayList<>();
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases/story.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\story.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        ArrayList<String> act1 = new ArrayList<>();
        inputStream.nextLine();
        inputStream.nextLine();
        outerLoop:
        while (inputStream.hasNextLine()) {
            String text = "";
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                if (line.equals("---")) {
                    break;
                }
                if (line.equals("-")) {
                    act1.add(text);
                    break outerLoop;
                }
                text = text + line;

            };
            act1.add(text);
        }

        ArrayList<String> act2 = new ArrayList<>();
        inputStream.nextLine();
        inputStream.nextLine();
        outerLoop:
        while (inputStream.hasNextLine()) {
            String text = "";
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                if (line.equals("---")) {
                    break;
                }
                if (line.equals("-")) {
                    act2.add(text);
                    break outerLoop;
                }
                text = text + line;
            };
            act2.add(text);
        }

        ArrayList<String> act3 = new ArrayList<>();
        inputStream.nextLine();
        inputStream.nextLine();
        outerLoop:
        while (inputStream.hasNextLine()) {
            String text = "";
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                if (line.equals("---")) {
                    break;
                }
                if (line.equals("-")) {
                    act3.add(text);
                    break outerLoop;
                }
                text = text + line;
            };
            act3.add(text);
        }

        allActs.add(act1);
        allActs.add(act2);
        allActs.add(act3);
        return allActs;
    }
}
