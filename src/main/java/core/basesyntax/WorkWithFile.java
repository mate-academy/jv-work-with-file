package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIAL_CHARACTERS = "[,\\s\\-:?]";
    private static final String COMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    private static void writeToFile(String fromFileName, String toFileName) {
        String result = getResult(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
        ) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file", e);
        }
    }

    private static String[] readFromFile(String fromFileName) {
        StringBuilder wordsInLowerCase = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                wordsInLowerCase.append(line.toLowerCase()).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return wordsInLowerCase.toString().split(SPECIAL_CHARACTERS);
    }

    private static String getResult(String fromFileName) {
        String[] allWords = readFromFile(fromFileName);
        StringBuilder statisticReport = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;

        for (int i = 0; i < allWords.length; i++) {
            String wordToCompare = allWords[i];
            if (wordToCompare.equals(SUPPLY)) {
                supplySum += Integer.parseInt(allWords[i + 1]);
            } else if (wordToCompare.equals(BUY)) {
                buySum += Integer.parseInt(allWords[i + 1]);
            }
        }
        statisticReport.append(SUPPLY).append(COMA).append(supplySum).append("\n");
        statisticReport.append(BUY).append(COMA).append(buySum).append("\n");
        return statisticReport.append("result").append(COMA).append(supplySum - buySum).toString();
    }
}
