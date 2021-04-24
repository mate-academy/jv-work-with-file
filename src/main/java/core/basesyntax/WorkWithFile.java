package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIAL_CHARACTERS = "[,\\s\\-:?]";
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFromFile(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
        ) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file", e);
        }
    }

    private static String readFromFile(String fromFileName) {
        StringBuilder statisticReport = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] arrayWithLowerCase = (line.toLowerCase()
                        + System.lineSeparator()).split(SPECIAL_CHARACTERS);
                for (int i = 0; i < arrayWithLowerCase.length; i++) {
                    String wordToCompare = arrayWithLowerCase[i];
                    if (wordToCompare.equals(SUPPLY_OPERATION)) {
                        supplySum += Integer.parseInt(arrayWithLowerCase[i + 1]);
                    } else if (wordToCompare.equals(BUY_OPERATION)) {
                        buySum += Integer.parseInt(arrayWithLowerCase[i + 1]);
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        statisticReport.append(SUPPLY_OPERATION).append(CSV_SEPARATOR)
                .append(supplySum).append("\n");
        statisticReport.append(BUY_OPERATION).append(CSV_SEPARATOR)
                .append(buySum).append("\n");
        return statisticReport.append("result").append(CSV_SEPARATOR)
                .append(supplySum - buySum).toString();
    }
}
