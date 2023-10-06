package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWork {
    public static String[] readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
            String fileContent = contentBuilder.toString().trim();
            return filterWordsStartingWithW(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static String[] filterWordsStartingWithW(String text) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        Matcher matcher = pattern.matcher(text.toLowerCase());

        List<String> wordsStartingWithW = new ArrayList<>();

        while (matcher.find()) {
            String word = matcher.group();

            if (word.startsWith("w")) {
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                wordsStartingWithW.add(word);
            }
        }

        Collator collator = null;
        try {
            collator = new RuleBasedCollator(
                    "< ' ' < '-' < '0' < '1' < '2' < '3' < '4' < '5' < '6' < '7' < '8' < '9' < "
                            + "'a' < 'b' < 'c' < 'd' < 'e' < 'f' < 'g' < 'h' < 'i' < 'j' < 'k' < " 
                            + " 'l' < 'm' < 'n' < 'o' < 'p' < 'q' < 'r' < 's' < 't' < 'u' < 'v' < "
                            + "'w' < 'x' < 'y' < 'z'");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        wordsStartingWithW.sort(collator);

        String[] result = new String[wordsStartingWithW.size()];
        return wordsStartingWithW.toArray(result);
    }
}
