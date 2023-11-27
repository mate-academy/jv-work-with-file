package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(',');
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read data from file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String dataFromFile) {
        int buyScore = 0;
        int supplyScore = 0;
        int resultScore = 0;
        buyScore = calculateCount(dataFromFile, buyScore, BUY);
        supplyScore = calculateCount(dataFromFile, supplyScore, SUPPLY);
        resultScore = supplyScore - buyScore;
        return SUPPLY + "," + supplyScore + System.lineSeparator()
                + BUY + "," + buyScore + System.lineSeparator()
                + RESULT + "," + resultScore + System.lineSeparator();
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

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file " + toFileName, e);
        }
    }
}

