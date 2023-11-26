package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        File fileToWrite = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int buyScore = 0;
        int supplyScore = 0;
        String infoTostring = readToFile(fileToRead, stringBuilder);
        buyScore = calculateCount(infoTostring, buyScore, BUY);
        supplyScore = calculateCount(infoTostring, supplyScore, SUPPLY);
        int resultScore = supplyScore - buyScore;
        writeToFile(fileToWrite, buyScore,supplyScore,resultScore);
    }

    private String readToFile(File fileToRead, StringBuilder builder) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(',');
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read data from file " + fileToRead, e);
        }
        return builder.toString();
    }

    private int calculateCount(String stringAllLines, int score, String subtraction) {
        String[] allStringsArray = stringAllLines.split(",");
        for (int i = 0; i < allStringsArray.length; i = i + 2) {
            if (allStringsArray[i].equals(BUY) && allStringsArray[i].equals(subtraction)) {
                score += Integer.parseInt(allStringsArray[i + 1]);
            }
            if (allStringsArray[i].equals(SUPPLY) && allStringsArray[i].equals(subtraction)) {
                score += Integer.parseInt(allStringsArray[i + 1]);
            }
        }
        return score;
    }

    private void writeToFile(File fileToWrite, int buyScore, int supplyScore, int resultScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(SUPPLY + "," + supplyScore + System.lineSeparator());
            writer.write(BUY + "," + buyScore + System.lineSeparator());
            writer.write(RESULT + "," + resultScore + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file " + fileToWrite, e);
        }
    }
}

